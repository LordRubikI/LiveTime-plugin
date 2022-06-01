package ru.rub1kib.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.rub1kib.LiveTimeMain;
import ru.rub1kib.utils.Messages;

public class SetTimeZone implements CommandExecutor {
    private LiveTimeMain plugin;

    public SetTimeZone(LiveTimeMain plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player) sender;

        if(!p.hasPermission("lt.admin")){
            p.sendMessage(Messages.format(plugin.getConfig().getString("messages.haventPerm")));
            return true;
        }

        if(args.length == 0){
            p.sendMessage(Messages.format(plugin.getConfig().getString("messages.argIsNull")));
            return true;
        }

        plugin.getConfig().set("timezone", args[0]);
        plugin.saveConfig();
        plugin.reloadConfig();
        p.sendMessage(Messages.format(plugin.getConfig().getString("messages.installed")));
        return true;
    }
}