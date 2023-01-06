package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;

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
            driver.findElement(EmptyCart).click();
            driver.findElement(EmptyCartNotification).click();
            System.out.println("Empty Cart");
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
        }
    }

}
