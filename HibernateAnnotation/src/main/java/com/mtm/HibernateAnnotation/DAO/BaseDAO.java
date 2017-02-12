package com.mtm.HibernateAnnotation.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;

import com.mtm.HibernateAnnotation.utils.HibernateUtil;

public abstract class  BaseDAO <T>{

	protected final Class<T> entityClass;
	private SessionFactory factory;

	public BaseDAO() {
		this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.factory=HibernateUtil.getSessionFactory();
	}
	public Session getSession(){
		return factory.openSession();
	}
	
	public String getEntity(){
		return entityClass.getSimpleName();
	}
	public int save(T t){
		Session s=  getSession();
    	s.beginTransaction();
    	int id=(Integer) s.save(t);
    	s.getTransaction().commit();
    	s.close();
    	return id;
	}
	
	public T getById(int id){
		Session s=getSession();
		T t=(T)s.get(entityClass, id);
		s.close();
		return t;
	}
	
	public void update(int id,T t){
		Session s=getSession();
		s.beginTransaction();
		s.saveOrUpdate(t);
		s.getTransaction().commit();
		s.close();
    	
	}
	
	public List<T> getAll(){
		Session s=getSession();
		List<T> t=s.createCriteria(entityClass).list();
		s.close();
		return t;
	}
	public int deleteAll(){
		Session s=getSession();
		s.beginTransaction();
		int num=s.createQuery("delete from "+getEntity()).executeUpdate();
		s.getTransaction().commit();
		s.close();
		return num;
	}
	public int delete(int id){
		Session s=getSession();
		s.beginTransaction();
		Query query=s.createQuery("delete from "+getEntity()+" where id=:id");
		query.setParameter("id", id);
		int num=query.executeUpdate();
		s.getTransaction().commit();
		s.close();
		return num;
	}

	
	
}
