import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ShopeeFunctionalTest {
    WebDriver driver;
    @Before
    public void setup() {
        // Tạo một instance của Logger cho Selenium WebDriver
        Logger logger = Logger.getLogger("org.openqa.selenium");
        // Thiết lập mức độ logging
        logger.setLevel(Level.OFF);

        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
//        options.addArguments("--headless", "--whitelisted-ips=''");
        driver = new ChromeDriver(options);

        // Navigate to the Shopee homepage
        driver.get("https://shopee.vn/");

        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @Test
    public void testSearchbar() {
        // Find the search box element by its name attribute
        WebElement searchBox = driver.findElement(By.className("shopee-searchbar-input__input"));
        // Enter a search term into the search box
        searchBox.sendKeys("Iphone 14");

        WebElement input = driver.findElement(By.className("shopee-searchbar-input__input"));

        String expected = "Iphone 14";
        String actual = input.getAttribute("value");
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchButton() {
        // Find the search box element by its name attribute
        WebElement searchBox = driver.findElement(By.className("shopee-searchbar-input__input"));
        // Enter a search term into the search box
        searchBox.sendKeys("Iphone 14");

        WebElement searchButton = driver.findElement(By.className("btn-solid-primary"));
        searchButton.click();
        String expected = "https://shopee.vn/search?keyword=iphone%2014";
        String actual = driver.getCurrentUrl();
        assertEquals(expected, actual);
    }

    @Test
    public void testLoginFunction() {
        String user = "", pass = "";
        try {
            File myObj = new File("src/test/java/accountShopee.txt");
            Scanner myReader = new Scanner(myObj);
            user = myReader.nextLine();
            pass = myReader.nextLine();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Navigate to Shopee login page
        driver.get("https://shopee.vn/buyer/login");

        // Input username and password
        WebElement username = driver.findElement(By.xpath("//body/div[@id='main']/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/div[1]/input[1]"));
        WebElement password = driver.findElement(By.xpath("//body/div[@id='main']/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[3]/div[1]/input[1]"));
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]"));

        username.sendKeys(user);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        password.sendKeys(pass);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loginButton.click();

        System.out.println(driver.getCurrentUrl());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify login success by checking the user profile button
        WebElement profile = driver.findElement(By.xpath("//header/div[1]/nav[1]/ul[1]/li[3]"));
        assertTrue(profile != null);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
