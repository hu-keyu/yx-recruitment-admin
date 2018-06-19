package org.jypj.dev.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.SocketException;

import org.junit.Test;
import org.jypj.dev.util.FtpUploadUtil;

public class FtpUploadUtilTest {

    @Test
    public void testUpload() throws SocketException, FileNotFoundException {
        FtpUploadUtil.upload("E:\\FFOutput\\cf.gif", "/up/test/", "xxxx.gif");
        
    }

    @Test
    public void testReadFilesFromFtp() {
/*        String txt = FtpUploadUtil.readFilesFromFtp("/share/test/", "exe.txt");
        System.out.println(txt);*/
    }

}
