package com.loja65.alarmintelbras.domain.utils;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.LocalTime;

@ApplicationScoped
public class CronUtils {

    public String toCronHour(LocalTime time) {
        return String.format("%s %s %s ? * * *",
                time.getSecond(), time.getMinute(), time.getHour());
    }

    public String toCronHourSingleUse(LocalDateTime localDateTime) {
        return String.format("%s %s %s %s %s ? %s",
                localDateTime.getSecond(), localDateTime.getMinute(), localDateTime.getHour(),
                localDateTime.getDayOfMonth(), localDateTime.getMonth(), localDateTime.getYear());
    }
}
