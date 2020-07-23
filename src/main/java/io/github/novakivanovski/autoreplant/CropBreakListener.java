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

    public static final int MAX_LENGTH = 10;

    @EventHandler
    public void onCropBreak(BlockRedstoneEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.LEVER) {
            Switch lever = (Switch) block.getBlockData();
            BlockFace behind = lever.getFacing().getOppositeFace();
            Block behindBlock = block.getRelative(behind);
            if (behindBlock.getType() == Material.REDSTONE_BLOCK) {
                BlockFace[] possibleDirections = {BlockFace.NORTH, BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH};
                BlockFace actualDirection = null;
                for (BlockFace direction : possibleDirections) {
                    Block adjacentBlock = behindBlock.getRelative(direction);
                    if (adjacentBlock.getType() == Material.FARMLAND) {
                        actualDirection = direction;
                        break;
                    }
                }

                if (actualDirection != null) {
                    Block currentBlock = behindBlock.getRelative(actualDirection);
                    while (currentBlock.getType() == Material.FARMLAND) {
                        Block cropBlock = currentBlock.getRelative(BlockFace.UP);
                        Bukkit.broadcastMessage("Breaking block: " + cropBlock.getType());
                        cropBlock.breakNaturally();
                        cropBlock.setType(Material.WHEAT);
                        currentBlock = currentBlock.getRelative(actualDirection);
                    }
                }
            }
        }
    }
}
