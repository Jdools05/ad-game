import db.daos.UserEntityDao;
import db.entities.UserEntity;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class Startup {

    @Inject
    UserEntityDao userEntityDao;

    @Transactional
    public void loadUsers(@Observes StartupEvent event) {
//        UserEntity.deleteAll();
//        userEntityDao.create("admin", "admin", "admin");
//        userEntityDao.create("user", "user", "user");
    }
}
