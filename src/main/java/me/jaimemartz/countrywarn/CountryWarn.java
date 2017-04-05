package me.jaimemartz.countrywarn;

import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import me.jaimemartz.faucet.ConfigFactory;
import net.md_5.bungee.api.plugin.Plugin;

import java.net.InetAddress;

public final class CountryWarn extends Plugin {
    private GeolocationManager geolocationManager;
    private ConfigFactory factory;

    @Override
    public void onEnable() {
        if (factory == null) {
            factory = new ConfigFactory(this);
            factory.register(0, "config.yml");
            factory.submit(ConfigEntries.class);
        }

        factory.load(0, true);

        try {
            geolocationManager = new GeolocationManager(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        getProxy().getPluginManager().registerListener(this, new PostLoginListener(this));
    }

    public boolean checkWarn(InetAddress address) {
        try {
            CountryResponse response = geolocationManager.getReader().country(address);
            Country country = response.getCountry();

            for (String string : ConfigEntries.COUNTRIES.get()) {
                if (country.getIsoCode().matches(string)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
