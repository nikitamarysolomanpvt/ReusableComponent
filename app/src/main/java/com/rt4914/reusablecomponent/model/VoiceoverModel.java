package com.rt4914.reusablecomponent.model;

public class VoiceoverModel {

    private String sContentId;
    private long fileSize;
    private String fileName;
    private boolean needsUpdate;

    public VoiceoverModel() {
    }

    public String getsContentId() {
        return sContentId;
    }

    public void setsContentId(String sContentId) {
        this.sContentId = sContentId;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isNeedsUpdate() {
        return needsUpdate;
    }

    public void setNeedsUpdate(boolean needsUpdate) {
        this.needsUpdate = needsUpdate;
    }
}
