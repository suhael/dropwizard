package com.example.myapplication.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.myapplication.api.MealTime;
import com.example.myapplication.db.MealTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by sakhtar on 19/01/2015.
 */
@Path("/mealtime")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MealTimeResource {

    private final MealTimeRepository mealTimeRepository;
    final static Logger logger = LoggerFactory.getLogger(MealTimeResource.class);

    public MealTimeResource(MealTimeRepository mealTimeRepository){
        this.mealTimeRepository = mealTimeRepository;
    }

    @GET
    @Timed
    public List<MealTime> getMealTimes() {
        return mealTimeRepository.getMealTimes();
    }

    @POST
    public void saveMealTime(@Valid MealTime mealTime){
        mealTimeRepository.saveMealTime(mealTime);
    }

    @GET
    @Timed
    @Path("/{id}")
    public MealTime getMealTime(@PathParam("id") String id){
        return mealTimeRepository.getMealTime(id);
    }

    @PUT
    @Path("/{id}")
    public void updateMealTime(@PathParam("id") String id, @Valid MealTime mealTime){
        mealTimeRepository.updateMealTime(id, mealTime);
    }

    @DELETE
    @Path("/{id}")
    public void deleteMealTime(@PathParam("id") String id){
        mealTimeRepository.deleteMealTime(id);
    }
}
