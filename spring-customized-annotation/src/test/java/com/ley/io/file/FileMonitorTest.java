package com.ley.io.file;

import com.ley.spring.customized.annotation.file.FileListener;
import com.ley.spring.customized.annotation.file.FileMonitor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/20 14:51
 * @describe
 */
@Slf4j
public class FileMonitorTest {

    @Test
    public void testFileMonitor() throws IOException {
        FileSystemResource resource = new FileSystemResource("D:\\workspace\\idea workspace\\springboot-learn\\spring-customized-annotation\\src\\main\\resources");
        String parent = resource.getPath();
        log.info("parent dir: {}", parent);
        FileMonitor monitor = new FileMonitor(parent,
                ".properties", 3, new FileListener());
        monitor.start();
        System.in.read();
    }
}
