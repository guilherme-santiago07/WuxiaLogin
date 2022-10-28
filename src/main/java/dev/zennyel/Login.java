package dev.zennyel;

import dev.zennyel.commands.ComandoLogin;
import dev.zennyel.commands.ComandoRegistrar;
import dev.zennyel.commands.ComandoTrocarSenha;
import dev.zennyel.database.MySQL;
import dev.zennyel.events.EventoLogin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Login extends JavaPlugin{
    private static Login instance;
    private static String tag = "§6§l[WuxiaCraft] ";
    PluginManager pm = Bukkit.getPluginManager();

    public static Login getInstance() {
        return instance;
    }

    public static String getTag() {
        return tag;
    }

    @Override
    public void onEnable() {
        instance = this;
        loadConfiguration();
        registerCommands();
        registerEvents();
        MySQL.createTable();
    }
    @Override
    public void onDisable() {
    }
    public void registerCommands(){
        getCommand("Login").setExecutor(new ComandoLogin());
        getCommand("Registrar").setExecutor(new ComandoRegistrar());
        getCommand("trocarsenha").setExecutor(new ComandoTrocarSenha());
    }
    public void registerEvents(){
        pm.registerEvents(new EventoLogin(), this);;
    }

    public void loadConfiguration(){
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }

}
