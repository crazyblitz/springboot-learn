package com.ley.springboot.mongodb.dao;

import com.ley.springboot.mongodb.entity.FileBO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongodbRepository extends MongoRepository<FileBO, String> {

    FileBO findFileBOByFileId(String fileId);

    FileBO findFileBOByFileName(String fileName);
}
