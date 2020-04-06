package com.crm.qa.testcases;

/*Author 
 * is 
 * Guneet 
 * Chhabra*/
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBasePage;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBasePage{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest() {
		super();
		
	}
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		 loginPage = new LoginPage();
		 testUtil= new TestUtil();
		 contactsPage = new ContactsPage();
		 homePage= loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		 //TestUtil.runTimeInfo("error", "login successful");
		 testUtil.switchToFrame();
		 //contactsPage=homePage.clickOnContactsLink();
	}
	
	
	@Test(priority=1)
	public void verifyContactsPageLabelTest() {
		contactsPage=homePage.clickOnContactsLink();
		Assert.assertTrue(contactsPage.verifyContactLabel(),"Contacts Label is MISSING!!");
		}
	
	@Test(priority=2)
	public void selectContactsTest() {
		contactsPage=homePage.clickOnContactsLink();
		contactsPage.selectContacts("Guneet Chhabra");
		}
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=3)
	public void clickCheckNewContacts() {
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstName, String lastName, String company){
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		contactsPage.createNewContact(title, firstName, lastName, company);
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
	
	
	
}
