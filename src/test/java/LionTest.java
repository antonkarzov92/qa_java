import com.example.Feline;
import com.example.Lion;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertArrayEquals;

@RunWith(MockitoJUnitRunner.class)
public class LionTest extends TestCase {
    private static final String MALE = "Самец";
    private static final String FEMALE = "Самка";
    private static final String PREDATOR = "Хищник";
    private static final String MEAT = "Мясо";
    private static final String UNSUPPORTED_SEX = "unsupported sex";
    private static final String TEXT_EXCEPTION = "Используйте допустимые значения пола животного - самей или самка";
    private Lion lion;

    @Mock
    private Feline feline;

    @Test
    public void testGetKittens() throws Exception {
        lion = new Lion(MALE, feline);

        lion.getKittens();
        Mockito.verify(feline).getKittens();
    }

    @Test
    public void testDoesHaveManeException() {
        Throwable throwable = catchThrowable(() -> {
            lion = new Lion(UNSUPPORTED_SEX, feline);
        });
        assertThat(throwable)
                .isInstanceOf(Exception.class)
                .hasMessage(TEXT_EXCEPTION);
    }

    @Test
    public void testDoesHaveMane() throws Exception {
        lion = new Lion(MALE, feline);

        boolean actual = lion.doesHaveMane();
        assertEquals("Должен иметь гриву",
                true, actual);
    }

    @Test
    public void testDoesNotHaveMane() throws Exception {
        lion = new Lion(FEMALE, feline);

        boolean actual = lion.doesHaveMane();
        assertEquals("Не должен иметь гриву",
                false, actual);
    }

    @Test
    public void testGetFood() throws Exception {
        var expected = List.of(MEAT);
        Mockito.when(feline.getFood(PREDATOR)).thenReturn(expected);

        lion = new Lion(MALE, feline);

        var actual = lion.getFood();
        Mockito.verify(feline).getFood(Mockito.anyString());
        assertArrayEquals("Возвращено некорректное значение для еды", actual.toArray(), expected.toArray());
    }
}
