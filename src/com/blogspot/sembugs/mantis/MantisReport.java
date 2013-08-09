package com.blogspot.sembugs.mantis;

import java.net.MalformedURLException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.mantisbt.connect.IMCSession;
import org.mantisbt.connect.MCException;
import org.mantisbt.connect.model.IProject;
import org.mantisbt.connect.model.Issue;
import org.mantisbt.connect.model.MCAttribute;

import com.blogspot.sembugs.util.IConstantes;

public class MantisReport implements IConstantes {

	
	public static String reporIssue(String summary, String descri, String categoria, String informAdi, String evidence, String nameArchive) {
	
		IMCSession sessions = null;
		String archive = nameArchive + ".png";
		String bugID= null;
		try {
			
			
			sessions = ConnectMantis.getSession();
			
			
			IProject projet = sessions.getProject(PROJET);
			
			
            Issue issue = new Issue();
            
            issue.setProject(new MCAttribute(projet.getId(), projet.getName()));
            issue.setAdditionalInformation(null);
            issue.setOs(System.getProperty("os.name"));
            issue.setOsBuild(System.getProperty("os.version"));
            issue.setPlatform(System.getProperty("os.arch"));
            issue.setSeverity(new MCAttribute(70, "crash"));
            issue.setReproducibility(new MCAttribute(10, "always"));
            
            
            issue.setSummary(summary + new Date());
            issue.setDescription(descri);
            issue.setCategory(categoria);
            issue.setPriority(new MCAttribute(40, "high"));
            issue.setAdditionalInformation(informAdi);
            
          
            long id = sessions.addIssue(issue);     
            sessions.addIssueAttachment(id, archive, "image/png", Base64.decodeBase64(evidence));
            bugID = String.valueOf (id); 
		} catch (MalformedURLException e) {
			System.err.println("Error no URL access to Mantis");
			e.printStackTrace();
		} catch (MCException e) {
			System.err.println("Error no connection to mantis");
			e.printStackTrace();
		}
		
		return bugID;
	}
}
