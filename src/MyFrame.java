import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
	
	JLabel lblTitle; // 타이틀
	JLabel PName; // 프로세스이름
	JTextField pinput; // 프로세스 입력창
	JButton btnSave; // 저장 버튼
	String getpro;
	int pnum;
	
	public MyFrame() { // 실행
		init();
		setDisplay();
		showFrame();
	}
	
	public void init() {
		lblTitle = new JLabel("OS TERM PORJECT");
		PName = new JLabel("프로세스 수");
		pinput = new JTextField(10);
		btnSave = new JButton("확인");
	}
	
	public void setDisplay() {
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblTitle);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //확인버튼 누르는거
            	getpro=pinput.getText().trim();
            	pnum=Integer.parseInt(getpro); // process 갯수
                new Second(pnum); //프로레 갯수
                dispose();
            }
        });
		
		pnlSouth.setBorder(new TitledBorder(""));
		
		JPanel c = new JPanel(new GridLayout(1,1));
		c.add(PName);
		c.add(pinput);
	
		add(pnlNorth, BorderLayout.NORTH);
		add(c, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
	}

	public void showFrame() {
		setTitle("OS PROJECT");
		setLocationRelativeTo(null);
		setResizable(false); // 창을 고정
		setSize(400,200); //프레임 크기
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
