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
    	
		JLabel lblTitle; // 타이틀
		JLabel[] info = new JLabel[5]; // 상단이름적는 칸
		JLabel[] Pnum = new JLabel[x]; // 프로세스이름
		JTextField[][] arr = new JTextField[x][4]; // 프로세스이름
		JButton btnSave; // 저장 버튼
		String[] str= new String[x]; //프로세스 이름 저장
		String[][] temp = new String[x][3]; //입력 값 저장할 임시 보관함
		int[][] num = new int[x][3]; //입력 값 저장
		JLabel timena; //시간할당량
		JTextField timein; //시간할당량 적는칸
		
		//////////////////////////////////////////////////////////////변수 설정
		lblTitle = new JLabel("프로세스 입력창");
		info[0]=new JLabel("   ");
		info[1]=new JLabel("        PID");
		info[2]=new JLabel("   도착시간");
		info[3]=new JLabel(" 서비스 시간");
		info[4]=new JLabel("  우선순위");
		for (int i=0; i<Pnum.length;i++) {
			Pnum[i]=new JLabel("                  "+i);
		}
		
		for (int i=0; i<x;i++) {
			for(int j=0; j<4; j++) {
				arr[i][j] = new JTextField(5);
			}
		}
		timena = new JLabel("시간 할당량:");
		timein = new JTextField(3);
		btnSave = new JButton("확인");
		
		////////////////////////////////////////////////////////////// 화면창 제작
		JPanel pnlNorth = new JPanel();
		pnlNorth.add(lblTitle);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(timena);
		pnlSouth.add(timein);
		pnlSouth.add(btnSave); //확인버튼
		btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //확인버튼 누르는거
            	for(int i=0;i<x;i++) {
            		str[i]=arr[i][0].getText().trim(); //최종 저장된 프로세스 이름
            		for(int j=0; j<3;j++) {
            			temp[i][j]=arr[i][j+1].getText().trim();
            			num[i][j]=Integer.parseInt(temp[i][j]); //최종 저장된 입력값
            		}
            	}
            	String gettime=timein.getText().trim();
              	int timenumber=Integer.parseInt(gettime); // 시간할당 량
                new third(x,timenumber,str.clone(),num.clone()); //프로세스 갯수, 시간할당량, 배열입력한거 넘겨줌
                //dispose(); //창닫기
            }
        });
		pnlSouth.setBorder(new TitledBorder(""));
		
		
		JPanel c = new JPanel(new GridLayout(x+1,2,5,5)); //화면 레이아웃
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
		
		setTitle("프로세스 입력창");
		setLocationRelativeTo(null);
		setResizable(false); // 창을 고정
		setSize(500,700); //프레임 크기
		setVisible(true);
    }

}
