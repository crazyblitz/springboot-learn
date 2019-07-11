package com.ley.qr.code;

import com.google.gson.Gson;
import com.ley.qr.code.bean.Person;
import com.ley.qr.code.utils.QRCodeUtils;
import org.junit.Test;

import java.io.File;

public class QRCodeTest {

    @Test
    public void testGenerateQRCode() {
        //  QRCodeUtils.generateQRCode("D:\\", "2.png", "我是刘恩源");
        Person person = new Person("刘家海", "13523882892", "河南省商城县苏仙石乡关帝庙小学");
        Gson gson = new Gson();
        QRCodeUtils.generateQRCode("D:" + File.separator + "二维码" + File.separator, "刘家海.png", gson.toJson(person));
    }

    @Test
    public void testParseQRCode() {
        System.out.println(QRCodeUtils.parseQRCode("D:\\二维码\\刘家海.png"));
    }
}
