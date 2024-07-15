package db;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Appear {

    private int id;
    private int number;
    private String name;
    private String ken;
    private String shi;
    private Date date;
    private Time time;
    private List<String> type;
   
    public Appear(int id, int number, String name, String ken, String shi, Date date, Time time) {
        super();
        this.id = id;
        this.number = number;
        this.name = name;
        this.ken = ken;
        this.shi = shi;
        this.date = date;
        this.time = time;
        
        this.type = new ArrayList<String>();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getKen() {
        return ken;
    }
    public void setKen(String ken) {
        this.ken = ken;
    }
    public String getShi() {
        return shi;
    }
    public void setShi(String shi) {
        this.shi = shi;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }
    
    
}