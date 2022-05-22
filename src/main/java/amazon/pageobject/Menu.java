package amazon.pageobject;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class Menu extends AbstractPageComponent{

	public static String MENU_ID = "nav-main";
	public static String MENU_SIDEBAR_ID = "hmenu-content";
		
	public Menu(WebDriver driver) {
		super(driver);		
	}
	
	@Step("Click on main menu")
	public void mainClick(String text) {
		clickByParentIdAndText(MENU_ID, text);
	}
	
	@Step("Click on sidebar menu")
	public void sideBarClick(String text) {
		await(500);
		String xpath="//div[@id='" + MENU_SIDEBAR_ID + "']/ul[contains(@class,'hmenu hmenu-visible')]/li//*[text()='" + text + "']";
		clickByXPath(xpath);				 
	}
}
