

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
	public Inquire(String title) {
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 200);
		setSize(300, 200);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(500,40));
		JLabel lbltitle = new JLabel("전체 수강자 목록");
		lbltitle.setBounds(50, 10, 150, 20);
		panel.add(lbltitle);
		
		add(panel,BorderLayout.NORTH);
		table();
		
	
		
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
		Inquire iq = new Inquire("수강신청자 조회");
	}
	
	public void showtable(Inquire inquire,String ID) throws SQLException{
		String sql = "SELECT * FROM user WHERE userID = '"+ID+"'";
		ResultSet rs;
		try {
			rs = DB.getResultSet(sql);
			String TYPE = rs.getString("type");
			String sql2 = "SELECT id FROM study WHERE word='"+TYPE+"' OR java='"+TYPE+"'"
					+ "OR excel='"+TYPE+"' OR ppt='"+TYPE+"'";
			String sql3 = "SELECT * FROM student";
			rs = DB.getResultSet(sql2);
			inquire.getModel().setNumRows(0);
			while(rs.next()) {
				String[] imsi = {rs.getString("studentID"),rs.getString("name"),rs.getString("type"),rs.getString("sin")};
				inquire.getModel().addRow(imsi);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
