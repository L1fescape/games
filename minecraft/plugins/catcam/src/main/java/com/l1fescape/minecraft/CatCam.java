package com.l1fescape.minecraft;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CatCam extends JavaPlugin {
  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(new CatGodMode(), this);
  }

  // todo: maybe search for the cat by looking at all entities and
  // checking getCustomName, but seems very expensive.
  // right now just get the bot close to the cat and type
  // /gamemode spectator
  // /spectate @e[type=cat,sort=nearest,limit=1]
  // @EventHandler
  // public void onPlayerJoin(PlayerJoinEvent e) {
  //   Player player = e.getPlayer();
  //   if (player.getName() == spectatingPlayerName) {
  //     player.setSpectatorTarget()
  //   }
  // }
}
