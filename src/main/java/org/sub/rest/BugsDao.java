package org.sub.rest;


import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.RoutingContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class BugsDao {

    private static final String BUG_COLLECTION = "app.bugs";

    public static void addOne(RoutingContext routingContext, MongoClient client){
        final JsonObject bug = new JsonObject(routingContext.getBodyAsString());
        client.save(BUG_COLLECTION, bug, res -> {
            if (res.succeeded()){
                routingContext.response()
                        .setStatusCode(201)
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(new JsonObject("{\"status\":\"success\"}")));
            } else {
                routingContext.response()
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .setStatusCode(500)
                        .end(Json.encodePrettily(new JsonObject("{\"status\":\"failed\"}")));
            }
        });
    }

    static void getAllBugs(RoutingContext routingContext,  MongoClient client) {
        client.find(BUG_COLLECTION, new JsonObject(), results -> {
            if (results.succeeded()){
                List<JsonObject> bugs = results.result();
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(new JsonObject().put("data", bugs)));
            }
            else {
                routingContext.response()
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .setStatusCode(500)
                        .end(Json.encodePrettily(new JsonObject("{\"status\":\"failed\"}")));
            }
        });
    }

    static void getBugById(RoutingContext routingContext, MongoClient client){
        String id = routingContext.request().getParam("id");
        client.find(BUG_COLLECTION, new JsonObject().put("bug_id", id), results -> {
            if (results.succeeded()){
                List<JsonObject> bugs = results.result();
                JsonObject response = new JsonObject();
                response.put("data", bugs);
                routingContext.response()
                        .setStatusCode(200)
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(response));
            }
            else {
                routingContext.response()
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .setStatusCode(500)
                        .end(Json.encodePrettily(new JsonObject("{\"status\":\"failed\"}")));
            }
        });
    }

    public static void deleteOne(RoutingContext routingContext, MongoClient client) {
        String id = routingContext.request().getParam("id");
        if (id == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            client.removeDocument(BUG_COLLECTION, new JsonObject().put("bug_id", id),
                    ar -> routingContext.response().setStatusCode(204).end());
        }
    }

    public static void updateOne(RoutingContext routingContext, MongoClient client) {
        final String id = routingContext.request().getParam("id");
        JsonObject json = routingContext.getBodyAsJson();
        if (id == null || json == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            client.findOneAndUpdate(BUG_COLLECTION,
                    new JsonObject().put("bug_id", id), // Select a unique document
                    new JsonObject()
                            .put("$set", json),
                    v -> {
                        if (v.failed()) {
                            routingContext.response().setStatusCode(404).end();
                        } else {
                            routingContext.response()
                                    .putHeader("content-type", "application/json; charset=utf-8")
                                    .end(Json.encodePrettily(json.put("bug_id", id)));
                        }
                    });
        }
    }

    static void insertFixureData(MongoClient client, String fixerFile){
        client.count(BUG_COLLECTION, new JsonObject(), count -> {
            if (count.succeeded()) {
                if (count.result() == 0) {
                    try {
                        byte[] encoded = Files.readAllBytes(Paths.get(fixerFile));
                        JsonArray bugs = new JsonArray(new String(encoded));
                        for (Object bug : bugs.getList()){
                            client.insert(BUG_COLLECTION, new JsonObject((Map<String, Object>) bug), insert_response -> {
                                if (insert_response.succeeded()){
                                    System.out.println("Successfully inserted : " + new String(encoded));
                                } else {
                                    System.out.println("Failed to inserted : " + new String(encoded));
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
