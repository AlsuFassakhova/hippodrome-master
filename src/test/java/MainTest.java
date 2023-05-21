import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


class MainTest {
    @Test
    @DisplayName("mainClassRunningTimeTest")
    @Timeout(22)
    @Disabled
    void mainClassRunningTimeTest() throws Exception {
        String[] args = null;
        Main.main(args);
    }
}