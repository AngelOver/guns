package com.stylefeng.guns.core.fdfs;

import static org.junit.Assert.*;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FastDFSClientTest {

	@Test
    public void testUpload2() throws Exception {
        // 1、把FastDFS提供的jar包添加到工程中
        // 2、初始化全局配置。加载一个配置文件。
        ClientGlobal.init("fdfs_client.conf");
        System.out.println(ClientGlobal.configInfo());
        
        // 3、创建一个TrackerClient对象。
        TrackerClient trackerClient = new TrackerClient();
        // 4、创建一个TrackerServer对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        // 5、声明一个StorageServer对象，null。
        StorageServer storageServer = null;
        // 6、获得StorageClient对象。
        StorageClient storageClient = new StorageClient(trackerServer,  storageServer);
        // 7、直接调用StorageClient对象方法上传文件即可。
        String[] strings = storageClient.upload_file("E:\\my store\\doc\\test.jpg", "jpg", null);
        for (String string : strings) {
            System.out.println(string);
        }
    }
	
	/**
     * 文件上传测试
     */
    @Test
    public void testUpload() {
        File file = new File("E:\\my store\\doc\\test.jpg");
        Map<String,String> metaList = new HashMap<String, String>();
        metaList.put("width","1024");
        metaList.put("height","768");
        metaList.put("author","杨信");
        metaList.put("date","20161018");
        String fid = FastDFSClient.uploadFile(file,file.getName(),metaList);
        System.out.println("upload local file " + file.getPath() + " ok, fileid=" + fid);
        //上传成功返回的文件ID： group1/M00/00/00/wKgAyVgFk9aAB8hwAA-8Q6_7tHw351.jpg
    }

    /**
     * 文件下载测试
     */
    @Test
    public void testDownload() {
        int r = FastDFSClient.downloadFile("group1/M00/00/00/rBIoElqWZnKAei5_AAI_UsCihpU878.jpg", new File("d:\\DownloadFile_fid.jpg"));
        System.out.println(r == 0 ? "下载成功" : "下载失败");
    }
    

    /**
     * 文件下载测试
     */
    @Test
    public void testDownload2() {
    	byte[] content = null;
       content= FastDFSClient.downloadFile("group1/M00/00/00/rBIoElqWZnKAei5_AAI_UsCihpU878.jpg");
       System.out.println(content);
    }

    /**
     * 获取文件元数据测试
     */
    @Test
    public void testGetFileMetadata() {
        Map<String,String> metaList = FastDFSClient.getFileMetadata("group1/M00/00/00/wKgAyVgFk9aAB8hwAA-8Q6_7tHw351.jpg");
        for (Iterator<Map.Entry<String,String>>  iterator = metaList.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String,String> entry = iterator.next();
            String name = entry.getKey();
            String value = entry.getValue();
            System.out.println(name + " = " + value );
        }
    }

    /**
     * 文件删除测试
     */
    @Test
    public void testDelete() {
        int r = FastDFSClient.deleteFile("group1/M00/00/00/wKgAyVgFk9aAB8hwAA-8Q6_7tHw351.jpg");
        System.out.println(r == 0 ? "删除成功" : "删除失败");
    }
}
