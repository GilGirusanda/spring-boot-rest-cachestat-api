package com.techtask.cachestat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techtask.cachestat.documents.SalesAndTrafficReport;
import com.techtask.cachestat.dto.AsinRequest;
import com.techtask.cachestat.services.SalesAndTrafficReportService;

@RestController
@RequestMapping("/api/statistics")
public class ReportController {

    @Autowired
    private SalesAndTrafficReportService reportService;

    // get statistics by date range or a single date
    @Cacheable(value = "statisticsByDate", key = "#startDate + ':' + #endDate")
    @GetMapping("/date")
    public List<SalesAndTrafficReport> getStatisticsByDate(@RequestParam String startDate, @RequestParam(required = false) String endDate) {
        if (endDate == null || endDate.isEmpty()) {
            endDate = new String(startDate);
        }
        return reportService.getReportsByDateBetween(startDate, endDate);
    }

    // get statistics by a single ASIN or ASIN list
    @Cacheable(value = "statisticsByAsin", key = "#asinRequest.asins")
    @PostMapping("/asin")
    public List<SalesAndTrafficReport> getStatisticsByAsin(@RequestBody AsinRequest asinRequest) {
        return reportService.getReportsByAsins(asinRequest.getAsins());
    }

    // get overall statistics
    @Cacheable(value = "allStatistics")
    @GetMapping("/all")
    public List<SalesAndTrafficReport> getAllStatistics() {
        return reportService.findAll();
    }

    // update statistics from "json.path" variable, linking to the file location
    @Caching(evict = {
        @CacheEvict(value = "statisticsByDate", allEntries = true),
        @CacheEvict(value = "statisticsByAsin", allEntries = true),
        @CacheEvict(value = "allStatistics", allEntries = true)
    })
    @Scheduled(fixedRate = 300000) // every 5 minutes
    @PostMapping("/update")
    public ResponseEntity<?> updateStatisticsFromJson() {
        boolean isUpdated = reportService.updateStatisticsFromJson();
        
        if (isUpdated) {
            return ResponseEntity.ok("Report updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body("New report instance created successfully");
        }
    }
}
