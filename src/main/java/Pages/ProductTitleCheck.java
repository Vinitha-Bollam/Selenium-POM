package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductTitleCheck {


    private final String checkTitlehasWord;
    private final String searchFor;
    private WebDriver driver;
    static int wordtable = 0;
    static int Nowordtable = 0;
    static String ProductName = "";
    static int TotalNoOfProducts = 0;
    By AllPages = By.cssSelector("#paging>nav>ul>li");
    By NoOfProducts = By.id("details");
    By NextPage = By.cssSelector("#paging > nav > ul > li.inline-block.leading-4.align-top.rounded-r-md");

    public ProductTitleCheck(WebDriver driver, String searchFor, String checkTitlehasWord) {
        this.driver = driver;
        this.searchFor = searchFor;
        this.checkTitlehasWord = checkTitlehasWord;
    }

    public void searchTable() {
        /*Get count of all pages for the search to navigate to each page to ensure the relevant word is present
         on title for each product displayed */
        List<WebElement> TotalPages = driver.findElements(AllPages);
        int TotalSearchpages = TotalPages.size() + 1;
        for (int i = 1; i <= TotalPages.size() + 1; i++) {

            // Get the list of no of products on a page
            List<WebElement> ProductInfo = driver.findElements(NoOfProducts);

            //Count the no of products on each page to do a math and print summary
            int TotalProductsOnPage = ProductInfo.size();
            TotalNoOfProducts = TotalNoOfProducts + TotalProductsOnPage;

            //Get Product list to iterate and verify the word 'Table'
            for (WebElement ProductDetails : ProductInfo) {

                /*Search for word 'Table' in each Product title. Here is the catch, we can not use contains
                or other methods here because it matches with word 'Adjustable' which is part of title for some products.
                To handle false positives, we can use space at the front of
                table like " table" but we never know if we run into some other issue tomorrow.
                 Best way to handle this scenario is to use pattern matchers */
                //     if(ProductDescription.toLowerCase().contains(" table"))
                String ProductDescription = ProductDetails.getText();
                String Product = ProductDescription.toLowerCase();
                String SF = checkTitlehasWord.toLowerCase();
                Pattern p = Pattern.compile("\\b" + SF + "\\b");
                Matcher m = p.matcher(Product);
                boolean match = m.find();
                if (match) {
                    // Count the no of products with word 'table'
                    wordtable = wordtable + 1;
                } else {
                    // Count the no of products without word 'table' along with list of product names of the same
                    Nowordtable = Nowordtable + 1;
                    ProductName = ProductName.concat("ProductName " + " ---> " + ProductDescription + '\n');
                }
            }

            //Navigate to next page after verifying word 'table' as title on every product on a page
            driver.findElement(NextPage).click();
        }
        // Summarize, calculate and print the results for better visibility
        String Title = checkTitlehasWord.toUpperCase();
        System.out.println("<------------------------ Summary of Results ------------------------>");
        System.out.println("Total no of search pages when we search with " + searchFor + " --> " + TotalSearchpages);
        System.out.println("Total no of products displayed for " + searchFor + " --> " + TotalNoOfProducts + " from " + TotalSearchpages + " pages");
        System.out.println("Total no of product has the word " + Title + " in its title " + " --> " + wordtable);
        System.out.println("Total no of product without word " + Title + " in its title " + " --> " + Nowordtable);
        System.out.println("Here are the list of products without word " + Title + " in its title " + '\n' + ProductName);
    }
}
