import gui.GUI;
import model.VideoFile;
import video.ConvertVideo;

import java.io.IOException;

public class Main {

    public static void main(String[] args){

        GUI gui = new GUI();
        ConvertVideo convertVideo = new ConvertVideo();
        VideoFile videoFile = gui.getVideoFile();
        try {
            long reference=System.nanoTime();
            System.out.println("Start video processing");
            videoFile = convertVideo.convertVideoToImages(videoFile);
            System.out.println("Done video to image");
            videoFile = convertVideo.convertImagesToVideo(videoFile);
            System.out.println("Done video making");
            long finish=System.nanoTime();
            gui.displayFile(videoFile.getFinalPath());
            System.out.println( ( (double)(finish-reference) )/1000000000.0 + " seconds");
        } catch (Exception e) {
            e.printStackTrace();
            gui.showMessage();
        }
    }
}
