/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import dao.HibernateUtil;
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
}
