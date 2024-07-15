package db;

/** テーブルpokemonのデータを保持するクラス */
public class Pokemon {
    /** 番号 */
    private int id;
    /** 名前 */
    private String name;
    /** 攻撃力 */
    private int attack;
    /** 防御力 */
    private int defense;
    /** 体力 */
    private int stamina;
    /** 最大CP */
    private int cp;
    /** タイプ */
    private String type;

    /** コンストラクタ */
    public Pokemon(int id, String name, int attack, int defense, int stamina, int cp) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.stamina = stamina;
        this.cp = cp;
        this.type = "";
    }
    
    public Pokemon(int id, String name, int attack, int defense, int stamina, int cp, String type) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.stamina = stamina;
        this.cp = cp;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getStamina() {
        return stamina;
    }

    public int getCp() {
        return cp;
    }
    
    public String getType() {
        return type;
    }
}
