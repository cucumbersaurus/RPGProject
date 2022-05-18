package project.rpg.listeners;
import org.bukkit.Material;
import org.bukkit.entity.Pose;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockClickEventListener implements Listener {

    @EventHandler
    public void blockFromTo(BlockFromToEvent event){
        if(event.getBlock().getType() == Material.DRAGON_EGG) { //드래곤알 tp 방지
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void playerBlockInteract(PlayerInteractEvent event){

        if(event.getClickedBlock()==null) return;

        switch (event.getClickedBlock().getType()){
            case ENCHANTING_TABLE: //인첸트 테이블 우클릭시 커스텀 ui 보여주기
                if(!isSneaking(event)){
                    event.setCancelled(true);
                    //ui 오픈
                }
                break;
            case ANVIL:
            case CHIPPED_ANVIL:
            case DAMAGED_ANVIL:
                //isSneaking(event);
                //e.setCancelled(true);
            //ui 오픈
                break;
            default:
                break;
        }
    }

    private boolean isSneaking(PlayerInteractEvent event){//웅크리기+블럭설치시 블럭설치 이벤트 취소 방지
        if(event.getAction().isRightClick()){
            return event.getPlayer().getPose() == Pose.SNEAKING;
        }
        return false;
    }

}
