import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Second extends JFrame{
    public Second(int x) {
    	
		JLabel lblTitle; // Ÿ��Ʋ
		JLabel[] info = new JLabel[5]; // ����̸����� ĭ
		JLabel[] Pnum = new JLabel[x]; // ���μ����̸�
		JTextField[][] arr = new JTextField[x][4]; // ���μ����̸�
		JButton btnSave; // ���� ��ư
		String[] str= new String[x]; //���μ��� �̸� ����
		String[][] temp = new String[x][3]; //�Է� �� ������ �ӽ� ������
		int[][] num = new int[x][3]; //�Է� �� ����
		JLabel timena; //�ð��Ҵ緮
		JTextField timein; //�ð��Ҵ緮 ����ĭ
		
		//////////////////////////////////////////////////////////////���� ����
		lblTitle = new JLabel("���μ��� �Է�â");
		info[0]=new JLabel("   ");
		info[1]=new JLabel("        PID");
		info[2]=new JLabel("   �����ð�");
		info[3]=new JLabel(" ���� �ð�");
		info[4]=new JLabel("  �켱����");
		for (int i=0; i<Pnum.length;i++) {
			Pnum[i]=new JLabel("                  "+i);
		}
		
		for (int i=0; i<x;i++) {
			for(int j=0; j<4; j++) {
				arr[i][j] = new JTextField(5);
			}
		}
		timena = new JLabel("�ð� �Ҵ緮:");
		timein = new JTextField(3);
		btnSave = new JButton("Ȯ��");
		
		////////////////////////////////////////////////////////////// ȭ��â ����
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblTitle);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(timena);
		pnlSouth.add(timein);
		pnlSouth.add(btnSave); //Ȯ�ι�ư
		btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //Ȯ�ι�ư �����°�
            	for(int i=0;i<x;i++) {
            		str[i]=arr[i][0].getText().trim(); //���� ����� ���μ��� �̸�
            		for(int j=0; j<3;j++) {
            			temp[i][j]=arr[i][j+1].getText().trim();
            			num[i][j]=Integer.parseInt(temp[i][j]); //���� ����� �Է°�
            		}
            	}
            	String gettime=timein.getText().trim();
              	int timenumber=Integer.parseInt(gettime); // �ð��Ҵ� ��
                new third(x,timenumber,str.clone(),num.clone()); //���μ��� ����, �ð��Ҵ緮, �迭�Է��Ѱ� �Ѱ���
                //dispose(); //â�ݱ�
            }
        });
		pnlSouth.setBorder(new TitledBorder(""));
		
		
		JPanel c = new JPanel(new GridLayout(x+1,2,5,5)); //ȭ�� ���̾ƿ�
		for (int i=0;i<5;i++) {c.add(info[i]);}
		for (int i=0; i<Pnum.length;i++) {
			c.add(Pnum[i]);
			for(int j=0; j<4; j++) {
				c.add(arr[i][j]);
			}
		}
		
		////////////////////////
		add(pnlNorth, BorderLayout.NORTH);
		add(c, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		/////////////////////
		
		setTitle("���μ��� �Է�â");
		setLocationRelativeTo(null);
		setResizable(false); // â�� ����
		setSize(500,700); //������ ũ��
		setVisible(true);
    }

}
