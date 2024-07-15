package db;

import java.util.List;

/** PokemonDAOを使ってテーブルpokemonの全てのデータを表示する */
public class Main {
    void allPokemon() {
        PokemonDAO pokemonDAO = new PokemonDAO();
        List<Pokemon> list = pokemonDAO.findAll();
        for (Pokemon p : list) {
            int id = p.getId();
            String name = p.getName();
            int attack = p.getAttack();
            int defense = p.getDefense();
            int stamina = p.getStamina();
            int cp = p.getCp();
            System.out.printf("%3d %-10s %3d %3d %3d %4d\n",
                    id, name, attack, defense, stamina, cp);
        }
    }

    void pikachu() {
        PokemonDAO pokemonDAO = new PokemonDAO();
        List<Pokemon> list = pokemonDAO.pikachu();
        
        System.out.println("攻撃力\t防御力\t体力");
        
        if( list.size() != 0 ) {
            Pokemon p = list.get(0);
            int attack = p.getAttack();
            int defense = p.getDefense();
            int stamina = p.getStamina();
            System.out.printf("%3d\t%3d\t%3d\n",
                    attack, defense, stamina);
        }
    }
    
    void threeLetters() {
        PokemonDAO pokemonDAO = new PokemonDAO();
        List<Pokemon> list = pokemonDAO.threeLetters();
        
        for (Pokemon p : list) {
            int id = p.getId();
            String name = p.getName();
            System.out.println(name);
        }
    }
    
    void max() {
        PokemonDAO pokemonDAO = new PokemonDAO();
        List<Pokemon> list = pokemonDAO.max();

        System.out.println(" MAX(攻撃力)  MAX(防御力)  MAX(体力)");
        
        if( list.size() != 0 ) {
            Pokemon p = list.get(0);
            int attack = p.getAttack();
            int defense = p.getDefense();
            int stamina = p.getStamina();
            System.out.printf("%3d\t%3d\t%3d\n",
                    attack, defense, stamina);
        }
    }
    
    void denki() {
        PokemonDAO pokemonDAO = new PokemonDAO();
        List<Pokemon> list = pokemonDAO.denki();
        for (Pokemon p : list) {
            int id = p.getId();
            String name = p.getName();
            int attack = p.getAttack();
            int defense = p.getDefense();
            int stamina = p.getStamina();
            int cp = p.getCp();
            System.out.printf("%3d %-10s %3d %3d %3d %4d\n",
                    id, name, attack, defense, stamina, cp);
        }
    }

    public static void main(String args[]) {
        Main main = new Main();
        main.allPokemon();
        main.pikachu();
        main.threeLetters();
        main.max();
        main.denki();
    }
}