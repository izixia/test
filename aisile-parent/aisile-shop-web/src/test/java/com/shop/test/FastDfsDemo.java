package com.shop.test;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.aisile.common.util.FastDFSClient;

/**
*
*@author：郭Sir
*@date：2018年7月14日 下午4:39:24
*/
public class FastDfsDemo {
	
	@Test
	public void FastDfsTest0(){
		FastDFSClient fastDFSClient;
		try {
			fastDFSClient = new FastDFSClient("D:/GongZuoQJ/aisile-parent/aisile-shop-web/src/main/resources/conf/client.conf");
			String string = fastDFSClient.uploadFile("C:/test1.jpg");
			System.out.println(string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void FastDfsTest(){
		//第一步加载地址
		try {
			ClientGlobal.init("D:/GongZuoQJ/aisile-parent/aisile-shop-web/src/main/resources/conf/client.conf");
			//创建一个TrackerClient对象
			TrackerClient trackerClient = new TrackerClient();
			//通过TrackClient获得一个TrackerServer对象
			TrackerServer trackerServer = trackerClient.getConnection();
			//创建一个StrorageServer的引用，可以是null
			StorageServer storageServer = null;
			//创建一个StorageClient，参数需要TrackerServer和StrorageServer
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			//使用StorageClient上传文件。
			String[] strings = storageClient.upload_file("C:/test1.jpg","jpg", null);
			for (String string : strings) {
				System.out.println(string);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	
	}

}
