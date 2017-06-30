package com.weweb.cloud.common.utils;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

/**
 * A program that demonstrates how to upload files from local computer
 * to a remote FTP server using Apache Commons Net API.
 *
 * @author www.codejava.net
 */
public class FTPUploadFileDemo {
    public static void main(String args[]) {

        // get an ftpClient object
        FTPClient ftpClient = new FTPClient();
        FileInputStream inputStream = null;

        try {
            // pass directory path on server to connect
            ftpClient.connect("192.168.1.111",9921);
            // pass username and password, returned true if authentication is
            // successful
            boolean login = ftpClient.login("admin", "admin123");

            if (login) {
                System.out.println("Connection established...");
                String filePath="/Users/jackshen/Desktop/timg.jpeg";
                String remotePath="/Users/jackshen/Downloads/devtools";
                System.out.println("---->"+new File(filePath).exists());
                ftpClient.enterLocalPassiveMode();//Switch to passive mode

                inputStream = new FileInputStream(filePath);
                ftpClient.setControlEncoding("UTF-8");


                ftpClient.setFileTransferMode(ftpClient.BINARY_FILE_TYPE);

                boolean uploaded = ftpClient.storeFile("test",
                        inputStream);
                int code=ftpClient.getReplyCode();
                System.out.println("code:"+code);
                if (uploaded) {
                    System.out.println("File uploaded successfully !");
                } else {
                    System.out.println("Error in uploading file !");
                }

                // logout the user, returned true if logout successfully
                boolean logout = ftpClient.logout();
                if (logout) {
                    System.out.println("Connection close...");
                }
            } else {
                System.out.println("Connection fail...");
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


