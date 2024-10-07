package model;

// import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SleepEntryTest {

    private SleepEntry sleepEntryTest; 
    
    @BeforeEach
    void runBefore() {
    sleepEntryTest = new SleepEntry(1.0, 5,"test");
    }

    @Test
    void testConstructor() {
        assertEquals(1.0, sleepEntryTest.getHoursSlept());
        assertEquals(5, sleepEntryTest.getSleepRating());
        assertEquals("test", sleepEntryTest.getSleepNote());
    }

}
