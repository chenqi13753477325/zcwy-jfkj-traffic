package com.dashboard.adp.api.utils;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/*import cn.com.goldwind.ercp.module.api.ApiParamCity;
import cn.com.goldwind.ercp.module.api.ApiParamCityResult;*/

public class HttpUtil {

    public static final MediaType POSTJSON = MediaType.parse("application/json; charset=utf-8");

    public static String postByJson(String url, String json) {
        String resultJson = null;
        // 申明给服务端传递一个json串
        // 创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().retryOnConnectionFailure(true)
                .connectTimeout(500, TimeUnit.SECONDS).readTimeout(500, TimeUnit.SECONDS).build();
        // 创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(POSTJSON, json);
        // 创建一个请求对象
        Request request = new Request.Builder().url(url).post(requestBody).build();
        // 发送请求获取响应
        try {
            Response response = okHttpClient.newCall(request).execute();
            // 判断请求是否成功
            if (response.isSuccessful()) {
                resultJson = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    /**
     * 慧能气象专用
     * 
     * @param url
     *            地址
     * @param useId
     *            用户名
     * @param pwd
     *            密码
     * @return
     * @throws IOException
     */
    public static String HuiNengGet(String url, String useId, String pwd) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().retryOnConnectionFailure(true)
                .connectTimeout(500, TimeUnit.SECONDS).readTimeout(500, TimeUnit.SECONDS).build();
        //SslUtil.SslParams sslParams = SslUtil.getSslSocketFactory(null, null, null);
        String credential = Credentials.basic(useId, pwd);
        Request request = new Request.Builder().url(url).header("Authorization", credential).build();

       /* client = new OkHttpClient.Builder().connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(60000L, TimeUnit.MILLISECONDS)
                *//** 其他配置 *//*
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }).sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager).build();
*/
        Response response = client.newCall(request).execute();

        if (!StringUtils.isEmpty(response) && response.code() == 200 && !StringUtils.isEmpty(response.body())) {
            return response.body().string();
        }
        return null;
    }



    /**
     * 通用url get方法
     * 
     * @param url
     * @return
     * @throws IOException
     */
    public static String infoGet(String url) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().retryOnConnectionFailure(true)
                .connectTimeout(500, TimeUnit.SECONDS).readTimeout(500, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (!StringUtils.isEmpty(response) && response.code() == 200 && !StringUtils.isEmpty(response.body())) {
            String info = response.body().string();
            return info;
        }
        return null;
    }

    /**
     * 返回字符串
     * 
     * @param urlParam
     * @param json
     * @return
     */
    public static String modelStringPostByJson(String urlParam, Object json) {
        String resultJson = null;
        // 申明给服务端传递一个json串
        // 创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().retryOnConnectionFailure(true)
                .connectTimeout(500, TimeUnit.SECONDS).readTimeout(500, TimeUnit.SECONDS).build();
        // 创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(POSTJSON, JSON.toJSONString(json));
        // 创建一个请求对象
        // Request request = new
        // Request.Builder().url("http://127.0.0.1:8089/model/api/" +
        // urlParam).post(requestBody).build();
        Request request = new Request.Builder().url(urlParam).post(requestBody).build();
        // 发送请求获取响应
        try {
            Response response = okHttpClient.newCall(request).execute();
            // 判断请求是否成功
            if (response.isSuccessful()) {
                resultJson = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    //日期格式转化
	public static String date2String(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	public static Date parseDate(String string, String string2) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(string2);
		Date d1 = null;
		try {
			d1 = simpleDateFormat.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d1;
	
	}
	
	


	
}
