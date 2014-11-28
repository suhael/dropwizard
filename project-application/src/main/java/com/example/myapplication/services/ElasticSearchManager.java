package com.example.myapplication.services;

import io.dropwizard.lifecycle.Managed;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

public class ElasticSearchManager implements Managed {

    private final Client client;

    public ElasticSearchManager() {
        final Settings settings = ImmutableSettings.settingsBuilder()
                .put("client.transport.sniff", true)
                .put("cluster.name", "elasticsearch").build();

        client =  new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress("localhost", 9300));

        //node = nodeBuilder().clusterName("elasticsearch").client(true).node();
        //client = node.client();
    }

    public void start() throws Exception {
    }

    public void stop() throws Exception {
        client.close();
    }

    public Client getClient() {
        return client;
    }

}
