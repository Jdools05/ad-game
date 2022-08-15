package apis;

import db.daos.UserEntityDao;
import db.daos.VerificationLinkEntityDao;
import db.entities.UserEntity;
import db.entities.VerificationLinkEntity;
import io.vertx.ext.auth.User;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Tag(name = "Users", description = "Endpoints for the user accounts")
@Path("/api/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserEntityDao userEntityDao;

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.TEXT_PLAIN)
    public List<UserEntity> listAllUsers() {
        return userEntityDao.listAll();
    }

    @GET
    @RolesAllowed("user")
    @Path("/me")
    public String getSelf(@Context SecurityContext securityContext) {
        return securityContext.getUserPrincipal().getName();
    }

    @PUT
    @RolesAllowed("user")
    @Path("/increment")
    @Transactional
    public Response incrementUserAdCount(@Context SecurityContext securityContext) {
        List<UserEntity> entities = userEntityDao.findByUsername(securityContext.getUserPrincipal().getName());
        UserEntity entity = entities.isEmpty() ? null : entities.get(0);
        if (entity == null) {
            return Response.noContent().build();
        }
        entity.incrementAdCount();
        return Response.ok().build();
    }
}
