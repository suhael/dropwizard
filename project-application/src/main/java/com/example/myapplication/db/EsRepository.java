package com.example.myapplication.db;

import com.example.myapplication.api.Ingredient;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.Client;

import java.io.IOException;

/**
 * Created by sakhtar on 23/12/2014.
 */
public class EsRepository<T> {

    private final Client client;
    private final ObjectMapper mapper;

    public EsRepository(Client client, ObjectMapper mapper){
        this.client = client;
        this.mapper = mapper;
    }

    protected Client getClient() {
        return client;
    }

    protected ObjectMapper getMapper() {
        return mapper;
    }

    protected IndexRequestBuilder prepareIndex(String index, String type) {
        return client.prepareIndex(index, type);
    }


    /**
     * Prepare a get request using the provided index, type and id.
     * @param index    The index name.
     * @param type     The type name.
     * @param id       The id.
     * @return A {@link org.elasticsearch.action.get.GetRequestBuilder} wit the index, type and id set to the provided values.
     */
    protected GetRequestBuilder prepareGet(String index, String type, String id) {
        return client.prepareGet(index, type, id);
    }

    /**
     * Prepare an update using the provided index, type and id.
     * @param index    The index name.
     * @param type     The type name.
     * @param id       The id.
     * @return A {@link org.elasticsearch.action.update.UpdateRequestBuilder} with the index, type and id set to the provided values.
     */
    protected UpdateRequestBuilder prepareUpdate(String index, String type, String id) {
        return client.prepareUpdate(index, type, id);
    }

    /**
     * Prepare a delete using the provided index, type and id.
     * @param index    The index name.
     * @param type     The type name.
     * @param id       The id.
     * @return A {@link org.elasticsearch.action.delete.DeleteRequestBuilder} with the index, type and id set to the provided values.
     */
    protected DeleteRequestBuilder prepareDelete(String index, String type, String id) {
        return client.prepareDelete(index, type, id);
    }

    protected byte[] toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsBytes(object);
    }

    protected <T> Object toObject(String string, Class<?> target) throws IOException, ClassNotFoundException {
        return mapper.readValue(string, Class.forName(target.getName()));
    }


}
