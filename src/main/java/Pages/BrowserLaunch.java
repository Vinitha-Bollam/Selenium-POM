package Pages;

import org.openqa.selenium.WebDriver;

public class BrowserLaunch {

    private static WebDriver driver;

    public BrowserLaunch(WebDriver driver) {
        this.driver = driver;
    }

    //Launching the browser and navigating to 'webstaurantstore'
    public void navigateToPage() {
        System.out.println("Navigating to webstaurantstore page");
        driver.get("https://www.webstaurantstore.com/");
        driver.manage().window().maximize();
    }
}
