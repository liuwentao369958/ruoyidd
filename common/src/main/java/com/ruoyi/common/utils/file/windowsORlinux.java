package com.ruoyi.common.utils.file;
/*
* 判断当前系统 win  or lin
*
* */
public class windowsORlinux {
    private static final String osName;

    private static final boolean isWindows;

    private static final boolean isLinux;

    static
    {
        osName = System.getProperty("os.name").toUpperCase();
        isWindows = osName.indexOf("WINDOWS") != -1 ? true : false;
        isLinux = osName.indexOf("LINUX") != -1 ? true : false;
    }

    public static boolean isWindows()
    {
        return isWindows;
    }

    public static boolean isLinux()
    {
        return isLinux;
    }
}
