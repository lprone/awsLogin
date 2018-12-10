package data;

/**
 * 
 * @author lprone
 *
 */
public class AWSLoginData {
	private String keyID;
	private String secretKey;
	private String sessionToken;

	/**
	 * 
	 * @param keyID
	 * @param secretKey
	 * @param sessionToken
	 */
	public AWSLoginData(String keyID, String secretKey, String sessionToken) {
		this.keyID = keyID;
		this.secretKey = secretKey;
		this.sessionToken = sessionToken;
	}

	public AWSLoginData() {
	}

	public String getKeyID() {
		return keyID;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setKeyID(String keyID) {
		this.keyID = keyID;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	@Override
	public String toString() {
		return this.keyID + " " + this.secretKey + " " + this.sessionToken;
	}
}