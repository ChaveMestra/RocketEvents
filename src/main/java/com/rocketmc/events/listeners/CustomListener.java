package com.rocketmc.events.listeners;

import com.rocketmc.events.event.Event;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import rocketmc.skyblockranking.events.BlocoMineradoEvent;
import rocketmc.skyblockranking.events.KillPVEEvent;
import rocketmc.skyblockranking.events.PeixePescadoEvent;
@AllArgsConstructor
public class CustomListener implements Listener {

    public Event activeEvent;

    @EventHandler
    public void onCatcH(PeixePescadoEvent e) {

    }

    @EventHandler
    public void onKillPVE(KillPVEEvent e) {

    }

    @EventHandler
    public void onMine(BlocoMineradoEvent e) {

    }
}
