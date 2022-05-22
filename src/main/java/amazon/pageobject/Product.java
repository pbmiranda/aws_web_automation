package amazon.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * 
 * @author Phelipe Miranda
 * 
 * This class represents the Product of AWS Page and its operations
 * 
 */
public class Product extends AbstractPageComponent{

	public static String REF_SIDEBAR_ID = "s-refinements";	
	public static String COMBO_ID = "a-autoid-0-announce";
	public static String COMBO_ITEM_ID = "a-popover-2";		
	public static String IMG_INDEX_ATTR = "data-image-index";
	
	public static String DESC_FULL="ppd";
	public static String DESC_CENTER_COL="centerCol";
	public static String DESC_RIGHT_COL="rightCol";
	
	public Product(WebDriver driver) {
		super(driver);		
	}
	
	/**
	 * This method selects a filter category and its item
	 * Example filter category = "Brands" and item= "Apple"  
	 * @param filterCategory 
	 * @param item
	 */
	public void selectRefinement(String filterCategory, String item) {		
		String xpath= "//*[@id='" + REF_SIDEBAR_ID + "']//span[text() ='" + filterCategory+"']//parent::div//following-sibling::ul//a//span[normalize-space(text())='"+ item + "']//ancestor::a";
		clickByXPath(xpath);
	}
	
	/**
	 *  This method selects a type of sorting of product list
	 * @param text - the desired sorting
	 */
	public void sortBy(String text) {
		await(1000); // for firefox,needed to be investigated
		clickByAttr("id",COMBO_ID);		
		clickByParentIdAndText(COMBO_ITEM_ID, text);	
	}
	
	/**
	 * This method selects a product by its index 
	 * @param index
	 */
	public void selectProductByIndex(String index){	
		clickByAttr(IMG_INDEX_ATTR,index);	
	}
	
	/**
	 * This methods executes the assertion for product
	 * by looking for specific text inside a component 
	 * @param text - the desired text
	 * @return true if finds
	 */
	public boolean assertion(String text) {
		WebElement e= findByParentIdAndTextContains(DESC_CENTER_COL, text);
		if(e != null) {
			return true;
		}
		return false;
	}
}
