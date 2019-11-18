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
 */
public interface BaseDao<T> {
     public void save(T entity);  
  
    public void update(T entity);  
  
    public void delete(Serializable id);  
  
    public T findById(Serializable id);  
    
    public List<T> findAll();  
//  TODO: 设置成如下的传参数方式 hql, Object... params 用来处理所有类型string/int/bool。。。
    public List<T> findByHQL(String hql, Object... params);  
}
