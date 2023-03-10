package cep;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.URI;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setResizable(false);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/home.png")));
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("@Autor Marcos Dantas");
		lblNewLabel.setBounds(27, 96, 203, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar CEP - Ver 1.0");
		lblNewLabel_1.setBounds(27, 41, 160, 14);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnGithub = new JButton("");
		btnGithub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://github.com/Mdantas26?tab=repositories");
			}
		});
		btnGithub.setBackground(SystemColor.control);
		btnGithub.setBorder(null);
		btnGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGithub.setIcon(new ImageIcon(Sobre.class.getResource("/img/1298743_github_git_logo_social_icon.png")));
		btnGithub.setToolTipText("Github");
		btnGithub.setBounds(139, 173, 48, 48);
		getContentPane().add(btnGithub);
		
		JButton btnLinkedin = new JButton("");
		btnLinkedin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://www.linkedin.com/in/marcos-costa-1aa3b8151/");
			}
		});
		btnLinkedin.setBackground(SystemColor.control);
		btnLinkedin.setIcon(new ImageIcon(Sobre.class.getResource("/img/834713_linkedin_icon.png")));
		btnLinkedin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLinkedin.setBorder(null);
		btnLinkedin.setToolTipText("Linkedin");
		btnLinkedin.setBounds(272, 173, 48, 48);
		getContentPane().add(btnLinkedin);

	}//fim do construtor
	private void link(String site) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI(site);
			desktop.browse(uri);
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
