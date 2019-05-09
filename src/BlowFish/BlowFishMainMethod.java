package BlowFish;

import java.io.PrintWriter;
public class BlowFishMainMethod {
	public static void main(String[] args) throws Exception {
		String e = BlowFishEnc.encryptBlowfish("fdakfldasjds", "dsflkajsd;fasd");
		System.out.println(e);
		String ee = BlowFishEnc.decryptBlowfish(e, "dsflkajsd;fasd");
		System.out.println(ee);
		PrintWriter writer = new PrintWriter("D:/results.txt", "UTF-8");
		writer.println(e);
		writer.close();
	}
}
