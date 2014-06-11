package com.xorinc.personify.command;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xorinc.personify.PersonifyPlugin;
import com.xorinc.personify.data.BlockColorTuple;
import com.xorinc.personify.data.Pixel;
import com.xorinc.personify.data.PositionDataTuple;
import com.xorinc.personify.util.Util;

public class PersonifyCommand implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private PersonifyPlugin plugin;
	
	public static final int XDIM = 0, YDIM = 1, ZDIM = 2;
	
	public static final PositionDataTuple[] REGIONS = {
		new PositionDataTuple(8, 16, 0, 8, XDIM, ZDIM, 4, 31, 1),			// Head Top
		new PositionDataTuple(16, 24, 0, 8, XDIM, -ZDIM, 4, 24, 1),			// Head Bottom
		new PositionDataTuple(24, 32, 8, 16, XDIM, YDIM, 4, 24, 8, true),	// Head Back
		new PositionDataTuple(0, 8, 8, 16, -ZDIM, YDIM, 4, 24, 1),			// Head Left
		new PositionDataTuple(16, 24, 8, 16, ZDIM, YDIM, 11, 24, 1),		// Head Right
		new PositionDataTuple(8, 16, 8, 16, XDIM, YDIM, 4, 24, 1),			// Head Front
		new PositionDataTuple(40, 48, 0, 8, XDIM, ZDIM, 4, 32, 1),			// Hat Top
		new PositionDataTuple(56, 64, 8, 16, XDIM, YDIM, 4, 24, 9, true),	// Hat Back
		new PositionDataTuple(32, 40, 8, 16, -ZDIM, YDIM, 3, 24, 1),		// Hat Left
		new PositionDataTuple(48, 56, 8, 16, ZDIM, YDIM, 12, 24, 1),		// Hat Right
		new PositionDataTuple(40, 48, 8, 16, XDIM, YDIM, 4, 24, 0),			// Hat Front
		new PositionDataTuple(20, 28, 16, 20, XDIM, ZDIM, 4, 23, 3), 		// Body Top
		new PositionDataTuple(28, 36, 16, 20, XDIM, -ZDIM, 4, 12, 3),		// Body Bottom
		new PositionDataTuple(32, 40, 20, 32, XDIM, YDIM, 4, 12, 6, true), 	// Body Back
		new PositionDataTuple(16, 20, 20, 32, -ZDIM, YDIM, 4, 12, 3),		// Body Left
		new PositionDataTuple(28, 32, 20, 32, ZDIM, YDIM, 11, 12, 3),		// Body Right
		new PositionDataTuple(20, 28, 20, 32, XDIM, YDIM, 4, 12, 3),		// Body Front
		new PositionDataTuple(4, 8, 16, 20, XDIM, ZDIM, 4, 11, 3),			// Left Leg Top
		new PositionDataTuple(4, 8, 16, 20, XDIM, ZDIM, 8, 11, 3),			// Right Leg Top
		new PositionDataTuple(8, 12, 16, 20, XDIM, -ZDIM, 4, 0, 3),			// Left Leg Bottom
		new PositionDataTuple(8, 12, 16, 20, XDIM, -ZDIM, 8, 0, 3),			// Right Leg Bottom
		new PositionDataTuple(12, 16, 20, 32, XDIM, YDIM, 4, 0, 6, true),	// Left Leg Back
		new PositionDataTuple(12, 16, 20, 32, XDIM, YDIM, 8, 0, 6),			// Right Leg Back
		new PositionDataTuple(0, 4, 20, 32, -ZDIM, YDIM, 4, 0, 3),			// Legs Left
		new PositionDataTuple(8, 12, 20, 32, ZDIM, YDIM, 11, 0, 3),			// Legs Right
		new PositionDataTuple(4, 8, 20, 32, XDIM, YDIM, 4, 0, 3),			// Left Leg Front
		new PositionDataTuple(4, 8, 20, 32, XDIM, YDIM, 8, 0, 3, true),		// Right Leg Front
		new PositionDataTuple(44, 48, 16, 20, XDIM, ZDIM, 0, 23, 3),		// Left Arm Top
		new PositionDataTuple(44, 48, 16, 20, XDIM, ZDIM, 12, 23, 3),		// Right Arm Top
		new PositionDataTuple(48, 52, 16, 20, XDIM, -ZDIM, 0, 12, 3),		// Left Arm Bottom
		new PositionDataTuple(48, 52, 16, 20, XDIM, -ZDIM, 12, 12, 3),		// Right Arm Bottom
		new PositionDataTuple(52, 56, 20, 32, XDIM, YDIM, 0, 12, 6, true),	// Left Arm Back
		new PositionDataTuple(52, 56, 20, 32, XDIM, YDIM, 12, 12, 6),		// Right Arm Back
		new PositionDataTuple(40, 44, 20, 32, -ZDIM, YDIM, 0, 12, 3),		// Arms Left
		new PositionDataTuple(48, 52, 20, 32, ZDIM, YDIM, 15, 12, 3),		// Arms Right
		new PositionDataTuple(44, 48, 20, 32, XDIM, YDIM, 0, 12, 3),		// Left Arm Front
		new PositionDataTuple(44, 48, 20, 32, XDIM, YDIM, 12, 12, 3, true),	// Right Arm Front
	};
	
	
	public PersonifyCommand(PersonifyPlugin p){
		plugin = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "Console cannot perform this command.");
			return true;
		}
		if(!sender.hasPermission("personify.use")){
			sender.sendMessage(ChatColor.RED + "You do not have permission to do this.");
			return true;
		}
		if(args.length < 1){	
			sender.sendMessage(ChatColor.GOLD + "/personify [name]");
			return true;
		}
		
		sender.sendMessage(ChatColor.GOLD + "Made By Jack3885 For MCCENTRAL" + " " + ChatColor.Green + " He Should Be Made Dev With Vislo");
		
			
		try {
			Util.getPixels(args[0]);
		} 
		catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "Error: Requested is not downloaded. Download with /skinfetch [name]");
			return true;
		}
		Pixel[][] pixels = Util.getPixels(args[0]); 
		int lx, ly, lz;
		Pixel color;
		BlockColorTuple closest;
		final Location loc = ((Player) sender).getLocation();
		Block block;
		int invertedX;
		
		for(PositionDataTuple tup : REGIONS){
			lx = tup.getSx();
			ly = tup.getSy();
			lz = tup.getSz();
			
			if(tup.getDim1() == -ZDIM)
				lz = tup.getSz() + (tup.getXh() - tup.getXl()) - 1;
			if(tup.getDim2() == -ZDIM)
				lz = tup.getSz() + (tup.getYh() - tup.getYl()) - 1;
				invertedX = tup.getXh() - 1;
			for(int px = tup.getXl(); px < tup.getXh(); px++){	
				for(int py = tup.getYh() - 1; py > tup.getYl() - 1; py--){
					color = pixels[tup.isFlipped()?invertedX:px][py];	
					if(!color.isTransparent()){
					closest = Util.closestColor(color);	
					block = ((Player) sender).getWorld().getBlockAt(((int)loc.getX()) + (15-lx), ((int)loc.getY()) + (ly), ((int)loc.getZ()) + (lz));
					try {
						block.setTypeIdAndData(closest.getMat().getId(), closest.getData(), true);
					} catch (NullPointerException e) {
						plugin.getLogger().severe(e.getMessage());
						sender.sendMessage(ChatColor.RED + "Error at: " + (((int)loc.getX()) + (15-lx)) + " " + (((int)loc.getY()) + (ly)) +" "+ (((int)loc.getZ()) + (lz)) + " with color " + color);
					}
				}	
					if (tup.getDim2() == YDIM)
						ly++;
					if (tup.getDim2() == ZDIM)
						lz++;
					if (tup.getDim2() == -ZDIM)
						lz--;
				}
					

				if (tup.getDim2() == YDIM)
					ly = tup.getSy();
				if (tup.getDim2() == ZDIM)
					lz = tup.getSz();
				if (tup.getDim2() == -ZDIM)
					lz = tup.getSz() + tup.getYh() - tup.getYl() - 1;
					

				if (tup.getDim1() == XDIM)
					lx++;
				if (tup.getDim1() == ZDIM)
					lz++;
				if (tup.getDim1() == -ZDIM)
					lz--;
				
				invertedX--;		
			}		
		}
		return true;
	}
}