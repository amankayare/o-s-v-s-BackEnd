package com.cdac.osvs.util.images;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

import com.cdac.osvs.util.RandomUtil;

public class ConversionUtil {
    
    //convert byte to file
    
 // Java Program to convert 
 // byte array to file 

 	// Path of a file 


 	// Method which write the bytes into a file 
 	public static File byteConvertToFile(byte[] bytes) 
 	{ 

 	 File file = new File(RandomUtil.uploadDirectory+"Temp"); 
 		
 		try { 

 			// Initialize a pointer 
 			// in file using OutputStream 
 			OutputStream 
 				os 
 				= new FileOutputStream(file); 

 			// Starts writing the bytes in it 
 			os.write(bytes); 
 			System.out.println("Successfully"
 							+ " byte inserted"); 

 			// Close the file 
 			os.close(); 
 		} 

 		catch (Exception e) { 
 			System.out.println("Exception: " + e); 
 		   
 		} 
 		
 		System.out.println("conversion util");
 		
    return file;
 } 
  
 	
 	public  static File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
 	    File convFile = new File(RandomUtil.uploadDirectory+"//Candidate"+"//"+fileName+".png");
 	    multipart.transferTo(convFile);
 	    return convFile;
 	}
 	
}
