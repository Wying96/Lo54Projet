/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;


import fr.utbm.tools.HibernateUtil;
import java.io.Serializable;  
import java.lang.reflect.ParameterizedType;  
import java.util.List;  
  
import javax.annotation.Resource;  
import org.hibernate.HibernateException;
  
import org.hibernate.Query;  
import org.hibernate.Session;  
import org.hibernate.SessionFactory; 
/**
 *
 * @author wuying
 * @param <T>
 */

public class BaseDaoImp<T> implements BaseDao <T> {
    private final Class<T> clazz;  
  /** 
     * 向DAO层注入SessionFactory 
     */  
    @Resource  
    private final SessionFactory sessionFactory;  
    
    /** 
     * 通过构造方法指定DAO的具体实现类 
     */  
    public BaseDaoImp() {  
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();  
        clazz = (Class<T>) type.getActualTypeArguments()[0];  
        System.out.println("DAO的真实实现类是：" + this.clazz.getName());  
        sessionFactory = HibernateUtil.getSessionFactory();
    }  
  
  
    /** 
     * 获取当前工作的Session 
     */  
    protected Session getSession() {  
        return this.sessionFactory.openSession();  
    }  
  
    @Override
    public void save(T entity) {
        Session session = this.getSession();
        try{
            session.beginTransaction();
            session.save(entity); // do something
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction()!=null){
                try{
                    session.getTransaction().rollback();
                    session.close();
                }catch(HibernateException heRollback){
                    heRollback.printStackTrace();
                }
            }
        }finally{
            session.getTransaction().commit();
            session.close();
        }
    }  
  
    public void update(T entity) {  
        
        Session session = this.getSession();
        try{
            session.beginTransaction();
            session.update(entity); // do something
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction()!=null){
                try{
                    session.getTransaction().rollback();
                    session.close();
                }catch(HibernateException heRollback){
                    heRollback.printStackTrace();
                }
            }
        }finally{
            session.getTransaction().commit();
            session.close();
        }  
    }  
  
    public void delete(Serializable id) {  
        Session session = this.getSession();
        try{
            session.beginTransaction();
            T t= this.findById(id);
            session.delete(t); // do something
        }catch(HibernateException he){
            he.printStackTrace();
            if(session.getTransaction()!=null){
                try{
                    session.getTransaction().rollback();
                    session.close();
                }catch(HibernateException heRollback){
                    heRollback.printStackTrace();
                }
            }
        }finally{
            session.getTransaction().commit();
            session.close();
        }  
    }  
  
    /**
     *
     * @param id
     * @return
     */
    @Override
    public T findById(Serializable id) {  
        return (T) this.getSession().get(this.clazz, id);  
    }  
  
    @Override
    public List<T> findAll() {
//        int index = this.clazz.toString().split(".").length;
        String cla = this.clazz.toString();
        String hql = "from "+ cla.substring(cla.lastIndexOf(".")+1);
        return this.findByHQL(hql);
    }  
    
    @Override
    public List<T> findByHQL(String hql, Object... params) {  
        Query query = this.getSession().createQuery(hql);  
        for (int i = 0; params != null && i < params.length; i++) {  
            query.setParameter(i, params);  
        } 
        
        return query.list();  
    }  

//    public List<T> findByHQL(String hql) {  
//        Query query = this.getSession().createQuery(hql);  
//        return query.list(); 
//    }    
}
