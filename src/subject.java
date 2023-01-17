

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class subject extends JFrame implements ActionListener, ItemListener{
	private JCheckBox word;
	private JCheckBox java;
	private JCheckBox excel;
	private JCheckBox ppt;
	private JButton btn;
	String ID;
	public subject(String title,String ID) {
		this.ID = ID;
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(280, 300);
		setLayout(new BorderLayout());
		
		//study테이블에 로그인한 아이디 관련 내용 생성
				String sql = "INSERT INTO study(id,word,java,excel,ppt)"
						+ "VALUES ('"+ID+"',0,0,0,0)";
				ChangeInfo(sql);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(null);
		panelCenter.setPreferredSize(new Dimension(300, 300));
		word = new JCheckBox("Word",false);
		word.addItemListener(this);
		word.setBounds(80, 30, 100, 30);
		panelCenter.add(word);
		
		java = new JCheckBox("Java",false);
		java.addItemListener(this);
		java.setBounds(80, 70, 100, 30);
		panelCenter.add(java);
		
		excel = new JCheckBox("Excel",false);
		excel.addItemListener(this);
		excel.setBounds(80, 110, 100, 30);
		panelCenter.add(excel);
		
		ppt = new JCheckBox("PowerPoint",false);
		ppt.addItemListener(this);
		ppt.setBounds(80, 150, 100, 30);
		panelCenter.add(ppt);
		
		btn = new JButton("강좌수준선택");
		btn.addActionListener(this);
		btn.setBounds(70, 190, 120, 30);
		panelCenter.add(btn);
		add(panelCenter);
		
		
		
		setVisible(true);
	}
//	public static void main(String[] args) {
//		subject sc = new subject("수강과목");
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn) {
			if (!word.isSelected() && !java.isSelected()
					&& !excel.isSelected() && !ppt.isSelected()) {
				JOptionPane.showMessageDialog(btn, "수강하실 강좌명을 선택해주세요","메시지",JOptionPane.INFORMATION_MESSAGE);
			}else {
				level lv = new level("수강수준",ID);
				System.out.println("수강수준 프레임 뜸");
			}
		}
	}
	
	//정보 추가/수정
	public void ChangeInfo(String sql) {
		try {
			DB.executeQuery(sql);
		} catch (Exception e) {
			System.out.println("DB문제");
			e.printStackTrace();
		}
	}
	int selected = 1;
	private String box_word;
	private String box_excel;
	private String box_java;
	private String box_ppt;
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			selected = 1;
		}else {
			selected = -1;
		}
		//word
		if (e.getItem()==word && selected == 1) {
			String sql = "Update study SET word = 1 WHERE id='"+ID+"'";
			ChangeInfo(sql);
			box_word = word.getText();
		}else {
			String sql = "Update study SET word = 0 WHERE id='"+ID+"'";
			ChangeInfo(sql);
			box_word = "";
		}
		//excel
		if (e.getItem()==excel && selected == 1) {
			String sql = "Update study SET excel = 1 WHERE id='"+ID+"'";
			ChangeInfo(sql);
			box_excel = excel.getText();
		}else {
			String sql = "Update study SET excel = 0 WHERE id='"+ID+"'";
			ChangeInfo(sql);
			box_excel = "";
		}
		//java
		if (e.getItem()==java && selected == 1) {
			String sql = "Update study SET java = 1 WHERE id='"+ID+"'";
			ChangeInfo(sql);
			box_java = java.getText();
		}else {
			String sql = "Update study SET java = 0 WHERE id='"+ID+"'";
			ChangeInfo(sql);
			box_java = "";
		}
		//ppt
				if (e.getItem()==ppt && selected == 1) {
					String sql = "Update study SET ppt = 1 WHERE id='"+ID+"'";
					ChangeInfo(sql);
					box_ppt = ppt.getText();
				}else {
					String sql = "Update study SET ppt = 0 WHERE id='"+ID+"'";
					ChangeInfo(sql);
					box_ppt = "";
				}
		
	}
	
}
