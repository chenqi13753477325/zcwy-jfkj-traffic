package com.dashboard.adp.api.utils;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * Copyright © 2018. All rights reserved.
 *
 * @Author: FreezeSoul
 * @CreateDate: 2018/4/12 14:57
 * @Description: ObjectConvertUtil
 * @Version: V1.0
 */
public class ObjectConvertUtil {

    public static String ParseString(Object obj) {
        if (StringUtils.isEmpty(ObjectUtils.toString(obj, null))) {
            return null;
        }
        return ObjectUtils.toString(obj, null);
    }

    public static Double ParseDouble(Object obj) {
        if (StringUtils.isEmpty(ParseString(obj))) {
            return null;
        }
        return NumberUtils.createDouble(ParseString(obj));
    }

    public static Integer ParseInteger(Object obj) {
        if (StringUtils.isEmpty(ParseString(obj))) {
            return null;
        }
        return NumberUtils.createInteger(ParseString(obj));
    }

    public static Long ParseLong(Object obj) {
        if (StringUtils.isEmpty(ParseString(obj))) {
            return null;
        }
        return NumberUtils.createLong(ParseString(obj));
    }

    public static Byte ParseByte(Object obj) {
        if (StringUtils.isEmpty(ParseString(obj))) {
            return null;
        }
        return Byte.parseByte(ParseString(obj));
    }
}
