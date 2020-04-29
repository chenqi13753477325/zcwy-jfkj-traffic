package com.dashobard.adp.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dashboard.adp.api.bean.AdJinFengWeather;
import com.dashboard.adp.api.service.BaseDataService;
import com.dashboard.adp.api.utils.HttpUtil;
import com.dashboard.adp.api.utils.JsonUtils;
import com.dashboard.adp.dao.BaseDataVOMapper;
import com.dashboard.adp.dao.RoleMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service(interfaceClass = BaseDataService.class)
public class BaseDataImpl implements BaseDataService {

    @Autowired
    BaseDataVOMapper dao;

    @Autowired
    RoleMapper roleMapper;

    public BaseDataImpl() {
        super();
    }

    @Override
    public List<Map<String, Object>> getCountry() {
        List<Map<String, Object>> list = dao.queryCountry();
        return list;
    }

    @Override
    public List<Map<String, Object>> getRegionInfos() {
        List<Map<String, Object>> list = dao.queryRegion();
        return list;
    }

    @Override
    public List<Map<String, Object>> getProvinces(String  regions,String userName) {
        String roleId = roleMapper.getRoleIdByUserIds(userName).toString();
        List<Map<String, Object>> list = dao.queryProvince(regions,roleId,userName);
        return list;
    }

    @Override
    public List<Map<String, Object>> getEnterpriseNature(String userName) {
        String roleId = roleMapper.getRoleIdByUserIds(userName).toString();
        List<Map<String, Object>> list = dao.queryEnterpriseNatures(roleId,userName);
        return list;
    }

    @Override
    public List<Map<String, Object>> getCustomer(String enterpriseNatures,String userName) {
        String roleId = roleMapper.getRoleIdByUserIds(userName).toString();
        List<Map<String, Object>> list = dao.queryCustomer(enterpriseNatures,roleId,userName);
        return list;
    }

    @Override
    public List<Map<String, Object>> getDepartment(String userName) {
        String roleId = roleMapper.getRoleIdByUserIds(userName).toString();
        List<Map<String, Object>> list = dao.queryDepartment(roleId,userName);
        return list;
    }

    @Override
    public List<Map<String, Object>> getSaleSubject(String userName) {
        String roleId = roleMapper.getRoleIdByUserIds(userName).toString();
        List<Map<String, Object>> list = dao.querySaleSubject(roleId,userName);
        return list;
    }

