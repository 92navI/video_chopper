package com.navi92;

import com.navi92.util.TextUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static com.navi92.util.TextUtils.format;

public class Main {


    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(format("&{46}aHello, this is Video Chopper terminal edition. :)"));
        System.out.println(format("&{34}a____________________</d></b>"));

        if (args.length == 2) {
            execute(args[0], args[1], "", 20, "0000", true);
        } else {
            System.out.print(format("&{255}aDo you want to open UI (Y/n): "));
            String yOrN = SCANNER.next();
            if (yOrN.equals("y") || yOrN.equals("Y")) {
                SwingUtilities.invokeLater(InputUI::createAndShowGUI);
                return;
            }
            System.out.println("Okayy");
        }

    }

    public static void execute(String videPath, String outputPath, String name, int frameRate, String numberFormatting, boolean doCorrectStructure) {
        System.out.println(format("&{255}aStarting the conversion!"));

        if (doCorrectStructure) {
            File file = new File(outputPath + "\\cutscenemod\\cutscenes\\"+name);
            if (file.mkdirs()) {
                System.out.println("Created directory.");
            } else {
                System.out.println("Directory already exists.");
            }
            outputPath = file.getAbsolutePath();
        }

        Converter.videoToPng(videPath, outputPath, frameRate, numberFormatting, "png");
    }
}
