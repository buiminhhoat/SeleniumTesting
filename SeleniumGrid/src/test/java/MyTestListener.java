import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class MyTestListener extends RunListener {
    @Override
    public void testStarted(Description description) throws Exception {
        System.out.println("Running test case: " + description.getTestClass() + " " + description.getMethodName());
    }

    @Override
    public void testFinished(Description description) throws Exception {
        System.out.println("Passed test case: " + description.getTestClass() + " " + description.getMethodName());
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        System.out.println("Test failed: " + failure.getTestHeader() + " " + failure.getDescription().getMethodName());
        System.out.println("Reason: " + failure.getMessage());
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        System.out.println("Test assumption failed: " + failure.getTestHeader() + " " + failure.getDescription().getMethodName());
        System.out.println("Reason: " + failure.getMessage());
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        System.out.println("Ignoring test case: " + description.getTestClass() + " " + description.getMethodName());
    }
}
