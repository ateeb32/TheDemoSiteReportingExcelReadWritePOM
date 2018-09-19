package com.qa.ateeb.TheDemoSiteReportingExcelReadWritePOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoSiteAddUser {
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")
	private WebElement writeUserFieldAddUser;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")
	private WebElement writePassFieldAddUser;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")
	private WebElement clickSaveAddUser;
	
	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")
	private WebElement clickLoginPage;
	
	public void writeUserFieldAddUser(String username) {
		
		writeUserFieldAddUser.sendKeys(username);
		
	}
	
	public void writePassFieldAddUser(String password) {
		
		writePassFieldAddUser.sendKeys(password);
		
	}
	
	public void clickSaveAddUser() {
		
		clickSaveAddUser.click();
		
	}
	
	public void clickLoginPage() {
		
		clickLoginPage.click();
		
	}

}
