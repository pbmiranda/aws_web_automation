package amazon.pageobject;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

/**
 * 
 * @author Phelipe Miranda
 * 
 *         This class represents the main Menu of AWS Page and its operations
 *
 */
public class Menu extends AbstractPageComponent {

	private static String MENU_ID = "nav-main";
	private static String MENU_SIDEBAR_ID = "hmenu-content";

	public Menu(WebDriver driver) {
		super(driver);
	}

	/**
	 * Click on main menu by its parent id and text element
	 * 
	 * @param text
	 */
	public void mainClick(String text) {
		clickByParentIdAndText(MENU_ID, text);
	}

	/**
	 * Click on sidebar menu The xpath strategy finds the sidebar id and then click
	 * by text
	 * 
	 * @param text - the sidebar menu option
	 */
	public void sideBarClick(String text) {
		await(1000);
		// the automation goes fast, Selenium finds the element and clicks during the
		// transition of the sidebar menu.
		// even though the xpath expects the element to be visible, await the transition
		// to visible with 'hmenu hmenu-visible', for some reason the browser does not
		// recognise the click,
		// probably because it happens during the transition, this needs to be
		// investigated further
		String xpath = "//div[@id='" + MENU_SIDEBAR_ID + "']/ul[contains(@class,'hmenu hmenu-visible')]/li//*[text()='"
				+ text + "']";
		clickByXPath(xpath);
	}
}
