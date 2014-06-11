package com.xorinc.personify;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

//msg me on enjin for the missing java file(This is to prevent everyone from having my plugin) name Is jack3885

import com.xorinc.personify.command.PersonifyCommand;
import com.xorinc.personify.command.SkinFetchCommand;

public class PersonifyPlugin extends JavaPlugin {
	
	private static PersonifyPlugin instance;
	private HashMap<String, BufferedImage> skins = new HashMap<String, BufferedImage>();
	private static boolean enabled = false; 
		
	public void onEnable(){
		instance = this;
		//Great Dev Jack3885
		getCommand("personify").setExecutor(new PersonifyCommand(this));	
		getCommand("skinfetch").setExecutor(new SkinFetchCommand(this));
	}
	
	public void onDisable(){
		
	}
	
	public static PersonifyPlugin getInstance(){
		return instance;
	}
	
	public void put(String k, BufferedImage v){
		skins.put(k, v);
	}
	
	public BufferedImage get(String k){
		return skins.get(k);
	}
	
	
}
