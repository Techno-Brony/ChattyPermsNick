package com.weebly.openboxtechnologies.chattypermsnick;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.event.EventHandler;

public class EventsListener implements net.md_5.bungee.api.plugin.Listener {
    @EventHandler
    public void onPlayerJoin(PostLoginEvent e) {
        if (Main.prefixes.containsKey(e.getPlayer().getName())) {
            e.getPlayer().setDisplayName(Main.prefixes.get(e.getPlayer().getName()) + e.getPlayer().getDisplayName());
        }
    }
}
