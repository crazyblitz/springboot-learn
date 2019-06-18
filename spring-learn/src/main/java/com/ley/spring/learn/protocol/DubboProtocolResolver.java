package com.ley.spring.learn.protocol;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.*;
import org.springframework.util.StringUtils;

import java.net.URL;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/7 14:33
 * @describe
 */
@Slf4j
public class DubboProtocolResolver implements ProtocolResolver {

    private static final String DUBBO_PROTOCOL_PREFIX = "dubbo://";

    @Override
    public Resource resolve(String location, ResourceLoader resourceLoader) {
        if (location.startsWith(DUBBO_PROTOCOL_PREFIX)) {
            String realLocation = location.substring(DUBBO_PROTOCOL_PREFIX.length());
            if (log.isDebugEnabled()) {
                log.debug("real location: {}", realLocation);
            }
            if (resourceLoader == null) {
                resourceLoader = new DefaultResourceLoader();
            }
            return resourceLoader.getResource(realLocation);
        }
        return null;
    }


    @Test
    public void testSubString() {
        String str = "AB我A我ABC";
        String result = subStringByBytes(str, 8);
        System.out.println(result);
    }


    private String subStringByBytes(String str, int len) {

        if (str == null || "".equals(str) || str.getBytes().length <= len) {
            return str;
        }


        StringBuilder builder = new StringBuilder();

        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (isChineseChar(str.charAt(i))) {
                builder.append(str.charAt(i));
                len -= 2;
            } else {
                builder.append(str.charAt(i));
                len -= 1;
            }

            //长度正好或者长度还剩余1而且该字符不是中文
            boolean end = (len == 0 || (len == 1 && (!isChineseChar(str.charAt(i)))));
            if (end) {
                break;
            }
        }


        return builder.toString();
    }


    private boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }
}
