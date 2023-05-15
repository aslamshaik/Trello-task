package Selenium;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Trello {
    public static void main(String[] args) throws InterruptedException {
       

        // Set the system property for chromedriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\windows\\Downloads\\chromedriver_win32\\chromedriver.exe");

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        // Open Trello URL
        driver.get("https://trello.com");

        // Find and click the "Log In" button
        driver.findElement(By.linkText("Log in")).click();

        // Fill in the login form and submit
        driver.findElement(By.id("user")).sendKeys("Testuserreward@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Test@1234");
        driver.findElement(By.id("login")).submit();

        // Wait for the boards page to load
        driver.findElement(By.linkText("Boards")).click();

        // Click on the "Create new board" button
        driver.findElement(By.xpath("//button[text()='Create new board']")).click();

        // Enter the board name and create the board
        driver.findElement(By.className("subtle-input")).sendKeys("New Board");
        driver.findElement(By.xpath("//button[text()='Create']")).click();

        // Click on the "Create new list" button to create List A
        driver.findElement(By.cssSelector("a[class='open-add-list']")).click();
        driver.findElement(By.cssSelector("input[class='list-name-input']")).sendKeys("List A");
        driver.findElement(By.cssSelector("input[class='primary mod-list-add-button js-save-edit']")).click();

        // Click on the "Create new list" button to create List B
        driver.findElement(By.cssSelector("a[class='open-add-list']")).click();
        driver.findElement(By.cssSelector("input[class='list-name-input']")).sendKeys("List B");
        driver.findElement(By.cssSelector("input[class='primary mod-list-add-button js-save-edit']")).click();

        // Add a card to List A
        driver.findElement(By.cssSelector("a[class='open-card-composer js-open-card-composer']")).click();
        driver.findElement(By.cssSelector("textarea[class='list-card-composer-textarea js-card-title']")).sendKeys("Card A");
        driver.findElement(By.cssSelector("input[class='primary confirm mod-compact js-add-card']")).click();

        // Find the created card element in List A
        WebElement cardElement = driver.findElement(By.cssSelector("div.list:nth-child(1) div.list-card"));

        // Find the target List B element
        WebElement targetListElement = driver.findElement(By.cssSelector("div.list:nth-child(2) div.list-cards"));

        // Get the initial position of the card
        Point initialPosition = cardElement.getLocation();
        int initialX = initialPosition.getX();
        int initialY = initialPosition.getY();
        System.out.println("Initial X Coordinate: " + initialX);
        System.out.println("Initial Y Coordinate: " + initialY);

        // Perform the drag and drop action to move the card to List B
        Actions actions = new Actions(driver);
        actions.dragAndDrop(cardElement, targetListElement).build().perform();

        // Get the final position of the card
        Point finalPosition = cardElement.getLocation();
        int finalX = finalPosition.getX();
        int finalY = finalPosition.getY();
        System.out.println("Final X Coordinate: " + finalX);
        System.out.println("Final Y Coordinate: " + finalY);

        // Logout from Trello
        driver.findElement(By.linkText("Log Out")).click();

        // Close the browser
        driver.quit();
    }
}
