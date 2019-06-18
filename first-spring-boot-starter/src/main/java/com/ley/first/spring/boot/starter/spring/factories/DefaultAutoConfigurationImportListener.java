package com.ley.first.spring.boot.starter.spring.factories;

import org.springframework.boot.autoconfigure.AutoConfigurationImportEvent;
import org.springframework.boot.autoconfigure.AutoConfigurationImportListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;
import java.util.Set;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/31 10:21
 * @describe
 * @see AutoConfigurationImportListener
 * @see AutoConfigurationImportEvent
 */
public class DefaultAutoConfigurationImportListener implements AutoConfigurationImportListener {


    @Override
    public void onAutoConfigurationImportEvent(AutoConfigurationImportEvent event) {
        ClassLoader classLoader = event.getClass().getClassLoader();

        //候选名单
        List<String> candidates = SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class, classLoader);

        //实际装配名单
        List<String> configurations = event.getCandidateConfigurations();

        //排除自动装配名单
        Set<String> excludes = event.getExclusions();


        System.out.println(String.format("自动装配Class名单 -候选数量: %d, 实际数量: %d, 排除数量: %d \n",
                candidates.size(), configurations.size(), excludes.size()));

        System.out.println("实际装配Class名单: ");
        configurations.forEach(System.out::println);


        System.out.println("排除Class名单: ");
        excludes.forEach(System.out::println);

    }
}
