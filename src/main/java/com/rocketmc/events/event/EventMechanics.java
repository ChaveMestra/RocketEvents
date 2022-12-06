package com.rocketmc.events.event;


import com.rocketmc.events.participant.Participant;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public interface EventMechanics {



    public void onDeath(Participant participant);
    public void onPlayerKill(Participant killer, Participant victim);
    public void onQuit(Participant participant);
    public void onEventJoin(Participant participant);
    public void onMobHit(Participant participant, Entity livingEntity);
    public void onMobDeath(Entity livingEntity);
    public void onBlockMine(Participant participant);
    public void onCatchFish(Participant participant);
    public void onCollectHerb(Participant participant);
    public void onMove(Participant participant);
    public void onBlockBreak(Participant participant);
}
