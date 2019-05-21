package com.gkqx.bluetoothprice.common.pageCommon;
/**
* 后端分页使用的
* @author Innocence
* @date 2019/5/7 000709:40
* @param
* @return
*/
public class Page {
	 //    1.    每页显示的数量
    private int everyPage;
    
    //    2.    总条目数
    private int totalCount;
    
    //    3.    总页数
    private int totalPage;
    
    //    4.    当前页数
    private int currentPage;
    
    //    5.    起始页
    private int beginIndex;
    
    //    6.    是否有上一页
    private boolean hasPrePage;
    
    //    7.    是否还有下一页
    private boolean hasNextPage;
    
    public Page() {
    }

    public Page(int everyPage, int totalCount, int totalPage, int currentPage, int beginIndex, boolean hasPrePage,
            boolean hasNextPage) {
        super();
        this.everyPage = everyPage;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.beginIndex = beginIndex;
        this.hasPrePage = hasPrePage;
        this.hasNextPage = hasNextPage;
    }

    public int getEveryPage() {
        return everyPage;
    }

    public void setEveryPage(int everyPage) {
        this.everyPage = everyPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}

