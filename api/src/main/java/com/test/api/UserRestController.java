package com.test.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.test.api.Model.User;

import java.util.Collection;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DemandRepository demandRepository;

    UserRestController(UserRepository userRepo) { this.userRepository = userRepo;}


    @PostMapping(value = "/add")
    public User addUser(@RequestBody User newUser) {
        User u = this.userRepository.addUser(newUser);

        if(u == null)
            throw new WebApplicationException("User already exists",Response.Status.CONFLICT);

        return u;
    }

    @GetMapping
    public Collection<User> getUsers() {
        return this.userRepository.getUsers();
    }

    @PutMapping(value = "/updateDetails")
    public Response updateDetails(@RequestBody User newUser) {
        try {
            this.userRepository.updateUserDetails(newUser);
        }
        catch(Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        }

        return Response.ok().build();
    }

    //The userId is known because is sent in the Get endpoint. Besides, it is returned when the user is created
    @DeleteMapping(value = "/delete/{userId}")
    public Response deleteUser(@PathVariable int userId) {

        if(this.demandRepository.findDemandByUserId(userId) != null)
            throw new WebApplicationException("User cannot be erased. There is a pending demand", Response.Status.CONFLICT);

        try {
            this.userRepository.removeUser(userId);
        }
        catch(Exception e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        }

        return Response.ok().build();
    }

}
