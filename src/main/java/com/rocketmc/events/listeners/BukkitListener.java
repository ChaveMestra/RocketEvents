package com.rocketmc.events.listeners;

import com.rocketmc.events.RocketEvents;
import com.rocketmc.events.event.Event;
import com.rocketmc.events.participant.Participant;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@AllArgsConstructor
public class BukkitListener implements Listener {

    public Event activeEvent;


//    @EventHandler
//    public void onDeath(PlayerDeathEvent event) {
//            Participant participant = activeEvent.getParticipant(event.getEntity());
//            if (participant != null)
//            RustEvents.getInstance().getEventManagar().getActiveEvent().onDeath(participant);
//        }


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Participant participant = activeEvent.getParticipant(event.getPlayer());
        if (participant != null)
            RocketEvents.getInstance().getEventManagar().getActiveEvent().onQuit(participant);
    }


}
