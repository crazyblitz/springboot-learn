package com.ley.springboot.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * file bo
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileBO {

    private String fileId;

    private String fileName;

    private String fileSuffix;

    private Date uploadTime;
}
