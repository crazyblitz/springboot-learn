package com.crazyblitz.spring.boot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.convert.DataSizeUnit;
//import org.springframework.util.unit.DataSize;
//import org.springframework.util.unit.DataUnit;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/6/17 17:49
 * @describe
 * @since 5.1
 */
@ConfigurationProperties(prefix = "app.io")
@Data
public class AppIoProperties {

//    @DataSizeUnit(DataUnit.MEGABYTES)
//    private DataSize bufferSize = DataSize.ofMegabytes(2);
}
