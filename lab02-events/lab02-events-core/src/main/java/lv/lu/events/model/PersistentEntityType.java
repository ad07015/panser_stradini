package lv.lu.events.model;

import lv.lu.events.interfaces.PersistentEntity;

/**
 * This is a registry of persistent objects in a system.
 * It is used in:
 *  	- CommonDAOImpl.cleanupDB() to delete everything from DB.
 *  	- CommonDAOImplTest to create instances of persistent objects
 */
public enum PersistentEntityType 
{
    INVITE(Invite.class, null),
    @SuppressWarnings("unchecked")
    EVENT(Event.class, new Class[]{PublicEvent.class, PrivateEvent.class}),
	FRIENDSHIP(Friendship.class, null),
    USER(User.class, null);	
	
	PersistentEntityType(Class<? extends PersistentEntity> clazz, Class<? extends PersistentEntity>[] subClasses){
		this.clazz = clazz;
		this.subClasses = subClasses;
	}
	
	private Class<? extends PersistentEntity> clazz;
	
	public Class<? extends PersistentEntity> getObjectClass(){
		return this.clazz;
	}
	
	private Class<? extends PersistentEntity>[] subClasses;
	
	public Class<? extends PersistentEntity>[] getSubClasses() {
		return subClasses;
	}
}
