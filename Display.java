package store_management;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;
public class Display implements ActionListener
{
    JFrame mainframe = new JFrame();  //frame for Search
    JComboBox X1,Z1;
    JButton btndisplay,btnBack,priceLTH,priceHTL,btnMWK,btnprc,btndisall;
    JTextField spctext;
    HashMap<String,hmnode> hm;
    
    public Display(HashMap<String,hmnode> hm)
    {
    	this.hm=hm;
        mainframe.setLayout(new GridLayout());
        mainframe.setSize(450,200);
        mainframe.setTitle("Display");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        mainframe.setLocationRelativeTo(null);
        mainframe.setBackground(Color.WHITE);        

        //panel1 
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(5,5));
        panel1.setBackground(Color.WHITE);
        
        btnMWK = new JButton("Stock");
        btnprc = new JButton("Price");     
        JLabel spc = new JLabel("Search by ID");
        spctext = new JTextField();
        X1 = new JComboBox();
        X1.addItem("High to Low");
        X1.addItem("Low to High");
        
        Z1 = new JComboBox();
        Z1.addItem("High to Low");
        Z1.addItem("Low to High");
        
        
        
       
        panel1.add(spc);
        panel1.add(spctext);
        panel1.add(btnMWK,BorderLayout.EAST);
        panel1.add(X1);
         
        //panel button
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.WHITE);
        
        btndisplay = new JButton("Dislpay");
        btnBack = new JButton("Back to previous frame");
        btndisall = new JButton("Display All");
        
        panel1.add(btnprc);
        panel1.add(Z1);
        panel1.add(btnBack);
        panel1.add(btndisplay);
        panel1.add(btndisall);
        
        X1.setVisible(false);
        Z1.setVisible(false);
        mainframe.add(panel1);
        mainframe.setVisible(true);
        btndisplay.addActionListener(this);
        btnBack.addActionListener(this);
        btnMWK.addActionListener(this);
        btnprc.addActionListener(this);
        btndisall.addActionListener(this);
    }
	int flag=0;

    public void actionPerformed(ActionEvent e) 
    {
        try
        {
            if(btndisplay.getText().equals(e.getActionCommand()))
            { 
            	 Store s = new Store();
            	 if(flag==0)
            	 {
            		 String sid=spctext.getText();
            		 s.searchById(sid,hm);
            	 }
            	 else if(flag==1)
            	 {
            		 if(Z1.getSelectedItem().toString().equals("High to Low"))
            			 s.high_to_low(hm, flag);
            		 else
            		 {
            			 s.low_to_high(hm, flag);
            		 }
            	 }
            	 else if(flag==2)
            	 {
            		 if(X1.getSelectedItem().toString().equals("High to Low"))
            			 s.high_to_low(hm, flag);
            		 else
            		 {
            			 s.low_to_high(hm, flag);
            		 }
            	 }
            	 flag=0;
            		 
            }
            else if(btnBack.getText().equals(e.getActionCommand()))
            {   
            	product_stock  obj1 = new product_stock(hm);
                mainframe.setVisible(false);
                obj1.mainframe.setVisible(true);
            }           
            else if(btnMWK.getText().equals(e.getActionCommand()))
            {   
            	X1.setVisible(true);
            	flag=2;
            }           
            else if(btnprc.getText().equals(e.getActionCommand()))
            {   
            	Z1.setVisible(true);
            	flag=1;
            } 
            else if(btndisall.getText().equals(e.getActionCommand()))
            {
            	Store s = new Store();
            	s.display_all();
            }
        }
        catch(Exception w)
        {
            System.out.println("Error");
        }
    
}
    }