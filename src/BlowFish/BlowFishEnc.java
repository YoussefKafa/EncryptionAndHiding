package BlowFish;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import IO.IOutils;
public class BlowFishEnc {
	public static String encryptBlowfish(String to_encrypt, String strkey) {
		  try {
//create a key
		    SecretKeySpec key = new SecretKeySpec(strkey.getBytes(), "Blowfish");
//create a cipher based upon blowfish
		     Cipher cipher = Cipher.getInstance("Blowfish");
//initialise cipher with secret key, bind them together
		     cipher.init(Cipher.ENCRYPT_MODE, key);
//encrypting the text and return it
		     return new String(cipher.doFinal(to_encrypt.getBytes()));
		  } catch (Exception e) { return null; }
		}
	public static String decryptBlowfish(String to_decrypt, String strkey) {
		  try {
//get the key and create it
		     SecretKeySpec key = new SecretKeySpec(strkey.getBytes(), "Blowfish");
//create the cipher
		     Cipher cipher = Cipher.getInstance("Blowfish");
//initialise cipher with the secret key
		     cipher.init(Cipher.DECRYPT_MODE, key);
//decrypting the text and return it
		     byte[] decrypted = cipher.doFinal(to_decrypt.getBytes());
		     return new String(decrypted);
		  } catch (Exception e) { return null; }
		}
  public static void main(String[] args) throws Exception {
	String e= BlowFishEnc.encryptBlowfish("textToEncrypt", "anyKey");
	//writing
	IOutils.writeAfile("D:\\output.txt", e);
	//reading
	String result=IOutils.readAfile("D:\\output.txt");
	System.out.println("result: "+result);
	String ee=BlowFishEnc.decryptBlowfish(result, "anyKey");
	System.out.println(ee);
	
  }
}