package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class Options extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void close() {
		this.dispose();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Options frame = new Options();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Options() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 568);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(0, 0, 434, 166);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblChooseOne = new JLabel("Choose one:");
		lblChooseOne.setBounds(164, 23, 90, 32);
		panel.add(lblChooseOne);
		lblChooseOne.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton encryptionButton = new JButton("Encryption and Decryption");
		encryptionButton.setBounds(91, 74, 224, 32);
		panel.add(encryptionButton);
		encryptionButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton hideInfo = new JButton("Hide/Unhide information");
		hideInfo.setBounds(91, 123, 224, 32);
		hideInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SteganographyFrame().setVisible(true);
				close();
			}
		});
		panel.add(hideInfo);
		hideInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblEncryptionAndDecryption = new JLabel("Encryption and Decryption:\r\n");
		lblEncryptionAndDecryption.setText(
				"<html>Encryption and Decryption:<br/>providing a very simple and easy GUI for encryption and decryption <br/> user can encrypt a text using any algorithm he choose, <br/>also user can decrypt any cipher text after providing the key.</html>");
		lblEncryptionAndDecryption.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEncryptionAndDecryption.setBounds(10, 177, 414, 131);
		getContentPane().add(lblEncryptionAndDecryption);

		JLabel lblHideunhideInformation = new JLabel(
				"<html>Hide/Unhide Information:<br/> user can hide information inside a PNG image file<br/> also user can reveal information from PNG images (if existed)</html>");
		lblHideunhideInformation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHideunhideInformation.setBounds(10, 285, 424, 118);
		getContentPane().add(lblHideunhideInformation);
		encryptionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EncryptionDecryptionFrame().setVisible(true);
				close();
			}
		});
	}
}
