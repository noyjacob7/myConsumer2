package com.example.myConsumer2.Controller;

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

    @Autowired
    Controller(Consumer c) {
        this.c = c;
    }

    public List<JSONObject> getOnlyJmsgs() {
        List<JSONObject> jMsgs = new ArrayList<>();
        JSONObject obj;

        for (String tmp : c.getMsgs()) {
            try {
                obj = new JSONObject((tmp));
            } catch (JSONException ex) {
                continue;
            }

            jMsgs.add(obj);
        }

        return jMsgs;
    }

    public Map<String, Integer> calcTypes(List<JSONObject> jMsgs) throws JSONException {
        Map<String, Integer> mapTypes = new HashMap<String, Integer>();
        String currType;
        Integer currNum;

        for (JSONObject obj : jMsgs) {
            currType = obj.getString("typeId");

            if (mapTypes.containsKey(currType)) {
                currNum = mapTypes.get(currType);
                mapTypes.put(currType, currNum + 1);
            }
            else {
                mapTypes.put(currType, 1);
            }
        }

        return mapTypes;
    }

    public String getTypesFromMap(Map<String, Integer> mapTypes) {
        String numOfTypes = "{ ";

        for (Map.Entry<String, Integer> entry : mapTypes.entrySet()) {
            numOfTypes += "\"" + entry.getKey() + "\": " + entry.getValue() + ", ";
        }
        numOfTypes = numOfTypes.substring(0, numOfTypes.length() - 2) + " }";

        return numOfTypes;
    }

    @GetMapping("/v1/stats")
    public String getMessages() throws JSONException {

        List<JSONObject> jMsgs = getOnlyJmsgs();
        Map<String, Integer> mapTypes = calcTypes(jMsgs);
        String numOfTypes = getTypesFromMap(mapTypes);

        String answer = "{ \"nOfTotalMessagesReceived\": " + jMsgs.size() + ", \"nOfMessagesByType\": " + numOfTypes + " }";

        return answer;
    }

}
