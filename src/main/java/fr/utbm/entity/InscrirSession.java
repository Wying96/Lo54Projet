/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author c
 */
@Entity
@Table(name = "INSCRIRSESSION")
public class InscrirSession {
    @Id
    @Column(name = "COURSE_SESSION_ID")
    int courseSessionId;
    @Id
    @Column(name = "USER_ID")
    int userId;

    public int getCourseSessionId() {
        return courseSessionId;
    }

    public void setCourseSessionId(int courseSessionId) {
        this.courseSessionId = courseSessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "InscrirSession{" + "courseSessionId=" + courseSessionId + ", userId=" + userId + '}';
    }
    
}
