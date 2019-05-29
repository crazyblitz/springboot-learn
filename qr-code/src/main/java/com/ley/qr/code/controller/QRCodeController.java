package com.ley.qr.code.controller;

import com.ley.qr.code.utils.QRCodeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/qrcode")
public class QRCodeController {

    @GetMapping("/{content}")
    public void getQRcode(HttpServletResponse response, @PathVariable String content) {
        QRCodeUtils.generateQRCode(response, content);
    }
}
