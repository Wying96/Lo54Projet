/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import fr.utbm.entity.CourseSession;
import fr.utbm.entity.Users;

/**
 *
 * @author wuying
 */
public interface UsersDao extends BaseDao<Users> {
    
//   public boolean checkEmailExistence(String eamil); 
   public Users findById(Integer id);
   public Users findByEmail(String email);
//   public boolean checkLogin(Users u);
   public void inscrirSession(int uId,CourseSession cs);
   
    
}
