package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EmptyCart {

    private static WebDriver driver;
    By EmptyCart = By.cssSelector(".cartItems>div>div>button");
    By EmptyCartNotification = By.cssSelector(".ReactModalPortal>div>div>div>footer>button:first-of-type");

    public EmptyCart(WebDriver driver) {
        this.driver = driver;
    }

    public void emptyTheCart() {
        try {
            //Empty Cart
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(EmptyCart)).click();
            driver.findElement(EmptyCartNotification).click();
            System.out.println("Empty Cart");
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
        }
    }

}
