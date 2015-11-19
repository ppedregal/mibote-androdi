package com.thinknowa.botin.sdk.platform;

/**
 * Created by ppedregal on 18/11/15.
 */
class DefaultPlatform extends Platform {

    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public String version() {
        return System.getProperty("java.version");
    }

    public String name() {
        return "jre";
    }

}
