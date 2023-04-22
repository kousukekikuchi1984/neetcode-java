package dev.kou2kkkt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class MainTest {
    @Test
    public void testHelloWorld() {
        String message = Main.getHelloWorldMessage();
        Assertions.assertEquals("Hello World", message);
    }
}