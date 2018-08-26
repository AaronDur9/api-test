package com.test.api;

import com.test.api.Model.DemandDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Collection;

@RestController
@RequestMapping("/demands")
public class DemandRestController {

    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private UserRepository userRepository;

    DemandRestController(DemandRepository demandRepository) { this.demandRepository = demandRepository;}


    @PostMapping(value = "/add")
    public DemandDetails addDemand(@RequestBody DemandDetails newDemand) {

        DemandDetails demand = null;

        if(this.userRepository.findUserByUserId(newDemand.getUserId())){
            demand = this.demandRepository.addDemand(newDemand);
        }
        else
            throw new WebApplicationException("There is not an user with the specified id", Response.Status.CONFLICT);

        return demand;
    }


    @GetMapping
    public Collection<DemandDetails> getDemands() {
        return this.demandRepository.getDemands();
    }


    @PutMapping(value = "/updateDetails")
    public Response updateDemandDetails(@RequestBody DemandDetails newDemandDetails) {

        if(!this.userRepository.findUserByUserId(newDemandDetails.getUserId()))
            throw new WebApplicationException("There is not an user with the specified id", Response.Status.CONFLICT);

        try {
            this.demandRepository.updateDemandDetails(newDemandDetails);
        }
        catch(Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        }

        return Response.ok().build();
    }

    @DeleteMapping(value = "/delete/{demandId}")
    public Response deleteDemand(@PathVariable int demandId) {
        try {
            this.demandRepository.removeDemand(demandId);
        }
        catch(Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        }

        return Response.ok().build();
    }
}
