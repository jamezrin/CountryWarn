package me.jaimemartz.countrywarn;

import me.jaimemartz.faucet.Messager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.net.InetAddress;

public class PostLoginListener implements Listener {
    private final CountryWarn plugin;
    public PostLoginListener(CountryWarn plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        InetAddress address = player.getAddress().getAddress();

        if (plugin.checkWarn(address)) {
            new Messager(player).send(ConfigEntries.WARN_MESSAGE.get());
        }
    }
}
