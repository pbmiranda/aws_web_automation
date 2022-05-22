package amazon.pageobject;

import org.openqa.selenium.WebDriver;

public class SearchBar extends AbstractPageComponent{

	public static String SEARCH_BAR_ID = "twotabsearchtextbox";	
	public static String SEARCH_BUTTON_ID = "nav-search-submit-button";
		
	public SearchBar(WebDriver driver) {
		super(driver);		
	}
	
	public void pressSearchButton() {
		clickByAttr("id", SEARCH_BUTTON_ID);
	}

	public void type(String text) {
		typeByAttr("id", SEARCH_BAR_ID, text);
	}	
}
