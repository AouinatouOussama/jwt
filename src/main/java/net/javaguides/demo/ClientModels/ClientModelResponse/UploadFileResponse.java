package net.javaguides.demo.ClientModels.ClientModelResponse;


public class UploadFileResponse {
    private String fileName;
    private String fileFullPath;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileFullPathh, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileFullPath=fileFullPathh;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
    public String getFileFullPath() {
        return fileFullPath;
    }

    public void setFileFullPath(String fileFullPath) {
        this.fileFullPath = fileFullPath;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
