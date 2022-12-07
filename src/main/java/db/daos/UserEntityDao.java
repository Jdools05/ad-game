package db.daos;

import db.entities.UserEntity;
import db.entities.VerificationLinkEntity;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.vertx.ext.auth.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserEntityDao {

    /**
     * Adds a new user in the database
     * @param username the username
     * @param password the unencrypted password (it will be encrypted with bcrypt)
     * @param role the comma-separated roles
     */
    @Transactional
    public UserEntity create(String username, String password, String email, String role) {
        UserEntity entity = new UserEntity();
        entity.username = username;
        entity.password = BcryptUtil.bcryptHash(password);
        entity.email = email;
        entity.role = role;
        VerificationLinkEntity verificationLinkEntity = new VerificationLinkEntity();
        verificationLinkEntity.key = BcryptUtil.bcryptHash(username);
        verificationLinkEntity.userEntity = entity;
        verificationLinkEntity.persist();
        entity.persist();

        // TODO: send email here

        return entity;
    }

    public List<UserEntity> listAll() {
        return UserEntity.listAll();
    }

    public UserEntity get(int id) {
        return UserEntity.findById(id);
    }

    public UserEntity findByUsername(String username) {
        return UserEntity.find("username", username).firstResult();
    }
}
