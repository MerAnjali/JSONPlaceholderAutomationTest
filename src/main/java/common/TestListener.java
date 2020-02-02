package common;

import config.configProvider;
import io.restassured.RestAssured;
import org.testng.*;
import org.apache.logging.log4j.Logger;

public class TestListener implements ISuiteListener, ITestListener {

    private final Logger log = MyLogger.log;

    public void onStart(ISuite iSuite) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        new configProvider();
        new Spec();
    }

    public void onFinish(ISuite iSuite) {
        log.info("Finished running all the tests.");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("Starting test " + iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("Test " + iTestResult.getName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.error("Test " + iTestResult.getName() + "  FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.warn("Test " + iTestResult.getName() + " SKIPPED");
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
