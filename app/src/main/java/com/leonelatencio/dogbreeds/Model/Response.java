package com.leonelatencio.dogbreeds.Model;

import java.util.List;

/**
 * Created by Leonel on 08/20/2017.
 */

public class Response {

    private String status;
    private List<String> message;


    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
