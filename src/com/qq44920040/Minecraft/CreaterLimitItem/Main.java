package com.qq44920040.Minecraft.CreaterLimitItem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public class Main extends JavaPlugin implements Listener {
    private String ItemNames;
    private String Msg;
    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!file.exists()){
            saveDefaultConfig();
        }
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        super.onEnable();
    }

    @EventHandler
    public void PlayerClickItem(InventoryClickEvent event){
        if (!(ClickType.CREATIVE==event.getClick())){
            return;
        }
        if (event.getWhoClicked() instanceof Player){
            Player p = (Player) event.getWhoClicked();
            ItemStack currentItem = event.getCursor();
            String ItemName = currentItem.getType().name();
            System.out.println(ItemName);
            if (ItemNames.contains(ItemName)){
                event.setCancelled(true);
                p.sendMessage(Msg);
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void LoadConfig(){
        reloadConfig();
        ItemNames = getConfig().getString("ItemsNames").toUpperCase();
        Msg =getConfig().getString("Msg");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()&&label.equalsIgnoreCase("CLI")){
            LoadConfig();
            sender.sendMessage("§2§l[系统]§4§l重载成功");
        }
        return super.onCommand(sender, command, label, args);
    }
}
