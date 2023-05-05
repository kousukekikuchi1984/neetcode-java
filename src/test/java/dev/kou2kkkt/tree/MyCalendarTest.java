package dev.kou2kkkt.tree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class MyCalendarTest {
    @Test
    void test_book() {
        // ref: https://leetcode.com/problems/my-calendar-i/
        MyCalendar myCalendar = new MyCalendar();
        Assertions.assertEquals(true, myCalendar.book(10, 20));
        Assertions.assertEquals(false, myCalendar.book(15, 25));
        Assertions.assertEquals(true, myCalendar.book(20, 30));
    }
}