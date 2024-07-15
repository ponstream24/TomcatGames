package db;

public class Test {
    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            System.out.println("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
