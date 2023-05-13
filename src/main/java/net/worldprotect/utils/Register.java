package net.worldprotect.utils;

import net.worldprotect.Commands.AddAreaCommand;
import net.worldprotect.Commands.RemoveAreCommand;
import net.worldprotect.Listener.BlockPlaceBreakListener;
import net.worldprotect.Listener.ExplodeListener;
import net.worldprotect.Listener.InteractKillListener;
import net.worldprotect.Main;
import org.bukkit.Bukkit;

public class Register {

    public Register() {
        Main.getInstace().getCommand("addArea").setExecutor(new AddAreaCommand());
        Main.getInstace().getCommand("removeArea").setExecutor(new RemoveAreCommand());
        Main.getInstace().getCommand("removeArea").setTabCompleter(new RemoveAreCommand());
        Bukkit.getPluginManager().registerEvents(new BlockPlaceBreakListener(), Main.getInstace());
        Bukkit.getPluginManager().registerEvents(new InteractKillListener(), Main.getInstace());
        Bukkit.getPluginManager().registerEvents(new ExplodeListener(), Main.getInstace());
    }
}
