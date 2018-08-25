package com.example.safdarali.medicentodelivery.data;

public class DeliveryJob {
    private String mPickUpPoint;
    private String mDeliveryPoint;
    private int mSlot;
    private float mCost;
    private float mDistance;
    private String mStatus;

    public DeliveryJob(String pickupPoint, String deliveryPoint, int slot, float distance, String status, float cost) {
        mPickUpPoint = pickupPoint;
        mDeliveryPoint = deliveryPoint;
        mSlot = slot;
        mDistance = distance;
        mStatus = status;
        mCost = cost;
    }

    public String getPickUpPoint() {
        return mPickUpPoint;
    }

    public String getDeliveryPoint() {
        return mDeliveryPoint;
    }

    public String getSlot() {
        return String.valueOf(mSlot);
    }

    public String getDistance() {
        return String.valueOf(mDistance);
    }

    public String getStatus() {
        return mStatus;
    }

    public String getCost() {
        return String.valueOf(mCost);
    }
}
