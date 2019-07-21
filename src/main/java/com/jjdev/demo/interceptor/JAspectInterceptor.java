package com.jjdev.demo.interceptor;

import com.jjdev.demo.dto.JUserDto;
import com.jjdev.demo.response.JResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Aspect
@Component
public class JAspectInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JAspectInterceptor.class);

    @After("execution(* com.jjdev.demo.controller.JUserController.create(..)) && args(userDto, result)")
    private void doSomethingAfterCall(JUserDto userDto, BindingResult result) {
        //do something here
        log.info("Intercepted POST: {}", userDto);
    }

    @AfterReturning(pointcut = "execution(* com.jjdev.demo.controller.JUserController.read(..))", returning = "result")
    private void doSomethigAfterReturn(JoinPoint joinPoint, Object result) {
        //do something here
        ResponseEntity responseEntity = (ResponseEntity) result;
        JResponse<JUserDto> response = (JResponse<JUserDto>) responseEntity.getBody();
        log.info("Returned GET: {}", (JUserDto) response.getData());
    }

}
