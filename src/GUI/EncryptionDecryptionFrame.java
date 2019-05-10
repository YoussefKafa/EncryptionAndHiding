package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import AES.AES;
import BlowFish.BlowFishEnc;
import Details.DetailsMethods;
import IDEA.Convert;
import IDEA.IDEA;
import IDEA.Key;
import IO.FilterTheFiles;
import IO.IOutils;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EncryptionDecryptionFrame extends JFrame {
	static String e="";
	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private static JTextField textFieldKeyPath;
	private static JTextField textFieldForText;
	JButton btnGenerateKeys;
	static PrintWriter writer;
	private File fcCurDir;
	static JTextArea textAreaForKey;
	static JTextArea textAreaForText;
	static JRadioButton textInputRadioButton;
	static JRadioButton fileInputRadioButton;
	static JCheckBox chckbxAsciiMessage;
	static JCheckBox chckbxAsciiKey;
	static JCheckBox chckbxHexadecimalMessage;
	static JCheckBox chckbxHexadecimalKey;
	static JCheckBox chckbxBinaryMessage;
	static JCheckBox chckbxBinaryKey;
	static JCheckBox chckbxCalculateBits;
	static JCheckBox chckbxCalculateBytes;
	static JCheckBox chckbxCalculateWords;
	static JRadioButton textInputRadioButtonForText;
	static JRadioButton fileInputRadioButtonForText;
	static JTextArea textAreaResults;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptionDecryptionFrame frame = new EncryptionDecryptionFrame();
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
	public EncryptionDecryptionFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 532);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton encryptButton = new JButton("Encrpt");
		encryptButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		encryptButton.setBounds(151, 296, 136, 23);
		contentPane.add(encryptButton);

		JButton btnAddDetails = new JButton("add details");
		btnAddDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddDetails.setBounds(151, 438, 136, 23);
		contentPane.add(btnAddDetails);

		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResults.setBounds(468, 10, 63, 17);
		contentPane.add(lblResults);
		Color color = new Color(240, 240, 250);

		JButton decryptButton = new JButton("Decrypt");
		decryptButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		decryptButton.setBounds(151, 296, 136, 25);
		contentPane.add(decryptButton);
		decryptButton.setVisible(false);

		JLabel lblChooseTheDetails = new JLabel("choose the details you want (for inputs) :");
		lblChooseTheDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseTheDetails.setBounds(10, 321, 277, 23);
		contentPane.add(lblChooseTheDetails);

		chckbxAsciiMessage = new JCheckBox("ASCII message");
		chckbxAsciiMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxAsciiMessage.setBounds(0, 351, 117, 23);
		contentPane.add(chckbxAsciiMessage);

		chckbxAsciiKey = new JCheckBox("ASCII key");
		chckbxAsciiKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxAsciiKey.setBounds(119, 351, 89, 23);
		contentPane.add(chckbxAsciiKey);

		 chckbxHexadecimalMessage = new JCheckBox("Hexadecimal message");
		chckbxHexadecimalMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxHexadecimalMessage.setBounds(213, 351, 159, 23);
		contentPane.add(chckbxHexadecimalMessage);

		chckbxHexadecimalKey = new JCheckBox("Hexadecimal key");
		chckbxHexadecimalKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxHexadecimalKey.setBounds(0, 377, 127, 23);
		contentPane.add(chckbxHexadecimalKey);

		 chckbxBinaryMessage = new JCheckBox("Binary message");
		chckbxBinaryMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxBinaryMessage.setBounds(129, 377, 127, 23);
		contentPane.add(chckbxBinaryMessage);

		 chckbxBinaryKey = new JCheckBox("Binary key");
		chckbxBinaryKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxBinaryKey.setBounds(258, 377, 97, 23);
		contentPane.add(chckbxBinaryKey);

		 chckbxCalculateBits = new JCheckBox("Calculate bits");
		chckbxCalculateBits.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxCalculateBits.setBounds(0, 403, 109, 23);
		contentPane.add(chckbxCalculateBits);

		chckbxCalculateBytes = new JCheckBox("Calculate bytes");
		chckbxCalculateBytes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxCalculateBytes.setBounds(119, 403, 121, 23);
		contentPane.add(chckbxCalculateBytes);

		 chckbxCalculateWords = new JCheckBox("Calculate words");
		chckbxCalculateWords.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxCalculateWords.setBounds(242, 405, 130, 23);
		contentPane.add(chckbxCalculateWords);

		JLabel lblInput = new JLabel("key input:");
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInput.setBounds(10, 53, 63, 23);
		contentPane.add(lblInput);
		fileInputRadioButton = new JRadioButton("file input");
		fileInputRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fileInputRadioButton.setBounds(99, 77, 109, 23);
		contentPane.add(fileInputRadioButton);
		textFieldKeyPath = new JTextField();
		textFieldKeyPath.setBounds(47, 105, 295, 28);
		contentPane.add(textFieldKeyPath);
		textFieldKeyPath.setColumns(10);
		JButton btnChoose = new JButton("Choose");
		btnChoose.setBounds(352, 108, 89, 23);
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] textFiles = new String[] { ".txt" };
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(fcCurDir);
				fc.setDialogTitle("Choose a file : ");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileFilter(new FilterTheFiles("text Files", textFiles));
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldKeyPath.setText(fc.getSelectedFile().getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You have not choose a file!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
				fcCurDir = fc.getCurrentDirectory();
			}
		});
		contentPane.add(btnChoose);
		textAreaForKey = new JTextArea();
		textAreaForKey.setWrapStyleWord(true);
		textAreaForKey.setLineWrap(true);
		textAreaForKey.setBounds(83, 107, 363, 45);
		textAreaForKey.setVisible(false);
		contentPane.add(textAreaForKey);
		JLabel pathLbl = new JLabel("path:");
		pathLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pathLbl.setBounds(10, 99, 48, 28);
		contentPane.add(pathLbl);
		textInputRadioButton = new JRadioButton("text input");
		textInputRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textInputRadioButton.setBounds(6, 77, 91, 23);
		contentPane.add(textInputRadioButton);
		JLabel enterKeyLbl = new JLabel("Enter a key:");
		enterKeyLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		enterKeyLbl.setBounds(10, 111, 87, 22);
		enterKeyLbl.setVisible(false);
		contentPane.add(enterKeyLbl);

		JLabel lblSelectTheAlgorithm = new JLabel("Please select an algorithm:");
		lblSelectTheAlgorithm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectTheAlgorithm.setBounds(10, 30, 174, 23);
		contentPane.add(lblSelectTheAlgorithm);

		JRadioButton aesRadioButton = new JRadioButton("AES");
		aesRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		aesRadioButton.setBounds(177, 30, 63, 23);
		contentPane.add(aesRadioButton);

		JRadioButton blowfishRadioButton = new JRadioButton("BlowFish");
		blowfishRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		blowfishRadioButton.setBounds(242, 30, 82, 23);
		contentPane.add(blowfishRadioButton);

		JRadioButton ideaRadioButton = new JRadioButton("IDEA");
		ideaRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ideaRadioButton.setBounds(326, 30, 57, 23);
		contentPane.add(ideaRadioButton);
		textInputRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pathLbl.setVisible(false);
				textFieldKeyPath.setVisible(false);
				btnChoose.setVisible(false);
				enterKeyLbl.setVisible(true);
				textAreaForKey.setVisible(true);
			}
		});
		fileInputRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pathLbl.setVisible(true);
				textFieldKeyPath.setVisible(true);
				btnChoose.setVisible(true);
				enterKeyLbl.setVisible(false);
				textAreaForKey.setVisible(false);
			}
		});

		JLabel lblTextInput = new JLabel("text input:");
		lblTextInput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTextInput.setBounds(14, 186, 73, 23);
		contentPane.add(lblTextInput);

		textInputRadioButtonForText = new JRadioButton("text input");
		textInputRadioButtonForText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textInputRadioButtonForText.setBounds(10, 216, 89, 23);
		contentPane.add(textInputRadioButtonForText);
		fileInputRadioButtonForText = new JRadioButton("file input");
		fileInputRadioButtonForText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fileInputRadioButtonForText.setBounds(103, 216, 109, 23);
		contentPane.add(fileInputRadioButtonForText);
		JLabel pathLabelForText = new JLabel("path:");
		pathLabelForText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pathLabelForText.setBounds(14, 240, 48, 23);
		contentPane.add(pathLabelForText);
		textFieldForText = new JTextField();
		textFieldForText.setBounds(51, 243, 295, 28);
		contentPane.add(textFieldForText);
		textFieldForText.setColumns(10);
		JButton chooseButtonForText = new JButton("Choose");
		chooseButtonForText.setBounds(356, 246, 89, 23);
		chooseButtonForText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] textFiles = new String[] { ".txt" };
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(fcCurDir);
				fc.setDialogTitle("Choose a file : ");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileFilter(new FilterTheFiles("text Files", textFiles));
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldForText.setText(fc.getSelectedFile().getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You have not choose a file!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
				fcCurDir = fc.getCurrentDirectory();
			}
		});
		contentPane.add(chooseButtonForText);
		textAreaForText = new JTextArea();
		textAreaForText.setBounds(87, 240, 363, 45);
		textAreaForText.setVisible(false);
		contentPane.add(textAreaForText);
		JLabel enterTextLblForText = new JLabel("Enter Text:");
		enterTextLblForText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		enterTextLblForText.setBounds(14, 246, 73, 14);
		enterTextLblForText.setVisible(false);
		contentPane.add(enterTextLblForText);
		fileInputRadioButtonForText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pathLabelForText.setVisible(true);
				textFieldForText.setVisible(true);
				chooseButtonForText.setVisible(true);
				enterTextLblForText.setVisible(false);
				textAreaForText.setVisible(false);
			}
		});
		textInputRadioButtonForText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pathLabelForText.setVisible(false);
				textFieldForText.setVisible(false);
				chooseButtonForText.setVisible(false);
				enterTextLblForText.setVisible(true);
				textAreaForText.setVisible(true);
			}
		});
		JLabel workingModeLbl = new JLabel("Please select a working mode:");
		workingModeLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		workingModeLbl.setBounds(10, 4, 198, 23);
		contentPane.add(workingModeLbl);

		JRadioButton encryptionRadioButton = new JRadioButton("Encryption");
		encryptionRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		encryptionRadioButton.setBounds(196, 4, 91, 23);
		contentPane.add(encryptionRadioButton);
		encryptionRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnGenerateKeys.setVisible(true);
				decryptButton.setVisible(false);
				encryptButton.setVisible(true);
			}
		});
		JRadioButton decryptionRadioButton = new JRadioButton("Decryption");
		decryptionRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		decryptionRadioButton.setBounds(288, 4, 109, 23);
		contentPane.add(decryptionRadioButton);
		decryptionRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnGenerateKeys.setVisible(false);
				encryptButton.setVisible(false);
				decryptButton.setVisible(true);
			}
		});
		// avoiding multiple radio button selection for inputText&inputFile for the key
		ButtonGroup group = new ButtonGroup();
		group.add(textInputRadioButton);
		group.add(fileInputRadioButton);
		// avoiding multiple radio button selection for algorithms radio buttons
		ButtonGroup group1 = new ButtonGroup();
		group1.add(aesRadioButton);
		group1.add(blowfishRadioButton);
		group1.add(ideaRadioButton);
		// avoiding multiple radio button selection for inputText&inputFIle for Text
		ButtonGroup group2 = new ButtonGroup();
		group2.add(fileInputRadioButtonForText);
		group2.add(textInputRadioButtonForText);
		// avoiding multiple radio button selection for encryption & decryption
		ButtonGroup group3 = new ButtonGroup();
		group3.add(encryptionRadioButton);
		group3.add(decryptionRadioButton);

		JButton btnBack = new JButton("back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(849, 461, 109, 31);
		Image img1 = new ImageIcon(this.getClass().getResource("/back_icon.png")).getImage();
		btnBack.setIcon(new ImageIcon(img1));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Options().setVisible(true);
			}
		});
		contentPane.add(btnBack);
		btnGenerateKeys = new JButton("Generate keys");
		btnGenerateKeys.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenerateKeys.setBounds(151, 163, 136, 23);
		contentPane.add(btnGenerateKeys);

		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(718, 461, 109, 31);
		Image img2 = new ImageIcon(this.getClass().getResource("/reset.png")).getImage();
		btnReset.setIcon(new ImageIcon(img2));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EncryptionDecryptionFrame().setVisible(true);
			}
		});
		contentPane.add(btnReset);
		 textAreaResults = new JTextArea();
		 GraphicsEnvironment.getLocalGraphicsEnvironment();
		    Font font = new Font("DejavuSans", Font.BOLD, 20);
		 textAreaResults.setBackground(color);
		 textAreaResults.setForeground(Color.LIGHT_GRAY);
		 textAreaResults.setFont(font);
		textAreaResults.setLineWrap(true);
		textAreaResults.setWrapStyleWord(true);
		textAreaResults.setText(e);
		contentPane.add(textAreaResults);
		JScrollPane sPane=new JScrollPane(textAreaResults,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sPane.setBounds(478, 38, 506, 412);
		contentPane.add(sPane);
		btnGenerateKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (encryptionRadioButton.isSelected() & aesRadioButton.isSelected()
						& textInputRadioButton.isSelected()) {
					try {
						choosingTextInputForAesKey();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (encryptionRadioButton.isSelected() & aesRadioButton.isSelected()
						& fileInputRadioButton.isSelected()) {
					try {
						choosingFileInputForAesKey();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (encryptionRadioButton.isSelected() & blowfishRadioButton.isSelected()
						& textInputRadioButton.isSelected()) {
					try {
						choosingTextInputForBlowFishKey();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (encryptionRadioButton.isSelected() & blowfishRadioButton.isSelected()
						& fileInputRadioButton.isSelected()) {
					try {
						choosingFileInputForBlowFishKey();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (encryptionRadioButton.isSelected() & ideaRadioButton.isSelected()
						& textInputRadioButton.isSelected()) {
					try {
						choosingTextInputForIdeaKey();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (encryptionRadioButton.isSelected() & ideaRadioButton.isSelected()
						& fileInputRadioButton.isSelected()) {
					try {
						choosingFileInputForIdeaKey();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//AES
				if (encryptionRadioButton.isSelected() & aesRadioButton.isSelected()
						& (textInputRadioButton.isSelected()||fileInputRadioButton.isSelected())& (textInputRadioButtonForText.isSelected()||fileInputRadioButtonForText.isSelected())) {
					if(textInputRadioButtonForText.isSelected())
						try {
							choosingTextInputForAesEncryption();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else if (fileInputRadioButtonForText.isSelected())
						try {
							choosingFileInputForAesEncryption();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				//BlowFish
				if (encryptionRadioButton.isSelected() & blowfishRadioButton.isSelected()
						& (textInputRadioButton.isSelected()||fileInputRadioButton.isSelected())& (textInputRadioButtonForText.isSelected()||fileInputRadioButtonForText.isSelected())) {
					if(textInputRadioButtonForText.isSelected())
						try {
							choosingTextInputForBlowfishEncryption();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (fileInputRadioButtonForText.isSelected())
						try {
							choosingFileInputForBlowfishEncryption();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				//Idea
				if (encryptionRadioButton.isSelected() & ideaRadioButton.isSelected()
						& (textInputRadioButton.isSelected()||fileInputRadioButton.isSelected())& (textInputRadioButtonForText.isSelected()||fileInputRadioButtonForText.isSelected())) {
					if(textInputRadioButtonForText.isSelected())
						try {
							choosingTextInputForIdeaEncryption();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (fileInputRadioButtonForText.isSelected())
						try {
							choosingFileInputForIdeaEncryption();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
		});
		btnAddDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					getDetails();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
public static void getDetails() throws IOException {
	String details="";
	if (chckbxAsciiKey.isSelected() & (textInputRadioButton.isSelected() || fileInputRadioButton.isSelected())) {
		if(textInputRadioButton.isSelected())
			try {
				details+=DetailsMethods.stringToASCII(textAreaForKey.getText())+"\n";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		else if (fileInputRadioButton.isSelected()) {
			try {
				details+=DetailsMethods.stringToASCII(IOutils.readAfile(textFieldKeyPath.getText()))+"\n";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	if (chckbxAsciiMessage.isSelected() & (textInputRadioButtonForText.isSelected() || fileInputRadioButtonForText.isSelected())) {
		if(textInputRadioButtonForText.isSelected())
			try {
				details+=DetailsMethods.stringToASCII(textAreaForText.getText())+"\n";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		else if (fileInputRadioButtonForText.isSelected()) {
			try {
				details+=DetailsMethods.stringToASCII(IOutils.readAfile(textFieldForText.getText()))+"\n";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	if(chckbxBinaryKey.isSelected()& (textInputRadioButton.isSelected() || fileInputRadioButton.isSelected())) {
		if(textInputRadioButton.isSelected())
			details+=DetailsMethods.stringToBinary((textAreaForKey.getText())).toString()+"\n";
		else if (fileInputRadioButton.isSelected()) {
			details+=DetailsMethods.stringToBinary(IOutils.readAfile(textFieldKeyPath.getText())).toString()+"\n";
		}
	}
	if (chckbxBinaryMessage.isSelected()& (textInputRadioButtonForText.isSelected() || fileInputRadioButtonForText.isSelected())) {
		if(textInputRadioButtonForText.isSelected())
			details+=DetailsMethods.stringToBinary(textAreaForText.getText()).toString()+"\n";
		else if (fileInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.stringToBinary(IOutils.readAfile(textFieldForText.getText())).toString()+"\n";
		}
	}
	if(chckbxHexadecimalKey.isSelected() &(textInputRadioButton.isSelected() || fileInputRadioButton.isSelected()) ) {
		if(textInputRadioButton.isSelected())
			details+=Convert.toHexadecimal((textAreaForKey.getText()))+"\n";
		else if (fileInputRadioButton.isSelected()) {
			details+=Convert.toHexadecimal(IOutils.readAfile(textFieldKeyPath.getText()))+"\n";
		}
	}
	if(chckbxHexadecimalMessage.isSelected()& (textInputRadioButtonForText.isSelected() || fileInputRadioButtonForText.isSelected()) ) {
		if(textInputRadioButtonForText.isSelected())
			details+=Convert.toHexadecimal(textAreaForText.getText())+"\n";
		else if (fileInputRadioButtonForText.isSelected()) {
			details+=Convert.toHexadecimal(IOutils.readAfile(textFieldForText.getText()))+"\n";
		}
	}
	if(chckbxCalculateBits.isSelected()) {
		if(textInputRadioButton.isSelected()) {
			details+=DetailsMethods.calculateBits((textAreaForKey.getText()))+"\n";
		}
		if(fileInputRadioButton.isSelected()) {
			details+=DetailsMethods.calculateBits(IOutils.readAfile(textFieldKeyPath.getText()))+"\n";
		}
		if(textInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.calculateBits(textAreaForText.getText())+"\n";
		}
		if(fileInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.calculateBits(IOutils.readAfile(textFieldForText.getText()))+"\n";
		}
	}
	if(chckbxCalculateBytes.isSelected()) {
		if(textInputRadioButton.isSelected()) {
			details+=DetailsMethods.calculateBytes((textAreaForKey.getText()))+"\n";
		}
		if(fileInputRadioButton.isSelected()) {
			details+=DetailsMethods.calculateBytes(IOutils.readAfile(textFieldKeyPath.getText()))+"\n";
		}
		if(textInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.calculateBytes(textAreaForText.getText())+"\n";
		}
		if(fileInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.calculateBytes(IOutils.readAfile(textFieldForText.getText()))+"\n";
		}
	}
	if(chckbxCalculateWords.isSelected()) {
		if(textInputRadioButton.isSelected()) {
			details+=DetailsMethods.calculateWords((textAreaForKey.getText()))+"\n";
		}
		if(fileInputRadioButton.isSelected()) {
			details+=DetailsMethods.calculateWords(IOutils.readAfile(textFieldKeyPath.getText()))+"\n";
		}
		if(textInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.calculateWords(textAreaForText.getText())+"\n";
		}
		if(fileInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.calculateWords(IOutils.readAfile(textFieldForText.getText()))+"\n";
		}
	}
	textAreaResults.setText(details);
	IOutils.writeAfile("D:\\details.txt", details);
}
public static  void choosingFileInputForIdeaEncryption() throws IOException {
	String text=IOutils.readAfile(textFieldForText.getText());
	String key="";
	if (textInputRadioButton.isSelected()) {
		key=textAreaForKey.getText();
		String resultingCipher=IDEA.encrypt(text, key);
		textAreaResults.setText(resultingCipher);
	}
	else if (fileInputRadioButton.isSelected()) {
		//get the content of original key file
		key=IOutils.readAfile(textFieldKeyPath.getText());
		String resultingCipher=IDEA.encrypt(text, key);
		textAreaResults.setText(resultingCipher);
	}
	}

public static void choosingTextInputForIdeaEncryption() throws IOException {
	String text=textAreaForText.getText();
	String key="";
	if (textInputRadioButton.isSelected()) {
		key=textAreaForKey.getText();
		String resultingCipher=IDEA.encrypt(text, key);
		textAreaResults.setText(resultingCipher);
	}
	else if (fileInputRadioButton.isSelected()) {
		//get the content of original key file
		key=IOutils.readAfile(textFieldKeyPath.getText());
		String resultingCipher=IDEA.encrypt(text, key);
		textAreaResults.setText(resultingCipher);
	}
	}

public static void choosingFileInputForBlowfishEncryption() throws IOException {
	String text=IOutils.readAfile(textFieldForText.getText());
	String key="";
	if (textInputRadioButton.isSelected()) {
		key=textAreaForKey.getText();
		String resultingCipher=BlowFishEnc.encryptBlowfish(text, key);
		textAreaResults.setText(resultingCipher);
		IOutils.writeAfile("D:\\blowfishCipher.txt", resultingCipher);
	
	}
	else if (fileInputRadioButton.isSelected()) {
		//get the content of original key file
		key=IOutils.readAfile(textFieldKeyPath.getText());
		String resultingCipher=BlowFishEnc.encryptBlowfish(text, key);
		textAreaResults.setText(resultingCipher);
		IOutils.writeAfile("D:\\blowfishCipher.txt", resultingCipher);
	}
	}

public static void choosingTextInputForBlowfishEncryption() throws IOException {
	String e= BlowFishEnc.encryptBlowfish(textAreaForText.getText(), textAreaForKey.getText());
	//writing
	IOutils.writeAfile("D:\\blowfishCipher.txt", e);
	//reading
	String result=IOutils.readAfile("D:\\blowfishCipher.txt");
	textAreaResults.setText(result);
	}

public static void choosingTextInputForAesEncryption() throws IOException {
	String text=textAreaForText.getText();
	String key="";
	if (textInputRadioButton.isSelected()) {
		key=Convert.toHexadecimal(textAreaForKey.getText());
		String resultingCipher=new AES().encrypt(Convert.toHexadecimal(text), key);
		textAreaResults.setText(resultingCipher);
	}
	else if (fileInputRadioButton.isSelected()) {
		//get the content of original key file and convert it to hexadecimal
		key=Convert.toHexadecimal(IOutils.readAfile(textFieldKeyPath.getText()));
		String resultingCipher=new AES().encrypt(Convert.toHexadecimal(text), key);
		textAreaResults.setText(resultingCipher);
	}
}
public static void choosingFileInputForAesEncryption() throws IOException {
	String text=IOutils.readAfile(textFieldForText.getText());
	String key="";
	if (textInputRadioButton.isSelected()) {
		key=Convert.toHexadecimal(textAreaForKey.getText());
		String resultingCipher=new AES().encrypt(Convert.toHexadecimal(text), key);
		textAreaResults.setText(resultingCipher);
	}
	else if (fileInputRadioButton.isSelected()) {
		//get the content of original key file and convert it to hexadecimal
		key=Convert.toHexadecimal(IOutils.readAfile(textFieldKeyPath.getText()));
		String resultingCipher=new AES().encrypt(Convert.toHexadecimal(text), key);
		textAreaResults.setText(resultingCipher);
	}
}
	public static void choosingTextInputForAesKey() throws IOException {
		textAreaResults.setText(AES.getAesKeys(Convert.toHexadecimal(textAreaForKey.getText())));
		IOutils.writeAfile("D:\\aesKeys.txt", AES.getAesKeys(Convert.toHexadecimal(textAreaForKey.getText())));
	}

	public static void choosingFileInputForAesKey() throws IOException {
		// The name of the file to open.
		String fileName = textFieldKeyPath.getText();
		String key = IOutils.readAfile(fileName);
		textAreaResults.setText(AES.getAesKeys(Convert.toHexadecimal(key)));
		IOutils.writeAfile("D:\\aesKeys.txt",AES.getAesKeys(Convert.toHexadecimal(key)));
	}

	
	public static void choosingTextInputForBlowFishKey() throws IOException {
		BlowFishEnc blowFishEnc = new BlowFishEnc();
		String string = textAreaForKey.getText();
		textAreaResults.setText(blowFishEnc.getKey(string));
		IOutils.writeAfile("D:\\blowfishKey.txt", blowFishEnc.getKey(string));
	}

	public static void choosingFileInputForBlowFishKey() throws IOException {
		BlowFishEnc blowFishEnc = new BlowFishEnc();
		String fileName = textFieldKeyPath.getText();
		String key = IOutils.readAfile(fileName);
		textAreaResults.setText(blowFishEnc.getKey(key));
		IOutils.writeAfile("D:\\blowfishkey.txt", blowFishEnc.getKey(key));
	}

	public static void choosingFileInputForIdeaKey() throws IOException {
		String fileName = textFieldKeyPath.getText();
		String key = IOutils.readAfile(fileName);
		Key k = new Key(key);
		textAreaResults.setText(k.getEncKeys());
		IOutils.writeAfile("D:\\ideaKeys.txt", k.getEncKeys());
	}

	public static void choosingTextInputForIdeaKey() throws IOException {
		String string = textAreaForKey.getText();
		String keys = "";
		Key key = new Key(string);
		keys = key.getEncKeys();
		textAreaResults.setText(keys);
		IOutils.writeAfile("D:\\ideaKeys.txt", keys);
	}
	public void close() {
		this.dispose();
	}
}
