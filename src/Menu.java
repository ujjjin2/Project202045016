
import java.awt.BorderLayout;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener{

	private JButton btn1;
	private JButton btn2;

	public Menu(String title) {
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 200);
		setSize(250, 200);
		setLayout(new BorderLayout());
		
		setPanelCenter();
		
		setVisible(true);
	}
	
	private void setPanelCenter() {
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(2, 1, 10,10));
		panelCenter.setBorder(BorderFactory.createEmptyBorder(30 , 30 , 30 , 30));

		
		btn1 = new JButton("수강자조회");
		btn1.setPreferredSize(new Dimension(40, 60));
		btn1.addActionListener(this);
		
		btn2 = new JButton("점수입력");
		btn2.setPreferredSize(new Dimension(90, 60));
		btn2.addActionListener(this);
		
		panelCenter.add(btn1);
		panelCenter.add(btn2);
		
		add(panelCenter);
		
	}

	public static void main(String[] args) {
		Menu main = new Menu("강사메뉴");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn1) {
			Inquire iq = new Inquire("수강신청자 조회");
		}else if (obj ==btn2) {
			Register rs = new Register("점수등록");
		}
		
	}
}