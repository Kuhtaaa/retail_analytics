package edu.school21.application.annotations;

import edu.school21.application.enums.Access;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AccessControl {
    Access value() default Access.PRIVATE;
}
