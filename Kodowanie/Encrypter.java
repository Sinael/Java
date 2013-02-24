/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encrypter;



import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import java.security.spec.KeySpec;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEParameterSpec;
import java.util.Scanner;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;




public class Encrypter {

    Cipher ecipher;
    Cipher dcipher;
    Encrypter(String passPhrase) {

 
        byte[] sol = {
            (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
            (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
        };

        // blok powtórzenia
        int iterationCount = 1;

        try {
            //zamiana stringa na char array
            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), sol, iterationCount);
            //tworzenie klucza tajnego przez zwrócenie o
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // przygotowanie parametrów dla bloków
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(sol, iterationCount);
            //opakowanie klucza secret kluczem publicznym
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        } catch (InvalidAlgorithmParameterException | InvalidKeySpecException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println();
        }
    }
   public String encrypt(String str) {
        try {
            // szyfrowanie fragmentów strina na bajty używajac utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // szyfrowanie
            byte[] enc = ecipher.doFinal(utf8);

            // zakodowanie bajtów przez base 64 by otrzymac string 
            return new sun.misc.BASE64Encoder().encode(enc);

        } catch (BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException e) {
        }
        return null;
    }
   /** pobiera pojedynczy string jako argument i zwraca zaszyfrowana wersje
    * tego stringa
    * @zwraca zaszyfrowana wersje podanego stringa w formie kod String /kod
    */
   public String decrypt(String str) {

        try {

            
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            
            byte[] utf8 = dcipher.doFinal(dec);

            
            return new String(utf8, "UTF8");

        } catch (BadPaddingException | IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        return null;
    }
   public static void testUsingPassPhrase() {

        
        Scanner Slowa = new Scanner (System.in);
        String secretString = (Slowa.nextLine()) ;
        String passPhrase   = "szyfr";

        // tworzenie klas
        Encrypter desEncrypter = new Encrypter(passPhrase);
       

        // zaszyfrowanie stringa
        String desEncrypted       = desEncrypter.encrypt(secretString);

        // odszyfrowanie
        String desDecrypted       = desEncrypter.decrypt(desEncrypted);

        // wypisanie wartości

        System.out.println("    Bazwy tekst  : " + secretString);
        System.out.println("    Zaszyfrowany tekst : " + desEncrypted);
        System.out.println("    Odszyfrowany tekst : " + desDecrypted);
        System.out.println();

    }
   public static void main(String[] args) {
        int l3;
        Scanner Osama = new Scanner (System.in);
        l3 = Osama.nextInt();
        while (l3 != 0){
        testUsingPassPhrase();
        l3 = Osama.nextInt();
        }
   }
}