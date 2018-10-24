package com.yuto.Scientificmagicmod;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.Level;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class SMConfig {
	public static Configuration config;

    public static boolean statusMagicPower;
    public static boolean peacefulMagicPower;
    public static boolean statusSplitPower;
    public static boolean peacefulSplitPower;

    public static boolean generateRbniumOre;

    public static final String SM_LANG = "config.sm.";
    public static final String CATEGORY_POTIONS = "potions";
    public static final String CATEGORY_WORLD = "world";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_MOD = "mod";

    private static final Side side = FMLLaunchHandler.side();

    public static void initConfig() {

        if (config == null) {

            File file = new File(Loader.instance().getConfigDir(), "ScientificMagic.cfg");
            config = new Configuration(file);

            try {

                config.load();
            }
            catch (Exception e) {

                File dest = new File(file.getParentFile(), file.getName() + ".bak");

                if (dest.exists()) {

                    dest.delete();
                }

                file.renameTo(dest);

                FMLLog.log(Level.ERROR, e, "A critical error occured reading the " + file.getName() + " file, defaults will be used - the invalid file is backed up at " + dest.getName());
            }
        }
    }

    public static void syncConfig() {

        initConfig();

        String category = Configuration.CATEGORY_GENERAL;
        Property prop;
        List<String> propOrder = Lists.newArrayList();

        //Generate Rbnium Ore - ルビニウム鉱石の生成
        prop = config.get(category, "generateRbniumOre", true);
        prop.setLanguageKey(SM_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        generateRbniumOre = prop.getBoolean(generateRbniumOre);


        category = CATEGORY_STATUS;
        propOrder = Lists.newArrayList();

        //MagicPower Status - 魔力のステータス
        prop = config.get(category, "statusMagicPower", true);
        prop.setLanguageKey(SM_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        statusMagicPower = prop.getBoolean(statusMagicPower);

        //Peaceful MagicPower - ピースフルの魔力
        prop = config.get(category, "peacefulMagicPower", false);
        prop.setLanguageKey(SM_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        peacefulMagicPower = prop.getBoolean(peacefulMagicPower);

      //MagicPower Status - 魔力のステータス
        prop = config.get(category, "statusSplitPower", true);
        prop.setLanguageKey(SM_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        statusSplitPower = prop.getBoolean(statusSplitPower);

        //Peaceful MagicPower - ピースフルの魔力
        prop = config.get(category, "peacefulSplitPower", false);
        prop.setLanguageKey(SM_LANG  + category + "." + prop.getName());
        prop.comment = StatCollector.translateToLocal(prop.getLanguageKey() + ".tooltip");
        prop.comment += " [default: " + prop.getDefault() + "]";
        propOrder.add(prop.getName());
        peacefulSplitPower = prop.getBoolean(peacefulSplitPower);

        config.setCategoryLanguageKey(category, SM_LANG + category);
        config.setCategoryPropertyOrder(category, propOrder);

        category = CATEGORY_MOD;
        propOrder = Lists.newArrayList();
        String key = "Compatibility with %s";

        if (config.hasChanged()) {

            config.save();
        }
    }
}
