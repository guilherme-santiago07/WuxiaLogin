package dev.zennyel.commands;

import dev.zennyel.Login;
import dev.zennyel.LoginAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoLogin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return false;

        String senha = args[0];
        Player p = (Player)sender;

        if(args.length < 1){
            p.sendMessage(Login.getTag() + "§6Use /login <senha> !");
            return false;
        }

        if(LoginAPI.loggedIn(p)) {
            p.sendMessage(Login.getTag() + " §6Você já está logado");
            return false;
            }

        if(!senha.equals(LoginAPI.getPassword(p))){
            p.sendMessage(Login.getTag() + "§cSenha errada, tente novamente!");
            return false;
        }
            LoginAPI.login(p);
            p.sendMessage(Login.getTag() + "§6Logado com sucesso!");


        return true;
    }
}
