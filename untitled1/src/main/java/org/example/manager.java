package org.example;

import com.yuilns.mapper.ManagerMapper;
import com.yuilns.pojo.Manager;
import org.apache.ibatis.session.SqlSession;
import org.example.util.MyBatisUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("serial")
public class manager extends JFrame {
	private JPanel contentPane;
	private JTable table;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manager frame = new manager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public manager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 918, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel label = new JLabel("\u5185\u90E8\u4EBA\u5458\u7BA1\u7406");
		label.setFont(new Font("楷体", Font.PLAIN, 30));
		label.setBounds(346, 20, 193, 57);
		contentPane.add(label);
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (SqlSession sqlSession= MyBatisUtil.getSqlSession()){
					ManagerMapper managerMapper= sqlSession.getMapper(ManagerMapper.class);
					List<Manager> managers= managerMapper.selectAll();

					DefaultTableModel model= (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					String[] columnNames = {
							"ID",
							"管理员姓名",
							"管理区域",
							"年龄",
							"性别",
							"联系电话",
							"权限"  // 对应数据库中的第9列(root)
					};
					model.setColumnIdentifiers(columnNames);

					for (Manager manager : managers) {
						Vector<Object> row = new Vector<>();
						row.add(manager.getUser_id());
						row.add(manager.getManager_name());
						row.add(manager.getArea());
						row.add(manager.getAge());
						row.add(manager.getGender());
						row.add(manager.getTel());
						row.add(manager.getRoot() == 1 ? "管理员" : "普通用户");
						model.addRow(row);
					}
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("黑体", Font.PLAIN, 20));
		button.setBounds(44, 426, 94, 47);
		contentPane.add(button);
		JButton button_2 = new JButton("\u8FD4\u56DE");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new management().setVisible(true);
				JFrame registerFrame = (JFrame) SwingUtilities.getWindowAncestor(button);
				registerFrame.setVisible(false);
			}
		});
		button_2.setFont(new Font("黑体", Font.PLAIN, 16));
		button_2.setBounds(733, 33, 131, 40);
		contentPane.add(button_2);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 96, 713, 301);
		contentPane.add(scrollPane);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow < 0) return; // 没有选中行

				try {
					detail frame = new detail();
					JTextField[] detailFields = {
							frame.uid,    // ID (可能是Integer)
							frame.uname,  // 姓名 (String)
							frame.uarea,   // 区域 (String)
							frame.uage,    // 年龄 (可能是Integer)
							frame.usex,    // 性别 (String)
							frame.utel     // 电话 (String)
					};

					for (int i = 0; i < Math.min(table.getColumnCount(), detailFields.length); i++) {
						Object value = table.getValueAt(selectedRow, i);
						detailFields[i].setText(value != null ? value.toString() : "");
					}

					// 安全处理权限复选框
					Object permissionValue = table.getValueAt(selectedRow, 6); // 权限列
					if (permissionValue != null) {
						frame.root.setSelected(permissionValue.toString().equals("管理员"));
					}

					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"加载数据出错: " + ex.getMessage(),
							"错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"User_id", "\u7BA1\u7406\u5458\u59D3\u540D", "\u7BA1\u7406\u533A\u57DF", "\u5E74\u9F84", "\u6027\u522B", "\u8054\u7CFB\u7535\u8BDD", "\u6743\u9650"
				}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(67);
		table.getColumnModel().getColumn(1).setPreferredWidth(91);
		table.getColumnModel().getColumn(2).setPreferredWidth(84);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(99);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		scrollPane.setViewportView(table);
	}
}

