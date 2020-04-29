package com.dashboard.adp.api.utils;

import java.io.Serializable;

/**
 * Copyright © 2016 ICST. All rights reserved.
 *
 * @ProjectName: cityos-parent
 * @Package: cityos.core.common.utils
 * @ClassName: StringUtils.java
 * @Description: TODO
 * @Author: CosmicSouls
 * @CreateDate: 2018/3/31 1:18
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark: The modified content
 * @Version: V1.0
 * Copyright: Copyright (c) 2018
 */
public class StringUtils implements Serializable
{

        private static final long serialVersionUID = 1L;

        public static boolean isEmpty(String str) {
            if(null == str || "".equals(str) || "".equals(str.trim()))
                return true;
            return false;
        }

        public static boolean isNotEmpty(String str) {
            return !isEmpty(str);
        }

        /**
         * 字符串转换为字符串数组
         * @param str 字符串
         * @param splitRegex 分隔符
         * @return
         */
        public static String[] str2StrArray(String str,String splitRegex){
            if(isEmpty(str)){
                return null;
            }
            return str.split(splitRegex);
        }

        /**
         * 用默认的分隔符(,)将字符串转换为字符串数组
         * @param str   字符串
         * @return
         */
        public static String[] str2StrArray(String str){
            return str2StrArray(str,",\\s*");
        }

        public static String[] str2StrArrayByABC(String str)
        {
            String[] strArray=new String[str.length()];
            for (int i=0;i<str.length();i++) {
                strArray[i]=str.substring(0, i+1);
            }
            return strArray;
        }

        public static String[] str2StrArrayAddPrefix(String str,String prefix)
        {
            return strarrAddPrefix(str2StrArray(str),prefix);
        }

        public static String[] str2StrArrayAddPrefix(String str,String splitRegex,String prefix)
        {
            return strarrAddPrefix(str2StrArray(str,splitRegex),prefix);
        }


        public static String[] strarrAddPrefix(String[] strarr,String prefix)
        {

            for (int i=0;i<strarr.length;i++) {
                strarr[i]=prefix+strarr[i];
            }
            return strarr;
        }
}
