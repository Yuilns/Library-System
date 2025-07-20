package org.example;

import com.yuilns.mapper.ReaderMapper;
import com.yuilns.pojo.Reader1;
import org.apache.ibatis.session.SqlSession;
import org.example.util.MyBatisUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class detail2 extends JFrame {
	private JPanel contentPane;
	JTextField uname;
	JTextField uage;
	private JLabel lblName;
	private JLabel lblTel;
	JTextField usex;
	JTextField utel;
	private JButton button_1;
	JLabel uid;
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
	public detail2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("\u59D3\u540D\uFF1A");
		lblId.setFont(new Font("黑体", Font.PLAIN, 16));
		lblId.setBounds(130, 34, 81, 46);
		contentPane.add(lblId);
		uname = new JTextField();
		uname.setBounds(196, 44, 102, 29);
		contentPane.add(uname);
		uname.setColumns(10);
		uage = new JTextField();
		uage.setColumns(10);
		uage.setBounds(196, 92, 102, 29);
		contentPane.add(uage);
		lblName = new JLabel("\u5E74\u9F84\uFF1A");
		lblName.setFont(new Font("黑体", Font.PLAIN, 16));
		lblName.setBounds(130, 88, 63, 37);
		contentPane.add(lblName);
		usex = new JTextField();
		usex.setColumns(10);
		usex.setBounds(417, 44, 102, 29);
		contentPane.add(usex);
		utel = new JTextField();
		utel.setColumns(10);
		utel.setBounds(417, 92, 102, 29);
		contentPane.add(utel);
		uid = new JLabel("");
		uid.setFont(new Font("黑体", Font.PLAIN, 16));
		uid.setBounds(196, 136, 175, 37);
		contentPane.add(uid);
		JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
		label_1.setFont(new Font("黑体", Font.PLAIN, 16));
		label_1.setBounds(350, 39, 62, 37);
		contentPane.add(label_1);
		JLabel lblTel_1 = new JLabel("Tel\uFF1A");
		lblTel_1.setFont(new Font("黑体", Font.PLAIN, 16));
		lblTel_1.setBounds(360, 88, 55, 37);
		contentPane.add(lblTel_1);
		lblTel = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		lblTel.setFont(new Font("黑体", Font.PLAIN, 16));
		lblTel.setBounds(107, 136, 86, 37);
		contentPane.add(lblTel);
		JButton btnNewButton = new JButton("\u66F4\u65B0");
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idCard = uid.getText().trim();
				String name = uname.getText().trim();
				String ageStr = uage.getText().trim();
				String sex = usex.getText().trim();
				String tel = utel.getText().trim();

				if (idCard.isEmpty() || name.isEmpty() || ageStr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "身份证号、姓名和年龄不能为空!");
					return;
				}

				try {
					// 转换年龄为Integer
					Integer age = Integer.parseInt(ageStr);

					try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
						// 先查询读者ID
						ReaderMapper readerMapper = sqlSession.getMapper(ReaderMapper.class);
						Reader1 reader = new Reader1();

						// 设置属性 - 注意使用正确的setter方法
						reader.setIdcard_number(idCard);
						reader.setReader_name(name);
						reader.setAge(age);
						reader.setGender(sex);
						reader.setTel(tel);

						Integer readerId = getReaderIdByIdCard(readerMapper, idCard);
						if (readerId == null) {
							JOptionPane.showMessageDialog(null, "找不到对应的读者记录!");
							return;
						}
						reader.setReader_id(readerId);

						// 执行更新
						int affectedRows = readerMapper.update(reader);

						if (affectedRows > 0) {
							sqlSession.commit();
							JOptionPane.showMessageDialog(null, "修改成功!");
							detail2.this.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "修改失败!");
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "年龄必须是数字!");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "修改失败: " + ex.getMessage(),
							"错误", JOptionPane.ERROR_MESSAGE);
				}
			}

			private Integer getReaderIdByIdCard(ReaderMapper mapper, String idCard) throws SQLException {
				Reader1 reader = mapper.selectByIdCard(idCard);// 根据身份证号查询Reader_id
				return reader != null ? reader.getReader_id() : null;
			}
		});
		btnNewButton.setBounds(117, 200, 86, 37);
		contentPane.add(btnNewButton);
		JButton button = new JButton("\u5220\u9664");
		button.setFont(new Font("黑体", Font.PLAIN, 16));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idCard = uid.getText().trim();
				try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
					// 获取Mapper
					ReaderMapper readerMapper = sqlSession.getMapper(ReaderMapper.class);

					// 执行删除
					int affectedRows = readerMapper.deleteByIdCard(idCard);

					if (affectedRows > 0) {
						sqlSession.commit(); // 提交事务
						JOptionPane.showMessageDialog(null, "删除成功!");
						detail2.this.dispose();

					} else {
						JOptionPane.showMessageDialog(null, "删除失败，未找到匹配的记录!");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"删除失败: " + ex.getMessage(),
							"错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(272, 200, 86, 37);
		contentPane.add(button);
		button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window window = SwingUtilities.getWindowAncestor(button);
				window.setVisible(false);
			}
		});
		button_1.setFont(new Font("黑体", Font.PLAIN, 16));
		button_1.setBounds(417, 200, 86, 37);
		contentPane.add(button_1);
	}
}

