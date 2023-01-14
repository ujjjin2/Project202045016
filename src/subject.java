

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class subject extends JFrame implements ActionListener{
	private JCheckBox word;
	private JCheckBox java;
	private JCheckBox excel;
	private JCheckBox ppt;
	private JButton btn;
	public subject(String title) {
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(280, 300);
		setLayout(new BorderLayout());
		
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(null);
		panelCenter.setPreferredSize(new Dimension(300, 300));
		word = new JCheckBox("Word",false);
		word.setBounds(80, 30, 100, 30);
		panelCenter.add(word);
		
		java = new JCheckBox("Java",false);
		java.setBounds(80, 70, 100, 30);
		panelCenter.add(java);
		
		excel = new JCheckBox("Excel",false);
		excel.setBounds(80, 110, 100, 30);
		panelCenter.add(excel);
		
		ppt = new JCheckBox("PowerPoint",false);
		ppt.setBounds(80, 150, 100, 30);
		panelCenter.add(ppt);
		
		btn = new JButton("강좌수준선택");
		btn.addActionListener(this);
		btn.setBounds(70, 190, 120, 30);
		panelCenter.add(btn);
		add(panelCenter);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		subject sc = new subject("수강과목");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn) {
			if (!word.isSelected() && !java.isSelected()
					&& !excel.isSelected() && !ppt.isSelected()) {
				JOptionPane.showMessageDialog(btn, "수강하실 강좌명을 선택해주세요","메시지",JOptionPane.INFORMATION_MESSAGE);
			}else {
				level lv = new level("수강수준");
				System.out.println("수강수준 프레임 뜸");
			}
		}
	}
}
