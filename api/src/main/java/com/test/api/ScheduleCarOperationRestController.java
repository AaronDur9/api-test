package com.test.api;


import com.test.api.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/schedule")
public class ScheduleCarOperationRestController {

    @Autowired
    private SchedulingProcessor processor;


    @GetMapping("/{demandId}")
    public Car scheduleDemand(@PathVariable int demandId) {
        Car c = null;
        try {
            c = this.processor.scheduleDemand(demandId);
        }
        catch(Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        }

        return c;
    }


}
