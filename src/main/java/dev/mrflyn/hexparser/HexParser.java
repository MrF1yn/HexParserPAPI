package dev.mrflyn.hexparser;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HexParser extends PlaceholderExpansion {


    public String translateHexColorCodes(String string)
    {
        Pattern HEX_PATTERN = Pattern.compile("([A-Fa-f0-9]{6})");
        Matcher matcher = HEX_PATTERN.matcher(string);
        while (matcher.find()) {
            string = string.replace(matcher.group(), "" + ChatColor.of("#"+matcher.group()));
        }
        return string;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        String s = "";
        try{
            s = translateHexColorCodes(params);
        }catch (Exception e){
            e.printStackTrace();
            Bukkit.getLogger().log(Level.WARNING, "Failed to parse hexCode: "+params);
            return "";
        }
        return s;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "#";
    }

    @Override
    public @NotNull String getAuthor() {
        return "MrF1yn";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }
}
