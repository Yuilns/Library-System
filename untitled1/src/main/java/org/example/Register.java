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
import java.awt.event.ItemEvent;

public class Register extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField age;
	private JTextField uid;
	private JTextField phone;
	private JTextField area;
	private JPasswordField password;
	private JPasswordField password_confirm;
	private final ButtonGroup buttongroup = new ButtonGroup();
	private String sex;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel label = new JLabel("\u7BA1\u7406\u5458\u6CE8\u518C");
		label.setFont(new Font("黑体", Font.PLAIN, 27));
		label.setBounds(162, 36, 180, 45);
		contentPane.add(label);
		JLabel label_1 = new JLabel("\u59D3\u540D\uFF1A");
		label_1.setFont(new Font("黑体", Font.PLAIN, 16));
		label_1.setBounds(107, 110, 67, 30);
		contentPane.add(label_1);
		JLabel label_2 = new JLabel("\u5E74\u9F84\uFF1A");
		label_2.setFont(new Font("黑体", Font.PLAIN, 16));
		label_2.setBounds(107, 152, 67, 30);
		contentPane.add(label_2);
		JLabel label_3 = new JLabel("\u6027\u522B\uFF1A");
		label_3.setFont(new Font("黑体", Font.PLAIN, 16));
		label_3.setBounds(107, 192, 67, 30);
		contentPane.add(label_3);
		JLabel label_4 = new JLabel("\u7535\u8BDD\uFF1A");
		label_4.setFont(new Font("黑体", Font.PLAIN, 16));
		label_4.setBounds(107, 232, 67, 30);
		contentPane.add(label_4);
		JLabel label_5 = new JLabel("\u7BA1\u7406\u533A\u57DF\uFF1A");
		label_5.setFont(new Font("黑体", Font.PLAIN, 16));
		label_5.setBounds(107, 272, 80, 30);
		contentPane.add(label_5);
		JLabel label_6 = new JLabel("\u8D26\u53F7\uFF1A");
		label_6.setFont(new Font("黑体", Font.PLAIN, 16));
		label_6.setBounds(107, 312, 67, 30);
		contentPane.add(label_6);
		JLabel label_7 = new JLabel("\u5BC6\u7801\uFF1A");
		label_7.setFont(new Font("黑体", Font.PLAIN, 16));
		label_7.setBounds(107, 353, 67, 30);
		contentPane.add(label_7);
		JLabel label_8 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_8.setFont(new Font("黑体", Font.PLAIN, 16));
		label_8.setBounds(107, 393, 92, 30);
		contentPane.add(label_8);
		name = new JTextField();
		name.setBounds(170, 114, 192, 24);
		contentPane.add(name);
		name.setColumns(10);
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(170, 154, 192, 24);
		contentPane.add(age);
		uid = new JTextField();
		uid.setColumns(10);
		uid.setBounds(170, 317, 192, 24);
		contentPane.add(uid);
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(170, 234, 192, 24);
		contentPane.add(phone);
		area = new JTextField();
		area.setColumns(10);
		area.setBounds(197, 276, 165, 24);
		contentPane.add(area);
		password = new JPasswordField();
		password.setBounds(170, 358, 192, 24);
		contentPane.add(password);
		password_confirm = new JPasswordField();
		password_confirm.setBounds(197, 399, 165, 24);
		contentPane.add(password_confirm);
		JRadioButton male = new JRadioButton("\u7537");
		buttongroup.add(male);
		// 默认选择男性
		male.setSelected(true);
		sex = "男";
		male.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				sex = "男";
			}
		});
		JRadioButton female = new JRadioButton("\u5973");
		buttongroup.add(female);
		female.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				sex = "女";
			}
		});
		male.setFont(new Font("黑体", Font.PLAIN, 16));
		male.setBounds(197, 194, 59, 23);
		female.setFont(new Font("黑体", Font.PLAIN, 16));
		female.setBounds(281, 194, 59, 23);
		contentPane.add(male);
		contentPane.add(female);

		JRadioButton root = new JRadioButton("");
		root.setBounds(302, 443, 31, 23);
		contentPane.add(root);

		JButton button = new JButton("注册 ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name1 = name.getText().trim();
				String ageStr = age.getText().trim();
				String sex1 = sex;  // 假设sex是从其他地方获取的
				String phone1 = phone.getText().trim();
				String area1 = area.getText().trim();
				String username = uid.getText().trim();
				String password1 = new String(password.getPassword()).trim();
				String password2 = new String(password_confirm.getPassword()).trim();
				boolean isAdmin = root.isSelected();

				if (name1.isEmpty() || ageStr.isEmpty() || sex1.isEmpty() ||
						phone1.isEmpty() || area1.isEmpty() || username.isEmpty() ||
						password1.isEmpty() || password2.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请补全信息");
					return;
				}
				if(!password1.equals(password2)) {
					JOptionPane.showMessageDialog(null, "两次输入的密码不一致");
					password.setText("");
					password_confirm.setText("");
					password.requestFocus();
					return;
				}

				try {
					// 3. 转换年龄为Integer
					Integer age1 = Integer.parseInt(ageStr);
					Integer rootFlag = isAdmin ? 1 : 0;

					try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
						ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);

						Manager existingManager = managerMapper.selectById(username);
						if (existingManager != null) {
							JOptionPane.showMessageDialog(null, "用户已存在！");
							return;
						}

						//创建对象
						Manager newManager = new Manager();
						newManager.setManager_name(name1);
						newManager.setArea(area1);
						newManager.setAge(age1);
						newManager.setGender(sex1);
						newManager.setTel(phone1);
						newManager.setUsername(username);
						newManager.setPassword(password1);
						newManager.setRoot(rootFlag);

						int affectedRows = managerMapper.insertManager(newManager);

						if (affectedRows > 0) {
							sqlSession.commit();
							JOptionPane.showMessageDialog(null, "注册成功！");

							JFrame registerFrame = (JFrame) SwingUtilities.getWindowAncestor(button);
							registerFrame.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "注册失败，请重试");
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "年龄必须是数字！");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"数据库操作出错: " + ex.getMessage(),
							"错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JLabel label_9 = new JLabel("\u662F\u5426\u62E5\u6709\u6700\u9AD8\u7BA1\u7406\u6743\u9650\uFF1A");
		label_9.setFont(new Font("黑体", Font.PLAIN, 16));
		label_9.setBounds(107, 438, 180, 30);
		contentPane.add(label_9);

		button.setFont(new Font("黑体", Font.PLAIN, 16));
		button.setBounds(119, 492, 80, 30);
		contentPane.add(button);

		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame registerFrame = (JFrame) SwingUtilities.getWindowAncestor(button);
				registerFrame.setVisible(false);
			}
		});
		button_1.setFont(new Font("黑体", Font.PLAIN, 16));
		button_1.setBounds(270, 492, 80, 30);
		contentPane.add(button_1);
	}
}

