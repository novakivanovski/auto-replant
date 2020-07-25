package io.github.novakivanovski.autoreplant;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.Switch;

import java.util.*;

public class BlockTools {
    /* Excluded: Melon, Pumpkin, Bamboo, Cocoa & Sugar Cane.
    These require different conditions to grow than the rest.
    */

    private static final BlockFace[] directions = {BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.WEST,
            BlockFace.SOUTH};

    static final Map<Material, Material> cropsToItems = ImmutableMap.of(
            Material.WHEAT, Material.WHEAT,
            Material.BEETROOTS, Material.BEETROOT,
            Material.CARROTS, Material.CARROT,
            Material.POTATOES, Material.POTATO
    );

    public static boolean isCrop(Block block) {
        for (Material cropType : cropsToItems.keySet()) {
            if (cropType == block.getType()) return true;
        }
        return false;
    }

    public static Optional<Block> getAdjacentBlockOfType(Block block, Material material) {
        for (BlockFace direction: directions) {
            Block adjacentBlock = block.getRelative(direction);
            if (adjacentBlock.getType() == material) return Optional.of(adjacentBlock);
        }
        return Optional.empty();
    }

    public static Optional<BlockFace> getDirectionOfBlock(Block block, Material material) {
        for (BlockFace direction : directions) {
            if (block.getRelative(direction).getType() == material) return Optional.of(direction);
        }
        return Optional.empty();
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

    public static Optional<Chest> getAdjacentChest(Block block) {
        Optional<Block> chestBlock = getAdjacentBlockOfType(block, Material.CHEST);
        return chestBlock.map(value -> (Chest) value.getState());
    }

    public static Map<Material, Integer> harvestCrops(List<Block> farmlandBlocks) {
        Map<Material, Integer>  harvest = new HashMap<>();
        for (Material cropType : cropsToItems.keySet()) {
            harvest.put(cropsToItems.get(cropType), 0);
        }

        for (Block block : farmlandBlocks) {
            Block aboveBlock = block.getRelative(BlockFace.UP);
            if (isCrop(aboveBlock)) {
                Ageable crop = (Ageable) aboveBlock.getBlockData();
                if (crop.getAge() == crop.getMaximumAge()) {
                    Material cropType = crop.getMaterial();
                    Material item = cropsToItems.get(cropType);
                    harvest.put(item, harvest.get(item) + 2);
                    crop.setAge(0);
                    aboveBlock.setBlockData(crop);
                }
            }
        }
        return harvest;
    }

}
