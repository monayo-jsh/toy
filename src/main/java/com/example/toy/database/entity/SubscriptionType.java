package com.example.toy.database.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;


public enum SubscriptionType {
    WEB;

    @JsonCreator
    public static SubscriptionType from(String code) {
        return Arrays.stream(SubscriptionType.values())
                     .filter(subscriptionType -> subscriptionType.name().equals(code))
                     .findFirst()
                     .orElse(null);
    }
}
