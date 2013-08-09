package com.blogspot.sembugs.testlink;

import com.blogspot.sembugs.util.IConstantes;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIHelper;

public class ResultExe implements IConstantes {
	
    
    public static void reportTestCaseResult(String projetTest, String planTest, String caseTest, String nameBuild, String msg, String result, Integer bugID) throws TestLinkAPIException {
        
     
        TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEVKEY, URL);
        if(bugID>0){
        	
        	
        	 Integer projectID = TestLinkAPIHelper.getProjectID (testlinkAPIClient, PROJET_TESTLINK);  
        	 Integer testPlanID= TestLinkAPIHelper.getPlanID (testlinkAPIClient, projectID, PLAN);  
        	 Integer testCaseID= TestLinkAPIHelper.getTestCaseID (testlinkAPIClient, projectID, caseTest);  
        	 Integer BUILDID =TestLinkAPIHelper.getBuildID (testlinkAPIClient, testPlanID, nameBuild);  
        	    System.out.println(BUILDID);
        	 testlinkAPIClient.reportTestCaseResult (testPlanID, testCaseID, BUILDID, bugID, false, msg, result);          
        	 
        	
        }else{
        	
        
        testlinkAPIClient.reportTestCaseResult(projetTest, planTest, caseTest, nameBuild, msg, result);
        }
    }
}
