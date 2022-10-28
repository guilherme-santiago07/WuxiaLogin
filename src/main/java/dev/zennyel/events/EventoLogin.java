package dev.zennyel.events;

import dev.zennyel.Login;
import dev.zennyel.LoginAPI;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
public class EventoLogin implements Listener {

    @EventHandler
    public void onLogin(PlayerJoinEvent e) throws InterruptedException {
        Player p = (Player) e.getPlayer();
        p.setGameMode(GameMode.SURVIVAL);

        if(!LoginAPI.areRegistered(p)) {
            p.sendMessage(Login.getTag() + "§6Por favor registre-se: /registrar <senha> <senha>!");
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BELL, 10, 10);
            return;
        }

            p.sendMessage(Login.getTag() +"§6Por favor logue-se: /login <senha>");
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BELL, 10, 10);

        new BukkitRunnable(){
            public void run(){
                if(!LoginAPI.loggedIn(p)) {
                    p.kickPlayer("§cVocê demorou demais para entrar!");
                }}}.runTaskLater(Login.getInstance(), 20*15);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void dontMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(!LoginAPI.loggedIn(p)){
            p.teleport(e.getFrom());
        }
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void takeDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player)e.getEntity();
            if(!LoginAPI.loggedIn(p)){
                e.setCancelled(true);
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void openInventory(InventoryOpenEvent e){
        Player p = (Player)e.getPlayer();
        if(!LoginAPI.loggedIn(p)){
            e.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void itemMove(InventoryDragEvent e){
        Player p = (Player) e.getWhoClicked();
        if(!LoginAPI.loggedIn(p)){
            e.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void placeBlock(BlockPlaceEvent e){
        Player p = (Player) e.getPlayer();
        if(!LoginAPI.loggedIn(p)){
            e.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void itemUse(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(!LoginAPI.loggedIn(p)){
            e.setCancelled(true);
        }

    }

}
