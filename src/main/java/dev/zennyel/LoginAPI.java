package dev.zennyel;

import dev.zennyel.database.MySQL;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public final class LoginAPI {

    private static Login login;

    static {
        login = Login.getInstance();
    }
    private static List<Player> loggedPlayers = new ArrayList<>();

    public static boolean loggedIn(Player player){
        return loggedPlayers.contains(player);
    }

    public static void login(Player player){
        loggedPlayers.add(player);
    }

    public void logout(Player player){
        loggedPlayers.remove(player);
    }

    public static List<Player> getLoggedPlayers() {
        return loggedPlayers;
    }

    public static void setLoggedPlayers(List<Player> loggedPlayers) {
        LoginAPI.loggedPlayers = loggedPlayers;
    }

    public static boolean areRegistered(Player player){
        return MySQL.registered(player);
    }

    public static String getPassword(Player player){
        return MySQL.getPassword(player);
    }

    public static void unRegister(Player player){
        MySQL.addPlayer(player, null);
    }

    public static void register(Player player, String senha){
        MySQL.addPlayer(player, senha);
    }

    public static void changePass(Player player, String senha){
        MySQL.attPlayer(player, senha);
    }

}
