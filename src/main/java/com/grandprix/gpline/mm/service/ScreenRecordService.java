package com.grandprix.gpline.mm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.grandprix.gpline.mm.model.ScreenRecord;
import com.grandprix.gpline.mm.repository.ScreenRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScreenRecordService {

    @Autowired
    ScreenRecordRepository screenRecordRepository;

    public ScreenRecordService(ScreenRecordRepository screenRecordRepository) {
        this.screenRecordRepository = screenRecordRepository;
    }

    public List<String> getScreenRecords(String login, Long startDate, Long endDate) {
        List<ScreenRecord> records = screenRecordRepository.getScreenRecords(login, startDate, endDate);
        List<String> paths = records.stream().map(file ->
                "sld://" + file.getFileStore().getHost() + "?" + file.getFileStore().getWritePath() + "/" + file.getFileName()
            ).collect(Collectors.toList());
        return paths;
    }
}
