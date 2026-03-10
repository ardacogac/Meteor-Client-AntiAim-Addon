package com.xardacc.yawmod;

import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xardacc.yawmod.modules.YawMod;

public class YawAddon extends MeteorAddon {
    public static final Logger LOG = LoggerFactory.getLogger("YawAddon");

    @Override
    public void onInitialize() {
        LOG.info("Yaw Addon Loading...");

        // Modülü Meteor'a kaydediyoruz
        Modules.get().add(new YawMod());
    }

    @Override
    public String getPackage() {
        return "com.xardacc.yawmod";
    }
}