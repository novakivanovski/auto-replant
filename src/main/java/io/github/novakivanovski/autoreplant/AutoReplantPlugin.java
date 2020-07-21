package io.github.novakivanovski.autoreplant;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoReplantPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginCommand command = this.getCommand("autoreplant");
        if (command != null) {
            command.setExecutor(new CommandEnable());
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called");
    }

}