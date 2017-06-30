package com.weweb.cloud.common.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jackshen on 2017/6/4.
 */
public class FTPUploader {
    public String username = "";
    public String password = "";
    public String ip = "";
    public Integer port = 21;

    public FTPUploader(String username, String password, String ip, int port) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.port = port;
    }

    public boolean upload(File file, String remoteFileName, String remoteFilePath)
            throws Exception {
       return upload(ip, port, username, password, file, remoteFilePath,
                remoteFileName);
    }

    private boolean upload(String ip, int port, String userName, String password,
                        File file, String remotePathName, String remoteName)
            throws Exception {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, port);
            boolean login=ftpClient.login(userName, password);
            System.out.println("login:"+login);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            return upload(ftpClient, file, remotePathName, remoteName);
        } catch (Exception e) {
            throw new Exception("upload to ftp faild");
        } finally {
            if (ftpClient != null && ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                }
            }
        }
    }

    private boolean upload(FTPClient ftpClient, File file, String remotePathName,
                        String remoteName) throws Exception {
        changeDirectory(ftpClient, remotePathName);
        if(uploadFile(ftpClient, file, remotePathName, remoteName)){
            backToRootDirectory(ftpClient);
            return true;
        }
        return false;
    }

    private void changeDirectory(FTPClient ftpClient, String path)
            throws IOException {
        int nextSeperator = path.indexOf("/", 1);
        String currentPath = null;
        if (nextSeperator < 0) {
            nextSeperator = path.length();
            currentPath = path.substring(1, nextSeperator);
            changeDirectory0(ftpClient, currentPath);
            return;
        } else {
            currentPath = path.substring(1, nextSeperator);
            changeDirectory0(ftpClient, currentPath);
            changeDirectory(ftpClient, path.substring(nextSeperator));
        }
    }

    private void changeDirectory0(FTPClient ftpClient, String path)
            throws IOException {
        if (!ftpClient.changeWorkingDirectory(path)) {
            ftpClient.makeDirectory(path);
        }
        ftpClient.changeWorkingDirectory(path);
    }

    private void backToRootDirectory(FTPClient ftpClient) throws IOException {
        ftpClient.changeWorkingDirectory("/");
    }

    private boolean uploadFile(FTPClient ftpClient, File file,
                            String remotePathName, String remoteName) throws Exception {
        String localPathName = file.getAbsolutePath();
        FTPFile[] files = ftpClient.listFiles(remoteName);
        if (files.length == 1) {
            if (!ftpClient.deleteFile(remoteName)) {
                throw new Exception("fail to delete remote file ["
                        + remotePathName + "] before uploading");
            }
        }
        File f = new File(localPathName);
        InputStream is = new FileInputStream(f);
        if (!ftpClient.storeFile(remoteName, is)) {
            is.close();
            return false;
        }
        is.close();
        return true;
    }

    public static void main(String[] args) {
         FTPUploader ftpUploader = new FTPUploader("admin", "admin123",
                "192.168.1.111", 9921);
         String filePath="/Users/jackshen/Desktop/timg.jpeg";
        File f = new File(filePath);
        try {

            String remotePath="/Users/jackshen/Downloads/devtools/demo";

           boolean flag=ftpUploader.upload(f, "xiaoxin.jpeg", "test");
            System.out.println("upload success:"+flag);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
