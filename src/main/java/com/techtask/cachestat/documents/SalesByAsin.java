package com.techtask.cachestat.documents;

import lombok.Data;

@Data
public class SalesByAsin {
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private MonetaryValue orderedProductSales;
    private MonetaryValue orderedProductSalesB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
}
