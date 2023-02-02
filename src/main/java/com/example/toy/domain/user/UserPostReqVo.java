package com.example.toy.domain.user;

import com.example.toy.annotation.NotNullEnum;
import com.example.toy.database.entity.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserPostReqVo {

    @NotNull @NotEmpty
    @JsonProperty("name")
    private String name;

    @NotNullEnum
    @JsonProperty("subscription_type")
    private SubscriptionType subscriptionType;

    @NotNull @NotEmpty
    @JsonProperty("email")
    private String email;

    @NotNull @NotEmpty
    @JsonProperty("password")
    private String password;

}
