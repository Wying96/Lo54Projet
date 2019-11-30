/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import fr.utbm.entity.InscrirSession;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author c
 */
@Repository(value = "inscrirSessionDao")
public class InscrirSessionDaoImp extends BaseDaoImp<InscrirSession> implements InscrirSessionDao  {
    public InscrirSession findByUserAndSession(int userId,int sessionId){
        Session session = this.getSession();
        String hql = "from InscrirSession ins "
                + "where ins.courseSessionId = :sessionId "
               + "and ins.userId= :uId";
       Query query = this.getSession().createQuery(hql);
       query.setInteger("sessionId", sessionId);
       query.setInteger("uId", userId);
       List<InscrirSession> hasSession = query.list();
       if(hasSession.isEmpty()){ session.close(); return null ;}
       else{ session.close();  return hasSession.get(0); }
       
    }



}

