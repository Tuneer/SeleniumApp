/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tuneer.seleniumapp;

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
import org.openqa.selenium.NoSuchElementException; // Import for better error handling

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;

public class OrderAutomationWithCategories {

   
//    public void runAutomation(){
//         String path = "/Users/gdknmac/Downloads/chromedriver-mac-arm64_2/chromedriver";
//        
//        System.setProperty("webdriver.chrome.driver", path); // SET YOUR DRIVER PATH
//
//        WebDriver driver = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10-second wait time
//
//        try {
//            driver.get("http://3.135.219.244:2068/Orders");
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
//
//            // --- Define your items, categorized by their main category ---
//            // This Map holds the category name as the key and a list of items within that category.
//            Map<String, List<String>> itemsToOrder = new HashMap<>();
////            itemsToOrder.put("Chicken", Arrays.asList(
////                "Chk Item1",
////                "GROUND CHICKEN BREAST"
////            ));
//            itemsToOrder.put("Beef", Arrays.asList(
//                "Bf Item 1",
//                "BEEF SHANKS"// Example item from Beef category (from your second screenshot)
//                // Add more beef items
//            ));
//            // Add other categories like "Goat", "Lamb", "Fish" and their respective items
//            // itemsToOrder.put("Goat", Arrays.asList("Goat Item 1"));
//
//
//            // Iterate through each category
//            for (Map.Entry<String, List<String>> entry : itemsToOrder.entrySet()) {
//                String categoryName = entry.getKey();
//                List<String> itemsInThisCategory = entry.getValue();
//
//                System.out.println("\n--- Processing Category: " + categoryName + " ---");
//
//                try {
//                    // STEP 1: Find and click the category header to open/expand it.
//                    // From your screenshots, categories like "Beef", "Chicken" appear to be <div> elements.
//                    // You might need to adjust the tag name if it's different (e.g., 'h3', 'li').
//                    // The XPath targets an element that contains the exact category name text.
//                    String categoryHeaderXPath = String.format("//div[text()='%s']", categoryName);
//                    WebElement categoryHeader = wait.until(
//                        ExpectedConditions.elementToBeClickable(By.xpath(categoryHeaderXPath))
//                    );
//
//                    // Check if the category is already open (optional, but good for robustness)
//                    // This often involves checking a class attribute (e.g., 'expanded', 'open') or an icon.
//                    // For now, we'll just click it, assuming clicking an open category won't break things.
//                    System.out.println("  - Clicking category header: " + categoryName);
//                    categoryHeader.click();
//
//                    // Add a small wait after clicking the category to allow items to load/become visible
//                    Thread.sleep(1000); // 1 second - adjust if items take longer to appear
//
//                    // STEP 2: Now that the category is open, iterate through its items and add them.
//                    for (String itemName : itemsInThisCategory) {
//                        System.out.println("  - Searching for item: " + itemName);
//                        try {
//                            // Find the element that *contains* the item name.
//                            // Use a more general XPath that looks for an element containing the text.
//                            // The `div` here is an assumption; you might need to change it based on your inspection.
//                            String itemContainerXPath = String.format("//div[contains(., '%s')]", itemName);
//                            // Ensure the container is visible after category expansion
//                            WebElement itemContainer = wait.until(
//                                ExpectedConditions.presenceOfElementLocated(By.xpath(itemContainerXPath))
//                            );
//
//                            // Find the "Add" button within this specific item's container.
//                            // Adjust 'button' tag and 'Add' text if different (e.g., 'Add to Cart', 'Select').
//                            // Based on your screenshot, the "Add" functionality might be directly linked to the row/card,
//                            // and there's a small circle with a plus sign for adding items.
//                            // Let's assume there's a button or an element with a plus icon.
//                            // You need to inspect the 'Add' button/icon HTML for each item.
//                            // If it's a circle with a plus, it might be an icon inside a clickable div/span.
//
//                            // Let's try to locate the 'Add' button/icon assuming it's a sibling or descendant
//                            // from the item name within its container.
//                            // Example for the '+' circle: It might be an icon (e.g., <i>, <span>) or a button.
//                            // Based on the screenshot, the "Add" button seems to be represented by a small circle
//                            // with a plus icon, usually at the right end of the item row.
//                            // You might need to find an element by its class or a more specific XPath.
//
//                            // Let's assume the clickable 'add' element is within the same container as the item name,
//                            // and it might have a specific class or be a button.
//                            // If it's a simple '+' icon:
//                            // WebElement addButton = itemContainer.findElement(By.xpath(".//span[contains(@class, 'add-icon') or text()='+']"));
//                            // OR if it's a button with specific text or class within the card:
//                            WebElement addButton = itemContainer.findElement(By.xpath(".//button[contains(text(),'Add') or contains(@class, 'add-item-button')] | .//div[contains(@class, 'add-button-icon')]"));
//                            // The above is a composite XPath, trying to find a button with 'Add' text,
//                            // or a button/div with a class like 'add-item-button' or 'add-button-icon'.
//                            // YOU WILL NEED TO FINE-TUNE THIS `addButton` XPath SIGNIFICANTLY.
//
//                            wait.until(ExpectedConditions.elementToBeClickable(addButton));
//                            addButton.click();
//                            System.out.println("    - Successfully added: " + itemName);
//
//                        } catch (NoSuchElementException e) {
//                            System.err.println("    - WARNING: 'Add' button/element not found for item '" + itemName + "' in category '" + categoryName + "': " + e.getMessage());
//                            // This can happen if an item isn't immediately visible or the XPath is wrong.
//                        } catch (Exception e) {
//                            System.err.println("    - ERROR adding item '" + itemName + "' in category '" + categoryName + "': " + e.getMessage());
//                            e.printStackTrace();
//                        }
//                    }
//
//                } catch (NoSuchElementException e) {
//                    System.err.println("  - WARNING: Category header '" + categoryName + "' not found or clickable: " + e.getMessage());
//                } catch (Exception e) {
//                    System.err.println("  - ERROR processing category '" + categoryName + "': " + e.getMessage());
//                    e.printStackTrace();
//                }
//            }
//
//        } catch (Exception e) {
//            System.err.println("An unhandled error occurred during automation: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            if (driver != null) {
//                driver.quit();
//            }
//        }
//    }
    
    
    
//    public void runAutomation() {
//        // Your existing chromedriver path
//        String path = "/Users/gdknmac/Downloads/chromedriver-mac-arm64_2/chromedriver";
//
//        System.setProperty("webdriver.chrome.driver", path); // SET YOUR DRIVER PATH
//
//        WebDriver driver = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time for robustness
//
//        try {
//            driver.get("http://3.135.219.244:2068/Orders");
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("accordion"))); // Wait for the main accordion container
//
//            // --- Define your items, categorized by their main category ---
//            // This Map holds the category name as the key and a list of items within that category.
//            // Populate this accurately from your database. Ensure exact capitalization and spelling.
//            Map<String, List<String>> itemsToOrder = new HashMap<>();
//            itemsToOrder.put("Beef", Arrays.asList(
//                "Bf Item 1"
//               
//            ));
//            itemsToOrder.put("Chicken", Arrays.asList(
//                "Chk item1"
//            ));
//            // Add other categories like "Goat", "Lamb", "Fish" and their respective items as needed
//            // itemsToOrder.put("Goat", Arrays.asList("Goat Item 1", "Goat Item 2"));
//
//
//            // Iterate through each category
//            for (Map.Entry<String, List<String>> entry : itemsToOrder.entrySet()) {
//                String categoryName = entry.getKey();
//                List<String> itemsInThisCategory = entry.getValue();
//
//                System.out.println("\n--- Processing Category: " + categoryName + " ---");
//
//                try {
//                    // STEP 1: Find and click the category header to open/expand it.
//                    // The clickable element is the <span> with class "card-header" and contains the category name.
//                    // We target the inner span with id="itemMainCat" as it directly holds the category text.
//                    String categoryHeaderXPath = String.format(
//                        "//span[@class='card-header border-i-primary-top menue-section-header panel-heading collapsed' or contains(@class, 'panel-heading') and not(contains(@class, 'collapsed'))]//span[@id='itemMainCat' and text()='%s']",
//                        categoryName
//                    );
//
//                    WebElement categoryHeaderSpan = wait.until(
//                        ExpectedConditions.elementToBeClickable(By.xpath(categoryHeaderXPath))
//                    );
//
//                    // Get the data-target ID from the parent 'card-header' span to wait for content
//                    String dataTargetId = categoryHeaderSpan.findElement(By.xpath("./ancestor::span[contains(@class, 'card-header')]")).getAttribute("data-target");
//                    // Remove '#' from the ID
//                    String targetContentId = dataTargetId.replace("#", "");
//
//                    // Check if the category is already expanded (optional, but good practice)
//                    // If the 'collapsed' class is present, it means it's closed and needs clicking.
//                    // We'll click it regardless, as clicking an open accordion header usually collapses it,
//                    // and clicking a collapsed one opens it.
//                    if (categoryHeaderSpan.findElement(By.xpath("./ancestor::span[contains(@class, 'card-header')]")).getAttribute("class").contains("collapsed")) {
//                         System.out.println("  - Category '" + categoryName + "' is collapsed. Clicking to expand.");
//                         categoryHeaderSpan.click();
//                    } else {
//                         System.out.println("  - Category '" + categoryName + "' is already expanded. Proceeding.");
//                    }
//
//
//                    // IMPORTANT: Wait for the content of the category to become visible.
//                    // The content is in a <span> with the ID from data-target.
//                    // Wait for it to NOT have the 'collapse' class (or to have 'show' if applicable)
//                    // and for the first item within it to be present.
//                    if (!itemsInThisCategory.isEmpty()) {
//                        String firstItemInCat = itemsInThisCategory.get(0);
//                        // XPath for the first item's name element within the expanded section
//                        String firstItemNameElementXPath = String.format(
//                            "//span[@id='%s']//div[@class='text-dark i-item-name']/strong[text()='%s']",
//                            targetContentId, firstItemInCat
//                        );
//                        try {
//                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(firstItemNameElementXPath)));
//                            System.out.println("  - Category '" + categoryName + "' appears to be opened (first item found).");
//                        } catch (TimeoutException toe) {
//                            System.err.println("  - WARNING: Timeout waiting for first item '" + firstItemInCat + "' in category '" + categoryName + "' to appear. It might not have expanded correctly.");
//                            // If the category didn't expand, we might skip processing its items
//                            continue; // Skip to next category
//                        }
//                    } else {
//                        // If no items are defined for this category, just a small pause after clicking
//                        Thread.sleep(500); // 0.5 seconds
//                    }
//
//                    // STEP 2: Now that the category is open, iterate through its items and add them.
//                    for (String itemName : itemsInThisCategory) {
//                        System.out.println("  - Searching for item: " + itemName);
//                        try {
//                            // Find the <li> element that represents the menu item.
//                            // It contains the item name within <div class="text-dark i-item-name"><strong>...</strong></div>
//                            String menuItemXPath = String.format(
//                                "//li[@class='menuItem border-i-secondary' and .//div[@class='text-dark i-item-name']/strong[text()='%s']]",
//                                itemName
//                            );
//                            WebElement menuItem = wait.until(
//                                ExpectedConditions.presenceOfElementLocated(By.xpath(menuItemXPath))
//                            );
//
//                            // STEP 3: Locate the "Add" button/icon within this specific menuItem.
//                            // Based on your screenshot, it's a plus icon.
//                            // The HTML for the category header shows `<i class="color-i-secondary fa more-less fa-plus-circle" aria-hidden="true"></i>`.
//                            // It's highly probable that a similar structure exists for the add button for each item.
//                            // We'll look for an <i> tag with 'fa-plus-circle' class within the menuItem.
//                            // YOU MUST VERIFY THIS SPECIFIC XPATH FOR THE ADD BUTTON BY INSPECTING IT ON THE LIVE SITE.
//                            String addButtonXPath = ".//i[contains(@class, 'fa-plus-circle')]";
//                            WebElement addButton = wait.until(
//                                ExpectedConditions.elementToBeClickable(menuItem.findElement(By.xpath(addButtonXPath)))
//                            );
//
//                            addButton.click();
//                            System.out.println("    - Successfully added: " + itemName);
//
//                        } catch (NoSuchElementException e) {
//                            System.err.println("    - WARNING: Item '" + itemName + "' or its 'Add' button not found in category '" + categoryName + "'. Error: " + e.getMessage());
//                        } catch (TimeoutException toe) {
//                            System.err.println("    - WARNING: Timeout waiting for item '" + itemName + "' or its 'Add' button to be clickable in category '" + categoryName + "'. Error: " + toe.getMessage());
//                        } catch (Exception e) {
//                            System.err.println("    - ERROR adding item '" + itemName + "' in category '" + categoryName + "': " + e.getMessage());
//                            e.printStackTrace();
//                        }
//                    }
//
//                } catch (NoSuchElementException e) {
//                    System.err.println("  - WARNING: Category header '" + categoryName + "' not found or clickable. Error: " + e.getMessage());
//                } catch (TimeoutException toe) {
//                    System.err.println("  - WARNING: Timeout waiting for category header '" + categoryName + "' to be clickable. Error: " + toe.getMessage());
//                } catch (Exception e) {
//                    System.err.println("An unexpected error occurred while processing category '" + categoryName + "': " + e.getMessage());
//                    e.printStackTrace();
//                }
//            }
//
//        } catch (Exception e) {
//            System.err.println("An unhandled error occurred during overall automation: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            // Always close the browser when done
//            if (driver != null) {
//                driver.quit();
//            }
//        }
//    }
    
    
    
