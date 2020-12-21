import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

class GanttChart  {
   
   JFrame frame;
   GanttChartModel gcmodel;
   JTable table;
   JScrollPane scrollPane;
   
   
   public GanttChart(GanttChartModel _gcmodel)  {
      JFrame frame = new JFrame("Gantt Chart");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      gcmodel = _gcmodel;
      
      JTable table = new JTable(gcmodel.getData(), gcmodel.getColumnNames());
      JScrollPane scrollPane = new JScrollPane(table);
      table.setFillsViewportHeight(true);
      scrollPane.setPreferredSize(new Dimension(1000,500));
      
      TableColumn column = null;
      for (int i = 0; i <= gcmodel.getEndTime(); i++) {
          column = table.getColumnModel().getColumn(i);
          if (i >= 1) {
              column.setPreferredWidth(2); //third column is bigger
          } else {
              column.setPreferredWidth(150);
          }
      }
      
      frame.setContentPane(scrollPane);
      GanttChartRenderer gcRenderer = new GanttChartRenderer(gcmodel);
      table.setDefaultRenderer(Object.class, gcRenderer);
      frame.pack();
      frame.setVisible(true);
   }
}
class cal{
   String name;
   int start_time;
   int ser_time;
   float number;
   
   int Wai;
   int Ans;
   int Ret;
   
   public cal(String name, int start_time, int ser_time, float number) {
      this.name = name;
      this.start_time = start_time;
      this.ser_time = ser_time;
      this.number = number;
      
      Wai = 0;
      Ans = 0;
      Ret = 0;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getStart_time() {
      return start_time;
   }

   public void setStart_time(int start_time) {
      this.start_time = start_time;
   }

   public int getSer_time() {
      return ser_time;
   }

   public void setSer_time(int ser_time) {
      this.ser_time = ser_time;
   }

   public float getNumber() {
      return number;
   }

   public void setNumber(float number) {
      this.number = number;
   }

   public int getWai() {
      return Wai;
   }

   public void setWai(int wai) {
      Wai = wai;
   }

   public int getAns() {
      return Ans;
   }

   public void setAns(int ans) {
      Ans = ans;
   }

   public int getRet() {
      return Ret;
   }

   public void setRet(int ret) {
      Ret = ret;
   }
}

public class third {

   static String str1 = ""; //대기시간, 평균 대기시간
   static String str2 = ""; //응답시간, 평균 응답시간
   static String str3 = ""; //반환시간, 평균 반환시간
   
   public static void Set(cal p1[], String[] str, int[][] num, int x) {

      String name;
      int start_time;
      int ser_time;
      int number;
      
      for (int i=0;i<str.length;i++) {
             name = str[i];
             start_time = num[i][0];
             ser_time = num[i][1];
             number = num[i][2];
             
             p1[i] = new cal(name, start_time, ser_time, number);
         }//모든 프로세스를 받아와서 계산해야 한다.
      String str1 = ""; //대기시간, 평균 대기시간
      String str2 = ""; //응답시간, 평균 응답시간
      String str3 = ""; //반환시간, 평균 반환시간
      
   }
   public static void SWAP(cal a, cal b) {
      int a1 = a.getStart_time();
      String a2 = a.getName();
      float a3 = a.getNumber();
      int a4 = a.getSer_time();
      
      a.setStart_time(b.getStart_time());
      a.setName(b.getName());
      a.setNumber(b.getNumber());
      a.setSer_time(b.getSer_time());
      
      b.setStart_time(a1);
      b.setName(a2);
      b.setNumber(a3);
      b.setSer_time(a4);
   }
   
