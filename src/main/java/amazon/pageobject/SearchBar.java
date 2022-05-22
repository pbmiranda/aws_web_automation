package amazon.pageobject;

import org.openqa.selenium.WebDriver;
/**
 * 
 * @author Phelipe Miranda
 * 
 * This class represents the SearchBar of AWS Page and its operations
 */
public class SearchBar extends AbstractPageComponent{

	public static String SEARCH_BAR_ID = "twotabsearchtextbox";	
	public static String SEARCH_BUTTON_ID = "nav-search-submit-button";
		
	public SearchBar(WebDriver driver) {
		super(driver);		
	}
	
	/**
	 * Press the search button 
	 */
	public void pressSearchButton() {
		clickByAttr("id", SEARCH_BUTTON_ID);
	}

	/**
	 * Type in the search bar
	 * @param text - the text that will be typed
	 */
	public void type(String text) {
		typeByAttr("id", SEARCH_BAR_ID, text);
	}	
}
