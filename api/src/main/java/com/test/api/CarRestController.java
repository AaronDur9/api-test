package com.test.api;

import com.test.api.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/cars")
public class CarRestController {

    @Autowired
    private CarRepository carRepository;

    CarRestController(CarRepository carRepo) {
        this.carRepository = carRepo;
    }

    @PostMapping(value = "/add")
    public Car addCar(@RequestBody Car newCar) {
        Car c = this.carRepository.addCar(newCar);

        if(c == null)
            throw new WebApplicationException("Car already exists",Response.Status.CONFLICT);

        return newCar;
    }

    //TODO: Add security with at least an agent (role with rights) table in DB
    @GetMapping
    public Collection<Car> getCars() {
        return this.carRepository.getCars();
    }

    @PutMapping(value = "/updateDetails")
    public Response updateDetails(@RequestBody Car newCar) {
        try {
            this.carRepository.updateCarDetails(newCar);
        }
        catch(Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        }

        return Response.ok().build();
    }

    @PutMapping(value = "/updateLocation/{carId}")
    public Response updateLocation(@PathVariable int carId, @RequestBody int location) {

        try {
            this.carRepository.updateCarLocation(carId, location);
        }
        catch(Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        }

        return Response.ok().build();
    }

    @PutMapping(value = "/updateBusyStatus/{carId}")
    public Response updateAvailabilityTime(@PathVariable int carId, @RequestBody int newDestination) {

        try {
            this.carRepository.updateAvailabilityTime(carId, newDestination);
        }
        catch(Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        }

        return Response.ok().build();
    }

    @DeleteMapping(value = "/delete/{carId}")
    public Response deleteCar(@PathVariable int carId) {

        try {
            this.carRepository.removeCar(carId);
        }
        catch(Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        }

        return Response.ok().build();
    }
}
