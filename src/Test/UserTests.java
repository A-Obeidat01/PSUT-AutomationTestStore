package Test;

import java.util.List;

import org.openqa.selenium.By;
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
	    String username;
	    String password = "Test@1234";

	    
@BeforeTest
public void setup() {
	 driver = DriverFactory.getDriver();
     driver.get("https://automationteststore.com/index.php?rt=account/create");
     signupPage =new SignUpPage(driver);
	
}


@Test(priority = 1,enabled = true)
public void testSignup() throws InterruptedException {
    firstName = TestDataGenerator.getRandFirstName();
    String lastName = TestDataGenerator.getRandLastName();
    username = TestDataGenerator.getRandUserName(firstName, lastName);
    String email = TestDataGenerator.getEmail(firstName, lastName);

    signupPage.fillForm(firstName, lastName, email, "9624545455", username, password);
    Thread.sleep(1000);
    Assert.assertTrue(signupPage.isSignUpSuccess(), "Signup failed");


}
@Test(priority = 3, dependsOnMethods = {"testSignup"})
public void testLogin() throws InterruptedException {
    driver.findElement(By.partialLinkText("Login or register")).click();
    driver.findElement(By.id("loginFrm_loginname")).sendKeys(username);
    driver.findElement(By.id("loginFrm_password")).sendKeys(password);
    driver.findElement(By.xpath("//button[@title='Login']")).click();
    Thread.sleep(1000);
    Assert.assertTrue(driver.findElement(By.id("customernav")).getText().contains(firstName), "Login failed");
}


@Test(priority = 2,enabled = true)
public void testLogout() throws InterruptedException {
    WebElement logoutButton = driver.findElement(By.linkText("Logoff"));
    logoutButton.click();
    Thread.sleep(1000);
    Assert.assertTrue(driver.getPageSource().contains("You have been logged off your account."), "Logout failed");
}
@Test(priority = 4, enabled = true)
public void testAddToCartAndCheckout() throws InterruptedException {
    driver.get("https://automationteststore.com/");
    Thread.sleep(1000);
    List<WebElement> items = driver.findElements(By.className("prdocutname"));
    items.get((int)(Math.random() * items.size())).click();
    Thread.sleep(2000);
    WebElement addToCartBtn = driver.findElement(By.className("productpagecart"));
    if (addToCartBtn.getText().equals("Out of Stock")) {
        System.out.println("Item is out of stock");
        driver.navigate().back();
         }
    else {
		boolean needSize = driver.getCurrentUrl().contains("product_id=116");
    	signupPage.itemSize(needSize);
    	addToCartBtn.click();
    }
    
    
    Thread.sleep(1000);
    driver.findElement(By.linkText("Checkout")).click();
    Assert.assertTrue(driver.getCurrentUrl().contains("checkout"), "Checkout navigation failed");
}


/*
@AfterTest
public void teardown() {
    DriverFactory.quitDriver();
}*/
}
