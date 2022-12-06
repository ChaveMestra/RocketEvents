package com.rocketmc.events.util;

import org.bukkit.ChatColor;

public class MessageUtil {

    public static String f(String toBeFormatted) {

        return ChatColor.translateAlternateColorCodes('&', toBeFormatted);
    }


}
