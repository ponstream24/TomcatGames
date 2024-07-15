package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/** テーブルappear用のDAO */
public class AppearDAO {
    /** プログラム起動時に一度だけ実行される */
    static {
        try {
            Class.forName("org.h2.Driver"); // JDBC Driverの読み込み
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 1件のデータを追加する．成功ならtrueを返す． */
    public boolean insert(int number, int shicode) {
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "INSERT INTO appear(番号,市コード) VALUES(?,?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, number);
            pre.setInt(2, shicode);
            int result = pre.executeUpdate();
            if (result == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /** 1件のデータを日時を指定して追加する．成功ならtrueを返す． */
    public boolean insert(int number, int shicode,
            int year, int month, int day, int hour, int minute, int second) {
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "INSERT INTO appear(番号,市コード,日付,時刻) VALUES(?,?,?,?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            String datestr = String.format("%4d-%02d-%02d", year, month, day);
            Date date = Date.valueOf(datestr);
            String timestr = String.format("%02d:%02d:%02d", hour, minute, second);
            Time time = Time.valueOf(timestr);
            pre.setInt(1, number);
            pre.setInt(2, shicode);
            pre.setDate(3, date);
            pre.setTime(4, time);
            ;
            int result = pre.executeUpdate();
            if (result == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean delete(int id) {
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "DELETE FROM appear WHERE ID=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            int result = pre.executeUpdate();
            if (result == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public List<Appear> findAll() {
        return findAll("ID", "asc", null, null);
    }

    public List<Appear> findAll(String item, String type, String shimei, String pokemonType) {

        if (item == null)
            item = "ID";
        if (type == null)
            type = "asc";

        List<Appear> list = new ArrayList<>();
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "SELECT APPEAR.ID, APPEAR.番号, POKEMON.名前, KEN.県名, SHI.市名, APPEAR.日付, APPEAR.時刻, タイプ\n"
                    + "FROM APPEAR\n"
                    + "INNER JOIN SHI ON APPEAR.市コード = SHI.市コード\n"
                    + "INNER JOIN KEN ON SHI.県コード = KEN.県コード\n"
                    + "INNER JOIN POKEMON ON POKEMON.番号 = APPEAR.番号\n"
                    + "INNER JOIN TYPE ON APPEAR.番号 = TYPE.番号\n";

            if (shimei != null && !shimei.equalsIgnoreCase("null")) {
                sql += "WHERE SHI.市名 = \'" + shimei + "\'\n";
            }
            if (pokemonType != null && !pokemonType.equalsIgnoreCase("null")) {
                sql += "WHERE タイプ = \'" + pokemonType + "\'\n";
            }
            sql += "ORDER BY " + item + " " + type + ";";
            PreparedStatement pre = conn.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int number = rs.getInt("番号");
                String name = rs.getString("名前");
                String ken = rs.getString("県名");
                String shi = rs.getString("市名");
                Date date = rs.getDate("日付");
                Time time = rs.getTime("時刻");
                String t = rs.getString("タイプ");

                Appear appear = hasAppear(list, id);

                if (appear == null) {
                    appear = new Appear(id, number, name, ken, shi, date, time);
//                    appear.getType().add(t);
                    appear.setType(getTypeByPokemon(number));
                    list.add(appear);
                }
                else {
//                    appear.getType().add(t);
                }
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

    public static List<String> getShimeiList() {
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        List<String> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "SELECT SHI.市名\n"
                    + "FROM APPEAR\n"
                    + "INNER JOIN SHI ON APPEAR.市コード = SHI.市コード\n"
                    + "GROUP BY SHI.市名;";
            PreparedStatement pre = conn.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String shi = rs.getString("市名");
                list.add(shi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static List<Integer> getCities() {

        String url = "jdbc:h2:tcp://localhost/./s2232103";
        List<Integer> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "SELECT 市コード\n"
                    + "FROM SHI;";
            PreparedStatement pre = conn.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int shi = rs.getInt("市コード");
                list.add(shi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static List<String> getTypeList() {
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        List<String> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "SELECT TYPE.タイプ\n"
                    + "FROM APPEAR\n"
                    + "INNER JOIN TYPE ON APPEAR.番号 = TYPE.番号\n"
                    + "GROUP BY TYPE.タイプ;";
            PreparedStatement pre = conn.prepareStatement(sql);

            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String type = rs.getString("タイプ");
                list.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    private Appear hasAppear(List<Appear> list, int id) {

        for (Appear appear : list) {

            if (appear.getId() == id)
                return appear;
        }

        return null;
    }

    public static List<String> getTypeByPokemon(int number) {
        String url = "jdbc:h2:tcp://localhost/./s2232103";
        List<String> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "user", "pass");
            String sql = "SELECT *\n"
                    + "    FROM TYPE\n"
                    + "    WHERE 番号 = ?;";
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, number);
            
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String type = rs.getString("タイプ");
                list.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}