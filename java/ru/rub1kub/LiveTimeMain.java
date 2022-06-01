package ru.rub1kub;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import ru.rub1kub.commands.Reload;
import ru.rub1kub.commands.SetTimeZone;
import ru.rub1kub.listeners.Intervals;
import ru.rub1kub.utils.Messages;

public final class LiveTimeMain extends JavaPlugin {

    private static LiveTimeMain instance;
    public static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    String version = getDescription().getVersion();
    String name = getDescription().getName();

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        this.console.sendMessage(Messages.format("\n \n&8[]=====[&aEnabling " + this.getPluginName() + "&8]=====[]\n&8| &cInformation:\n&8|   &cName: &7" + this.getPluginName() + "\n&8|   &cDeveloper: &7rubikub\n&8|   &cVersion: &7" + this.getVersion() + "\n&8[]======================================[]&r\n "));
        Intervals.interval();
        getCommand("liveTimeReload").setExecutor(new Reload(this));
        getCommand("setTimeZone").setExecutor(new SetTimeZone(this));
        for(String w : getConfig().getStringList("worlds")){
            if(Bukkit.getWorld(w) == null){
                console.sendMessage(Messages.format("&cUnknown world!&8/&cНеизвестный мир\n&7Проверьте название мира!"));
                continue;
            }
            World world = Bukkit.getWorld(w);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        }
    }

    @Override
    public void onDisable() {
        this.console.sendMessage(Messages.format("\n \n&8[]=====[&aDisabling " + this.getPluginName() + "&8]=====[]\n&8| &cInformation:\n&8|   &cName: &7 " + this.getPluginName() + "\n&8|   &cDeveloper: &7R_u_B_i_K\n&8|   &cVersion: &7" + this.getVersion() + "\n&8[]======================================[]&r\n "));

    }

    public static LiveTimeMain getInstance() {
        return instance;
    }

    public String getPluginName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

}
