package com.test.api;

import com.test.api.Model.Car;
import com.test.api.Model.CarFeatures;
import com.test.api.Utils.LocationUtils;

import java.util.*;

public class CarRepository {

    // DB loaded in memory
    private LinkedHashMap<Integer,Car> cars;

    public Car addCar(Car c){

        if(!this.findCarByPlateNumber(c.getPlateNumber())) {
            //Generate random id
            //Generating an id and not using the plate number as key
            //lets us to change the plate without problem.
            //The id will not change
            int id = (int) Math.round(Math.random());
            c.setCarId(id);
            this.cars.put(id, c);
        }
        else
            c = null;

        return c;

    }

    private boolean findCarByPlateNumber(String plateNumber){
        Iterator<Car> iterator = this.getCars().iterator();
        boolean found = false;
        while(iterator.hasNext() && !found) {
            if(iterator.next().getPlateNumber().equals(plateNumber))
                found = true;
        }

        return found;
    }

    public void removeCar(int carId) throws Exception {
        Car carRemoved = this.cars.remove(carId);
        if(carRemoved == null)
            throw new Exception("Car with id " + carId + " is not registered");
    }

    public void updateCarDetails(Car c) throws Exception {
        if(this.cars.get(c.getCarId()) != null) {
            this.cars.replace(c.getCarId(), c);
        }
        else {
            throw new Exception("Car with id " + c.getCarId() + " is not registered");
        }
    }

    public void updateCarLocation(int carId, int location) throws Exception {
        Car c = this.cars.get(carId);
        if(c != null) {
            c.setLocation(location);
            this.cars.replace(carId, c);
        }
        else {
            throw new Exception("Car with id " + carId + " is not registered");
        }
    }

    //Calculates the time a car will be available given a new destination for the car
    public void updateAvailabilityTime(int carId, int newDestination) throws Exception {
        Car c = this.cars.get(carId);
        if(c != null) {
            //Using the google maps API would be the most accurate way to calculate this
            //This is just a mock
            int distance = LocationUtils.getDistance(c.getLocation(), newDestination);
            long time = LocationUtils.calculateTime(distance);

            Date d = new Date();

            long newAvailableTime = d.getTime() + time;
            c.setTimeAvailable(new Date(newAvailableTime));

            this.cars.replace(carId, c);
        }
        else {
            throw new Exception("Car with id " + carId + " is not registered");
        }
    }

    public Collection<Car> getCars() {
        return this.cars.values();
    }

    protected Collection<Car> findNoBusyCarsWithFeatures(Date pickUpTime, CarFeatures features) {
        Collection<Car> selectedCars = new ArrayList<Car>();

        Iterator<Car> iterator = this.getCars().iterator();

        while(iterator.hasNext()) {
            Car car = iterator.next();
            if(car.getTimeAvailable() == null || car.getTimeAvailable().before(pickUpTime))
                if(car.checkFeatures(features))
                    selectedCars.add(car);
        }

        return selectedCars;
    }

}
