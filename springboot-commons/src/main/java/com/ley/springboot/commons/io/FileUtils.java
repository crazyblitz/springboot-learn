package com.ley.springboot.commons.io;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * file utility class
 *
 * @author liuenyuan
 **/
@Slf4j
public class FileUtils extends org.apache.commons.io.FileUtils {

    /**
     * read file
     **/
    public static byte[] readFile(File file) {
        byte[] bytes = null;
        try {
            bytes = IOUtils.readFully(new FileInputStream(file), (int) file.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }


    /**
     * read file
     *
     * @param fileName
     **/
    public static byte[] readFile(String fileName) {
        return readFile(new File(fileName));
    }

    /**
     * write file
     **/
    public static void writeFile(byte[] bytes, String outputFile) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(outputFile);
            fos.write(bytes);
        } catch (Exception arg6) {
            arg6.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
        }

    }


    /**
     * delete file
     **/
    public static boolean delFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            log.warn(fileName + " 文件不存在!");
            return true;
        } else {
            return file.isFile() ? deleteFile(fileName) : false;
        }
    }

    /**
     * delete file
     **/
    private static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("删除单个文件 " + fileName + " 成功!");
                return true;
            } else {
                log.info("删除单个文件 " + fileName + " 失败!");
                return false;
            }
        } else {
            log.warn(fileName + " 文件不存在!");
            return true;
        }
    }


    /**
     * get file extension
     **/
    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }


    /**
     * get file name
     **/
    public static String getFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }


    /**
     * read file as string list
     **/
    public static List<String> readAsStringList(String fileName) {
        List<String> list = new ArrayList();
        BufferedReader reader = null;
        FileInputStream fis = null;

        try {
            File f = new File(fileName);
            if (f.isFile() && f.exists()) {
                fis = new FileInputStream(f);
                reader = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!"".equals(line)) {
                        list.add(line);
                    }
                }
            }
        } catch (Exception e) {
            log.error("readFile exception: {}", e);
        } finally {
            IOUtils.closeQuietly(fis, reader);
        }
        return list;
    }

}
