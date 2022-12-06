package com.rocketmc.events.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;


@Getter
public class Participant {

    public Player player;
    public Location savedLocation;

    public Participant(Player player) {
        this.player = player;
    }

    public void saveLocation() {
        this.savedLocation = player.getLocation();
    }
}
