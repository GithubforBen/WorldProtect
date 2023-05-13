package net.worldprotect;

import net.worldprotect.utils.CheckIfBlockIsProtectet;
import net.worldprotect.utils.LoactionConfiguration;
import net.worldprotect.utils.Register;
import net.worldprotect.utils.SaveAndLoad;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instace;
    private LoactionConfiguration loactionConfiguration;

    private CheckIfBlockIsProtectet checkIfBlockIsProtectet;
    private SaveAndLoad saveAndLoad;

    @Override
    public void onLoad() {
        instace = this;
    }

    @Override
    public void onEnable() {
        loactionConfiguration = new LoactionConfiguration();
        saveAndLoad = new SaveAndLoad();
        checkIfBlockIsProtectet = new CheckIfBlockIsProtectet(saveAndLoad.load());
        new Register();
    }

    @Override
    public void onDisable() {
        checkIfBlockIsProtectet.save();
        loactionConfiguration.save();
    }

    public static Main getInstace() {
        return instace;
    }

    public LoactionConfiguration getLoactionConfiguration() {
        return loactionConfiguration;
    }

    public CheckIfBlockIsProtectet getCheckIfBlockIsProtectet() {
        return checkIfBlockIsProtectet;
    }

    public SaveAndLoad getSaveAndLoad() {
        return saveAndLoad;
    }
}
