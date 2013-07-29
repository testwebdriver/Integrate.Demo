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

	
	public static String reporIssue(String sumario, String descricao, String categoria, String informacaoAdicional, String evidencia, String nomeArquivo) {
	
		IMCSession sessao = null;
		String arquivo = nomeArquivo + ".png";
		String bugID= null;
		try {
			
			// efetua a conexao com o Mantis atraves do Singleton
			sessao = ConnectMantis.getSessao();
			
			// objeto que representa um projeto no Mantis
			IProject projeto = sessao.getProject(PROJET);
			
			// objeto que representa uma issue (bug) no Mantis
            Issue issue = new Issue();
            
            issue.setProject(new MCAttribute(projeto.getId(), projeto.getName()));
            issue.setAdditionalInformation(null);
            issue.setOs(System.getProperty("os.name"));
            issue.setOsBuild(System.getProperty("os.version"));
            issue.setPlatform(System.getProperty("os.arch"));
            issue.setSeverity(new MCAttribute(70, "crash"));
            issue.setReproducibility(new MCAttribute(10, "always"));
            
            // abaixo o sumario sera apresentado com a data. Remova o sumario em execução fora de testes
            issue.setSummary(sumario + new Date());
            issue.setDescription(descricao);
            issue.setCategory(categoria);
            issue.setPriority(new MCAttribute(40, "high"));
            issue.setAdditionalInformation(informacaoAdicional);
            
            // submete o bug no Mantis
            long id = sessao.addIssue(issue);     
            sessao.addIssueAttachment(id, arquivo, "image/png", Base64.decodeBase64(evidencia));
            bugID = String.valueOf (id); 
		} catch (MalformedURLException e) {
			System.err.println("Erro n0 URL access to Mantis");
			e.printStackTrace();
		} catch (MCException e) {
			System.err.println("Erro no connection to mantis");
			e.printStackTrace();
		}
		
		return bugID;
	}
}
