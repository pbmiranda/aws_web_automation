package amazon.test;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import amazon.factories.DriverFactory;

/**
 * 
 * @author Phelipe Miranda
 *  This abstract class represents the Test
 *  And its operations of before and after
 *
 */

public abstract class TestAbstract {
	
	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}


	@BeforeEach
	void setUp() {
		driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
	}

	@AfterEach
	void tearDown() {
		driver.quit();
	}
}
