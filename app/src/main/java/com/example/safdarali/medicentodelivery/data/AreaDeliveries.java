package com.example.safdarali.medicentodelivery.data;

public class AreaDeliveries {
    private String mAreaName;
    private int mNoOfDeliveries;

    public AreaDeliveries(String areaName, int noOfDeliveries) {
        mAreaName = areaName;
        mNoOfDeliveries = noOfDeliveries;
    }

    public String getNoOfDeliveries() {
        return String.valueOf(mNoOfDeliveries);
    }

    public String getAreaName() {
        return mAreaName;
    }
}
