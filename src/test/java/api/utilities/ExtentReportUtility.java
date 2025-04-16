package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentReportUtility implements ITestListener
{
	public ExtentSparkReporter sparkReporter; //UI
    public ExtentReports extent; //COMMON INFO
    public ExtentTest test; //creating entries like test pass or fail

    String repName;
    
    public void onStart(ITestContext testcontext){
    	
    	/*  SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    	Date dt = new Date();
    	String currentdatetimestamp = df.format(dt);  */
    	
    	String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	repName = "Test-Report-" + timestamp + ".html";
    	
    	
    	sparkReporter = new ExtentSparkReporter(".\\Reports\\" + repName);
        //sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport.html"); // Location of report
        sparkReporter.config().setDocumentTitle("RestAssured Report"); //Title of report
        sparkReporter.config().setReportName("API Automation testing"); // Name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        //--------------------------------------------------------------------------------------------------------------

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application Name","Pet Store");
        extent.setSystemInfo("Enviroment","QA");
        extent.setSystemInfo("Tester Name","Yesh");
        extent.setSystemInfo("Module", "Users");
        //extent.setSystemInfo("Sub module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("OS Name", System.getProperty("os.name"));
        
        String os = testcontext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);
        
        String browser = testcontext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);
        
        List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()) {
        	extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result){

        test = extent.createTest(result.getTestClass().getName()); //Create a new entity in the report
        test.assignCategory(result.getMethod().getGroups()); // TO DISPLAY GROUPS IN REPORTS
        test.createNode(result.getName());
        test.log(Status.PASS,"Test case PASSED is " + result.getName()+" got successfully executed") ; // update status ie PASS/FAIL/SKIP in the report
        
      /*  try {
        	
        	String imgpath = new baseClass().captureScreen(result.getName());
        	test.addScreenCaptureFromPath(imgpath);
        	
        } catch (Exception e1) {
        	e1.printStackTrace();
        }
	*/
    } 

    public void onTestFailure(ITestResult result){

        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL,"Test case FAILED is " + result.getName() + " got failed");
        test.log(Status.INFO,result.getThrowable().getMessage());
        
      /*  try {
        	
        	String imgpath = new baseClass().captureScreen(result.getName());
        	test.addScreenCaptureFromPath(imgpath);
        	
        } catch (Exception e1) {
        	e1.printStackTrace();
        } */

    }

    public void onTestSkipped(ITestResult result){

        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.SKIP,"Test case SKIPPED is " + result.getName()+" got skipped ");
        test.log(Status.INFO, result.getThrowable().getMessage());

    }

    public void onFinish(ITestContext context){
        extent.flush();
        
        String pathofExtentReport = System.getProperty("user.dir")+"\\Reports\\"+repName;
        File extentReport = new File(pathofExtentReport);
        
        try {
        	Desktop.getDesktop().browse(extentReport.toURI()); // will open report on browser automatically
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
		/*
		 * try { URL url = new
		 * URL("file:///"+System.getProperty("user.dir")+"\\Reports\\"+repName); //
		 * converting location of report into URL
		 * 
		 * ImageHtmlEmail email = new ImageHtmlEmail(); email.setDataSourceResolver(new
		 * DataSourceUrlResolver(url)); email.setHostName("smtp.googlemail.com"); //only
		 * for gmail if required need to to change to other email.setSmtpPort(465);
		 * email.setAuthenticator(new DefaultAuthenticator("indanayeswanth@gmail.com",
		 * "8121398952")); email.setSSLOnConnect(true);
		 * email.setFrom("indanayeswanth@gmail.com"); //sender
		 * email.setSubject("Test Results");
		 * email.setMsg("Please find the attached report");
		 * email.addTo("indanayeswanth@gmail.com"); //Receiver
		 * email.attach(url,"Extent report","please check report"); email.send(); //send
		 * the email } catch(Exception e) { e.printStackTrace(); }
		 */
        
    }



}
