package com.dashboard.adp.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright © 2016 ICST. All rights reserved.
 *
 * @Description: TODO
 * @Author: CosmicSouls
 * @CreateDate: 2018/3/31 19:31
 * @Remark: The remark content
 * @Version: V1.0
 * Copyright: Copyright (c) 2018
 */
public class IDUtils {

    private static final Logger logger = LoggerFactory.getLogger(IDUtils.class);
    /**
     * 锁
     */
    private static final Object ID_LOCK = new Object();
    /**
     * 当前秒数
     */
    private static long CURRENT_SECOND = System.currentTimeMillis() / 1000L;
    private static int ID = 0;

    public static void main(String[] args) {
        logger.info(String.valueOf(Integer.MAX_VALUE / (365 * 24 * 60 * 60)));
        logger.info(Integer.toBinaryString((int) (System.currentTimeMillis() / 1000)));
    }

    /**
     * 获取唯一一个id
     *
     * @return long
     */
    public static long getId() {
        int tempId;
        long tempCurSec = System.currentTimeMillis() / 1000L;
        synchronized (ID_LOCK) {
            ID += 1;
            tempId = ID;
            int i = 65000;
            if (ID > i) {
                ID = 0;
                CURRENT_SECOND += 1L;
            }
            if (tempCurSec > CURRENT_SECOND) {
                CURRENT_SECOND = tempCurSec;
            } else {
                tempCurSec = CURRENT_SECOND;
            }
        }
        return ((tempCurSec) << 16 | tempId & 0xFFFF);
    }

}
