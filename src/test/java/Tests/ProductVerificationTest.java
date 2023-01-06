package Tests;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductVerificationTest {

    private static WebDriver driver;

    public static void main(String[] args) {

        // Using WebDriverManager to automate driver management
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //Launch Browser
        BrowserLaunch bl = new BrowserLaunch(driver);
        bl.navigateToPage();

        //Search for a product
        SearchTab st = new SearchTab(driver, "stainless work table");
        //  SearchTab st = new SearchTab(driver, "stainless table");
        st.searchBar();

        //Verify the title of each product from all search results for relevant word
        ProductTitleCheck ptc = new ProductTitleCheck(driver, "stainless work table", "Table");
        // ProductTitleCheck ptc = new ProductTitleCheck(driver, "stainless table", "Table");
        ptc.searchTable();

        //Add last Item from search results
        AddLastItemToCart ac = new AddLastItemToCart(driver);
        ac.addToCart();

        //Empty Cart
        EmptyCart ec = new EmptyCart(driver);
        ec.emptyTheCart();

        //Close browser
        driver.quit();


    }
}
