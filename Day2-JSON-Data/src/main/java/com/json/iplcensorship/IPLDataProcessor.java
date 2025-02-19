package com.json.iplcensorship;


public class IPLDataProcessor {
    public static String maskTeamName(String teamName) {
        String[] words = teamName.split(" ");
        return words[0] + " ***";
    }

    public static String redactPlayer(String playerName) {
        return "REDACTED";
    }
}
