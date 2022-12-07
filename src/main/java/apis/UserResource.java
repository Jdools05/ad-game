package apis;

import db.daos.UserEntityDao;
import db.daos.VerificationLinkEntityDao;
import db.entities.UserEntity;
import db.entities.VerificationLinkEntity;
import io.quarkus.security.Authenticated;
import io.vertx.ext.auth.User;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.PermitAll;
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
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"user", "admin"})
public class UserResource {

    @Inject
    UserEntityDao userEntityDao;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.TEXT_PLAIN)
    public List<UserEntity> listAllUsers(@Context SecurityContext securityContext) {
        return userEntityDao.listAll();
    }

    @GET
    @Path("/me")
    public String getSelf(@Context SecurityContext securityContext) {
        return securityContext.getUserPrincipal().getName();
    }

    @GET
    @Path("/authenticate")
    public Response authenticateUser(@Context SecurityContext securityContext) {
        System.out.println("User: " + securityContext.getUserPrincipal().getName());
        return Response.ok().build();
    }

    @PUT
    @Path("/increment")
    @Transactional
    public Response incrementUserAdCount(@Context SecurityContext securityContext) {
        UserEntity entity = userEntityDao.findByUsername(securityContext.getUserPrincipal().getName());
        if (entity == null) {
            return Response.noContent().build();
        }
        entity.incrementAdCount();
        return Response.ok().build();
    }
}
