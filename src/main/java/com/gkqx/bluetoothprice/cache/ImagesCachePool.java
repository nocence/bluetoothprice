package com.gkqx.bluetoothprice.cache;

import com.gkqx.bluetoothprice.model.Images;

import java.util.Hashtable;
import java.util.Map;

/**
 * 发送图片缓存池
 * @author waver
 *
 */
public class ImagesCachePool {
	private static Map<Long, Images> tab = new Hashtable<Long, Images>();
	
	/**
	 * 根据会话获取图片对象
	 * @param sessionID
	 * @return
	 */
	public static Images getImages(Long sessionID) {
		return tab.get(sessionID);
	}
	
	public static void addImages(Long sessionID, Images img) {
		tab.put(sessionID, img);
	}
	
	
	public static void removeImages(Long sessionID) {
		tab.remove(sessionID);
	}
	
	
	
	
	
	
	
}
