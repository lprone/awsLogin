package utils;

import data.AWSLoginData;

import java.io.PrintWriter;

/**
 * 
 * @author lprone
 *
 */
public class Credentials {

	private final String credPath;
	private final AWSLoginData loginData;

	/**
	 * 
	 * @param credPath
	 * @param loginData
	 */
	public Credentials(String credPath, AWSLoginData loginData) {
		super();
		this.credPath = credPath;
		this.loginData = loginData;
	}

	public void writeCreds() throws Exception {
		if (loginData.getKeyID() == null || loginData.getSecretKey() == null) {
			throw new Exception("Null data!!");
		}
		PrintWriter writer = new PrintWriter(credPath, "UTF-8");
		writer.println("[default]");
		writer.println("aws_secret_access_key = " + loginData.getSecretKey());
		if (loginData.getSessionToken() != null) {
			writer.println("aws_session_token = " + loginData.getSessionToken());
		}
		writer.println("aws_access_key_id = " + loginData.getKeyID());
		writer.close();
	}

}
