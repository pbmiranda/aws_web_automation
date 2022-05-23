package amazon.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.typesafe.config.Config;

import amazon.config.EnvFactory;
import amazon.pageobject.Menu;
import amazon.pageobject.Product;
import amazon.pageobject.SearchBar;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Phelipe Miranda 
 *  
 * This class represents the Tests  
 * Each method is associated to a file used as input
 */

@Feature("Search for Products")
@Slf4j
public class TestAWSWeb extends TestAbstract {

	private static Config config = EnvFactory.getInstance().getConfig();
	private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");

	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify sort by Options")
	@Story("Test type of sorting by Product")
	@ParameterizedTest
	@CsvFileSource(resources = "/test_data/sortByScenarios.csv", delimiter = '|')
	void sortByScenarios(ArgumentsAccessor accessor) {
		log.info("->>> sortByScenarios | data={}", accessor.toList());
		String menuAll = accessor.getString(0);
		String sector = accessor.getString(1);
		String subSector = accessor.getString(2);
		String sectionFilter = accessor.getString(3);
		String sectionFilterItem = accessor.getString(4);
		String sortByCriteria = accessor.getString(5);
		String productIndex = accessor.getString(6);
		String mainAssertion = accessor.getString(7);

		Menu menu = new Menu(getDriver());
		Product product = new Product(getDriver());

		menu.open(HOME_PAGE_URL);
		menu.mainClick(menuAll);
		menu.sideBarClick(sector);
		menu.sideBarClick(subSector);

		product.selectRefinement(sectionFilter, sectionFilterItem);
		product.sortBy(sortByCriteria);
		product.selectProductByIndex(productIndex);
		product.switchWindowByIndex(1);

		assertTrue(product.assertion(mainAssertion));
	}

	@Severity(SeverityLevel.BLOCKER)
	@Description("Verify Amazon Prime Options")
	@Story("Test option Amazon Prime for Products")
	@ParameterizedTest
	@CsvFileSource(resources = "/test_data/amazonPrimeScenarios.csv", delimiter = '|')
	void amazonPrimeScenarios(ArgumentsAccessor accessor) {
		log.info("->>> amazonPrimeScenarios | data={}", accessor.toList());
		String menuAll = accessor.getString(0);
		
		String sector = accessor.getString(1);
		String subSector = accessor.getString(2);		
		String sectionFilter1 = accessor.getString(3);
		String sectionFilterItem1 = accessor.getString(4);		
		String sectionFilter2 = accessor.getString(5);
		String sectionFilterItem2 = accessor.getString(6);		
		String productIndex = accessor.getString(7);
		String mainAssertion = accessor.getString(8);

		Menu menu = new Menu(getDriver());
		Product product = new Product(getDriver());

		menu.open(HOME_PAGE_URL);
		menu.mainClick(menuAll);
		menu.sideBarClick(sector);
		menu.sideBarClick(subSector);

		product.selectRefinement(sectionFilter1, sectionFilterItem1);
		product.selectRefinement(sectionFilter2, sectionFilterItem2);		
		product.selectProductByIndex(productIndex);
		product.switchWindowByIndex(1);

		assertTrue(product.assertion(mainAssertion));
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify main filter categories available")
	@Story("Test filter by categories")
	@ParameterizedTest
	@CsvFileSource(resources = "/test_data/mainFilterCategoriesScenarios.csv", delimiter = '|')
	void mainFilterCategoriesScenarios(ArgumentsAccessor accessor) {
		log.info("->>> mainFilterCategoriesScenarios | data={}", accessor.toList());
		
		String menuAll = accessor.getString(0);
		String sector = accessor.getString(1);
		String subSector = accessor.getString(2);
		String sectionFilter = accessor.getString(3);
		String sectionFilterItem = accessor.getString(4);	
		String productIndex = accessor.getString(5);
		String mainAssertion = accessor.getString(6);

		Menu menu = new Menu(getDriver());
		Product product = new Product(getDriver());

		menu.open(HOME_PAGE_URL);
		menu.mainClick(menuAll);
		menu.sideBarClick(sector);
		menu.sideBarClick(subSector);

		product.selectRefinement(sectionFilter, sectionFilterItem);		
		product.selectProductByIndex(productIndex);
		product.switchWindowByIndex(1);

		assertTrue(product.assertion(mainAssertion));
	}

	
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify Search by Departament")
	@Story("Test searching by departments")
	@ParameterizedTest
	@CsvFileSource(resources = "/test_data/searchBarScenarios.csv", delimiter = '|')
	void searchBarScenarios(ArgumentsAccessor accessor) {
		log.info("->>> searchBarScenarios | data={}", accessor.toList());
		String searcBarComboText = accessor.getString(0);
		String searcBarText = accessor.getString(1);
		String productIndex = accessor.getString(2);
		String assertionText = accessor.getString(3);
		
		SearchBar searchBar = new SearchBar(getDriver());
		Product product = new Product(getDriver());

		searchBar.open(HOME_PAGE_URL);		
		if(!searcBarComboText.equals("All Categories")) {
			searchBar.selectDepartament(searcBarComboText);	
		}		
		searchBar.type(searcBarText);
		searchBar.pressSearchButton();

		product.selectProductByIndex(productIndex);
		product.switchWindowByIndex(1);
		assertTrue(product.assertion(assertionText));
	}
}
