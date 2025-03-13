package com.navi92;

import com.navi92.util.ProgressBar;
import com.navi92.util.TextUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import static com.navi92.util.TextUtils.format;

public class Converter {
    public static void videoToPng(String mp4Path, String imagePath, int targetRate, String nameFormatting, String imgType) {

        Java2DFrameConverter converter = new Java2DFrameConverter();

        try (FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(mp4Path)) {
            frameGrabber.setAudioChannels(0);
            frameGrabber.setOption("loglevel", "error");
            frameGrabber.start();

            double rate = frameGrabber.getFrameRate();
            double percentage = targetRate / rate;

            int totalFrames = frameGrabber.getLengthInFrames();
            int framesToKeep = (int) (totalFrames * percentage);
            double frameInterval = (double) totalFrames / framesToKeep;

            System.out.printf("Chopping video from:\n%s\nTo directory:\n%s\n", mp4Path, imagePath);
            System.out.printf("Going from %s fps to %s fps.\n", rate, targetRate);
            System.out.printf("Total Frames: %s, Keeping %s frames.\n", totalFrames, framesToKeep);
            System.out.printf("Frame Interval: %s\n\n", frameInterval);

            int imgNum = 0;
            for (int i = 0; i < framesToKeep; i++) {
                int frameNumber = (int) Math.round(i * frameInterval);
                frameGrabber.setVideoFrameNumber(frameNumber);

                Frame frame = frameGrabber.grabImage();
                if (frame == null) break;

                BufferedImage image = converter.convert(frame);
                if (image == null) continue;

                String path = imagePath + File.separator + new DecimalFormat(nameFormatting).format((++imgNum)) + "." + imgType;
                ImageIO.write(image, imgType, new File(path));

                TextUtils.del();
                System.out.print(ProgressBar.getBarByPercentage(((float) imgNum /framesToKeep)*100));
            }
        } catch (FrameGrabber.Exception e) {
            System.out.println("An error occurred while trying to chop the video. Please check the arguments and try again.");
        } catch (IOException e) {
            System.out.println("Unable to write images!");
        }
    }
}
