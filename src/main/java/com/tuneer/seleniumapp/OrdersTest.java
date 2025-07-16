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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.ElementClickInterceptedException;

public class OrdersTest {
    WebDriver driver;
    String path = "/Users/gdknmac/Downloads/chromedriver-mac-arm64_2/chromedriver";

    public void runTest() throws InterruptedException {
        // Set driver path and initialize
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        //List<UserLogin> userloginList = getUserLoginList();

        // Navigate to home page
        driver.get("http://3.135.219.244:2068/");
        System.out.println("Website loaded");
        // === LOGIN ===
        driver.findElement(By.id("navlogin")).click();

        Thread.sleep(500);
        System.out.println("Clicked on the Login Button for Login the user: ");
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='loginModal']/div/div/div[3]/a[2]"))).click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("User_mail")));
        emailField.sendKeys("vaibhavjsc@gmail.com");

        WebElement passwordField;
        passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("User_password")));
        passwordField.sendKeys("1234");

        System.out.println("User Logged IN with this details: vaibhavjsc@gmail.com");
        
        driver.findElement(By.id("loginbutton")).click();
        System.out.println("Finally Login button clicked.");
        
        Thread.sleep(1000);
        By orderNowButtonBy = By.cssSelector("a.hero-btn"); // Locate the 'Order Now' link by its class
        WebElement orderNowButton = wait.until(ExpectedConditions.elementToBeClickable(orderNowButtonBy));
        System.out.println("Found 'Order Now' button. Clicking it to go to Orders page.");
        orderNowButton.click();
        
        // === Navigate to Orders Page ===
        wait.until(ExpectedConditions.urlContains("Orders")); // Login done
        Thread.sleep(1000);
        driver.get("http://3.135.219.244:2068/Orders");

        // === SELECT CATEGORY AND ITEMS ===
       // String targetCategory = "Chicken"; // Category accordion name  BABY LAMB
        String[] targetCategories = {"Chicken", "Lamb"}; // Items to click
        
        for(String targetCategory : targetCategories){
                
              String[] itemNames = getItemsForCategory(targetCategory); // Items to click
             
              // Construct dynamic XPath
                String xpathCategory = "//span[@id='itemMainCat' and contains(normalize-space(), '" + targetCategory + "')]";

                // Find the span and click
                WebElement categorySpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathCategory)));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", categorySpan);
                categorySpan.click();

                 Thread.sleep(500); // Small wait for items to render
                
                 for (String itemName : itemNames) {
                    List<WebElement> menuItems = driver.findElements(By.cssSelector("li.menuItem.border-i-secondary"));

                    boolean itemClicked = false;

                    for (WebElement item : menuItems) {
                        try {
                            WebElement nameDiv = item.findElement(By.cssSelector(".text-dark.i-item-name strong"));
                            String foundName = nameDiv.getText().trim();

                            if (foundName.equalsIgnoreCase(itemName)) {
        //                        js.executeScript("arguments[0].scrollIntoView(true);", item);
        //                        item.click();
        //                        System.out.println("Clicked item: " + itemName);

                                 // Scroll the full li element into view
                                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", item);
                                Thread.sleep(500); // Allow animation/popup to settle

                                // Wait until the element is actually clickable
                                wait.until(ExpectedConditions.elementToBeClickable(item));

                                try {
                                    item.click();
                                } catch (ElementClickInterceptedException e) {
                                    System.out.println("Standard click failed, trying JS click...");
                                    js.executeScript("arguments[0].click();", item);
                                }
                                 itemClicked = true;
                                System.out.println("Clicked item: " + itemName);

                                // Wait for item popup
                                WebElement qtyField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartPopUpQty")));
                                qtyField.clear();
                                qtyField.sendKeys("1");

                                Thread.sleep(1000); // Small wait for items to render

                                WebElement specialInstructions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SpecialInstructions")));
                                specialInstructions.sendKeys("Order by: - Tuneer Mahatpure.");

                                Thread.sleep(1000); // Small wait for items to render

                                driver.findElement(By.id("addToCart")).click();
                                System.out.println("Added to cart: " + itemName);
                                break;
                            }

                        } catch (Exception e) {
                            System.out.println("Error checking item: " + e.getMessage());
                        }
                    }

                    if (!itemClicked) {
                        System.out.println("Item not found: " + itemName);
                    }
                  }

        }
       
        // You can stop here OR continue with:
        // - Viewing cart
        // - Setting payment method
        // - Submitting the order
        
        // Wait for Pickup label to be visible
        WebElement pickupButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("label[for='Pickup'].btn.border-i-secondary")
        ));

        // Scroll to the button just in case it's off-screen
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", pickupButton);
        Thread.sleep(300); // allow UI to stabilize

        // Click the Pickup button
        pickupButton.click();
        System.out.println("Clicked on 'Pickup' button to proceed to cart/payment.");
        Thread.sleep(1000); // wait for the transition to cart/payment page
        
        // Wait and enter special instructions
        WebElement cartInstructions = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("SpecialInstructions"))
        );
        cartInstructions.clear();
        cartInstructions.sendKeys("Order by: - Tuneer Mahatpure.");
        System.out.println("Entered cart-level special instructions");
        
        // Wait for the Submit Order button to be clickable
        WebElement submitOrderBtn = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("btnSubmitOrder"))
        );

        // Scroll into view in case it's off-screen
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", submitOrderBtn);
        Thread.sleep(300);

        // Click to place the order
        submitOrderBtn.click();
        System.out.println("Clicked 'Submit Order' button.");

        // Optional: wait a few seconds for confirmation page to load
        Thread.sleep(3000);
        
        
        // Wait for confirmation page to load and receipt paragraph to appear
        var receiptElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Receipt No:')]")));

        // Extract the full text from the element
        String receiptText = receiptElement.getText(); 

        // Extract only the receipt number
        String receiptNo = receiptText.replace("Receipt No: ", "").trim();

        System.out.println("✅ Order placed successfully.");
        System.out.println("🧾 Receipt Number: " + receiptNo);


        System.out.println("Test complete.");
        // driver.quit(); // Uncomment to close browser at end
    }
    
    public String[] getItemsForCategory(String category){
        if("Chicken".equals(category)){
             return new String[]{"WHOLE CHICKEN"};
        }else if ("Lamb".equals(category)){
             return new String[]{"BABY LAMB"};
        }
        return null;
    }

   
 
}
