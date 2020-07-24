package io.github.novakivanovski.autoreplant;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Switch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import java.util.List;


public class CropBreakListener implements Listener {

    @EventHandler
    public void onLeverPull(BlockRedstoneEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.LEVER) {
            Block redstoneBlock = BlockTools.getBlockBehindLever(block);
            if (redstoneBlock.getType() == Material.REDSTONE_BLOCK) {
                BlockFace direction = BlockTools.getDirectionOfBlock(redstoneBlock, Material.FARMLAND);
                if (direction == null) return;
                List<Block> farm = BlockTools.getConsecutiveBlocksOfType(redstoneBlock, direction, Material.FARMLAND);
                BlockTools.harvestCrops(farm);
            }
        }
    }
}
