package org.gardella.underarmour.textservice.services;

import org.gardella.underarmour.textservice.models.ExpiredMessage;
import org.gardella.underarmour.textservice.models.Message;
import org.gardella.underarmour.textservice.repos.ExpiredMessageRepository;
import org.gardella.underarmour.textservice.repos.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class MessageMigrator {

    private static final Logger logger = LoggerFactory.getLogger(MessageMigrator.class);

    private static final String DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";
    private static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_PATTERN);

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ExpiredMessageRepository expiredMessageRepository;

    @Autowired
    private TaskExecutor taskExecutor;


    @Scheduled(fixedRate = 15000)//run every 15 seconds
    public void sweepMessages(){

        logger.info("sweeping...");

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                sweep();
            }
        });
    }


    private void sweep() {
        List<Message> messageList = messageRepository.findAll();

        Date now = new Date();
        for(Message message : messageList) {

            logger.info( SDF.format(now) + " : " + SDF.format(message.getExpirationDate()) );

            if(now.after(message.getExpirationDate())){
                logger.info("expire message id: " + message.getId());
                ExpiredMessage expiredMessage = message.expire();
                expiredMessageRepository.save(expiredMessage);
                messageRepository.delete(message);
            }
        }
    }
}
