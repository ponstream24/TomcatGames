package db;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppearServlet")
public class AppearServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AppearServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String item = request.getParameter("item"); // 並び替えの項目
        String order = request.getParameter("order"); // asc:昇順 desc:降順
        String submit = request.getParameter("submit"); // "並び替え" "登録" "削除"
        String newnumber = request.getParameter("newnumber"); // 登録するポケモン番号
        String newshicode = request.getParameter("newshicode"); // 登録する市コード
        String deleteid = request.getParameter("deleteid"); // 削除するID
        String shimei = request.getParameter("shimei"); // 市名をクリックした場合
        String type = request.getParameter("type"); // 市名をクリックした場合
        System.out.printf("\n%s:%s:%s:\n", item, order, submit);
        System.out.printf("%s:%s:\n", newnumber, newshicode);
        System.out.printf("%s:%s:\n", deleteid, shimei);
        
        if (submit != null) {
            if (submit.equals("並び替え")) {
                // この場合は特に何もしない
            } 
            
            else if (submit.equals("登録")) {
                insert(newnumber, newshicode);
            } 
            
            else if (submit.equals("削除")) {
                delete(deleteid);
            }
        }
        
        selectAll(request, response, item, order, shimei, type);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/appear.jsp");
        dispatcher.forward(request, response);
    }

    /** サーブレットがPOSTメソッドで実行された場合でもdoGet()で処理する */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /** DAOを呼び出す */
    void selectAll(HttpServletRequest request, HttpServletResponse response, String item, String order, String shimei, String type)
            throws ServletException {
        AppearDAO appearDAO = new AppearDAO();
        List<Appear> list = appearDAO.findAll(item, order, shimei, type);
        request.setAttribute("list", list);
    }

    /** DAOを呼び出す */
    void insert(String newnumber, String newshicode) {
        
        
        int num, code;
        AppearDAO appearDAO = new AppearDAO();
        
        try {
            
            if( newnumber.equals("") ) {
                
                Random random = new Random();

                num = random.nextInt(386) + 1;
            }
            else num = Integer.parseInt(newnumber);
            
            if( newshicode.equals("") ) {
                
                Random random = new Random();

                int randomNumber = random.nextInt(1896);
                
                List<Integer> list = appearDAO.getCities();
                
                code = list.get(randomNumber);
            }
            
            else code = Integer.parseInt(newshicode);
            
            LocalDateTime nowDate = LocalDateTime.now();
            
            boolean success = appearDAO.insert(num, code, nowDate.getYear(), nowDate.getMonthValue(), nowDate.getDayOfMonth(), nowDate.getHour(), nowDate.getMinute(), nowDate.getSecond());
            
        } catch (NumberFormatException e) {
            System.out.println("不正な番号または市コードが入力されました" + e.getMessage());
        }
    }

    /** DAOを呼び出す */
    void delete(String deleteid) {
        try {
            int id = Integer.parseInt(deleteid);
            
            AppearDAO appearDAO = new AppearDAO();
            boolean success = appearDAO.delete(id);
            
        } catch (NumberFormatException e) {
            System.out.println("不正なIDが入力されました" + e.getMessage());
        }
    }
}
