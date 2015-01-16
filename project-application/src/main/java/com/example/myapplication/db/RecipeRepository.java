package com.example.myapplication.db;

import com.example.myapplication.api.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.lifecycle.Managed;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by sakhtar on 24/12/2014.
 */
public class RecipeRepository extends EsRepository implements Managed {

    final static Logger logger = LoggerFactory.getLogger(RecipeRepository.class);
    private static final String INDEX_NAME = "cookbook";
    private static final String TYPE_NAME = "recipe";

    public RecipeRepository(Client client, ObjectMapper mapper) {
        super(client, mapper);
    }

    public void start() throws Exception {
        // No need to wrap this in a command; failure will simply prevent startup of the application.
        if (!getClient().admin().indices().exists(Requests.indicesExistsRequest(INDEX_NAME)).actionGet().isExists()) {
            getClient().admin().indices().prepareCreate(INDEX_NAME).execute().actionGet().isAcknowledged();
        }

        //Create mapping for the key. Leave it not_analyzed so it doesn't tokenize the "-" delimiters
        PutMappingResponse response = getClient().admin().indices()
                .preparePutMapping(INDEX_NAME)
                .setType(TYPE_NAME)
                .setSource(buildMapping())
                .execute().actionGet();
        if (!response.isAcknowledged()) {
            throw new Exception("Could not define mapping.");
        }
    }

    public void stop() throws Exception {

    }

    private static XContentBuilder buildMapping() throws Exception {
        return jsonBuilder().prettyPrint()
                .startObject()
                .startObject(TYPE_NAME)
                //.startObject("_id").field("path", "my_id").endObject()
                .startObject("properties")
                .startObject("created").field("type", "date").field("format", "date").endObject()
                .endObject()
                .endObject()
                .endObject();
    }

    public List<Recipe> getRecipes() {
        SearchResponse response = getClient().prepareSearch().setIndices(INDEX_NAME).setTypes(TYPE_NAME).execute().actionGet();
        SearchHits hits = response.getHits();
        List<Recipe> recipes = new ArrayList<Recipe>();
        for(SearchHit hit : hits){
            try {
                Recipe recipe = (Recipe) toObject(hit.getSourceAsString(), Recipe.class);
                recipe.setId(hit.getId());
                recipes.add(recipe);
            } catch (Exception ex) {
                logger.error("Error getting recipes: {}", ex.getMessage());
            }
        }
        return recipes;
    }

    public void saveRecipe(Recipe recipe){
        try {
            IndexResponse response = prepareIndex(INDEX_NAME, TYPE_NAME).setSource(toJson(recipe)).execute().actionGet();
        } catch (JsonProcessingException e) {
            logger.error("Error converting object to json: {}", e.getMessage());
        }
    }

    public Recipe getRecipe(String id){
        GetResponse response = prepareGet(INDEX_NAME, TYPE_NAME, id).execute().actionGet();
        if (response.isExists()) {
            try {
                return  (Recipe) toObject(response.getSourceAsString(), Recipe.class);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void updateRecipe(String id, Recipe recipe){
        try {
            prepareUpdate(INDEX_NAME, TYPE_NAME, id).setDoc(toJson(recipe)).execute().actionGet();
        } catch (JsonProcessingException e) {
            logger.error("Error converting object to json: {}", e.getMessage());
        }
    }

    public void deleteRecipe(String id){
        prepareDelete(INDEX_NAME, TYPE_NAME, id).execute().actionGet();
    }

}
