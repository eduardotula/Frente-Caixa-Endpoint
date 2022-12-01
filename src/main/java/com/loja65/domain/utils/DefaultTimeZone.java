package com.loja65.domain.utils;

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
}