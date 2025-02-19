package com.json.iplcensorship;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IPLDataProcessorTest {

    @Test
    void testMaskTeamName() {
        assertEquals("Mumbai ***", IPLDataProcessor.maskTeamName("Mumbai Indians"));
        assertEquals("Delhi ***", IPLDataProcessor.maskTeamName("Delhi Capitals"));
    }

    @Test
    void testRedactPlayer() {
        assertEquals("REDACTED", IPLDataProcessor.redactPlayer("Rohit Sharma"));
        assertEquals("REDACTED", IPLDataProcessor.redactPlayer("Virat Kohli"));
    }
}

