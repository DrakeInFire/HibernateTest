package com.mtm.HibernateAnnotation;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.mtm.HibernateAnnotation.DAO.StudentDAO;
import com.mtm.HibernateAnnotation.model.Student;
import com.mtm.HibernateAnnotation.utils.HibernateUtil;



/**
 * Hello world!
 *
 */
public class App 
{
	 public static void main( String[] args )
	    {
	        App app=new App();
//	        
	        StudentDAO stDao=new StudentDAO();
	        stDao.save(new Student("Mario","Temprano","Programador"));
	        stDao.save(new Student("Diana","Gomez","Estudiante")); 
	        
	        List<Student> students=stDao.getAll();
	        
	        students=stDao.getAll();
	        System.out.println("ANTES");
	        for(Student std:students){
	        	System.out.println(std.toString());
	        	stDao.delete(std.getId());
	        }
//	        System.out.println("Eliminados->"+stDao.deleteAll());
//	        
	        System.out.println("DESPUES");
	        students=stDao.getAll();
	        for(Student std:students){
	        	System.out.println(std.toString());
	        }
	        
	        
	        HibernateUtil.close();
	        
	        
	    }
}
