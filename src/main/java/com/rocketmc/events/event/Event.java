package com.rocketmc.events.event;

import com.rocketmc.events.RocketEvents;
import com.rocketmc.events.participant.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.rocketmc.events.util.DefaultFontInfo.sendCenteredMessage;
import static com.rocketmc.events.util.MessageUtil.f;


@Getter
@Setter
@AllArgsConstructor
public abstract class Event implements EventMechanics {

    public String name;
    public List<String> description;
    public EventStatus eventStatus;
    public long millisTillEnd;
    public long millisTillNextStatus;
    public ArrayList<Participant> participants;
    public List<String> commandsToParticipants;
    public List<String> commandsToWinners;
    public Location joinLocation;
    public boolean isJoinable;
    public BukkitTask timer;
    public int score;
    public int scoreToEnd;




    public void pushNextStatus() {
        if (eventStatus == EventStatus.WAITING) {
            this.eventStatus = EventStatus.ACTIVE;
            millisTillNextStatus = millisTillEnd;
        } else {
            this.eventStatus = EventStatus.ENDED;
        }
    }


    public void startEvent() {
       this.timer = new BukkitRunnable() {
           @Override
           public void run() {
               if (System.currentTimeMillis()/1000 >= System.currentTimeMillis()/1000+ millisTillNextStatus) {
                   pushNextStatus();
               }

               if (secondsLeft() % 30 == 0 && !isForceStop()) {
                   broadcastStatusMessage();
               }
               millisTillNextStatus--;

           }
       }.runTaskTimer(RocketEvents.getInstance(), 0, 20L);


    }



    public boolean isForceStop() {
        //THINGS THAT WILL MAKE THE EVENT END
        if (eventStatus == EventStatus.ENDED) {
            endEvent(false);
            return true;
        }
        if (isJoinable && participants.size() <= 1 && eventStatus == EventStatus.ACTIVE) {
            endEvent(false);
            return true;
        }
        if (scoreToEnd >= 1 && score >= scoreToEnd && eventStatus == EventStatus.ACTIVE) {
            endEvent(true);
            return true;
        }
        return false;
    }

    public long secondsLeft() { return (System.currentTimeMillis()/ 1000 + millisTillNextStatus) - System.currentTimeMillis()/1000;
    }

    public Participant getParticipant(Player player) {
        return this.participants.stream().filter(participant -> participant.player.equals(player)).findFirst().orElse(null);
    }

    public void removeParticipant(Participant participant) {
        this.participants.remove(participant);
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }


    public void forceRemoveParticipant(Participant participant) {
        //just for abstract
    }
    public void onStart() {
        //just for abstract
    }
    public void onStop() {
        //just for abstract
    }

    public void endEvent(boolean sendReward) {
        timer.cancel();
        eventStatus = EventStatus.ENDED;
        millisTillNextStatus = 0;
        millisTillEnd = 0;
        broadcastStatusMessage();
        if (sendReward) {
            sendParticipantsReward();
        }
        if (isJoinable) {
            this.participants.forEach(this::forceRemoveParticipant);
        }
        RocketEvents.getInstance().getEventManagar().stopEvent();
    }

    public void sendParticipantsReward() {
        commandsToWinners.forEach(command ->
                participants.forEach(participant ->
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                command.replace("%player%", participant.getPlayer().getName()))));
        participants.forEach(participant -> participant.getPlayer().sendMessage(f("&dVoce recebeu um &e&npremio&d do &aEVENTO")));

    }

    public void sendConsolationPrize(Participant participant) {
        commandsToParticipants.forEach(command ->
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                command.replace("%player%", participant.getPlayer().getName())));
        participant.getPlayer().sendMessage(f("&dVoce recebeu um &e&npremio&d do &aEVENTO"));

    }

    public void broadcastStatusMessage() {
        List<Player> receivers = (eventStatus == EventStatus.ACTIVE && isJoinable() ?
                this.participants.stream()
                        .map(Participant::getPlayer)
                        .collect(Collectors.toList())               : new ArrayList<>(Bukkit.getOnlinePlayers()));
        for (Player player : receivers) {
            sendCenteredMessage(player, f("&a&lEVENTO "+this.name));
            sendCenteredMessage(player, f(" "));
            sendCenteredMessage(player, f("&dStatus: &f"+this.eventStatus.toString()));
            sendCenteredMessage(player, f("&dTempo Restante: &f"+secondsLeft()+"s"));
            sendCenteredMessage(player, f(" "));
            this.description.forEach(msg -> sendCenteredMessage(player, msg));
            if (isJoinable && eventStatus == EventStatus.WAITING) {
                sendCenteredMessage(player, f("&aPara participar, digite &e/evento entrar"));
            }
            if (scoreToEnd > 0 && eventStatus == EventStatus.ACTIVE) {
                sendCenteredMessage(player, f("&b&nPontuacao Atual:&f "+this.score+"&7/&f"+this.scoreToEnd));
            }
        }
    }




}
