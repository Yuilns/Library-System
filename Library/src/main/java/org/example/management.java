package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class management extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					management frame = new management();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public management() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		label.setFont(new Font("楷体", Font.BOLD, 30));
		label.setBounds(175, 10, 240, 56);
		contentPane.add(label);

		JButton button = new JButton("进入聊天系统");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if(Login.is_admin) {
					try {
						new server().setVisible(true);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}
				else {
					try {
						new client().setVisible(true);
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}
            }
		});
		button.setFont(new Font("黑体", Font.PLAIN, 20));
		button.setBounds(205, 242, 164, 56);
		contentPane.add(button);

		JButton btnn = new JButton("读者信息录入");
		btnn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reader().setVisible(true);
				JFrame manageFrame = (JFrame) SwingUtilities.getWindowAncestor(btnn);
				manageFrame.setVisible(false);
			}
		});
		btnn.setFont(new Font("黑体", Font.PLAIN, 20));
		btnn.setBounds(205, 333, 164, 56);
		contentPane.add(btnn);

		JLabel lbln = new JLabel("\u60A8\u597D\uFF0C");
		lbln.setFont(new Font("黑体", Font.PLAIN, 16));
		lbln.setBounds(65, 76, 75, 50);
		contentPane.add(lbln);

		JLabel label_1 = new JLabel("\u8BF7\u9009\u62E9\u60A8\u8981\u4F7F\u7528\u7684\u529F\u80FD\r\n");
		label_1.setFont(new Font("黑体", Font.PLAIN, 16));
		label_1.setBounds(65, 162, 240, 50);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u6B22\u8FCE\u60A8\u4F7F\u7528\n\u540E\u53F0\u7BA1\u7406\u7CFB\u7EDF");
		label_2.setFont(new Font("黑体", Font.PLAIN, 16));
		label_2.setBounds(65, 118, 240, 50);
		contentPane.add(label_2);

		JLabel name = new JLabel("");
		name.setText(Login.login_name);
		if(Login.is_admin) name.setText("【管理员】"+Login.login_name);
		name.setFont(new Font("黑体", Font.PLAIN, 16));
		name.setBounds(117, 76, 356, 50);
		contentPane.add(name);

		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window window = SwingUtilities.getWindowAncestor(button_1);
				window.setVisible(false);
				new Login().setVisible(true);
			}
		});
		button_1.setFont(new Font("黑体", Font.PLAIN, 16));
		button_1.setBounds(483, 28, 75, 33);
		contentPane.add(button_1);

		JButton root = new JButton("<html>内部人员<br>管理</html>");
		root.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window window = SwingUtilities.getWindowAncestor(button_1);
				window.setVisible(false);
				new manager().setVisible(true);

			}
		});
		root.setFont(new Font("黑体", Font.PLAIN, 18));
		root.setBounds(425, 270, 118, 91);
		contentPane.add(root);
//		System.out.println(Login.is_admin);
		if(!Login.is_admin) root.setVisible(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

