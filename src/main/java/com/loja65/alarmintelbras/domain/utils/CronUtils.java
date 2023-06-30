package com.loja65.alarmintelbras.domain.utils;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalTime;

@ApplicationScoped
public class CronUtils {

    public String toCronHour(LocalTime time) {
        return String.format("%s %s %s ? * * *",
                time.getSecond(), time.getMinute(), time.getHour());
    }
}
