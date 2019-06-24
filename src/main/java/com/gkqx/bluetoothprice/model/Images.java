package com.gkqx.bluetoothprice.model;

import java.io.Serializable;
import java.util.Queue;

/**
 * @ClassName Images
 * @Description 生成的图片实体类
 * @Author Innocence
 * @Date 2019/5/8 000811:34
 * @Version 1.0
 **/
public class Images implements Serializable {
    private static final long serialVersionUID = -5604862259927994272L;
    // 会话id
	private Long sessionID;
	// wifiIP
	private String wifiIP;
	
	// 图片字节队列
	private Queue<Byte> imgQueue;
	// 每次发送字节大小
	private int size = 255;
	
	
	
	
	// -----------以上新增内容---------
	
    private Integer imgId;

    //图片名
    private String imgName;

    //图片路径
    private String imgPath;

    //关联的商品id
    private String goodsId;
    
    
    public Images() {}
    public Images(Long sessionID, Queue<Byte> imgQueue, String wifiIP) {
    	this.sessionID = sessionID;
    	this.imgQueue = imgQueue;
    	this.wifiIP = wifiIP;
    }
    
    
    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    
    // ------------------------新增---------------------
    public Long getSessionID() {
		return sessionID;
	}
	public void setSessionID(Long sessionID) {
		this.sessionID = sessionID;
	}
    
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public Queue<Byte> getImgQueue() {
		return imgQueue;
	}

	public void setImgQueue(Queue<Byte> imgQueue) {
		this.imgQueue = imgQueue;
	}
	public String getWifiIP() {
		return wifiIP;
	}
	public void setWifiIP(String wifiIP) {
		this.wifiIP = wifiIP;
	}
    
    
    
    
    
    
    
}
