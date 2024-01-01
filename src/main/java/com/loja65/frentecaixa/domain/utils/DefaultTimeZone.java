package com.loja65.frentecaixa.domain.utils;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ApplicationScoped
public class DefaultTimeZone {

    @ConfigProperty(name = "quarkus.timezone.zoneId")
    String zoneId;

    public LocalDateTime getSp(){
        var now = ZonedDateTime.now().withZoneSameInstant(ZoneId.of(zoneId));
        var t = now.toLocalDateTime();
        return t;
    }

    public LocalDateTime convertToSystemZoneId(LocalDateTime localDateTime){
        var correctTime = ZonedDateTime.of(localDateTime,ZoneId.of(zoneId));
        var convertedTime = correctTime.withZoneSameInstant(ZoneId.systemDefault());
        var t = convertedTime.toLocalDateTime();
        return t;
    }
}
