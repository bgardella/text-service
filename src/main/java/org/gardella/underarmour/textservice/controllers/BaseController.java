package org.gardella.underarmour.textservice.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected void returnJsonSuccess(JSONObject data, HttpServletResponse response){

        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(data.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            logger.error("cannot write json response", e);
        }
    }

    protected void returnJsonSuccess(JSONArray arr, HttpServletResponse response){

        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-cache");
        try {
            response.getWriter().write(arr.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            logger.error("cannot write json response", e);
        }
    }

}
