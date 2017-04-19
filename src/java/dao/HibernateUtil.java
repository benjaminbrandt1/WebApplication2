/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Ben
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final String TUNNEL_IN = "jdbc:mysql://localhost:3307/sp17_3308_tue94788?user=tue94788&password=egierein&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
    private static final String TEMPLE_URL = "jdbc:mysql://cis-linux2.temple.edu:3306/sp17_3308_tue94788?user=tue94788&password=egierein&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
                
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            AnnotationConfiguration conf = new AnnotationConfiguration().configure();
            if(isTemple()){
                conf.setProperty("hibernate.connection.url", TEMPLE_URL);
            } else {
                conf.setProperty("hibernate.connection.url", TUNNEL_IN);
            }
            sessionFactory = conf.buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    private static boolean isTemple() {
        boolean temple = false;
        try {
            String hostName = java.net.InetAddress.getLocalHost().getCanonicalHostName();
            hostName = hostName.toLowerCase();
            if (hostName.endsWith("temple.edu")) {
                temple = true;
                //System.out.println("************* Running from Temple, so using cis-linux2 for db connection");
            } else {
                //System.out.println("************* Not running from Temple, so using local for db connection");
            }
        } catch (Exception e) {
            System.out.println("Unable to get hostname: " + e.getMessage());
        }
        return temple;
    }
}
