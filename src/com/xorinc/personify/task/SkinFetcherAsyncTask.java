package com.xorinc.personify.task;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.xorinc.personify.PersonifyPlugin;

public 	class SkinFetcherAsyncTask implements Runnable{
	public BufferedImage skin;
	public boolean isDownloaded = false;
	private String name;
	private CommandSender sender;
	
	public SkinFetcherAsyncTask(String s, CommandSender sn){
		name = s;
		sender = sn;
	}
	
	public void run(){
		URL url;
		try {
			url = new URL("http://s3.amazonaws.com/MinecraftSkins/" + name + ".png");
			URLConnection conn = url.openConnection();
			skin = ImageIO.read(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
			sender.sendMessage(ChatColor.GOLD + "Download failed.");
			return;
		}
		PersonifyPlugin.getInstance().put(name, skin);
		sender.sendMessage(ChatColor.GOLD + "Download succesful.");
		isDownloaded = true;
		
	}
}
