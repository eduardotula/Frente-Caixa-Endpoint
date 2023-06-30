package com.loja65.alarmintelbras.inbound.port;

import com.loja65.alarmintelbras.domain.model.Central;

import java.util.List;

public interface CentralPort {

    Central update(Central central);

    Central create(Central central);

    void delete(int id);

    List<String> getScheduledJobs();
}
