package testScript;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import GenericUtility.BaseTest;
import GenericUtility.ListenerImplementation;

@Listeners(GenericUtility.ListenerImplementation.class)
public class GoogleLinkTest extends BaseTest {

	@Test
	public void linkVerificationTest() throws InterruptedException {
		int flipcartCount = 0;
		int amazonCount = 0;
		driver.findElement(By.id("APjFqb")).sendKeys("Best Washing Machine", Keys.ENTER);
		//driver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc'] //input[@value='Google Search']")).click();
		ListenerImplementation.test.log(LogStatus.PASS, "Washing machines displayed successfully....");
		Thread.sleep(4000);
		List<WebElement> links = driver.findElements(By.xpath("//div[@jscontroller='SC7lYd'] //div[@class='TbwUpd iUh30 ojE3Fb']"));

		Actions a = new Actions(driver);
		for (WebElement link : links) {
			a.moveToElement(link).keyDown(Keys.COMMAND).click().perform();
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true)", link);
			Thread.sleep(1000);
		}
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			ListenerImplementation.test.log(LogStatus.INFO, "Switched to " + driver.getTitle());
			if (driver.getCurrentUrl().contains("flipkart")) {
				flipcartCount++;
			} else if (driver.getCurrentUrl().contains("amazon")) {
				amazonCount++;
			}
		}
		Thread.sleep(4000);
		ListenerImplementation.test.log(LogStatus.INFO, "Number of Flipkart links = " + flipcartCount);
		ListenerImplementation.test.log(LogStatus.INFO, "Number of Amazon links = " + amazonCount);
	}
}
