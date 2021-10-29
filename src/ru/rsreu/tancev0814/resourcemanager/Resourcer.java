package ru.rsreu.tancev0814.resourcemanager;

import java.util.ResourceBundle;

public class Resourcer {
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("text");
    private Resourcer() { }
    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
