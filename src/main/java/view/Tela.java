package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AlunoDAO;
import model.Aluno;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.TextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtRa;
	private JLabel lblEmail;
	private JLabel lblEmail_1;
	private JLabel lblDataNascimento;
	private JLabel lblEndereo;
	private JLabel lblPeriodo;
	private JTextField txtDataNascimento;
	private JTextField txtEmail;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private TextArea txtListar;
	private JComboBox cmbPeriodo;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnListar;
	private JButton btnAtualizar;
	private JButton btnExcluir;
	private JButton btnConsultar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
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
	public Tela() {
		setTitle("Sistema Academico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("RA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(30, 11, 45, 24);
		contentPane.add(lblNewLabel);
		
		txtRa = new JTextField();
		txtRa.setBounds(85, 13, 223, 24);
		contentPane.add(txtRa);
		txtRa.setColumns(8);
		
		lblEmail = new JLabel("Nome");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(10, 46, 53, 24);
		contentPane.add(lblEmail);
		
		lblEmail_1 = new JLabel("Email");
		lblEmail_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail_1.setBounds(10, 149, 100, 24);
		contentPane.add(lblEmail_1);
		
		lblDataNascimento = new JLabel("Data Nascimento");
		lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataNascimento.setBounds(10, 190, 113, 24);
		contentPane.add(lblDataNascimento);
		
		lblEndereo = new JLabel("Endereço");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndereo.setBounds(10, 79, 66, 24);
		contentPane.add(lblEndereo);
		
		lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPeriodo.setBounds(10, 114, 100, 24);
		contentPane.add(lblPeriodo);
		
		txtDataNascimento = new JTextField();
		txtDataNascimento.setColumns(10);
		txtDataNascimento.setBounds(133, 192, 152, 24);
		contentPane.add(txtDataNascimento);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(85, 151, 353, 24);
		contentPane.add(txtEmail);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(85, 48, 353, 24);
		contentPane.add(txtNome);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(85, 81, 426, 24);
		contentPane.add(txtEndereco);
		
		txtListar = new TextArea();
		txtListar.setEditable(false);
		txtListar.setBounds(10, 235, 686, 177);
		contentPane.add(txtListar);
		
		cmbPeriodo = new JComboBox();
		cmbPeriodo.setModel(new DefaultComboBoxModel(new String[] {"Selecione o periodo", "Manhã", "Tarde", "Noite"}));
		cmbPeriodo.setBounds(85, 116, 119, 24);
		contentPane.add(cmbPeriodo);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRa.setText(null);
				txtEmail.setText(null);
				txtNome.setText(null);
				txtDataNascimento.setText(null);
				txtEndereco.setText(null);
				txtListar.setText(null);
				cmbPeriodo.setSelectedIndex(0);
			}
		});
		btnNovo.setBounds(140, 429, 100, 34);
		contentPane.add(btnNovo);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno aluno = new Aluno();
				boolean valid = false;
				try {
					valid = getDados();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					return;
				}
				if (valid) {
					aluno.setRa(Integer.parseInt(txtRa.getText().toString()));
					aluno.setNome(txtNome.getText().toString());
					aluno.setEmail(txtEmail.getText().toString());
					aluno.setDataNascimento(txtDataNascimento.getText().toString());
					aluno.setEndereco(txtEndereco.getText().toString());
					aluno.setPeriodo(String.valueOf(cmbPeriodo.getSelectedItem()));
				}
				try {
					AlunoDAO dao = new AlunoDAO();
					dao.salvar(aluno);
					JOptionPane.showMessageDialog(null, "Aluno cadastrado");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnSalvar.setBounds(30, 429, 100, 34);
		contentPane.add(btnSalvar);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AlunoDAO dao = new AlunoDAO();
					List<Aluno> alunos = dao.listar();
					txtListar.setText(null);
					for(var al : alunos) {
						txtListar.append("Ra: " + al.getRa() + "\nNome: " + al.getNome() + "\nEmail: " + al.getEmail() + "\nData de nascimento: " + al.getDataNascimento() + "\nEndereço: " + al.getEndereco()
						+ "\nPeriodo: " + al.getPeriodo()+ "\n=================\n");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnListar.setBounds(395, 429, 100, 34);
		contentPane.add(btnListar);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno aluno = new Aluno();
				boolean valid = false;
				try {
					valid = getDados();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					return;
				}
				if (valid) {
					aluno.setRa(Integer.parseInt(txtRa.getText().toString()));
					aluno.setNome(txtNome.getText().toString());
					aluno.setEmail(txtEmail.getText().toString());
					aluno.setDataNascimento(txtDataNascimento.getText().toString());
					aluno.setEndereco(txtEndereco.getText().toString());
					aluno.setPeriodo(String.valueOf(cmbPeriodo.getSelectedItem()));
				}
				try {
					AlunoDAO dao = new AlunoDAO();
					dao.atualizar(aluno);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
			}
		});
		btnAtualizar.setBounds(505, 429, 100, 34);
		contentPane.add(btnAtualizar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno aluno = new Aluno();
				try {
					aluno.setRa(Integer.parseInt(txtRa.getText()));
				}catch (Exception err) {
					JOptionPane.showMessageDialog(null, "RA deve ser um numero inteiro de até 10 digitos");
					return;
				}
				try {
					AlunoDAO dao = new AlunoDAO();
					dao.excluir(aluno);
					JOptionPane.showMessageDialog(null, "Aluno deletado");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnExcluir.setBounds(615, 429, 100, 34);
		contentPane.add(btnExcluir);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno aluno = new Aluno();
				try {
					aluno.setRa(Integer.parseInt(txtRa.getText()));
				}catch (Exception err) {
					JOptionPane.showMessageDialog(null, "RA deve ser um numero inteiro de até 10 digitos");
					return;
				}
				try {
					AlunoDAO dao = new AlunoDAO();
					aluno = dao.consultar(Integer.parseInt(txtRa.getText().toString()));
					txtNome.setText(aluno.getNome());
					txtEmail.setText(aluno.getEmail());
					txtDataNascimento.setText(aluno.getDataNascimento());
					txtEndereco.setText(aluno.getEndereco());
					cmbPeriodo.setSelectedItem(aluno.getPeriodo());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnConsultar.setBounds(270, 429, 100, 34);
		contentPane.add(btnConsultar);
	}
	
	public  boolean getDados() throws Exception {
		Aluno aluno = new Aluno();
		try {
			Integer.parseInt(txtRa.getText().toString());
		}catch (Exception err) {
			throw new Exception("RA deve ser um numero inteiro de até 10 digitos e maior que 0");
		}
		if (!txtNome.getText().matches("^[a-zA-Z]+$")) {
			throw new Exception("Nome deve ser inteiramente de caracteres ");
		}
		if (!Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$").matcher(txtEmail.getText().toString()).matches()) {
			throw new Exception("Insira um email valido");
		}
		try {
			getData();
		}catch (Exception e) {
			throw new Exception("Insira uma data válida no seguinte formado: 00/00/0000");
		}
		if (txtEndereco.getText().equals("")) {
			throw new Exception("Insira um endereço");
		}
		
		if (cmbPeriodo.getSelectedItem().equals("Selecione o periodo")) {
			throw new Exception("Selecione um periodo valido");
		}
		return true;
	}
	
	public void getData() throws Exception {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		String dataInput = txtDataNascimento.getText();
		try {
			Date data = format.parse(dataInput);
			Date now = new Date();
			if(data.after(now)) {
				throw new Exception();
			}
			
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
