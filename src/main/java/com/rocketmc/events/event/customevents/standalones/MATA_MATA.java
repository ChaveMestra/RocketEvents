package com.rocketmc.events.event.customevents.standalones;

import com.rocketmc.events.event.Event;
import com.rocketmc.events.event.EventMechanics;
import com.rocketmc.events.event.EventStatus;
import com.rocketmc.events.participant.Participant;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.rocketmc.events.util.MessageUtil.f;

public class MATA_MATA extends Event implements EventMechanics {



    public MATA_MATA() {
        super(f("&4&lMATA-MATA"),
                Arrays.asList(f("&7Sobreviva na arena ate que o tempo acabe"),
                        f("&7Voce pode sair a qualquer momento com &f/evento sair"),
                        f("&7Jogadores recebem premiacao apenas por participar"),
                f("&8OBS: &eAPENAS UM VENCE & PVP ENTRE CLANS ATIVO")),
                EventStatus.WAITING,
                15*60,
                5*60,
                new ArrayList<>(),
                Collections.singletonList("darcaixa %player% Aleatoria 2"),
                Collections.singletonList("darcaixa %player% Spawners 2"),
                new Location(Bukkit.getWorld("RustMapa"), 409, 71, 602),
                true,
                null,
                0,
                0);
    }


    @Override
    public void forceRemoveParticipant(Participant participant) {
        participant.getPlayer().teleport(participant.savedLocation);
    }

    @Override
    public void onDeath(Participant participant) {

        participant.getPlayer().teleport(participant.savedLocation);
        sendConsolationPrize(participant);
        removeParticipant(participant);
    }

    @Override
    public void onPlayerKill(Participant killer, Participant victim) {

    }

    @Override
    public void onQuit(Participant participant) {
        participant.getPlayer().teleport(participant.savedLocation);
        if (eventStatus == EventStatus.ACTIVE) {
            sendConsolationPrize(participant);
        }
        removeParticipant(participant);
    }

    @Override
    public void onEventJoin(Participant participant) {

        if (getParticipant(participant.player) == null) {
            addParticipant(participant);
            participant.saveLocation();
            participant.getPlayer().teleport(joinLocation);
            participant.getPlayer().sendMessage(f("&7Voce entrou na arena &c&lMATA-MATA&7."));
            participant.getPlayer().sendMessage(f("&eObs: &7Voce pode sair a qualquer momento com &a/evento sair"));
            participant.getPlayer().sendMessage(f("&dPremio por participar: &a2x Caixa Aleatoria"));
            participant.getPlayer().sendMessage(f("&3Premio para vencedores: &a2x Caixa Spawners"));
        }
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
