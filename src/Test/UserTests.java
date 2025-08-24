package Test;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.SignUpPage;
import Utils.DriverFactory;
import Utils.TestDataGenerator;


public class UserTests {
	 WebDriver driver;
	    SignUpPage signupPage;
	    String firstName;
	    String lastName;
	    String username;
	    String email;
	    String password = "Test@1234";

	    
@BeforeTest
public void setup() {
	 driver = DriverFactory.getDriver();
     driver.get("https://www.automationexercise.com/.");
     signupPage =new SignUpPage(driver);
	
}

@Test(priority = 1,enabled = true)
public void NewCustomer() throws InterruptedException {
	
	WebElement signupLogin = driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']"));
	
	signupLogin.click();
}

@Test(priority = 2,enabled = true)
public void testRegister() throws InterruptedException {
	Thread.sleep(2000);
	firstName = TestDataGenerator.getRandFirstName();
	lastName =TestDataGenerator.getRandLastName(); 
    email = TestDataGenerator.getEmail(firstName, lastName);
    signupPage.fillReg(firstName, email);
	driver.findElement(By.xpath("//button[normalize-space()='Signup']")).click();
  
}

@Test(priority = 3,enabled = true)
public void testRegister2() throws InterruptedException {
	Thread.sleep(500);
	String firstName = TestDataGenerator.getRandFirstName();
	String lastName =TestDataGenerator.getRandLastName(); 

	
	Thread.sleep(500);
	 signupPage.fillForm( password,firstName, lastName);
	 driver.findElement(By.xpath("//button[normalize-space()='Create Account']")).click(); 
	 driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click(); 

	
}
@Test(priority = 4,enabled = true)
public void testLogout() throws InterruptedException {
    WebElement logoutButton = driver.findElement(By.xpath("//a[normalize-space()='Logout']"));
    logoutButton.click();
    Thread.sleep(1000);
}

@Test(priority = 5,enabled = true, dependsOnMethods = {"testRegister"})
public void testLogin() throws InterruptedException {
    driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(email);
    driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
    driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
}

@Test(priority = 6,enabled =true)
public void viewingProductDetailsAndAddToCart() throws InterruptedException {
	Thread.sleep(2000);
	 int numberOfProductsToAdd = 3;  
	    for (int i = 0; i < numberOfProductsToAdd; i++) {
	    	WebElement randomProduct = TestDataGenerator.getRandomProduct(driver);
	    	randomProduct.click();
	    	
	    	WebElement addToCrtButton = driver.findElement(By.xpath("//button[@type='button']"));
	    	WebElement continueShoppinButton = driver.findElement(By.xpath(" //button[@class='btn btn-success close-modal btn-block']"));
	    
	    	addToCrtButton.click();
	    	Thread.sleep(500);
	    	continueShoppinButton.click();
	    	driver.navigate().back();
	    }
	 
}


@Test(priority = 7, enabled = true)
public void testCartAndCheckout() throws InterruptedException {
	Thread.sleep(1000);
    WebElement cartButton = driver.findElement(By.xpath("/html[1]/body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/a[1]"));

    cartButton.click();
    Thread.sleep(500);
    WebElement checkOut = driver.findElement(By.xpath("//a[@class='btn btn-default check_out']"));
    checkOut.click();
    Thread.sleep(500);
    WebElement placeOrder = driver.findElement(By.xpath("//a[@class='btn btn-default check_out']"));
    placeOrder.click();
  
}


@Test(priority = 8, enabled = true, dependsOnMethods = {"viewingProductDetailsAndAddToCart", "testCartAndCheckout"})
public void testPayment() throws InterruptedException {
    Thread.sleep(1000);
    signupPage.Payment(firstName);
    Thread.sleep(1000);

    WebElement payButton = driver.findElement(By.xpath("//button[@id='submit']"));
    Thread.sleep(1000);

    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", payButton);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", payButton);

    Thread.sleep(1000);
    driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
}
@Test(priority = 9, enabled =true)
public void contactUs() throws InterruptedException {
	driver.findElement(By.xpath("//a[normalize-space()='Contact us']")).click();
	Thread.sleep(500);
	firstName = TestDataGenerator.getRandFirstName();
	lastName =TestDataGenerator.getRandLastName(); 
    email = TestDataGenerator.getEmail(firstName, lastName);
    signupPage.Contact(firstName, email);
    Thread.sleep(500);
    driver.findElement(By.xpath("//input[@name='submit']")).click();
    Thread.sleep(500);
    Alert alert = driver.switchTo().alert();
    alert.accept();
    Thread.sleep(500);
    driver.findElement(By.xpath("//span[normalize-space()='Home']")).click();

  
}
@Test(priority = 10, enabled =true)
public void Subscription() throws InterruptedException {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollTo(0,8000)");
	Thread.sleep(1000);
	firstName = TestDataGenerator.getRandFirstName();
	lastName =TestDataGenerator.getRandLastName(); 
    email = TestDataGenerator.getEmail(firstName, lastName);
    signupPage.Subscription(email);
    Thread.sleep(1000);
    driver.findElement(By.xpath("//i[@class='fa fa-arrow-circle-o-right']")).click();

  
}
@Test(priority = 11, enabled = true,invocationCount = 2)
public void testSelectRandomBrand() throws InterruptedException {
	signupPage.selectRandomBrand(driver);
}

@Test(priority = 12,enabled =true)
public void Review() throws InterruptedException {
	Thread.sleep(2000);
	driver.navigate().to("https://www.automationexercise.com/");   
	WebElement randomProduct = TestDataGenerator.getRandomProduct(driver);
	randomProduct.click();
	Thread.sleep(500);
	firstName = TestDataGenerator.getRandFirstName();
	lastName =TestDataGenerator.getRandLastName(); 
    email = TestDataGenerator.getEmail(firstName, lastName);
    signupPage.Review(firstName,email);   
    Thread.sleep(500);
    driver.findElement(By.id("button-review")).click();
    Thread.sleep(500);
	driver.navigate().back();
	    
	 
}





/*
@AfterTest
public void teardown() {
    DriverFactory.quitDriver();
}*/
}
