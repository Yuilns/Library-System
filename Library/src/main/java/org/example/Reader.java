package org.example;

import com.yuilns.mapper.ReaderMapper;
import com.yuilns.pojo.Reader1;
import org.apache.ibatis.session.SqlSession;
import org.example.util.MyBatisUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("serial")
public class Reader extends JFrame {
	private JPanel contentPane;
	private JTextField name;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JTextField age;
	private JTextField phone;
	private JTextField rid;
	private JRadioButton male;
	private JRadioButton female;
	private String sex;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton button;
	private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reader frame = new Reader();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Reader() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1213, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u8BFB\u8005\u5F55\u5165\u7CFB\u7EDF");
		label.setFont(new Font("楷体", Font.PLAIN, 30));
		label.setBounds(450, 22, 203, 57);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u8BFB\u8005\u59D3\u540D\uFF1A");
		label_1.setFont(new Font("黑体", Font.PLAIN, 20));
		label_1.setBounds(76, 109, 105, 40);
		contentPane.add(label_1);

		name = new JTextField();
		name.setFont(new Font("黑体", Font.PLAIN, 16));
		name.setBounds(187, 112, 145, 33);
		contentPane.add(name);
		name.setColumns(10);

		label_2 = new JLabel("\u6027\u522B\uFF1A");
		label_2.setFont(new Font("黑体", Font.PLAIN, 20));
		label_2.setBounds(116, 166, 75, 40);
		contentPane.add(label_2);

		label_3 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_3.setFont(new Font("黑体", Font.PLAIN, 20));
		label_3.setBounds(76, 280, 105, 40);
		contentPane.add(label_3);

		label_4 = new JLabel("\u5E74\u9F84\uFF1A");
		label_4.setFont(new Font("黑体", Font.PLAIN, 20));
		label_4.setBounds(116, 220, 65, 40);
		contentPane.add(label_4);

		age = new JTextField();
		age.setFont(new Font("黑体", Font.PLAIN, 16));
		age.setColumns(10);
		age.setBounds(187, 223, 145, 33);
		contentPane.add(age);

		phone = new JTextField();
		phone.setFont(new Font("黑体", Font.PLAIN, 16));
		phone.setColumns(10);
		phone.setBounds(187, 285, 145, 33);
		contentPane.add(phone);

		male = new JRadioButton("\u7537");
		// 默认选择男性
		male.setSelected(true);
		sex = "男";
		male.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				sex = "男";
			}
		});
		female = new JRadioButton("\u5973");
		buttonGroup.add(female);
		female.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				sex = "女";
			}
		});

		female.setFont(new Font("黑体", Font.PLAIN, 16));
		female.setBounds(269, 174, 63, 23);
		contentPane.add(female);

		rid = new JTextField();
		rid.setFont(new Font("黑体", Font.PLAIN, 16));
		rid.setColumns(10);
		rid.setBounds(187, 346, 203, 33);
		contentPane.add(rid);

		buttonGroup.add(male);
		male.setFont(new Font("黑体", Font.PLAIN, 16));
		male.setBounds(199, 174, 63, 23);
		contentPane.add(male);

		JButton confirm = new JButton("录入");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name1 = name.getText().trim();
				String ageStr = age.getText().trim();
				String sex1 = sex;
				String phone1 = phone.getText().trim();
				String readerIdentity = rid.getText().trim();

				if (name1.isEmpty() || ageStr.isEmpty() || sex1.isEmpty() ||
						phone1.isEmpty() || readerIdentity.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请补全信息");
					return;
				}

				try {
					Integer age1 = Integer.parseInt(ageStr);

					//MyBatis
					try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
						ReaderMapper readerMapper = sqlSession.getMapper(ReaderMapper.class);

						Reader1 existingReader = readerMapper.selectByIdCard(readerIdentity);
						if (existingReader != null) {
							JOptionPane.showMessageDialog(null, "读者已存在！");
							return;
						}

						//创建对象
						Reader1 newReader = new Reader1();
						newReader.setReader_name(name1);
						newReader.setAge(age1);
						newReader.setGender(sex1);
						newReader.setTel(phone1);
						newReader.setIdcard_number(readerIdentity);

						int affectedRows = readerMapper.add(newReader);

						if (affectedRows > 0) {
							sqlSession.commit(); // 提交事务
							JOptionPane.showMessageDialog(null, "录入成功！");
						} else {
							JOptionPane.showMessageDialog(null, "录入失败，请重试");
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
		confirm.setFont(new Font("黑体", Font.PLAIN, 16));
		confirm.setBounds(76, 419, 120, 40);
		contentPane.add(confirm);

		JLabel label_5 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label_5.setFont(new Font("黑体", Font.PLAIN, 20));
		label_5.setBounds(76, 340, 105, 40);
		contentPane.add(label_5);

		button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new management().setVisible(true);
				JFrame registerFrame = (JFrame) SwingUtilities.getWindowAncestor(button);
				registerFrame.setVisible(false);
			}
		});
		button.setFont(new Font("黑体", Font.PLAIN, 16));
		button.setBounds(981, 22, 156, 40);
		contentPane.add(button);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(450, 109, 719, 382);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("黑体", Font.PLAIN, 16));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow < 0) return; // 未选中行时直接返回

				detail2 frame = new detail2();
				JTextField[] detailFields = {
						frame.uname,
						frame.uage,
						frame.usex,
						frame.utel,
				};

				for (int i = 0; i < detailFields.length; i++) {
					Object value = table.getValueAt(selectedRow, i + 1);
					detailFields[i].setText(value != null ? value.toString() : "");
				}

				Object idCardValue = table.getValueAt(selectedRow, 5);
				frame.uid.setText(idCardValue != null ? idCardValue.toString() : "");

				frame.setVisible(true);
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "\u59D3\u540D", "\u6027\u522B", "\u8054\u7CFB\u7535\u8BDD", "\u5E74\u9F84", "\u8EAB\u4EFD\u8BC1\u53F7"
				}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(61);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(116);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(140);
		JButton button_1 = new JButton("\u67E5\u8BE2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
					// 获取ReaderMapper接口
					ReaderMapper readerMapper = sqlSession.getMapper(ReaderMapper.class);

					// 查询所有读者信息
					List<Reader1> readers = readerMapper.selectAll();

					// 获取表格模型
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0); // 清空现有数据

					// 设置表格列名（根据你的实体类属性）
					String[] columnNames = {"读者ID", "姓名", "年龄", "性别", "联系电话", "身份证号"};
					model.setColumnIdentifiers(columnNames);

					// 填充数据
					for (Reader1 reader : readers) {
						Vector<Object> row = new Vector<>();
						row.add(reader.getReader_id());
						row.add(reader.getReader_name());
						row.add(reader.getAge());
						row.add(reader.getGender());
						row.add(reader.getTel());
						row.add(reader.getIdcard_number());
						model.addRow(row);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("黑体", Font.PLAIN, 16));
		button_1.setBounds(283, 419, 111, 40);
		contentPane.add(button_1);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

