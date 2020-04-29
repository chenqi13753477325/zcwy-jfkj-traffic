package com.dashboard.adp.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author freezesoul
 * The type Name cast tools.
 */
public class NameCastTools {
    private static final Logger logger = LoggerFactory.getLogger(NameCastTools.class);

    /**
     * List to list map list.
     *
     * @param objects the objects
     * @return the list
     */
    public static List<Map<String, Object>> listToListMap(List<?> objects) {
        if (objects != null)
            return objects.stream().map(NameCastTools::objectToMap).collect(Collectors.toList());
        return new ArrayList<>();
    }

    /**
     * Object to map map.
     *
     * @param <T> the type parameter
     * @param obj the obj
     * @return the map
     */
    public static <T> Map<String, Object> objectToMap(T obj) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (obj != null) {
                BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
                for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                    String propertyName = propertyDesc.getName();
                    Object value = propertyDesc.getReadMethod().invoke(obj);
                    map.put(propertyName, value);
                }
            }
        } catch (Exception e) {
            logger.error("objectToMap", e);
        }
        return map;
    }


    /**
     * Underscore map map.
     *
     * @param map the map
     * @return the map
     */
    public static Map<String, Object> underscoreMap(Map<String, Object> map) {
        Map<String, Object> convertMap = new HashMap<>();
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = underscoreToCamelCase(entry.getKey());
                if (!convertMap.containsKey(key)) {
                    convertMap.put(key, entry.getValue());
                }
            }
        } catch (Exception e) {
            logger.error("objectToMap", e);
        }
        return convertMap;
    }

    /**
     * List map underscore list.
     *
     * @param maps the maps
     * @return the list
     */
    public static List<Map<String, Object>> listMapUnderscore(List<Map<String, Object>> maps) {
        if (maps != null)
            return maps.stream().map(NameCastTools::underscoreMap).collect(Collectors.toList());
        return new ArrayList<>();
    }


    private static String camelCaseToUnderscore(String str) {
        return str.replaceAll("([A-Z]+)", "\\_$1").toLowerCase();
    }


    private static String underscoreToCamelCase(String str) {
        String[] parts = str.split("_");
        StringBuilder camelCaseString = new StringBuilder();
        for (String part : parts) {
            camelCaseString.append(part.substring(0, 1).toUpperCase()).append(part.substring(1).toLowerCase());
        }
        return camelCaseString.substring(0,1).toLowerCase() + camelCaseString.substring(1);
    }

}
