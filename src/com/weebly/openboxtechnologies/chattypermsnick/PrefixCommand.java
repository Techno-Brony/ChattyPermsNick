package com.weebly.openboxtechnologies.chattypermsnick;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

class PrefixCommand extends Command{
    PrefixCommand() {
        super("prefix");
    }

    private String chatNoPerms = ChatColor.RED + "You do not have permission to use this command!";
    private String chatNoOther = ChatColor.RED + "You do not have permission to change other players!";
    private String chatNoColor = ChatColor.YELLOW + "You must specify a colour!";
    private String chatBadColor = ChatColor.YELLOW + "That colour is invalid!";

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender.hasPermission("chatty.nick.admin")) {
            if (strings.length < 1) {
                commandSender.sendMessage(chatNoColor);
                return;
            } else if (strings.length == 1){
                try {
                    ChatColor.valueOf(strings[0]);
                } catch (IllegalArgumentException e) {
                    commandSender.sendMessage(chatBadColor);
                    return;
                }
                Main.prefixes.put(commandSender.getName(), ChatColor.valueOf(strings[0]));
                ProxiedPlayer p = BungeeCord.getInstance().getPlayer(commandSender.getName());
                p.setDisplayName(ChatColor.valueOf(strings[0]) + p.getName());
                commandSender.sendMessage(ChatColor.YELLOW + "You name has been set to " + p.getDisplayName());
                return;
            } else {
                if (!commandSender.hasPermission("chatty.nick.others")) {
                    commandSender.sendMessage(chatNoOther);
                    return;
                }
                try {
                    ChatColor.valueOf(strings[0]);
                } catch (IllegalArgumentException e) {
                    commandSender.sendMessage(chatBadColor);
                    return;
                }
                ProxiedPlayer p = BungeeCord.getInstance().getPlayer(strings[1]);
                if (p == null) {
                    commandSender.sendMessage(ChatColor.RED + "That player is invalid or offline!");
                    return;
                }
                Main.prefixes.put(p.getName(), ChatColor.valueOf(strings[0]));
                p.setDisplayName(ChatColor.valueOf(strings[0]) + p.getName());
                p.sendMessage(ChatColor.YELLOW + "You name has been set to " + p.getDisplayName());
                commandSender.sendMessage(ChatColor.YELLOW + "The player's name has been set to " + p.getDisplayName());
            }
        } else {
            commandSender.sendMessage(chatNoPerms);
            return;
        }
    }
}
