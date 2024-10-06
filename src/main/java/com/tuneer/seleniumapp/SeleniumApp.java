/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tuneer.seleniumapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import org.apache.poi.ss.usermodel.Cell;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author gdknmac
 */
public class SeleniumApp {
    
    WebDriver driver;
   
   String path = "/Users/itteam2/Downloads/chromedriver-mac-arm64/chromedriver";
   
   public void launchBrowser() throws InterruptedException{
       
       System.setProperty("webdriver.chrome.driver", path);
       
       driver = new ChromeDriver();
       driver.get("http://3.135.219.244:2068/");
       
       
       //driver.findElement(By.ById.id("navlogin")).click();
driver.findElement(By.ById.id("navlogin")).click();

driver.findElement(By.xpath("//*[@id=\"loginModal\"]/div/div/div[3]/a[2]")).click();

WebElement element = driver.findElement(By.ById.id("Email"));
Thread.sleep(2000);
element.sendKeys("sysadmin");

//driver.findElement(By.ById.id("User_password"));
//Thread.sleep(2000);
//driver.findElement(By.ById.id("User_password")).sendKeys("jsc_f@sysadmin#");
//Thread.sleep(2000);
//driver.findElement(By.ById.id("loginbutton")).click();

//message for successfull login.

driver.findElement(By.ByClassName.className("hero-btn")).click();

Thread.sleep(2000);


//driver.findElement(By.ById.id("autocomplete")).sendKeys("Saag hut");
//
//driver.wait(2f);
//driver.findElement(By.ById.id(('findRestaurants')).click();

//time.sleep(2);
//inputElement = driver.find_element_by_class_name('rest_logo').click();
//  
//time.sleep(2);
//driver.findElement(By.ByXPath.xpath("//*[@id='16892']/span/ul/li[1]/span/span[1]")).click();


driver.findElement(By.ByXPath.xpath("//*[@id=\"16892\"]/span/ul/li[1]")).click();

Thread.sleep(2000);
//
driver.findElement(By.ById.id("SpecialInstructions")).sendKeys("Extra sauce");
//
Thread.sleep(2000);
driver.findElement(By.ById.id("cartPopUpQty")).clear();
driver.findElement(By.ById.id("cartPopUpQty")).sendKeys("2");
//
Thread.sleep(2000);
driver.findElement(By.ById.id("addToCart")).click();
Thread.sleep(2000);
//
driver.findElement(By.ById.id("spanCartCount")).click();
Thread.sleep(2000);
//
//
driver.findElement(By.ByXPath.xpath("//*[@id='OrderPaymentType']/div/div/label[1]")).click();
// //*[@id="OrderPaymentType"]/div/div/label[1]
Thread.sleep(2000);
//
//driver.findElement(By.ByXPath.xpath("//*[@id='PickUpOrDelivery']/label[2]")).click();
//
driver.findElement(By.ById.id("btnSubmitOrder")).click();
//
Thread.sleep(5000);
//
driver.findElement(By.ByClassName.className("normalbutton")).click();

       
      // driver.quit();
      
      
   }
   
   private int createDatabase(Connection con, String dbName, String[] columnNames) {
        try {
            StringBuilder strB = new StringBuilder();
            for(int k=0;k<columnNames.length;k++){
                strB.append(""+columnNames[k]+" VARCHAR(1000)").append(",");
            }
            
             // Remove the last comma
    if (strB.length() > 0) {
        strB.setLength(strB.length() - 1);
    }
            
            System.out.println(parseString(dbName)+" Columns: "+strB);
            Statement s = con.createStatement();
            int myResult = s.executeUpdate("CREATE TABLE IF NOT EXISTS " + parseString(dbName)+
                    "("+strB+")");
            System.out.println(myResult);
            return myResult;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 100;
    }
   
   public void pickExcelandShow(DisplayData aThis){
       
        String excelFilePath = "";
        
       // create an object of JFileChooser class
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
 
            // invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);
 
            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION)
 
            {
                // set the label to the path of the selected file
                excelFilePath = j.getSelectedFile().getAbsolutePath();
            }
            // if the user cancelled the operation
            else
            {
                
            }
            
            if(!excelFilePath.equalsIgnoreCase("")){
                    String jdbcURL = "jdbc:mysql://localhost:3306/sales?autoReconnect=true&useSSL=false";
                    String username = "root";
                    String password = "Root@2655";
 
      
        int batchSize = 20;
 
        Connection connection = null;
 
        try {
            long start = System.currentTimeMillis();
             
            FileInputStream inputStream = new FileInputStream(excelFilePath);
 
            Workbook workbook = new XSSFWorkbook(inputStream);
            
            
            
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
            
            
            // Read column names from the first row of the Excel sheet
            if (!rowIterator.hasNext()) {
                 JOptionPane.showMessageDialog(aThis, "The Excel sheet is empty.");
                return;
               // throw new IOException("The Excel sheet is empty.");
            }
           
            // Extract column names from the first row
            Row firstRow = rowIterator.next();
            int columnCount = firstRow.getPhysicalNumberOfCells();
            String[] columnNames = new String[columnCount];

            for (int i = 0; i < columnCount; i++) {
                Cell cell = firstRow.getCell(i);
                columnNames[i] = parseString(cell.getStringCellValue());
            }
        
            System.out.println("Columns: "+columnNames.toString());
           
           
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
            
            if(createDatabase(connection,workbook.getSheetName(0),columnNames)!=100){
                //created database or already exists
                if(createDatabase(connection,workbook.getSheetName(0),columnNames)==0){
                    //Database Created
                    System.setProperty("DBorSheetName", parseString(workbook.getSheetName(0)));
                    importDatafromExcel(connection,rowIterator,batchSize,workbook,start,aThis);
                }else{
                    //Database not created.
                }
                
            }
            else{
                //database can't be created.
                
                //
                
            }
            
        } catch (IOException ex1) {
            System.out.println("Error reading file");
            ex1.printStackTrace();
        } catch (SQLException ex2) {
            System.out.println("Database error");
            ex2.printStackTrace();
        }
            }
       
       
   }
   
