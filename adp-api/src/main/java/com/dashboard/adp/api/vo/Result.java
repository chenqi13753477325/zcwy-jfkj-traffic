package com.dashboard.adp.api.vo;

import java.io.Serializable;
import java.time.Instant;

/**
 * Copyright © 2016 ICST. All rights reserved.
 *
 * @Description: TODO
 * @Author: CosmicSouls
 * @CreateDate: 2018/3/31 14:31
 * @Remark: The remark content
 * @Version: V1.0
 * Copyright: Copyright (c) 2018
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    /**系统定义错误码*/
    private String errCode = "";
    /**异常或者系统定义具体功能的错误信息*/
    private String errInfo="";
    /**0(表示失败)或者1(表示成功)*/
    private String resultCode = "0";
    /**失败或者成功的提示信息，是给用户友好的信息提示*/
    private String resultInfo="";
    /**查询结果，调用查询接口时才会返回上去，添加、删除、修改等一些提交性的接口不需要*/
    private Object resultData;
    /**是否加密：0-不加密，1-加密*/
    private Integer encrypt = 0;

    //构造函数
    public Result() {

    }

    public String getErrCode() {
        return errCode;
    }



    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }



    public String getErrInfo() {
        return errInfo;
    }



    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }



    public String getResultCode() {
        return resultCode;
    }



    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }



    public String getResultInfo() {
        return resultInfo;
    }



    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }



    public Object getResultData() {
        return resultData;
    }



    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Integer encrypt) {
        this.encrypt = encrypt;
    }

    public void setError(String errCode,String errInfo)
    {
        this.errCode=errCode;
        this.errInfo=errInfo;
    }
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{errCode=").append(errCode).append(",");
        sb.append("errInfo=").append(errInfo).append(",");
        sb.append("resultCode=").append(resultCode).append(",");
        sb.append("resultInfo=").append(resultInfo).append(",");
        sb.append("encrypt=").append(encrypt);
        if(resultData!=null)
            sb.append(",resultData=[").append(resultData.toString()).append("]}");
            //sb.append(",resultData=[").append(JsonDateUntil.toJson(resultData)).append("]}");
        else
            sb.append("}");
        return sb.toString();
    }
}
