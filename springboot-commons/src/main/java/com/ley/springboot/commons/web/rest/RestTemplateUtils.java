package com.ley.springboot.commons.web.rest;

import com.ley.springboot.commons.utils.CollectionUtils;
import com.ley.springboot.commons.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * RestTemplateUtils<br/>
 * 1:为了解决Get方法参数拼接问题
 **/
@Slf4j
public class RestTemplateUtils {

    private static final String URL_PARAM_VALUE_PREFIX = "{";

    private static final String URL_PARAM_VALUE_SUFFIX = "}";

    private static final String URL_PARAM_VALUE_CONTACT = "&";

    /**
     * get for object
     *
     * @param url the url is for example <b>http://api.map.baidu.com/geosearch/v3/nearby</b>,and after handler
     *            is <b>http://api.map.baidu.com/geosearch/v3/nearby?ak={ak}&geotable_id={geotable_id} when uriVariables size is not null</b>
     **/
    public static <T> T getForObject(RestTemplate restTemplate, String url, Class<T> responseType, Map<String, String> uriVariables) {
        StringBuffer buffer = new StringBuffer(255);
        if (CollectionUtils.isNotEmpty(uriVariables)) {
            buffer.append(url).append("?");
            for (Map.Entry<String, String> uriVariableEntry : uriVariables.entrySet()) {
                String paramKey = uriVariableEntry.getKey();
                if (StringUtils.isNotBlank(paramKey)) {
                    String param = paramKey + "=" + URL_PARAM_VALUE_PREFIX + paramKey + URL_PARAM_VALUE_SUFFIX + URL_PARAM_VALUE_CONTACT;
                    buffer.append(param);
                }
            }
        }
        String realUrl = buffer.toString();
        realUrl = realUrl.substring(0, realUrl.length() - 1);
        if (log.isInfoEnabled()) {
            log.info("real url: {}", realUrl);
        }
        return restTemplate.getForObject(realUrl, responseType, uriVariables);
    }


    public static void main(String[] args) {

        String url = "http://api.map.baidu.com/geosearch/v3/nearby";

        Map<String, String> nearByGetParams = new HashMap<>(5);

        //传入AK
        nearByGetParams.put("ak", "GU3Pjg5Ug6sm7awZ1uP2vi5wmm89c93h");

        //geotable主键
        nearByGetParams.put("geotable_id", "1");

        //检索的中心点;逗号分隔的经纬度(经度,纬度)
        nearByGetParams.put("location", "2");

        StringBuffer buffer = new StringBuffer(255);
        if (CollectionUtils.isNotEmpty(nearByGetParams)) {
            buffer.append(url).append("?");
            for (Map.Entry<String, String> uriVariableEntry : nearByGetParams.entrySet()) {
                String paramKey = uriVariableEntry.getKey();
                if (StringUtils.isNotBlank(paramKey)) {
                    String param = paramKey + "=" + URL_PARAM_VALUE_PREFIX + paramKey + URL_PARAM_VALUE_SUFFIX + "&";
                    buffer.append(param);
                }
            }
        }
        String realUrl = buffer.toString();
        realUrl = realUrl.substring(0, realUrl.length() - 1);
        if (log.isInfoEnabled()) {
            log.info("real url: {}", realUrl);
        }
        System.out.println(buffer.toString().substring(0, buffer.toString().length() - 1));
    }
}
