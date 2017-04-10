package org.sub.bug.api;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import org.sub.bug.BugCRUDService;
import org.sub.bug.entity.Bug;


public class BugCRUDServiceImpl implements BugCRUDService {

    private static final String COLLECTION = "bugs";

    private final MongoClient client;

    public BugCRUDServiceImpl(Vertx vertx, JsonObject config) {
        this.client = MongoClient.createNonShared(vertx,
                config.getJsonObject("mongo",
                        new JsonObject().put("host", "127.0.0.1")
                                .put("port", 27017)));
    }


    @Override
    public void saveBug(Bug bug, Handler<AsyncResult<Void>> resultHandler) {
        final JsonObject bugObj = new JsonObject();
        Bug.toJson(bug, bugObj);
        client.save(COLLECTION, bugObj,
                ar -> {
                    if (ar.succeeded()) {
                        resultHandler.handle(Future.succeededFuture());
                    } else {
                        resultHandler.handle(Future.failedFuture(ar.cause()));
                    }
                }
        );
    }

    @Override
    public void retrieveBug(String bugId, Handler<AsyncResult<Bug>> resultHandler) {
        client.findOne(COLLECTION, new JsonObject().put("bug_id", bugId), null, ar -> {
            if (ar.succeeded()) {
                if (ar.result() == null) {
                    resultHandler.handle(Future.succeededFuture());
                } else {
                    resultHandler.handle(Future.succeededFuture(new Bug(ar.result())));
                }
            } else {
                resultHandler.handle(Future.failedFuture(ar.cause()));
            }
        });
    }

    @Override
    public void removeBug(String bugId, Handler<AsyncResult<Void>> resultHandler) {
        JsonObject query = new JsonObject().put("bug_id", bugId);
        client.removeDocument(COLLECTION, query, ar -> {
            if (ar.succeeded()) {
                resultHandler.handle(Future.succeededFuture());
            } else {
                resultHandler.handle(Future.failedFuture(ar.cause()));
            }
        });
    }
}
