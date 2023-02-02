package com.example.toy.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;


public enum SubscriptionType {
    WEB, GOO;

    @JsonCreator
    public static SubscriptionType fromCode(String code) {
        return Arrays.stream(SubscriptionType.values())
                     .filter(subscriptionType -> subscriptionType.name().equals(code))
                     .findFirst()
                     .orElse(null);
    }
}
