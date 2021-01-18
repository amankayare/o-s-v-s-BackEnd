package com.cdac.osvs.util.images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
//from   ww  w  .  j  a v  a2s .c om
import javax.imageio.ImageIO;

import com.cdac.osvs.util.RandomUtil;

public class MergeImage {
	
  public static File merge(File file1,File file2) throws Exception {
	  System.out.println("15");
    BufferedImage img1 = ImageIO.read(file1);
    System.out.println("17");
    BufferedImage img2 = ImageIO.read(file2);
    System.out.println("19");
    BufferedImage joinedImg = joinBufferedImage(img1, img2);
    System.out.println("21");
    File fileJoined=new File(RandomUtil.uploadDirectory+"joined.png");
    
    System.out.println("22");
    
    ImageIO.write(joinedImg, "png",fileJoined);
    
    System.out.println("merged image");
    
    return fileJoined;
  }

  public static BufferedImage joinBufferedImage(BufferedImage img1,
      BufferedImage img2) {
	  System.out.println("35");
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
    System.out.println("49");
    return newImage;
  }
}
