package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class client extends JFrame {

	private final JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client frame = new client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public client() throws UnknownHostException, IOException {
		setTitle("org.example.client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 455);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Socket client = new Socket("localhost", 8866);

		textField = new JTextField();
		textField.setBounds(119, 127, 234, 27);
		contentPane.add(textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(119, 178, 234, 169);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JButton btnNewButton = new JButton("发送给管理员");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataOutputStream dos;
				try {
					dos = new DataOutputStream(client.getOutputStream());
					String s=textField.getText();
					dos.writeUTF("用户说："+s);
					textArea.append("用户说："+s);
					textArea.append("\n");
					dos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(408, 126, 123, 29);
		contentPane.add(btnNewButton);

		JButton button = new JButton("接收信息");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataInputStream dis;
				try {
					dis = new DataInputStream(client.getInputStream());
					String s = dis.readUTF();
					textArea.append(s);
					textArea.append("\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}
		});
		button.setBounds(408, 190, 123, 29);
		contentPane.add(button);

		JLabel label = new JLabel("用户聊天端");
		label.setFont(new Font("楷体", Font.PLAIN, 30));
		label.setBounds(283, 37, 208, 39);
		contentPane.add(label);
	}
}
