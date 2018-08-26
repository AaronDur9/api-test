package com.test.api.Model;

import java.util.Date;

public class DemandDetails {

    private CarFeatures desiredFeatures;
    private int pickUpLocation;
    private int dropOffLocation;
    private Date pickUp;
    private Date dropOff;
    private int userId;
    private int demandId;
    private int assignedCarId;

    public Date getPickUp() {
        return pickUp;
    }

    public void setPickUp(Date pickUp) {
        this.pickUp = pickUp;
    }

    public Date getDropOff() {
        return dropOff;
    }

    public void setDropOff(Date dropOff) {
        this.dropOff = dropOff;
    }
    public int getAssignedCarId() {
        return assignedCarId;
    }

    public void setAssignedCarId(int assignedCarId) {
        this.assignedCarId = assignedCarId;
    }

    public CarFeatures getDesiredFeatures() {
        return desiredFeatures;
    }

    public void setDesiredFeatures(CarFeatures desiredFeatures) {
        this.desiredFeatures = desiredFeatures;
    }

    public int getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(int pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public int getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(int dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDemandId() {
        return demandId;
    }

    public void setDemandId(int demandId) {
        this.demandId = demandId;
    }


}
