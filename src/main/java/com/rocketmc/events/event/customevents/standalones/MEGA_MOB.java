package com.rocketmc.events.event.customevents.standalones;

import com.rocketmc.events.event.Event;
import com.rocketmc.events.event.EventMechanics;
import com.rocketmc.events.event.EventStatus;
import com.rocketmc.events.participant.Participant;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.rocketmc.events.util.MessageUtil.f;

public class MEGA_MOB extends Event implements EventMechanics {



    public MEGA_MOB() {
        super(f("&3&lMEGA MOB"),
                Arrays.asList(f("&7Um monstro nasceu na mina do /spawn"),
                        f("&7Matem ele para receberem uma recompensa"),
                f("&8OBS: &eRecompensa apenas para quem contribuir com o kill")),
                EventStatus.ACTIVE,
                5*60,
                0,
                new ArrayList<>(),
                Collections.singletonList("darcaixa %player% Aleatoria 1"),
                Collections.singletonList("darcaixa %player% Spawners 1"),
                null,
                false,
                null,
                0,
                0);
    }


    @Override
    public void onStart() {

        Giant giant = (Giant) Bukkit.getWorld("world").spawnEntity(Bukkit.getWorld("world").getSpawnLocation(), EntityType.GIANT);
        giant.setMaxHealth(10000);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDeath(Participant participant) {
        removeParticipant(participant);
    }

    @Override
    public void onPlayerKill(Participant killer, Participant victim) {

    }

    @Override
    public void onQuit(Participant participant) {
        removeParticipant(participant);
    }

    @Override
    public void onEventJoin(Participant participant) {

    }

    @Override
    public void onMobHit(Participant participant, Entity livingEntity) {
        //todo check for mob
        if (livingEntity instanceof Giant) {
            if (getParticipant(participant.player) == null) {
                this.addParticipant(participant);
            }
        }
    }

    @Override
    public void onMobDeath(Entity livingEntity) {
        //todo check for mob
        if (livingEntity instanceof Giant) {
            endEvent(true);
        }
    }

    @Override
    public void onBlockMine(Participant participant) {

    }

    @Override
    public void onCatchFish(Participant participant) {

    }

    @Override
    public void onCollectHerb(Participant participant) {

    }

    @Override
    public void onMove(Participant participant) {

    }

    @Override
    public void onBlockBreak(Participant participant) {

    }
}
