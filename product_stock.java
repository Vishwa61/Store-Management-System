package store_management;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
public class product_stock implements ActionListener
{
    JFrame mainframe = new JFrame();  //frame for Search
    JButton btnProduct,btnStock,btnDisplay,btnBlank,btnBill;
    HashMap<String,hmnode> hm;

    
    public product_stock(HashMap<String,hmnode> hm)
    {
    	this.hm=hm;
    	mainframe.setLayout(new GridLayout());
        mainframe.setSize(300,300);
        mainframe.setTitle("Store");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        mainframe.setLocationRelativeTo(null);
        mainframe.setBackground(Color.BLACK);        

        //panel1 
            
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4,1));
        panel1.setBackground(Color.WHITE);
      
          
       
        btnProduct = new JButton("Insert Product");
        btnStock = new JButton("Add Stock  ");
        btnDisplay = new JButton("Display   ");
        btnBill = new JButton("Generate Bill");
        btnBlank = new JButton("");
        btnBlank.setBackground(Color.LIGHT_GRAY);
        btnBlank.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
        panel1.add(btnProduct);
        panel1.add(btnStock);
        panel1.add(btnDisplay);
        panel1.add(btnBill);
        mainframe.add(panel1);
        mainframe.setVisible(true);   
        btnProduct.addActionListener(this);
        btnStock.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnBill.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) 
    {
        try
        {
             if(btnProduct.getText().equals(e.getActionCommand()))
            { 
            	 insert  ins = new insert();
                 ins.Insert(hm);
                 mainframe.setVisible(false);
                 ins.mainframe.setVisible(true);
            }
             else if(btnStock.getText().equals(e.getActionCommand()))
             {   
                
                 Add_stock  sckobj = new Add_stock(hm);
                 mainframe.setVisible(false);
                 sckobj.mainframe.setVisible(true);
               
                    
             }
            else if(btnDisplay.getText().equals(e.getActionCommand()))
             {   
            	 Display  disobj = new Display(hm);
                 mainframe.setVisible(false);
                 disobj.mainframe.setVisible(true);
                 
             }      
            else if(btnBill.getText().equals(e.getActionCommand()))
             {   
                 mainframe.setVisible(false);
            	 BillGenerate  billg = new BillGenerate(hm);
                 billg.mainframe.setVisible(true);
                 
             }
           
        }
        catch(Exception w)
        {
            System.out.println("Error in product stock");
        }
    }
    	public static void main(String args[])
	{
		HashMap<String,hmnode> hm = new HashMap<String,hmnode>();
	        Store s = new Store();		
		s.makehm(hm); 
                product_stock ps=new product_stock(hm);
	             
     
	}
}