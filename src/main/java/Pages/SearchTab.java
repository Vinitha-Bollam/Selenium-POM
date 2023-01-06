package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchTab {

    private final String searchFor;
    private WebDriver driver;
    By searchBox = By.id("searchval");
    By searchButton = By.cssSelector("#searchForm>div>button");

    public SearchTab(WebDriver driver, String searchFor) {
        this.driver = driver;
        this.searchFor = searchFor;
    }

    //Searching for product
    public void searchBar() {
        System.out.println("Searching for " + searchFor);
        driver.findElement(searchBox).sendKeys(searchFor);
        driver.findElement(searchButton).click();
    }


}
