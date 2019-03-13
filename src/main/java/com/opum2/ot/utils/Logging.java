package com.opum2.ot.utils;

import org.apache.logging.log4j.Level;

import com.opum2.ot.ModInfo;

import net.minecraftforge.fml.common.FMLLog;

public class Logging {

    public static void log(Object object)
    {
    	Logging.log(Level.ALL, object);
    }

    public static void info(Object object)
    {
    	Logging.log(Level.INFO, object);
    }

    public static void debug(Object object)
    {
    	Logging.log(Level.DEBUG, object);
    }

    public static void warn(Object object)
    {
    	Logging.log(Level.WARN, object);
    }

    public static void error(Object object)
    {
    	Logging.log(Level.ERROR, object);
    }

    private static void log(Level logLevel, Object object)
    {
        FMLLog.log(ModInfo.MOD_NAME, logLevel, String.valueOf(object), new Object[0]);
    }
}
