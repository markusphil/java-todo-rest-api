import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorTest {

    @Test
    public void addValidSixDigitHexCode(){
        Color c = new Color("#3392FF");
        assertEquals("#3392FF",c.value);
    }

    @Test
    public void addValidThreeDigitHexCode(){
        Color c = new Color("#333");
        assertEquals("#333",c.value);
    }

   @ParameterizedTest
   @NullAndEmptySource
   @ValueSource(strings = {"333", "red", "#1234FFF"})
    public void addInvalidDigitHexCode(String colorCode){
        Color c = new Color(colorCode);
        assertEquals(Color.DEFAULT_COLOR,c.value);
    }
}
