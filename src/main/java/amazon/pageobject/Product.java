package amazon.pageobject;

import org.openqa.selenium.WebDriver;

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

	public void selectRefinement(String sectionParentName, String item) {		
		String xpath= "//*[@id='" + REF_SIDEBAR_ID + "']//span[text() ='" + sectionParentName+"']//parent::div//following-sibling::ul//a//span[normalize-space(text())='"+ item + "']//ancestor::a";
		System.out.println(xpath);
		clickByXPath(xpath);
	}
	
	public void sortBy(String text) {		
		clickByAttr("id",COMBO_ID);
		clickByParentIdAndText(COMBO_ITEM_ID, text);	
	}
	
	public void selectProductByIndex(String index){	
		clickByAttr(IMG_INDEX_ATTR,index);	
	}
	
	public String findTextInDescription(String section,String text) {
		 return findByParentIdAndText(section, text).getText();
	}
	
}
