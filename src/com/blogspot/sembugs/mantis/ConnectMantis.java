package com.blogspot.sembugs.mantis;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mantisbt.connect.IMCSession;
import org.mantisbt.connect.MCException;
import org.mantisbt.connect.axis.MCSession;

import com.blogspot.sembugs.util.IConstantes;

public class ConnectMantis implements IConstantes {

    private static ConnectMantis instance = null;
    private static IMCSession session = null;

   
    public ConnectMantis() throws MalformedURLException, MCException {
    	
        URL url = new URL(MANTIS_URL);
        session = new MCSession(url, MANTIS_USER, MANTIS_PWD);
       
    }

    
    public static ConnectMantis getInstance() {
        if (instance == null) {
            try {
            	 instance = new ConnectMantis();
            } catch (MalformedURLException ex) {
                Logger.getLogger(ConnectMantis.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MCException ex) {
                Logger.getLogger(ConnectMantis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

    
    public static IMCSession getSession() throws MalformedURLException, MCException {
        if (session == null) {
            getInstance();
        }
        return session;
    }	
}
