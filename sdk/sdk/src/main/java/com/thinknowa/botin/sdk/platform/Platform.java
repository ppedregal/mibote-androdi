package com.thinknowa.botin.sdk.platform;

/**
 * Created by ppedregal on 18/11/15.
 */
public abstract class Platform {

    private static final Platform instance = findPlatform();

    private static Platform findPlatform(){
        // detect android
        // detect log4j
        return new DefaultPlatform();
    }

    public static Platform get(){
        return instance;
    }

    public abstract void log(String message);
    public abstract String version();
    public abstract String name();

}
