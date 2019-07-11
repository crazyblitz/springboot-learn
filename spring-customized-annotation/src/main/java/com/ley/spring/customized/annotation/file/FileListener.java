package com.ley.spring.customized.annotation.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/20 14:33
 * @describe file listener
 * @see org.apache.commons.io.monitor.FileAlterationListener
 * @see org.apache.commons.io.monitor.FileAlterationMonitor
 */
@Slf4j
public class FileListener extends FileAlterationListenerAdaptor {

    private volatile boolean fileModify = false;

    private static Properties properties;

    @Override
    public void onDirectoryDelete(File directory) {
        if (log.isDebugEnabled()) {
            log.debug("delete directory: {}", directory.getName());
        }
    }

    @Override
    public void onDirectoryCreate(File directory) {
        log.info("create directory: {}", directory.getName());
    }

    @Override
    public void onDirectoryChange(File directory) {
        log.info("change directory: {}", directory.getName());
        log.info("fileModify: {}", fileModify);
    }

    @Override
    public void onFileCreate(File file) {
        log.info("create file: {}", file.getName());
        try {
            getChangeFileToProperties(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFileDelete(File file) {
        log.info("delete file: {}", file.getName());
        try {
            getChangeFileToProperties(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileModify = true;
    }

    @Override
    public void onFileChange(File file) {
        log.info("change file: {}", file.getName());
        try {
            getChangeFileToProperties(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileModify = true;
    }


    public boolean isFileModify() {
        return fileModify;
    }

    public static Properties getProperties() {
        return properties;
    }

    public void getChangeFileToProperties(File file) throws IOException {
        List<String> readLines = FileUtils.readLines(file, "UTF-8");
        properties = new Properties();
        if (!CollectionUtils.isEmpty(readLines)) {
            for (String readLine : readLines) {
                String[] propertyValues = readLine.split("=");
                properties.setProperty(propertyValues[0], propertyValues[1]);
            }
        }
    }
}
