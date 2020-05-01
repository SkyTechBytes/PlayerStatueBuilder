package com.skytechbytes.support;

import org.bukkit.plugin.RegisteredServiceProvider;

import com.skytechbytes.testplugin.Log;
import com.skytechbytes.testplugin.PlayerStatuePlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class VaultWrapper {

    private Economy econ = null;
    private Permission perms = null;
    private Chat chat = null;
    private PlayerStatuePlugin p = PlayerStatuePlugin.instance;

    public VaultWrapper() throws Exception {
        if (!setupEconomy() ) {
            Log.log("Vault not detected. You MUST have the Vault Plugin if you want PlayerStatueBuilderX to interact with the economy.");
            throw new Exception();
        }
        //setupPermissions();
        //setupChat();
        Log.log("Vault detected!");
    }
    
    private boolean setupEconomy() {
        if (p.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = p.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
//    private boolean setupChat() {
//        RegisteredServiceProvider<Chat> rsp = p.getServer().getServicesManager().getRegistration(Chat.class);
//        chat = rsp.getProvider();
//        return chat != null;
//    }
//    
//    private boolean setupPermissions() {
//        RegisteredServiceProvider<Permission> rsp = p.getServer().getServicesManager().getRegistration(Permission.class);
//        perms = rsp.getProvider();
//        return perms != null;
//    }
    
//    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
//        if(!(sender instanceof Player)) {
//            log.info("Only players are supported for this Example Plugin, but you should not do this!!!");
//            return true;
//        }
//        
//        Player player = (Player) sender;
//        
//        if(command.getLabel().equals("test-economy")) {
//            // Lets give the player 1.05 currency (note that SOME economic plugins require rounding!)
//            sender.sendMessage(String.format("You have %s", econ.format(econ.getBalance(player.getName()))));
//            EconomyResponse r = econ.depositPlayer(player, 1.05);
//            if(r.transactionSuccess()) {
//                sender.sendMessage(String.format("You were given %s and now have %s", econ.format(r.amount), econ.format(r.balance)));
//            } else {
//                sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
//            }
//            return true;
//        } else if(command.getLabel().equals("test-permission")) {
//            // Lets test if user has the node "example.plugin.awesome" to determine if they are awesome or just suck
//            if(perms.has(player, "example.plugin.awesome")) {
//                sender.sendMessage("You are awesome!");
//            } else {
//                sender.sendMessage("You suck!");
//            }
//            return true;
//        } else {
//            return false;
//        }
//    }
    
    public Economy getEconomy() {
        return econ;
    }
    
    public Permission getPermissions() {
        return perms;
    }
    
    public Chat getChat() {
        return chat;
    }
}
