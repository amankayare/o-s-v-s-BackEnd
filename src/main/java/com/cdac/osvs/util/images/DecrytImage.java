package com.cdac.osvs.util.images;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class DecrytImage {

    public static File doDecrypt(File fileToDecrypt, int key)
            throws FileNotFoundException, IOException {


        System.out.println("Note : Encryption Key act as Password to Decrypt the same Image, otherwise it will corrupt the Image.");


        // Selecting a Image for Decryption.

        FileInputStream fis = new FileInputStream(fileToDecrypt); //reading file

        // Converting image into byte array,it will
        // Create a array of same size as image.
        byte data[] = new byte[fis.available()];

        // Read the array

        fis.read(data);
        int i = 0;

        // Performing an XOR operation
        // on each value of
        // byte array to Decrypt it.
        for (byte b : data) {
            data[i] = (byte) (b ^ key);
            i++;
        }

        // Opening file for writting purpose
        FileOutputStream fos = new FileOutputStream(fileToDecrypt);

        // Writting Decrypted data on Image
        fos.write(data);
        fos.close();

        fis.close();

        System.out.println(" image decryted");
        System.out.println("File Dcrypted=>"+fileToDecrypt);
        return fileToDecrypt;
    }
}
