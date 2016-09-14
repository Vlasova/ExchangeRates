package ru.vlasova.translator;

import org.junit.Test;
import static org.junit.Assert.*;

public class TranslatorTest {
    @Test
    public void convert(){
        Translator translator = new Translator();
        assertTrue(translator.convert(5) == 12.7);
    }

}