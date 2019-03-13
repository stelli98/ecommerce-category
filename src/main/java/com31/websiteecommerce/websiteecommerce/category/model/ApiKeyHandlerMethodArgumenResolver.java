package com31.websiteecommerce.websiteecommerce.category.model;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ApiKeyHandlerMethodArgumenResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ApiKey.class.equals(parameter.getParameterType());
    }


    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String value= webRequest.getHeader("api-key");

        if(value==null){
            throw new ApiKeyException("Unauthorized");
        }else{
            return  new ApiKey(value);
        }
    }
}
