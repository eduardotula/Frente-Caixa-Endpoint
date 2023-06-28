package com.loja65.alarmintelbras.domain.utils;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class CronUtils {

    public  String toCron(LocalDateTime dateTime) {
        return String.format("%s %s %s %s %s %s",
                dateTime.getMinute(), dateTime.getHour(), dateTime.getDayOfMonth(),
                dateTime.getMonth(), dateTime.getDayOfWeek(), dateTime.getYear());
    }
}
