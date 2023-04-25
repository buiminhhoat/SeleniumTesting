package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Shopee homepage
        driver.get("https://shopee.vn/");

        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Find the search box element by its name attribute
        WebElement searchBox = driver.findElement(By.className("shopee-searchbar-input__input"));

        // Enter a search term into the search box
        searchBox.sendKeys("Iphone 14");

        // Click the search button
        WebElement searchButton = driver.findElement(By.className("btn-solid-primary"));
        searchButton.click();

        // Wait for the search results to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Get the number of search results
        WebElement searchResultCount = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div/div/div[2]/div[2]/div[2]/div[1]"));
        String resultText = searchResultCount.getText();
        System.out.println(resultText);

        // Close the browser window
        driver.quit();
    }
}