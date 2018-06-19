package org.jypj.dev.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUploadUtil {
    public static String getHost() {
        PropertiesUtil p = new PropertiesUtil("config/conf.properties");
        return p.getValue("FTPHOST");
    }
    
    
    public static Integer getPort() {
        PropertiesUtil p = new PropertiesUtil("config/conf.properties");
        return Integer.parseInt(p.getValue("FTPPORT"));
    }
    
    public static String getUsername() {
        PropertiesUtil p = new PropertiesUtil("config/conf.properties");
        return p.getValue("FTPUSERNAME");
    }
    
    public static String getPassword() {
        PropertiesUtil p = new PropertiesUtil("config/conf.properties");
        return p.getValue("FTPPASSWORD");
    }
    
    public static String getOutUrl() {
        PropertiesUtil p = new PropertiesUtil("config/conf.properties");
        return p.getValue("OUTTIME");
    }
    public static String getDefaultImage() {
        PropertiesUtil p = new PropertiesUtil("config/conf.properties");
        return p.getValue("DEFAULTIMAGE");
    }
    
    public static String getStudentOutUrl() {
        PropertiesUtil p = new PropertiesUtil("config/conf.properties");
        return p.getValue("SOUTTIME");
    }
    
    public static String getFileServer() {
        PropertiesUtil p = new PropertiesUtil("config/conf.properties");
        return p.getValue("FILESERVER");
    }
    
    public static String getReportUrl() {
        PropertiesUtil p = new PropertiesUtil("config/conf.properties");
        return p.getValue("reportUrl");
    }
    
    /**
     * 获取ftp文件输入流
     * http://jszp.dgjy.net:81/f6/f6b6341ada3646b6813497dc3214b67d.jpg
     * @author QICAI
     * @param path /f6/
     * @param fileName f6b6341ada3646b6813497dc3214b67d.jpg
     * @return InputStream
     */
    private static InputStream getFtpFileInputStream(String path, String fileName){
    	InputStream in = null;
        FTPClient client = null;
        try {
            client = getFtpClient();
            client.setControlEncoding("UTF-8");
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.enterLocalPassiveMode();
            client.changeWorkingDirectory(path);
            in = client.retrieveFileStream(fileName);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return in;
    }
    
    /**
     * 获取FTP参数
     * @author QICAI
     * @param path
     * @return
     */
    private static String [] getFtpFileParameter(String path){
		String [] parameters=new String [2];
    	String filePath=path.replaceAll("//", "");
		filePath=filePath.substring(filePath.indexOf("/"), filePath.lastIndexOf("/")+1);
		String fileName=path.substring(path.lastIndexOf("/")+1, path.length());
		parameters[0]=filePath;
		parameters[1]=fileName;
    	return parameters;
    }
    
    /**
     * 获取ftp文件输入流
     * @author QICAI
     * @param path http://jszp.dgjy.net:81/3a/3a841472fe334d3887346d4dc646f9f2.jpg
     * @return InputStream
     */
    public static InputStream getFtpFileInputStream(String path){
    	String [] parameters=getFtpFileParameter(path);
    	return getFtpFileInputStream(parameters[0],parameters[1]);
    }
    
    /**
     * 指定的ftp服务器路径下读取文件
     * 
     * @param path
     * @param fileName
     * @return
     */
    public static String readFilesFromFtp(String path, String fileName) {
        StringBuffer result = new StringBuffer();
        InputStream in = null;
        FTPClient client = null;

        try {
            client = getFtpClient();
            client.setControlEncoding("UTF-8");
            client.setFileType(client.BINARY_FILE_TYPE);
            client.enterLocalPassiveMode();
            client.changeWorkingDirectory(path);
            in = client.retrieveFileStream(fileName);
        } catch (SocketException e) {
            e.printStackTrace();
            return "链接FTP失败！";
        } catch (IOException e) {
            e.printStackTrace();
            return "文件读取失败！";
        }

        if (in != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String data = null;
            try {
                while ((data = br.readLine()) != null) {
                    result.append(data + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "文件读取失败！";
            }

        } else {
            return "文件读取失败！";
        }

        return result.toString();
    }


    /**
     * 根据IP、端口、用户名密码等信息建立与服务器的连接，并返回FTPClient
     * 
     * @return
     * @throws SocketException
     */
    public static FTPClient getFtpClient() throws SocketException {
        FTPClient client = null;
        try {
            client = new FTPClient();
            client.connect(getHost(), getPort());
            client.login(getUsername(), getPassword());
            client.enterLocalPassiveMode();//PASV方式访问
            client.setFileType(client.BINARY_FILE_TYPE);
            if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
                // ftp链接失败
                client.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }


    /**
     * 文档类型的上传
     * @param path
     * @param content
     * @param WriteTempPath
     */
    public static boolean upload(String localFileName, String ftpDic, String ftpFileName) {
        FTPClient client = null;
        FileInputStream fis = null;
        boolean flag = false;
        try {
            client = getFtpClient();
            File srcFile = new File(localFileName);
            fis = new FileInputStream(srcFile);
            mkDir(ftpDic, client);
            client.setBufferSize(1024 * 1024 * 50);
            client.setControlEncoding("UTF-8");
            flag = client.storeFile(ftpFileName, fis);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    
    /**
     * 循环创建目录，并且创建完目录后，设置工作目录为当前创建的目录下
     */
    public static boolean mkDir(String ftpPath, FTPClient client) {
        try {
            char[] chars = ftpPath.toCharArray();
            StringBuffer sbStr = new StringBuffer(256);
            for (int i = 0; i < chars.length; i++) {
                if ('\\' == chars[i]) {
                    sbStr.append('/');
                } else {
                    sbStr.append(chars[i]);
                }
            }
            ftpPath = sbStr.toString();
            System.out.println("ftpPath" + ftpPath);
            if (ftpPath.indexOf('/') == -1) {
                // 只有一层目录
                client.makeDirectory(new String(ftpPath.getBytes(), "iso-8859-1"));
                client.changeWorkingDirectory(new String(ftpPath.getBytes(), "iso-8859-1"));
            } else {
                // 多层目录循环创建
                String[] paths = ftpPath.split("/");
                // String pathTemp = "";
                for (int i = 0; i < paths.length; i++) {
                    client.makeDirectory(new String(paths[i].getBytes(), "iso-8859-1"));
                    client.changeWorkingDirectory(new String(paths[i].getBytes(), "iso-8859-1"));
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将文件写入临时文件路径
     * @param fileName
     * @param content
     * @param tempPath
     * @return
     */
    public static boolean writeFilesToTempPath(String fileName, String content, String tempPath) {
        try {
            File f = new File(tempPath + "/" + fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.write(content.replaceAll("\n", "\r\n"));
            bw.flush();
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    /**
     * 判断文件是否是图片
     * @param fileName QQ图片20161108104846.jpg
     * @return String 1图片文件 0非图片文件 
     */
    public static String isImage(String fileName){
    	String isImage="0";
    	List<String> imagesList=Arrays.asList(new String []{".jpg",".jpeg",".png",".gif"});
    	if(StringUtils.isNotBlank(fileName)){
    		if(fileName.lastIndexOf(".") > -1){
    			String suffix=fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
    			if(imagesList.contains(suffix)){
    				isImage="1";
    			}
    		}
    		
    	}
    	return isImage;
    }

}
