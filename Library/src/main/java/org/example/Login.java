package org.example;

import com.yuilns.mapper.ManagerMapper;
import com.yuilns.pojo.Manager;
import org.apache.ibatis.session.SqlSession;
import org.example.util.MyBatisUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	public static String login_name;
	public static boolean is_admin;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("\u7BA1\u7406\u5458\u767B\u5F55\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 27));
		lblNewLabel.setBounds(140, 31, 208, 63);
		contentPane.add(lblNewLabel);
		JLabel label = new JLabel("\u8D26\u53F7\uFF1A");
		label.setFont(new Font("黑体", Font.PLAIN, 16));
		label.setBounds(101, 152, 75, 35);
		contentPane.add(label);
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("黑体", Font.PLAIN, 16));
		label_1.setBounds(101, 231, 82, 35);
		contentPane.add(label_1);
		textField = new JTextField();
		textField.setBounds(193, 159, 179, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		passwordField = new JPasswordField();
		passwordField.setBounds(193, 238, 179, 28);
		contentPane.add(passwordField);

		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uid=textField.getText().trim();
				String password=new String(passwordField.getPassword()).trim();
				if(uid.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "用户名和密码不能为空", "输入错误", JOptionPane.WARNING_MESSAGE);
					return;
				}
				try (SqlSession sqlSession= MyBatisUtil.getSqlSessionFactory().openSession()){
					ManagerMapper managerMapper= sqlSession.getMapper(ManagerMapper.class);
					List<Manager> managers=managerMapper.selectListById(uid);

					if (managers.isEmpty()) {
						JOptionPane.showMessageDialog(null, "请先注册！", "用户不存在", JOptionPane.WARNING_MESSAGE);
						textField.setText("");
						passwordField.setText("");
						textField.requestFocus();
						return;
					}
					Manager manager = managers.get(0);
					if (manager.getPassword().equals(password)) {
						JOptionPane.showMessageDialog(null, "登录成功", "成功", JOptionPane.INFORMATION_MESSAGE);
						login_name = manager.getManager_name();
						is_admin = "1".equals(manager.getRoot().toString());
						new management().setVisible(true);
						Window window = SwingUtilities.getWindowAncestor(button);
						window.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "密码错误", "登录失败", JOptionPane.ERROR_MESSAGE);
						passwordField.setText("");
						passwordField.requestFocus();
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "数据库错误: " + e1.getMessage(), "系统错误", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		button.setFont(new Font("黑体", Font.PLAIN, 13));
		button.setBounds(116, 331, 82, 29);
		contentPane.add(button);

		JButton button_1 = new JButton("\u6CE8\u518C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register=new Register();
				register.setVisible(true);
			}
		});
		button_1.setFont(new Font("黑体", Font.PLAIN, 13));
		button_1.setBounds(266, 331, 82, 29);
		contentPane.add(button_1);
	}
}
