

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Register extends JFrame implements ActionListener{
	String score[] = {"0","1","2","3","4","5"};
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox2;
	String type;
	private JButton btnok;
	private JButton btncancle;
	
	public Register(String title, String type) {
		this.type = type;
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 200);
		setSize(300, 200);
		setLayout(new BorderLayout());
		
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(null);
		panelCenter.setPreferredSize(new Dimension(200, 300));
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(40, 30, 50, 20);
		panelCenter.add(lblId);
		
		comboBox = new JComboBox();
		comboBox.setBounds(80, 30, 150, 20);
		panelCenter.add(comboBox);
		
		insertcombo();
		
		JLabel lblscore = new JLabel("점수");
		lblscore.setBounds(35, 70, 50, 20);
		panelCenter.add(lblscore);
		
		comboBox2 = new JComboBox<String>(score);
		comboBox2.setBounds(80, 70, 150, 20);
		panelCenter.add(comboBox2);

		add(panelCenter, BorderLayout.CENTER);
		
		JPanel panelBottom = new JPanel();
		btnok = new JButton("확인");
		btnok.addActionListener(this);
		panelBottom.add(btnok);
		btncancle = new JButton("취소");
		btncancle.addActionListener(this);
		panelBottom.add(btncancle);
		add(panelBottom, BorderLayout.SOUTH);
		setVisible(true);
	}
	
//	public static void main(String[] args) {
//		Inquire iq = new Inquire("점수등록");
//	}
	
	public void insertcombo() {
		String sql = "SELECT id FROM study WHERE "+type+"='1'";
			ResultSet rs,rs1;
			try {
				rs = DB.getResultSet(sql);
				while (rs.next()) {
					String id = rs.getString("id");
					comboBox.addItem(id);
				}
			} catch (Exception e) {
				System.out.println("점수등록 DB오류");
				e.printStackTrace();
			}
		
	}
	
	//DB업데이트
	public void insertScore(String sql) {
		try {
			DB.executeQuery(sql);
		} catch (Exception e) {
			System.out.println("DB문제");
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btncancle) {
			dispose();
		}else if (obj == btnok) {
			String comboID = comboBox.getSelectedItem().toString();
			String comboScore = comboBox2.getSelectedItem().toString();
			
			String sql = "Update result SET "+type+" = '"+comboScore+"' "
					+ "WHERE id = '"+comboID+"'";
			insertScore(sql);
			JOptionPane.showMessageDialog(btnok, type+" 점수가 입력되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	
	
}
