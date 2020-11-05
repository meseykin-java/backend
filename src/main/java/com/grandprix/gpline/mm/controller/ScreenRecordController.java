package com.grandprix.gpline.mm.controller;

import java.util.List;

import com.grandprix.gpline.mm.service.ScreenRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Запрос списка видео-файлов (скриншотов), точнее их путей
 */
@RestController
public class ScreenRecordController {

    @Autowired
    ScreenRecordService screenRecordService;

    @GetMapping(value = "/screenshotfiles")
    @CrossOrigin(origins = "*")
    public List<String> getScreenshotFiles(
            @RequestParam String login,
            @RequestParam Long startDate,
            @RequestParam Long endDate
    ) {
        return screenRecordService.getScreenRecords(login, startDate, endDate);
    }
}
