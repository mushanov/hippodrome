import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@DisplayName("Main class tests")
public class MainTest {

    @Test
    @DisplayName("main method completes within 22 seconds")
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Disabled to save test run time. Run manually if needed.")
    void testMainExecutionTime() throws Exception {
        // This test will fail if main() takes longer than 22 seconds
        // It is disabled to avoid waiting during normal test runs
        Main.main(new String[]{});
    }
}