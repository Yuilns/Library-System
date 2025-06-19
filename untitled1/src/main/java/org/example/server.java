package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					server frame = new server();
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
	 */
	public server() throws IOException {
		setTitle("server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ServerSocket server = new ServerSocket(8866);
		Socket accept = server.accept();

		textField = new JTextField();
		textField.setBounds(119, 111, 234, 27);
		contentPane.add(textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(119, 159, 234, 169);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JButton btnNewButton = new JButton("发送给用户");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DataOutputStream dos = new DataOutputStream(accept.getOutputStream());
					String s=textField.getText();
					dos.writeUTF("管理员说："+s);
					textArea.append("管理员说："+s);
					textArea.append("\n");
					dos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(408, 110, 123, 29);
		contentPane.add(btnNewButton);

		JButton button = new JButton("接收信息");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataInputStream dis;
				try {
					dis = new DataInputStream(accept.getInputStream());
					String s = dis.readUTF();
					textArea.append(s);
					textArea.append("\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		button.setBounds(408, 170, 123, 29);
		contentPane.add(button);

		JLabel label = new JLabel("管理员聊天端");
		label.setFont(new Font("楷体", Font.PLAIN, 30));
		label.setBounds(275, 31, 208, 39);
		contentPane.add(label);
	}
}
