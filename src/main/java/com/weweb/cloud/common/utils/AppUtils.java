package com.weweb.cloud.common.utils;

import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by jackshen on 2017/6/4.
 */
public class AppUtils {
    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
    public static void main( String[] args ) {
        FTPClient client = new FTPClient();
        FileInputStream fis = null;
        try {
            client.connect("192.168.1.111",9921);
            showServerReply(client);
            client.login("admin", "admin123");
            showServerReply(client);
            System.out.println("Current working directory is: " + client.printWorkingDirectory());
            String someDirectory = "nonexistentDir";
            client.makeDirectory( someDirectory + "/Archive");
            showServerReply(client);

            client.logout();
            showServerReply(client);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
