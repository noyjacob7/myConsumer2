package com.example.myConsumer2.Controller;

import com.example.myConsumer2.Calculator.Calculator;
import com.example.myConsumer2.Consumer.Consumer;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    private final Consumer c;
    private Calculator cal;

    @Autowired
    Controller(Consumer c) {
        this.c = c;
        this.cal = new Calculator();
    }

    @GetMapping("/v1/stats")
    public String getMessages() throws JSONException {

        int numOfJsons = cal.getOnlyJmsgsNum(c.getMsgs());
        String numOfTypes = cal.getTypesFromMap();

        String answer = "{ \"nOfTotalMessagesReceived\": " + numOfJsons + ", \"nOfMessagesByType\": " + numOfTypes + " }";

        return answer;
    }

}
