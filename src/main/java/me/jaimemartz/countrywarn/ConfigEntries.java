package me.jaimemartz.countrywarn;

import me.jaimemartz.faucet.ConfigEntry;
import me.jaimemartz.faucet.ConfigEntryHolder;

import java.util.Collections;
import java.util.List;

public class ConfigEntries implements ConfigEntryHolder {
    public static final ConfigEntry<List<String>> COUNTRIES = new ConfigEntry<>(0, "countries", Collections.emptyList());
    public static final ConfigEntry<String> WARN_MESSAGE = new ConfigEntry<>(0, "warn-message", "&cYou can connect to eu.example.com to get a better connection to the server, resulting in less latency");
}
