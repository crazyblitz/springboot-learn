package com.ley.springboot.commons.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String listToString(List<String> stringList) {
        if (CollectionUtils.isEmpty(stringList)) {
            return null;
        } else {
            StringBuilder builder = new StringBuilder(255);

            for (int i = 0; i < stringList.size(); ++i) {
                builder.append((String) stringList.get(i));
                if (i < stringList.size() - 1) {
                    builder.append(",");
                }
            }

            return builder.toString();
        }
    }

    public static List<String> stringToList(String string) {
        return isEmpty(string) ? null : Arrays.asList(string.split(","));
    }

    public static List<Integer> stringToIntList(String string) {
        if (isEmpty(string)) {
            return null;
        } else {
            List<Integer> integerList = new ArrayList<>(16);
            String[] stringArray = string.split(",");

            for (int i = 0; i < stringArray.length; ++i) {
                String str = stringArray[i];
                if (isNotEmpty(str)) {
                    integerList.add(Integer.valueOf(str));
                }
            }

            return integerList;
        }
    }
}

