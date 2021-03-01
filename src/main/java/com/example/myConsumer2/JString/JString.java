package com.example.myConsumer2.JString;

public class JString {

    private int numOfJsons = 0;
    private String numOfTypes = "{ }}";

    public void setNumOfJsons(int num) {
        this.numOfJsons = num;
    }

    public void setNumOfTypes(String num) {
        this.numOfTypes = num;
    }

    public String getJString() {
        return "{ \"nOfTotalMessagesReceived\": " + this.numOfJsons +
                ", \"nOfMessagesByType\": " + this.numOfTypes + " }";
    }

}
