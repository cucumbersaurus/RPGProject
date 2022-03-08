package rpgProject.eventListeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockClickEvent implements Listener {

    @EventHandler
    public void BlockRightClick(BlockFromToEvent e){


        if(e.getBlock().getType() == Material.DRAGON_EGG) {
            e.setCancelled(true);

        }
    }

}
