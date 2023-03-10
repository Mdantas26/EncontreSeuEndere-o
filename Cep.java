package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

@SuppressWarnings("serial")
public class Cep extends JFrame {

	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JLabel lblStatus;
	private JComboBox cboUf;
	private JButton btnLimpar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Cep() {
		setTitle("Encontre seu Endereço");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/icons8-código-postal-24.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CEP");
		lblNewLabel.setBounds(10, 23, 46, 14);
		contentPane.add(lblNewLabel);

		txtCep = new JTextField();
		txtCep.setBounds(66, 20, 86, 20);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Endereço");
		lblNewLabel_1.setBounds(10, 70, 55, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Bairro");
		lblNewLabel_1_1.setBounds(10, 113, 46, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Cidade");
		lblNewLabel_1_2.setBounds(10, 174, 46, 14);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("UF");
		lblNewLabel_1_3.setBounds(270, 174, 16, 14);
		contentPane.add(lblNewLabel_1_3);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(66, 67, 340, 20);
		contentPane.add(txtEndereco);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(66, 110, 340, 20);
		contentPane.add(txtBairro);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(66, 171, 194, 20);
		contentPane.add(txtCidade);

		JComboBox<?> cboUf = new JComboBox();
		cboUf.setToolTipText("UF");
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA",
						"PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));
		cboUf.setBounds(296, 170, 46, 22);
		contentPane.add(cboUf);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCep.setText("");
				txtEndereco.setText("");
				txtBairro.setText("");
				txtCidade.setText("");
				cboUf.setSelectedItem("");
				lblStatus.setIcon(null);
			}
		});
		btnLimpar.setBounds(10, 227, 89, 23);
		contentPane.add(btnLimpar);

		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txtCep.requestFocus();
				} else {
					buscarCep(cboUf);
					// buscar CEP
				}
			}
		});
		btnCep.setBounds(211, 19, 89, 23);
		contentPane.add(btnCep);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/img/2303195_about_faq_help_info_information_icon.png")));
		btnSobre.setToolTipText("Sobre");
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBorder(null);
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setBounds(320, 8, 48, 48);
		contentPane.add(btnSobre);
		/* Uso da biblioteca Atxy2k para validação do campo txtCep */
		RestrictedTextField validar = new RestrictedTextField(txtCep);

		lblStatus = new JLabel("");
		lblStatus.setBounds(157, 20, 20, 20);
		contentPane.add(lblStatus);
		validar.setOnlyNums(true);
		validar.setLimit(8); 
	}

	// fim do construtor

	private void buscarCep(JComboBox<?> bntUf) {
		String logradouro="";
		String tipoLogradouro="";
		String resultado=null;
		String cep= txtCep.getText();
		try {
			URL url = new URL ("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml\r\n");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			 for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				  Element element = it.next();
			       if (element.getQualifiedName().equals("cidade")) {
			    	   txtCidade.setText(element.getText());
			       }
			       if (element.getQualifiedName().equals("bairro")) {
			    	   txtBairro.setText(element.getText());
			       }		      
			       if (element.getQualifiedName().equals("uf")) {
			    	   bntUf.setSelectedItem(element.getText());
			       }
			       if (element.getQualifiedName().equals("tipo_logradouro")) {
			    	   tipoLogradouro = element.getText();
			    	   
			       }
			       if (element.getQualifiedName().equals("logradouro")) {
			    	   logradouro = element.getText();
			       }
			       if (element.getQualifiedName().equals("resultado")) {
			    		resultado = element.getText();
			       		if ("1".equals(resultado)) {
			       			lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
			       		}else {
			       			JOptionPane.showMessageDialog(null, "CEP não encontrado");
			       		}
			       	}
			 }
			 // setar o campo endereco
			 	txtEndereco.setText(tipoLogradouro +" "+ logradouro);
		}catch(Exception e) {
						System.out.println(e);
		}
}
	/**
	 * limpar
	 */
	
	private void limpar() {
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		txtCep.requestFocus();
		lblStatus.setIcon(null);
		
		
	}
}