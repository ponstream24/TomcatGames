package poker;

import static poker.Util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerModel {

    /** ゲーム回数 */
    int games;
    /** 現在のチップ数 初期値は500 */
    int chips;
    int oldChips = 500;
    /** 山札 */
    List<Integer> deckcards;
    /** 手札 */
    List<Integer> handcards;
    /** 送信ボタンに表示する文字列("交換","次のゲーム"のどちらか */
    String buttonLabel;
    /** プレイヤーへのメッセージ */
    String message;
    int count[];

    /** コンストラクタ */
    public PokerModel() {
        deckcards = new ArrayList<>();
        handcards = new ArrayList<>();
    }

    /** 一連のゲームを開始する */
    public void reset() {
        games = 0;
        chips = 500;
    }

    /** 次のゲームのためにカードを配りなおす */
    public void nextgame() {
        // 52枚の山札を作る
        deckcards.clear();
        for (int i = 0; i <= 51; i++) {
            deckcards.add(i);
        }

        // シャッフル
        Collections.shuffle(deckcards);

        // 山札の先頭から5枚抜いて手札にする
        handcards.clear();
        for (int i = 0; i < 5; i++) {
            int n = deckcards.remove(0);
            handcards.add(n);
        }
        //        // 残りの場札の枚数とカード番号をコンソールに表示する
        //        System.out.printf("%d: ", deckcards.size());
        //        for (int n : deckcards) {
        //            System.out.printf("%d ", n);
        //        }
        message = "交換するカードをクリックしてください";
        buttonLabel = "交換";
        games++;
        oldChips = chips;
    }

    /** JSPから呼び出されるメソッド */
    public int getGames() {
        return games;
    }

    /** 現在のチップ数を返す */
    public int getChips() {
        return chips;
    }

    public int getOldChips() {
        return oldChips;
    }

    /** 5枚の手札のうち，i番目のカード番号を返す (i=0～4) */
    public int getHandcardAt(int i) {
        return handcards.get(i);
    }

    /** 送信ボタンのラベルを返す */
    public String getButtonLabel() {
        return buttonLabel;
    }

    /** プレイヤーへのメッセージを返す */
    public String getMessage() {
        return message;
    }

    /** indexで指定された位置のカードを、山札から補充したカードを置き換える */
    public void change(List<String> index) {
        for (String str : index) {

            // もし数字なら
            if (isNumber(str)) {
                int i = Integer.parseInt(str);
                int c = deckcards.remove(0);
                handcards.set(i, c);
            } else {
                System.out.println(str + " is not number!");
            }
        }
        //        for (int i = 0; i < 5; i++) {
        //            int c = deckcards.remove(0); // 山札の先頭を取り除き、
        //            handcards.set(i, c); // 手札の指定場所に入れる
        //        }
        evaluate();
        buttonLabel = "次のゲーム";
      
    }

    /** 役の判別を行い、チップを増減させる */
    public void evaluate() {
        System.out.println(countSameNumber(handcards));
        System.out.println(handcards.get(0)+","+  handcards.get(1)+","+  handcards.get(2)+","+  handcards.get(3)+","+ handcards.get(4));
        countNumber();
        int red = countRed();
        int seven = countSeven();
        int point = 0;

        int two = 0;
        int three = 0;

        if( isLoyalStraightFlush(handcards) ) {
            message = "ロイヤルストレートフラッシュ";
            point = 1000;
        }
        else if( isStraightFlush(handcards) ) {
            message = "ストレートフラッシュ";
            point = 750;
        }
        else if( isQuads(handcards) ) {
            message = "フォーカード";
            point = 500;
        }
        else if( isFullHouse(handcards) ) {
            message = "フルハウス";
            point = 250;
        }
        else if( isFlush(handcards) ) {
            message = "フラッシュ";
            point = 200;
        }
        else if( isStraight(handcards) ) {
            message = "ストレート";
            point = 150;
        }
        else if( isTrips(handcards) ) {
            message = "スリーカード";
            point = 100;
        }
        else if( isTwoPairs(handcards) ) {
            message = "ツーペア";
            point = 50;
        }
        else if( isOnePair(handcards) ) {
            message = "ワンペア";
            point = 20;
        } 
        else if (red == 5) {
            message = "レッド";
            point = 15;
        } 
        else if (seven > 0) {
            message = "セブン";
            point = 70;
        } 
        else {
            message = "ハイカード";
            point = -200;
        }
        
        oldChips = chips;
        chips += point;
        message += ": " + point;
        
    }

    /** 数字毎に手札の枚数を数える */
    void countNumber() {
        count = new int[13];

        for (int n : handcards)
            count[n % 13]++;
    }

    /** 7 の枚数を返す */
    int countSeven() {

        int c = 0;

        for (int n : handcards) {

            if (n % 13 == 6)
                c++;
        }

        return c;
    }

    /** 赤の枚数を返す */
    int countRed() {

        int count = 0;

        for (int n : handcards) {

            if (n >= 13 && n < 39)
                count++;
        }
        return count;
    }

    /** 数字できるか確認 **/
    public static boolean isNumber(String str) {
        try {
            int number = Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** 手札をセットする（テスト用） */
    public void setHandcards(int a, int b, int c, int d, int e) {
        handcards.set(0, a);
        handcards.set(1, b);
        handcards.set(2, c);
        handcards.set(3, d);
        handcards.set(4, e);
    }
}