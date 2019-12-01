/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.repository;

import fr.utbm.entity.InscrirSession;

/**
 *
 * @author c
 */
public interface InscrirSessionDao extends BaseDao<InscrirSession> {
    //TODO: getById for this table is not correct
    public InscrirSession findByUserAndSession(int userId,int sessionId);
}
