package com.cdac.osvs.util.images;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SplitImage {

	public static ArrayList<File> breakImage(File file) {
		ArrayList<File> files=new ArrayList<File>();
	try {
			
		//Provide number of rows and column
		int row = 1;
		int col = 2;
		
		BufferedImage originalImgage = ImageIO.read(file);
		
		//total width and total height of an image
		int tWidth = originalImgage.getWidth();
		int tHeight = originalImgage.getHeight();

		System.out.println("Image Dimension: " + tWidth + "x" + tHeight);
		
		//width and height of each piece
		int eWidth = tWidth / col;
		int eHeight = tHeight / row;

		int x = 0;
		int y = 0;
		//File[] fileArray=new File[2];

		for (int i = 0; i < row; i++) {
			y = 0;
			for (int j = 0; j < col; j++) {
				try {
					System.out.println("creating piece: "+i+" "+j);
					
					BufferedImage SubImgage = originalImgage.getSubimage(y, x, eWidth, eHeight);
					File outputfile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\split\\"+i+j+".png");
					//File[i]=outputfile;
					ImageIO.write(SubImgage, "png", outputfile);
					files.add(outputfile);
					y += eWidth;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			x += eHeight;
		}
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	return files;
	
	
	
	}
}