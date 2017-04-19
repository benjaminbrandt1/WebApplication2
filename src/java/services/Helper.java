/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.HibernateDemo;
import dao.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ben
 */
public class Helper {

    Session session = null;

    public Helper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

     public List<HibernateDemo> getAllUsers() {
        List<HibernateDemo> userList = null;
        org.hibernate.Transaction tx = null;
        
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM HibernateDemo");
            userList = (List<HibernateDemo>) q.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
             tx.rollback();
             tx.commit();
             throw e;
           }
            userList = new ArrayList();
            userList.add(new HibernateDemo("Cannot get connection to database."));
            
        } 
        return userList;
    }

    public HibernateDemo getUserByEmail(String email) {
        HibernateDemo user = null;
        org.hibernate.Transaction tx = null;
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM HibernateDemo AS user WHERE user.email = ?");
            q.setString(0, email);
            user = (HibernateDemo) q.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
        return user;
    }

    public HibernateDemo getUserById(int userId) {
        HibernateDemo user = null;
        org.hibernate.Transaction tx = null;
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM HibernateDemo AS user WHERE user.userId = ?");
            q.setInteger(0, userId);
            user = (HibernateDemo) q.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
        return user;
    }

    public void insert(HibernateDemo user) {
        org.hibernate.Transaction tx = null;
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void delete(int userId) {
        org.hibernate.Transaction tx = null;
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            tx = session.beginTransaction();
            HibernateDemo user = (HibernateDemo) session.load(HibernateDemo.class, new Integer(userId));
            if (null != user) {
                session.delete(user);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void update(HibernateDemo user) {
        org.hibernate.Transaction tx = null;
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Integer userId = user.getUserId();
            HibernateDemo foundUser = (HibernateDemo) session.load(HibernateDemo.class, userId);
            if (null != foundUser) {
                foundUser.setFirstName(user.getFirstName());
                foundUser.setLastName(user.getLastName());
                foundUser.setPswd(user.getPswd());
                foundUser.setEmail(user.getEmail());
                foundUser.setCountry(user.getCountry());
                session.update(foundUser);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
    }
}
