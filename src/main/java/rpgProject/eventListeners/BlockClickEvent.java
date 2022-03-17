package rpgProject.eventListeners;
import org.bukkit.Material;
import org.bukkit.entity.Pose;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockClickEvent implements Listener {

    @EventHandler
    public void BlockFromTo(BlockFromToEvent e){
        if(e.getBlock().getType() == Material.DRAGON_EGG) { //드래곤알 tp 방지
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerBlockInteract(PlayerInteractEvent e){

        if(e.getClickedBlock()==null) return;

        switch (e.getClickedBlock().getType()){
            case ENCHANTING_TABLE: //인첸트 테이블 우클릭시 커스텀 ui 보여주기
                if(checkInteraction(e)){
                    e.setCancelled(true);
                    //ui 오픈
                }
                break;
            case ANVIL:
            case CHIPPED_ANVIL:
            case DAMAGED_ANVIL:
                if(checkInteraction(e)){
                    //e.setCancelled(true);
                    //ui 오픈
                }
                break;
            default:
                break;
        }
    }

    private boolean checkInteraction(PlayerInteractEvent e){
        if(e.getAction().isRightClick()){
            return e.getPlayer().getPose() != Pose.SNEAKING;
        }
        return false;
    }

}
