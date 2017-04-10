package org.sub.bug;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import org.sub.bug.entity.Bug;

@VertxGen
@ProxyGen
public interface BugCRUDService {

    String SERVICE_NAME = "bug-eb-service";

    String SERVICE_ADDRESS = "service.bug";

    void saveBug(Bug bug, Handler<AsyncResult<Void>> resultHandler);

    void retrieveBug(String bugId, Handler<AsyncResult<Bug>> resultHandler);

    void removeBug(String bugId, Handler<AsyncResult<Void>> resultHandler);
}
