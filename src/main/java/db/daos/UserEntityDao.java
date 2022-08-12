package db.daos;

import db.entities.UserEntity;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.enterprise.context.ApplicationScoped;
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
    public UserEntity create(String username, String password, String role) {
        UserEntity entity = new UserEntity();
        entity.username = username;
        entity.password = BcryptUtil.bcryptHash(password);
        entity.role = role;
        entity.persist();
        return entity;
    }

    public List<UserEntity> listAll() {
        return UserEntity.listAll();
    }

    public UserEntity get(int id) {
        return UserEntity.findById(id);
    }

    public List<UserEntity> findByUsername(String username) {
//        return UserEntity.listAll().fil;
        //TODO: Implement this function
        return null;
    }
}
