package project.rpg.effect;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import project.rpg.Rpg;

import java.util.Objects;

public class ElectricShock extends EffectBase {

    @Override
    public void effect() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        try {
            Class.forName("project.rpg.Rpg");
        } catch (ClassNotFoundException exception) {
            return;
        }
        Plugin plugin = pluginManager.getPlugin("Rpg");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Objects.requireNonNull(plugin), () -> {
            this._entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2, 7,true));
        },10, 20L *_second);
    }

    public ElectricShock(LivingEntity entity, int second) {
        super(entity, second);
    }


}
