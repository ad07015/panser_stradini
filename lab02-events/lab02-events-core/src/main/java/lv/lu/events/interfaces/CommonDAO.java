package lv.lu.events.interfaces;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;


import lv.lu.events.model.PersistentEntityType;

/**
 * Interface for general operations with persistent objects.
 * @see PersistentEntityType for enumeration of persistent entities.
 */
public interface CommonDAO {
	
	/**
	 * Saves object in a database.
	 * @param object to be saved
	 */
	public void save(PersistentEntity object);
	
	/**
	 * Saves all list elements in a database.
	 * @param objects - collection of objects to be saved
	 */
	public void saveAll(Collection<? extends PersistentEntity> objects);
	
	/**
	 * Retrieves object of certain type from database by id.
	 * @param clazz - persistent object class
	 * @param id - id of instance to be retrieved
	 * @return instance from database
	 */
	public <T extends PersistentEntity> T getById(Class<T> clazz, Long id);
	
	/**
	 * Returns all objects of certain type from database.
	 * @param clazz - persistent object class
	 * @return list of instances from database
	 */
	public <T extends PersistentEntity> List<T> findAll(Class<T> clazz);
	
	/**
	 * Deletes object from database.
	 * @param object to be deleted
	 */
	public void delete(PersistentEntity object);
	
	/**
	 * Deletes all objects from database.
	 */
	public void cleanupDB();
	
	/**
	 * Returns underlying EntityManager for using it directly.
	 */
	public EntityManager getEntityManager();
}