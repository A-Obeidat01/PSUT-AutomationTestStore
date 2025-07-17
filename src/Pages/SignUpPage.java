package Pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage {
	
	WebDriver driver ;
	
	Random rand =new Random();
	
	public void signUpPage(WebDriver theDriver) {
		
		this.driver=theDriver;
		
	}
	
	
// Locators
	By firstName = By.name("firstname");
	By lastName = By.id("AccountFrm_lastname");
	By email = By.id("AccountFrm_email");
    By telephone = By.id("AccountFrm_telephone");
    By fax = By.id("AccountFrm_fax");
    By company = By.id("AccountFrm_company");
    By address1 = By.id("AccountFrm_address_1");
	By address2 = By.id("AccountFrm_address_2");
    By city = By.id("AccountFrm_city");
    By postcode = By.id("AccountFrm_postcode");
    By loginname = By.id("AccountFrm_loginname");
    By password = By.id("AccountFrm_password");
    By confirm = By.id("AccountFrm_confirm");
    By agree = By.id("AccountFrm_agree");
    By continueBtn = By.cssSelector("button[title='Continue']");
    By country = By.id("AccountFrm_country_id");
    By state = By.id("AccountFrm_zone_id");
	
	
    
//Action    
    public void action(String f, String l, String mail, String phone, String user, String pass, String firstname) throws InterruptedException
    {
    	driver.findElement(firstName).sendKeys(f);
    	driver.findElement(lastName).sendKeys(l);
    	driver.findElement(email).sendKeys(mail);
    	driver.findElement(telephone).sendKeys(phone);
        driver.findElement(fax).sendKeys("55828282");
        driver.findElement(company).sendKeys("abd");
        driver.findElement(address1).sendKeys("Irbid - Alrafed");
        driver.findElement(address2).sendKeys("Irbid - Alrafed");
        driver.findElement(city).sendKeys("Irbid");
        
        Select countrySelect = new Select(driver.findElement(country));
        int countryCount = driver.findElement(country).findElements(By.tagName("option")).size();
        countrySelect.selectByIndex(rand.nextInt(1,countryCount));
        
        Thread.sleep(2000);
        
        Select stateSelect = new Select(driver.findElement(state));
        int stateCount = driver.findElement(state).findElements(By.tagName("option")).size();
        stateSelect.deselectByIndex(rand.nextInt(1,stateCount));
		
        driver.findElement(postcode).sendKeys("3817");
        driver.findElement(loginname).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirm).sendKeys(pass);
        driver.findElement(agree).click();
        driver.findElement(continueBtn).click();
    }

    public boolean isSignUpSuccess() {
    	return driver.getPageSource().contains("Your Account Has Been Created!");
		
	}

}
