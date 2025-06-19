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

@SuppressWarnings("serial")
public class detail extends JFrame {
	private JPanel contentPane;
	JTextField uid;
	JTextField uname;
	private JLabel lblName;
	JTextField uarea;
	private JLabel lblTel;
	JTextField uage;
	JTextField usex;
	JTextField utel;
	private JButton button_1;
	protected JRadioButton root;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					detail frame = new detail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public detail() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblId = new JLabel("User_id\uFF1A");
		lblId.setFont(new Font("黑体", Font.PLAIN, 16));
		lblId.setBounds(141, 35, 81, 46);
		contentPane.add(lblId);
		uid = new JTextField();
		uid.setBounds(226, 45, 102, 29);
		contentPane.add(uid);
		uid.setColumns(10);
		uname = new JTextField();
		uname.setColumns(10);
		uname.setBounds(226, 93, 102, 29);
		contentPane.add(uname);
		lblName = new JLabel("\u59D3\u540D\uFF1A");
		lblName.setFont(new Font("黑体", Font.PLAIN, 16));
		lblName.setBounds(160, 91, 63, 37);
		contentPane.add(lblName);

		uarea = new JTextField();
		uarea.setColumns(10);
		uarea.setBounds(226, 140, 102, 29);
		contentPane.add(uarea);
		uage = new JTextField();
		uage.setColumns(10);
		uage.setBounds(226, 191, 102, 29);
		contentPane.add(uage);
		usex = new JTextField();
		usex.setColumns(10);
		usex.setBounds(447, 45, 102, 29);
		contentPane.add(usex);
		utel = new JTextField();
		utel.setColumns(10);
		utel.setBounds(447, 97, 102, 29);
		contentPane.add(utel);
		root = new JRadioButton("");
		root.setBounds(473, 160, 43, 23);
		contentPane.add(root);
		JLabel label = new JLabel("\u5E74\u9F84\uFF1A");
		label.setFont(new Font("黑体", Font.PLAIN, 16));
		label.setBounds(160, 186, 63, 37);
		contentPane.add(label);
		JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
		label_1.setFont(new Font("黑体", Font.PLAIN, 16));
		label_1.setBounds(380, 40, 62, 37);
		contentPane.add(label_1);
		JLabel lblTel_1 = new JLabel("Tel\uFF1A");
		lblTel_1.setFont(new Font("黑体", Font.PLAIN, 16));
		lblTel_1.setBounds(380, 92, 55, 37);
		contentPane.add(lblTel_1);
		JLabel label_3 = new JLabel("\u6700\u9AD8\u6743\u9650\uFF1A");
		label_3.setFont(new Font("黑体", Font.PLAIN, 16));
		label_3.setBounds(380, 152, 80, 37);
		contentPane.add(label_3);
		lblTel = new JLabel("\u7BA1\u7406\u533A\u57DF\uFF1A");
		lblTel.setFont(new Font("黑体", Font.PLAIN, 16));
		lblTel.setBounds(137, 137, 86, 37);
		contentPane.add(lblTel);
		JButton btnNewButton = new JButton("更新");
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer id = Integer.valueOf(uid.getText().trim());
					String name = uname.getText().trim();
					String ageStr = uage.getText().trim();
					String sex = usex.getText().trim();
					String tel = utel.getText().trim();
					String area = uarea.getText().trim();
					int rootFlag = root.isSelected() ? 1 : 0;

					if (name.isEmpty() || ageStr.isEmpty() || sex.isEmpty() ||
							tel.isEmpty() || area.isEmpty()) {
						JOptionPane.showMessageDialog(null, "请补全所有信息！");
						return;
					}

					Integer age = Integer.parseInt(ageStr);

					//MyBatis更新
					try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
						ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);

						//创建对象
						Manager manager = new Manager();
						manager.setUser_id(id);
						manager.setManager_name(name);
						manager.setArea(area);
						manager.setAge(age);
						manager.setGender(sex);
						manager.setTel(tel);
						manager.setRoot(rootFlag);

						int affectedRows = mapper.updateManager(manager);

						if (affectedRows > 0) {
							sqlSession.commit();
							JOptionPane.showMessageDialog(null, "修改成功！");
							detail.this.dispose();

						} else {
							JOptionPane.showMessageDialog(null, "修改失败，未找到对应记录！");
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "年龄必须是数字！");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"修改失败: " + ex.getMessage(),
							"错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(141, 268, 86, 37);
		contentPane.add(btnNewButton);
		JButton button = new JButton("\u5220\u9664");
		button.setFont(new Font("黑体", Font.PLAIN, 16));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer id = Integer.valueOf(uid.getText().trim());

					//MyBatis删除
					try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
						ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);

						int affectedRows = mapper.deleteManagerById(id);

						if (affectedRows > 0) {
							sqlSession.commit(); // 提交事务
							JOptionPane.showMessageDialog(null, "删除成功！");
							detail.this.dispose();

						} else {
							JOptionPane.showMessageDialog(null, "删除失败，未找到对应记录！");
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID必须是数字！");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"删除失败: " + ex.getMessage(),
							"错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(292, 268, 86, 37);
		contentPane.add(button);

		button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window window = SwingUtilities.getWindowAncestor(button);
				window.setVisible(false);
			}
		});
		button_1.setFont(new Font("黑体", Font.PLAIN, 16));
		button_1.setBounds(446, 268, 86, 37);
		contentPane.add(button_1);
	}
}

