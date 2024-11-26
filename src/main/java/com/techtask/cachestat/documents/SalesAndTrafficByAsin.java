package com.techtask.cachestat.documents;

import lombok.Data;

@Data
public class SalesAndTrafficByAsin {
    private String parentAsin;
    private SalesByAsin salesByAsin;
    private TrafficByAsin trafficByAsin;
}
