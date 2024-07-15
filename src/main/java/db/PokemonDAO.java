package db;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** テーブルpokemon用のDAO */
public class PokemonDAO {
    /** プログラム起動時に一度だけ実行される */
    static {
        try {
            Class.forName("org.h2.Driver"); // JDBC Driverの読み込み
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Pokemon> findAll() {
        List<Pokemon> list = new ArrayList<>();
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "SELECT * FROM pokemon";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("番号");
                String name = rs.getString("名前");
                int attack = rs.getInt("攻撃力");
                int defense = rs.getInt("防御力");
                int stamina = rs.getInt("体力");
                int cp = rs.getInt("最大CP");
                Pokemon p = new Pokemon(id, name, attack, defense, stamina, cp);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return list;
    }

    public List<Pokemon> pikachu() {
        List<Pokemon> list = new ArrayList<>();
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "SELECT 攻撃力,防御力,体力 FROM pokemon WHERE 名前='ピカチュウ';";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int attack = rs.getInt("攻撃力");
                int defense = rs.getInt("防御力");
                int stamina = rs.getInt("体力");
                Pokemon p = new Pokemon(0, "", attack, defense, stamina, 0);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return list;
    }

    public List<Pokemon> threeLetters() {
        List<Pokemon> list = new ArrayList<>();
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "SELECT 名前 FROM pokemon WHERE CHAR_LENGTH(名前)=3;";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String name = rs.getString("名前");
                Pokemon p = new Pokemon(0, name, 0, 0, 0, 0);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return list;
    }

    public List<Pokemon> max() {
        List<Pokemon> list = new ArrayList<>();
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "SELECT MAX(攻撃力),MAX(防御力),MAX(体力) FROM pokemon";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int attack = rs.getInt("MAX(攻撃力)");
                int defense = rs.getInt("MAX(防御力)");
                int stamina = rs.getInt("MAX(体力)");
                Pokemon p = new Pokemon(0, "", attack, defense, stamina, 0);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return list;
    }

    public List<Pokemon> denki() {
        List<Pokemon> list = new ArrayList<>();
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "SELECT pokemon.番号,名前,攻撃力,防御力,体力,最大CP,タイプ FROM pokemon\n"
                    + "JOIN type ON pokemon.番号=type.番号 WHERE タイプ = 'でんき';";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("番号");
                String name = rs.getString("名前");
                int attack = rs.getInt("攻撃力");
                int defense = rs.getInt("防御力");
                int stamina = rs.getInt("体力");
                int cp = rs.getInt("最大CP");
                String type = rs.getString("タイプ");
                Pokemon p = new Pokemon(id, name, attack, defense, stamina, cp, type);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return list;
    }
//    
//    public List<Pokemon> pikachu2() {
//        List<Pokemon> list = new ArrayList<>();
//        
//        HashMap<String, Array> runSQL = runSQL("SELECT 攻撃力,防御力,体力 FROM pokemon WHERE 名前='ピカチュウ';");
//        
//        if( runSQL == null ) return null;
//        
//        for (String key : runSQL.keySet()) {
//            
//            int attack = rs.getInt("攻撃力");
//            int defense = rs.getInt("防御力");
//            int stamina = rs.getInt("体力");
//            Pokemon p = new Pokemon(0, "", attack, defense, stamina, 0);
//            list.add(p);
//        }
//        
//        String url = "jdbc:h2:tcp://localhost/./s2232103";
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url, "user", "pass");
//            String sql = "SELECT 攻撃力,防御力,体力 FROM pokemon WHERE 名前='ピカチュウ';";
//            PreparedStatement pre = conn.prepareStatement(sql);
//            ResultSet rs = pre.executeQuery();
//            while (rs.next()) {
//                int attack = rs.getInt("攻撃力");
//                int defense = rs.getInt("防御力");
//                int stamina = rs.getInt("体力");
//                Pokemon p = new Pokemon(0, "", attack, defense, stamina, 0);
//                list.add(p);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            }
//        }
//        return list;
//    }

//    private static HashMap<String, Array> runSQL(String sql) {
        private static List<Pokemon> runSQL(String sql) {
        
        List<Pokemon> list = new ArrayList<>();

        HashMap<String, Array> resultHashMap = new HashMap<String, Array>();
        
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url, "user", "pass");
//            PreparedStatement pre = conn.prepareStatement(sql);
//            ResultSet rs = pre.executeQuery();
//            ResultSetMetaData metaData = rs.getMetaData();
//            
//            int columnCount = metaData.getColumnCount();
//            while (rs.next()) {
//                for (int i = 1; i <= columnCount; i++) {
//                    String columnName = metaData.getColumnName(i);
//                    Array columnValue = rs.getArray(0);
//                    resultHashMap.put(columnName, columnValue);
//                }
//            }
//        } 
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                
                for (int i = 0; i < rs.getFetchSize(); i++) {
                    Object array_element = rs.getObject(i);
                    
                    System.out.println( i + " : " + array_element.toString());
                }
//                int id = rs.getInt("番号");
//                String name = rs.getString("名前");
//                int attack = rs.getInt("攻撃力");
//                int defense = rs.getInt("防御力");
//                int stamina = rs.getInt("体力");
//                int cp = rs.getInt("最大CP");
//                String type = rs.getString("タイプ");
//                Pokemon p = new Pokemon(id, name, attack, defense, stamina, cp, type);
//                list.add(p);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
//        return resultHashMap;
        return null;
    }
    public static void main(String[] args) {
        
        List<Pokemon> sql = runSQL("SHOW DATABASES;");
        
        for (Pokemon str : sql) {
            
            System.out.println(str.getName());
        }
    }
}
