package com.test.api;

import com.test.api.Model.DemandDetails;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class DemandRepository {

    // DB loaded in memory
    private LinkedHashMap<Integer,DemandDetails> carDemands;

    private LinkedHashMap<Integer,DemandDetails> carDemandsScheduled;

    public DemandDetails findDemandByUserId(int userId) {
        Iterator<DemandDetails> iterator = this.getDemands().iterator();
        boolean found = false;
        DemandDetails demand = null;
        while(iterator.hasNext() && !found) {
            demand = iterator.next();
            if(demand.getUserId() == userId)
                found = true;
        }

        return demand;
    }

    public DemandDetails addDemand(DemandDetails demand) {
        int id = (int) Math.round(Math.random());
        demand.setDemandId(id);
        this.carDemands.put(id, demand);

        return demand;
    }

    public void removeDemand(int demandId) throws Exception {
        DemandDetails d = this.carDemands.remove(demandId);

        if(d == null)
            throw new Exception("Demand with id" + demandId + "does not exist");
    }

    public void updateDemandDetails(DemandDetails newDemandDetails) throws Exception {
        if(this.carDemands.get(newDemandDetails.getDemandId()) != null) {
            this.carDemands.replace(newDemandDetails.getDemandId(), newDemandDetails);
        }
        else {
            throw new Exception("Demand with id" + newDemandDetails.getDemandId() + "does not exist");
        }
    }

    public void demandToScheduled(int demandId, int carId) throws Exception {

        DemandDetails demand = this.carDemands.get(demandId);

        if(demand == null)
            throw new Exception("Demand with id" + demandId + "does not exist");

        demand.setAssignedCarId(carId);
        this.carDemandsScheduled.put(demandId, demand);
        this.carDemands.remove(demandId);

    }


    public Collection<DemandDetails> getDemands() {
        return this.carDemands.values();
    }

    protected DemandDetails getDemand(int demandId) {
        return this.carDemands.get(demandId);
    }
}
