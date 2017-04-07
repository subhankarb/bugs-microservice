package org.sub.rest;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;

public class RestServer extends AbstractVerticle {


    @Override
    public void start(Future<Void> fut) throws Exception {
        super.start();

        MongoClient mongoClient = MongoClient.createShared(vertx, new JsonObject(), "app");
        BugsDao.insertFixureData(mongoClient, config().getString("fixture-data"));

        Router router = Router.router(vertx);

        router.get("/ping").handler(ctx -> {
            ctx.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end("{\"ping\":\"pong\"}");
        });
        router.get("/api/bugs").handler(ctx -> BugsDao.getAllBugs(ctx, mongoClient));
        router.get("/api/bugs/:id").handler(ctx -> BugsDao.getBugById(ctx, mongoClient));
        router.post("/api/bugs").handler(ctx -> BugsDao.addOne(ctx, mongoClient));
        router.put("/api/bugs/:id").handler(ctx -> BugsDao.updateOne(ctx, mongoClient));
        router.delete("/api/bugs/:id").handler(ctx -> BugsDao.deleteOne(ctx, mongoClient));

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        });

    }
}
