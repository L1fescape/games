package com.l1fescape.minecraft;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.Bukkit;

public class App extends JavaPlugin {
  BukkitTask slackAsyncTask;
  SlackClient slackClient = new SlackClient();

  @Override
  public void onEnable() {
    slackAsyncTask = Bukkit.getScheduler().runTaskAsynchronously(this, new Runnable() {
      @Override
      public void run() {
        getLogger().info("Initializing Slack client");
        slackClient.start();
      }
    });
  }

  @Override
  public void onDisable() {
    getLogger().info("Killing Slack client");
    slackClient.stop();
    slackAsyncTask.cancel();
  }
}
