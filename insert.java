package store_management;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;
public class insert  implements ActionListener
{
    JFrame mainframe = new JFrame();  //frame for Search
    JComboBox X1,Y1,Z1;
    JButton btninsert,btnBack,btndeletestock;
    HashMap<String,hmnode> hm;
    JTextField txtcmp,txtpro,txtclr,txtsize,txtprc,txtqua,txtdel; 
    public void Insert(HashMap<String,hmnode> hm)
    {
        this.hm=hm;
        mainframe.setLayout(new GridLayout());
        mainframe.setSize(600,400);
        mainframe.setTitle("INSERT");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        mainframe.setLocationRelativeTo(null);
        mainframe.setBackground(Color.BLACK);        

        //panel1 
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(12,12));
        panel1.setBackground(Color.WHITE);
        
        JLabel lblMWK = new JLabel("Category");
        JLabel lblWBF = new JLabel("Sub-category");
        JLabel lblCFT = new JLabel("Type");
        JLabel lblcmp = new JLabel("Company");
        JLabel lblpro = new JLabel("Product");
        JLabel lblclr = new JLabel("Color");
        JLabel lblsize = new JLabel("Size");
        JLabel lblprc = new JLabel("Price");
        JLabel lblqua = new JLabel("Quantity");
        
       txtcmp = new JTextField();
         txtpro = new JTextField();
           txtclr = new JTextField();
            txtsize = new JTextField();
             txtprc = new JTextField();
             txtqua = new JTextField();
        txtdel = new JTextField();
             
      
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
        panel1.add(txtcmp);
        panel1.add(lblpro);
        panel1.add(txtpro);
        panel1.add(lblclr);
        panel1.add(txtclr);
        panel1.add(lblsize);
        panel1.add(txtsize);
        panel1.add(lblprc);
        panel1.add(txtprc);
        panel1.add(lblqua);
        panel1.add(txtqua);     
        
  
       
        btninsert = new JButton("Insert");
        btnBack = new JButton("Back to previous frame");
        btndeletestock = new JButton("Delete");
        btnBack.addActionListener(this);
        btninsert.addActionListener(this);
        btndeletestock.addActionListener(this);
        panel1.add(btninsert);
        panel1.add(btnBack);
        panel1.add(txtdel);
        panel1.add(btndeletestock);
        
        
        mainframe.add(panel1);
        mainframe.setVisible(true);        
    }
    
        public void actionPerformed(ActionEvent e) 
    {
        try
        {
            if(btninsert.getText().equals(e.getActionCommand()))
            { 
            	 Store s = new Store();
                 s.AddProduct(X1.getSelectedItem().toString(),Y1.getSelectedItem().toString(),Z1.getSelectedItem().toString(),txtcmp.getText(),txtpro.getText(),txtclr.getText(),txtsize.getText(),Float.parseFloat(txtprc.getText()),Float.parseFloat(txtqua.getText()));
                 mainframe.setVisible(false);
                 product_stock  obj1 = new product_stock(hm);
                 obj1.mainframe.setVisible(true);
            }
            else if(btnBack.getText().equals(e.getActionCommand()))
            {   
            	product_stock  obj1 = new product_stock(hm);
                mainframe.setVisible(false);
                obj1.mainframe.setVisible(true);
            }   
            else if(btndeletestock.getText().equals(e.getActionCommand()))
            {
            	 Store s = new Store();
            	 s.DelProduct(txtdel.getText(), hm);
            	 mainframe.setVisible(false);
                 product_stock  obj1 = new product_stock(hm);
                 obj1.mainframe.setVisible(true);
            } 
        }
        catch(Exception w)
        {
            System.out.println("Error");
        }
    
}
}