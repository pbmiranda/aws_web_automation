package amazon.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPageComponent {

	private WebDriver driver;

	public AbstractPageComponent(WebDriver driver) {		
		this.driver = driver;
	}
			
	protected WebElement findByAttr(String attr, String value) {
		WebElement e = driver.findElement(By.xpath("//*[@" + attr + "='" + value + "']"));
		return e;
	}	
		
	protected void typeByAttr(String attrName, String value, String text) {
		findByAttr(attrName, value).sendKeys(text);	
	}
	
	protected void clickByAttr(String attrName, String value) {
		findByAttr(attrName, value).click();
	}
	
	
	protected WebElement findByXPath(String xpath) {
		WebElement e = driver.findElement(By.xpath(xpath));
		return e;
	}

	protected void typeByXPath(String xpath, String text) {
		findByXPath(xpath).sendKeys(text);	
	}
	
	protected void clickByXPath(String xpath) {
		findByXPath(xpath).click();
	}

	protected WebElement findByText(String text) {
		return findByXPath("//*[normalize-space(text())= \"" + text.trim() + "\"]");
	}

	
	protected WebElement findByParentIdAndText(String parentId, String text) {
		String xpath="//*[@id='"+parentId + "']//*[normalize-space(text())='"+ text +"']";		
		return driver.findElement(By.xpath(xpath));
	}
	
	protected void clickByParentIdAndText(String parentId, String text) {
		findByParentIdAndText(parentId, text).click();
	}
	
	public void switchWindowByIndex(int index) {
		String[] windows = driver.getWindowHandles().toArray(new String[driver.getWindowHandles().size()]);
		driver.switchTo().window(windows[index]);
	}

	protected void await(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}

}
