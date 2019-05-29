package com.ley.springboot.images.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.io.*;

public class SvgUtils {

    //svg转为png
    public static void convertSvg2Png(File svg, File png) throws IOException, TranscoderException {
        InputStream in = new FileInputStream(svg);
        OutputStream out = new FileOutputStream(png);
        out = new BufferedOutputStream(out);

        Transcoder transcoder = new PNGTranscoder();
        try {
            TranscoderInput input = new TranscoderInput(in);
            try {
                TranscoderOutput output = new TranscoderOutput(out);
                transcoder.transcode(input, output);
                //按照比例进行缩小和放大
                Thumbnails.of(png).scale(0.6f).toFile(png);//按比例缩小
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }


    public static void main(String[] args) throws IOException {
        File f = new File("E:/18.svg");
        File destFile = new File("E:/18.png");
        long startTime = System.currentTimeMillis();
        try {
            convertSvg2Png(f, destFile);
            long endTime = System.currentTimeMillis();
            System.out.println("time: " + (endTime - startTime) / 1000);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TranscoderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
