package com.example.myConsumer2.Calculator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculator {

    private List<JSONObject> msgs = new ArrayList<>();
    private Map<String, Integer> mapTypes = new HashMap<String, Integer>();

    public int getOnlyJmsgsNum(List<String> lst) {
        JSONObject obj;

        for (String tmp : lst) {
            try {
                obj = new JSONObject((tmp));
            } catch (JSONException ex) {
                continue;
            }

            this.msgs.add(obj);
        }

        return this.msgs.size();
    }

    public void calcTypes(List<JSONObject> jMsgs) throws JSONException {
        String currType;
        Integer currNum;

        for (JSONObject obj : jMsgs) {
            currType = obj.getString("typeId");

            if (this.mapTypes.containsKey(currType)) {
                currNum = this.mapTypes.get(currType);
                this.mapTypes.put(currType, currNum + 1);
            }
            else {
                this.mapTypes.put(currType, 1);
            }
        }

    }

    public String getTypesFromMap() throws JSONException {
        calcTypes(this.msgs);

        if (this.mapTypes.isEmpty()) {
            return "{ }";
        }
        else {
            String numOfTypes = "{ ";

            for (Map.Entry<String, Integer> entry : this.mapTypes.entrySet()) {
                numOfTypes += "\"" + entry.getKey() + "\": " + entry.getValue() + ", ";
            }
            numOfTypes = numOfTypes.substring(0, numOfTypes.length() - 2) + " }";

            return numOfTypes;
        }

    }

}
