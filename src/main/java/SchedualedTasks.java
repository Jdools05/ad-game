import db.daos.UserEntityDao;
import db.daos.VerificationLinkEntityDao;
import db.entities.UserEntity;
import db.entities.VerificationLinkEntity;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class SchedualedTasks {

    @Inject
    VerificationLinkEntityDao verificationLinkEntityDao;

    @Inject
    UserEntityDao userEntityDao;

    Logger logger = Logger.getGlobal();

    @Transactional
    @Scheduled(cron = "59 59 23 ? * * *")
    public void deleteExpiredVerificationLinks() {
        List<VerificationLinkEntity> entityList = verificationLinkEntityDao.listAll();

        int deletedUserCount = 0;
        for (VerificationLinkEntity entity : entityList) {
            if (entity.getLifespanInDays() >= 7) {
                entity.userEntity.delete();
                entity.delete();
                deletedUserCount++;
            }
        }
        logger.info(String.format("Deleted %s unverified users.", deletedUserCount));
    }

    @Transactional
    @Scheduled(cron = "59 59 23 ? * * *")
    public void resetDailyAdCount() {
        List<UserEntity> entityList = userEntityDao.listAll();
        for (UserEntity entity : entityList) {
            entity.resetDailyAdCount();
        }
        logger.info("Reset daily ad count for all users.");
    }

    @Transactional
    @Scheduled(cron = "59 59 23 ? * SUN *")
    public void resetWeeklyAdCount() {
        List<UserEntity> entityList = userEntityDao.listAll();
        for (UserEntity entity : entityList) {
            entity.resetWeeklyAdCount();
        }
        logger.info("Reset weekly ad count for all users.");
    }


    @Transactional
    @Scheduled(cron = "59 59 23 L * ? *")
    public void resetMonthlyAdCount() {
        List<UserEntity> entityList = userEntityDao.listAll();
        for (UserEntity entity : entityList) {
            entity.resetMonthlyAdCount();
        }
        logger.info("Reset monthly ad count for all users.");
    }

    @Transactional
    @Scheduled(cron = "59 59 23 L DEC ? *")
    public void resetYearlyAdCount() {
        List<UserEntity> entityList = userEntityDao.listAll();
        for (UserEntity entity : entityList) {
            entity.resetYearlyAdCount();
        }
        logger.info("Reset yearly ad count for all users.");
    }

}