//    public void runAutomation() {
//        // Your existing chromedriver path
//        String path = "/Users/gdknmac/Downloads/chromedriver-mac-arm64_2/chromedriver";
//
//        System.setProperty("webdriver.chrome.driver", path); // SET YOUR DRIVER PATH
//
//        WebDriver driver = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8)); // Increased wait time for robustness
//
//        try {
//            driver.get("http://3.135.219.244:2068/Orders");
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("accordion"))); // Wait for the main accordion container
//
//            // --- Define your items, categorized by their main category ---
//            // This Map holds the category name as the key and a list of items within that category.
//            // For each item, you can optionally specify quantity and special instructions.
//            // Example: "Item Name", quantity, "special instructions"
//            // If you don't need instructions, pass an empty string or null.
//            // For simplicity, I'm modifying itemsToOrder to hold a Map<String, String[]>
//            // where String[] is {itemName, quantity, specialInstructions}
//            Map<String, List<String[]>> itemsToOrder = new HashMap<>();
////            itemsToOrder.put("Beef", Arrays.asList(
////                new String[]{"Bf Item 1", "2", "Cut into cubes"}, // Order 2 lbs of Bf Item 1
////                new String[]{"BEEF LEAN NO FAT", "1", ""}, // Order 1 lb of BEEF LEAN NO FAT
////                new String[]{"BEEF SHANK", "3", "Please make it thin slices"} // Example with instructions
////                // Add more beef items as {itemName, quantity, instructions}
////            ));
//            itemsToOrder.put("Chicken", Arrays.asList(
//                new String[]{"Chk item1", "1", ""},
//                new String[]{"GROUND CHICKEN BREAST", "1", "Half pound please"} // Example with decimal quantity
//                // Add more chicken items
//            ));
//            // Add other categories like "Goat", "Lamb", "Fish" and their respective items
//            // itemsToOrder.put("Goat", Arrays.asList(new String[]{"Goat Item 1", "1", ""}));
//
//
//            // Iterate through each category
//            for (Map.Entry<String, List<String[]>> entry : itemsToOrder.entrySet()) {
//                String categoryName = entry.getKey();
//                List<String[]> itemsInThisCategory = entry.getValue();
//
//                System.out.println("\n--- Processing Category: " + categoryName + " ---");
//
//                try {
//                    // STEP 1: Find and click the category header to open/expand it.
//                    String categoryHeaderXPath = String.format(
//                        "//span[@class='card-header border-i-primary-top menue-section-header panel-heading collapsed' or contains(@class, 'panel-heading') and not(contains(@class, 'collapsed'))]//span[@id='itemMainCat' and text()='%s']",
//                        categoryName
//                    );
//
//                    WebElement categoryHeaderSpan = wait.until(
//                        ExpectedConditions.elementToBeClickable(By.xpath(categoryHeaderXPath))
//                    );
//
//                    String dataTargetId = categoryHeaderSpan.findElement(By.xpath("./ancestor::span[contains(@class, 'card-header')]")).getAttribute("data-target");
//                    String targetContentId = dataTargetId.replace("#", "");
//
//                    if (categoryHeaderSpan.findElement(By.xpath("./ancestor::span[contains(@class, 'card-header')]")).getAttribute("class").contains("collapsed")) {
//                         System.out.println("  - Category '" + categoryName + "' is collapsed. Clicking to expand.");
//                         categoryHeaderSpan.click();
//                    } else {
//                         System.out.println("  - Category '" + categoryName + "' is already expanded. Proceeding.");
//                    }
//
//                    if (!itemsInThisCategory.isEmpty()) {
//                        String firstItemNameInCat = itemsInThisCategory.get(0)[0]; // Get the name of the first item
//                        String firstItemNameElementXPath = String.format(
//                            "//span[@id='%s']//div[@class='text-dark i-item-name']/strong[text()='%s']",
//                            targetContentId, firstItemNameInCat
//                        );
//                        try {
//                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(firstItemNameElementXPath)));
//                            System.out.println("  - Category '" + categoryName + "' appears to be opened (first item found).");
//                        } catch (TimeoutException toe) {
//                            System.err.println("  - WARNING: Timeout waiting for first item '" + firstItemNameInCat + "' in category '" + categoryName + "' to appear. It might not have expanded correctly.");
//                            continue;
//                        }
//                    } else {
//                        Thread.sleep(500);
//                    }
//
//                    Thread.sleep(200);
//                    
//                    // STEP 2: Now that the category is open, iterate through its items and interact with the modal.
//                    for (String[] itemDetails : itemsInThisCategory) {
//                        String itemName = itemDetails[0];
//                        String quantity = itemDetails[1];
//                        String specialInstructions = itemDetails[2];
//
//                        System.out.println("  - Searching for item: " + itemName);
//                        try {
//                            // Find the <li> element that represents the menu item.
//                            // The entire li or the element containing the name is likely clickable.
//                            // We'll try clicking the <li> itself.
//                            String menuItemXPath = String.format(
//                                "//li[@class='menuItem border-i-secondary' and .//div[@class='text-dark i-item-name']/strong[text()='%s']]",
//                                itemName
//                            );
//                            WebElement menuItem = wait.until(
//                                ExpectedConditions.elementToBeClickable(By.xpath(menuItemXPath))
//                            );
//
//                            System.out.println("    - Clicking item card for: " + itemName + " to open dialog.");
//                            menuItem.click();
//
//                             Thread.sleep(1000);
//                             
//                            // STEP 3: Interact with the modal dialog
//                            // Wait for the modal to appear (e.g., by waiting for its title or "Add to Cart" button)
//                            WebElement addToCartModalButton = wait.until(
//                                ExpectedConditions.elementToBeClickable(By.id("addToCart"))
//                            );
//                            System.out.println("    - Modal dialog opened for: " + itemName);
//
//                             Thread.sleep(1000);
//                            
//                            // Set Quantity
//                            WebElement quantityInput = wait.until(
//                                ExpectedConditions.visibilityOfElementLocated(By.id("cartPopUpQty"))
//                            );
//                            quantityInput.clear(); // Clear existing value (usually '1')
//                            quantityInput.sendKeys(quantity);
//                            System.out.println("    - Set quantity to: " + quantity);
//
//                             Thread.sleep(1000);
//                            
//                            // Set Special Instructions (if provided)
//                            if (specialInstructions != null && !specialInstructions.isEmpty()) {
//                                WebElement instructionsTextarea = driver.findElement(By.id("SpecialInstructions"));
//                                instructionsTextarea.clear(); // Clear any pre-filled text
//                                instructionsTextarea.sendKeys(specialInstructions);
//                                System.out.println("    - Added special instructions: '" + specialInstructions + "'");
//                            }
//
//                            // Click "Add to Cart" button inside the modal
//                            addToCartModalButton.click();
//                            System.out.println("    - Clicked 'Add to Cart' for: " + itemName);
//
//                            // Wait for the modal to disappear
//                            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-dialog")));
//                            System.out.println("    - Modal dialog closed.");
//                            
//                             Thread.sleep(1000);
//
//                        } catch (NoSuchElementException e) {
//                            System.err.println("    - WARNING: Item '" + itemName + "' or modal element not found in category '" + categoryName + "'. Error: " + e.getMessage());
//                        } catch (TimeoutException toe) {
//                            System.err.println("    - WARNING: Timeout waiting for item '" + itemName + "' or modal elements in category '" + categoryName + "'. Error: " + toe.getMessage());
//                        } catch (Exception e) {
//                            System.err.println("    - ERROR processing item '" + itemName + "' in category '" + categoryName + "': " + e.getMessage());
//                            e.printStackTrace();
//                        }
//                    }
//
//                } catch (NoSuchElementException e) {
//                    System.err.println("  - WARNING: Category header '" + categoryName + "' not found or clickable. Error: " + e.getMessage());
//                } catch (TimeoutException toe) {
//                    System.err.println("  - WARNING: Timeout waiting for category header '" + categoryName + "' to be clickable. Error: " + toe.getMessage());
//                } catch (InterruptedException e) {
//                    System.err.println("An unexpected error occurred while processing category '" + categoryName + "': " + e.getMessage());
//                    e.printStackTrace();
//                }
//            }
//
//        } catch (Exception e) {
//            System.err.println("An unhandled error occurred during overall automation: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            // Always close the browser when done
////            if (driver != null) {
////                driver.quit();
////            }
//        }
//    }
    
    
    
    
    
     // Common locators for search dialog (PLACEHOLDER - YOU MUST VERIFY THESE ON THE LIVE SITE)
    // REMINDER: Update these based on your actual website's HTML for the search dialog.
    private static final By SEARCH_ITEM_BUTTON_BY = By.cssSelector("button.searchItem[title='Search Item']");
    private static final By SEARCH_DIALOG_BY = By.id("searchModal"); // Example: common modal ID
    private static final By SEARCH_INPUT_FIELD_BY = By.id("txtItemSearch"); // Example: common search input ID
    private static final By SEARCH_RESULTS_CONTAINER_BY = By.id("searchResultsContainer"); // Example: container for results
    // Example: XPath format for the item name within a search result item.
    // This assumes the search result item is clickable and contains a strong tag with the name.
    private static final By SEARCH_RESULT_ITEM_NAME_XPATH_FORMAT = By.xpath(".//strong[text()='%s']");

    public void runAutomation() {
        String path = "/Users/gdknmac/Downloads/chromedriver-mac-arm64_2/chromedriver"; // SET YOUR DRIVER PATH
        System.setProperty("webdriver.chrome.driver", path);

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Generous wait time

        try {
            driver.get("http://3.135.219.244:2068/Orders");
            // Initial wait for the main content to be present
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("accordion")));
            System.out.println("Page loaded and accordion is present.");

            Map<String, List<String[]>> itemsToOrder = new HashMap<>();
            itemsToOrder.put("Beef", Arrays.asList(
                new String[]{"Bf Item 1", "2", "Cut into cubes"},
                new String[]{"BEEF LEAN NO FAT", "1", ""},
                new String[]{"BEEF SHANK", "3", "Please make it thin slices"}
            ));
            itemsToOrder.put("Chicken", Arrays.asList(
                new String[]{"Chk item1", "1", ""},
                new String[]{"GROUND CHICKEN BREAST", "0.5", "Half pound please"}
            ));


            // Locators for potential obstructing elements
            By obstructingLogo = By.xpath("//img[@src='/MerchantImages/ZemZem/Logo.png']");
            By obstructingNavbar = By.xpath("//nav[contains(@class, 'navbar') and contains(@class, 'navbar-expand-lg')]");


            // Iterate through each category
            for (Map.Entry<String, List<String[]>> entry : itemsToOrder.entrySet()) {
                String categoryName = entry.getKey();
                List<String[]> itemsInThisCategory = entry.getValue();

                System.out.println("\n--- Processing Category: " + categoryName + " ---");

                try {
                    String categoryHeaderXPath = String.format(
                        "//span[@class='card-header border-i-primary-top menue-section-header panel-heading collapsed' or contains(@class, 'panel-heading') and not(contains(@class, 'collapsed'))]//span[@id='itemMainCat' and text()='%s']",
                        categoryName
                    );

                    WebElement categoryHeaderSpan = wait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath(categoryHeaderXPath))
                    );

                    String dataTargetId = categoryHeaderSpan.findElement(By.xpath("./ancestor::span[contains(@class, 'card-header')]")).getAttribute("data-target");
                    String targetContentId = dataTargetId.replace("#", "");

                    if (categoryHeaderSpan.findElement(By.xpath("./ancestor::span[contains(@class, 'card-header')]")).getAttribute("class").contains("collapsed")) {
                         System.out.println("  - Category '" + categoryName + "' is collapsed. Clicking to expand.");
                         categoryHeaderSpan.click();
                         // Add a small wait after clicking to allow UI to settle, especially for accordions
                         try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                    } else {
                         System.out.println("  - Category '" + categoryName + "' is already expanded. Proceeding.");
                    }

                    if (!itemsInThisCategory.isEmpty()) {
                        String firstItemNameInCat = itemsInThisCategory.get(0)[0];
                        String firstItemNameElementXPath = String.format(
                            "//span[@id='%s']//div[@class='text-dark i-item-name']/strong[text()='%s']",
                            targetContentId, firstItemNameInCat
                        );
                        // Wait for at least the first item in the category to be present after expansion
                        try {
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(firstItemNameElementXPath)));
                            System.out.println("  - Category '" + categoryName + "' appears to be opened (first item found).");
                        } catch (TimeoutException toe) {
                            System.err.println("  - WARNING: Timeout waiting for first item '" + firstItemNameInCat + "' in category '" + categoryName + "' to appear. It might not have expanded correctly. Will rely on fallback if needed.");
                        }
                    } else {
                        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                    }

                    // STEP 2: Iterate through items and try to add them
                    for (String[] itemDetails : itemsInThisCategory) {
                        String itemName = itemDetails[0];
                        String quantity = itemDetails[1];
                        String specialInstructions = itemDetails[2];

                        System.out.println("  - Attempting to add item: " + itemName);
                        boolean itemAddedSuccessfully = false;

                        try {
                            // --- Try accordion method first ---
                            String itemNameElementXPath = String.format(
                                "//li[@class='menuItem border-i-secondary']//div[@class='text-dark i-item-name']/strong[text()='%s']",
                                itemName
                            );
                            WebElement itemNameElement = wait.until(
                                ExpectedConditions.elementToBeClickable(By.xpath(itemNameElementXPath))
                            );

                            System.out.println("    - Waiting for obstructing elements (logo, navbar) to become invisible (Accordion path)...");
                            try {
                                wait.until(ExpectedConditions.invisibilityOfElementLocated(obstructingLogo));
                                System.out.println("    - Logo is now invisible (Accordion path).");
                            } catch (TimeoutException e) {
                                System.out.println("    - Warning: Logo did not become invisible within timeout. (Accordion path)");
                            }
                            try {
                                wait.until(ExpectedConditions.invisibilityOfElementLocated(obstructingNavbar));
                                System.out.println("    - Navbar is now invisible (Accordion path).");
                            } catch (TimeoutException e) {
                                System.out.println("    - Warning: Navbar did not become invisible within timeout. (Accordion path)");
                            }
                            System.out.println("    - Proceeding to click item name via accordion.");

                            // Small sleep as a last resort before clicking to ensure rendering is complete
                            try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                            itemNameElement.click();

                            // Interact with the modal dialog (common for both methods)
                            interactWithItemDetailsModal(driver, wait, itemName, quantity, specialInstructions);
                            itemAddedSuccessfully = true;
                            System.out.println("    - Successfully added: " + itemName + " via accordion.");

                        } catch (NoSuchElementException | TimeoutException e) {
                            System.err.println("    - Failed to add '" + itemName + "' via accordion (" + e.getClass().getSimpleName() + "). Attempting fallback via search dialog.");
                            try {
                                // --- Fallback to search dialog method ---
                                searchAndAddItem(driver, wait, itemName, quantity, specialInstructions);
                                itemAddedSuccessfully = true;
                                System.out.println("    - Successfully added: " + itemName + " via search dialog.");
                            } catch (Exception fallbackE) {
                                System.err.println("    - ERROR: Failed to add '" + itemName + "' even via search dialog: " + fallbackE.getMessage());
                                fallbackE.printStackTrace();
                            }
                        } catch (Exception e) {
                            System.err.println("    - UNEXPECTED ERROR trying to add '" + itemName + "' via accordion: " + e.getMessage());
                            e.printStackTrace();
                        }

                        if (!itemAddedSuccessfully) {
                            System.err.println("    - Item '" + itemName + "' could not be added by any method. Skipping this item.");
                        }
                    } // End of inner for loop (items in category)

                } catch (NoSuchElementException e) {
                    System.err.println("  - WARNING: Category header '" + categoryName + "' not found or clickable: " + e.getMessage());
                } catch (TimeoutException toe) {
                    System.err.println("  - WARNING: Timeout waiting for category header '" + categoryName + "' to be clickable: " + toe.getMessage());
                } catch (Exception e) {
                    System.err.println("An unexpected error occurred while processing category '" + categoryName + "': " + e.getMessage());
                    e.printStackTrace();
                }
            } // End of outer for loop (categories)

            // --- Click the "Pickup" button after all items are processed ---
            System.out.println("\n--- All items processed. Attempting to click 'Pickup' button ---");

            By pickupButtonBy = By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/div/div/label[2]");

            try {
                WebElement pickupButton = wait.until(ExpectedConditions.elementToBeClickable(pickupButtonBy));
                // Small sleep before clicking pickup button for stability
                try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                // Using JavaScript click for robustness
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pickupButton);
                System.out.println("--- Successfully clicked 'Pickup' button. ---");
            } catch (TimeoutException e) {
                System.err.println("--- ERROR: 'Pickup' button not found or clickable within timeout. ---");
                e.printStackTrace();
            } catch (NoSuchElementException e) {
                System.err.println("--- ERROR: 'Pickup' button element not found. ---");
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("--- An unexpected error occurred while clicking 'Pickup' button: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.err.println("An unhandled error occurred during overall automation: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    /**
     * Handles interaction with the item details modal (quantity, instructions, Add to Cart).
     * This logic is reused by both accordion and search methods.
     */
    private void interactWithItemDetailsModal(WebDriver driver, WebDriverWait wait,
                                              String itemName, String quantity, String specialInstructions) {
        WebElement addToCartModalButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("addToCart"))
        );
        System.out.println("      - Item details modal opened for: " + itemName);

        WebElement quantityInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("cartPopUpQty"))
        );
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
        System.out.println("      - Set quantity to: " + quantity);

        if (specialInstructions != null && !specialInstructions.isEmpty()) {
            WebElement instructionsTextarea = driver.findElement(By.id("SpecialInstructions"));
            instructionsTextarea.clear();
            instructionsTextarea.sendKeys(specialInstructions);
            System.out.println("      - Added special instructions: '" + specialInstructions + "'");
        }

        addToCartModalButton.click();
        System.out.println("      - Clicked 'Add to Cart' for: " + itemName);

        // Wait for the modal to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-dialog")));
        System.out.println("      - Item details modal closed.");
    }

    /**
     * Fallback method to search for an item via the search dialog and add it.
     * YOU MUST VERIFY THE PLACEHOLDER LOCATORS FOR THE SEARCH DIALOG.
     */
    private void searchAndAddItem(WebDriver driver, WebDriverWait wait,
                                  String itemName, String quantity, String specialInstructions) {
        System.out.println("    - Entering searchAndAddItem method for: " + itemName);

        // 1. Locate and click the "Search Item" button
        WebElement searchItemButton = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_ITEM_BUTTON_BY));
        searchItemButton.click();
        System.out.println("      - Clicked 'Search Item' button.");

        // 2. Wait for the search dialog to appear
        WebElement searchDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_DIALOG_BY));
        System.out.println("      - Search dialog opened.");

        // 3. Locate the search input field and enter item name
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT_FIELD_BY));
        searchInput.clear();
        searchInput.sendKeys(itemName);
        System.out.println("      - Entered '" + itemName + "' into search field.");

        // Give a brief moment for search results to appear (if AJAX-loaded)
        // This is a common place for a Thread.sleep if results load asynchronously without a clear 'loading' indicator.
        try {
            Thread.sleep(1000); // Adjust as needed. Consider replacing with a more specific ExpectedCondition
                               // if you can identify a common element for search results.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 4. Locate the search results container and then the specific item in the search results
        WebElement searchResultsContainer = wait.until(
            ExpectedConditions.visibilityOfElementLocated(SEARCH_RESULTS_CONTAINER_BY));

        // Construct XPath for the specific item in results relative to the container
        By searchResultItemNameElementBy = By.xpath(String.format(SEARCH_RESULT_ITEM_NAME_XPATH_FORMAT.toString(), itemName));

        WebElement searchResultItemNameElement = wait.until(
            ExpectedConditions.elementToBeClickable(searchResultsContainer.findElement(searchResultItemNameElementBy))
        );
        System.out.println("      - Located item '" + itemName + "' in search results.");

        // Small sleep before clicking the search result item for stability
        try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        searchResultItemNameElement.click();
        System.out.println("      - Clicked item '" + itemName + "' in search results.");

        // 5. Reuse the existing item details modal interaction logic
        // This assumes clicking the search result item opens the SAME item details modal.
        interactWithItemDetailsModal(driver, wait, itemName, quantity, specialInstructions);

        // OPTIONAL: If the search dialog remains open after selecting an item, you might need to close it.
        // For example, if there's a close button on the search dialog:
        // By closeSearchDialogButton = By.xpath("//div[@id='searchModal']//button[@data-dismiss='modal']");
        // if (driver.findElements(closeSearchDialogButton).size() > 0 && driver.findElement(closeSearchDialogButton).isDisplayed()) {
        //     driver.findElement(closeSearchDialogButton).click();
        //     wait.until(ExpectedConditions.invisibilityOfElementLocated(SEARCH_DIALOG_BY));
        //     System.out.println("      - Search dialog explicitly closed after item selection.");
        // }
    }
    
    
    
    
}