package amazon.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

import com.typesafe.config.Config;

import amazon.config.EnvFactory;
import amazon.factories.DriverFactory;
import amazon.pageobject.Menu;
import amazon.pageobject.Product;
import amazon.pageobject.SearchBar;
import io.qameta.allure.Description;

public class TestAWSWeb extends TestAbstract{
	
	private static Config config = EnvFactory.getInstance().getConfig();
	private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");

	@Tag("smokeTest")
	@DisplayName("This test is for demo purpose only to show that the basic code works."
			+ "You have to use the best practices that you normally use to design your tests")
	
	@Description("TESTE DESCRIPITON ALUURE")	
	@ParameterizedTest
	@CsvFileSource(resources = "/test_data/sortByScenarios.csv", delimiter = '|')
	void sortByScenarios(ArgumentsAccessor accessor) {
		
		System.out.println(accessor.toList());
		String sector = accessor.getString(0);
		String subSector = accessor.getString(1);
		String sectionFilter = accessor.getString(2);
		String sectionFilterItem = accessor.getString(3);
		String sortByCriteria = accessor.getString(4);
		String productIndex = accessor.getString(5);
		String mainAssertion = accessor.getString(6);
		
		open(HOME_PAGE_URL);
		
		Menu menu = new Menu(getDriver());
		Product product = new Product(getDriver());

		menu.mainClick("All");
		
		menu.sideBarClick(sector);
		menu.sideBarClick(subSector);

		product.selectRefinement(sectionFilter, sectionFilterItem);
		product.sortBy(sortByCriteria);
		product.selectProductByIndex(productIndex);

		product.switchWindowByIndex(1);
		
		System.out.println("...realizando assertion");
		assertEquals(mainAssertion, product.findTextInDescription(Product.DESC_CENTER_COL, mainAssertion));
		System.out.println("fiim do ...realizando assertion");
	}

	
	@Description("TESTE DESCRIPITON ALUURE 2")	
	@ParameterizedTest
	@CsvFileSource(resources = "/test_data/searchBarScenarios.csv", delimiter = '|')
	void searchBarScenarios(ArgumentsAccessor argumentsAccessor) {

		System.out.println(argumentsAccessor.toList());

		String searcBarText = argumentsAccessor.getString(0);
		String sectionFilter = argumentsAccessor.getString(1);
		String sectionFilterItem = argumentsAccessor.getString(2);
		String productIndex = argumentsAccessor.getString(3);
		String assertionText = argumentsAccessor.getString(4);

		open(HOME_PAGE_URL);

		SearchBar searchBar = new SearchBar(getDriver());
		Product product = new Product(getDriver());

		searchBar.type(searcBarText);
		searchBar.pressSearchButton();

		product.selectRefinement(sectionFilter, sectionFilterItem);
		product.selectProductByIndex(productIndex);
		product.switchWindowByIndex(1);

		assertEquals(assertionText, product.findTextInDescription(Product.DESC_FULL, assertionText));
	}
	

}
