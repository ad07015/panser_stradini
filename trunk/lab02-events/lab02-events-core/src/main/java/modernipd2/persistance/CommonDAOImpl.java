package modernipd2.persistance;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modernipd2.model.PersistentEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Common DAO implementation.
 * For JavaDocs see CommonDAO interface.
 */

@Transactional
public class CommonDAOImpl implements CommonDAO {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void save(PersistentEntity object) {
        if (object.getId() == null) {
            getEntityManager().persist(object);
        } else {
            getEntityManager().merge(object);
        }
    }

    public void saveAll(Collection<? extends PersistentEntity> objects) {
        for (PersistentEntity object : objects) {
            save(object);
        }
    }

    public <T extends PersistentEntity> T getById(Class<T> clazz, Long id) {
        return getEntityManager().find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public <T extends PersistentEntity> List<T> findAll(Class<T> clazz) {
        String entity = clazz.getSimpleName();
        return getEntityManager().createQuery("select e FROM " + entity + " e").getResultList();
    }

    public void delete(PersistentEntity object) {
        getEntityManager().remove(object);
    }
}
