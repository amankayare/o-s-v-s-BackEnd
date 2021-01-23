package com.cdac.osvs.util.images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
//from   ww  w  .  j  a v  a2s .c om
import javax.imageio.ImageIO;

import com.cdac.osvs.util.RandomUtil;

public class MergeImage {

    public static File merge(File file1, File file2) throws Exception {
        System.out.println("7");
        System.out.println(file1.getPath());
        System.out.println(file2.getPath());

        BufferedImage img1 = ImageIO.read(file1);

        System.out.println("8");

        BufferedImage img2 = ImageIO.read(file2);
        System.out.println("9");

        BufferedImage joinedImg = joinBufferedImage(img1, img2);
        System.out.println("10");
        new File(RandomUtil.mergeUploadDirectory).mkdirs();
        System.out.println("20");
        String RandomFIleName = RandomUtil.generatingRandomAlphanumericFileName();
        System.out.println("30");
        File fileJoined = new File(RandomUtil.mergeUploadDirectory +RandomFIleName +".png");
        System.out.println("40");
        ImageIO.write(joinedImg, "png", fileJoined);
        System.out.println("50");

        return fileJoined;
    }

    public static BufferedImage joinBufferedImage(BufferedImage img1,BufferedImage img2) {

        System.out.println("imge1---->"+img1);
        System.out.println("img2----->"+img2);

        int offset = 0;
        System.out.println("BEFORE");

        int width = img1.getWidth() + img2.getWidth() + offset;
        System.out.println(width+" WIDTH");

        int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        System.out.println(height+" Height");

        System.out.println("51");
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        System.out.println("52");

        Graphics2D g2 = newImage.createGraphics();
        System.out.println("53");

        Color oldColor = g2.getColor();
        // g2.setPaint(Color.BLACK);
        System.out.println("54");

        g2.fillRect(0, 0, width, height);
        System.out.println("55");

        g2.setColor(oldColor);
        System.out.println("56");

        g2.drawImage(img1, null, 0, 0);
        System.out.println("57");

        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        System.out.println("59");

        g2.dispose();
        System.out.println("60");

        return newImage;
    }
}
