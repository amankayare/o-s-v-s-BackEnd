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

        BufferedImage img1 = ImageIO.read(file1);

        BufferedImage img2 = ImageIO.read(file2);

        BufferedImage joinedImg = joinBufferedImage(img1, img2);

        new File(RandomUtil.mergeUploadDirectory).mkdirs();
        String RandomFIleName = RandomUtil.generatingRandomAlphanumericFileName();
        File fileJoined = new File(RandomUtil.mergeUploadDirectory +RandomFIleName +".png");

        ImageIO.write(joinedImg, "png", fileJoined);


        return fileJoined;
    }

    public static BufferedImage joinBufferedImage(BufferedImage img1,
                                                  BufferedImage img2) {
        int offset = 0;
        int width = img1.getWidth() + img2.getWidth() + offset;
        int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        // g2.setPaint(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        g2.dispose();

        return newImage;
    }
}
