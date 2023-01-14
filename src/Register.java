

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Register extends JFrame{
	String score[] = {"0","1","2","3","4","5"};
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox2;
	public Register(String title) {
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
		
		JLabel lblscore = new JLabel("점수");
		lblscore.setBounds(35, 70, 50, 20);
		panelCenter.add(lblscore);
		
		comboBox2 = new JComboBox<String>(score);
		comboBox2.setBounds(80, 70, 150, 20);
		panelCenter.add(comboBox2);

		add(panelCenter, BorderLayout.CENTER);
		
		JPanel panelBottom = new JPanel();
		JButton btnok = new JButton("확인");
		panelBottom.add(btnok);
		JButton btncancle = new JButton("취소");
		panelBottom.add(btncancle);
		add(panelBottom, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Inquire iq = new Inquire("점수등록");
	}
	
	public void insertcombo() {
		String sql = "SELECT ";
		
	}
}
