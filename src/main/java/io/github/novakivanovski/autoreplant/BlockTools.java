package io.github.novakivanovski.autoreplant;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Switch;

import java.util.ArrayList;
import java.util.List;

public class BlockTools {

    private static final BlockFace[] directions = {BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.WEST,
            BlockFace.SOUTH};

    public static Block getAdjacentBlockOfType(Block block, Material material) {
        for (BlockFace direction: directions) {
            Block adjacentBlock = block.getRelative(direction);
            if (adjacentBlock.getType() == material) return adjacentBlock;
        }
        return null;
    }

    public static BlockFace getDirectionOfBlock(Block block, Material material) {
        for (BlockFace direction : directions) {
            if (block.getRelative(direction).getType() == material) return direction;
        }
        return null;
    }


    public static List<Block> getConsecutiveBlocksOfType(Block startBlock, BlockFace direction, Material material) {
        List<Block> blocks = new ArrayList<>();
        Block nextBlock = startBlock.getRelative(direction);
        while (nextBlock.getType() == material) {
            blocks.add(nextBlock);
            nextBlock = nextBlock.getRelative(direction);
        }
        return blocks;
    }

    public static Block getBlockBehindLever(Block block) {
        Switch lever = (Switch) block.getBlockData();
        BlockFace behind = lever.getFacing().getOppositeFace();
        return block.getRelative(behind);
    }

    public static Block getAdjacentChest(Block block) {
        return getAdjacentBlockOfType(block, Material.CHEST);
    }

    public static void harvestCrops(List<Block> farmlandBlocks) {
        int harvest = 0;
        for (Block block : farmlandBlocks) {
            Block aboveBlock = block.getRelative(BlockFace.UP);
            if (aboveBlock.getType() == Material.WHEAT) {
                aboveBlock.setType(Material.AIR);
                harvest += 1;
            }
        }
        Bukkit.broadcastMessage("Harvest count: " + harvest);
    }

}
