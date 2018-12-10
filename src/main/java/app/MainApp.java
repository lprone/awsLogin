package app;

import data.AWS;
import data.AWSLoginData;
import utils.Credentials;
import utils.ProgramProperties;

/**
 * 
 * @author lprone
 *
 */
public class MainApp {

	/**
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String propertiesPath = null;

		switch (args.length) {
		case 1:
			propertiesPath = "config.properties";
			break;
		case 2:
			propertiesPath = args[1];
			break;

		default:
			System.out.println("input error!! ");
			System.out.println("java -jar AwsLogin.jar 123456");
			System.out.println("java -jar AwsLogin.jar 123456 myProfile.properties");
			System.exit(1);
			break;
		}

		ProgramProperties prop = new ProgramProperties(propertiesPath);
		prop.readProperties();

		Credentials c = new Credentials(prop.getCredentialsPath(),
				new AWSLoginData(prop.getKeyID(), prop.getSecretKey(), null));
		c.writeCreds();

		AWS aws = new AWS(prop.getMfaArn(), args[0]);
		aws.Login();

		c = new Credentials(prop.getCredentialsPath(), aws.getLoginData());
		c.writeCreds();

		System.out.println("Done");
	}

}
