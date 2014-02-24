package Git;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Limit extends JavaPlugin implements Listener {
	
	static int pripojeni;
	
	public void onEnable() {
		
		getServer().getPluginManager().registerEvents(this, this);
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			public void run() {
				pripojeni = 0;
			}
		};
		timer.schedule(task, 1000, 1500);
		
	}
	
	public void onDisable() {
		
	}
	
	
	@EventHandler (priority = EventPriority.HIGHEST) 
	public void PreLogin(AsyncPlayerPreLoginEvent e) {
		
		pripojeni++;
		
		if (pripojeni > getConfig().getInt("limit")) {
			
			//e.disallow(Result.KICK_OTHER, "Server je momentalne pretizen, vyckejte prosim nekolik vterin.");
			
		}
		
	}
	
	
	public Boolean hasPerm(CommandSender p, String permission) {
		if (!(p instanceof Player)) return true;
		if (p.hasPermission("gt." + permission)) return true;
		return false;
	}
  
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		CommandSender ps = sender;
		Player p = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("limit")) {
			if(hasPerm(p, "admin")) {

				if (args.length == 1) {
					
					if (Integer.valueOf(args[0]) != 0) {
					getConfig().set("limit", Integer.valueOf(args[0]));
					saveConfig();
					p.sendMessage(ChatColor.AQUA + "Limit pripojeni na vterinu nastaven na: " + ChatColor.GREEN + Integer.valueOf(args[0]));
					} else p.sendMessage(ChatColor.RED + "Limit nemuze byt 0!");
				} else p.sendMessage(ChatColor.RED + "Spatne argumenty! Napis /limit int");
				
				
				
				return true;
			}
		
		
		
	}
	
		return false;
	}
	
	

}
