import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class total extends JFrame {
	private String[] title = {"ID", "이름", "성별","학과","학년"};
	private String[][] datas = new String[0][5];
	private DefaultTableModel model = new DefaultTableModel(datas,title);
	private JTable table = new JTable(model);
	private JScrollPane ScrollPane;
	private JPanel panel;
	
	public total(String title) {
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 200);
		setSize(500, 300);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500,40));
		JLabel lbltitle = new JLabel("전체 수강자 목록");
		lbltitle.setBounds(50, 10, 150, 20);
		panel.add(lbltitle);
		
		add(panel,BorderLayout.NORTH);
		table();
		
		try {
			showTotal(this);
		} catch (SQLException e) {
			System.out.println("테이블 오류");
			e.printStackTrace();
		}
		setVisible(true);
	}
	private void table() {
		JPanel listPanel = new JPanel();
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		ScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollPane.setPreferredSize(new Dimension(450,200));
		listPanel.add(ScrollPane);
		add(listPanel, BorderLayout.CENTER);
	}
	public DefaultTableModel getModel() {
		return model;
	}

	public static void main(String[] args) {
		DB.init();
		total tt = new total("수강자신청자목록");
	}
	
	public void showTotal(total total) throws SQLException{
		String sql = "SELECT * FROM student";
		ResultSet rs;
		try {
			rs = DB.getResultSet(sql);
			total.getModel().setNumRows(0);
			while(rs.next()) {
				String[] imsi = {rs.getString("studentID"),rs.getString("name"),rs.getString("sex"),rs.getString("type"),rs.getString("sin")};
				total.getModel().addRow(imsi);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
