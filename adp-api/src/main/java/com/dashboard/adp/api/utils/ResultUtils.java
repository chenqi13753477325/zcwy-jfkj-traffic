package com.dashboard.adp.api.utils;

import com.dashboard.adp.api.vo.ResultCode;
import com.dashboard.adp.api.vo.Result;

/**
 * Copyright © 2016 ICST. All rights reserved.
 *
 * @Description: TODO
 * @Author: CosmicSouls
 * @CreateDate: 2018/3/31 14:27
 * @Remark: The remark content
 * @Version: V1.0
 * Copyright: Copyright (c) 2018
 */
public class ResultUtils {

    public static Result success(Object object, String resultInfo) {

        Result result = new Result();

        result.setResultCode(ResultCode.Success.getCode());

        result.setResultInfo(resultInfo);

        result.setResultData(object);

        return result;

    }

    public static Result success(Object object, ResultCode errCode) {

        Result result = new Result();

        result.setResultCode(ResultCode.Success.getCode());

        result.setResultInfo(ResultCode.Success.getDescription());

        result.setResultData(object);

        if (errCode != null) {
            result.setErrCode(errCode.getCode());

            result.setErrInfo(errCode.getDescription());
        }

        return result;

    }

    public static Result success(ResultCode resultCode) {

        Result result = new Result();

        result.setResultCode(resultCode.getCode());

        result.setResultInfo(resultCode.getDescription());

        return result;

    }

    public static Result success(ResultCode resultCode, ResultCode errCode) {

        Result result = new Result();

        result.setResultCode(resultCode.getCode());

        result.setResultInfo(resultCode.getDescription());

        result.setErrCode(errCode.getCode());

        result.setErrInfo(errCode.getDescription());

        return result;

    }

    public static Result success(Object object) {


        return success(object, ResultCode.Success.getDescription());

    }

    public static Result success(String resultinfo) {

        return success(null, resultinfo);

    }

    public static Result success() {

        return success(null, ResultCode.Success.getDescription());

    }

    /**
     * 未知异常
     */
    public static Result failure(String errMsg) {
        return failure(ResultCode.SystemException.getCode(), errMsg, ResultCode.SystemException.getDescription());
    }

    public static Result failure(String errCode, String errMsg, String resultInfo) {

        Result result = new Result();

        result.setResultCode(ResultCode.Failure.getCode());

        result.setErrCode(errCode);

        result.setErrInfo(errMsg);

        result.setResultInfo(resultInfo);

        return result;

    }

    public static Result failure(ResultCode resultCode, ResultCode errCode) {

        Result result = new Result();

        result.setResultCode(resultCode.getCode());

        result.setResultInfo(resultCode.getDescription());

        result.setErrCode(errCode.getCode());

        result.setErrInfo(errCode.getDescription());

        return result;

    }

    public static Result failure(ResultCode resultCode) {

        Result result = new Result();

        result.setResultCode(resultCode.getCode());

        result.setResultInfo(resultCode.getDescription());

        return result;

    }
}
