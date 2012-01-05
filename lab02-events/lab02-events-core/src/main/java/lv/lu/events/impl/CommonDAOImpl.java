package lv.lu.events.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import lv.lu.events.interfaces.CommonDAO;

import lv.lu.events.interfaces.PersistentEntity;
import lv.lu.events.model.PersistentEntityType;

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
		for (PersistentEntity object: objects){
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
	
	public void cleanupDB(){
		// delete everything from USER table
		List<? extends PersistentEntity> objects = findAll(PersistentEntityType.USER.getObjectClass());
		for (PersistentEntity o: objects){
			delete(o);
		}
		
		// TODO [task]: all domain model objects have to be deleted from database
		// Your task is to make all domain model objects persistent entities.
		// Do not forget to adjust this method to cleanup tables for all persistent entities.
		// [Hint]: try to make the following code work:
//		for (PersistentEntityType clazz: PersistentEntityType.values()){
//			List<? extends PersistentEntity> objects = findAll(clazz.getObjectClass());
//			for (PersistentEntity o: objects){
//				delete(o);
//			}
//		}
		System.out.println("Database is cleaned");
	}
}
