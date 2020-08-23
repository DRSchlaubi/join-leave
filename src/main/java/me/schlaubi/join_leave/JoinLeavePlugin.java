package me.schlaubi.join_leave;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class JoinLeavePlugin extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    saveDefaultConfig();
    Bukkit.getPluginManager().registerEvents(this, this);
  }

  @EventHandler
  public void onJoin(@NotNull PlayerJoinEvent event) {
    event.setJoinMessage(getMessage("join", event.getPlayer()));
  }

  @EventHandler
  public void onJoin(@NotNull PlayerQuitEvent event) {
    event.setQuitMessage(getMessage("quit", event.getPlayer()));
  }

  public String getMessage(@NotNull String name, @NotNull Player player) {
    String value = getConfig().getString(String.format("messages.%s", name));
    Preconditions.checkNotNull(value, String.format("%s must be set in config!", name));

    return ChatColor.translateAlternateColorCodes('&', value.replace("%player%", player.getName()));
  }
}
