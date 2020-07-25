package io.github.novakivanovski.autoreplant;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.Switch;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlockTools {

    private static final BlockFace[] directions = {BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.WEST,
            BlockFace.SOUTH};

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

    public static int harvestCrops(List<Block> farmlandBlocks) {
        int harvest = 0;
        for (Block block : farmlandBlocks) {
            Block aboveBlock = block.getRelative(BlockFace.UP);
            if (aboveBlock.getType() == Material.WHEAT) {
                Ageable crop = (Ageable) aboveBlock.getBlockData();
                if (crop.getAge() == crop.getMaximumAge()) {
                    harvest += 1;
                    crop.setAge(0);
                    aboveBlock.setBlockData(crop);
                }
            }
        }
        return harvest;
    }

}
