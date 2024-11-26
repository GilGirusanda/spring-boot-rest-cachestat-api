package com.techtask.cachestat.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techtask.cachestat.documents.SalesAndTrafficReport;
import com.techtask.cachestat.repo.SalesAndTrafficReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SalesAndTrafficReportService {

    @Value("${json.path}")
    String jsonPath;

    @Autowired
    private SalesAndTrafficReportRepository reportRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();
    
    // @Scheduled(fixedRate = 300000) // every 5 mins
    public boolean updateStatisticsFromJson() {
        // load JSON from file
        String jsonResponse = fetchJsonFromUrl();
        boolean isUpdated = false;

        // parse the JSON and update mongodb storage
        try {
            SalesAndTrafficReport newReport = objectMapper.readValue(jsonResponse, SalesAndTrafficReport.class);
            
            // check updated fields if any
            Query query = new Query(Criteria.where("reportSpecification").is(newReport.getReportSpecification()));
            SalesAndTrafficReport existingReport = mongoTemplate.findOne(query, SalesAndTrafficReport.class);
            isUpdated = this.updateOrCreate(newReport, existingReport, query);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return isUpdated;
    }

    private String fetchJsonFromUrl() {
        try {
            return Files.readString(Paths.get(this.jsonPath));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateOrCreate(SalesAndTrafficReport newReport, SalesAndTrafficReport existingReport, Query query) {
        boolean isUpdated = false;

        if (existingReport != null) {
            Update update = new Update();

            // compare and update salesAndTrafficByDate
            if (!newReport.getSalesAndTrafficByDate().equals(existingReport.getSalesAndTrafficByDate())) {
                update.set("salesAndTrafficByDate", newReport.getSalesAndTrafficByDate());
                isUpdated = true;
            }

            // compare and update salesAndTrafficByAsin
            if (!newReport.getSalesAndTrafficByAsin().equals(existingReport.getSalesAndTrafficByAsin())) {
                update.set("salesAndTrafficByAsin", newReport.getSalesAndTrafficByAsin());
                isUpdated = true;
            }

            if (isUpdated) {
                mongoTemplate.updateFirst(query, update, SalesAndTrafficReport.class);
            }
        } else {
            // add new record if no match
            mongoTemplate.insert(newReport);
        }

        return isUpdated;
    }

    public List<SalesAndTrafficReport> getReportsByAsins(List<String> asins) {
        return reportRepository.findByAsinIn(asins);
    }

    public List<SalesAndTrafficReport> getReportsByDateBetween(String startDate, String endDate) {
        return reportRepository.findByDateBetween(startDate, endDate);
    }

    public List<SalesAndTrafficReport> findAll() {
        return reportRepository.findAll();
    }
}
