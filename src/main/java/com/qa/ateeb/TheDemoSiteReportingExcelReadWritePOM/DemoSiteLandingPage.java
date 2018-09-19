package com.qa.ateeb.TheDemoSiteReportingExcelReadWritePOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoSiteLandingPage {
	
	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")
	private WebElement hypAddUser;
	
	public void hypAddUser() {
		
		hypAddUser.click();
		
	}

}
