package net.worldprotect.Commands;

import net.md_5.bungee.api.ChatColor;
import net.worldprotect.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AddAreaCommand implements CommandExecutor, TabCompleter {

    private String usage() {
        return ChatColor.DARK_GRAY + "Use \"" + ChatColor.DARK_BLUE.toString() + ChatColor.BOLD + "x1 y1 z1 x2 y2 z2" + ChatColor.RESET.toString() + ChatColor.DARK_GRAY + "\".";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 6) {
            sender.sendMessage(usage());
            return false;
        }

        for (String s : args) {
            try {
                Integer.parseInt(s);
            }catch (Exception e) {
                sender.sendMessage(usage());
                return false;
            }
        }

        int x1 = Integer.parseInt(args[0]);
        int y1 = Integer.parseInt(args[1]);
        int z1 = Integer.parseInt(args[2]);

        int x2 = Integer.parseInt(args[3]);
        int y2 = Integer.parseInt(args[4]);
        int z2 = Integer.parseInt(args[5]);

        Player player = (Player) sender;

        Location location1 = new Location(player.getWorld(), x1,y1,z1);
        Location location2 = new Location(player.getWorld(), x2,y2,z2);

        Main.getInstace().getCheckIfBlockIsProtectet().add(location1, location2);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> strings = new ArrayList<>();

        if (!(sender instanceof Player)) {
            return null;
        }

        Player player = (Player) sender;

        strings.add(toString(player.getLocation().getBlockX()) +
                toString(player.getLocation().getBlockY()) +
                toString(player.getLocation().getBlockZ())
        );
        return strings;
    }

    private String toString(int i) {
        return i + " ";
    }
}
