import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program{


    public void run(){
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setFile("*.jpg;*.jpeg");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);

        String fileName = dialog.getFile();
        String fileDirectory = dialog.getDirectory();
        String url =dialog.getDirectory()+ dialog.getFile();
        dialog.setVisible(false);


        BufferedImage image = null;
        try {
            File file = new File(url);
            image = ImageIO.read(file);

        } catch (Exception e) {
            System.out.println("File couldn't be loaded");
        }
        System.out.println("Image started processing");
        Threshold threshold = new Threshold();

        long reference=System.nanoTime();
        image = threshold.apply(image);
        long finishm=System.nanoTime();
        System.out.println( ( (double)(finishm-reference) )/1000000000.0);

        File outfile = new File(fileDirectory+"threshold_"+fileName);
        try {
            ImageIO.write(image,"jpg",outfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Desktop dt = Desktop.getDesktop();
        try {
            dt.open(outfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
