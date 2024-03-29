package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import DAO.HotelDAO;
import conexao.Conexao;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Cuidadores extends JFrame {

	private JPanel contentPane;
	private JButton btnVai;
	private JButton btnVolta;
	private JTextField txtNomeCliente;
	private MaskFormatter mascaras;
	private JFormattedTextField data;
	private JFormattedTextField data2;
	private JTextField txtNome;
	JTextPane txtDs = new JTextPane();
	int one = -1;
	private JTextField txtObs;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton gato = new JRadioButton("Gato");
	JRadioButton cachorro = new JRadioButton("Cachorro");
	private String tipo;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cuidadores frame = new Cuidadores();
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
	public Cuidadores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton sair = new JButton("");
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		txtNomeCliente = new JTextField();
		txtNomeCliente.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		txtNomeCliente.setText("Digite seu nome...");
		txtNomeCliente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNomeCliente.setText("");
			}
		});

		// https://www.devmedia.com.br/java-swing-conheca-os-componentes-jtextfield-e-jformattedtextfield/30981
		try {
			mascaras = new MaskFormatter("##/##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		JRadioButton gato = new JRadioButton("Gato");
		buttonGroup.add(gato);
		gato.setContentAreaFilled(false);
		gato.setBorderPainted(false);
		gato.setOpaque(false);
		gato.setFocusPainted(false);
		gato.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		gato.setBounds(168, 220, 63, 23);
		contentPane.add(gato);
		
		JRadioButton cachorro = new JRadioButton("Cachorro");
		buttonGroup.add(cachorro);
		cachorro.setContentAreaFilled(false);
		cachorro.setBorderPainted(false);
		cachorro.setOpaque(false);
		cachorro.setFocusPainted(false);
		cachorro.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		cachorro.setBounds(243, 220, 89, 23);
		contentPane.add(cachorro);

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtNomeCliente.getText();
				String dataComeco = data.getText();
				String dataFinal = data2.getText();
				String cuidador = txtNome.getText();
				String obs = txtObs.getText();
				
				if (cachorro.isSelected()){
					tipo = "Cachorro";
					System.out.println("dog");
				} else if (gato.isSelected()) {
					tipo = "Gato";
					System.out.println("cat");
				}
				
				
				DAO.HotelDAO.Agendar(nome, dataComeco, dataFinal, cuidador, obs, tipo);
				
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(424, 139, 191, 28);
		contentPane.add(scrollPane);

		JTextPane txtDs = new JTextPane();
		txtDs.setEditable(false);
		txtDs.setContentType("");
		txtDs.setBounds(424, 179, 191, 135);
		contentPane.add(txtDs);
		
		txtNome = new JTextField();
		txtNome.setEditable(false);
		scrollPane.setViewportView(txtNome);
		txtNome.setColumns(10);
		btnFinalizar.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnFinalizar.setBounds(182, 344, 101, 23);
		contentPane.add(btnFinalizar);

		btnVai = new JButton("");
		btnVai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				one++;
				ResultSet rs = HotelDAO.findAll(one);

				try {
					if (rs.next()) {
						String nome = rs.getString("NM_Cuidador");
						String ds = rs.getString("DS_Cuidador");

						txtNome.setText(nome);
						txtDs.setText(ds);

					} else {
						one = -1;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVai.setContentAreaFilled(false);
		btnVai.setOpaque(false);
		btnVai.setFocusPainted(false);
		btnVai.setBorderPainted(false);

		btnVai.setIcon(new ImageIcon(Cuidadores.class.getResource("/imagens/3847912-128(2).png")));
		btnVai.setBounds(523, 325, 89, 51);
		contentPane.add(btnVai);

		btnVolta = new JButton("");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				one--;
				ResultSet rs = HotelDAO.findAll(one);

				try {
					if (rs.next()) {
						String nome = rs.getString("NM_Cuidador");
						String ds = rs.getString("DS_Cuidador");

						txtNome.setText(nome);
						txtDs.setText(ds);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		});
		btnVolta.setContentAreaFilled(false);
		btnVolta.setOpaque(false);
		btnVolta.setFocusPainted(false);
		btnVolta.setBorderPainted(false);

		btnVolta.setIcon(new ImageIcon(Cuidadores.class.getResource("/imagens/3847912-128(1).png")));
		btnVolta.setBounds(424, 325, 89, 51);
		contentPane.add(btnVolta);

		JLabel lblCuidadores = new JLabel("Cuidadores");
		lblCuidadores.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		lblCuidadores.setBounds(461, 108, 117, 20);
		contentPane.add(lblCuidadores);

		data2 = new JFormattedTextField(mascaras);
		data2.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		data2.setBounds(312, 183, 54, 19);
		contentPane.add(data2);
		
		data = new JFormattedTextField(mascaras);
		data.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		data.setBounds(202, 183, 54, 19);
		contentPane.add(data);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Cuidadores.class.getResource("/imagens/Sem t\u00EDtulo.png")));
		lblNewLabel.setBounds(399, 108, 2, 268);
		contentPane.add(lblNewLabel);
		txtNomeCliente.setBounds(166, 130, 210, 23);
		contentPane.add(txtNomeCliente);
		txtNomeCliente.setColumns(10);

		JLabel lblAt = new JLabel("at\u00E9");
		lblAt.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		lblAt.setBounds(266, 182, 36, 20);
		contentPane.add(lblAt);

		JLabel lblData = new JLabel("Data: ");
		lblData.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		lblData.setBounds(97, 182, 63, 20);
		contentPane.add(lblData);

		JLabel label = new JLabel("Nome:");
		label.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		label.setBounds(97, 131, 73, 20);
		contentPane.add(label);
		
		JLabel lblAlgumaObservao = new JLabel("Alguma observa\u00E7\u00E3o?");
		lblAlgumaObservao.setFont(new Font("Lucida Bright", Font.PLAIN, 18));
		lblAlgumaObservao.setBounds(150, 253, 205, 28);
		contentPane.add(lblAlgumaObservao);
		sair.setContentAreaFilled(false);
		sair.setOpaque(false);
		sair.setFocusPainted(false);
		sair.setBorderPainted(false);
		sair.setIcon(new ImageIcon(Cuidadores.class.getResource("/imagens/183189-128(1).png")));
		sair.setBounds(10, 325, 63, 51);
		contentPane.add(sair);
		
		

		JLabel lblCuidador = new JLabel("Hotel IPet");
		lblCuidador.setFont(new Font("Bauhaus 93", Font.PLAIN, 50));
		lblCuidador.setBounds(231, 37, 247, 65);
		contentPane.add(lblCuidador);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		lblTipo.setBounds(97, 224, 63, 23);
		contentPane.add(lblTipo);
		
		JLabel lblDe = new JLabel("De");
		lblDe.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		lblDe.setBounds(165, 177, 46, 31);
		contentPane.add(lblDe);
		
		txtObs = new JTextField();
		txtObs.setBounds(139, 280, 205, 20);
		contentPane.add(txtObs);
	
		txtObs.setColumns(10);

		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(Cuidadores.class.getResource("/imagens/Background.jpg")));
		background.setBounds(0, 0, 644, 401);
		contentPane.add(background);
		
		textField = new JTextField();
		textField.setBounds(74, 11, -31, 9);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}