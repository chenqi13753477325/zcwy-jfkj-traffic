package com.dashboard.adp.api.utils;

import java.util.UUID;

/**
 * Copyright © 2016 ICST. All rights reserved.
 *
 * @ProjectName: cityos-parent
 * @Package: cityos.core.common.utils
 * @ClassName: ${FILE_NAME}
 * @Description: TODO
 * @Author: CosmicSouls
 * @CreateDate: 2018/3/31 13:18
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark: The modified content
 * @Version: V1.0
 * Copyright: Copyright (c) 2018
 */
public class UUIDUtils {

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };



    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    public static String generateUuid()
    {
        String uuid = UUID.randomUUID().toString();

        return uuid;
    }
}
