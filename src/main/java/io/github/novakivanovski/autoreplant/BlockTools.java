package io.github.novakivanovski.autoreplant;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.List;

public class BlockTools {

    private static final BlockFace[] directions = {BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.WEST,
            BlockFace.SOUTH};

    public static Block[] getAdjacentBlocks(Block block) {
        Block[] adjacentBlocks = new Block[directions.length];

        for (int i = 0; i < directions.length; i++) {
            adjacentBlocks[i] = block.getRelative(directions[i]);
        }
        return adjacentBlocks;
    }

    public static Block getAdjacentBlockOfType(Block block, Material material) {
        Block[] adjacentBlocks = getAdjacentBlocks(block);
        for (Block adjacentBlock : adjacentBlocks) {
            if (adjacentBlock.getType() == material) {
                return adjacentBlock;
            }
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

}
