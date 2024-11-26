package com.techtask.cachestat.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.techtask.cachestat.documents.SalesAndTrafficReport;

public interface SalesAndTrafficReportRepository extends MongoRepository<SalesAndTrafficReport, String> {
    @Query("{ 'reportSpecification.dataStartTime': { $lte: ?1 }, 'reportSpecification.dataEndTime': { $gte: ?0 } }")
    List<SalesAndTrafficReport> findByDateBetween(String startDate, String endDate);

    @Query("{ 'salesAndTrafficByAsin': { $elemMatch: { 'parentAsin': { $in: ?0 } } } }")
    List<SalesAndTrafficReport> findByAsinIn(List<String> asins);
}
