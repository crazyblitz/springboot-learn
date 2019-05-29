package com.ley.springboot.mongodb.controller;

import com.ley.springboot.mongodb.service.MongodbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/mongodb")
@Slf4j
@CrossOrigin
public class MongodbController {


    @Autowired
    private MongodbService mongodbService;

    @GetMapping(value = "/{fileId}")
    public void showFile(@PathVariable String fileId, HttpServletResponse response) throws IOException {
        log.info("filename: {}", fileId);
        byte[] bytes = mongodbService.getFileFromMongodb(fileId).get(0);
        log.info("bytes: {}", bytes);
        OutputStream out = response.getOutputStream();
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileId.getBytes("gb2312"), "ISO8859-1"));
        out.write(bytes);
        out.flush();
        out.close();
    }


}
