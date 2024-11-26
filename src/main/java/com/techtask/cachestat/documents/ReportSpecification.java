package com.techtask.cachestat.documents;

import java.util.List;

import lombok.Data;

@Data
public class ReportSpecification {
    private String reportType;
    private ReportOptions reportOptions;
    private String dataStartTime;
    private String dataEndTime;
    private List<String> marketplaceIds;
}
