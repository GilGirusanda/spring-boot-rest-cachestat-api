package com.techtask.cachestat.documents;

import lombok.Data;

@Data
public class MonetaryValue {
    private double amount;
    private String currencyCode;
}
