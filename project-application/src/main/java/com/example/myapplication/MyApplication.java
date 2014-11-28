package com.example.myapplication;

import com.example.myapplication.resources.PersonResource;
import com.example.myapplication.services.ElasticSearchManager;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.elasticsearch.client.Client;

/**
 * Created by sakhtar on 27/11/2014.
 */
public class MyApplication extends Application<MyApplicationConfiguration> {
    public static void main(String[] args) throws Exception {
        new MyApplication().run(args);
    }
    @Override
    public void initialize(Bootstrap<MyApplicationConfiguration> bootstrap) {

    }

    public void run(MyApplicationConfiguration configuration, Environment environment) {

        // Create elasticsearch server
        ElasticSearchManager esManager = new ElasticSearchManager();
        final PersonResource personResource = new PersonResource(esManager.getClient());
        environment.jersey().register(personResource);
    }
}
