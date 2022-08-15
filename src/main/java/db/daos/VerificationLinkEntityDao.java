package db.daos;

import db.entities.VerificationLinkEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class VerificationLinkEntityDao {

    @Transactional
    public VerificationLinkEntity create(String username) {
        VerificationLinkEntity entity = new VerificationLinkEntity();
        entity.key = String.valueOf(username.hashCode());
        entity.persist();
        return entity;
    }

    public VerificationLinkEntity findByKey(String code) {
        return VerificationLinkEntity.findByKey(code);
    }

    public List<VerificationLinkEntity> listAll() {
        return VerificationLinkEntity.listAll();
    }

    @Transactional
    public void delete(int id) {
        VerificationLinkEntity entity = VerificationLinkEntity.findById(id);
        if (entity != null) entity.delete();
    }
}
