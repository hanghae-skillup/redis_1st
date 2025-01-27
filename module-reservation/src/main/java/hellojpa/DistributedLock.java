package hellojpa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    String key();
    int waitTime() default 11;
    int leaseTime() default 10;
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}