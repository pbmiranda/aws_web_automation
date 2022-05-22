package amazon.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Phelipe Miranda
 * 
 * This class is responsible for the finding strategies.
 * It abstracts the operations over the components  
 *
 */
public abstract class AbstractPageComponent {

	private WebDriver driver;

	public AbstractPageComponent(WebDriver driver) {		
		this.driver = driver;
	}
	
	/**
	 * Find element by attribute	
	 * @param attr  - the attribute name
	 * @param value  - the attribute value
	 * @return - WebElement
	 */
	protected WebElement findByAttr(String attr, String value) {
		WebElement e = driver.findElement(By.xpath("//*[@" + attr + "='" + value + "']"));
		return e;
	}	
		
	/**
	 * Find element by attribute and type a text	
	 * @param attrName - the attribute name
	 * @param value  - the attribute value
	 * @param text - the element text
	 */
	protected void typeByAttr(String attrName, String value, String text) {
		findByAttr(attrName, value).sendKeys(text);	
	}
	
	/**
	 * Find element by attribute and click	
	 * @param attrName - the attribute name
	 * @param value  - the attribute value
	 * @param text - the element text
	 */
	protected void clickByAttr(String attrName, String value) {
		findByAttr(attrName, value).click();
	}
	
	/**
	 * Find element by xpath
	 * @param xpath - the xpath for locating the element
	 * @return - WebElement
	 */
	protected WebElement findByXPath(String xpath) {
		WebElement e = driver.findElement(By.xpath(xpath));
		return e;
	}

	/**
	 * Find element by xpath and type a text	
	 * @param xpath - the xpath for locating the element	 
	 * @param text - the text that will be typed
	 */
	protected void typeByXPath(String xpath, String text) {
		findByXPath(xpath).sendKeys(text);	
	}
	
	/**
	 * Find element by xpath and click
	 * @param text - the xpath for locating the element
	 */
	protected void clickByXPath(String xpath) {
		findByXPath(xpath).click();
	}

	/**
	 * Find an element by its text in any location of a page
	 * @param text  - the element text
	 * @return WebElement
	 */
	protected WebElement findByText(String text) {
		return findByXPath("//*[normalize-space(text())= \"" + text.trim() + "\"]");
	}

	/**
	 * Find an element from its parent id and by element text 
	 *  Text match must be equals 
	 * @param parentId - the id of element's parent
	 * @param text - the element text of the desired element
	 * @return WebElement
	 */
	protected WebElement findByParentIdAndTextEquals(String parentId, String text) {
		String xpath="//*[@id='"+parentId + "']//*[normalize-space(text())='"+ text.trim() +"']";		
		return driver.findElement(By.xpath(xpath));
	}
	
	/**
	 * Find an element from its parent id and by element text
	 * Text match as contains text 
	 * @param parentId - the id of element's parent
	 * @param text - the element text of the desired element
	 * @return WebElement
	 */
	protected WebElement findByParentIdAndTextContains(String parentId, String text) {
		String xpath="//*[@id='"+parentId + "']//*[contains(normalize-space(text()),'"+ text.trim() +"')]";		
		return driver.findElement(By.xpath(xpath));
	}
	
	/**
	 * Find an element from its parent and by element text and click
	 * @param parentId - the id of element's parent
	 * @param text - the element text of the desired element
	 * @return WebElement
	 */
	protected void clickByParentIdAndText(String parentId, String text) {
		findByParentIdAndTextEquals(parentId, text).click();
	}
	
	/**
	 * Switch between windows, the index starts in zero 
	 * @param index - index of window
	 */
	public void switchWindowByIndex(int index) {
		String[] windows = driver.getWindowHandles().toArray(new String[driver.getWindowHandles().size()]);
		driver.switchTo().window(windows[index]);
	}

	/**
	 * Opens a url
	 * @param url
	 */
	public void open(String url) {
		driver.get(url);
	}
	
	/**
	 *  Perform sleep
	 * @param sleep - time in millisecconds
	 */
	protected void await(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
