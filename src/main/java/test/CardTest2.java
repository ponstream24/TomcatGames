package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CardTest2")
public class CardTest2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CardTest2() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 受信内容の文字コード
        response.setContentType("text/html; charset=UTF-8"); // 送信内容の文字コード
        // 受け取った内容を変数に入れる
        String suit = request.getParameter("suit");
        String number[] = request.getParameterValues("number");
        String color = request.getParameter("color");
        String query = request.getParameter("query");
        // コンソールへの出力
        System.out.println("--------");
        List<String> numberlist = null;
        if (number == null) {
            numberlist = new ArrayList<>();
        } else {
            numberlist = Arrays.asList(number);
        }
        System.out.println(numberlist);
        System.out.println("color=" + color);
        System.out.println("query=" + query);
        // ブラウザへの出力
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println(suit);
        out.println("<hr>");
        out.println(numberlist);
        out.println("<hr>");
        out.println(color);
        out.println("<hr>");
        out.println(query);
        out.println("<hr>");
        boolean suitFlag[] = new boolean[52];
        boolean numberFlag[] = new boolean[52];

        // 記号による選別(suitFlag[0..51]のうち，表示したカードをtrueにする)
        if (suit.equals("all")) {
            for (int i = 0; i <= 51; i++) {
                suitFlag[i] = true;
            }
        } else if (suit.equals("spade")) {
            for (int i = 0; i <= 12; i++) {
                suitFlag[i] = true;
            }
        } else if (suit.equals("heart")) {
            for (int i = 13; i <= 25; i++) {
                suitFlag[i] = true;
            }
        } else if (suit.equals("diamond")) {
            for (int i = 26; i <= 38; i++) {
                suitFlag[i] = true;
            }
        } else if (suit.equals("club")) {
            for (int i = 39; i <= 51; i++) {
                suitFlag[i] = true;
            }
        }

        // 数字による選別(numberFlag[0..51]のうち，表示したいカードをtrueにする)
        if (numberlist.contains("99")) {
            for (int i = 0; i <= 51; i++) {
                numberFlag[i] = true;
            }
        }
        for (String string : numberlist) {

            if (isNumber(string)) {
                int n = Integer.parseInt(string);

                if (n >= 0 && n <= 51) {
                    for (int i = n; i <= 51; i += 13) { // 13個おきにiを増やす
                        numberFlag[i] = true;
                    }
                }
            }
        }
        if (numberlist.contains("0")) {
            for (int i = 0; i <= 51; i += 13) { // 13個おきにiを増やす
                numberFlag[i] = true;
            }
        }
        // suitFlag[i]とnumberFlag[i]のどちらもtrueなら，iに対応するカードを表示する
        for (int i = 0; i <= 51; i++) {
            if (suitFlag[i] == true && numberFlag[i] == true) {
                String filename = "cards/" + i + ".png";
                out.printf("<img src=\"%s\" width=\"100\" height=\"150\">\n", filename);
            }
        }
        out.println("</html>");
    }

    public boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}