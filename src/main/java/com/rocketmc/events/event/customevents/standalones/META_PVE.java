package com.rocketmc.events.event.customevents.standalones;

import com.rocketmc.events.event.Event;
import com.rocketmc.events.event.EventMechanics;
import com.rocketmc.events.event.EventStatus;
import com.rocketmc.events.participant.Participant;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.rocketmc.events.util.MessageUtil.f;

public class META_PVE extends Event implements EventMechanics {



    public META_PVE() {
        super(f("&e&lMETA PVP"),
                Arrays.asList(f("&7Os jogadores terao de cumprir a meta de"),
                        f("&7kills PVP (/pvp) no tempo estimado para ganharem recompensa."),
                        f("&8OBS: &eRecompensa apenas para quem contribuir com a meta")),
                EventStatus.WAITING,
                60*10,
                60*5,
                new ArrayList<>(),
                Collections.singletonList("darcaixa %player% Aleatoria 1"),
                Collections.singletonList("darcaixa %player% Aleatoria 1"),
                null,
                false,
                null,
                0,
                150);
    }


    @Override
    public void onDeath(Participant participant) {


    }

    @Override
    public void onPlayerKill(Participant killer, Participant victim) {
        if (getParticipant(killer.player) == null) {
            this.addParticipant(killer);
        }

            score++;
    }

    @Override
    public void onQuit(Participant participant) {

    }

    @Override
    public void onEventJoin(Participant participant) {

    }

    @Override
    public void onMobHit(Participant participant, Entity livingEntity) {

    }

    @Override
    public void onMobDeath(Entity livingEntity) {

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
