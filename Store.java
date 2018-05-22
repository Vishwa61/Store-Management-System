package store_management;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

class hmnode
{
    String company;
    String product;
    String color;
    String siz;
    float price;
    float quantity;
    String id;
    hmnode next=null;
    public hmnode(String Company,String Product,String Color,String Siz,float Price,float Quantity,String Id)
    {
        this.company=Company;
        this.product=Product;
        this.color=Color;
        this.siz=Siz;
        this.price=Price;
        this.quantity=Quantity;
        this.id=Id;
    }
}
public class Store 
{

	public void AddProduct(String x1,String y1,String z1,String company,String product,String color,String size,float price,float quantity)  
	{
		try
		{
			File file = new File("E:\\store.xls");
			FileInputStream fread = null;
			fread = new FileInputStream(file);

			Workbook wb = WorkbookFactory.create(fread);
			Sheet sh = wb.getSheet("Sheet1");
			
			int rlast = sh.getLastRowNum();
			
			Row row = sh.createRow(rlast+1);
			Cell cell = row.createCell(0);
			cell = sh.getRow(rlast+1).getCell(0);
			cell.setCellValue(x1);
			cell = row.createCell(1);
			cell = sh.getRow(rlast+1).getCell(1);
			cell.setCellValue(y1);
			cell = row.createCell(2);
			cell = sh.getRow(rlast+1).getCell(2);
			cell.setCellValue(z1);
			cell = row.createCell(3);
			cell = sh.getRow(rlast+1).getCell(3);
			cell.setCellValue(company);
			cell = row.createCell(4);
			cell = sh.getRow(rlast+1).getCell(4);
			cell.setCellValue(product);
			cell = row.createCell(5);
			cell = sh.getRow(rlast+1).getCell(5);
			Sheet sh2 = wb.getSheet("Sheet2");
			int rlast2 = sh.getLastRowNum()+1;
			for(int i=0;i<rlast2;i++)
			{
				Row row2 = sh2.getRow(i);
				Cell cell2 = row2.getCell(1);
				if(cell2.toString().equals(color))
				{
					cell2 = row2.getCell(0);
					color = cell2.toString();
				}
			}
			cell.setCellValue(color);
			cell = row.createCell(6);
			cell = sh.getRow(rlast+1).getCell(6);
			cell.setCellValue(size);
			cell = row.createCell(7);
			cell = sh.getRow(rlast+1).getCell(7);
			cell.setCellValue(price);
			cell = row.createCell(8);
			cell = sh.getRow(rlast+1).getCell(8);
			cell.setCellValue(quantity);
			cell = row.createCell(9);
			cell = sh.getRow(rlast+1).getCell(9);
			cell.setCellValue(price*quantity);
			cell = row.createCell(10);
			cell = sh.getRow(rlast+1).getCell(10);
			cell.setCellValue(x1.substring(0,1)+y1.substring(0,1)+z1.substring(0,1)+company.substring(0,1)+product.substring(0,1)+color+size+price);
		
			System.out.print(cell.getStringCellValue()+"  ");
			fread.close();
			FileOutputStream fwrite = null;
			fwrite = new FileOutputStream(file);
			wb.write(fwrite);
			fwrite.close();	
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
	}
	public boolean delete(String searchValue,hmnode head)
	{
	    hmnode tmpNode = head;
	    hmnode prevNode = null;
	    boolean deletedANode = false;

	    while (tmpNode != null) 
	    {
	        if (tmpNode.id.equals(searchValue)) 
	        {
	            if (tmpNode == head) 
	            {
	                head = head.next;
	            } 
	            else
	            {
	                prevNode.next = tmpNode.next;
	            }
	            deletedANode = true;
	        } 
	        else 
	        {
	        	prevNode = tmpNode;
	        }
	        tmpNode = tmpNode.next;
	    }
	    return deletedANode;
	}
	public void DelProduct(String id,HashMap<String,hmnode> hm)
	{
		hmnode n=hm.get(id.substring(0,3));
		delete(id,n);
		File file = new File("E:\\store.xls");
		FileInputStream fread = null;
		try 
		{
			fread = new FileInputStream(file);
			Workbook wb;
			wb = WorkbookFactory.create(fread);
			Sheet sh = wb.getSheet("Sheet1");
			int rn=sh.getLastRowNum()+1;
			for(int i=0;i<rn;i++)
			{
				Row row = sh.getRow(i);
				Cell cell = row.getCell(10);
				if(id.equals(cell.toString()))
					sh.removeRow(row);
			}
			fread.close();
			FileOutputStream fwrite = null;
			fwrite = new FileOutputStream(file);
			wb.write(fwrite);
			fwrite.close();	
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (InvalidFormatException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

	public hmnode searchById(String sid,HashMap<String,hmnode> hm)
	{
		
		hmnode shm=hm.get(sid.substring(0,3));
		while(shm!=null)
		{
			if(shm.id.matches(sid))
			{
				System.out.println(shm.company+"  "+shm.product+"  "+shm.color+"  "+shm.siz+"  "+shm.price+"  "+shm.quantity);
				break;
			}
			shm=shm.next;
		}
		return shm;
	}

	public void makehm(HashMap<String,hmnode> hm)
	{
        FileInputStream fis;
		
		try {
		
			String fn = "E:\\store.xls";
			fis = new FileInputStream(fn);
			Workbook wb;
			wb = new HSSFWorkbook(fis);
			Sheet sheet = wb.getSheet("Sheet1");
			int rn = sheet.getLastRowNum()+1;
			int cn = sheet.getRow(0).getLastCellNum();
			System.out.println(rn);
			System.out.println(cn);
			String[][] data= new String[rn][cn];
        	for(int i=0;i<rn;i++)
			{
				Row row = sheet.getRow(i);
				for(int j=0;j<cn;j++)
				{
					Cell cell = row.getCell(j);
					String s1 = cell.toString();
					if(j==5)
					{
						Sheet sheet2 = wb.getSheet("Sheet2");
						int rn2=sheet2.getLastRowNum()+1;
						for(int k=0;k<rn2;k++)
						{
							Row row2 = sheet2.getRow(k);
							Cell cell2 = row2.getCell(0);
							if(s1.equals(cell2.toString()))
							{
								s1=row2.getCell(1).toString();
							}
						}
					}
					data[i][j]=s1;
				}
			}
            wb.close();
            fis.close();
             hmnode ptr;
            for(int i=0;i<rn;i++)
            {
            	ptr = new hmnode(data[i][3],data[i][4],data[i][5],data[i][6],Float.parseFloat(data[i][7]),Float.parseFloat(data[i][8]),data[i][10]);
            	ptr.next=hm.get(ptr.id.substring(0, 3));
            	hm.put(ptr.id.substring(0, 3),ptr);
            }
     	} 
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}
	public void change_stock(String x,String y,String z,HashMap<String,hmnode> hm,int add_del,String com,String pro,String col,String siz,int stock)
	{
		x=x.substring(0, 1);
		y=y.substring(0, 1);
		z=z.substring(0, 1);
		hmnode shm=hm.get(x+y+z);
		File file = new File("E:\\store.xls");
		FileInputStream fread = null;
		Workbook wb=null;
		try {
			fread = new FileInputStream(file);
			wb = WorkbookFactory.create(fread);
			Sheet sh = wb.getSheet("Sheet1");
			while(shm!=null)
			{
				if(shm.company.equals(com) && shm.product.equals(pro) && shm.color.equals(col) && shm.siz.equals(siz))
				{
					if(add_del==1)
						shm.quantity=shm.quantity+stock;
					else
						shm.quantity=shm.quantity-stock;
					
					break;
				}
				shm=shm.next;
			}
	
			int rn = sh.getLastRowNum();
			for(int i=0;i<=rn;i++)
			{
				Row row = sh.getRow(i);
				Cell cell = row.getCell(10);
				if(shm.id.equals(cell.toString()))
				{
					cell = row.getCell(8);
					cell.setCellValue(shm.quantity);
					cell = row.getCell(9);
					cell.setCellValue(shm.price*shm.quantity);
				}
			}
			fread.close();
			FileOutputStream fwrite = null;
			fwrite = new FileOutputStream(file);
			wb.write(fwrite);
			fwrite.close();	
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvalidFormatException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public void bubble_sort(hmnode node,String key,int flag)
    {
    	hmnode node2=node;
        while(node!=null)
        {
           hmnode node1 = node.next;
           while(node1!=null)
           {
        	   if(flag==1)
        	   {
        		   if(node.price<node1.price)
        		   {
        			   String temp = node.company;
        			   node.company = node1.company;
        			   node1.company = temp;
                   
        			   temp = node.product;
        			   node.product = node1.product;
        			   node1.product = temp;
                   
        			   temp = node.color;
        			   node.color = node1.color;
        			   node1.color = temp;
                   
        			   temp = node.siz;
        			   node.siz = node1.siz;
        			   node1.siz = temp;
                   
        			   float temp1 = node.quantity;
        			   node.quantity = node1.quantity;
        			   node1.quantity = temp1;
                    
        			   temp1 = node.price;
        			   node.price = node1.price;
        			   node1.price = temp1;
                   
        			   	temp = node.id;
        			   	node.id = node1.id;
        			   	node1.id = temp;
        		
        		   	}
        	   	}
        	   	else
        	   	{
        	   		if(node.quantity<node1.quantity)
         		   	{
         			   	String temp = node.company;
         			   	node.company = node1.company;
         			   	node1.company = temp;
                    
         			   	temp = node.product;
         			   	node.product = node1.product;
         			   	node1.product = temp;
                    
         			   	temp = node.color;
         			   	node.color = node1.color;
         			   	node1.color = temp;
                    
         			   	temp = node.siz;
         			   	node.siz = node1.siz;
         			   	node1.siz = temp;
                    
         			   	float temp1 = node.quantity;
         			   	node.quantity = node1.quantity;
         			   	node1.quantity = temp1;
                     
         			   	temp1 = node.price;
         			   	node.price = node1.price;
         			   	node1.price = temp1;
                    
         			   	temp = node.id;
         			   	node.id = node1.id;
         			   	node1.id = temp;
                
         		   	}
         	   }
        	   node1 = node1.next;
            }
           node = node.next;
        }
        while(node2!=null)
        {
        	System.out.println(node2.company+"\t\t"+node2.product+"\t\t"+node2.color+"\t\t"+node2.siz+"\t\t"+node2.price+"\t\t"+node2.quantity+"\t\t"+node2.id);
        	node2=node2.next;
        }
    }
    public hmnode merge_sort(hmnode fnode,int flag)
    {
    	if(fnode==null||fnode.next==null)
        {
            return fnode;
        }
        int count=0;
        hmnode p = fnode;
        while(p!=null)
        {
            count++;
            p=p.next;
        }
        int middle = count/2;
        hmnode l = fnode,r=null;
        hmnode p2 = fnode;
        int countHalf = 0;
        while(p2!=null)
        {
            countHalf++;
            hmnode next = p2.next;
            if(countHalf==middle)
            {
                p2.next=null;
                r = next;
            }
            p2 = next;
        }
        hmnode h1 = merge_sort(l,flag);
        hmnode h2 = merge_sort(r,flag);
        hmnode merged = merge(h1,h2,flag);
        
        return merged;
    }
    
    public hmnode merge(hmnode l,hmnode r,int flag)
    {
        hmnode p1 = l;
        hmnode p2 = r;
        hmnode fake = new hmnode("levis","jeans","c","d",1,2,"e");
        hmnode pnew = fake;
        while(p1 != null || p2 != null)
        
        {
            if (p1 == null)
            {
                pnew.next = new hmnode(p2.company,p2.product,p2.color,p2.siz,p2.price,p2.quantity,p2.id);
                p2 = p2.next;
 	       pnew = pnew.next;
            }
            else if (p2 == null) 
            {
                pnew.next = new hmnode(p1.company,p1.product,p1.color,p1.siz,p1.price,p1.quantity,p1.id);
                p1 = p1.next;
                pnew = pnew.next;
            }
            else
            {
            	if(flag==1)
            	{
            		if (p1.price < p2.price) 
            		{
            			pnew.next = new hmnode(p1.company,p1.product,p1.color,p1.siz,p1.price,p1.quantity,p1.id);
            			p1 = p1.next;
            			pnew = pnew.next;
            		}
            		else if (p1.price == p2.price) 
            		{
            			pnew.next = new hmnode(p1.company,p1.product,p1.color,p1.siz,p1.price,p1.quantity,p1.id);
            			pnew.next.next = new hmnode(p1.company,p1.product,p1.color,p1.siz,p1.price,p1.quantity,p1.id);
            			pnew = pnew.next.next;
            			p1 = p1.next;
            			p2 = p2.next;
            		}
            		else
            		{
            			pnew.next = new hmnode(p2.company,p2.product,p2.color,p2.siz,p2.price,p2.quantity,p2.id);
            			p2 = p2.next;
            			pnew = pnew.next;
            		}
            	}
            	else
            	{
            		if (p1.quantity < p2.quantity) 
                    {
                     // if(fakeHead)
                         pnew.next = new hmnode(p1.company,p1.product,p1.color,p1.siz,p1.price,p1.quantity,p1.id);
                         p1 = p1.next;
                         pnew = pnew.next;
                    }
                    else if (p1.quantity == p2.quantity) 
                    {
                         pnew.next = new hmnode(p1.company,p1.product,p1.color,p1.siz,p1.price,p1.quantity,p1.id);
                         pnew.next.next = new hmnode(p1.company,p1.product,p1.color,p1.siz,p1.price,p1.quantity,p1.id);
                         pnew = pnew.next.next;
                         p1 = p1.next;
                         p2 = p2.next;
      
                    }
                    else
                    {
                        pnew.next = new hmnode(p2.company,p2.product,p2.color,p2.siz,p2.price,p2.quantity,p2.id);
                        p2 = p2.next;
                        pnew = pnew.next;
                    }
            	}
            }
        }
        return fake.next;
        
    }
    public void low_to_high(HashMap<String,hmnode> hm,int flag)
    {
    	ArrayList<String> lh = new ArrayList<String>(hm.keySet());
    	for(int i=0;i<lh.size();i++)
    	{
    		hmnode node2=merge_sort((hm.get(lh.get(i))),flag);
    		while(node2!=null)
            {
            	System.out.println(node2.company+"\t\t"+node2.product+"\t\t"+node2.color+"\t\t"+node2.siz+"\t\t"+node2.price+"\t\t"+node2.quantity+"\t\t"+node2.id);
            	node2=node2.next;
            }
    	}
    }
    public void high_to_low(HashMap<String,hmnode> hm,int flag)
    {
    	ArrayList<String> hl = new ArrayList<String>(hm.keySet());
    	for(int i=0;i<hl.size();i++)
    	{
    		bubble_sort((hm.get(hl.get(i))),hl.get(i),flag);
    	}
    }
    public void generate_bill(ArrayList<billnode> al)
    {
    	int alsiz = al.size();
    	float total_price=0;
    	System.out.println("\t\tBILL\t\t");
    	System.out.println("Product\t\tQuantity\t\tPrice");
    	for(int i=0;i<alsiz;i++)
    	{
    		total_price+=al.get(i).quantity*al.get(i).price;
    		System.out.println(al.get(i).product+"\t\t"+al.get(i).quantity+"\t\t"+al.get(i).price);
    	}
    	System.out.println("\t\t\t\tTotal  "+total_price);
    }
    public void display_all()
    {
		File file = new File("E:\\store.xls");
    	FileInputStream fread = null;
		try 
		{
			fread = new FileInputStream(file);
			Workbook wb;
			wb = WorkbookFactory.create(fread);
			Sheet sh = wb.getSheet("Sheet1");
			int rn=sh.getLastRowNum()+1;
			int cn = sh.getRow(0).getLastCellNum();
			for(int i=0;i<rn;i++)
			{
				Row row = sh.getRow(i);
				for(int j=0;j<cn;j++)
				{
					Cell cell = row.getCell(j);
					String s1 = cell.toString();
					if(j==5)
					{
						Sheet sheet2 = wb.getSheet("Sheet2");
						int rn2=sheet2.getLastRowNum()+1;
						for(int k=0;k<rn2;k++)
						{
							Row row2 = sheet2.getRow(k);
							Cell cell2 = row2.getCell(0);
							if(s1.equals(cell2.toString()))
							{
								s1=row2.getCell(1).toString();
							}
						}
					}
					System.out.print(s1+"\t\t");
				}
				System.out.println();
			}
			fread.close();
			FileOutputStream fwrite = null;
			fwrite = new FileOutputStream(file);
			wb.write(fwrite);
			fwrite.close();	
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (InvalidFormatException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
    }
}
