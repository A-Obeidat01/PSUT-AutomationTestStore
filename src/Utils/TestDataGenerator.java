package Utils;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestDataGenerator {
	static Random rand= new Random();
	
	public static String getRandFirstName() {
		
		String [] firstNames= {"amira", "rogina", "dana", "mais", "dareen","alaa", "saif", "abduallah", "hamzeh", "marwan", "abedalrahman", "omar"};
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
	
	public static WebElement getRandomProduct(WebDriver driver) {
        List<WebElement> listOfItem = driver.findElements(By.linkText("View Product"));
        int numOfItem = listOfItem.size();
        int randIndex = rand.nextInt(numOfItem);
        return listOfItem.get(randIndex); 
    }
	
}