    @Override
    public List<Map<String, Object>> getSaleDept(String saleComps,String userName) {
        String roleId = roleMapper.getRoleIdByUserIds(userName).toString();
        List<Map<String, Object>> list = dao.querySaleDept(saleComps,roleId,userName);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryModelPlatforms() {
        List<Map<String, Object>> list = dao.queryModelPlatforms();
        return list;
    }

    @Override
    public List<Map<String, Object>> getContrmodModels(String platforms,String customer) {
        List<Map<String, Object>> list = dao.queryContrmodModels(platforms,customer);
        return list;
    }


    /**
     * 取得token
     *
     * @return
     * @throws IOException
     */
    @Override
    public String getToken() throws IOException {
        String info = HttpUtil.HuiNengGet("https://metapi.goldwind.com.cn:443/api/token", "FAMS_test", "K_jgEocmYDX0");
        if (!StringUtils.isEmpty(info)) {
            JSONObject tokenJson = JSON.parseObject(info);
            if (null != tokenJson) {
                String token = tokenJson.getString("token");
                if (!StringUtils.isEmpty(token)) {
                    return token;
                }
            }
        }
        return null;
    }

    /**
     * 查询7天数据
     * @return
     * @return
     *
     * @throws IOException
     * @throws ParseException
     */
    @Override
    @Transactional(rollbackForClassName = "Exception")
    public JSONArray getfutureWeather(String projectId) throws IOException, ParseException {
        String token = getToken();
        if(projectId == null){
            projectId="B00770";
        }
        JSONArray json = new JSONArray();
        if (!StringUtils.isEmpty(token)) {
            // Calendar ca = Calendar.getInstance(); JSONObject
            // ca.setTime(new Date());
            // 日期减1
            // ca.add(Calendar.DATE, -1);
            // String beforeDate = HttpUtil.date2String(ca.getTime(), "yyyy-MM-dd");
            // 查询经纬度
            List<AdJinFengWeather> LongLat = dao.queryLongLat(projectId);
            String Pj_latitude = null;
            String Pj_longitude = null;
            for (AdJinFengWeather longLat : LongLat) {
                Pj_latitude = longLat.getPj_latitude();
                Pj_longitude = longLat.getPj_longitude();
            }
            String info = HttpUtil.HuiNengGet("http://54.223.199.3:8082/weather/v1/fcst_pt/ec_0p1/?lon=" + Pj_longitude
                    + "&lat=" + Pj_latitude + "&vars=wspd_70,t_2&format=json", "FAMS_test", token);

            // 7天风功率信息
            List<AdJinFengWeather> weatherBean = new ArrayList<AdJinFengWeather>();

            if (!StringUtils.isEmpty(info)) {
                AdJinFengWeather sevenDay = JSON.parseObject(info, AdJinFengWeather.class);
                if (!StringUtils.isEmpty(sevenDay)) {
                    if (!StringUtils.isEmpty(sevenDay.getDatetime())) {
                        List<String> datetime = sevenDay.getDatetime();
                        for (int i = 0; i < datetime.size(); i++) {
                            AdJinFengWeather sevenEntity = new AdJinFengWeather();
                            Date date = HttpUtil.parseDate(datetime.get(i), "yyyy-MM-dd HH:mm:ss");
                            Calendar nowcal = Calendar.getInstance();
                            nowcal.setTime(date);
                            // 小时+8
                            nowcal.add(Calendar.HOUR_OF_DAY,8);
                            //owcal.add(Calendar.HOUR,0);

                            // 年月日  HH:mm:ss
                            String dateYYYYMMDD = HttpUtil.date2String(nowcal.getTime(), "dd");
                            // 年
                            String year = HttpUtil.date2String(nowcal.getTime(), "yyyy");
                            // 月
                            String month = HttpUtil.date2String(nowcal.getTime(), "MM");
                            // 日
                            String day = HttpUtil.date2String(nowcal.getTime(), "dd");
                            // 日
                            String time = HttpUtil.date2String(nowcal.getTime(), "HH:mm");
                            // 7天信息
                            sevenEntity.setPj_code(projectId);
                            sevenEntity.setDate(dateYYYYMMDD);
                            sevenEntity.setYear(year);
                            sevenEntity.setMonth(month);
                            sevenEntity.setDay(day);
                            sevenEntity.setWindtime(time);
                            sevenEntity.setPredictwind(sevenDay.getWspd_70().get(i));
                            sevenEntity.setTemperature(sevenDay.getT_2().get(i));
                            weatherBean.add(sevenEntity);
                        }
                    }
                }

                for (AdJinFengWeather pLog : weatherBean) {
                    String HH = pLog.getWindtime();
                    if ("12:00".equals(HH)) {
                        JSONObject jo = new JSONObject();
                        jo.put("date", pLog.getDate());
                        //取小数点后两位
                        java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
                        jo.put("predictwind", df.format(pLog.getPredictwind()));
                        jo.put("Temperature", df.format(pLog.getTemperature()));
                        json.add(jo);
                    }
                }
            }
        }
        return json;
    }

    /**
     * 查询7天数据
     * @return
     * @return
     *
     * @throws IOException
     * @throws ParseException
     */
    @Override
    @Transactional(rollbackForClassName = "Exception")
    public JSONArray getSameDayWeather(String contractno) throws IOException, ParseException {
        String token = getToken();
        if(contractno == null){
            contractno="GOLDWIND-2000-2018070";
        }
        JSONArray json = new JSONArray();
        if (!StringUtils.isEmpty(token)) {
            // 查询经纬度
            List<AdJinFengWeather> LongLat = dao.queryLongLat(contractno);
            String Pj_latitude = null;
            String Pj_longitude = null;
            for (AdJinFengWeather longLat : LongLat) {
                Pj_latitude = longLat.getPj_latitude();
                Pj_longitude = longLat.getPj_longitude();
            }
            String info = HttpUtil.HuiNengGet("http://54.223.199.3:8082/weather/v1/fcst_pt/ec_0p1/?lon=" + Pj_longitude
                    + "&lat=" + Pj_latitude + "&vars=wspd_70,t_2&format=json", "FAMS_test", token);

            // 7天风功率信息
            List<AdJinFengWeather> weatherBean = new ArrayList<AdJinFengWeather>();

            if (!StringUtils.isEmpty(info)) {
                AdJinFengWeather sevenDay = JSON.parseObject(info, AdJinFengWeather.class);
                if (!StringUtils.isEmpty(sevenDay)) {
                    if (!StringUtils.isEmpty(sevenDay.getDatetime())) {
                        List<String> datetime = sevenDay.getDatetime();
                        for (int i = 0; i < datetime.size(); i++) {
                            AdJinFengWeather sevenEntity = new AdJinFengWeather();
                            Date date = HttpUtil.parseDate(datetime.get(i), "yyyy-MM-dd HH:mm:ss");
                            Calendar nowcal = Calendar.getInstance();
                            nowcal.setTime(date);
                            // 小时+8
                            nowcal.add(Calendar.HOUR_OF_DAY,8);
                            //owcal.add(Calendar.HOUR,0);

                            // 年月日  HH:mm:ss
                            String dateYYYYMMDD = HttpUtil.date2String(nowcal.getTime(), "dd");
                            // 年
                            String year = HttpUtil.date2String(nowcal.getTime(), "yyyy");
                            // 月
                            String month = HttpUtil.date2String(nowcal.getTime(), "MM");
                            // 日
                            String day = HttpUtil.date2String(nowcal.getTime(), "dd");
                            // 日
                            String time = HttpUtil.date2String(nowcal.getTime(), "HH:mm");
                            // 7天信息
                            sevenEntity.setPj_code(contractno);
                            sevenEntity.setDate(dateYYYYMMDD);
                            sevenEntity.setYear(year);
                            sevenEntity.setMonth(month);
                            sevenEntity.setDay(day);
                            sevenEntity.setWindtime(time);
                            sevenEntity.setPredictwind(sevenDay.getWspd_70().get(i));
                            sevenEntity.setTemperature(sevenDay.getT_2().get(i));
                            weatherBean.add(sevenEntity);
                        }
                    }
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
                String sameDay = dateFormat.format(new Date());
                for (AdJinFengWeather pLog : weatherBean) {
                    String day = pLog.getDay();
                    if (sameDay.equals(day) && pLog.getWindtime().indexOf(":00")>=0) {
                        pLog.getDate();
                        JSONObject jo = new JSONObject();
                        jo.put("date", pLog.getWindtime());
                        //取小数点后两位
                        java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
                        jo.put("predictwind", df.format(pLog.getPredictwind()));
                        jo.put("Temperature", df.format(pLog.getTemperature()));
                        json.add(jo);
                    }
                }
            }
        }
        return json;
    }

    @Override
    public JSONArray getBaiduWeather(String contractno,String key) {
        JSONArray json = new JSONArray();
        String aKey = "FGDIm1ioaYSEPjsvnsMvgOMkAD3sZxPg";
        if(contractno == null){
            contractno="GOLDWIND-2000-2018070";
        }
        if(!StringUtils.isEmpty(key))
        {
            aKey = key;
        }

        try{
            if (!StringUtils.isEmpty(contractno)) {
                // 查询经纬度 112.51549586383865,37.86656599050925
                List<AdJinFengWeather> LongLat = dao.queryLongLat(contractno);
                String Pj_latitude = null;
                String Pj_longitude = null;
                for (AdJinFengWeather longLat : LongLat) {
                    Pj_latitude = longLat.getPj_latitude();
                    Pj_longitude = longLat.getPj_longitude();
                }
                String lon_lat = Pj_longitude+","+Pj_latitude;
                String info = HttpUtil.infoGet("http://api.map.baidu.com/telematics/v3/weather?" +
                        "location="+lon_lat+"&output=json&ak="+aKey);

                if (!StringUtils.isEmpty(info)) {
                    JSONObject jsonObj = JSONObject.parseObject(info);

                    JSONArray jsonArr = jsonObj.getJSONArray("results");

                    JSONArray objArr = jsonArr.getJSONObject(0).getJSONArray("weather_data");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dateStr = jsonObj.getString("date");
                    Date currentTime = new Date();
                    for (int i=0;i<objArr.size();i++) {
                        dateStr = dateFormat.format(new Date(currentTime.getTime() + i * 24 * 60 * 60 * 1000));
                        objArr.getJSONObject(i).put("date",dateStr);
                        json.add(objArr.getJSONObject(i));
                    }
                }
            }
        }
        catch (Exception e){

        }
        return json;
    }

    @Override
    public List<Map<String, Object>> getEnvironments(String terrains) {
        List<Map<String, Object>> list = dao.queryEnvironments(terrains);
        return list;
    }

    @Override
    public List<Map<String, Object>> getTerrains() {
        List<Map<String, Object>> list = dao.queryTerrains();
        return list;
    }

    @Override
    public List<Map<String, Object>> getWindResources() {
        List<Map<String, Object>> list = dao.queryWindResources();
        return list;
    }

    @Override
    public List<Map<String, Object>> getSuppliers() {
        List<Map<String, Object>> list = dao.querySuppliers();
        return list;
    }

    @Override
    public List<Map<String, Object>> getFactoryTypes() {
        List<Map<String, Object>> list = dao.queryFactoryTypes();
        return list;
    }

    @Override
    public List<Map<String, Object>> getFactorys(String factorytypes) {
        Map<String,Object> parm = new HashMap<>();
        if("'工厂'".equals(factorytypes))        {
            parm.put("isOnlyfacttory",0);
        }else if("'供应商'".equals(factorytypes))        {
            parm.put("isOnlyfacttory",1);
        }
        parm.put("factorytype",factorytypes);
        List<Map<String, Object>> list = dao.queryFactorys(parm);
        return list;
    }

    @Override
    public List<Map<String, Object>> getLargePartTypes() {
        List<Map<String, Object>> list = dao.queryLargePartTypes();
        return list;
    }

    @Override
    public List<Map<String, Object>> getLargeParts(String parttypes) {
        List<Map<String, Object>> list = dao.queryLargeParts(parttypes);
        return list;
    }

    @Override
    public List<Map<String, Object>> getKeyLargePartTypes() {
        List<Map<String, Object>> list = dao.queryKeyLargePartTypes();
        return list;
    }

    @Override
    public List<Map<String, Object>> getKeyLargeParts(String keyparttypes) {
        List<Map<String, Object>> list = dao.queryKeyLargeParts(keyparttypes);
        return list;
    }

    @Override
    public int updateWarnList(Map<String, Object> map) {
        int resultInt = dao.updateWarnList(map);
        return resultInt;
    }

    @Override
    public List<Map<String, Object>> getWarnTypes() {
        List<Map<String, Object>> list = dao.queryWarnTypes();
        return list;
    }

    @Override
    public List<Map<String,Object>> getQuotaList(String warntypes) {
        List<Map<String, Object>> list = dao.queryQuotaList(warntypes);
        return list;
    }

    @Override
    public List<Map<String,Object>> protectiontype() {
        return dao.protectiontype();
    }

    @Override
    public int saveAdpWarningList(Map<String, Object> adpWarningListMap) {
        String warnBodyCode = (String) adpWarningListMap.get("warnBodyCode");
        String warnStr = (String) adpWarningListMap.get("warn");
        if(warnBodyCode!=null && !"".equals(warnBodyCode) && warnStr !=null && !"".equals(warnStr)){
            int baseDateNameByCodeWarn = dao.getBaseDateNameByCodeWarn(warnBodyCode, warnStr);
            if(baseDateNameByCodeWarn > 0 ){
                return 10;
            }
        }
        if(warnBodyCode!=null && !"".equals(warnBodyCode)){
            String baseDateNameById = dao.getBaseDateNameById(warnBodyCode);
            adpWarningListMap.put("warnBody",baseDateNameById);
        }
        int resInt = 0;
        resInt = dao.saveAdpWarningList(adpWarningListMap);
        return resInt;
    }

    @Override
    public int delAdpWarningList(String id) {
        int resInt = 0;
        resInt = dao.delAdpWarningList(id);
        return resInt;
    }

    @Override
    public List<Map<String, Object>> getWarnByWarnlo(String warnlo) {
        return dao.getWarnByWarnlo(warnlo);
    }


    @Override
    public List<Map<String, Object>> selFactoryName() {
        return dao.selFactoryName();
    }

    @Override
//    public List<Map<String, Object>> videoList(Map<String, String> map) {
//        //1.构造去年，今年，明年 共12个月项目列表
//        Calendar c = Calendar.getInstance();
//
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
//        String format = simpleDateFormat.format(date);
//        c.setTime(date);
//        c.add(Calendar.MONTH, -1);
//        Date m = c.getTime();
//        //2.查询库中存储的列表，参数：合同编号
//
//        //3.比对列表，将视频名称赋值给1列表
//
//        //4.返回列表1
//        return null;
//    }

    public void saveVideoInfo(Map paramMap) {
        //1.判断是否存在此月份，参数合同编号
        Map<String,Object> params = dao.isVideo(paramMap);

        //2.如果存在，则进行修改 //
        if(params!=null && params.size()>0){
            String videoPathStr = (String) params.get("videoPath");
            if(videoPathStr!=null && !"".equals(videoPathStr)){
                String vedioPath = (String) paramMap.get("vedioPath");
                System.out.println();
                java.io.File file = new java.io.File(vedioPath+videoPathStr);
                System.out.println("文件位置："+vedioPath+videoPathStr+"；文件长度："+file.length());
                //判断目录或文件是否存在 && 判断是否为文件
                if(file.exists() && file.isFile()){
                    //删除文件
                    file.delete();
                    System.out.println("删除文件成功！！！");
                }
            }
            //如果存在进行更新
            dao.update(paramMap);
        }else{
            // 不存在则执行新增操作
            dao.insert(paramMap);
        }
    }

    /**
     * @Description: 各阶段情况-计划页面，模板生产
     * @Param: [params]
     * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Date: 2020/4/21 14:05
     */
    @Override
    public List<Map<String, Object>> templetList(Map<String, Object> params) {





        return null;
    }
}
