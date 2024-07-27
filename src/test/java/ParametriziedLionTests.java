import com.example.Feline;
import com.example.Lion;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertArrayEquals;

@RunWith(Parameterized.class)
public class ParametriziedLionTests extends TestCase {
    private static final String MALE = "Самец";
    private static final String FEMALE = "Самка";

    private String gender;
    private boolean hasMane;

    private Feline feline = new Feline();
    private Lion lion;

    public ParametriziedLionTests(String gender, boolean hasMane) {
        this.gender = gender;
        this.hasMane = hasMane;
    }

    @Test
    public void testDoesHaveCorrectMane() throws Exception {
        lion = new Lion(this.gender, feline);

        boolean actual = lion.doesHaveMane();
        assertEquals("Гриву должен иметь только лев",
                this.hasMane, actual);
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                { MALE, true},
                { FEMALE, false},
        };
    }
}
