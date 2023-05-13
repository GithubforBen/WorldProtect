package net.worldprotect;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class SaveAndLoad {

    public void save(List<ProtectedArea> protectedAreas) {
        LoactionConfiguration loactionConfiguration = Main.getInstace().getLoactionConfiguration();

        loactionConfiguration.getConfiguration().set("areas.size", protectedAreas.size());

        for (int i = 0; i < protectedAreas.size(); i++) {
            loactionConfiguration.getConfiguration().set("areas." + String.valueOf(i) + ".loc1", protectedAreas.get(i).location1());
            loactionConfiguration.getConfiguration().set("areas." + String.valueOf(i) + ".loc2", protectedAreas.get(i).location2());
        }
    }

    public List<ProtectedArea> load() {
        LoactionConfiguration loactionConfiguration = Main.getInstace().getLoactionConfiguration();

        List<ProtectedArea> protectedAreaList = new ArrayList<>();

        for (int i = 0; i < loactionConfiguration.getConfiguration().getInt("areas.size"); i++) {
            int finalI = i;
            ProtectedArea protectedArea = new ProtectedArea() {
                @Override
                public Location location1() {
                    return loactionConfiguration.getConfiguration().getLocation("areas." + String.valueOf(finalI) + ".loc1");
                }

                @Override
                public Location location2() {
                    return loactionConfiguration.getConfiguration().getLocation("areas." + String.valueOf(finalI) + ".loc2");
                }
            };

            protectedAreaList.add(protectedArea);
        }
        return protectedAreaList;
    }
}
