package com.gkqx.bluetoothprice.common.pageCommon;

import org.springframework.stereotype.Component;

import java.util.List;

/**
* 传到前端的，用于展示给用户
* @author Innocence
* @date 2019/5/7 000709:41
* @param
* @return
*/
@Component
public class PageResult<T> {
	/**
	 * 当前页数
	 */
	private Integer nowPage;
	/**
	 * 总页数
	 */
	private Integer totalPage;
	private List<T> list;
	/**
	 * 总数据条数
	 */
	private Integer totalData;
	
	
	public PageResult() {
		super();
	}

	public PageResult(Integer nowPage, Integer totalPage, List<T> list, Integer totalData) {
		super();
		this.nowPage = nowPage;
		this.totalPage = totalPage;
		this.list = list;
		this.totalData = totalData;
	}

	public Integer getTotalData() {
		return totalData;
	}

	public void setTotalData(Integer totalData) {
		this.totalData = totalData;
	}
	
	public Integer getNowPage() {
		return nowPage;
	}

	public void setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageResult [nowPage=" + nowPage + ", totalPage=" + totalPage + ", list=" + list + ", totalData="
				+ totalData + "]";
	}


	
}
