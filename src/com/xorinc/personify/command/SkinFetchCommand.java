package com.xorinc.personify.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.xorinc.personify.PersonifyPlugin;
import com.xorinc.personify.task.SkinFetcherAsyncTask;

public class SkinFetchCommand implements CommandExecutor{
	
	private PersonifyPlugin plugin;
	
	public SkinFetchCommand(PersonifyPlugin p){
		plugin = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("personify.download")){
			sender.sendMessage(ChatColor.RED + "You do not have permission to do this.");
			return true;
		}
		if(args.length < 1){
			sender.sendMessage(ChatColor.GOLD + "/skinfetch [name]");
		}
			
		SkinFetcherAsyncTask task = new SkinFetcherAsyncTask(args[0], sender);
		PersonifyPlugin.getInstance().getServer().getScheduler().runTaskAsynchronously(PersonifyPlugin.getInstance(), task);
		return true;
	}

}
