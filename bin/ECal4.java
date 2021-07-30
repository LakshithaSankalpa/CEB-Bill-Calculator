import javax.swing.*;  
import javax.swing.ImageIcon; 
import java.awt.event.*;
import java.io.*;  
import java.awt.*;
import java.awt.Image;
import java.lang.Object;
import javax.imageio.ImageIO;
import java.util.*;
import java.net.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ECal4 implements ActionListener,Runnable {
	JFrame f1;
	JLabel background, L1;
	JPanel panel;
	JTextField T1,T2,T3,BT1,BT2,BT3,BT4,BT5,BT6,BT7,BT8;
	JButton B1,B2,B3,B4,B5,B6, button1, button2;
	int Bx,By,Lx,Ly,BTx,BTx2;
	float unit,Month,PBA,kWhCharge=0, FixedCharge=0,TotBAmount=0, TotAmount=0, LPICharge=0;
	String kWhChargeS="0",FixedChargeS="0",TotAmountS="0",TotBAmountS="0", LPIChargeS="0",T1kWh="0",T3PBA="0";

// time	
Thread t=null;  
int hours=0, minutes=0, seconds=0;  
String timeString = "";  
JLabel b,l;
	
    ECal4(){
	//Read Txt------------------
				try {
				File F1 = new File("kWhCharge.txt");
				Scanner Scan1= new Scanner(F1);
				while (Scan1.hasNextLine()) {
				kWhChargeS = Scan1.nextLine();
				System.out.println(kWhChargeS);
				}
				Scan1.close();
				} catch (FileNotFoundException t) {
				System.out.println("An error occurred.");
				t.printStackTrace();
				}
				try {
				File F2 = new File("FixedCharge.txt");
				Scanner Scan2= new Scanner(F2);
				while (Scan2.hasNextLine()) {
				FixedChargeS = Scan2.nextLine();
				System.out.println(FixedChargeS);
				}
				Scan2.close();
				} catch (FileNotFoundException t) {
				System.out.println("An error occurred.");
				t.printStackTrace();
				}
				try {
				File F3 = new File("TotAmount.txt");
				Scanner Scan3= new Scanner(F3);
				while (Scan3.hasNextLine()) {
				TotAmountS = Scan3.nextLine();
				System.out.println(TotAmountS);
				}
				Scan3.close();
				} catch (FileNotFoundException t) {
				System.out.println("An error occurred.");
				t.printStackTrace();
				}		
				try {
				File F33 = new File("TotBAmount.txt");
				Scanner Scan33= new Scanner(F33);
				while (Scan33.hasNextLine()) {
				TotBAmountS = Scan33.nextLine();
				System.out.println(TotBAmountS);
				}
				Scan33.close();
				} catch (FileNotFoundException t) {
				System.out.println("An error occurred.");
				t.printStackTrace();
				}			
				try {
				File F333 = new File("LPI.txt");
				Scanner Scan333= new Scanner(F333);
				while (Scan333.hasNextLine()) {
				LPIChargeS = Scan333.nextLine();
				System.out.println(LPIChargeS);
				}
				Scan333.close();
				} catch (FileNotFoundException t) {
				System.out.println("An error occurred.");
				t.printStackTrace();
				}				
				try {
				File F4 = new File("kWh.txt");
				Scanner Scan4= new Scanner(F4);
				while (Scan4.hasNextLine()) {
				T1kWh = Scan4.nextLine();
				System.out.println(T1kWh);
				}
				Scan4.close();
				} catch (FileNotFoundException t) {
				System.out.println("An error occurred.");
				t.printStackTrace();
				}
				try {
				File F5 = new File("pba.txt");
				Scanner Scan5= new Scanner(F5);
				while (Scan5.hasNextLine()) {
				T3PBA = Scan5.nextLine();
				System.out.println(T3PBA);
				}
				Scan5.close();
				} catch (FileNotFoundException t) {
				System.out.println("An error occurred.");
				t.printStackTrace();
				}



	
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	
        // Create a wrapper around 
        // the double value 
        Double newData1 = new Double(width);
		Double newData2 = new Double(height);		
  
        // convert into int 
        int value1 = newData1.intValue(); 
		int value2 = newData2.intValue();
  
        // print the int value 
        System.out.println("width : " + value1);
		System.out.println("height : " + value2);
		// get scrn res value
		Bx=value1-135;
		By=value2-110;
		Lx=280;
		Ly=value2-200;
		BTx=(value1/2)+20;
		BTx2=BTx+325;
		System.out.println("Bx : " + Bx);
		System.out.println("By : " + By);
		System.out.println("Lx : " + Lx);
		System.out.println("Ly : " + Ly);
		
		f1 = new JFrame();
		f1.setSize(value1,value2);
		f1.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f1.setUndecorated(true);
		f1.setTitle("Electricity Bill Calculator");
		f1.setLocationRelativeTo(null);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		//#### Time & Date
				
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        l = new JLabel(date);
        System.out.println(date);
		l.setBounds(30,value2-35,100,30);
		l.setFont(new Font("", 1, 16));
		l.setForeground(Color.white);
        panel.add(l);
		
		t = new Thread(this);  
		t.start();  
      
		b=new JLabel();  
		b.setBounds(130,value2-35,100,30);
		b.setFont(new Font("", 1, 16));
		b.setForeground(Color.white);
		panel.add(b);
		
	//----------------------------

		B1 = new JButton("Industrial");
		B1.setBounds(50,250,130,50);
		B1.setBackground(Color.CYAN);
		B1.setForeground(Color.blue);
		B1.setFont(new Font("", 1, 18));
		//B1.setIcon(resize(new ImageIcon("1.png"), 150, 100));
		B1.addActionListener(this);
		panel.add(B1);

		B2 = new JButton("General");
		B2.setBounds(50,305,130,50);
		B2.setBackground(Color.MAGENTA);
		B2.setForeground(Color.white);
		B2.setFont(new Font("", 1, 18));
		//B2.setIcon(resize(new ImageIcon("1.png"), 150, 100));
		B2.addActionListener(this);
		panel.add(B2);

		B3 = new JButton("Hotel");
		B3.setBounds(50,360,130,50);
		B3.setBackground(Color.CYAN);
		B3.setForeground(Color.blue);
		B3.setFont(new Font("", 1, 18));
		//B3.setIcon(resize(new ImageIcon("1.png"), 150, 100));
		B3.addActionListener(this);
		panel.add(B3);

		B4 = new JButton("Government");
		B4.setBounds(50,415,130,50);
		B4.setBackground(Color.CYAN);
		B4.setForeground(Color.blue);
		B4.setFont(new Font("", 1, 16));
		//B4.setIcon(resize(new ImageIcon("1.png"), 150, 100));
		B4.addActionListener(this);
		panel.add(B4);

		B5 = new JButton("Domestic");
		B5.setBounds(50,470,130,50);
		B5.setBackground(Color.CYAN);
		B5.setForeground(Color.blue);
		B5.setFont(new Font("", 1, 18));
		//B5.setIcon(resize(new ImageIcon("1.png"), 150, 100));
		B5.addActionListener(this);
		panel.add(B5);

		B6 = new JButton("Religious");
		B6.setBounds(50,525,130,50);
		B6.setBackground(Color.CYAN);
		B6.setForeground(Color.blue);
		B6.setFont(new Font("", 1, 18));
		//B6.setIcon(resize(new ImageIcon("1.png"), 150, 100));
		B6.addActionListener(this);
		panel.add(B6);


		button1 = new JButton("Calculate");
		button1.setBounds(Lx,Ly,140,50);
		button1.setBackground(Color.RED);
		button1.setForeground(Color.white);
		button1.setFont(new Font("", 1, 22));
		//button1.setIcon(resize(new ImageIcon("1.png"), 150, 100));
		button1.addActionListener(this);
		panel.add(button1);		
		
		
		button2 = new JButton("Exit");
		button2.setForeground(Color.blue);
		button2.setFont(new Font("", 1, 14));
		button2.setBounds(Bx,By,100,100);
		button2.setOpaque(false);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button2.addActionListener(this);
		panel.add(button2);

		T1 = new JTextField(T1kWh);
		T1.setBounds(225,290,250,40);
		T1.setFont(new Font("", 1, 20));
		T1.setHorizontalAlignment(JTextField.CENTER);
		panel.add(T1);

		
		T2 = new JTextField("1");
		T2.setBounds(225,390,250,40);
		T2.setFont(new Font("", 1, 20));
		T2.setHorizontalAlignment(JTextField.CENTER);
		T2.setBackground(Color.white);
		T2.setEditable(false);
		panel.add(T2);

		T3 = new JTextField(T3PBA);
		T3.setBounds(225,490,250,40);
		T3.setFont(new Font("", 1, 20));
		T3.setHorizontalAlignment(JTextField.CENTER);
		panel.add(T3);

		BT1 = new JTextField(kWhChargeS);
		BT1.setHorizontalAlignment(JTextField.CENTER);
		BT1.setEditable(false);
		BT1.setForeground(Color.blue);
		BT1.setBackground(Color.white);
		BT1.setBounds(BTx,250,250,40);
		BT1.setFont(new Font("", 1, 20));
		panel.add(BT1);

		BT2 = new JTextField("0");
		BT2.setHorizontalAlignment(JTextField.CENTER);
		BT2.setEditable(false);
		BT2.setForeground(Color.blue);
		BT2.setBackground(Color.white);
		BT2.setBounds(BTx,350,250,40);
		BT2.setFont(new Font("", 1, 20));
		panel.add(BT2);

		BT3 = new JTextField("0");
		BT3.setHorizontalAlignment(JTextField.CENTER);
		BT3.setEditable(false);
		BT3.setForeground(Color.blue);
		BT3.setBackground(Color.white);
		BT3.setBounds(BTx,450,250,40);
		BT3.setFont(new Font("", 1, 20));
		panel.add(BT3);

		BT4 = new JTextField(LPIChargeS);
		BT4.setHorizontalAlignment(JTextField.CENTER);
		BT4.setEditable(false);
		BT4.setForeground(Color.magenta);
		BT4.setBackground(Color.white);
		BT4.setBounds(BTx,550,250,40);
		BT4.setFont(new Font("", 1, 20));
		panel.add(BT4);

		BT5 = new JTextField(FixedChargeS);
		BT5.setHorizontalAlignment(JTextField.CENTER);
		BT5.setEditable(false);
		BT5.setForeground(Color.blue);
		BT5.setBackground(Color.white);
		BT5.setBounds(BTx2,250,250,40);
		BT5.setFont(new Font("", 1, 20));
		panel.add(BT5);

		BT6 = new JTextField("0");
		BT6.setHorizontalAlignment(JTextField.CENTER);
		BT6.setEditable(false);
		BT6.setForeground(Color.blue);
		BT6.setBackground(Color.white);
		BT6.setBounds(BTx2,350,250,40);
		BT6.setFont(new Font("", 1, 20));
		panel.add(BT6);

		BT7 = new JTextField(TotAmountS);
		BT7.setHorizontalAlignment(JTextField.CENTER);
		BT7.setEditable(false);
		BT7.setForeground(Color.magenta);
		BT7.setBackground(Color.white);
		BT7.setBounds(BTx2,450,250,40);
		BT7.setFont(new Font("", 1, 20));
		panel.add(BT7);

		BT8 = new JTextField(TotBAmountS);
		BT8.setSize(4,2);
		BT8.setHorizontalAlignment(JTextField.CENTER);
		BT8.setEditable(false);
		BT8.setForeground(Color.red);
		BT8.setBackground(Color.white);
		BT8.setBounds(BTx2,550,250,40);
		BT8.setFont(new Font("", 1, 24));
		panel.add(BT8);
		
		
		background=new JLabel();
		background.setBounds(0,0,value1, value2);
		background.setIcon(resize(new ImageIcon("3.jpg"), value1, value2));
		panel.add(background); 
		
		

		Image icon = Toolkit.getDefaultToolkit().getImage("1.png");  
		f1.setIconImage(icon);	
		f1.add(panel);
		f1.setVisible(true);
	} 
	public void actionPerformed(ActionEvent WelcomeAction){

    if(WelcomeAction.getSource()==button2)
		{
			System.out.println("Exit Button ok");
			ImageIcon abc = new ImageIcon("4.jpg");
			Image abcImage = abc.getImage();
			Image modifiedAbcImage = abcImage.getScaledInstance(180,170 , java.awt.Image.SCALE_SMOOTH);
			abc = new ImageIcon(modifiedAbcImage);
			JOptionPane.showMessageDialog(null,"# : All Right Received...\r\n# : ©-Lakshitha Sankalpa.\r\n# : 2020.05.03","Thank You!!!!!!!!!!!!!!",JOptionPane.INFORMATION_MESSAGE,abc);
			System.exit(0);
		}
	else if(WelcomeAction.getSource()==B1)
		{
			System.out.println("Industrial Button ok");
			//txt----------------------------
		try {
				FileWriter kwh = new FileWriter("kWhCharge.txt");
				kwh.write("0");
				kwh.close();
				System.out.println("Successfully wrote kWhCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter FixedCg = new FileWriter("FixedCharge.txt");
				FixedCg.write("0");
				FixedCg.close();
				System.out.println("Successfully wrote FixedCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter tot = new FileWriter("TotAmount.txt");
				tot.write("0");
				tot.close();
				System.out.println("Successfully wrote TotAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter totb = new FileWriter("TotBAmount.txt");
				totb.write("0");
				totb.close();
				System.out.println("Successfully wrote TotBAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter LPI = new FileWriter("LPI.txt");
				LPI.write("0");
				LPI.close();
				System.out.println("Successfully wrote LPI ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//----------------------------------------
		try {
				FileWriter KWH = new FileWriter("kWh.txt");
				KWH.write("0");
				KWH.close();
				System.out.println("Successfully wrote kWh ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter pba = new FileWriter("pba.txt");
				pba.write("0");
				pba.close();
				System.out.println("Successfully wrote pba ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//-----------------------------------------------------------------	
			new ECal3();
			System.out.println("Industrial Button");
			f1.dispose();
			//System.exit(0);
		}
	else if(WelcomeAction.getSource()==B2)
		{
			System.out.println("General Button ok");
			//txt----------------------------
		try {
				FileWriter kwh = new FileWriter("kWhCharge.txt");
				kwh.write("0");
				kwh.close();
				System.out.println("Successfully wrote kWhCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter FixedCg = new FileWriter("FixedCharge.txt");
				FixedCg.write("0");
				FixedCg.close();
				System.out.println("Successfully wrote FixedCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter tot = new FileWriter("TotAmount.txt");
				tot.write("0");
				tot.close();
				System.out.println("Successfully wrote TotAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter totb = new FileWriter("TotBAmount.txt");
				totb.write("0");
				totb.close();
				System.out.println("Successfully wrote TotBAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter LPI = new FileWriter("LPI.txt");
				LPI.write("0");
				LPI.close();
				System.out.println("Successfully wrote LPI ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//----------------------------------------
		try {
				FileWriter KWH = new FileWriter("kWh.txt");
				KWH.write("0");
				KWH.close();
				System.out.println("Successfully wrote kWh ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter pba = new FileWriter("pba.txt");
				pba.write("0");
				pba.close();
				System.out.println("Successfully wrote pba ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//-----------------------------------------------------------------	
			new ECal4();
			System.out.println("General Button");
			f1.dispose();
			//System.exit(0);
		}
	else if(WelcomeAction.getSource()==B3)
		{
			System.out.println("Hotel Button ok");
			//txt----------------------------
		try {
				FileWriter kwh = new FileWriter("kWhCharge.txt");
				kwh.write("0");
				kwh.close();
				System.out.println("Successfully wrote kWhCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter FixedCg = new FileWriter("FixedCharge.txt");
				FixedCg.write("0");
				FixedCg.close();
				System.out.println("Successfully wrote FixedCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter tot = new FileWriter("TotAmount.txt");
				tot.write("0");
				tot.close();
				System.out.println("Successfully wrote TotAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter totb = new FileWriter("TotBAmount.txt");
				totb.write("0");
				totb.close();
				System.out.println("Successfully wrote TotBAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter LPI = new FileWriter("LPI.txt");
				LPI.write("0");
				LPI.close();
				System.out.println("Successfully wrote LPI ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//----------------------------------------
		try {
				FileWriter KWH = new FileWriter("kWh.txt");
				KWH.write("0");
				KWH.close();
				System.out.println("Successfully wrote kWh ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter pba = new FileWriter("pba.txt");
				pba.write("0");
				pba.close();
				System.out.println("Successfully wrote pba ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//-----------------------------------------------------------------	
			new ECal5();
			System.out.println("Hotel Button");
			f1.dispose();
			//System.exit(0);
		}
	else if(WelcomeAction.getSource()==B4)
		{
			System.out.println("Government Button ok");
			//txt----------------------------
		try {
				FileWriter kwh = new FileWriter("kWhCharge.txt");
				kwh.write("0");
				kwh.close();
				System.out.println("Successfully wrote kWhCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter FixedCg = new FileWriter("FixedCharge.txt");
				FixedCg.write("0");
				FixedCg.close();
				System.out.println("Successfully wrote FixedCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter tot = new FileWriter("TotAmount.txt");
				tot.write("0");
				tot.close();
				System.out.println("Successfully wrote TotAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter totb = new FileWriter("TotBAmount.txt");
				totb.write("0");
				totb.close();
				System.out.println("Successfully wrote TotBAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter LPI = new FileWriter("LPI.txt");
				LPI.write("0");
				LPI.close();
				System.out.println("Successfully wrote LPI ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//----------------------------------------
		try {
				FileWriter KWH = new FileWriter("kWh.txt");
				KWH.write("0");
				KWH.close();
				System.out.println("Successfully wrote kWh ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter pba = new FileWriter("pba.txt");
				pba.write("0");
				pba.close();
				System.out.println("Successfully wrote pba ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//-----------------------------------------------------------------	
			new ECal6();
			System.out.println("Government Button");
			f1.dispose();
			//System.exit(0);
		}
	else if(WelcomeAction.getSource()==B5)
		{
			//txt----------------------------
		try {
				FileWriter kwh = new FileWriter("kWhCharge.txt");
				kwh.write("0");
				kwh.close();
				System.out.println("Successfully wrote kWhCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter FixedCg = new FileWriter("FixedCharge.txt");
				FixedCg.write("0");
				FixedCg.close();
				System.out.println("Successfully wrote FixedCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter tot = new FileWriter("TotAmount.txt");
				tot.write("0");
				tot.close();
				System.out.println("Successfully wrote TotAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter totb = new FileWriter("TotBAmount.txt");
				totb.write("0");
				totb.close();
				System.out.println("Successfully wrote TotBAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter LPI = new FileWriter("LPI.txt");
				LPI.write("0");
				LPI.close();
				System.out.println("Successfully wrote LPI ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//----------------------------------------
		try {
				FileWriter KWH = new FileWriter("kWh.txt");
				KWH.write("0");
				KWH.close();
				System.out.println("Successfully wrote kWh ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter pba = new FileWriter("pba.txt");
				pba.write("0");
				pba.close();
				System.out.println("Successfully wrote pba ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//-----------------------------------------------------------------	
			new ECal2();
			System.out.println("Domestic Button");
			f1.dispose();
			//System.exit(0);
		}
	else if(WelcomeAction.getSource()==B6)
		{
			System.out.println("Religious Button ok");
			//txt----------------------------
		try {
				FileWriter kwh = new FileWriter("kWhCharge.txt");
				kwh.write("0");
				kwh.close();
				System.out.println("Successfully wrote kWhCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter FixedCg = new FileWriter("FixedCharge.txt");
				FixedCg.write("0");
				FixedCg.close();
				System.out.println("Successfully wrote FixedCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter tot = new FileWriter("TotAmount.txt");
				tot.write("0");
				tot.close();
				System.out.println("Successfully wrote TotAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter totb = new FileWriter("TotBAmount.txt");
				totb.write("0");
				totb.close();
				System.out.println("Successfully wrote TotBAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter LPI = new FileWriter("LPI.txt");
				LPI.write("0");
				LPI.close();
				System.out.println("Successfully wrote LPI ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//----------------------------------------
		try {
				FileWriter KWH = new FileWriter("kWh.txt");
				KWH.write("0");
				KWH.close();
				System.out.println("Successfully wrote kWh ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter pba = new FileWriter("pba.txt");
				pba.write("0");
				pba.close();
				System.out.println("Successfully wrote pba ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//-----------------------------------------------------------------	
			new ECal7();
			System.out.println("Religious Button");
			f1.dispose();
			//System.exit(0);
		}
	else //Calculate button
	{
		//Domestic
		String A = T1.getText();
		Float unit = Float.parseFloat(A);
		System.out.println("kWh : "+unit);
		String B = T2.getText();
		Float Month = Float.parseFloat(B);
		System.out.println("Month : "+Month);
		String C = T3.getText();
		Float PBA = Float.parseFloat(C);
		System.out.println("PBAmount : "+PBA);	
		//Float
		if(unit<=300)
		{
			kWhCharge = unit*18.30f;
			FixedCharge = 240;
			TotAmount = kWhCharge+FixedCharge;
			LPICharge = PBA*0.02f;
			TotBAmount = TotAmount+LPICharge+PBA;
			kWhChargeS=String.valueOf(kWhCharge); // balance value convert to String value
			FixedChargeS=String.valueOf(FixedCharge); // balance value convert to String value
			LPIChargeS=String.valueOf(LPICharge); // balance value convert to String value
			TotAmountS=String.valueOf(TotAmount); // balance value convert to String value
			TotBAmountS=String.valueOf(TotBAmount); // balance value convert to String value

		}
		else if(unit>300)
		{

			kWhCharge = unit*22.85f;
			FixedCharge = 240;
			TotAmount = kWhCharge+FixedCharge;
			LPICharge = PBA*0.02f;
			TotBAmount = TotAmount+LPICharge+PBA;
			kWhChargeS=String.valueOf(kWhCharge); // balance value convert to String value
			FixedChargeS=String.valueOf(FixedCharge); // balance value convert to String value
			LPIChargeS=String.valueOf(LPICharge); // balance value convert to String value
			TotAmountS=String.valueOf(TotAmount); // balance value convert to String value
			TotBAmountS=String.valueOf(TotBAmount); // balance value convert to String value
	
		}
		else
		{
			System.out.println("ERROR....!!!");
			JOptionPane.showMessageDialog(null, "Error..!!  kWh Value incorrect!  ","Attention",JOptionPane.WARNING_MESSAGE);
		}
		
			System.out.println("--------------------------------");
			System.out.println("PBA : "+PBA);
			System.out.println("kWhCharge : "+kWhChargeS);	
			System.out.println("FixedCharge : "+FixedChargeS);	
			System.out.println("LPICharge : "+LPIChargeS);	
			System.out.println("TotAmount : "+TotAmountS);	
			System.out.println("TotBAmount : "+TotBAmountS);
			
			
//------------------------------------
		
		try {
				FileWriter kwh = new FileWriter("kWhCharge.txt");
				kwh.write(kWhChargeS);
				kwh.close();
				System.out.println("Successfully wrote kWhCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter FixedCg = new FileWriter("FixedCharge.txt");
				FixedCg.write(FixedChargeS);
				FixedCg.close();
				System.out.println("Successfully wrote FixedCharge ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter tot = new FileWriter("TotAmount.txt");
				tot.write(TotAmountS);
				tot.close();
				System.out.println("Successfully wrote TotAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter totb = new FileWriter("TotBAmount.txt");
				totb.write(TotBAmountS);
				totb.close();
				System.out.println("Successfully wrote TotBAmount ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter LPI = new FileWriter("LPI.txt");
				LPI.write(LPIChargeS);
				LPI.close();
				System.out.println("Successfully wrote LPI ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//----------------------------------------
		try {
				FileWriter KWH = new FileWriter("kWh.txt");
				KWH.write(A);
				KWH.close();
				System.out.println("Successfully wrote kWh ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
		try {
				FileWriter pba = new FileWriter("pba.txt");
				pba.write(C);
				pba.close();
				System.out.println("Successfully wrote pba ");
		} catch (IOException ett) {
					System.out.println("An error occurred.");
					ett.printStackTrace();
		}
//-----------------------------------------------------------------	
	new ECal4();
	f1.dispose();
	
	}
	
	}
	
	public static ImageIcon resize(ImageIcon im, int w, int h){
		BufferedImage bi=new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		Graphics2D gd=(Graphics2D)bi.createGraphics();
		gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
		gd.drawImage(im.getImage(),0,0,w,h,null);
		gd.dispose();
		return new ImageIcon(bi);
	}


 public void run() {  
      try {  
         while (true) {  
  
            Calendar cal = Calendar.getInstance();  
            hours = cal.get( Calendar.HOUR_OF_DAY );  
            if ( hours > 12 ) hours -= 12;  
            minutes = cal.get( Calendar.MINUTE );  
            seconds = cal.get( Calendar.SECOND );  
  
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");  
            Date date = cal.getTime();  
            timeString = formatter.format( date );  
  
            printTime();  
  
            t.sleep( 1000 );  // interval given in milliseconds  
         }  
      }  
      catch (Exception e) { }  
 }  
  
public void printTime(){  
b.setText(timeString);  
}  


	public static void main(String[] args) {  
		new ECal4(); 

		//new C1();
	} 
}  