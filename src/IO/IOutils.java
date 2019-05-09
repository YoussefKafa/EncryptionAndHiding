package IO;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class IOutils {
	static PrintWriter writer;
	public static void  writeAfile(String path,String toWrite) {
		try {
			writer = new PrintWriter(path, "UTF-8");
			writer.println(toWrite);
			writer.close();
			JOptionPane.showMessageDialog(new JFrame(), "key saved in "+path, "Dialog",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(new JFrame(), "error : file not found!", "Dialog", JOptionPane.ERROR_MESSAGE);
		} catch (UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(new JFrame(), "error : unsupported Encoding!", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	public static String readAfile(String path) {
		String fileName=path;
		String result="";
		// This will reference one line at a time
		String line = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				result += line;
			}
			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			ex.printStackTrace();
		}
		return result;
	}
}
