package poker;

import java.util.Arrays;
import java.util.List;

public class Util {

    // カード番号からスートに変換
    public static int getSuit(int a) {

        return a / 13;
    }
    
    // カード番号から数字に変換
    public static int getNumber(int a) {
        return a % 13;
    }
    
    // スートの一致を確認
    public static boolean suitCheck(int a, int b) {
        
        return getSuit(a) == getSuit(b);
    }

    // 各数字の数字を数える
    public static int countSameNumber(List<Integer> cards) {
        int count = 0;
        for(int i = 0; i < 5; ++i) {
            for(int j = i + 1; j < 5; ++j) {
                if (getNumber(cards.get(i)) == getNumber(cards.get(j)))
                    ++count;
            }
        }
        return count;
    }
    
    public static boolean isLoyal(List<Integer> cards) {
        int[] list = { getNumber(cards.get(0)), getNumber(cards.get(1)), getNumber(cards.get(2)), getNumber(cards.get(3)), getNumber(cards.get(4)) };
        
        for (int i : list) {
            
            if( i != 1 && i < 9 ) return false;
        }
        
        return true;
    }
    

    public static boolean isFlush( List<Integer> cards) {
        for (int i = 0; i < 4; ++i) {
            if (getSuit(cards.get(i)) != getSuit(cards.get(i+1)))
                return false;
        }
        return true;
    }
    
    public static boolean isQuads(List<Integer> cards) {
        return countSameNumber(cards) == 10;
    }

    public static boolean isFullHouse(List<Integer> cards) {
        return countSameNumber(cards) == 4;
    }

    public static boolean isTrips(List<Integer> cards) {
        return countSameNumber(cards) == 3;
    }

    public static boolean isTwoPairs(List<Integer> cards) {
        return countSameNumber(cards) == 2;
    }

    public static boolean isOnePair(List<Integer> cards) {
        return countSameNumber(cards) == 1;
    }
    
    public static boolean isStraight(List<Integer> cards) {
        int[] list = { getNumber(cards.get(0)), getNumber(cards.get(1)), getNumber(cards.get(2)), getNumber(cards.get(3)), getNumber(cards.get(4)) };
        Arrays.sort(list);
        for (int i = 0; i < 3; ++i) {
            if (list[i + 1] - list[i] != 1)
                return false;
        }
        return list[4] - list[3] == 1 || (list[3] == 5 && list[4] == 14);
    }
    
    public static boolean isStraightFlush(List<Integer> cards) {
        return isFlush(cards) && isStraight(cards);
    }
    
    public static boolean isLoyalStraightFlush(List<Integer> cards) {
        int[] list = { getNumber(cards.get(0)), getNumber(cards.get(1)), getNumber(cards.get(2)), getNumber(cards.get(3)), getNumber(cards.get(4)) };
        Arrays.sort(list);
        if( 
                list[0] == 0  &&
                list[1] == 9  &&
                list[2] == 10  &&
                list[3] == 11  &&
                list[4] == 12
        ) return true;
        
        return false;
    }
}
