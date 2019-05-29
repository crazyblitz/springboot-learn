package com.ley.springboot.mongodb.service;


import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * mongodb service
 *
 * @author liuenyuan
 **/
@Service
@Slf4j
public class MongodbService {

    @Autowired
    private GridFsTemplate gridFsTemplate;



    /**
     * store file to mongodb
     *
     * @param files the files store to mongodb
     **/
    public List<String> storeFile2Mongodb(MultipartFile... files) throws IOException {
        if (files != null && files.length > 0) {
            List<String> fileNames = new ArrayList<>(files.length);
            for (MultipartFile file : files) {
                //获取提交的文件名
                String fileName = file.getOriginalFilename();
                // 获得文件输入流
                InputStream ins = file.getInputStream();
                // 获得文件类型
                String contentType = file.getContentType();
                try {
                    // 将文件存储到mongodb中,mongodb将会返回这个文件
                    ObjectId gridFSFile = gridFsTemplate.store(ins, fileName, contentType);
                    if (gridFSFile != null) {
                        fileNames.add(fileName);
                    }
                } finally {
                    ins.close();
                }
            }
            return fileNames;
        }
        return Collections.emptyList();
    }


    /**
     * store file to mongodb
     *
     * @param files the files store to mongodb
     **/
    public List<String> storeFile2Mongodb(File... files) throws IOException {
        if (files != null && files.length > 0) {
            List<String> filePaths = new ArrayList<>(files.length);
            for (File file : files) {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    //获取提交的文件名
                    String fileName = file.getName();
                    // 将文件存储到mongodb中,mongodb 将会返回这个文件
                    ObjectId gridFSFile = gridFsTemplate.store(fileInputStream, fileName);
                    if (gridFSFile != null) {
                        filePaths.add(file.getAbsolutePath());
                    }
                } finally {
                    fileInputStream.close();
                }
            }
            return filePaths;
        }
        return Collections.emptyList();
    }


    public Map<String, byte[]> getFileFromMongodb(String... fileIds) throws IOException {
        if (fileIds != null && fileIds.length > 0) {
            Map<String, byte[]> map = new HashMap<>(fileIds.length);
            for (String fileId : fileIds) {
                Query query = Query.query(Criteria.where("_id").is(fileId));
                GridFSFile gridFSFile = gridFsTemplate.findOne(query);
                GridFsResource gridFsResource = new GridFsResource(gridFSFile);
                if (gridFSFile != null) {
                    InputStream inputStream = gridFsResource.getInputStream();
                    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                        IOUtils.copy(inputStream, baos);
                        map.put(fileId, baos.toByteArray());
                    } catch (IOException e) {
                        log.error("e message: {}", e.getMessage());
                    }
                }
            }
            return map;
        } else {
            return Collections.emptyMap();
        }
    }
}
