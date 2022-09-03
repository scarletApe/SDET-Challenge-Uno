package mx.com.microsoft.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MicrosoftStorePages {

	WebDriver driver;
	By windowsLink = By.id("shellmenu_2");
	By searchButton = By.id("search");
	By searchField = By.id("cli_shellHeaderSearchInput");
	By comprarItem = By.xpath("//span[contains(.,'Comprar ahora')]");
	By todoMicrosoftLink = By.xpath("//*[@id=\"uhf-c-nav\"]/ul/li/div/button");
	By aplicacionesLink = By.xpath("//*[@id=\"uhf-c-nav\"]/ul/li/div/ul/li[2]/ul/li[1]");
	By productivityItems = By.xpath("//*[@id=\"navigationbar-Productivity\"]/div");
	By itemsForSale = By.xpath("//*[@id=\"all-products-listall-list-container\"]/div");
	By priceOnItem = By.xpath("//*[@id=\"main\"]/div/div/div[1]/div[1]/div[2]/div/span");

	public MicrosoftStorePages(WebDriver driver) {
		this.driver = driver;
	}

	public void goToMicrosoftStore(String url) {
		driver.get(url);
	}

	public void clickWindowsLink() {
		driver.findElement(windowsLink).click();
		sleep(1);
	}

	public void clickSearchButton() {
		driver.findElement(searchButton).click();
		sleep(1);
	}

	public void inputToSearchField(String s) {
		driver.findElement(searchField).sendKeys(s);
		sleep(1);
	}

	public void clickItemWithComprar() {
		driver.findElement(comprarItem).click();
		sleep(1);
	}

	public void clickTodoMicrosoft() {
		driver.findElement(todoMicrosoftLink).click();
		sleep(1);
	}

	public void clickAplicacionesLink() {
		driver.findElement(aplicacionesLink).click();
		sleep(1);
	}

	public void clickProductivityItems() {
		driver.findElement(productivityItems).click();
		sleep(1);
	}

	public void printNumberOfItemsAndTitles() {
		WebElement grid = driver.findElement(itemsForSale);
		List<WebElement> apps = grid.findElements(By.xpath("*"));
		int count = apps.size();
		System.out.println("Number of apps in page: " + (count - 1));
		System.out.println("Title of apps for sale: ");
		for (WebElement a : apps) {
			try {
				WebElement i = a.findElement(By.cssSelector("div > a > span.title-span"));
				System.out.println(i.getText());
			} catch (Exception e) {
			}
		}
	}

	public void findNonFreeItemStoreValueGoToItemAndCompare() {
		WebElement grid = driver.findElement(itemsForSale);
		List<WebElement> apps = grid.findElements(By.xpath("*"));
		String price = null;
		WebElement nonFreeItem = null;
		for (WebElement a : apps) {
			try {
				WebElement i = a.findElement(By.cssSelector("div > a > div.price > span"));
				// System.out.println(i.getText());
				if (containsDigits(i.getText())) {
					price = i.getText();
					nonFreeItem = a;
					System.out.println("Stored price=" + price);
					break;
				}
			} catch (Exception e) {
			}
		}
		sleep(1);
		nonFreeItem.click();
		sleep(2);
		WebElement priceOnItemPage = driver.findElement(priceOnItem);
		System.out.println("priceOnItemPage=" + priceOnItemPage.getText());
		Assert.assertEquals(priceOnItemPage.getText().contains(price), true);
	}

	/**
	 * This method check to see if the string has a digit, for checking prices
	 * embedded in a text.
	 * 
	 * @param s String to check
	 * @return
	 */
	private boolean containsDigits(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	private void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
