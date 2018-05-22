/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.management;
import java.util.*;
//import javafx.scene.Node;

class node{
    
    public String company,product,color,siz,id;
    public int price,quantity;
    public node next;
    
    public node(String company,String product,String color,String siz,int price,int quantity,String id)
    {
        this.company=company;
        this.product=product;
        this.color=color;
        this.siz=siz;
        this.price=price;
        this.quantity=quantity;
        this.id=id;
        
    }
   public void display()
    {
        System.out.println(company+"    "+product+"    "+color+"    "+siz+" "+price+"    "+quantity+"   "+id);
    }
}
class data{
    public node fnode,lnode;
    public data()
    {
        fnode=null;
        lnode=null;
    }
    public void insertnode(String company,String product,String color,String siz,int price,int quantity,String id)  //To insert a node
    {
        node obj = new node(company,product,color,siz,price,quantity,id);
        obj.next=null;
        if(fnode==null)
        {
            fnode=obj;
            lnode=obj;
        }
        else
        {
            lnode.next=obj;
            lnode=obj;
            System.out.println("data inserted successfully");
        }
    }
    void dis(node node1)   //To display
    {
        System.out.println("The sorted data:");
        while(node1!=null)
        {
            node1.display();   //function call
            node1=node1.next;
        }
    }
    public void bubble_sort(node node)
    {
        while(node!=null)
        {
           node node1 = node.next;
           while(node1!=null)
           {
               if(node.price>node1.price)
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
                   
                   int temp1 = node.quantity;
                   node.quantity = node1.quantity;
                   node1.quantity = temp1;
                    
                   temp1 = node.price;
                   node.price = node1.price;
                   node1.price = temp1;
                   
                   temp = node.id;
                   node.id = node1.id;
                   node1.id = temp;
               
               }
               node1 = node1.next;
            }
           node = node.next;
        }
    }
   public node merge_sort(node fnode)
   {
       if(fnode==null||fnode.next==null)
       {
           return fnode;
       }
       int count=0;
       node p = fnode;
       while(p!=null)
       {
           count++;
           p=p.next;
       }
       int middle = count/2;
       node l = fnode,r=null;
       node p2 = fnode;
       int countHalf = 0;
       while(p2!=null)
       {
           countHalf++;
           node next = p2.next;
           if(countHalf==middle)
           {
               p2.next=null;
               r = next;
           }
           p2 = next;
       }
       node h1 = merge_sort(l);
       node h2 = merge_sort(r);
       node merged = merge(h1,h2);
       return merged;
   }
   
   public node merge(node l, node r)
   {
       node p1 = l;
       node p2 = r;
       node fake = new node("levis","jeans","c","d",1,2,"e");
       node pnew = fake;
       while(p1 != null || p2 != null)
       
       {
           if (p1 == null)
           {
               pnew.next = new node(p2.company,p2.product,p2.color,p2.siz,p2.price,p2.quantity,p2.id);
               p2 = p2.next;
	       pnew = pnew.next;
           }
           else if (p2 == null) 
           {
               pnew.next = new node(p1.company,p1.product,p1.color,p1.siz,p1.price,p1.quantity,p1.id);
               p1 = p1.next;
               pnew = pnew.next;
           }
           else
           {
               if (p1.price < p2.price) 
               {
                // if(fakeHead)
                    pnew.next = new node(p1.company,p1.product,p1.color,p1.siz,p1.price,p1.quantity,p1.id);
                    p1 = p1.next;
                    pnew = pnew.next;
		}
               else if (p1.price == p2.price) 
               {
                    pnew.next = new node(p1.company,p1.product,p1.color,p1.siz,p1.price,p1.quantity,p1.id);
                    pnew.next.next = new node(p1.company,p1.product,p1.color,p1.siz,p1.price,p1.quantity,p1.id);
                    pnew = pnew.next.next;
                    p1 = p1.next;
                    p2 = p2.next;
 
		}
               else
               {
                   pnew.next = new node(p2.company,p2.product,p2.color,p2.siz,p2.price,p2.quantity,p2.id);
                   p2 = p2.next;
                   pnew = pnew.next;
		}
				
	    }
       }
       return fake.next;
       
   }
}
public class sort 
{   
    public static void main(String[] args) 
    {
  
        int choice;
        data obj1 = new data();
      Scanner sc = new Scanner(System.in);
      obj1.insertnode("peter England","tshirt","red","M",50,15,"mptr1");
      obj1.insertnode("Biba","kurti","blue","L",100,20,"bkb1");
      obj1.insertnode("Jealous21","top","black","S",1000,8,"bkb1");
      obj1.insertnode("Rig","kurti","pink","XL",350,10,"bkb1");
      obj1.dis(obj1.fnode);
      do
      {
        System.out.println("\n\t\tMENU\t\t\n");
        System.out.println("1.Bubble Sort");
        System.out.println("2.Merge Sort");
        System.out.println("0.Exit");
        System.out.println("Enter your choice: ");
        choice = sc.nextInt();
      
         switch(choice)
        {
            case 1:
                  obj1.bubble_sort(obj1.fnode);
                  obj1.dis(obj1.fnode);
                  break;
            case 2:
                 obj1.fnode = obj1.merge_sort(obj1.fnode);
                  obj1.dis(obj1.fnode);
                  break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice!!!!");
        }
      }while(choice!=0);
    }   
}
