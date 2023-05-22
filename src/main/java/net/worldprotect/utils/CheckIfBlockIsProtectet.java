package net.worldprotect.utils;

import net.worldprotect.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CheckIfBlockIsProtectet {
    List<ProtectedArea> protectedAreas;

    public CheckIfBlockIsProtectet(List<ProtectedArea> protectedAreas) {
        this.protectedAreas = protectedAreas;
        for (ProtectedArea protectedArea : protectedAreas) {
            System.out.println(protectedArea.location1().toString());
            System.out.println(protectedArea.location2().toString());
        }
    }

    public boolean check(Location location) {
        Location location1 = new Location(location.getWorld(), round(location.getX()), round(location.getY()), round(location.getZ()));
        Location location2 = new Location(location.getWorld(), round2(location.getX()), round2(location.getY()), round2(location.getZ()));
        Location location3 = new Location(location.getWorld(), round3(location.getX()), round3(location.getY()), round3(location.getZ()));
        boolean contains = false;

        for (ProtectedArea protectedArea : protectedAreas) {
            List<Location> locations = getBlocksBetweenLocations(protectedArea.location1(), protectedArea.location2());

            if (contains) {
                return contains;
            }

            if (contains(location1, locations)) {
                contains = true;
            }
            if (contains(location2, locations)) {
                contains = true;
            }if (contains(location3, locations)) {
                contains = true;
            }
        }

        return contains;
    }

    public void add(Location location1, Location location2) {
        ProtectedArea protectedArea = new ProtectedArea() {
            @Override
            public Location location1() {
                return location1;
            }

            @Override
            public Location location2() {
                return location2;
            }
        };

        if (!protectedAreas.contains(protectedArea)) {
            protectedAreas.add(protectedArea);
        }
    }

    public String remove(Location location1, Location location2) {
        for (ProtectedArea area: protectedAreas) {
            if (area.location1().equals(location1)) {
                if (area.location2().equals(location2)) {
                    protectedAreas.remove(area);
                    return "Success!";
                }
            }
        }
        return "That didn't work!";
    }


    public boolean contains(Location locationToCheck, List<Location> locations) {
        return locations.contains(locationToCheck);
    }

    private List<Location> getBlocksBetweenLocations(Location loc1, Location loc2) {
        World world = loc1.getWorld();
        List<Location> blocks = new ArrayList<>();
        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    blocks.add(new Location(world, x,y,z));
                }
            }
        }

        return blocks;
    }

    private int round(double d) {
        return (int) Math.round(d);
    }
    private int round2(double d) {
        return ((int) Math.round(d) - 1);
    }
    private int round3(double d) {
        return ((int) Math.round(d) + 1);
    }

    public void save() {
        Main.getInstace().getSaveAndLoad().save(protectedAreas);
    }

    public List<ProtectedArea> getProtectedAreas() {
        return protectedAreas;
    }
}
