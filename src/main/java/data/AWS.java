package data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * 
 * @author lprone
 *
 *
 */
public class AWS {

	private final String mfaURL;
	private final String code;
	private AWSLoginData loginData;

	/**
	 * 
	 * @param mfaURL
	 * @param code
	 */
	public AWS(String mfaURL, String code) {
		super();
		this.mfaURL = mfaURL;
		this.code = code;
		this.loginData = null;
	}

	public void Login() throws Exception {
		this.loginData = new AWSLoginData();

		final String cmd = "aws sts get-session-token --serial-number <mfaURL> --token-code <code>";

		ProcessBuilder builder = new ProcessBuilder(
				"cmd.exe", "/c", cmd.replaceAll("<mfaURL>", mfaURL).replaceAll("<code>", code));
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = r.readLine()) != null) {
			line = line.trim().replaceAll("\"", "").replaceAll(",", "").replaceAll(" ", "");
			
			System.out.println(line);
			
			if (line.contains("AccessKeyId")) {
				loginData.setKeyID(line.split(":")[1]);
			}
			if (line.contains("SecretAccessKey")) {
				loginData.setSecretKey(line.split(":")[1]);
			}
			if (line.contains("SessionToken")) {
				loginData.setSessionToken(line.split(":")[1]);
			}
		}

		System.out.println();
		
		if (loginData.getKeyID() == null || loginData.getKeyID().isEmpty()
				|| loginData.getSecretKey() == null | Objects.requireNonNull(loginData.getSecretKey()).isEmpty()
				|| loginData.getSessionToken() == null | Objects.requireNonNull(loginData.getSessionToken()).isEmpty()) {
			throw new Exception("Incomplete login data!!");
		}

	}

	public AWSLoginData getLoginData() {
		return loginData;
	}
}
