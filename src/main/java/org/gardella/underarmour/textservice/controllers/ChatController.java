package org.gardella.underarmour.textservice.controllers;


import org.apache.commons.lang3.time.DateUtils;
import org.gardella.underarmour.textservice.models.Message;
import org.gardella.underarmour.textservice.models.MessageRequest;
import org.gardella.underarmour.textservice.models.User;
import org.gardella.underarmour.textservice.repos.MessageRepository;
import org.gardella.underarmour.textservice.repos.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ChatController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    private static final String DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";
    private static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_PATTERN);


    @PostMapping("/chat")
    public void postChat(@RequestBody MessageRequest messageRequest, HttpServletResponse resp) {


        User user = userRepository.findByUsername(messageRequest.getUsername());
        if(user == null) {
            user = new User();
            user.setUsername(messageRequest.getUsername());
            user = userRepository.save(user);

            logger.info("user saved id: " + user.getId() + " username: " + user.getUsername());
        }

        Message message = new Message();
        message.setUser(user);
        message.setText(messageRequest.getText());

        int timeoutSeconds = 60; //default
        if(messageRequest.getTimeout() != null) {
            try {
                timeoutSeconds = Integer.parseInt(messageRequest.getTimeout());
            } catch(NumberFormatException e) {
                //no-op
            }
        }
        Date now = new Date();
        now = DateUtils.addSeconds(now, timeoutSeconds);
        message.setExpirationDate(now);

        message = messageRepository.save(message);

        JSONObject jobj = new JSONObject();
        jobj.put("id", message.getId());

        returnJsonSuccess(jobj, resp);
    }

    @GetMapping("/chat/{id}")
    public void getChat(@PathVariable Integer id, HttpServletResponse resp) {

        JSONObject jobj = new JSONObject();
        Optional<Message> op = messageRepository.findById(id);

        if(op.isPresent()) {
            Message message = op.get();

            jobj.put("username", message.getUser().getUsername());
            jobj.put("text", message.getText());
            jobj.put("expiration_date", SDF.format(message.getExpirationDate()));
        }

        returnJsonSuccess(jobj, resp);
    }

    @GetMapping("/chats/{username}")
    public void getChatsByUsername(@PathVariable String username, HttpServletResponse resp) {

        User user = userRepository.findByUsername(username);
        JSONArray jsonArray = new JSONArray();
        if(user != null) {
            logger.info("user found id: " +  user.getId() + " username: " + user.getUsername());

            List<Message> list = user.getMessageList();

            logger.info("user id: " + user.getId() + " message count: " + list.size());

            for(Message m : list) {
                JSONObject jobj = new JSONObject();
                jobj.put("id", m.getId());
                jobj.put("text", m.getText());
                jsonArray.put(jobj);
            }
        }

        returnJsonSuccess(jsonArray, resp);
    }
}
