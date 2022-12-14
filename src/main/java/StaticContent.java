import io.quarkus.vertx.web.Route;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.FileSystemAccess;
import io.vertx.ext.web.handler.StaticHandler;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StaticContent {

    @Route(path = "/home", methods = Route.HttpMethod.GET)
    void indexContent(RoutingContext rc) {
        StaticHandler.create(FileSystemAccess.RELATIVE, "src/main/java/frontend/index.html").handle(rc);
    }
}
