package org.sub.bug;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;

import io.vertx.serviceproxy.ProxyHelper;
import org.sub.bug.api.BugCRUDServiceImpl;
import org.sub.bug.api.BugServiceAPIVerticle;
import org.sub.rest.common.BaseMicroserviceVerticle;

import static org.sub.bug.BugCRUDService.SERVICE_ADDRESS;
import static org.sub.bug.BugCRUDService.SERVICE_NAME;

public class BugVerticle extends BaseMicroserviceVerticle {

    private BugCRUDService crudService;

    @Override
    public void start(Future<Void> future) throws Exception {
        super.start();

        crudService = new BugCRUDServiceImpl(vertx, config());
        ProxyHelper.registerService(BugCRUDService.class, vertx, crudService, SERVICE_ADDRESS);
        // publish service and deploy REST verticle
        publishEventBusService(SERVICE_NAME, SERVICE_ADDRESS, BugCRUDService.class)
                .compose(servicePublished -> deployRestVerticle(crudService))
                .setHandler(future.completer());
    }

    private Future<Void> deployRestVerticle(BugCRUDService service) {
        Future<String> future = Future.future();
        vertx.deployVerticle(new BugServiceAPIVerticle(service),
                new DeploymentOptions().setConfig(config()),
                future.completer());
        return future.map(r -> null);
    }
}
