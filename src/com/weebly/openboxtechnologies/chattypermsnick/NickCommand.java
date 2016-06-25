package com.weebly.openboxtechnologies.chattypermsnick;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

class NickCommand extends Command{
    NickCommand() {
        super("nick");
    }

    private String chatNoPerms = ChatColor.RED + "You do not have permission to use this command!";
    private String chatNoArgs = ChatColor.YELLOW + "You must specify a nick name!";

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender.hasPermission("chatty.nick.nick")) {
            if (strings.length < 1) {
                commandSender.sendMessage(chatNoArgs);
                return;
            } else {
                ProxiedPlayer p = BungeeCord.getInstance().getPlayer(commandSender.getName());
                if (strings[0].equalsIgnoreCase("clear")){
                    if (strings.length > 1) {
                        if (!commandSender.hasPermission("chatty.nick.admin")) {
                            commandSender.sendMessage(chatNoPerms);
                            return;
                        }
                        ProxiedPlayer s = BungeeCord.getInstance().getPlayer(strings[1]);
                        if (s == null) {
                            commandSender.sendMessage(ChatColor.RED + "That player is invalid or offline!");
                            return;
                        }
                        s.setDisplayName(s.getName());
                        Main.prefixes.remove(s.getName());
                        Main.config.set(s.getName(), null);
                        s.sendMessage(ChatColor.YELLOW + "You name has been reset to " + s.getDisplayName());
                        commandSender.sendMessage(ChatColor.YELLOW + "The player's name has been reset to " + s.getDisplayName());
                        return;
                    }
                    p.setDisplayName(p.getName());
                    Main.prefixes.remove(p.getName());
                    Main.config.set(p.getName(), null);
                    commandSender.sendMessage(ChatColor.YELLOW + "You name has been reset to " + p.getDisplayName());
                    return;
                }
                if (Main.prefixes.containsKey(commandSender.getName())) {
                    p.setDisplayName(Main.prefixes.get(p.getName()) + strings[0]);
                } else {
                    p.setDisplayName(strings[0]);
                }
                commandSender.sendMessage(ChatColor.YELLOW + "You name has been set to " + p.getDisplayName());
                return;
            }
        } else {
            commandSender.sendMessage(chatNoPerms);
            return;
        }
    }
}
