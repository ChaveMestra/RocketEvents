package com.rocketmc.events.event;

import com.rocketmc.events.RocketEvents;
import com.rocketmc.events.event.customevents.standalones.*;
import com.rocketmc.events.listeners.BukkitListener;
import com.rocketmc.events.listeners.CustomListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

@Getter
@Setter
public class EventManagar {

    public Event activeEvent;
    public BukkitListener bukkitListener;
    public CustomListener customListener;



    public void startEvent(Event event) {
        if (activeEvent != null) {
            System.out.println("ja tem evento ativo mongoloide, se quiser cancelar digita /evento cancelar");
            return;
        }
        setActiveEvent(event);
        activeEvent.startEvent();
        bukkitListener = new BukkitListener(activeEvent);
        customListener = new CustomListener(activeEvent);
        Bukkit.getPluginManager().registerEvents(customListener, RocketEvents.getInstance());
        Bukkit.getPluginManager().registerEvents(bukkitListener, RocketEvents.getInstance());
    }

    public void forceStop() {
        if (activeEvent != null) {
            activeEvent.endEvent(false);
            stopEvent();
        }
    }

    public void stopEvent() {
        setActiveEvent(null);
        HandlerList.unregisterAll(bukkitListener);
    }

    public Event getBlyClass(String name) {
        if (name.equalsIgnoreCase("MATA_MATA")) {
            return new MATA_MATA();
        }
        if (name.equalsIgnoreCase("MEGA_MOB")) {
            return new MEGA_MOB();
        }
        if (name.equalsIgnoreCase("META_PVP")) {
            return new META_PVP();
        }

        return null;
    }

}