   public void importDatafromExcel(Connection connection, Iterator<Row> rowIterator, int batchSize, Workbook workbook, long start, DisplayData aThis) throws SQLException,IOException{
            
            // Read column names from the first row of the Excel sheet
            StringBuilder columnNames = new StringBuilder();
             Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> columnIterator = firstSheet.iterator();
            
           Row firstRow = columnIterator.next();
           int columnCount = firstRow.getPhysicalNumberOfCells();

        for (int i = 0; i < columnCount; i++) {
            Cell cell = firstRow.getCell(i);
           // columnNames.append(cell.getStringCellValue());
            
                String colName = parseString(cell.getStringCellValue());
                System.out.println(colName); // Print each column name for debugging
                columnNames.append("`").append(colName).append("`");
                if (i != columnCount - 1) {
                    columnNames.append(", ");
                }
        }
             System.out.println("Column Names: "+columnNames);

            // Construct the SQL INSERT statement dynamically
            StringBuilder placeholders = new StringBuilder();
            for (int i = 0; i < columnCount; i++) {
                placeholders.append("?");
                if (i != columnCount - 1) {
                    placeholders.append(", ");
                }
            }
       
            String sql = "INSERT INTO " + parseString(workbook.getSheetName(0)) + 
                         " (" + columnNames.toString() + ") VALUES (" + placeholders.toString() + ")";
       
           // String sql = "INSERT INTO "+workbook.getSheetName(0).replaceAll("\\s", "").trim()+" (userid, password) VALUES (?, ?)";
           
           System.out.println("SQL String: "+sql);
           
            PreparedStatement statement = connection.prepareStatement(sql);    
             
            int count = 0;
             
            rowIterator.next(); // skip the header row
             
            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();


                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    
                    if(nextCell!=null){
 
                      int columnIndex = nextCell.getColumnIndex();
                      String cellValue = "";
                        switch (nextCell.getCellType()) {
                            case STRING:
                                cellValue = nextCell.getStringCellValue();
                                break;
                            case NUMERIC:
                                cellValue = String.valueOf(nextCell.getNumericCellValue());
                                break;
                            // Add more cases for other cell types if needed
                            default:
                                // Handle other cell types if necessary
                                break;
                        }
                            //statement.setString(i + 1, cellValue);
                            statement.setString(columnIndex+1, cellValue);
                       }
//                    switch (columnIndex) {
//                    case 0:
//                        String name = nextCell.getStringCellValue();
//                        statement.setString(1, name);
//                        break;
//                    case 1:
//                        String pass = nextCell.getStringCellValue();
//                        statement.setString(2, pass);
//                        break;
//                    }
 
                }
                 
                statement.addBatch();
                 
                if (++count % batchSize == 0) {
                    statement.executeBatch();
                }           
       
            }
            
            
          
            
 
            workbook.close();
             
            // execute the remaining queries
            statement.executeBatch();
  
            connection.commit();
            connection.close();
             
            long end = System.currentTimeMillis();
            System.out.printf("Import done in %d ms\n", (end - start));
            JOptionPane.showMessageDialog(aThis, "Saved Successfully.");
   }
   
   
   public String parseString(String value){
       return value.replaceAll("\\s", "").replaceAll("[\"'“”‘’]", "").replaceAll("[#,$,-,%,&,*,@,!,(,),?]", "").replace("-", "").trim();
   }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        //new SeleniumTest().pickExcelandShow();
       new DisplayData().setVisible(true);
//       try {
//           new SeleniumTest().launchBrowser();
//       } catch (InterruptedException ex) {
//           Logger.getLogger(SeleniumTest.class.getName()).log(Level.SEVERE, null, ex);
//       }
    }
}
