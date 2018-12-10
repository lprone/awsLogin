package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author lprone
 *
 */
public class ProgramProperties {
	private String keyID;
	private String secretKey;
	private String mfaArn;
	private final String propertiesPath;
	private String credentialsPath;

	/**
	 * 
	 * @param propertiesPath
	 */
	public ProgramProperties(String propertiesPath) {
		this.propertiesPath = propertiesPath;
	}

	public void readProperties() throws Exception {
		Properties prop = new Properties();
		InputStream input;

		input = new FileInputStream(propertiesPath);

		prop.load(input);

		keyID = prop.getProperty("aws_access_key_id");
		secretKey = prop.getProperty("aws_secret_access_key");
		mfaArn = prop.getProperty("mfa_serial");
		credentialsPath = prop.getProperty("credentials_path");
		input.close();

		if (keyID.isEmpty() || secretKey.isEmpty() || mfaArn.isEmpty() || credentialsPath.isEmpty()) {
			throw new Exception("Incomplete properties!!");
		}
	}

	public String getKeyID() {
		return keyID;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public String getMfaArn() {
		return mfaArn;
	}

	public String getPropertiesPath() {
		return propertiesPath;
	}

	public String getCredentialsPath() {
		return credentialsPath;
	}

	@Override
	public String toString() {
		return this.keyID + " " + this.secretKey + " " + this.mfaArn + " " + this.propertiesPath + " "
				+ this.credentialsPath;
	}
}
