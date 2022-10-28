package dev.zennyel.database;

import dev.zennyel.Login;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class MySQL {

    public static void createTable(){
        try {
           Connection con = openConnection();
            PreparedStatement st = con.prepareStatement("CREATE TABLE IF NOT EXISTS login(id LONGTEXT,password LONGTEXT); ");
            st.executeUpdate();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean registered(Player player) {
        Boolean password = false;
        try {
            Connection con = openConnection();
            UUID id = player.getUniqueId();
            try {
                PreparedStatement st = con.prepareStatement("SELECT password FROM login WHERE id = ?;");
                st.setString(1, id.toString());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    password = true;
                }
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return password;
    }
    public static String getPassword(Player player){
        String password = "?";
        try {
            Connection con = openConnection();
            UUID id = player.getUniqueId();
            try {
                PreparedStatement st = con.prepareStatement("SELECT password FROM login WHERE id = ?;");
                st.setString(1, id.toString());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    password = rs.getString(1);
                }
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return password;
    }
    public static void addPlayer(Player player, String password){
        Connection con = openConnection();
        UUID id = player.getUniqueId();
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO `login`(`id`, `password`) VALUES (?,?);");
            st.setString(1, id.toString());
            st.setString(2, password);
            st.executeUpdate();
            con.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    public static void attPlayer(Player player, String password){
        Connection con = openConnection();
        UUID id = player.getUniqueId();
        try{
            PreparedStatement st = con.prepareStatement("UPDATE `login` SET `id`= ?,`password`= ? WHERE 1;");
            st.setString(1, id.toString());
            st.setString(2, password);
            st.executeUpdate();
            con.close();;
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static Connection openConnection() {
        String password = Login.getInstance().getConfig().getString("Sql.password");
        String user = Login.getInstance().getConfig().getString("Sql.user");
        String host = Login.getInstance().getConfig().getString("Sql.host");
        String port = Login.getInstance().getConfig().getString("Sql.port");
        String database = Login.getInstance().getConfig().getString("Sql.database");
        String type = "jdbc:mysql://";
        String url = type+host+":"+port+"/"+database;

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}




