package com.techtask.cachestat.documents;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "reports")
public class SalesAndTrafficReport {
    @Field("report_specification")
    private ReportSpecification reportSpecification;

    private List<SalesAndTrafficByDate> salesAndTrafficByDate;
    
    @Field("asin_stats")
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;
}
