package com.codky.book.springboot2webservice2.junitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CalculatorTest {

    @Test
    public void testSum() {
        Calculator c = new Calculator();

        double result = c.sum(10, 50);

        assertEquals(60, result, 0);
    }

}