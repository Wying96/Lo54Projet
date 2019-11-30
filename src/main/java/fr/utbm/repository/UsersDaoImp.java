/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import fr.utbm.entity.Users;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wuying
 */
@Repository(value = "usersDao")
public class UsersDaoImp extends BaseDaoImp<Users> implements UsersDao{
    
    
    
}
