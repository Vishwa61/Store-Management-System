package store_management;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class BillGenerate implements ActionListener
{
    JFrame mainframe = new JFrame();  //frame for Search
    JComboBox ID;
    JButton btnGenerateBill,btnAdd,btnBack;
    JTextField Q;
    JTextField As;
     JComboBox C,P,C1,S;
     String s;
    HashMap<String,hmnode> hm;
   
    public BillGenerate(HashMap<String,hmnode> hm)
    {
    	this.hm=hm;
        mainframe.setLayout(new GridLayout());
        mainframe.setSize(300,200);
        mainframe.setTitle("BILL");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        mainframe.setLocationRelativeTo(null);
        mainframe.setBackground(Color.WHITE);        
        //panel1 
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4,1));
        panel1.setBackground(Color.WHITE);
        JLabel lblId = new JLabel("ID");
        JLabel lblQua = new JLabel("Quantity");
        JLabel blank = new JLabel("");
        ID = new JComboBox();
        try
		{
            File file = new File("E:\\store.xls");
			FileInputStream fread = null;
			fread = new FileInputStream(file);

			Workbook wb = WorkbookFactory.create(fread);
			Sheet sh = wb.getSheet("Sheet1");
			
			int rlast = sh.getLastRowNum()+1;
			for(int i=0;i<rlast;i++)
			{   
				Row row = sh.getRow(i);
				ID.addItem(row.getCell(10).toString());                                
			}
			System.out.println();
			fread.close();
		}
        catch(Exception err)
		{
        	System.out.println(err);
		}
        Q=new JTextField();
                      
        lblId.setForeground(Color.BLACK);
        lblQua.setForeground(Color.BLACK);
        
       
        panel1.add(lblId,BorderLayout.EAST);
        panel1.add(ID);
        panel1.add(lblQua);
        panel1.add(Q);
   
        //panel button
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.WHITE);
       
        btnGenerateBill = new JButton("Generate Bill");
        btnAdd = new JButton("Add to Bill");
        btnBack = new JButton("Back to previous frame");
        
        panel1.add(btnAdd);
        panel1.add(btnGenerateBill);
        panel1.add(btnBack);
      
        
        mainframe.add(panel1);
        mainframe.setVisible(true);
        btnGenerateBill.addActionListener(this);
        btnAdd.addActionListener(this);
        btnBack.addActionListener(this);
        
    }
    ArrayList<billnode> al = new ArrayList<billnode>();
    public void actionPerformed(ActionEvent e) 
    {
    	try
        {
            
            if(btnAdd.getText().equals(e.getActionCommand()))
            { 
            	 Store s = new Store();
            	 billnode bn = new billnode(ID.getSelectedItem().toString(),Float.parseFloat(Q.getText()),s.searchById(ID.getSelectedItem().toString(), hm).price,s.searchById(ID.getSelectedItem().toString(), hm).product);
            	 al.add(bn);
            }
            else if(btnGenerateBill.getText().equals(e.getActionCommand()))
            { 
            	 Store s = new Store();
            	 product_stock ps = new product_stock(hm);
                 mainframe.setVisible(false);
                 s.generate_bill(al);
                 ps.mainframe.setVisible(true);
                 
            }

             else if(btnBack.getText().equals(e.getActionCommand()))
             {   
            	 product_stock  obj1 = new product_stock(hm);
                 mainframe.setVisible(false);
                 obj1.mainframe.setVisible(true);
                 
             }
        }
        catch(Exception w)
        {
            System.out.println("Error");
        }
    
    }
}
class billnode
{
	String id,product;
	float price,quantity;
	public billnode(String id,float quantity,float price,String product)
	{
		this.id=id;
		this.quantity=quantity;
		this.price=price;
		this.product=product;
	}
}