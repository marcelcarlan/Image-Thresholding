package video;

import io.TemporaryFiles;
import model.VideoFile;
import org.bytedeco.javacv.*;
import org.jcodec.api.awt.AWTSequenceEncoder;
import thresholding.Threshold;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ConvertVideo {

    public VideoFile convertVideoToImages(VideoFile videoFile) throws IOException {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(new File(videoFile.getFileURL()));
        Threshold threshold = new Threshold();
        Path tempDir = TemporaryFiles.createTemporaryDirectory();
        frameGrabber.start();
        BufferedImage processedImage = null;
        System.out.println("No. frames :"+frameGrabber.getLengthInFrames());
        System.out.println("Resolution: "+frameGrabber.getImageWidth()+"X"+frameGrabber.getImageHeight());
        String[] strings = new String[frameGrabber.getLengthInFrames()];
        for (int i = 0; i < frameGrabber.getLengthInFrames() ; i++) {
            frameGrabber.setFrameNumber(i);
            Frame frame = frameGrabber.grabImage();
            processedImage = converter.convert(frame);
            processedImage = threshold.apply(processedImage);
            String outputString = tempDir.toString()+"\\"+i+".jpg";
            File outfile = new File(outputString);
            ImageIO.write(processedImage,"jpg",outfile);
            strings[i]=outfile.getAbsolutePath();
        }
        videoFile.setFrameRate(frameGrabber.getFrameRate());
        videoFile.setOutputImages(strings);
        videoFile.setOutputTempDir(tempDir);
        return videoFile;
    }


    public VideoFile convertImagesToVideo(VideoFile videoFile) throws IOException{
        String finalPath = videoFile.getFileDirectory()+"output_"+videoFile.getFileName();
        AWTSequenceEncoder encoder = AWTSequenceEncoder.createSequenceEncoder(new File(finalPath), (int) videoFile.getFrameRate());
        videoFile.setFinalPath(finalPath);
        encoder.getEncoder().setKeyInterval((int) videoFile.getFrameRate());
        int framesToEncode = videoFile.getOutputImages().length;

        for (int i = 0; i < framesToEncode ; i++) {
            BufferedImage img = ImageIO.read(new File(videoFile.getOutputImages()[i]));
            encoder.encodeImage(img);
        }

        encoder.finish();
        TemporaryFiles.recursiveDeleteOnShutdownHook(videoFile.getOutputTempDir());
        return videoFile;
    }

}
