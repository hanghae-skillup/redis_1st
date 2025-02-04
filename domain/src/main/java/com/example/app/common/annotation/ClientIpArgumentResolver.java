package com.example.app.common.annotation;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;
import java.util.stream.Stream;

@Component
public class ClientIpArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(ClientIp.class);
    }

    @Override
    public String resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest,
            WebDataBinderFactory webDataBinderFactory) {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();

        return getClientIp(request);
    }

    private String getClientIp(HttpServletRequest request) {
        return Stream.of(
                        request.getHeader("X-Forwarded-For"),
                        request.getHeader("Proxy-Client-IP"),
                        request.getHeader("WL-Proxy-Client-IP"),
                        request.getHeader("HTTP_CLIENT_IP"),
                        request.getHeader("HTTP_X_FORWARDED_FOR")
                )
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(request.getRemoteAddr());
    }
}
