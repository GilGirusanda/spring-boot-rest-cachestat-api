package com.techtask.cachestat.documents;

import lombok.Data;

@Data
public class TrafficByAsin {
    private int browserSessions;
    private int browserSessionsB2B;
    private int mobileAppSessions;
    private int mobileAppSessionsB2B;
    private int sessions;
    private int sessionsB2B;

    private int browserSessionPercentage;
    private int browserSessionPercentageB2B;
    private int mobileAppSessionPercentage;
    private int mobileAppSessionPercentageB2B;
    private int sessionPercentage;
    private int sessionPercentageB2B;

    private int browserPageViews;
    private int browserPageViewsB2B;
    private int mobileAppPageViews;
    private int mobileAppPageViewsB2B;
    private int pageViews;
    private int pageViewsB2B;

    private int browserPageViewsPercentage;
    private int browserPageViewsPercentageB2B;
    private int mobileAppPageViewsPercentage;
    private int mobileAppPageViewsPercentageB2B;
    private int pageViewsPercentage;
    private int pageViewsPercentageB2B;

    private int buyBoxPercentage;
    private int buyBoxPercentageB2B;
    private int unitSessionPercentage;
    private int unitSessionPercentageB2B;
}
