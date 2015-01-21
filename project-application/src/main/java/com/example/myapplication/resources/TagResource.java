package com.example.myapplication.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.myapplication.api.Tag;
import com.example.myapplication.db.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by sakhtar on 19/01/2015.
 */
@Path("/tag")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TagResource {

    private final TagRepository tagRepository;
    final static Logger logger = LoggerFactory.getLogger(TagResource.class);

    public TagResource(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @GET
    @Timed
    public List<Tag> getTags() {
        return tagRepository.getTags();
    }

    @POST
    public void saveTag(@Valid Tag tag){
        tagRepository.saveTag(tag);
    }

    @GET
    @Timed
    @Path("/{id}")
    public Tag getTag(@PathParam("id") String id){
        return tagRepository.getTag(id);
    }

    @PUT
    @Path("/{id}")
    public void updateTag(@PathParam("id") String id, @Valid Tag tag){
        tagRepository.updateTag(id, tag);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTag(@PathParam("id") String id){
        tagRepository.deleteTag(id);
    }
}
