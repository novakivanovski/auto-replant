package io.github.novakivanovski.autoreplant;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoReplantPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called");
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called");
    }

}