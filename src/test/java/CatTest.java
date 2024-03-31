import com.example.Cat;
import com.example.Feline;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CatTest extends TestCase {
    private Cat cat;

    @Mock
    Feline feline;

    @Before
    public void setUp() {
        cat = new Cat(feline);
    }


    @Test
    public void testGetSound() {
        String actual = cat.getSound();

        assertEquals("Мяу", actual);
    }

    @Test
    public void testGetFood() throws Exception {
        cat.getFood();
        Mockito.verify(feline).eatMeat();
    }
}