package com.example.surveyserver.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();  // 쿠키에 정보를 저장
        resolver.setDefaultLocale(Locale.KOREA);  // 기본값은 한국어로 세팅
        resolver.setCookieName("lang");  // 쿠키에 lang으로 저장
        return resolver;
    }


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lcInterceptor = new LocaleChangeInterceptor();
        lcInterceptor.setParamName("lang");   // request로 넘어오는 language 파라미터를 받아서 locale로 설정
        return lcInterceptor;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();  // locale 프로퍼티 파일 관련 설정 역할을 하는 친구
        messageSource.setBasename("messages/lang");  // 경로 설정
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
