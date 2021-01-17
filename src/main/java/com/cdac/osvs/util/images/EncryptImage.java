package com.cdac.osvs.util.images;


import com.cdac.osvs.util.RandomUtil;

import java.io.*;


public class EncryptImage {
	public static File doEncrypt(File fileToEncrypt,int key)
		throws FileNotFoundException, IOException 
	{ 
		System.out.println("Note : Encryption Key act as Password to Decrypt the same Image,otherwise it will corrupt the Image."); 
	
		// Here key is act as password to Encrypt and 
		// Decrypt the Image 
							
		// Selecting a Image for operation 
		FileInputStream fis = new FileInputStream(fileToEncrypt); 
							
		// Converting Image into byte array, create a 
		// array of same size as Image size 
							
		byte data[] = new byte[fis.available()]; 
							
		// Read the array 
		fis.read(data); 
		int i = 0; 
							
		// Performing an XOR operation on each value of 
		// byte array due to which every value of Image 
		// will change. 
		for (byte b : data) { 
			data[i] = (byte)(b ^ key);
			i++; 
		} 
							
		// Opening a file for writing purpose
		FileOutputStream fos = new FileOutputStream(fileToEncrypt);
							
		// Writing new byte array value to image which 
		// will Encrypt it. 
							
		fos.write(data); 
							
		// Closing file 
		fos.close(); 
		fis.close(); 
		System.out.println("Encyption Done...");

		return fileToEncrypt;
	} 
}
