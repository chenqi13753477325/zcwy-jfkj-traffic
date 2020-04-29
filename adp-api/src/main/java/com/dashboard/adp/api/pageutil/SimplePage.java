package com.dashboard.adp.api.pageutil;
import java.io.Serializable;

/**
 * 分页数据封装类
 * @author liupengcheng
 *
 */
public class SimplePage implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1083390664693258231L;
    /**
     * 分页查询的行数
     */
    protected int             pageSize         = 20;
    /**
     * 分页查询的起始行数
     */
    protected int             startIndex       = 0;

    /**
     * 记录总数
     */
    protected int             recordsTotal     = 0;

    /**
     * 取得分页数
     */
    protected int             pageNo       = 0;

    /**
     * 取得分页总数
     */
    protected int             pageTotalNum     = 0;

    /**
     *表示请求次数
     */
    protected int         draw=1;

    /**
     *
     * @return pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     *
     * @param pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     *
     */
    public SimplePage() {
        super();
    }

    /**
     *
     * @param pageSize
     * @param startIndex
     * @param recordsTotal
     */
    public SimplePage(int pageSize, int startIndex, int recordsTotal) {
        super();
        this.pageSize = pageSize;
        if(startIndex < 0)
        	this.startIndex = 0;
        else
        	this.startIndex = startIndex;
    }

    /**
     *
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     *
     * @return startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     *
     * @param startIndex
     */
    public void setStartIndex(int startIndex) {
    	if(startIndex < 0)
    		this.startIndex = 0;
    	else
    		this.startIndex = startIndex;
    }

    /**
     *
     * @return recordsTotal
     */
    public int getRecordsTotal() {
        return recordsTotal;
    }

    /**
     *
     * @param recordsTotal
     */
    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    /**
     *
     * @return pageTotalNum
     */
    public int getPageTotalNum() {
        if(this.getRecordsTotal()%this.pageSize>0){
            return (this.getRecordsTotal()/this.pageSize)+1;
        }else{
            return this.getRecordsTotal()/this.pageSize;
        }
    }

    /**
     *
     * @param pageTotalNum
     */
    public void setPageTotalNum(int pageTotalNum) {
        this.pageTotalNum = pageTotalNum;
    }

    /**
     *
     * @return draw
     */
    public int getDraw() {
        return draw;
    }

    /**
     *
     * @param draw
     */
    public void setDraw(int draw) {
        this.draw = draw;
    }
}
