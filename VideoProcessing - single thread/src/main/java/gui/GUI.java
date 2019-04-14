package gui;

import model.VideoFile;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUI {

    public VideoFile getVideoFile(){
        Frame frame = new Frame();
        FileDialog dialog = new FileDialog(frame,"Select file to open");
        dialog.setFile("*.mp4");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        frame.setVisible(true);

        VideoFile videoFile=  new VideoFile.Builder()
                .withFileName(dialog.getFile())
                .withFileDirectory(dialog.getDirectory())
                .withFileURL(""+dialog.getDirectory()+dialog.getFile())
                .build();
        frame.setVisible(false);
        frame.dispose();
        return videoFile;
    }

    public void showMessage(){
        JOptionPane.showMessageDialog(null,"Error in video processing");
    }

    public void displayFile(String path) {

        File outfile = new File(path);
        Desktop dt = Desktop.getDesktop();
        try {
            dt.open(outfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
