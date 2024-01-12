package com.skytechbytes.playerstatuebuilder.support;

import org.bukkit.plugin.RegisteredServiceProvider;

import com.skytechbytes.playerstatuebuilder.Log;
import com.skytechbytes.playerstatuebuilder.PlayerStatueBuilder;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class VaultWrapper {

    private Economy econ = null;
    private Permission perms = null;
    private Chat chat = null;
    private PlayerStatueBuilder p = PlayerStatueBuilder.instance;

    public VaultWrapper() throws Exception {
        if (!setupEconomy() ) {
            Log.log("Vault with economy provider not detected. You MUST have the Vault Plugin if you want PlayerStatueBuilderX to interact with the economy.");
            throw new Exception();
        }
        Log.log("Vault detected!");
    }
    
    private boolean setupEconomy() {
        if (p.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = p.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            Log.log("Vault detected, but no vault economy provider detected!");
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
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
