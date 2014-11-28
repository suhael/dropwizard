package com.example.myapplication.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.myapplication.api.Person;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by sakhtar on 27/11/2014.
 */
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final Client client;

    public PersonResource(Client client){
        this.client = client;
    }

    @GET
    @Timed
    public Person getPerson(){

        try {
            GetResponse responseGet = client.prepareGet("twitter", "tweet", "1")
                    .setOperationThreaded(false)
                    .execute()
                    .actionGet();

            System.out.println(responseGet.getSourceAsString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Person(1, "suhael");
    }
}
