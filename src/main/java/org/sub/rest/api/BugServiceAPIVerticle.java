package org.sub.rest.api;


import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.sub.rest.BugCRUDService;
import org.sub.rest.common.RestAPIVerticle;
import org.sub.rest.entity.Bug;

public class BugServiceAPIVerticle extends RestAPIVerticle {

    private static final String SERVICE_NAME = "bug-rest-api";

    private static final String API_SAVE = "/save";
    private static final String API_RETRIEVE = "/:bugId";
    private static final String API_CLOSE = "/:bugId";

    private final BugCRUDService service;

    public BugServiceAPIVerticle(BugCRUDService service) {
        this.service = service;
    }

    @Override
    public void start(Future<Void> future) throws Exception {
        super.start();

        Router router = Router.router(vertx);
        // body handler
        router.route().handler(BodyHandler.create());
        // API route handler
        router.post(API_SAVE).handler(this::apiSave);
        router.get(API_RETRIEVE).handler(this::apiRetrieve);
        router.delete(API_CLOSE).handler(this::apiClose);

        enableHeartbeatCheck(router, config());

        // get HTTP host and port from configuration, or use default value
        String host = config().getString("http.address", "0.0.0.0");
        int port = config().getInteger("http.port", 8080);

        createHttpServer(router, host, port)
                .compose(serverCreated -> publishHttpEndpoint(SERVICE_NAME, host, port))
                .setHandler(future.completer());
    }

    private void apiSave(RoutingContext context) {
        Bug bug = new Bug();
        Bug.fromJson(new JsonObject(context.getBodyAsString()), bug);
        if (bug.getBugId() == null) {
            badRequest(context, new IllegalStateException("Seller id does not exist"));
        } else {
            JsonObject result = new JsonObject().put("message", "store_saved")
                    .put("sellerId", bug.getBugId());
            service.saveBug(bug, resultVoidHandler(context, result));
        }
    }

    private void apiRetrieve(RoutingContext context) {
        String bugId = context.request().getParam("bugId");
        service.retrieveBug(bugId, resultHandlerNonEmpty(context));
    }

    private void apiClose(RoutingContext context) {
        String bugId = context.request().getParam("bugId");
        service.removeBug(bugId, deleteResultHandler(context));
    }
}
