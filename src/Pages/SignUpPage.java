package Pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage {
	
	WebDriver driver ;
	
	Random rand =new Random();
	
	public  SignUpPage(WebDriver theDriver) {
		
		this.driver=theDriver;
		
	}
	
	
// Locators
	By firstName = By.xpath("//input[@placeholder='Name']");
	By email = By.xpath("//input[@data-qa='signup-email']");
    By password = By.xpath("//input[@id='password']");
    By day = By.xpath(" //select[@id='days']");
    By months = By.xpath(" //select[@id='months']");
    By years = By.xpath("//select[@id='years']");
    By firstName2 = By.id("first_name");
	By lastName = By.id("last_name");
    By company = By.xpath(" //input[@id='company']");
    By address1 = By.xpath(" //input[@id='address1']");
    By country = By.xpath(" //select[@id='country']");
    By state = By.xpath(" //input[@id='state']");
    By city = By.xpath(" //input[@id='city']");
    By zipCode = By.xpath(" //input[@id='zipcode']");
    By mobileNumber = By.xpath(" //input[@id='mobile_number']");
    By nameCard = By.xpath("//input[@name='name_on_card']");
    By cardNumber = By.xpath("//input[@name='card_number']");
    By cvc = By.xpath("//input[@placeholder='ex. 311']");
    By MM = By.xpath("//input[@placeholder='MM']");
    By YYYY = By.xpath("//input[@placeholder='YYYY']");
    By name = By.xpath("//input[@placeholder='Name']");
    By email2 = By.xpath("//input[@placeholder='Email']");
    By Subject = By.xpath("//input[@placeholder='Subject']");
    By message = By.xpath("//textarea[@id='message']");
    By email3 = By.xpath("//input[@id='susbscribe_email']");

    By nameRev = By.id("name");
    By emailRev = By.id("email");
    By reviewRev = By.id("review");

    
    
//Action    
    public void fillReg(String f, String mail) throws InterruptedException {
    	driver.findElement(firstName).sendKeys(f);
        driver.findElement(email).sendKeys(mail);
    	
    }
    
    
    public void fillForm(String pass, String f, String l) throws InterruptedException {
    	
    	driver.findElement(password).sendKeys(pass);
 
        Select daySelect = new Select(driver.findElement(day));
        int dayCount = driver.findElement(day).findElements(By.tagName("option")).size();
        daySelect.selectByIndex(rand.nextInt(1,dayCount));

        Select monthSelect = new Select(driver.findElement(months));
        int monthCount = driver.findElement(months).findElements(By.tagName("option")).size();
        monthSelect.selectByIndex(rand.nextInt(monthCount));
        
        Select yearsSelect = new Select(driver.findElement(years));
        int yearsCount = driver.findElement(years).findElements(By.tagName("option")).size();
        yearsSelect.selectByIndex(rand.nextInt(yearsCount));
        
        driver.findElement(firstName2).sendKeys(f);
    	driver.findElement(lastName).sendKeys(l);
    	driver.findElement(company).sendKeys("abc");
    	driver.findElement(address1).sendKeys("Amman tlaaelAli");
        
    	Select countrySelect = new Select(driver.findElement(country));
        int countryCount = driver.findElement(country).findElements(By.tagName("option")).size();
        countrySelect.selectByIndex(rand.nextInt(1,countryCount));
        
        driver.findElement(state).sendKeys("alrafed");
        driver.findElement(city).sendKeys("Amman");
        driver.findElement(zipCode).sendKeys("9624545755");
        driver.findElement(mobileNumber).sendKeys("9624545455");
        
    
       
    }
    public void Payment(String f) throws InterruptedException {
    	driver.findElement(nameCard).sendKeys(f);
        driver.findElement(cardNumber).sendKeys("5582155");
        driver.findElement(cvc).sendKeys("311");
        driver.findElement(MM).sendKeys("11");
        driver.findElement(YYYY).sendKeys("2005");
    	
    }
    
    public void Contact(String f,String e) throws InterruptedException {
    	driver.findElement(name).sendKeys(f);
        driver.findElement(email2).sendKeys(e);
        driver.findElement(Subject).sendKeys("hi");
        driver.findElement(message).sendKeys("my name abdalrahman obeidat");
       
    	
    }
    public void Subscription(String e) throws InterruptedException {
    	
        driver.findElement(email3).sendKeys(e);
        
        
    	
    }
    
    public void selectRandomBrand(WebDriver driver) {
        List<WebElement> brands = driver.findElements(By.xpath("//div[@class='brands-name']//ul/li/a"));

        int brandCount = brands.size();
        int randomIndex = rand.nextInt(brandCount); 
        WebElement randomBrand = brands.get(randomIndex);
        randomBrand.click();
    }
    
    
    public void Review(String f, String mail) throws InterruptedException {
    	driver.findElement(nameRev).sendKeys(f);
        driver.findElement(emailRev).sendKeys(mail);
        driver.findElement(reviewRev).sendKeys("my name abdalrahman obeidat");

    	
    }
    
    public void itemSize(boolean needSize) throws InterruptedException {
    	if (needSize) {
    		Thread.sleep(2000);
			System.out.println("This item need size");
			driver.findElement(By.xpath("//label[@for='option344747']")).click();

		}
    }
    	
		
	
    
    public boolean isSignUpSuccess() {
    	return driver.getPageSource().contains("Your Account Has Been Created!");
		
	}

}
