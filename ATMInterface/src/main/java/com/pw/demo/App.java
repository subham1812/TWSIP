package com.pw.demo;


import java.util.Scanner;

import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class App 
{
    public static void main( String[] args )  
    {
    	Scanner sc=new Scanner(System.in);
       Person p=new Person();
//       p.setBalance(2000);
//       p.setName("sachin");
//       p.setPin(107);
       Configuration cfg=new Configuration().configure().addAnnotatedClass(Person.class);
       SessionFactory sf=cfg.buildSessionFactory();
       Session s=sf.openSession();
       Transaction tx=s.beginTransaction();
       System.out.println("Enter 1 to withdraw cash,2 to deposit cash,3 to check balance");
       int n=sc.nextInt();
       if(n==1)
       {
    	   System.out.println("Enter PIN");
    	   int pin=sc.nextInt();
    	   p=s.get(Person.class, pin);
    	   if(p==null)
    	   {
    		   System.out.println("Record not found");
    	   }
    	   else
    	   {
    		   System.out.println("Enter Amount");
    		   int a=sc.nextInt();
    		   if(a>p.getBalance())
    		   {
    			   System.out.println("Amount not available");
    		   }
    		   else
    		   {
    			   System.out.println("Cash withdrawn successfully");
    			   int m=p.getBalance()-a;
    			   p.setBalance(m);
    		   }
    	   }
       }
       else if(n==2)
       {
    	   System.out.println("Enter PIN");
    	   int pin=sc.nextInt();
    	   p=s.get(Person.class, pin);
    	   if(p==null)
    	   {
    		   System.out.println("Record not found");
    	   }
    	   else
    	   {
    		   System.out.println("Enter Amount");
    		   int a=sc.nextInt();
    		   System.out.println("Cash deposited successfully");
    		   int m=p.getBalance()+a;
    		   p.setBalance(m);
    	   }
       }
       else if(n==3)
       {
    	   System.out.println("Enter PIN");
    	   int pin=sc.nextInt();
    	   p=s.get(Person.class, pin);
    	   if(p==null)
    	   {
    		   System.out.println("Record not found");
    	   }
    	   else
    	   {
    		   System.out.println(p.getBalance());
    	   }
       }
       else
       {
    	   System.out.println("Choose correct option");
       }
      // s.save(p);
       tx.commit();
       
      // System.out.println(p);
       
      }
}
