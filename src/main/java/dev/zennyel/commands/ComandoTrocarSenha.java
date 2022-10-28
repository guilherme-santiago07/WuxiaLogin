package dev.zennyel.commands;

import dev.zennyel.Login;
import dev.zennyel.LoginAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoTrocarSenha implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player p = (Player) sender;
        String senhaAntiga = LoginAPI.getPassword(p);

        if (!LoginAPI.loggedIn(p)) {
            p.sendMessage(Login.getTag() + "§cVocê precisa estar logado para isso!");
            return false;
        }

        if (args.length < 2) {
            p.sendMessage(Login.getTag() + "§cUse /trocarsenha <senhaantiga> <senhanova>");
            return false;
        }

        if (!args[0].equals(senhaAntiga)) {
            p.sendMessage(Login.getTag() + "§cSenha antiga incorreta, insira novamente");
            return false;
        }

        if (args[1].equals(senhaAntiga)) {
            p.sendMessage(Login.getTag() + "§cSua senha nova não pode ser igual a antiga!");
            return false;
        }

        if(args[1].length() < 4) {
            p.sendMessage(Login.getTag() + "§cSenha muito curta!");
            return false;
        }

        LoginAPI.changePass(p, args[1]);
        p.sendMessage(Login.getTag() + "§6Senha alterada com sucesso!");

        return false;
    }
}
