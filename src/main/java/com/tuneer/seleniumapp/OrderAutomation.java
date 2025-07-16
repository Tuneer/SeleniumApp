package com.tuneer.seleniumapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gdknmac
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class OrderAutomation {
 
    public void runAutomation(){
        //String path = "/Users/itteam2/Downloads/chromedriver-mac-arm64/chromedriver";
        String path = "/Users/gdknmac/Downloads/chromedriver-mac-arm64_2/chromedriver";

        // Set the path to your WebDriver executable (e.g., ChromeDriver)
        // You might need to change this path based on where you saved your chromedriver.exe
        System.setProperty("webdriver.chrome.driver", path);

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10-second wait time

        try {
            driver.get("http://3.135.219.244:2068/Orders");

            // Wait for the body element to be present, indicating the page has loaded
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            // --- Simulate getting item names from a database ---
            // In a real application, you would fetch these from your actual database
            List<String> itemNamesFromDatabase = Arrays.asList(
                    "BEEF LEAN"
            );
            // ----------------------------------------------------

            for (String itemName : itemNamesFromDatabase) {
                System.out.println("Processing item: " + itemName);
                try {
                    // STEP 1: Locate the element that contains the item name.
                    // IMPORTANT: You MUST adjust the tag name (e.g., 'div', 'h3', 'span')
                    // based on the actual HTML structure of the item name on the website.
                    // Use browser developer tools (F12) to inspect the element.
                    String itemNameXPath = String.format("//*[text()='%s']", itemName);
                    WebElement itemNameElement = wait.until(
                            ExpectedConditions.presenceOfElementLocated(By.xpath(itemNameXPath))
                    );
                    System.out.println("  - Found item name element for: " + itemName);

                    // STEP 2: Locate the "Add" or "Select" button associated with this item.
                    // This is the MOST CRUCIAL part. The XPath here depends entirely on
                    // how the "Add" button is structured relative to the item name element.
                    // You will need to inspect the HTML to create the correct XPath.

                    // COMMON SCENARIO EXAMPLES (choose or adapt based on your inspection):

                    // SCENARIO A: "Add" button is a direct sibling of the item name element.
                    // Example HTML: <div>Chicken 65</div> <button>Add</button>
                    // String addButtonXPath = itemNameXPath + "/following-sibling::button[contains(text(), 'Add')]";

                    // SCENARIO B: "Add" button is a descendant within the same parent container
                    // as the item name.
                    // Example HTML: <div class="item-card"><div>Chicken 65</div><button>Add</button></div>
                    // In this case, find the common parent first, then find the button within it.
                    // String commonParentXPath = itemNameXPath + "/ancestor::div[1]"; // Adjust ancestor level if needed
                    // WebElement commonParent = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(commonParentXPath)));
                    // WebElement addButton = commonParent.findElement(By.xpath(".//button[contains(text(), 'Add')]"));

                    // SCENARIO C: A more robust approach - find a common container that *contains* the item name
                    // and then look for the 'Add' button within that container.
                    // This is often good if item names and buttons are within product "cards" or "rows".
                    // Replace 'div' with the actual tag for your item's card/container if different.
                    String itemCardXPath = String.format("//div[contains(., '%s')]", itemName); // Finds a div containing the item name text
                    WebElement itemCard = wait.until(
                            ExpectedConditions.presenceOfElementLocated(By.xpath(itemCardXPath))
                    );

                    // Now, find the 'Add' button *within* this specific itemCard.
                    // Adjust 'button' tag and 'Add' text if different (e.g., 'Add to Cart', 'Select')
                    WebElement addButton = itemCard.findElement(By.xpath(".//button[contains(text(), 'Add')]"));


                    // Ensure the button is clickable before interacting
                    wait.until(ExpectedConditions.elementToBeClickable(addButton));

                    System.out.println("  - Found 'Add' button for: " + itemName);
                    addButton.click();
                    System.out.println("  - Clicked 'Add' for: " + itemName);

                    // Optional: Add a small pause if needed between clicks, though not generally recommended
                    // Thread.sleep(500); // 0.5 seconds

                } catch (Exception e) {
                    System.err.println("  - ERROR: Could not find or click 'Add' button for " + itemName + ": " + e.getMessage());
                    // You might want to log the full stack trace for debugging: e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.err.println("An error occurred during automation: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Always close the browser when done
            if (driver != null) {
                driver.quit();
            }
        }
    }
}


