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
	
	public static String SEARCH_COMBO_ID = "nav-search-dropdown-card";
	public static String SEARCH_COMBO_ITEM_ID = "searchDropdownBox";
	
		
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
	
	
	public void selectDepartament(String text) {	
		clickByAttr("id",SEARCH_COMBO_ID);		
		clickByParentIdAndText(SEARCH_COMBO_ID, text);	
	}
}
