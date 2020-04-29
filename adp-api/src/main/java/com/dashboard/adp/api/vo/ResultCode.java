package com.dashboard.adp.api.vo;

/**
 * Copyright © 2016 ICST. All rights reserved.
 *
 * @Description: TODO
 * @Author: CosmicSouls
 * @CreateDate: 2018/3/31 14:30
 * @Remark: The remark content
 * @Version: V1.0
 * Copyright: Copyright (c) 2018
 */
public enum ResultCode {

    /**系统未知异常*/
    SystemException("-1","系统未知异常"),
    /**操作失败*/
    Failure("0","操作失败"),
    /**操作成功*/
    Success("1","操作成功"),
    ReLogin("2","重新登录"),
    TokenExpired("3","会话过期"),
    /**服务内部异常*/
    ServiceRunException("99","服务内部异常"),
    /**平台服务连接失败*/
    ConnectFailed("100","平台服务连接失败"),
    /**参数格式错误*/
    ParameterFormatError("101","参数格式错误"),
    /**参数类型不匹配*/
    ParameterTypeError("102","参数类型不匹配"),
    /**请求头校验失败*/
    RequestHeaderCheckFailure("103","请求头校验失败"),

    /**此手机号已经注册，请更换手机号！*/
    UserExists("10000","此手机号已经注册，请更换手机号！"),

    /**登录成功*/
    LoginSuccess("20001","登录成功"),

    /**帐号或密码错误，请重新填写。*/
    LoginUserError("20002","帐号或密码错误，请重新填写。"),

    UserNotExists("20013","此手机号尚未注册，请先注册。"),
    VerificationCodeError("20014","验证码错误，请重新获取验证码并验证。");

    private String code;
    private String description;
    private ResultCode(String code, String desc) {
        this.code = code;
        this.description = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
    /***
     *
     * @author zengjie
     * @date 2017年3月14日
     */
    public enum CodeDefine{
        /**1-9999，平台通用信息编码*/
        CommonCode("1-9999","平台通用信息编码"),
        /**10000-19999,业务通用信息编码*/
        CommonCodeService("10000-19999","业务通用信息编码"),
        /**20000-20999,后台管理编码*/
        BackgroundServiceCode("20000-20999","后台管理编码"),
        /**21000-21999,手机客户端编码*/
        MobileClientCode("21000-21999","手机客户端编码");

        private String code;
        private String description;
        private CodeDefine(String code, String desc) {
            this.code = code;
            this.description = desc;
        }
        public String getCode() {
            return this.code;
        }
        public String getDescription() {
            return this.description;
        }
    }
}
