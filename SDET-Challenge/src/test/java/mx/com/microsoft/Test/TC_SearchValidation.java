package mx.com.microsoft.Test;

import java.util.HashMap;

import org.testng.annotations.Test;

import mx.com.microsoft.PageObjects.MicrosoftStorePages;
import mx.com.microsoft.Util.DataUtil;

public class TC_SearchValidation extends BaseTest {

	/**
	 * TC1 – Search validation
	 */
	@Test(dataProviderClass = DataUtil.class, dataProvider = "jsonDataProvider")
	public void searchValidation(HashMap<String, String> data) {
		// 1. Go to https://www.microsoft.com/es-mx/
		MicrosoftStorePages store = new MicrosoftStorePages(driver);
		store.goToMicrosoftStore(data.get("Microsoft URL"));

		// 2. Go to Windows
		store.clickWindowsLink();

		// 3. Go to Search
		store.clickSearchButton();

		// 4. Search for “Xbox” and click on "Comprar"
		store.inputToSearchField(data.get("Search Input"));
		store.clickItemWithComprar();

		// 5. Once in the result page you will see "Aplicaciones" click on it
		store.clickTodoMicrosoft();
		store.clickAplicacionesLink();
		store.clickProductivityItems();

		// 6. Count the elements on the first 3 pages and print the sum of elements
		// and all the titles
		store.printNumberOfItemsAndTitles();

		// 7. Go back to the first page and select the first NON-FREE option and STORE
		// the price for later comparison. In this page, you will see the price again,
		// compare first price vs current
		// prince and they should match
		store.findNonFreeItemStoreValueGoToItemAndCompare();

		// 10. Click on the "3 dot" button next to "Comprar" button and add the item to the CART. 
		// Microsoft online store no longer has a shopping cart

	}

}
