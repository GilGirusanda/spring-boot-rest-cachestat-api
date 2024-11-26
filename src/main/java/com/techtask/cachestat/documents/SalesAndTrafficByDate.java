package com.techtask.cachestat.documents;

import java.util.Date;

import lombok.Data;

@Data
public class SalesAndTrafficByDate {
    private Date date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;
}
