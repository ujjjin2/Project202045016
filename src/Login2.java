
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login2 extends JFrame implements ActionListener{
	
	private JTextField tfID;
	private JTextField tfPW;
	private JButton btnok;
	private JButton btncancle;
	public Login2(String title) {
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(280, 180);
		setLayout(new BorderLayout());
		
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(null);
		panelCenter.setPreferredSize(new Dimension(200, 300));
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(40, 30, 50, 20);
		panelCenter.add(lblId);
		tfID = new JTextField(15);
		tfID.addActionListener(this);
		tfID.setBounds(80, 30, 150, 20);
		panelCenter.add(tfID);
		
		JLabel lblpw = new JLabel("PW");
		lblpw.setBounds(35, 70, 50, 20);
		panelCenter.add(lblpw);
		tfPW = new JTextField(15);
		tfPW.addActionListener(this);
		tfPW.setBounds(80, 70, 150, 20);
		panelCenter.add(tfPW);

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
	public static void main(String[] args) {
		Login2 lg = new Login2("로그인");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btncancle) {
			dispose();
		}else if (obj == btnok) {
			String ID = tfID.getText();
			String PW = tfPW.getText();
			login(ID,PW);
		}
		
	}
	
	public void login(String ID2,String PW2) {
		String sql = "SELECT studentID,password FROM student WHERE studentID = '" + ID2 + "'";
		try {
			ResultSet rs = DB.getResultSet(sql);
			if (rs.next()) {
				String id = rs.getString("studentID");
				String pw = rs.getString("password");
				
				if (ID2.equals(id) && PW2.equals(pw)) {
					JOptionPane.showMessageDialog(null, "로그인을 완료하였습니다","메세지",JOptionPane.INFORMATION_MESSAGE);
					Menu2 menu2 = new Menu2("수강자 메뉴");
					dispose();
					System.out.println("로그인 성공");
				}else {
					JOptionPane.showMessageDialog(null, "아이디를 다시입력해주세요","메세지",JOptionPane.INFORMATION_MESSAGE);
					tfID.setText("");
					tfPW.setText("");
					tfID.requestFocus(true);
					System.out.println("로그인 입력 실수");
				}
			}
		} catch (Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
	}
}
