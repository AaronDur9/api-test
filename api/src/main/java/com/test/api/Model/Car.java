package com.test.api.Model;

import java.util.Date;

public class Car {

    private int carId;
    private String plateNumber;
    private CarFeatures features;
    private int location;
    //Indicates the time the car will be available
    private Date timeAvailable;

    public Date getTimeAvailable() {
        return timeAvailable;
    }

    public void setTimeAvailable(Date timeAvailable) {
        this.timeAvailable = timeAvailable;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public CarFeatures getFeatures() {
        return features;
    }

    public void setFeatures(CarFeatures features) {
        this.features = features;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    /*
    Return true if every feature is in the car
     */
    public boolean checkFeatures(CarFeatures featuresToCheck) {
        return this.features.checkFeatures(featuresToCheck.getModel(), featuresToCheck.getEngine(), featuresToCheck.getInfoSystem());
    }



}
