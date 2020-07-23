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


public class CropBreakListener implements Listener {

    @EventHandler
    public void onCropBreak(BlockRedstoneEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.LEVER) {
            Switch lever = (Switch) block.getBlockData();
            BlockFace behind = lever.getFacing().getOppositeFace();
            Block behindBlock = block.getRelative(behind);
            if (behindBlock.getType() == Material.REDSTONE_BLOCK) {
                Bukkit.broadcastMessage("Lever attached to Redstone block pulled.");

                BlockFace[] possibleDirections = {BlockFace.NORTH, BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH};
                for (BlockFace direction : possibleDirections) {
                    Block adjacentBlock = block.getRelative(direction);
                    if (adjacentBlock.getType() == Material.GRASS) {
                        Bukkit.broadcastMessage("Grass direction: " + direction.toString());
                    }
                }
            }
        }
    }
}
