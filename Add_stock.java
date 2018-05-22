package store_management;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class Add_stock implements ActionListener
{
    JFrame mainframe = new JFrame();  //frame for Search
    JComboBox X1,Y1,Z1;
    JButton btnAddstock,btnDelstock,btnBack;
    JTextField As;
     JComboBox C,P,C1,S;
    HashMap<String,hmnode> hm;
   
    public Add_stock(HashMap<String,hmnode> hm)
    {
    	this.hm=hm;
        mainframe.setLayout(new GridLayout());
        mainframe.setSize(600,300);
        mainframe.setTitle("STOCK");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        mainframe.setLocationRelativeTo(null);
        mainframe.setBackground(Color.WHITE);        

        //panel1 
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(10,10));
        panel1.setBackground(Color.WHITE);
        JLabel lblMWK = new JLabel("Categories");
        JLabel lblWBF = new JLabel("Sub-Categories");
        JLabel lblCFT = new JLabel("Type");
        JLabel lblcmp = new JLabel("Company");
        JLabel lblpro = new JLabel("Product");
        JLabel lblclr = new JLabel("Color");
        JLabel lblsize = new JLabel("Size");
        JLabel blank = new JLabel("");
      
        X1 = new JComboBox();
        X1.addItem("Men");
        X1.addItem("Women");
        X1.addItem("Kids");
        
        Y1 = new JComboBox();
        Y1.addItem("Top Wear");
        Y1.addItem("Bottom Wear");
        Y1.addItem("Foot Wear");
               
        Z1 = new JComboBox();
        Z1.addItem("Casual");
        Z1.addItem("Formal");
        Z1.addItem("Traditional");
              
        
        C = new JComboBox();
        C.addItem("teamspirit");
        C.addItem("lee");
        P = new JComboBox();
        P.addItem("tshirt");
        P.addItem("shirt");
        C1 = new JComboBox();
        C1.addItem("black");
        S = new JComboBox();
        S.addItem("M");
        As = new JTextField();
                   
        lblMWK.setForeground(Color.BLACK);
        lblWBF.setForeground(Color.BLACK);
        lblCFT.setForeground(Color.BLACK);
       
        panel1.add(lblMWK,BorderLayout.EAST);
        panel1.add(X1);
        panel1.add(lblWBF);
        panel1.add(Y1);
        panel1.add(lblCFT);
        panel1.add(Z1);
        panel1.add(lblcmp);
        panel1.add(C);
        panel1.add(lblpro);
        panel1.add(P);
        panel1.add(lblclr);
        panel1.add(C1);
        panel1.add(lblsize);
        panel1.add(S);
        
        //panel button
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.WHITE);
        
        btnAddstock = new JButton("Add Stock");
        btnDelstock = new JButton("Delete Stock");
        btnBack = new JButton("Back to previous frame");
        
        
        
        panel1.add(btnBack);
        panel1.add(As);
        panel1.add(btnAddstock);
        panel1.add(btnDelstock);
        
        mainframe.add(panel1);
        mainframe.setVisible(true);   
        btnAddstock.addActionListener(this);
        btnDelstock.addActionListener(this);
        btnBack.addActionListener(this);
        Z1.addActionListener(this);
        
    }
    public void actionPerformed(ActionEvent e) 
    {
        try
        {
            if(btnAddstock.getText().equals(e.getActionCommand()))
            { 
            	 Store s = new Store();
            	 s.change_stock(X1.getSelectedItem().toString(), Y1.getSelectedItem().toString(), Z1.getSelectedItem().toString(),hm,1,C.getSelectedItem().toString(),P.getSelectedItem().toString(),C1.getSelectedItem().toString(),S.getSelectedItem().toString(),Integer.parseInt(As.getText()));
                 mainframe.setVisible(false);
                 product_stock ps = new product_stock(hm);
                 ps.mainframe.setVisible(true);
            }
            else if(btnDelstock.getText().equals(e.getActionCommand()))
            { 
            	 Store s = new Store();
            	 s.change_stock(X1.getSelectedItem().toString(), Y1.getSelectedItem().toString(), Z1.getSelectedItem().toString(),hm,0,C.getSelectedItem().toString(),P.getSelectedItem().toString(),C1.getSelectedItem().toString(),S.getSelectedItem().toString(),Integer.parseInt(As.getText()));
                 product_stock ps = new product_stock(hm);
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