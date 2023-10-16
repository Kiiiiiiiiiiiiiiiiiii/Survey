package com.example.surveyserver.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date clearDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        //시분초 0으로 설정
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Date returnDate = cal.getTime();
        return returnDate;
    }

    public static Date addDay(Date date, int addDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 일을 추가
        cal.add(Calendar.DAY_OF_MONTH, addDay);
        Date returnDate = cal.getTime();
        return returnDate;
    }
}
