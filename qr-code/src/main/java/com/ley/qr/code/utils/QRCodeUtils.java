package com.ley.qr.code.utils;

import com.google.gson.Gson;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class QRCodeUtils {

    /**
     * 图像宽度
     **/
    private final static int DEFAULT_WIDTH = 200;


    /**
     * 图像高度
     **/
    private final static int DEFAULT_HIGHT = 200;

    /**
     * 编码
     **/
    private final static String CHARSET = "UTF-8";

    /**
     * 二维码图片后缀
     **/
    private final static String QRCODE_FORMAT = "png";

    /**
     * 生成二维码文件
     *
     * @param filePath 生成二维码文件路径(最好不要以C:\\为前缀路径,否则会造成文件找不到,而抛出异常FileNotFoundException)
     * @param fileName 生成二维码文件名
     * @param content  二维码内容(json数据)
     * @return 生成二维码文件路径
     */
    public static String generateQRCode(String filePath, String fileName, String content) {

        int width = DEFAULT_WIDTH;
        int height = DEFAULT_HIGHT;
        String format = QRCODE_FORMAT;// 图像类型

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
            File rootPath = new File(filePath);
            if (!rootPath.exists()) {
                rootPath.setWritable(true);
                rootPath.mkdirs();
            }
            String qrCodeFilePath = rootPath + File.separator + fileName;
            File qrCodeFile = new File(qrCodeFilePath);
            MatrixToImageWriter.writeToStream(bitMatrix, format, new FileOutputStream(qrCodeFile));// 输出图像
            log.info("二维码生成成功,地址: {}", qrCodeFilePath);
            return qrCodeFilePath;
        } catch (WriterException e) {
            e.printStackTrace();
            System.out.println("二维码输出异常");
        } catch (IOException e) {
            log.error("e message: {}", e.getMessage());
            log.warn("二维码图片生成失败");
        }
        return null;
    }

    /**
     * 解析二维码内容
     *
     * @param filePath 二维码绝对路径
     * @return return qrcode json content
     */
    public static String parseQRCode(String filePath) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(filePath));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, CHARSET);

            Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
            return result.getText();
        } catch (IOException e) {
            log.error("e: {}", e.getMessage());
        } catch (NotFoundException e) {
            log.error("{} file not found: {}", filePath);
        }
        return null;
    }


    /**
     * 生成二维码输出流
     * 无须保存 即生成即展示
     *
     * @param response http servlet响应流
     * @param content  二维码内容
     */
    public static void generateQRCode(HttpServletResponse response, String content) {

        int width = DEFAULT_WIDTH; // 图像宽度
        int height = DEFAULT_HIGHT; // 图像高度
        String format = QRCODE_FORMAT;// 图像类型

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
            MatrixToImageWriter.writeToStream(bitMatrix, format, response.getOutputStream());// 输出图像
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            log.info("二维码输出成功");
        } catch (WriterException e) {
            log.error("e message: {}", e.getMessage());
            log.warn("二维码输出异常");
        } catch (IOException e) {
            log.error("e message: {}", e.getMessage());
            log.warn("二维码输出异常");
        }
    }
}
