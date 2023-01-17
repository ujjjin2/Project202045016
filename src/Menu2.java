
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu2 extends JFrame implements ActionListener{

	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	String ID,name, id;
	
	public Menu2(String title, String ID,String name) {
		this.ID = ID;
		this.name = name;
		System.out.println(ID);
		System.out.println(name);
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 200);
		setSize(250, 250);
		setLayout(new BorderLayout());
		
		setPanelCenter();
		
		setVisible(true);
	}
	
	private void setPanelCenter() {
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(3, 1, 10,10));
		panelCenter.setBorder(BorderFactory.createEmptyBorder(30 , 30 , 30 , 30));

		
		btn1 = new JButton("수강신청");
		btn1.setPreferredSize(new Dimension(40, 60));
		btn1.addActionListener(this);
		
		btn2 = new JButton("점수조회");
		btn2.setPreferredSize(new Dimension(90, 60));
		btn2.addActionListener(this);
		
		btn3 = new JButton("수강취소");
		btn3.setPreferredSize(new Dimension(90, 60));
		btn3.addActionListener(this);
		
		panelCenter.add(btn1);
		panelCenter.add(btn2);
		panelCenter.add(btn3);
		
		add(panelCenter);
		
	}

//	public static void main(String[] args) {
//		Menu2 main = new Menu2("수강자메뉴");
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn1) {//수강신청
			Apply_Check(ID);
			if (ID.equals(id)) {
				JOptionPane.showMessageDialog(btn1, "이미 수강신청한 내역이 존재합니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
			}else {
				subject sb = new subject("수강과목",ID);
			}
		}else if (obj ==btn2) {
			Apply_Check(ID);
			if (ID.equals(id)) {
				score sc = new score("수강자 점수조회",name,ID);
				dispose();
			}else {
				JOptionPane.showMessageDialog(btn2, "입력된 정보가 없습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
			
			}
		}else if (obj ==btn3) {//수강취소
			Apply_Check(ID);
			if (ID.equals(id)) {
				String sql = "DELETE FROM study where id='"+ID+"'";
				Deleteinfo(sql);
				String sql2 = "DELETE FROM result WHERE id='"+ID+"'";
				Deleteinfo(sql2);
				JOptionPane.showMessageDialog(btn3, "수강취소가 완료되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
				dispose();
				Menu2 m2 = new Menu2("수강자 메뉴",ID,name);
			}else {
				JOptionPane.showMessageDialog(btn3, "수강신청을 먼저 진행해주세요","메시지",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	
	
	//수강신청 했나 확인 하기
	public void Apply_Check(String ID2) {
		String sql = "SELECT id FROM study WHERE id='"+ID2+"'";
		ResultSet rs;
		try {
			rs = DB.getResultSet(sql);
			while(rs.next()) {
				id = rs.getString("id");
				System.out.println(id);
			}
			
		} catch (Exception e) {
			System.out.println("수강신청 확인 DB실패");
			e.printStackTrace();
		}
	}
	
	//수강취소 할려고 사용하는거
		public void Deleteinfo(String sql) {
			try {
				DB.executeQuery(sql);
			} catch (Exception e) {
				System.out.println("DB문제");
				e.printStackTrace();
			}
		}
}