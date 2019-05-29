package com.ley.springboot.mongodb;

import com.ley.springboot.mongodb.dao.MongodbRepository;
import com.ley.springboot.mongodb.entity.FileBO;
import com.ley.springboot.mongodb.service.MongodbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MongodbApplication.class})
public class MongodbServiceTest {

    @Autowired
    private MongodbService mongodbService;

    @Autowired
    private MongodbRepository mongodbRepository;

    @Test
    public void testMongodb() throws IOException {
        File file = new File("D:\\idea workspace\\springboot-learn\\springboot-mongdb\\src\\main\\resources\\application.properties");
        System.out.println(file.getAbsolutePath());
        mongodbService.storeFile2Mongodb(file);
    }

    @Test
    public void testMongodbDownload() throws IOException {
        Map<String, byte[]> map = mongodbService.getFileFromMongodb("5c975715c453030a54eb062f", "5c97585ec45303311c568142");
        for (Map.Entry<String, byte[]> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + new String(entry.getValue(), "UTF-8"));
        }
    }

    @Test
    public void testMongdbRepository1() throws IOException {
        FileBO fileBO = new FileBO();
        fileBO.setFileId(UUID.randomUUID().toString());
        fileBO.setFileName("application.properties");
        fileBO.setFileSuffix("properties");
        fileBO.setUploadTime(new Date(System.currentTimeMillis()));
        mongodbRepository.save(fileBO);
    }


}
