package io.github.novakivanovski.autoreplant;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.block.Block;

public class CropBreakListener implements Listener {

    @EventHandler
    public void onCropBreak(BlockBreakEvent event) {
        if (event.isDropItems()) {
            Player player = event.getPlayer();
            Block block = event.getBlock();
            Bukkit.broadcastMessage("Player " + player.getDisplayName() + " broke block: " + block.getType());
        }
    }

}
