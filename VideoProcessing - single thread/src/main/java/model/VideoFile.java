package model;

import java.nio.file.Path;

public class VideoFile {


    private String fileName;
    private String fileDirectory;
    private String fileURL;
    private Path outputTempDir;
    private String[] outputImages;
    private double frameRate;
    private String finalPath;

    public String getFileName() {
        return fileName;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public String getFileURL() {
        return fileURL;
    }

    public Path getOutputTempDir() {
        return outputTempDir;
    }

    public void setOutputTempDir(Path outputTempDir) {
        this.outputTempDir = outputTempDir;
    }

    public String[] getOutputImages() {
        return outputImages;
    }

    public void setOutputImages(String[] outputImages) {
        this.outputImages = outputImages;
    }

    public void setFrameRate(double frameRate) {
        this.frameRate=frameRate;
    }

    public double getFrameRate(){
        return this.frameRate;
    }

    public void setFinalPath(String finalPath) {
        this.finalPath=finalPath;
    }
    public String getFinalPath(){
        return this.finalPath;
    }




    public static class Builder{

        private String fileName;
        private String fileDirectory;
        private String fileURL;

        public Builder(){}

        public Builder withFileName(String fileName){
            this.fileName=fileName;
            return this;
        }

        public Builder withFileDirectory(String fileDirectory){
            this.fileDirectory=fileDirectory;
            return this;
        }
        public Builder withFileURL(String url){
            this.fileURL=url;
            return this;
        }

        public VideoFile build(){
            VideoFile videoFile = new VideoFile();
            videoFile.fileDirectory=this.fileDirectory;
            videoFile.fileName=this.fileName;
            videoFile.fileURL=this.fileURL;

            return videoFile;
        }

    }
}
