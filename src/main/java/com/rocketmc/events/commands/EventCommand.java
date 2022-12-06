package com.rocketmc.events.commands;

import com.rocketmc.events.RocketEvents;
import com.rocketmc.events.event.Event;
import com.rocketmc.events.event.EventStatus;
import com.rocketmc.events.participant.Participant;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.rocketmc.events.util.MessageUtil.f;

public class EventCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length == 0) {
                player.sendMessage(f("Comandos evento:"));
                player.sendMessage(f("&e/evento entrar"));
                player.sendMessage(f("&e/evento sair"));
                return false;
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("entrar")) {
                if (RocketEvents.getInstance().getEventManagar().getActiveEvent() != null &&
                RocketEvents.getInstance().getEventManagar().getActiveEvent().isJoinable()
                        && RocketEvents.getInstance().getEventManagar().getActiveEvent().getEventStatus() ==
                        EventStatus.WAITING) {
                    RocketEvents.getInstance().getEventManagar().getActiveEvent().onEventJoin(new Participant(player));
                }
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("sair")) {
                if (RocketEvents.getInstance().getEventManagar().getActiveEvent() != null &&
                        RocketEvents.getInstance().getEventManagar().getActiveEvent().isJoinable) {
                    Participant participant = RocketEvents.getInstance().getEventManagar().getActiveEvent().getParticipant(player);
                    if (participant != null)
                    RocketEvents.getInstance().getEventManagar().getActiveEvent().onQuit(participant);
                }
            }
        } else {
            if (args.length != 2) {
                System.out.println(f("Comandos evento:"));
                System.out.println(f("&e/evento iniciar <EVENTO>"));
                System.out.println(f("&e/evento cancelar"));
                System.out.println(f("&e/evento forcejoin <player>"));
                System.out.println(f("&e/evento forcequit <player>"));
                return false;
            }
            if (args[0].equalsIgnoreCase("iniciar")) {
                Event event = RocketEvents.getInstance().getEventManagar().getBlyClass(args[1]);
                if (event != null) {
                    RocketEvents.getInstance().getEventManagar().startEvent(event);
                }
            }
            if (args[0].equalsIgnoreCase("cancelar")) {
                RocketEvents.getInstance().getEventManagar().forceStop();
            }
        }
        return false;
    }
}
