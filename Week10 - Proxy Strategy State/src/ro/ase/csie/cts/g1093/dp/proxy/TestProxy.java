package ro.ase.csie.cts.g1093.dp.proxy;

public class TestProxy {

	public static void main(String[] args) {
		
		LoginInterface loginModule = new LoginModule("10.0.0.1", "users");
		
		if(loginModule.login("admin", "1234admin")) {
			System.out.println("Hello Admin");
		}
		else {
			System.out.println("Wrong credentials");
		}
		
		loginModule = new LoginWith2FactorAuth(loginModule);
		
		String[] password = new String[] {"1234", "123456", "admin", "root", "1234admin"};
		for(String pass: password) {
			if(loginModule.login("admin", pass))
				System.out.println("Found it ! Is " + pass);
		}
	}

}
