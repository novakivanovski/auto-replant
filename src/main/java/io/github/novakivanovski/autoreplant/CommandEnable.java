package io.github.novakivanovski.autoreplant;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class CommandEnable implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location playerLocation = player.getLocation();
            Vector facingDirection = player.getFacing().getDirection();
            Location inFront = playerLocation.add(facingDirection);
            Block inFrontBlock = inFront.getBlock();
            inFrontBlock.breakNaturally();
        }
        return true;
    }


}
