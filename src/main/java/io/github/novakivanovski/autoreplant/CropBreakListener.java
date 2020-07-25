package io.github.novakivanovski.autoreplant;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.inventory.ItemStack;
import java.util.List;
import java.util.Optional;


public class CropBreakListener implements Listener {

    @EventHandler
    public void onLeverPull(BlockRedstoneEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.LEVER) {
            Block redstoneBlock = BlockTools.getBlockBehindLever(block);
            if (redstoneBlock.getType() == Material.REDSTONE_BLOCK) {
                Optional<Chest> chest = BlockTools.getAdjacentChest(redstoneBlock);
                Optional<BlockFace> direction = BlockTools.getDirectionOfBlock(redstoneBlock, Material.FARMLAND);
                if (!chest.isPresent() || !direction.isPresent()) return;
                List<Block> farm = BlockTools.getConsecutiveBlocksOfType(redstoneBlock, direction.get(), Material.FARMLAND);
                int harvestCount = BlockTools.harvestCrops(farm);
                ItemStack crops = new ItemStack(Material.WHEAT, harvestCount);
                chest.map(Chest::getInventory)
                        .ifPresent(inventory -> inventory.addItem(crops));
            }
        }
    }
}
