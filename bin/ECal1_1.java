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

public class ECal1_1 {
	JFrame f1;
	JLabel background, L1;
	JPanel panel;
	JButton button1, button2;
	int Bx,By,Lx,Ly;
	
    ECal1_1(){


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
		
		background=new JLabel();
		background.setBounds(0,0,value1, value2);
		background.setIcon(resize(new ImageIcon("1.jpg"), value1, value2));
		panel.add(background); 
		


		Image icon = Toolkit.getDefaultToolkit().getImage("1.png");  
		f1.setIconImage(icon);	
		f1.add(panel);
		f1.setVisible(true);
	} 


	
	public static ImageIcon resize(ImageIcon im, int w, int h){
		BufferedImage bi=new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		Graphics2D gd=(Graphics2D)bi.createGraphics();
		gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
		gd.drawImage(im.getImage(),0,0,w,h,null);
		gd.dispose();
		return new ImageIcon(bi);
	}

	public static void main(String[] args) {  
		new ECal1_1(); 
		//new C1();
	} 
}  