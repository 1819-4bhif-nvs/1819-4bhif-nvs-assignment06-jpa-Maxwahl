package at.htl.LibraryJPAST;

import org.junit.Before;
import org.junit.Test;

import javax.json.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LibraryTests {
    private Client client;
    private WebTarget tut;

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.tut = client.target("http://localhost:8080/Library/api/");
    }
    @Test
    public void t01_deleteCustomer(){
        Response response = tut.path("persons/2").request().delete();
        assertThat(response.getStatus(),is(204));
    }
    @Test
    public void t02_getClassics(){
        Response response= tut.path("items").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));


        JsonArrayBuilder exBuilder = Json.createArrayBuilder();
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("genre","classic");
        obj.add("id",1);
        obj.add("name","test");
        obj.add("price",9.11);
        obj.add("composer","mozart");
        obj.add("runtime",123.1);
        exBuilder.add(obj);
        JsonArray expected = exBuilder.build();
        JsonArray act = response.readEntity(JsonArray.class);

        assertThat(act, equalTo(expected));

    }
    @Test
    public void t03_getUnfinishedLoans(){
        Response response= tut.path("loans/").request(MediaType.APPLICATION_JSON_TYPE).get();
        assertThat(response.getStatus(),is(200));
        JsonArray payload = response.readEntity(JsonArray.class);

        assertThat(payload.size(),is(1));
    }
}
