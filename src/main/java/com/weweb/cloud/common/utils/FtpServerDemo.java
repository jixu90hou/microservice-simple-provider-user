/*
package com.weweb.cloud.common.utils;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;

*/
/**
 * Created by jackshen on 2017/6/4.
 *//*

public class FtpServerDemo {
    public static void main(String[] args) throws FtpException {
        FtpServerFactory serverFactory = new FtpServerFactory();
        FtpServer server = serverFactory.createServer();
        ListenerFactory factory = new ListenerFactory();
        factory.setPort(2222);
        serverFactory.addListener("default", factory.createListener());

        BaseUser user = new BaseUser();
        //user.setName("anonymous");
        user.getAuthorities().forEach(s-> System.out.println(s));
        user.setName("lizhiwei1");
        user.setPassword("test");
        user.setHomeDirectory("/users/jackshen/Downloads/devtools");
        user.setEnabled(true);
        serverFactory.getUserManager().save(user);
        System.out.println("---->"+serverFactory.getFtplets());

        server.start();

    }
}
*/
