package db;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class AppearMain {
    
    void insert(int number, int shicode) {
        AppearDAO appearDAO = new AppearDAO();
        boolean success = appearDAO.insert(number, shicode, 2003, 8,24,8,24,13);
        System.out.println(success);
    }
    void delete(int number) {
        AppearDAO appearDAO = new AppearDAO();
        boolean success = appearDAO.delete(number);
        System.out.println(success);
    }
    
    void allAppear() {
        AppearDAO appearDAO = new AppearDAO();
        List<Appear> list = appearDAO.findAll();
        for (Appear p : list) {
            int id = p.getId();
            int number = p.getNumber();
            String name = p.getName();
            String ken = p.getKen();
            String shi = p.getShi();
            Date date = p.getDate();
            Time time = p.getTime();
            System.out.printf("%3d\t%3d\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\n",
                    id, number, name, ken, shi, date, time);
        }
    }

    public static void main(String args[]) {
        AppearMain main = new AppearMain();
//        main.insert(6, 12206);
//        main.insert(6, 11230);
//        main.insert(6, 11229);
//        main.insert(6, 12204);
        main.allAppear();
    }
}