package Utils;

import java.util.Random;

public class TestDataGenerator {
	static Random rand= new Random();
	
	public static String getRandFirstName() {
		String [] firstNames= {"amira", "rogina", "dana", "mais", "dareen"};
		return firstNames[rand.nextInt(firstNames.length)]; 	
	}

	public static String  getRandLastName() {
		String [] lastNames= {"alaa", "saif", "abduallah", "hamzeh", "marwan", "abedalrahman", "omar"};
		return lastNames[rand.nextInt(lastNames.length)];
	}
	
	public static String  getEmail(String first , String last) {
	return first+last+rand.nextInt(7000)+"@gimal.com";	
	}
	
	public static String getRandUserName(String first , String last) {
		return first+last+rand.nextInt(7000);
	}
	
}
