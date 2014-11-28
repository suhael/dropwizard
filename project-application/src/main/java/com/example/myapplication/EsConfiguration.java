package com.example.myapplication;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by sakhtar on 27/11/2014.
 */
public class EsConfiguration extends Configuration {

    @JsonProperty
    @NotEmpty
    public String host = "localhost";
    @JsonProperty
    @NotEmpty
    public int port = 9300;
    @JsonProperty
    @NotEmpty
    private String clusterName = "elasticsearch";

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getClusterName() {
        return clusterName;
    }
}
