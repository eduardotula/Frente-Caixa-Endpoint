package com.loja65.alarmintelbras.outbound.port;

import com.loja65.alarmintelbras.domain.model.CentralScheduleJob;

import java.util.List;

public interface CentralScheduleJobPort {


    CentralScheduleJob save(CentralScheduleJob centralScheduleJob);

    List<CentralScheduleJob> listAll();

    void delete(Integer id);
}
