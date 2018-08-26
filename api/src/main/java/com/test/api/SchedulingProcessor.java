package com.test.api;

import com.test.api.Model.DemandDetails;
import com.test.api.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class SchedulingProcessor {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DemandRepository demandRepository;

    public Car scheduleDemand(int demandId) throws Exception {

        DemandDetails demand = this.demandRepository.getDemand(demandId);
        if(demand == null)
            throw new Exception("Demand with id" + demandId + "does not exist");

        ArrayList<Car> potentialCars = new ArrayList<Car>(this.carRepository.findNoBusyCarsWithFeatures(demand.getPickUp(), demand.getDesiredFeatures()));

        if(potentialCars.isEmpty())
            throw new Exception("No cars with the desired features or free at the desired pick up time");

        //Find the nearest car
        Car car = this.findNearestCar(potentialCars, demand.getPickUpLocation());

        //Set a new available time for the car given the drop off location
        this.carRepository.updateAvailabilityTime(car.getCarId(), demand.getPickUpLocation());

        this.demandRepository.demandToScheduled(demandId, car.getCarId());

        return car;
    }


    private Car findNearestCar(ArrayList<Car> cars, int demandLocation) {
        //We sort the list in order to get the nearest car in the first position of the list
        Collections.sort(cars, new SortbyDistance(demandLocation));

        return cars.get(0);
    }
}

class SortbyDistance implements Comparator<Car>
{
    private int desiredLocation;

    SortbyDistance(int desiredLocation) {
        this.desiredLocation = desiredLocation;
    }
    public int compare(Car a, Car b)
    {
        return Math.abs(a.getLocation() - this.desiredLocation) - Math.abs(b.getLocation() - this.desiredLocation);
    }
}
