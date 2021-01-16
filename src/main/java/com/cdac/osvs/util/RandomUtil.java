package com.cdac.osvs.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class RandomUtil {
    private static final long serialVersionUID = 1L;


    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;
    public static final  String uploadDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\uploads";

    public static File generateRamdomImage(String fileName) {

     //   System.out.println("main1");

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = (Graphics2D) image.getGraphics();

        setBackground(g);
        setBorder(g);
        drawRandomLine(g);
        drawRandomNumber(g);

        System.out.println("34");
        // pass image to browser
        //response.setContentType("image/jpeg");
        File outputfile = new File(uploadDirectory+fileName+".png");
        //	ImageIO.write(image, "jpg", response.getOutputStream());


        try {
            ImageIO.write(image, "jpg", outputfile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return outputfile;

    }

    private static void drawRandomNumber(Graphics2D g) {
        System.out.println("50");
        g.setColor(Color.BLUE);
        g.setFont(new Font("",Font.BOLD, 60));

        for (int i = 0; i < 6; i++) {
            int degree = new Random().nextInt()%30; 	// can be +-
            //g.rotate(degree*Math.PI/180, 1, HEIGHT);
            g.drawString(String.valueOf(new Random().nextInt(10)),WIDTH-100*i, HEIGHT-250);
            //g.rotate(-degree*Math.PI/180, 1, HEIGHT);
        }
    }

    private static void setBorder(Graphics g) {
        System.out.println("62");
        g.setColor(Color.BLACK);
        g.drawRect(1, 1, WIDTH-2, HEIGHT-2);
    }

    private static void setBackground(Graphics g) {
        System.out.println("68");
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private static void drawRandomLine(Graphics g) {
        System.out.println("75");
        g.setColor(Color.GRAY);
        for (int i = 0; i < 5; i++) {
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }
    public String generatingRandomAlphanumericFileName() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