   public static void Print(cal p1[], int count)
   {
      float Wai = 0;
      float Ans = 0;
      float Ret = 0;

      for (int i = 0; i < count; i++)
      {
         System.out.println((i+1) + "번째 프로세스의 이름 : "+ p1[i].name);
         System.out.println("대기 시간 : " + p1[i].Wai);
         System.out.println("응답 시간 : " + p1[i].Ans);
         System.out.println("반환 시간 : " + p1[i].Ret);
         
         str1 = str1 + p1[i].name + "프로세스의 대기시간 : " + p1[i].Wai + " "; 
         Wai = Wai + p1[i].Wai;
         Ans = Ans + p1[i].Ans;
         Ret = Ret + p1[i].Ret;
         
         p1[i].Wai = 0;
         p1[i].Ans = 0;
         p1[i].Ret = 0;
         
      }
      str1 = str1 + "평균 대기시간 : " + Wai / (float)count;
      
      System.out.println("평균 대기 시간, 응답 시간, 반환 시간 : "+ Wai / (float)count +"  "+ Ans / (float)count +"  "+ Ret / (float)count);
   }
   public static void SP(cal p1[], int count)
   { 
      GanttChartModel gcModel = new GanttChartModel(); //간트 생성
      gcModel.addTask("알고리즘명: 비선점형", 0,0);
	   	int num2 = -1;
	   	int ret = 0;
	   	int end_count = 0;
	   	
	   	int [] ser_time = new int[count];
   		for(int i = 0; i < count; i++)
   		{
   			ser_time[i] = p1[i].ser_time;
   		}
      
      while(true)
	   		//각 반복이 끝난 후 서비스 시간이 0인 프로세슨 제외해야 함 
	   	{
	   		num2 = -1;
	   		for(int i = 0; i < count; i++)	//시작시간이 가장 빠른 프로세스 검색
	   		{
	   			if(p1[i].start_time <= ret && p1[i].ser_time != 0)	//ret보다 작고, 서비스시간이 0이 아닌 것 우선순위가 높은 것
	   			{
	   				if(num2 == -1 || p1[i].number < p1[num2].number)
	   					num2 = i;
	   			}
	   		}

	   		p1[num2].Ans = ret - p1[num2].start_time;
	   		p1[num2].Wai = p1[num2].Ans;
	   		p1[num2].Ret = ret + p1[num2].ser_time;
	   			
	   			
	   			
	   		if(ret == 0)
	   			gcModel.addTask(p1[num2].getName(), p1[num2].start_time + 1, p1[num2].Ret + 1);
	   		else
	   			gcModel.addTask(p1[num2].getName(), p1[num2].Ret - ser_time[num2] + 2, ser_time[num2]);
	   			
	   			
	   		p1[num2].ser_time = 0;
	   		ret = p1[num2].Ret;
	   		
	   		end_count++;
	   		
	   		if(end_count == count)
	   			break;
	   	}
      
      //추가
      for(int i = 0; i < count; i++)
      {
         
         p1[i].Wai = (p1[i].Ret - ser_time[i]) - p1[i].start_time;
         p1[i].Ret = p1[i].Ret - p1[i].start_time;
      }
        
        float Wai = 0;
          float Ans = 0;
          float Ret1 = 0;
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "대기시간 : " + p1[i].Wai;
           gcModel.addTask(str1, 0,0);
           Wai = Wai + p1[i].Wai;
        }
        str1 ="평균 대기시간 : " + Wai / (float)count;
        gcModel.addTask(str1, 0,0);
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "응답시간 : " + p1[i].Ans;
           gcModel.addTask(str1, 0,0);
            Ans = Ans + p1[i].Ans;
        }
        str1 ="평균 응답시간 : " + Ans / (float)count;
        gcModel.addTask(str1, 0,0);
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "반환시간 : " + p1[i].Ret;
           gcModel.addTask(str1, 0,0);
            Ret1 = Ret1 + p1[i].Ret;
        }
        str1 ="평균 반환시간 : " + Ret1 / (float)count;
        gcModel.addTask(str1, 0,0);
        
        
        
        GanttChart gc = new GanttChart(gcModel);
   }
   public static void SP2(cal p1[], int count)
   	{
	   	GanttChartModel gcModel = new GanttChartModel(); //간트 생성
	   	gcModel.addTask("알고리즘명: 선점형", 0,0);
	   
	   	int num1 = -1;
	   	int num2 = -1;
	   	int ret = 0;
	   	int end_count = 0;
	   	
	   	int [] ser_time = new int[count];
   		for(int i = 0; i < count; i++)
   		{
   			ser_time[i] = p1[i].ser_time;
   		}
   		int [] start_time = new int[count];
   		
   		for(int i = 0; i < count; i++)
   		{
   			start_time[i] = p1[i].start_time;
   		}
   		
   		int [] ans_time = new int[count];
   		
   		for(int i = 0; i < count; i++)
   		{
   			ans_time[i] = -1;
   		}
	   	
	   	
	   	while(true)
	   	{
	   		num1 = -1;
	   		num2 = -1;
        
	   		for(int i = 0; i < count; i++)
	   		{
	   			if((p1[i].ser_time != 0 && num2 == -1) ||( p1[i].ser_time != 0 && p1[i].number < p1[num2].number 
	   					&& p1[i].start_time <= ret))
	   			{
	   				num2 = i;
	   			}
	   		}
	   		
	   		for(int i = 0; i < count; i++)	//num2에는 시작시간이 가장 빠른 프로세스가 저장
	   		{
	   			//중간에 종료되어야 하는지 검사 i에 num2의 프로세스가 종료되기 전 우선순위가 더 높은 프로세스가 있을 경우 num1에 저장
	   			if(p1[num2].number > p1[i].number && p1[num2].ser_time + ret > p1[i].start_time && p1[i].ser_time != 0)
	   			{
	   				num1 = i;
	   			}
	   		}
	   		
	   		if(num1 != -1)	//중간에 종료되어야 할 경우 num2의 시작시간 전까지 실행, 서비스 시간 감소
	   		{
	   			if(ans_time[num2] == -1)
	   				ans_time[num2] = ret;
	   			p1[num2].Wai = ret;
	   			p1[num2].Ret = ret + (p1[num1].start_time - ret);
	   			
	   			if(ret == 0)
	   				gcModel.addTask(p1[num2].getName(), 1,  (p1[num1].start_time - ret) + 1);
	   			else
	   				gcModel.addTask(p1[num2].getName(), ret + 2,  (p1[num1].start_time - ret));
	   			
	   			p1[num2].ser_time = p1[num2].ser_time - (p1[num1].start_time - ret);
	   			
	   			p1[num2].start_time = ret;
	   			ret = p1[num2].Ret;
	   			
	   		}
	   		else 
	   		{
	   			if(ans_time[num2] == -1)
	   				ans_time[num2] = ret;
	   			p1[num2].Wai = ret;
	   			p1[num2].Ret = ret + p1[num2].ser_time;
	   			
	   			if(ret == 0)
	   				gcModel.addTask(p1[num2].getName(), 1,  p1[num2].ser_time + 1);
	   			else
	   				gcModel.addTask(p1[num2].getName(), ret + 2,  p1[num2].ser_time);
	   		
	   			p1[num2].ser_time = 0;
	   			
	   			p1[num2].start_time = ret;
	   			ret = p1[num2].Ret;
	   			
	   			end_count++;
	   		}
	   		
	   		if(end_count == count)
	   			break;
	   	}
	   	
	  //추가
        for(int i = 0; i < count; i++)
        {
           p1[i].Ret = p1[i].Ret - start_time[i];
           p1[i].Wai = p1[i].Wai - start_time[i];
           p1[i].Ans = ans_time[i] - start_time[i];
        }
        
        float Wai = 0;
          float Ans = 0;
          float Ret = 0;
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "대기시간 : " + p1[i].Wai;
           gcModel.addTask(str1, 0,0);
           Wai = Wai + p1[i].Wai;
        }
        str1 ="평균 대기시간 : " + Wai / (float)count;
        gcModel.addTask(str1, 0,0);
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "응답시간 : " + p1[i].Ans;
           gcModel.addTask(str1, 0,0);
            Ans = Ans + p1[i].Ans;
        }
        str1 ="평균 응답시간 : " + Ans / (float)count;
        gcModel.addTask(str1, 0,0);
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "반환시간 : " + p1[i].Ret;
           gcModel.addTask(str1, 0,0);
            Ret = Ret + p1[i].Ret;
        }
        str1 ="평균 반환시간 : " + Ret / (float)count;
        gcModel.addTask(str1, 0,0);
	   	
       	GanttChart gc = new GanttChart(gcModel);
   	}
   
   public static void FCFS(cal p1[], int count) {
	   	GanttChartModel gcModel = new GanttChartModel(); //간트 생성
	   	gcModel.addTask("알고리즘명: FCFS", 0,0);
	   	int num2 = -1;
	   	int ret = 0;
	   	int end_count = 0;
	   	
	   	int [] ser_time = new int[count];
	   		for(int i = 0; i < count; i++)
	   		{
	   			ser_time[i] = p1[i].ser_time;
	   		}

	    while(true)
	   		//각 반복이 끝난 후 서비스 시간이 0인 프로세슨 제외해야 함 
	   	{
	   		num2 = -1;
	   		for(int i = 0; i < count; i++)	//들어온 순서대로 프로세스 검색
	   		{
	   			if(p1[i].start_time <= ret && p1[i].ser_time != 0)	//ret보다 작고, 서비스시간이 0이 아닌 것 우선순위가 높은 것
	   			{
	   				if(num2 == -1 || p1[i].start_time < p1[num2].start_time)
	   					num2 = i;
	   			}
	   		}

	   		p1[num2].Ans = ret - p1[num2].start_time;
	   		p1[num2].Wai = p1[num2].Ans;
	   		p1[num2].Ret = ret + p1[num2].ser_time;
	   			
	   		if(ret == 0)
	   			gcModel.addTask(p1[num2].getName(), p1[num2].start_time + 1, p1[num2].Ret + 1);
	   		else
	   			gcModel.addTask(p1[num2].getName(), p1[num2].Ret - ser_time[num2] + 2, ser_time[num2]);
	   			
	   			
	   		p1[num2].ser_time = 0;
	   		ret = p1[num2].Ret;
	   		
	   		end_count++;
	   		
	   		if(end_count == count)
	   			break;
	   	}
	    
	    for(int i = 0; i < count; i++)
        {
        	 p1[i].Wai = (p1[i].Ret - ser_time[i]) - p1[i].start_time;
             p1[i].Ret = p1[i].Ret - p1[i].start_time;
        }
        
        float Wai = 0;
          float Ans = 0;
          float Ret = 0;
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "대기시간 : " + p1[i].Wai;
           gcModel.addTask(str1, 0,0);
           Wai = Wai + p1[i].Wai;
        }
        str1 ="평균 대기시간 : " + Wai / (float)count;
        gcModel.addTask(str1, 0,0);
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "응답시간 : " + p1[i].Ans;
           gcModel.addTask(str1, 0,0);
            Ans = Ans + p1[i].Ans;
        }
        str1 ="평균 응답시간 : " + Ans / (float)count;
        gcModel.addTask(str1, 0,0);
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "반환시간 : " + p1[i].Ret;
           gcModel.addTask(str1, 0,0);
            Ret = Ret + p1[i].Ret;
        }
        str1 ="평균 반환시간 : " + Ret / (float)count;
        gcModel.addTask(str1, 0,0);
        
        GanttChart gc = new GanttChart(gcModel);
   }
   public static void SJF(cal p1[], int count) {
	   GanttChartModel gcModel = new GanttChartModel(); //간트 생성
     	gcModel.addTask("알고리즘명: SJF", 0,0);
     	int num2 = -1;
	   	int ret = 0;
	   	int end_count = 0;
	   	
	   	int [] ser_time = new int[count];
	   		for(int i = 0; i < count; i++)
	   		{
	   			ser_time[i] = p1[i].ser_time;
	   		}
	   
	   	num2 = 0;				//프로세스 번호

	    while(true)
	   		//각 반복이 끝난 후 서비스 시간이 0인 프로세슨 제외해야 함 
	   	{
	   		num2 = -1;
	   		for(int i = 0; i < count; i++)	//남은시간이 가장 적은 프로세스 검색
	   		{
	   			if(p1[i].start_time <= ret && p1[i].ser_time != 0)	//ret보다 작고, 서비스시간이 0이 아닌 것
	   			{
	   				if(num2 == -1 || p1[i].ser_time < p1[num2].ser_time)
	   					num2 = i;
	   			}
	   		}

	   		p1[num2].Ans = ret - p1[num2].start_time;
	   		p1[num2].Wai = p1[num2].Ans;
	   		p1[num2].Ret = ret + p1[num2].ser_time;
	   			
	   			
	   			
	   		if(ret == 0)
	   			gcModel.addTask(p1[num2].getName(), p1[num2].start_time + 1, p1[num2].Ret + 1);
	   		else
	   			gcModel.addTask(p1[num2].getName(), p1[num2].Ret - ser_time[num2] + 2, ser_time[num2]);
	   			
	   			
	   		p1[num2].ser_time = 0;
	   		ret = p1[num2].Ret;
	   		
	   		end_count++;
	   		
	   		if(end_count == count)
	   			break;
	   	}
      
      //추가
        for(int i = 0; i < count; i++)
        {
        	 p1[i].Wai = (p1[i].Ret - ser_time[i]) - p1[i].start_time;
             p1[i].Ret = p1[i].Ret - p1[i].start_time;
        }
        
        float Wai = 0;
          float Ans = 0;
          float Ret = 0;
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "대기시간 : " + p1[i].Wai;
           gcModel.addTask(str1, 0,0);
           Wai = Wai + p1[i].Wai;
        }
        str1 ="평균 대기시간 : " + Wai / (float)count;
        gcModel.addTask(str1, 0,0);
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "응답시간 : " + p1[i].Ans;
           gcModel.addTask(str1, 0,0);
            Ans = Ans + p1[i].Ans;
        }
        str1 ="평균 응답시간 : " + Ans / (float)count;
        gcModel.addTask(str1, 0,0);
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "반환시간 : " + p1[i].Ret;
           gcModel.addTask(str1, 0,0);
            Ret = Ret + p1[i].Ret;
        }
        str1 ="평균 반환시간 : " + Ret / (float)count;
        gcModel.addTask(str1, 0,0);
        
        GanttChart gc = new GanttChart(gcModel);
   }
   public static void HRN(cal p1[], int count) {
     GanttChartModel gcModel = new GanttChartModel(); //간트 생성
     gcModel.addTask("알고리즘명: HRN", 0,0);
     	int num2 = -1;
	   	int ret = 0;
	   	int end_count = 0;
	   	
	   	int [] ser_time = new int[count];
	   		for(int i = 0; i < count; i++)
	   		{
	   			ser_time[i] = p1[i].ser_time;
	   		}
	   
	   	num2 = 0;				//프로세스 번호

	    while(true)
	   		//각 반복이 끝난 후 서비스 시간이 0인 프로세슨 제외해야 함 
	   	{
	   		num2 = -1;
	   		for(int i = 0; i < count; i++)	//남은시간이 가장 적은 프로세스 검색
	   		{
	   			if(p1[i].start_time == 0 && p1[i].ser_time != 0)
	   				num2 = i;
	   			
	   			else if(p1[i].start_time <= ret && p1[i].ser_time != 0)	//ret보다 작고, 서비스시간이 0이 아닌 것 우선순위가 높은 것
	   			{
	   				if(num2 == -1 || (float)(ret+p1[i].ser_time)/((float)p1[i].ser_time) >
	   				(float)(ret + p1[num2].ser_time)/((float)p1[num2].ser_time))
	   					num2 = i;
	   			}
	   		}

	   		p1[num2].Ans = ret - p1[num2].start_time;
	   		p1[num2].Wai = p1[num2].Ans;
	   		p1[num2].Ret = ret + p1[num2].ser_time;
	   			
	   		if(ret == 0)
	   			gcModel.addTask(p1[num2].getName(), p1[num2].start_time + 1, p1[num2].Ret + 1);
	   		else
	   			gcModel.addTask(p1[num2].getName(), p1[num2].Ret - ser_time[num2] + 2, ser_time[num2]);
	   			
	   			
	   		p1[num2].ser_time = 0;
	   		ret = p1[num2].Ret;
	   		
	   		end_count++;
	   		
	   		if(end_count == count)
	   			break;
	   	}
      
      //추가
        for(int i = 0; i < count; i++)
        {
        	 p1[i].Wai = (p1[i].Ret - ser_time[i]) - p1[i].start_time;
             p1[i].Ret = p1[i].Ret - p1[i].start_time;
        }
        
        float Wai = 0;
          float Ans = 0;
          float Ret = 0;
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "대기시간 : " + p1[i].Wai;
           gcModel.addTask(str1, 0,0);
           Wai = Wai + p1[i].Wai;
        }
        str1 ="평균 대기시간 : " + Wai / (float)count;
        gcModel.addTask(str1, 0,0);
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "응답시간 : " + p1[i].Ans;
           gcModel.addTask(str1, 0,0);
            Ans = Ans + p1[i].Ans;
        }
        str1 ="평균 응답시간 : " + Ans / (float)count;
        gcModel.addTask(str1, 0,0);
        
        for(int i = 0; i < count; i++)
        {
           str1 = p1[i].name + "반환시간 : " + p1[i].Ret;
           gcModel.addTask(str1, 0,0);
            Ret = Ret + p1[i].Ret;
        }
        str1 ="평균 반환시간 : " + Ret / (float)count;
        gcModel.addTask(str1, 0,0);
        
        GanttChart gc = new GanttChart(gcModel);
   }
   public static void RR(cal p1[], int count, int time) {
     GanttChartModel gcModel = new GanttChartModel(); //간트 생성
    
	   	gcModel.addTask("알고리즘명: RR", 0,0);
	   
	   	int num2 = -1;
	   	int ret = 0;
	   	int end_count = 0;
	   	
	   	int [] ser_time = new int[count];
	   		for(int i = 0; i < count; i++)
	   		{
	   			ser_time[i] = p1[i].ser_time;
	   		}
	   		int [] start_time = new int[count];
	   		
	   		for(int i = 0; i < count; i++)
	   		{
	   			start_time[i] = p1[i].start_time;
	   		}
	   		
	   		int [] ans_time = new int[count];
	   		
	   		for(int i = 0; i < count; i++)
	   		{
	   			ans_time[i] = -1;
	   		}
	   		
	   		
	   	while(true)
	   	{
	   		for(int i = 0; i < count; i++)
	   		{
	   			if( num2 == -1 || p1[i].start_time < p1[num2].start_time && p1[i].ser_time != 0)
	   			{
	   				num2 = i;
	   			}
	   		}
	   		if(p1[num2].ser_time <= time)	//타임슬라이스보다 작을 경우 
   			{
	   			if(ans_time[num2] == -1)
	   				ans_time[num2] = ret - p1[num2].start_time;
	   			
   				p1[num2].Ans = ret - p1[num2].start_time;
	   			p1[num2].Wai = p1[num2].Ans;
	   			p1[num2].Ret = ret + p1[num2].ser_time;
	   			
	   			if(ret == 0)
	   				gcModel.addTask(p1[num2].getName(), p1[num2].start_time + 1, p1[num2].Ret + 1);
	   			else
	   				gcModel.addTask(p1[num2].getName(), p1[num2].Ret - p1[num2].ser_time + 2, p1[num2].ser_time);
	   			
	   			
	   			p1[num2].ser_time = 0;
	   			
	   			ret = p1[num2].Ret;
	   			
	   			p1[num2].start_time = ret;
	   			
	   			end_count++;
   			}
   			else
   			{
   				if(ans_time[num2] == -1)
	   				ans_time[num2] = ret - p1[num2].start_time;;
	   			
   				
   				p1[num2].Ans = ret - p1[num2].start_time;
	   			p1[num2].Wai = p1[num2].Ans;
	   			p1[num2].Ret = ret + time;
	   			
	   			if(ret == 0)
	   				gcModel.addTask(p1[num2].getName(), p1[num2].start_time + 1, p1[num2].Ret + 1);
	   			else
	   				gcModel.addTask(p1[num2].getName(), p1[num2].Ret - time + 2, time);
	   			
	   			
	   			p1[num2].ser_time = p1[num2].ser_time - time;
	   			ret = p1[num2].Ret;
	   			
	   			p1[num2].start_time = ret;
   			}
	   		if(end_count == count)
	   			break;
	   	}

	  //추가
     for(int i = 0; i < count; i++)
     {
    	 p1[i].Wai = p1[i].Ret - start_time[i] - ser_time[i];
    	 p1[i].Ret = p1[i].Ret - start_time[i];
    	 p1[i].Ans = ans_time[i];
     }
     
     float Wai = 0;
       float Ans = 0;
       float Ret = 0;
     
     for(int i = 0; i < count; i++)
     {
        str1 = p1[i].name + "대기시간 : " + p1[i].Wai;
        gcModel.addTask(str1, 0,0);
        Wai = Wai + p1[i].Wai;
     }
     str1 ="평균 대기시간 : " + Wai / (float)count;
     gcModel.addTask(str1, 0,0);
     
     for(int i = 0; i < count; i++)
     {
        str1 = p1[i].name + "응답시간 : " + p1[i].Ans;
        gcModel.addTask(str1, 0,0);
         Ans = Ans + p1[i].Ans;
     }
     str1 ="평균 응답시간 : " + Ans / (float)count;
     gcModel.addTask(str1, 0,0);
     
     for(int i = 0; i < count; i++)
     {
        str1 = p1[i].name + "반환시간 : " + p1[i].Ret;
        gcModel.addTask(str1, 0,0);
         Ret = Ret + p1[i].Ret;
     }
     str1 ="평균 반환시간 : " + Ret / (float)count;
     gcModel.addTask(str1, 0,0);
	   	
    	GanttChart gc = new GanttChart(gcModel);
   }
   public static void SRT(cal p1[], int count, int time) {
	   GanttChartModel gcModel = new GanttChartModel(); //간트 생성
	    
	   	gcModel.addTask("알고리즘명: SRT", 0,0);
	   
	   	int num1 = -1;
	   	int num2 = -1;
	   	int ret = 0;
	   	int end_count = 0;
	   	
	   	int [] ser_time = new int[count];
	   		for(int i = 0; i < count; i++)
	   		{
	   			ser_time[i] = p1[i].ser_time;
	   		}
	   		int [] start_time = new int[count];
	   		
	   		for(int i = 0; i < count; i++)
	   		{
	   			start_time[i] = p1[i].start_time;
	   		}
	   		
	   		int [] ans_time = new int[count];
	   		
	   		for(int i = 0; i < count; i++)
	   		{
	   			ans_time[i] = -1;
	   		}
	   		
	   		
	   	while(true)
	   	{
	   		num2 = -1;
	   		for(int i = 0; i < count; i++)
	   		{
	   			if((p1[i].ser_time != 0 && num2 == -1) ||( p1[i].ser_time != 0 && p1[i].ser_time <= p1[num2].ser_time
	   					&& p1[i].start_time <= ret))
	   			{
	   				num2 = i;
	   			}
	   		}
	   		System.out.println("num2선택" + num2);
	   		//num2프로세스의 선택
	   		if(p1[num2].ser_time <= time)	//타임슬라이스보다 작을 경우 
  			{
	   			if(ans_time[num2] == -1)	//처음실행되는 경우
	   				ans_time[num2] = ret - p1[num2].start_time;
	   			
  				p1[num2].Ans = ret - p1[num2].start_time;
	   			p1[num2].Wai = p1[num2].Ans;
	   			p1[num2].Ret = ret + p1[num2].ser_time;
	   			
	   			if(ret == 0)
	   				gcModel.addTask(p1[num2].getName(), p1[num2].start_time + 1, p1[num2].Ret + 1);
	   			else
	   				gcModel.addTask(p1[num2].getName(), p1[num2].Ret - p1[num2].ser_time + 2, p1[num2].ser_time);
	   			
	   			
	   			p1[num2].ser_time = 0;
	   			
	   			ret = p1[num2].Ret;
	   			
	   			p1[num2].start_time = ret;		//다시 큐에 들어올 때의 시간
	   			
	   			end_count++;
  			}
  			else
  			{
  				if(ans_time[num2] == -1)
	   				ans_time[num2] = ret - p1[num2].start_time;;
	   			
  				
  				p1[num2].Ans = ret - p1[num2].start_time;
	   			p1[num2].Wai = p1[num2].Ans;
	   			p1[num2].Ret = ret + time;
	   			
	   			if(ret == 0)
	   				gcModel.addTask(p1[num2].getName(), p1[num2].start_time + 1, p1[num2].Ret + 1);
	   			else
	   				gcModel.addTask(p1[num2].getName(), p1[num2].Ret - time + 2, time);
	   			
	   			
	   			p1[num2].ser_time = p1[num2].ser_time - time;
	   			ret = p1[num2].Ret;
	   			
	   			p1[num2].start_time = ret;
  			}
	   		if(end_count == count)
	   			break;
	   	}

	  //추가
    for(int i = 0; i < count; i++)
    {
   	 p1[i].Wai = p1[i].Ret - start_time[i] - ser_time[i];
   	 p1[i].Ret = p1[i].Ret - start_time[i];
   	 p1[i].Ans = ans_time[i];
    }
    
    float Wai = 0;
      float Ans = 0;
      float Ret = 0;
    
    for(int i = 0; i < count; i++)
    {
       str1 = p1[i].name + "대기시간 : " + p1[i].Wai;
       gcModel.addTask(str1, 0,0);
       Wai = Wai + p1[i].Wai;
    }
    str1 ="평균 대기시간 : " + Wai / (float)count;
    gcModel.addTask(str1, 0,0);
    
    for(int i = 0; i < count; i++)
    {
       str1 = p1[i].name + "응답시간 : " + p1[i].Ans;
       gcModel.addTask(str1, 0,0);
        Ans = Ans + p1[i].Ans;
    }
    str1 ="평균 응답시간 : " + Ans / (float)count;
    gcModel.addTask(str1, 0,0);
    
    for(int i = 0; i < count; i++)
    {
       str1 = p1[i].name + "반환시간 : " + p1[i].Ret;
       gcModel.addTask(str1, 0,0);
        Ret = Ret + p1[i].Ret;
    }
    str1 ="평균 반환시간 : " + Ret / (float)count;
    gcModel.addTask(str1, 0,0);
	   	
   	GanttChart gc = new GanttChart(gcModel);
   }
   //프로세스 갯수, 시간할당량, 프로세스 이름, 숫자값
   public third(int x,int y, String[] str, int[][] num){
      //프로세스 이름, 도착시간, 시간 할당량, 우선순위 순
      
      cal[] p1 = new cal[x];
      
      Set(p1, str, num, x);
      
      FCFS(p1, x);
      System.out.println("FCFS결과");
      Print(p1, x);
      Set(p1, str, num, x);
      
      SJF(p1, x);
      System.out.println("SJF결과");
      Print(p1, x);
      Set(p1, str, num, x);
      
      HRN(p1, x);
      System.out.println("HRN결과");
      Print(p1, x);
      Set(p1, str, num, x);
      
      RR(p1, x, y);
      System.out.println("RR결과");
      Print(p1, x);  
      Set(p1, str, num, x);
      
      SRT(p1, x, y);
      System.out.println("SRT결과!");
      Print(p1, x);
      Set(p1, str, num, x);
      
      SP(p1, x);
      System.out.println("비선점형 우선순위결과");
      Print(p1, x);
      Set(p1, str, num, x);
      
      SP2(p1, x);
      System.out.println("선점형 우선순위결과");
      Print(p1, x);
      Set(p1, str, num, x);
      
      
   } 
}