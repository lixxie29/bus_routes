package service;

import domain.Route;
import repository.Repository;

import java.util.ArrayList;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public ArrayList<Route> getAll() throws Exception{
        var routes = repo.getAll();
        if (routes == null)
            throw new Exception("Could not read");
        return routes;}

    public void UpdateSchema(Integer updatedSeats,String sourceCity,String destCity,Integer departure){
        repo.UpdateSchema(updatedSeats,sourceCity,destCity,departure);
    }
}
