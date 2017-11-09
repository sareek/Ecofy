/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Timestamp;
import java.util.Date;
/**
 *
 * @author sareek
 */
public class ItemsM2 {
    private int id;
    private String place, complain,image;
    private Float average;
//    private byte[] image;
    java.sql.Timestamp datetime = new java.sql.Timestamp(new java.util.Date().getTime());
    java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
    
   
   
    
    public ItemsM2(int id, String place, Float avg_rating, String complain, String image,Date date)
    {
        this.id=id;
        this.place=place;
        this.average=avg_rating;
        this.complain=complain;
        this.image=image;
        this.date=(java.sql.Date) date;
       
    }

  
    
    
    public int getid(){
        return id;
    }
    public String getplace(){
        return place;
    }
     public Float getaverage(){
        return average;
    }
      public String getcomplain(){
        return complain;
    }
    
      public String getimage(){
        
        return image;
    }
        public Date getdate(){
        return date;
    }
    
    
     
     
    
}
