import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;

public class DashboardAdmin {
    private Connection connection;
    private static Statement statement;
    private static ResultSet result;

    WebDriver driver = new ChromeDriver();
    DashboardAdminHelper dashboardAdminHelper = new DashboardAdminHelper(driver);

    @Given("^access database$")
    public void access_database() throws Throwable {
        String databaseURL = "jdbc:mysql://${MYSQL_HOST:localhost}:${MYSQL_PORT:3306}/admin";
        String user = "root";
        String password = "root";
        connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to Database...");
            connection = DriverManager.getConnection(databaseURL, user, password);
            if (connection != null) {
                System.out.println("Connected to the Database");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Given("^access web dashboard$")
    public void access_web_dashboard() throws Throwable {
        dashboardAdminHelper.access_web_dashboard();
    }

    @When("^validation data dashboard with database$")
    public void validation_data_dashboard_with_database()throws Throwable {
        String ID = dashboardAdminHelper.validation_data_ID().getText();
        String userName = dashboardAdminHelper.validation_data_userName().getText();
        String sourceBank = dashboardAdminHelper.validation_data_sourceBank().getText();
        String destinationBank = dashboardAdminHelper.validation_data_destinationBank().getText();
        String amount = dashboardAdminHelper.validation_data_amount().getText();

        try {
            String query = "select * from transaction where ID = :ID";
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while(result.next()){
                int IDUser= result.getInt("ID");
                String userNameData= result.getString("User_Name");
                String sourceBankData=result.getString("Source_Bank");
                String destinationBankData=result.getString("Destination_Bank");
                int amountData= result.getInt("Amount");

                Assert.assertEquals(IDUser,ID);
                Assert.assertEquals(userNameData,userName);
                Assert.assertEquals(sourceBankData,sourceBank);
                Assert.assertEquals(destinationBankData,destinationBank);
                Assert.assertEquals(amount,amountData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @When("^validation data examples UI \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void validation_data_examples_UI(String idUser,String userName,String sourceBank,String destinationBank,String amount){
        try {
            String query = "select * from transaction where ID = :idUser";
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while(result.next()){
                int ID= result.getInt("ID");
                String userNameData= result.getString("User_Name");
                String sourceBankData=result.getString("Source_Bank");
                String destinationBankData=result.getString("Destination_Bank");
                int amountData= result.getInt("Amount");

                Assert.assertEquals(idUser,ID);
                Assert.assertEquals(userName, userNameData);
                Assert.assertEquals(sourceBank, sourceBankData);
                Assert.assertEquals(destinationBank, destinationBankData);
                Assert.assertEquals(amountData, amount);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
