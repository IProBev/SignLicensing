package me.IProBev.SignLicensing;


import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener{
	
	public static Bukkit plugin;
	
	//-----------------//
	//Setting up Vault.//
	//-----------------//
	public static Economy economy = null;

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	
	@Override
	public void onEnable() {
		if(!setupEconomy()){
			getLogger().severe("PLUGIN REQUIRES VAULT, ERROR STARTING!");
			Bukkit.getPluginManager().disablePlugin(this);
		}else{
			getLogger().info("SignLicensing Has Been Enabled");
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
	}
	@Override
	public void onDisable() {
		getLogger().info("SignLicensing Has Been Disabled");
	}
	
	@EventHandler
	public void onSignCreate(SignChangeEvent sign){
		Player player = sign.getPlayer();
		String Prefix = ChatColor.BLACK + "[" + ChatColor.AQUA + "SignLicenseing" + ChatColor.BLACK + "]" + ChatColor.RED;
		if(sign.getLine(0).equalsIgnoreCase("LBuy")){
			if(player.hasPermission("LSign.create") || player.isOp()){
				sign.setLine(0, "&0[&6LBuy&0]");
				player.sendMessage(Prefix + "Sign Created sucessfully!");
			}
			}else{
				sign.setCancelled(true);
				player.sendMessage(Prefix + "You DO NOT have permission to do that!");
			}
		}
	

}



