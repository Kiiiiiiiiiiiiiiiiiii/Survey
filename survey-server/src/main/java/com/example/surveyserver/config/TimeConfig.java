package com.example.surveyserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class TimeConfig {
    @Bean
    public Date getVTNTime() throws ParseException {
        TimeZone time;
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        df.setTimeZone(time);
        Date parseDate = df.parse(df.format(date));

        return parseDate;
    }

    @Bean
    public Date getFormatTime() throws ParseException {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseDate = df.parse(df.format(date));

        return parseDate;
    }
}
