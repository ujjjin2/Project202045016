

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Inquire extends JFrame{
	private String[] title = {"ID", "이름","학과","학년"};
	private String[][] datas = new String[0][5];
	private DefaultTableModel model = new DefaultTableModel(datas,title);
	private JTable table = new JTable(model);
	private JScrollPane ScrollPane;
	private JPanel panel;
	String type;
	
	public Inquire(String title, String type) {
		this.type = type;
		System.out.println(type +"-수강신청자 조회");
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 200);
		setSize(500, 300);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500,40));
		JLabel lbltitle = new JLabel(type+"신청자 조회");
		lbltitle.setBounds(50, 10, 150, 20);
		panel.add(lbltitle);
		
		add(panel,BorderLayout.NORTH);
		table();
		try {
			showtable(this);
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
		ScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollPane.setPreferredSize(new Dimension(450,200));
		listPanel.add(ScrollPane);
		add(listPanel, BorderLayout.CENTER);
	}
	public DefaultTableModel getModel() {
		return model;
	}
	
//	public static void main(String[] args) {
//		Inquire iq = new Inquire("수강신청자 조회");
//	}
	
	public void showtable(Inquire inquire) throws SQLException{
		
		ResultSet rs,rs1;
		try {
			String sql = "SELECT id FROM study WHERE "+type+"='1'";
			rs = DB.getResultSet(sql);
			while(rs.next()) {
			String id = rs.getString("id");
			
			String sql2 = "SELECT * FROM student WHERE studentID ='"+id+"'";
			rs1 = DB.getResultSet(sql2);
//			inquire.getModel().setNumRows(0);
			while(rs1.next()) {
				String[] imsi = {rs1.getString("studentID"),rs1.getString("name"),rs1.getString("type"),rs1.getString("sin")};
				inquire.getModel().addRow(imsi);
				}	
			}
		} catch (Exception e) {
			System.out.println("테이블 생성 실패");
			e.printStackTrace();
		}
	}
}
