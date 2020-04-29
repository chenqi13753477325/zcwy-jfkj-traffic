package com.dashboard.adp.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dashboard.adp.api.service.BaseDataService;
import com.dashboard.adp.api.utils.JsonUtils;
import com.dashboard.adp.api.utils.ResultUtils;
import com.dashboard.adp.api.vo.Result;
import com.dashboard.adp.web.config.JwtTokenUtil;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("BaseData")
public class BaseDataController {

    private static final Logger logger = LoggerFactory.getLogger(BaseDataController.class);

    @Reference
    private BaseDataService service;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 获取国家
     * @param request
     * @return
     */
    @RequestMapping(value = "/country" ,method = {RequestMethod.POST, RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result country(HttpServletRequest request) {
        Result result;
        try {
            result = ResultUtils.success(service.getCountry());
        } catch (Exception e) {
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 获取区域
     * @param request
     * @return
     */
    @RequestMapping(value = "/regions" ,method = {RequestMethod.POST, RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result regions(HttpServletRequest request) {
        Result result;
        try {
            result = ResultUtils.success(service.getRegionInfos());
        } catch (Exception e) {
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 获取省份
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/province" ,method = {RequestMethod.POST, RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result province(@RequestBody String jsonStr,HttpServletRequest request) {
        Result result;
        try {
            String regions = "";
            String userName = "";
            final String requestHeader = request.getHeader(this.tokenHeader);
            if(requestHeader!=null && !"".equals(requestHeader)){
                String authToken = requestHeader.substring(7);
                userName = jwtTokenUtil.getUsernameFromToken(authToken);
            }
            if(jsonStr!=null && jsonStr!=""){
                Map<String, Object> mapObj = JsonUtils.toObject(jsonStr, Map.class);
                if(mapObj!=null && mapObj.containsKey("regions")){
                    regions = mapObj.get("regions").toString();
                }
            }
            result = ResultUtils.success(service.getProvinces(regions,userName));
        } catch (Exception e) {
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 获取企业性质
     * @param request
     * @return
     */
    @RequestMapping(value = "/erpNatures",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody Result erpNatures(HttpServletRequest request){
        Result result;
        try {
            String userName = "";
            final String requestHeader = request.getHeader(this.tokenHeader);
            if(requestHeader!=null && !"".equals(requestHeader)){
                String authToken = requestHeader.substring(7);
                userName = jwtTokenUtil.getUsernameFromToken(authToken);
            }
            result = ResultUtils.success(service.getEnterpriseNature(userName));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 获取客户
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/customer",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody Result customer(@RequestBody String jsonStr,HttpServletRequest request){
        Result result;
        try {
            Map<String, Object> mapObj = JsonUtils.toObject(jsonStr, Map.class);

            String userName = "";
            final String requestHeader = request.getHeader(this.tokenHeader);
            if(requestHeader!=null && !"".equals(requestHeader)){
                String authToken = requestHeader.substring(7);
                userName = jwtTokenUtil.getUsernameFromToken(authToken);
            }

            String enterpriseNatures = "";
            if(mapObj!=null && mapObj.containsKey("enterpriseNatures")){
                enterpriseNatures = mapObj.get("enterpriseNatures").toString();
            }
            result = ResultUtils.success(service.getCustomer(enterpriseNatures,userName));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 查询所有客户事业部
     * @param request
     * @return
     */
    @RequestMapping(value = "/department",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result department (HttpServletRequest request){
        Result result;
        try {
            String userName = "";
            final String requestHeader = request.getHeader(this.tokenHeader);
            if(requestHeader!=null && !"".equals(requestHeader)){
                String authToken = requestHeader.substring(7);
                userName = jwtTokenUtil.getUsernameFromToken(authToken);
            }
            result = ResultUtils.success(service.getDepartment(userName));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 获取销售主体
     * @param request
     * @return
     */
    @RequestMapping(value = "/saleSubject",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result saleSubject (HttpServletRequest request){
        Result result;
        String userName = "";
        final String requestHeader = request.getHeader(this.tokenHeader);
        if(requestHeader!=null && !"".equals(requestHeader)){
            String authToken = requestHeader.substring(7);
            userName = jwtTokenUtil.getUsernameFromToken(authToken);
        }
        try {
            result = ResultUtils.success(service.getSaleSubject(userName));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 销售事业部
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/saleDepts",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody Result saleDepts(@RequestBody String jsonStr, HttpServletRequest request) {
        Result result ;

        try
        {
            Map<String, Object> mapObj = JsonUtils.toObject(jsonStr, Map.class);/*toMap(jsonStr);*/
            String userName = "";
            final String requestHeader = request.getHeader(this.tokenHeader);
            if(requestHeader!=null && !"".equals(requestHeader)){
                String authToken = requestHeader.substring(7);
                userName = jwtTokenUtil.getUsernameFromToken(authToken);
            }

            String saleComps = "";
            if(mapObj!=null && mapObj.containsKey("saleComps")){
                saleComps = mapObj.get("saleComps").toString();
            }
            result = ResultUtils.success(service.getSaleDept(saleComps,userName));
        }
        catch (Exception e)
        {
            logger.error("捕获未知异常信息"+e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 获取所有模型平台
     * @param request
     * @return
     */
    @RequestMapping(value = "/modelPlats",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result modelPlats (HttpServletRequest request){
        Result result ;
        try {
            result = ResultUtils.success(service.queryModelPlatforms());
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 所有机型
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/contrmodModels",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result contrmodModels (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            Map<String, Object> mapObj = JsonUtils.toObject(jsonStr, Map.class);/*toMap(jsonStr);*/
            String platforms = "";
            String customer="";
            if(mapObj!=null && mapObj.containsKey("platforms")){
                platforms = mapObj.get("platforms").toString();
            }
            if(mapObj!=null && mapObj.containsKey("customer")){
                customer = mapObj.get("customer").toString();
            }
            result = ResultUtils.success(service.getContrmodModels(platforms,customer));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 所有机组环境
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/environments",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result environments (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            Map<String, Object> mapObj = JsonUtils.toObject(jsonStr, Map.class);/*toMap(jsonStr);*/
            String terrains = "";
            if(mapObj!=null && mapObj.containsKey("terrains")){
                terrains = mapObj.get("terrains").toString();
            }
            result = ResultUtils.success(service.getEnvironments(terrains));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 所有地形
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/terrains",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result terrains (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            result = ResultUtils.success(service.getTerrains());
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 所有风区
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/windresource",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result windresource (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            result = ResultUtils.success(service.getWindResources());
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
     }

     /**
     * 所有承运商
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/suppliers",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result suppliers (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            result = ResultUtils.success(service.getSuppliers());
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 所有工厂类型
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/factorytypes",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result factorytypes (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            result = ResultUtils.success(service.getFactoryTypes());
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 所有工厂
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/factorys",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result factorys (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            Map<String, Object> mapObj = JsonUtils.toObject(jsonStr, Map.class);/*toMap(jsonStr);*/
            String factorytypes = "";
            if(mapObj!=null && mapObj.containsKey("factorytypes")){
                factorytypes = mapObj.get("factorytypes").toString();
            }
            result = ResultUtils.success(service.getFactorys(factorytypes));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 所有生产大部件类型
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/largepartpypes",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result largepartpypes (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            result = ResultUtils.success(service.getLargePartTypes());
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 所有生产部件
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/largeparts",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result largeparts (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            Map<String, Object> mapObj = JsonUtils.toObject(jsonStr, Map.class);/*toMap(jsonStr);*/
            String largepartpypes = "";
            if(mapObj!=null && mapObj.containsKey("largepartpypes")){
                largepartpypes = mapObj.get("largepartpypes").toString();
            }
            result = ResultUtils.success(service.getLargeParts(largepartpypes));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 所有采购关键部件类型
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/keypartpypes",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result keypartpypes (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            result = ResultUtils.success(service.getKeyLargePartTypes());
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 所有采购关键部件
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/keyparts",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result keyparts (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            Map<String, Object> mapObj = JsonUtils.toObject(jsonStr, Map.class);/*toMap(jsonStr);*/
            String keypartpypes = "";
            if(mapObj!=null && mapObj.containsKey("keypartpypes")){
                keypartpypes = mapObj.get("keypartpypes").toString();
            }
            result = ResultUtils.success(service.getKeyLargeParts(keypartpypes));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 更新PMC列表信息
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/pmc",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result pmc (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            Map<String, Object> mapObj = JsonUtils.toObject(jsonStr, Map.class);/*toMap(jsonStr);*/
            if(mapObj.get("value")!=""){
                mapObj.put("value",
                        Integer.parseInt(mapObj.get("value").toString()));
            }else{
                mapObj.put("value",0);
            }
            result = ResultUtils.success(service.updateWarnList(mapObj));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 所有预警分类
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/warntypes",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result warntypes (@RequestBody String jsonStr,HttpServletRequest request){
        Result result ;
        try {
            result = ResultUtils.success(service.getWarnTypes());
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }


    /**
     * 获取token
     * @throws IOException
     */
    @RequestMapping(value = "/token",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result token (HttpServletRequest request){
        Result result;
        try {
            result = ResultUtils.success(service.getToken());
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

   /* @RequestMapping(value = "/token",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public void getToken() throws IOException {
        String token = service.getToken();
        String tokens = token.toString();
    }*/

    //获取天气
    @RequestMapping(value = "/futureWeather",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result futureWeather (String projectId,HttpServletRequest request)throws IOException, ParseException {
        Result result;
        try {
            result = ResultUtils.success(service.getfutureWeather(projectId));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    //获取天气
    @RequestMapping(value = "/weather",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody
    JSONArray weather (String projectId, HttpServletRequest request)throws IOException, ParseException {
        JSONArray jsonArray = null;
        try {
            jsonArray = service.getfutureWeather(projectId);
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);

        }
        return jsonArray;
    }

    //获取天气
    @RequestMapping(value = "/currentDayWeather",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody
    JSONArray currentDayWeather (String contractno, HttpServletRequest request)throws IOException, ParseException {
        JSONArray jsonArray = null;
        try {
            jsonArray = service.getSameDayWeather(contractno);
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);

        }
        return jsonArray;
    }

    //获取百度天气
    @RequestMapping(value = "/bdweather",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody
    JSONArray bdweather (@Param(value="contractno") String contractno, @Param(value="akey") String akey, HttpServletRequest request)throws IOException, ParseException {
        JSONArray jsonArray = null;
        try {
            jsonArray = service.getBaiduWeather(contractno,akey);
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);

        }
        return jsonArray;
    }

    /**
     * 根据地质获取坐标
     * @return
     */
    @RequestMapping(value = "/coordinate",method = { RequestMethod.GET})
    public Object coordinate (String address,String key){
        Object result ;
        try {

            String _key = "yVXL98ZoCxTBVWqibt9TGVYKyqYwwPRH";
            if(key!=null && !key.equals(""))
            {
                _key = key ;
            }
            /*result = this.getCoordinate(address,_key).toString();*/
            //数据返回格式调整
            result = JSONObject.fromObject(this.getCoordinate(address, _key));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = e.getMessage();
        }
        return result;
    }

    public Map<String, Object> getCoordinate(String addr,String keyParm) throws IOException {
        String address = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            address = java.net.URLEncoder.encode(addr, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
//		String key = "f247cdb592eb43ebac6ccd27f796e2d2";
        String key = keyParm;
        String url = String.format(
                "http://api.map.baidu.com/geocoder/v2/?address=%s&output=json&ak=%s",
                address, key);
        URL myURL = null;
        URLConnection httpsConn = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            httpsConn = (URLConnection) myURL.openConnection();// ��ʹ�ô���
            if (httpsConn != null) {
                insr = new InputStreamReader(httpsConn.getInputStream(),
                        "UTF-8");
                br = new BufferedReader(insr);
                System.out.println("insrΪ��"+insr);

                String data = br.readLine();
//				while ((data = br.readLine()) != null) {
//					if(data.indexOf("lng") != -1){
//						map.put("lng", data.split(":")[1].replaceAll(",", ""));
////						System.out.println("lng" + data);
//					}
//					if(data.indexOf("lat") != -1){
//						map.put("lat", data.split(":")[1]);
//					}
//				}
                Map mapTypes = JSON.parseObject(data);
//				for (Object obj : mapTypes.keySet()){
//		            System.out.println("keyΪ��"+obj+"ֵΪ��"+mapTypes.get(obj));
//		        }
                String[] arr = data.split(",");
//				System.out.println(arr.length);
//				System.out.println(arr[0].length());
//				System.out.println(arr[1]);
//				System.out.println(arr[1].substring(28, arr[1].length()));
//				System.out.println(arr[2]);
//				System.out.println(arr[2].substring(6, arr[2].length()-1));


                map.put("lng", arr[1].substring(28, arr[1].length()));
                map.put("lat", arr[2].substring(6, arr[2].length()-1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (insr != null) {
                insr.close();
            }
            if (br != null) {
                br.close();
            }
        }
        return map;
    }

    /**
     * 获取指标列表
     * @param
     * @param request
     * @return
     */
    @RequestMapping(value = "/indicators",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result quotalist (@RequestBody String warntypes,HttpServletRequest request){
        Result result ;
        try {
            java.util.Map map = JsonUtils.toObject(warntypes, java.util.Map.class);
            warntypes = (String)map.get("warntypes");
            result = ResultUtils.success(service.getQuotaList(warntypes));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
            return result;
    }

    /**
     * 获取保障类型
     * @param
     * @param request
     * @return
     */
    @RequestMapping(value = "/securitytype",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result protectiontype (HttpServletRequest request){
        Result result ;
        try {
            result = ResultUtils.success(service.protectiontype());
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }


    /**
     * 保存预警-预警清单合集
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveAdpWarningList",method = { RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result saveAdpWarningList (@RequestBody String jsonStr,HttpServletRequest request){
        Result result = ResultUtils.success() ;
        try {
            java.util.Map map = JsonUtils.toObject(jsonStr, java.util.Map.class);
            map.put("warnLevel","项目");
            int resultInt = service.saveAdpWarningList(map);
            if (resultInt == 1) {
                result.setResultCode("100");
                result.setResultInfo("保存成功");
            } else if(resultInt == 10){
                result.setResultCode("101");
                result.setResultInfo("当前选择的项目已经有类似的预警指标");
            } else {
                result = ResultUtils.failure("保存失败");
                result.setResultCode("102");
                result.setResultInfo("保存失败");
            }
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 删除预警-预警清单合集
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/delAdpWarningListbyId",method = { RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result delAdpWarningListbyId (@RequestBody String jsonStr,HttpServletRequest request){
        Result result = ResultUtils.success();;
        try {
            java.util.Map map = JsonUtils.toObject(jsonStr, java.util.Map.class);
            String id = (String) map.get("id");
            int resultInt = service.delAdpWarningList(id);
            if (resultInt > 0) {
                result.setResultCode("100");
                result.setResultInfo("删除成功");
            } else {
                result = ResultUtils.failure("账号不存在");
                result.setResultCode("102"); //修改账号失败
                result.setResultInfo("删除失败");
            }
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }

    /**
     * 通过预警分类获取预警指标
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/getWarnByWarnlo",method = { RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result getWarnByWarnlo (@RequestBody String jsonStr,HttpServletRequest request){
        Result result;
        try {
            java.util.Map map = JsonUtils.toObject(jsonStr, java.util.Map.class);
            String warnlo = (String) map.get("warnlo");
            result = ResultUtils.success(service.getWarnByWarnlo(warnlo));
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }


    
    /**
     * @Description: 添加描述
     * @Param: [采购-日报报缺日报-工厂列表]
     * @Return: com.dashboard.adp.api.vo.Result
     * @Date: 2019/7/9 17:35
     */
    @RequestMapping(value = "/selFactoryName",method = { RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public @ResponseBody  Result selFactoryName (){
        Result result ;
        try {
            result = ResultUtils.success(service.selFactoryName());
        }catch (Exception e){
            logger.error("捕获未知异常信息" + e);
            result = ResultUtils.failure(e.getMessage());
        }
        return result;
    }



}
