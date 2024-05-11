package com.loan.generation.dashboard.util;

public enum KafkaUtil {

    LO_GEN_TOPIC_RECEIVED("LO_GEN_TOPIC_RECEIVED");

    private final String name;

    private KafkaUtil(String s) {
        name = s;
    }


    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

}
