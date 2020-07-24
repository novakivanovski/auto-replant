package io.github.novakivanovski.autoreplant;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoReplantPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new CropBreakListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called");
    }

}