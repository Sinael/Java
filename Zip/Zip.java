/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zip;

/**
 *
 * @author MediaMarkt
 */
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip
  {
public static void main(String a[])
{
   try {
		String sciezka = "C:\\nowy.txt";
		String sciezkaZIP = "C:\\text.zip";
		
		ZipOutputStream outZIP = new ZipOutputStream(new BufferedOutputStream(
			new FileOutputStream(sciezkaZIP)));

		outZIP.setMethod(ZipOutputStream.DEFLATED);
         
		final int BUFFER = 2048;  

		byte data[] = new byte[BUFFER];
		FileInputStream fi = new FileInputStream(sciezka);
		BufferedInputStream origin = new BufferedInputStream(fi, BUFFER);
		ZipEntry entry = new ZipEntry(sciezka);
		outZIP.putNextEntry(entry);
		int count;
		while((count = origin.read(data, 0, BUFFER)) != -1) {
			outZIP.write(data, 0, count);
		}
		origin.close();
		outZIP.close();
 
	} catch (Exception e) {
		System.out.println(e.toString());
	}
	 
}
}
