import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardAdminHelper {
    WebDriver driver;

    public DashboardAdminHelper(WebDriver driver) {
        this.driver = driver;
    }
    public void access_web_dashboard() throws Throwable {
        this.driver.get("http://example/dashboardadmin");
    }
    public WebElement validation_data_ID()throws Throwable {
        WebElement IdData= this.driver.findElement(By.xpath("//div[@id='idUser']"));
       return IdData;
    }
    public WebElement validation_data_userName()throws Throwable {
        WebElement userNameData= this.driver.findElement(By.xpath("//div[@id='userName']"));
        return userNameData;
    }
    public WebElement validation_data_sourceBank()throws Throwable {
        WebElement sourceBankData= this.driver.findElement(By.xpath("//div[@id='sourceBank']"));
        return sourceBankData;
    }
    public WebElement validation_data_destinationBank()throws Throwable {
        WebElement destinationBankData= this.driver.findElement(By.xpath("//div[@id='destinationBank']"));
        return destinationBankData;
    }
    public WebElement validation_data_amount()throws Throwable {
        WebElement amountData= this.driver.findElement(By.xpath("//div[@id='amount']"));
        return amountData;
    }
}
