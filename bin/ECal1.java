import javax.swing.*;  
import javax.swing.ImageIcon; 
import java.awt.event.*;
import java.io.*;  
import java.awt.*;
import java.awt.Image;
import java.lang.Object;
import javax.imageio.ImageIO;
import java.util.*;
import java.util.Scanner;
import java.net.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ECal1 implements ActionListener,Runnable {
	JFrame f1;
	JLabel background;
	JPanel panel;
	//JTextField p8;
	JButton button1, button2, button3;
	int Bx,By,Lx,Ly;

// time	
Thread t=null;  
int hours=0, minutes=0, seconds=0;  
String timeString = "";  
JLabel b,l;
	
    ECal1(){
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
		Bx=value1-75;
		By=value2-75;
		Lx=(value1/2)-75;
		Ly=value2-200;
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
		button1 = new JButton();
		
		button1.setOpaque(false);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		
		button1.setBounds(Lx,Ly,180,100);
		//button1.setBackground(Color.BLUE);
		//button1.setForeground(Color.white);
		//button1.setFont(new Font("", 1, 35));
		//button1.setIcon(resize(new ImageIcon("1.png"), 150, 100));
		button1.addActionListener(this);
		panel.add(button1);		
		
		
		button2 = new JButton();
		button2.setBounds(Bx,By,60,60);
		//button2.setBackground(Color.RED);
		//button2.setForeground(Color.white);
		//button2.setFont(new Font("", 1, 9));
		button2.setBorderPainted(false);
		button2.setOpaque(false);
		button2.setContentAreaFilled(false);
		//button2.setIcon(resize(new ImageIcon("exit.png"), 50, 50));
		button2.addActionListener(this);
		panel.add(button2);

		button3 = new JButton("Info.");
		button3.setBounds(Lx+50,By,100,40);
		button3.setBackground(Color.RED);
		button3.setForeground(Color.white);
		button3.setFont(new Font("", 1, 20));
		button3.setBorderPainted(false);
		//button3.setOpaque(false);
		//button3.setIcon(resize(new ImageIcon("exit.png"), 50, 50));
		button3.addActionListener(this);
		panel.add(button3);

		background=new JLabel();
		background.setBounds(0,0,value1, value2);
		background.setIcon(resize(new ImageIcon("2.jpg"), value1, value2));
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
			ImageIcon a = new ImageIcon("4.jpg");
			Image aImage = a.getImage();
			Image modifiedAbcImage = aImage.getScaledInstance(180,170 , java.awt.Image.SCALE_SMOOTH);
			a = new ImageIcon(modifiedAbcImage);
			JOptionPane.showMessageDialog(null,"# : All Right Received...\r\n# : ©-Lakshitha Sankalpa.\r\n# : 2020.05.03","Thank You!!!!!!!!!!!!!!",JOptionPane.INFORMATION_MESSAGE,a);
			System.exit(0);
		}
	else if(WelcomeAction.getSource()==button1)
		{

			System.out.println("Next Button ok");
			new ECal2();
			f1.dispose();
			//System.exit(0);
		}
	else
	{
			ImageIcon b1 = new ImageIcon("i1.png");
			Image bImage = b1.getImage();
			Image modifiedBImage = bImage.getScaledInstance(700,480 , java.awt.Image.SCALE_SMOOTH);
			b1 = new ImageIcon(modifiedBImage);
			JOptionPane.showMessageDialog(null,"","Information!!",JOptionPane.INFORMATION_MESSAGE,b1);
			
			ImageIcon c = new ImageIcon("i2.png");
			Image cImage = c.getImage();
			Image modifiedCImage = bImage.getScaledInstance(700,480 , java.awt.Image.SCALE_SMOOTH);
			c = new ImageIcon(modifiedCImage);
			JOptionPane.showMessageDialog(null,"","Information!!",JOptionPane.INFORMATION_MESSAGE,c);
			
			ImageIcon d = new ImageIcon("i3.png");
			Image dImage = d.getImage();
			Image modifiedDImage = dImage.getScaledInstance(650,280 , java.awt.Image.SCALE_SMOOTH);
			d = new ImageIcon(modifiedDImage);
			JOptionPane.showMessageDialog(null,"","Information!!",JOptionPane.INFORMATION_MESSAGE,d);
			
			ImageIcon e = new ImageIcon("i4.png");
			Image eImage = e.getImage();
			Image modifiedEImage = eImage.getScaledInstance(700,480 , java.awt.Image.SCALE_SMOOTH);
			e = new ImageIcon(modifiedEImage);
			JOptionPane.showMessageDialog(null,"","Information!!",JOptionPane.INFORMATION_MESSAGE,e);

			ImageIcon f = new ImageIcon("i5.png");
			Image fImage = f.getImage();
			Image modifiedFImage = fImage.getScaledInstance(700,480 , java.awt.Image.SCALE_SMOOTH);
			f = new ImageIcon(modifiedFImage);
			JOptionPane.showMessageDialog(null,"","Information!!",JOptionPane.INFORMATION_MESSAGE,f);
			
			ImageIcon g = new ImageIcon("i6.png");
			Image gImage = g.getImage();
			Image modifiedGImage = gImage.getScaledInstance(700,480 , java.awt.Image.SCALE_SMOOTH);
			g = new ImageIcon(modifiedGImage);
			JOptionPane.showMessageDialog(null,"","Information!!",JOptionPane.INFORMATION_MESSAGE,g);
			
			ImageIcon h = new ImageIcon("1.png");
			Image hImage = h.getImage();
			Image modifiedHImage = hImage.getScaledInstance(60,60 , java.awt.Image.SCALE_SMOOTH);
			h = new ImageIcon(modifiedHImage);			
			JOptionPane.showMessageDialog(null,"Get resources form:\r\n \t https://www.ceb.lk/commercial-tariff/en#menu2\r\n \t  ceylon electricity board (2020, April - Update) \r\nThank You..! \r\nDevelop By : PML_Sankalpa","Information!!",JOptionPane.INFORMATION_MESSAGE,h);

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
		new ECal1_1();
		new ECal1();		
		
	} 
}  






