package com.example.toy.database.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "tb_user")
public class UserEntity {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_type")
    private SubscriptionType subscriptionType;

    @Column(name = "password")
    private String password;

    @Column(name = "reg_dt")
    private Timestamp regDt;

    @Builder
    public UserEntity(String email, String name, SubscriptionType subscriptionType, String password) {
         this.email = email;
         this.subscriptionType = subscriptionType;
         this.name = name;
         this.password = password;
         this.regDt = Timestamp.valueOf(LocalDateTime.now());
    }

}
