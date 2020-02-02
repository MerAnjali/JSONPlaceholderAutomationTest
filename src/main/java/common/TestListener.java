package common;

import config.configProvider;
import io.restassured.RestAssured;
import org.testng.*;

public class TestListener implements ISuiteListener, ITestListener {


    public void onStart(ISuite iSuite) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        new configProvider();
        new Spec();
    }

    public void onFinish(ISuite iSuite) {
        System.out.println("Finished running all the tests.");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Starting test " + iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test " + iTestResult.getName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test " + iTestResult.getName() + "  FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test " + iTestResult.getName() + " SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
