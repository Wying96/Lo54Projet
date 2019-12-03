/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import java.io.Serializable;
import java.util.List;  
/**
 *
 * @author wuying
 * @param <T>
 */
public interface BaseDao<T> {
     public void save(T entity);  
  
    public void update(T entity);  
  
    public void delete(Serializable id);  
  
    public T findById(Serializable id);  
    
    public List<T> findAll();  

    public List<T> findByHQL(String hql, Object... params);  
}
