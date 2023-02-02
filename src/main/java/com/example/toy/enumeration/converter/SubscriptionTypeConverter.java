package com.example.toy.enumeration.converter;

import com.example.toy.enumeration.SubscriptionType;
import com.example.toy.restfull.controllers.exception.database.DatabaseReadException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

@Slf4j
@Converter
public class SubscriptionTypeConverter implements AttributeConverter<SubscriptionType, String> {

    @Override
    public String convertToDatabaseColumn(SubscriptionType subscriptionType) {
        if (ObjectUtils.isEmpty(subscriptionType)) return null;
        return subscriptionType.name();
    }

    @SneakyThrows
    @Override
    public SubscriptionType convertToEntityAttribute(String dbData) {
        if (ObjectUtils.isEmpty(dbData)) return null;

        SubscriptionType subscriptionType = SubscriptionType.fromCode(dbData);

        if (Objects.nonNull(subscriptionType)) return subscriptionType;

        throw new DatabaseReadException(String.format("subscription_type is invalid. actual: %s, supported: %s",
                                                      dbData,
                                                      Arrays.stream(SubscriptionType.values())
                                                            .map(Enum::name)
                                                            .collect(Collectors.joining(", "))));
    }
}
