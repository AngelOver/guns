package com.stylefeng.guns.modular.auth.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * <p>Description: FastDFS文件上传下载工具类 </p>
 * <p>Copyright: Copyright (c) 2016</p>
 *
 * @author yangxin
 * @version 1.0
 * @date 2016/10/19
 */
public class FastDFSClient {
	
	 //private static final String CONFIG_FILENAME = "src/main/resources/fdfs_client.conf";
	 private static final String CONFIG_FILENAME = "fdfs_client.conf";

	    private static StorageClient1 storageClient1 = null;

	    // 初始化FastDFS Client
	    static {
	        try {
	            ClientGlobal.init(CONFIG_FILENAME);
	            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
	            TrackerServer trackerServer = trackerClient.getConnection();
	            if (trackerServer == null) {
	                throw new IllegalStateException("getConnection return null");
	            }

	            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
	            if (storageServer == null) {
	                throw new IllegalStateException("getStoreStorage return null");
	            }

	            storageClient1 = new StorageClient1(trackerServer,storageServer);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * 上传文件
	     * @param file 文件对象
	     * @param fileName 文件名
	     * @return
	     */
	    public static String uploadFile(File file, String fileName) {
	        return uploadFile(file,fileName,null);
	    }

	    /**
	     * 上传文件
	     * @param file 文件对象
	     * @param fileName 文件名
	     * @param metaList 文件元数据
	     * @return
	     */
	    public static String uploadFile(File file, String fileName, Map<String,String> metaList) {
	        try {
	            byte[] buff = IOUtils.toByteArray(new FileInputStream(file));
	            NameValuePair[] nameValuePairs = null;
	            if (metaList != null) {
	                nameValuePairs = new NameValuePair[metaList.size()];
	                int index = 0;
	                for (Iterator<Map.Entry<String,String>> iterator = metaList.entrySet().iterator(); iterator.hasNext();) {
	                    Map.Entry<String,String> entry = iterator.next();
	                    String name = entry.getKey();
	                    String value = entry.getValue();
	                    nameValuePairs[index++] = new NameValuePair(name,value);
	                }
	            }
	            return storageClient1.upload_file1(buff,FilenameUtils.getExtension(fileName),nameValuePairs);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    /**
	     * 获取文件元数据
	     * @param fileId 文件ID
	     * @return
	     */
	    public static Map<String,String> getFileMetadata(String fileId) {
	        try {
	            NameValuePair[] metaList = storageClient1.get_metadata1(fileId);
	            if (metaList != null) {
	                HashMap<String,String> map = new HashMap<String, String>();
	                for (NameValuePair metaItem : metaList) {
	                    map.put(metaItem.getName(),metaItem.getValue());
	                }
	                return map;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    /**
	     * 删除文件
	     * @param fileId 文件ID
	     * @return 删除失败返回-1，否则返回0
	     */
	    public static int deleteFile(String fileId) {
	        try {
	            return storageClient1.delete_file1(fileId);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return -1;
	    }

	    /**
	     * 下载文件
	     * @param fileId 文件ID（上传文件成功后返回的ID）
	     * @param outFile 文件下载保存位置
	     * @return
	     */
	    public static int downloadFile(String fileId, File outFile) {
	        FileOutputStream fos = null;
	        try {
	            byte[] content = storageClient1.download_file1(fileId);
	            InputStream in = new ByteArrayInputStream(content);
	            fos = new FileOutputStream(outFile);
	            IOUtils.copy(in,fos);
	            return 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (fos != null) {
	                try {
	                    fos.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return -1;
	    }
	    

	    /**
	     * <strong>方法概要： 文件下载</strong> <br>
	     * <strong>创建时间： 2016-9-26 上午10:28:21</strong> <br>
	     * 
	     * @param String
	     *            groupName
	     * @param String
	     *            remoteFileName
	     * @return returned value comment here
	     * @author Wang Liang
	     */
	    public static ResponseEntity<byte[]> download(String groupName,
	            String remoteFileName,String specFileName) {
		        byte[] content = null;
		        HttpHeaders headers = new HttpHeaders();
		        try {
		            content = storageClient1.download_file(groupName, remoteFileName);
		            headers.setContentDispositionFormData("attachment",  new String(specFileName.getBytes("UTF-8"),"iso-8859-1"));
		            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
	    }
	    
	    
	    
	    /**
	     * <strong>方法概要： 文件上传</strong> <br>
	     * <strong>创建时间： 2016-9-26 上午10:26:11</strong> <br>
	     * 
	     * @param FastDFSFile
	     *            file
	     * @return fileAbsolutePath
	     * @author Wang Liang
	     */
//	    public static String upload(FastDFSFile file,NameValuePair[] valuePairs) {
//	        String[] uploadResults = null;
//	        try {
//	            uploadResults = storageClient1.upload_file(file.getContent(),file.getExt(), valuePairs);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        String groupName = uploadResults[0];
//	        String remoteFileName = uploadResults[1];
//
//	        String fileAbsolutePath = PROTOCOL
//	                + TRACKER_NGNIX_ADDR
//	                //+ trackerServer.getInetSocketAddress().getHostName()
//	                //+ SEPARATOR + TRACKER_NGNIX_PORT 
//	                + SEPARATOR + groupName
//	                + SEPARATOR + remoteFileName;
//	        return fileAbsolutePath;
//	    }
	    
	        
	        
	    /**
	     * 下载文件
	     * @param fileId 文件ID（上传文件成功后返回的ID）
	     * @param outFile 文件下载保存位置
	     * @return
	     */
	    public static byte[] downloadFile(String fileId) {
	        try {
	        	byte[] content = null;
	           content = storageClient1.download_file1(fileId);
	            return content;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


}
