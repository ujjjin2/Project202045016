
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

public class Main extends JFrame implements ActionListener{

	private JButton btn1;
	private JButton btn2;
	private JButton btn3;

	public Main(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		
		btn1 = new JButton("강사로그인");
		btn1.setPreferredSize(new Dimension(40, 60));
		btn1.addActionListener(this);
		
		btn2 = new JButton("수강자로그인");
		btn2.setPreferredSize(new Dimension(90, 60));
		btn2.addActionListener(this);
		
		btn3 = new JButton("전체수강자조회");
		btn3.setPreferredSize(new Dimension(90, 60));
		btn3.addActionListener(this);
		
		panelCenter.add(btn1);
		panelCenter.add(btn2);
		panelCenter.add(btn3);
		
		add(panelCenter);
		
	}

	public static void main(String[] args) {
		DB.init();
		System.out.println("DB연결됨");
		Main main = new Main("메인");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn1) {
			Login lg = new Login("강사로그인");
		}else if (obj ==btn2) {
			Login2 lg2 = new Login2("수강자로그인");
		}else if (obj ==btn3) {
			total tt = new total("전체수강자조회");
		}
		
	}
}