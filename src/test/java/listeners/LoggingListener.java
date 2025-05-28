package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class LoggingListener implements ITestListener {


    private static final Logger logger = LogManager.getLogger(LoggingListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("STARTED: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("PASSED: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("FAILED: {}", result.getName(), result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("SKIPPED: {}", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("TEST CONTEXT STARTED: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("TEST CONTEXT FINISHED: {}", context.getName());
    }
}

