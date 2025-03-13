package com.navi92.util;

public class ProgressBar {
    public static String getBarByPercentage(float percentage) {
        String square = "█";
        String dash = "-";

        StringBuilder bar = new StringBuilder();
        for (int i = 0; i<20; i++) {
            if (i<((int)percentage)/5) {
                bar.append(square);
            } else {
                bar.append(dash);
            }
        }

        return String.format("Progress: | %s | %s%% Complete", bar, percentage);
    }
}
