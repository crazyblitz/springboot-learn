package com.ley.springboot.elasticsearch.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Document(indexName = "elasticsearch_demo", type = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private Integer age;
}
