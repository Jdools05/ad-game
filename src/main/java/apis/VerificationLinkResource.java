package apis;


import db.entities.UserEntity;
import db.entities.VerificationLinkEntity;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Tag(name = "Verification Link", description = "Endpoints for the verification links")
@Path("/api/verify")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VerificationLinkResource {

    @POST
    @Transactional
    public UserEntity verifyUser(@DefaultValue("-1") @QueryParam("key") String key) {
        VerificationLinkEntity entity = VerificationLinkEntity.findByKey(key);
        if (entity == null) {
            System.out.println("Incorrect verification code");
            return null;
        }
        UserEntity user = entity.userEntity;
        user.verified = true;
        entity.delete();
        return user;
    }
}
