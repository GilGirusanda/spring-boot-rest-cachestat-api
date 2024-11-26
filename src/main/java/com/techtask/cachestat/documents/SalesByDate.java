package com.techtask.cachestat.documents;

import lombok.Data;

@Data
public class SalesByDate {
    private MonetaryValue orderedProductSales;
    private MonetaryValue orderedProductSalesB2B;
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
    private MonetaryValue averageSalesPerOrderItem;
    private MonetaryValue averageSalesPerOrderItemB2B;
    private double averageUnitsPerOrderItem;
    private double averageUnitsPerOrderItemB2B;
    private MonetaryValue averageSellingPrice;
    private MonetaryValue averageSellingPriceB2B;
    private int unitsRefunded;
    private double refundRate;
    private int claimsGranted;
    private MonetaryValue claimsAmount;
    private MonetaryValue shippedProductSales;
    private int unitsShipped;
    private int ordersShipped;
}
