package dev.zennyel.commands;

import dev.zennyel.Login;
import dev.zennyel.LoginAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComandoRegistrar implements CommandExecutor {

    @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if (!(sender instanceof Player))
                return false;

            final Player player = (Player) sender;

            if (LoginAPI.areRegistered(player)) {
                player.sendMessage(Login.getTag() + "§cVocê ja está registrado!");
                return false;
            }

            if (args.length != 2) {
                player.sendMessage(Login.getTag() + "§cUso incorreto, use: /registrar <senha> <senha>.");
                return false;
            }

            final String firstPassword = args[0];
            final String confirmPassword = args[1];

            if(firstPassword.length() < 4) {
                player.sendMessage(Login.getTag() + "§cSenha muito curta!");
                return false;
            }

            if (!(firstPassword.equals(confirmPassword))){
                player.sendMessage(Login.getTag() + "§cAs senhas estão diferentes");
                return false;
            }

            LoginAPI.register(player, firstPassword);
            player.sendMessage(Login.getTag() + "§6Você foi registrado com sucesso");
            LoginAPI.login(player);

            return true;
        }
    }

