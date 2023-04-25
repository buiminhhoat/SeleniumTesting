import com.googlecode.junittoolbox.ParallelSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;
import org.junit.runners.model.RunnerScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(ParallelSuite.class)
@Suite.SuiteClasses({ShopeeUITestChrome.class, ShopeeFunctionalTestFirefox.class})
public class ParallelTestSuite {

    private static class ParallelSuite extends Suite {
        public ParallelSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
            super(klass, builder);
            setScheduler(new ParallelScheduler());
        }
    }

    private static class ParallelScheduler implements RunnerScheduler {

        private ExecutorService executor;

        public ParallelScheduler() {
            executor = Executors.newFixedThreadPool(4); // Số thread chạy test case
        }

        public void schedule(Runnable childStatement) {
            executor.submit(childStatement);
        }

        public void finished() {
            try {
                executor.shutdown();
                executor.awaitTermination(10, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
