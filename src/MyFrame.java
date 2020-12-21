import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
	
	JLabel lblTitle; // Ÿ��Ʋ
	JLabel PName; // ���μ����̸�
	JTextField pinput; // ���μ��� �Է�â
	JButton btnSave; // ���� ��ư
	String getpro;
	int pnum;
	
	public MyFrame() { // ����
		init();
		setDisplay();
		showFrame();
	}
	
	public void init() {
		lblTitle = new JLabel("OS TERM PORJECT");
		PName = new JLabel("���μ��� ��");
		pinput = new JTextField(10);
		btnSave = new JButton("Ȯ��");
	}
	
	public void setDisplay() {
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblTitle);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //Ȯ�ι�ư �����°�
            	getpro=pinput.getText().trim();
            	pnum=Integer.parseInt(getpro); // process ����
                new Second(pnum); //���η� ����
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
		setResizable(false); // â�� ����
		setSize(400,200); //������ ũ��
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
