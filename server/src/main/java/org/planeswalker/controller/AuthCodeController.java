package org.planeswalker.controller;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.base.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;


/**
 * 验证码生成
 * @author Planeswalker23
 * @date 2020/2/3
 */
@Slf4j
@Controller
public class AuthCodeController implements Serializable {

    /**
     * 图片的宽度
     */
    private static int WIDTH = 60;
    /**
     * 图片的高度
     */
    private static int HEIGHT = 22;

    /**
     * 获取四位数据的验证码
     * @param response
     * @param session
     * @throws IOException
     */
    @GetMapping("/codeImg")
    public void getCodeImg(HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/jpeg");
        ServletOutputStream sos = response.getOutputStream();
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setDateHeader("Expires", 0);
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        char[] rands = generateCheckCode();
        drawBackground(g);

        // 测试使用交付时需删除
        rands = new char[]{'1', '2', '3', '4'};

        drawRands(g, rands);
        g.dispose();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", sos);
        byte[] buf = bos.toByteArray();

        response.setContentLength(buf.length);
        sos.write(buf);
        bos.close();
        sos.close();
        log.info("验证码 = " + new String(rands));
        session.setAttribute(Constant.CODE_IMG, new String(rands));
    }

    private void drawBackground(Graphics g) {
        g.setColor(new Color(255,  255, 255));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
        }
    }
    private void drawRands(Graphics g, char[] rands) {
        g.setColor(new Color(23, 143, 242));
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));
        g.drawString("" + rands[0], 1, 17);
        g.drawString("" + rands[1], 16, 15);
        g.drawString("" + rands[2], 31, 18);
        g.drawString("" + rands[3], 46, 16);
    }

    private char[] generateCheckCode() {
        String chars = "0123456789";
        int randomNum = 10;
        char[] rands = new char[Constant.FOUR];

        for (int i = Constant.ZERO; i < Constant.FOUR; i++) {
            int rand = (int) (Math.random() * randomNum);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }
}
