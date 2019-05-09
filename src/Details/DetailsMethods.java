package Details;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
public class DetailsMethods {
	public static String stringToBinary(String s) {
		  byte[] bytes = s.getBytes();
		  StringBuilder binary = new StringBuilder();
		  for (byte b : bytes)
		  {
		     int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		        binary.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     binary.append(' ');
		  }
		  return "'" + s + "' to binary: " + binary;
	}
public static String stringToASCII(String t) throws UnsupportedEncodingException {
         String text = t;
         // translating text String to 7 bit ASCII encoding
         byte[] bytes = text.getBytes("US-ASCII");     
        return "ASCII value of " +"\""+text +"\""+ " is following:\n "+Arrays.toString(bytes);   
}
public static String calculateBits (String text) {
	return "bits in "+ "\""+ text + "\""+": "+ text.length()*8+" bits";
}
public static String calculateBytes (String text) {
	return "bytes in "+ "\""+ text + "\""+": "+ text.length()+" bytes";
}
public static String calculateWords (String text) {
	return "words in "+ "\""+ text + "\""+": "+ text.length()*2+" words";
}
public static void main (String args[]) throws UnsupportedEncodingException {
System.out.println(	stringToASCII("hello world"));
	System.out.println(calculateBits("he"));
	System.out.println(calculateBytes("he"));
	System.out.println(calculateWords("he"));
}
}
