

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
	private ButtonGroup group3;
	private ButtonGroup group2;
	private ButtonGroup group1;
	private ButtonGroup group4;
	private String word;
	private String java;
	private String excel;
	private String ppt;
	
	public level(String title, String ID) {
		this.ID = ID;
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(320, 320);
		setLayout(new BorderLayout());
		
		String sql3 ="INSERT INTO result(id,word,java,excel,ppt) VALUES ('"+ID+"',0,0,0,0)";
		ChangeInfo(sql3);
		
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
			JLabel lbllevel = new JLabel("??????  ??????  ??????  ?????????");
			lbllevel.setBounds(0, 30, 300, 20);
			panelLevel.add(lbllevel);
			
			//word
			group1 = new ButtonGroup();
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
			group2 = new ButtonGroup();
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
			group3 = new ButtonGroup();
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
			group4 = new ButtonGroup();
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
		btn = new JButton("??????");
		btn.addActionListener(this);
		panelButton.add(btn);
		add(panelButton,BorderLayout.SOUTH);
		
		radioEnable();
		
		setVisible(true);
	}
	public static void main(String[] args) {
		DB.init();
		level sc = new level("????????????","mobile010");
	}
	
	public void radioEnable() {
		String sql = "Select * from study where id='"+ID+"'";
		ResultSet rs;
		try {
			rs = DB.getResultSet(sql);
			if (rs.next()) {
				int word= rs.getInt("word");
				int java= rs.getInt("java");
				int excel= rs.getInt("excel");
				int ppt= rs.getInt("ppt");
				
				if (word ==1) {
					for (int i = 0; i < radio.length; i++) {
						radio[i].setEnabled(true);
					}
				}
				if (java ==1) {
					for (int i = 0; i < radio2.length; i++) {
						radio2[i].setEnabled(true);
					}
				}
				if (excel ==1) {
					for (int i = 0; i < radio3.length; i++) {
						radio3[i].setEnabled(true);
					}
				}
				if (ppt ==1) {
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
	
	//?????? ??????/??????
		public void ChangeInfo(String sql) {
			try {
				DB.executeQuery(sql);
			} catch (Exception e) {
				System.out.println("DB??????");
				e.printStackTrace();
			}
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn) {
				int j=0;
				for (int i = 0; i < radio.length; i++) {
					if (radio[i].isSelected()) {
						switch (i) {
						case 0: 
							word = "??????";
							break;
						case 1: 
							word = "??????";
							break;
						case 2: 
							word = "??????";
							break;
						case 3: 
							word = "?????????";
							break;
						default:
							word = "??????";
							break;
						}
						j+=1;
					}else if(j!=1){
						word = "????????????";						
					}
				}
				j=0;
				for (int i = 0; i < radio2.length; i++) {
					if (radio2[i].isSelected()) {
						switch (i) {
						case 0: 
							java = "??????";
							break;
						case 1: 
							java = "??????";
							break;
						case 2: 
							java = "??????";
							break;
						case 3: 
							java = "?????????";
							break;
						default:
							java = "??????";
							break;
						}
						j+=1;
					}else if(j!=1){
						java = "????????????";						
					}
				}
				j=0;
				for (int i = 0; i < radio3.length; i++) {
					if (radio3[i].isSelected()) {
						switch (i) {
						case 0: 
							excel = "??????";
							break;
						case 1: 
							excel = "??????";
							break;
						case 2: 
							excel = "??????";
							break;
						case 3: 
							excel = "?????????";
							break;
						default:
							excel = "??????";
							break;
						}
						j+=1;
					}else if(j!=1){
						excel = "????????????";						
					}
				}
				j=0;
				for (int i = 0; i < radio4.length; i++) {
					if (radio4[i].isSelected()) {
						switch (i) {
						case 0: 
							ppt = "??????";
							break;
						case 1: 
							ppt = "??????";
							break;
						case 2: 
							ppt = "??????";
							break;
						case 3: 
							ppt = "?????????";
							break;
						default:
							ppt = "??????";
							break;
						}
						j +=1;
					}else if(j!=1) {
						ppt = "????????????";						
					}
				}
				String sql2 ="INSERT INTO slimit(id,word,java,excel,ppt) VALUES ('"+ID+"','"+word+"','"+java+"','"+excel+"','"+ppt+"')";
				ChangeInfo(sql2);
				System.out.println("?????????");
				JOptionPane.showMessageDialog(btn, "????????? ?????????????????????.","?????????",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		
	}
	
