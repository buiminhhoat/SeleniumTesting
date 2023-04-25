import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

public class TestClassParallel {

    @Test
    public void test() {
        Class[] cls = { ShopeeUITestChrome.class, ShopeeFunctionalTestFirefox.class };
        JUnitCore.runClasses(ParallelComputer.methods(), cls);
    }
}