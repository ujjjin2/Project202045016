

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class level extends JFrame implements ActionListener{
	String ID;
	JRadioButton radio[] = new JRadioButton[4];
	JRadioButton radio2[] = new JRadioButton[4];
	JRadioButton radio3[] = new JRadioButton[4];
	JRadioButton radio4[] = new JRadioButton[4];
	private JButton btn;
	
	public level(String title, String ID) {
		this.ID = ID;
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(320, 320);
		setLayout(new BorderLayout());
		
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new BorderLayout());
		
			JPanel panelWest = new JPanel();
			panelWest.setLayout(null);
			panelWest.setPreferredSize(new Dimension(130,300));
				JLabel lblword = new JLabel("Word");
				lblword.setBounds(20, 50, 100, 20);
				panelWest.add(lblword);
				
				JLabel lbljava = new JLabel("Java");
				lbljava.setBounds(20, 100, 100, 20);
				panelWest.add(lbljava);
				
				JLabel lblexcel = new JLabel("Excel");
				lblexcel.setBounds(20, 150, 100, 20);
				panelWest.add(lblexcel);
				
				JLabel lblppt = new JLabel("PowerPoint");
				lblppt.setBounds(20, 200, 120, 20);
				panelWest.add(lblppt);
			
			panelCenter.add(panelWest,BorderLayout.WEST);
			
			JPanel panelLevel = new JPanel();
			panelLevel.setLayout(null);
			panelLevel.setPreferredSize(new Dimension(130,800));
			JLabel lbllevel = new JLabel("초급  중급  고급  전문가");
			lbllevel.setBounds(0, 30, 300, 20);
			panelLevel.add(lbllevel);
			
			//word
			ButtonGroup group1 = new ButtonGroup();
			for (int i = 0; i < radio.length; i++) {
					radio[i] = new JRadioButton();
					group1.add(radio[i]);
					panelLevel.add(radio[i]);
					radio[i].setSelected(false);
					radio[i].setEnabled(false);
			}
			radio[0].setBounds(5, 52, 20, 20);
			radio[1].setBounds(35, 52, 20, 20);
			radio[2].setBounds(65, 52, 20, 20);
			radio[3].setBounds(100, 52, 20, 20);
			
			
			//java
			ButtonGroup group2 = new ButtonGroup();
			for (int i = 0; i < radio2.length; i++) {
					radio2[i] = new JRadioButton();
					group2.add(radio2[i]);
					panelLevel.add(radio2[i]);
					radio2[i].setSelected(false);
					radio2[i].setEnabled(false);
			}
			radio2[0].setBounds(5, 100, 20, 20);
			radio2[1].setBounds(35, 100, 20, 20);
			radio2[2].setBounds(65, 100, 20, 20);
			radio2[3].setBounds(100, 100, 20, 20);

			//Excel
			ButtonGroup group3 = new ButtonGroup();
			for (int i = 0; i < radio3.length; i++) {
					radio3[i] = new JRadioButton();
					group3.add(radio3[i]);
					panelLevel.add(radio3[i]);
					radio3[i].setSelected(false);
					radio3[i].setEnabled(false);
			}			
			
			radio3[0].setBounds(5, 150, 20, 20);
			radio3[1].setBounds(35, 150, 20, 20);
			radio3[2].setBounds(65, 150, 20, 20);
			radio3[3].setBounds(100, 150, 20, 20);
			
			//ppt
			ButtonGroup group4 = new ButtonGroup();
			for (int i = 0; i < radio4.length; i++) {
				radio4[i] = new JRadioButton();
				group4.add(radio4[i]);
				panelLevel.add(radio4[i]);
				radio4[i].setSelected(false);
				radio4[i].setEnabled(false);
			}			
			
			radio4[0].setBounds(5, 200, 20, 20);
			radio4[1].setBounds(35, 200, 20, 20);
			radio4[2].setBounds(65, 200, 20, 20);
			radio4[3].setBounds(100, 200, 20, 20);
			
			
			
			panelCenter.add(panelLevel,BorderLayout.CENTER);
		add(panelCenter,BorderLayout.CENTER);
		
		
		JPanel panelButton = new JPanel();
		btn = new JButton("입력");
		btn.addActionListener(this);
		panelButton.add(btn);
		add(panelButton,BorderLayout.SOUTH);
		
		radioEnable();
		
		setVisible(true);
	}
	public static void main(String[] args) {
		DB.init();
		level sc = new level("수강수준","naver135");
	}
	
	public void radioEnable() {
		String sql = "Select * from study where id='"+ID+"'";
		ResultSet rs;
		try {
			rs = DB.getResultSet(sql);
			while (rs.next()) {
				int word= rs.getInt("word");
				int java= rs.getInt("java");
				int excel= rs.getInt("excel");
				int ppt= rs.getInt("ppt");
				
				if (word ==1) {
					for (int i = 0; i < radio.length; i++) {
						radio[i].setEnabled(true);
					}
				}else if (java ==1) {
					for (int i = 0; i < radio2.length; i++) {
						radio2[i].setEnabled(true);
					}
				}else if (excel ==1) {
					for (int i = 0; i < radio3.length; i++) {
						radio3[i].setEnabled(true);
					}
				}else if (ppt ==1) {
					for (int i = 0; i < radio4.length; i++) {
						radio4[i].setEnabled(true);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn) {
			String sql="Insert Into slimit(id,word,java,excel,ppt) values ('"+ID+"',?,?,?,?)";
			ChangeInfo(sql);
			JOptionPane.showMessageDialog(btn, "과목이 선택되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}