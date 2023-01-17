

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class score extends JFrame implements ActionListener{
	
	private String[] title = {"과목", "점수", "강사명"};
	private String[][] datas = new String[0][3];
	private DefaultTableModel model = new DefaultTableModel(datas,title);
	private JTable table = new JTable(model);
	private JScrollPane ScrollPane;
	String name,ID;
	private String type;
	private JButton btn;
	
	public score(String title, String name, String ID) {
		this.name = name;
		this.ID = ID;
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(350, 220);
		setLayout(new BorderLayout());
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setPreferredSize(new Dimension(500,40));
		JLabel lbltitle = new JLabel(name+"님의 점수");
		lbltitle.setBounds(50, 15, 150, 20);
		panel1.add(lbltitle);
		add(panel1,BorderLayout.NORTH);
		
		table();
		
		JPanel panel2 = new JPanel();
		btn = new JButton("메뉴로");
		btn.addActionListener(this);
		panel2.add(btn);
		add(panel2,BorderLayout.SOUTH);
		
		try {
			showScoreTable(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setVisible(true);
	}
	
	private void table() {
		JPanel listPanel = new JPanel();
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		ScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollPane.setPreferredSize(new Dimension(300,100));
		listPanel.add(ScrollPane);
		add(listPanel, BorderLayout.CENTER);
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
//	public static void main(String[] args) {
//		score sc = new score("수강자 점수");
//	}
	
	public void showScoreTable(score score) throws SQLException {
		String sql = "SELECT type,name FROM user";
		ResultSet rs, rs2;
		try {
			rs = DB.getResultSet(sql);
			while(rs.next()) {
				type = rs.getString("type");	
			
			String sql2 = "SELECT * FROM result WHERE id = '"+ID+"'";
			rs2 = DB.getResultSet(sql2);
			//score.getModel().setNumRows(0);
			while(rs2.next()) {
				String[] imsi = {type ,rs2.getString(type),rs.getString("name")};
				score.getModel().addRow(imsi);
				}	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn) {
			dispose();
			Menu2 menu2 = new Menu2("수강자 메뉴",ID,name);
		}
		
	}
	
}
