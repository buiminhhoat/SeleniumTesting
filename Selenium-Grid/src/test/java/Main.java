import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.experimental.ParallelComputer;
public class Main {
    public static void main(String[] args) {
        Class[] cls = {
                ShopeeUITestChrome.class,
//                ShopeeUITestFirefox.class,
                ShopeeUITestMicrosoftEdge.class,
                ShopeeFunctionalTestChrome.class,
//                ShopeeFunctionalTestFirefox.class,
                ShopeeFunctionalTestMicrosoftEdge.class
        };


        // Đăng ký listener với JUnitCore
        JUnitCore core = new JUnitCore();
        core.addListener(new MyTestListener());

        Result result = core.run(new ParallelComputer(true, true), cls);

        System.out.println("Number of tests run: " + result.getRunCount());
        System.out.println("Number of failed tests: " + result.getFailureCount());
    }
}
