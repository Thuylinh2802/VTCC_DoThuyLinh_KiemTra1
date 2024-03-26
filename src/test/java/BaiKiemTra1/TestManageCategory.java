package BaiKiemTra1;

import common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

public class TestManageCategory extends BaseTest {
    public void loginCRM() {
        driver.get("https://cms.anhtester.com/login");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("admin@example.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
    }

    @Test
    public void addCategory() {
        loginCRM();

        driver.findElement(By.xpath("//span[normalize-space()='Products']")).click();
        sleep(1);
        driver.findElement(By.xpath("//span[contains(@class, 'aiz-side-nav-text') and contains(text(), 'Category')]")).click();
        sleep(1);
        driver.findElement(By.xpath("//span[normalize-space()='Add New category']")).click();
        sleep(1);
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Test add new category");
        sleep(1);
        driver.findElement(By.xpath("//div[contains(text(),'No Parent')]")).click();
        driver.findElement(By.xpath("//span[normalize-space()='-- demo']")).click();
        sleep(1);
        driver.findElement(By.id("order_level")).sendKeys("50");
        driver.findElement(By.xpath("//div[contains(text(),'Physical')]")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Digital']")).click();
        sleep(1);
        driver.findElement(By.xpath("//div[@class='form-control file-amount'][normalize-space()='Choose File']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search your files']")).sendKeys("icon");
        sleep(3);
        driver.findElement(By.xpath("//div[@id='aiz-select-file']//div[1]//div[1]//div[1]//div[1]//img[1]")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Add Files']")).click();
        sleep(1);
        driver.findElement(By.xpath("//div[@class='form-control file-amount'][normalize-space()='Choose File']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search your files']")).sendKeys("icon");
        sleep(3);
        driver.findElement(By.xpath("//div[@title='icon.jpg']//img[@class='img-fit']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Add Files']")).click();
        sleep(1);
        driver.findElement(By.xpath("//input[@placeholder='Meta Title']")).sendKeys("test");
        driver.findElement(By.xpath("//textarea[@name='meta_description']")).sendKeys("test add new category");
        driver.findElement(By.xpath("//div[contains(text(),'Nothing selected')]")).click();
        driver.findElement(By.id("bs-select-3-2")).click();
        sleep(1);
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
        sleep(2);
    }

    @Test
    public void verifyAddCategory() {
        loginCRM();
        Actions action = new Actions(driver);
        String categoryName = "Test add new category";
        String parentCategoryName = "demo";
        String orderLevel = "50";

        driver.findElement(By.xpath("//span[normalize-space()='Products']")).click();
        sleep(1);
        driver.findElement(By.xpath("//span[contains(@class, 'aiz-side-nav-text') and contains(text(), 'Category')]")).click();
        sleep(1);
        driver.findElement(By.id("search")).sendKeys("test add new category");
        action.sendKeys(Keys.ENTER).build().perform();
        sleep(4);
        String getCategoryName = driver.findElement(By.xpath("//td[normalize-space()='Test add new category']")).getText();
        System.out.println(getCategoryName);
        String getParentCategoryName = driver.findElement(By.xpath("//td[normalize-space()='demo']")).getText();
        System.out.println(getParentCategoryName);
        String getOrderLevel = driver.findElement(By.xpath("//td[normalize-space()='50']")).getText();
        System.out.println(getOrderLevel);
        Assert.assertEquals(categoryName, getCategoryName, "FAILED. Category Name not match.");
        Assert.assertEquals(parentCategoryName, getParentCategoryName, "FAILED. Parent Category Name not match.");
        Assert.assertEquals(orderLevel, getOrderLevel, "FAILED. Order Level not match.");
        sleep(2);
    }
}