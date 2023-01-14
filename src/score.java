

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class score extends JFrame{
	
	private String[] title = {"과목", "점수", "강사명"};
	private String[][] datas = new String[0][3];
	private DefaultTableModel model = new DefaultTableModel(datas,title);
	private JTable table = new JTable(model);
	private JScrollPane ScrollPane;
	
	public score(String title) {
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(350, 220);
		setLayout(new BorderLayout());
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setPreferredSize(new Dimension(500,40));
		JLabel lbltitle = new JLabel("000님의 점수");
		lbltitle.setBounds(50, 15, 150, 20);
		panel1.add(lbltitle);
		add(panel1,BorderLayout.NORTH);
		
		table();
		
		JPanel panel2 = new JPanel();
		JButton btn = new JButton("메뉴로");
		panel2.add(btn);
		add(panel2,BorderLayout.SOUTH);
		
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
	public static void main(String[] args) {
		score sc = new score("수강자 점수");
	}
}
