package com.rocketmc.events;

import com.rocketmc.events.commands.EventCommand;
import com.rocketmc.events.event.EventManagar;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Setter
@Getter
public class RocketEvents extends JavaPlugin {

    @Getter
    public static RocketEvents instance;
    public EventManagar eventManagar;


    @Override
    public void onEnable() {
        instance = this;
    setEventManagar(new EventManagar());
    getCommand("evento").setExecutor(new EventCommand());
    }


    @Override
    public void onDisable() {

    }
}
