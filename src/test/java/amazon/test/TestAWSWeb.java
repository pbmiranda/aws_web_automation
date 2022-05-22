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

/**
 * 
 * @author Phelipe Miranda 
 *  
 * This class represents the Tests  
 * Each method is associated to a file used as input
 */

public class TestAWSWeb extends TestAbstract {

	private static Config config = EnvFactory.getInstance().getConfig();
	private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");

	@ParameterizedTest
	@CsvFileSource(resources = "/test_data/sortByScenarios.csv", delimiter = '|')
	void sortByScenarios(ArgumentsAccessor accessor) {

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

	
	@ParameterizedTest
	@CsvFileSource(resources = "/test_data/searchBarScenarios.csv", delimiter = '|')
	void searchBarScenarios(ArgumentsAccessor argumentsAccessor) {
		String searcBarText = argumentsAccessor.getString(0);
		String sectionFilter = argumentsAccessor.getString(1);
		String sectionFilterItem = argumentsAccessor.getString(2);
		String productIndex = argumentsAccessor.getString(3);
		String assertionText = argumentsAccessor.getString(4);
		
		SearchBar searchBar = new SearchBar(getDriver());
		Product product = new Product(getDriver());

		searchBar.open(HOME_PAGE_URL);
		searchBar.type(searcBarText);
		searchBar.pressSearchButton();

		product.selectRefinement(sectionFilter, sectionFilterItem);
		product.selectProductByIndex(productIndex);
		product.switchWindowByIndex(1);
		assertTrue(product.assertion(assertionText));
	}
}
