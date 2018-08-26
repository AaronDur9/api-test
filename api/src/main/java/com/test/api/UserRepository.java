package com.test.api;

import com.test.api.Model.User;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class UserRepository {

    // DB loaded in memory
    private LinkedHashMap<Integer,User> users;

    public User addUser(User u){
        if(!this.findUserByDocumentId(u.getDocumentId())) {
            //Generate random id
            int id = (int) Math.round(Math.random());
            u.setUserId(id);
            this.users.put(id, u);
        }
        else
            u = null;

        return u;

    }

    public boolean findUserByUserId(int userId) {
        return (this.users.get(userId) != null);
    }

    private boolean findUserByDocumentId(String documentId){
        Iterator<User> iterator = this.getUsers().iterator();
        boolean found = false;
        while(iterator.hasNext() && !found) {
            if(iterator.next().getDocumentId().equals(documentId))
                found = true;
        }

        return found;
    }

    public void removeUser(int userId) throws Exception {
        User userRemoved = this.users.remove(userId);
        if(userRemoved == null)
            throw new Exception("User with id " + userId + " is not registered");
    }

    public void updateUserDetails(User u) throws Exception {
        if(this.users.get(u.getUserId()) != null) {
            this.users.replace(u.getUserId(), u);
        }
        else {
            throw new Exception("User with id " + u.getUserId() + " is not registered");
        }
    }

    public Collection<User> getUsers() {
        return this.users.values();
    }


}
