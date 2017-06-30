package com.weweb.cloud.common.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by jackshen on 2017/6/4.
 */
public class Test {
    public static boolean upload(String server,String username,String password,File localfile,String destinationfile ){
        boolean Store=false;
        try{
            FTPClient ftp = new FTPClient();
            // ftp.connect(server);
    /* you can use either code which is written above above or below code as ftp port 20 is used for the data transfer and port 21 is used for command and controlls */
            ftp.connect(server,9921);
            //here 'server' is your domain name of ftp server or url
            if(!ftp.login(username, password))
            {
                ftp.logout();
                return false;
            }
            System.out.println("login successful");
            ftp.sendNoOp();//used so server timeout exception will not rise
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                return false;
            }
            ftp.enterLocalPassiveMode(); /* just include this line here and your code will work fine */
            InputStream in = new FileInputStream(localfile);
            // ftp.setFileType(ftp.BINARY_FILE_TYPE, ftp.BINARY_FILE_TYPE);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            // ftp.setFileTransferMode(ftp.BINARY_FILE_TYPE);
            Store = ftp.storeFile(destinationfile, in);
            in.close();
            //ftp.disconnect();
            //here logout will close the connection for you
            ftp.logout();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        return Store;
    }

    public static void main(String[] args) {
        String filePath="/Users/jackshen/Desktop/timg.jpeg";

        String remotePath="/Users/jackshen/Downloads/devtools/";
        boolean flag=upload("192.168.1.111","test1","test",new File(filePath),"test");
        System.out.println("succes:"+flag);
    }

}

