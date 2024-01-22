package edu.school21.application.aspects;

import edu.school21.application.annotations.AccessControl;
import edu.school21.application.enums.Access;
import edu.school21.application.services.CustomerUserService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;


@Component
@AllArgsConstructor
@Aspect
public class AccessControlAspect {

    private final CustomerUserService service;

    @Pointcut(value = "@within(edu.school21.application.annotations.AccessControl) || " +
                      "@annotation(edu.school21.application.annotations.AccessControl)")
    public void callAccessControlAnnotation() {
        // implementation is not required
    }

    @Around("callAccessControlAnnotation()")
    public Object checkAccessControl(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        final Access accessControl = getMethodOrTypeAnnotation(method, AccessControl.class)
                .map(AccessControl::value)
                .orElse(Access.PUBLIC);

        if(service.getAccessType() != accessControl && accessControl == Access.PRIVATE) {
            throw new RuntimeException("Permission denied");
        }
        return joinPoint.proceed();
    }

    private <T extends Annotation> Optional<T> getMethodOrTypeAnnotation(
            final Method method,
            final Class<T> annotationType
    ) {
        return Optional.ofNullable(Optional
                .ofNullable(AnnotationUtils.findAnnotation(method, annotationType))
                .orElse(AnnotationUtils.findAnnotation(method.getDeclaringClass(), annotationType)));
    }
}
