package GUI;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstFrame frame = new FirstFrame();
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
	public void close() {
		this.dispose();
	}

	public FirstFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u0639\u0646\u0648\u0627\u0646 \u0627\u0644\u0645\u0634\u0631\u0648\u0639");
		label.setBounds(181, 10, 71, 14);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u0627\u0644\u0637\u0644\u0627\u0628");
		label_1.setBounds(197, 79, 48, 14);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u0627\u0634\u0631\u0627\u0641 \u0627\u0644\u062F\u0643\u062A\u0648\u0631");
		label_2.setBounds(182, 162, 71, 14);
		contentPane.add(label_2);

		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Tishreen_University_logo.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(115, 188, 238, 236);
		contentPane.add(lblNewLabel);

		JButton button = new JButton("\u0627\u0644\u062A\u0627\u0644\u064A");
		Image img1 = new ImageIcon(this.getClass().getResource("/Next_Icon.png")).getImage();
		button.setIcon(new ImageIcon(img1));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Options().setVisible(true);
				close();
			}
		});
		button.setBounds(167, 450, 113, 40);
		contentPane.add(button);
	}
}
