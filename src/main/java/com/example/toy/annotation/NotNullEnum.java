package com.example.toy.annotation;

import com.example.toy.annotation.NotNullEnum.EnumValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target({ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidator.class)
public @interface NotNullEnum {
    String message() default "invalid enum value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class EnumValidator implements ConstraintValidator<NotNullEnum, Enum<?>> {

        @Override
        public void initialize(NotNullEnum constraintAnnotation) {

        }

        @Override
        public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
            //NotNullEnum 어노테이션 정상 동작을 위해 다음과 같이 처리
            //해당 Enum 객체 내 @JsonCreator 정의
            //parse value 없는 경우 null 반환
            return Objects.nonNull(value);
        }
    }
}
