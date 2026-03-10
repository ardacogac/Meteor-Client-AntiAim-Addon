package com.xardacc.yawmod.modules;

import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.utils.player.Rotations;
import meteordevelopment.orbit.EventHandler;

import java.util.Random;

public class YawMod extends Module {

    private final Random random = new Random();

    public enum YawMode {
        Spin,
        Backwards,
        Jitter,
        Static,
        OffsetStatic
    }

    public enum PitchMode {
        Up,
        Down,
        Zero,
        Jitter,
        Static
    }
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    public final EnumSetting<YawMode> yawMode = sgGeneral.add(new EnumSetting.Builder<YawMode>()
        .name("yaw-mode")
        .description("Yaw (body direction) mode")
        .defaultValue(YawMode.Spin)
        .build()
    );

    public final EnumSetting<PitchMode> pitchMode = sgGeneral.add(new EnumSetting.Builder<PitchMode>()
        .name("pitch-mode")
        .description("Pitch (head direction) mode")
        .defaultValue(PitchMode.Down)
        .build()
    );

    public final DoubleSetting spinSpeed = sgGeneral.add(new DoubleSetting.Builder()
        .name("spin-speed")
        .description("Spin speed (degrees/tick) in spin mode")
        .defaultValue(  180.0)
        .min(140.0)
        .max(230.0)
        .sliderRange(140.0, 230.0)
        .build()
    );

    public final DoubleSetting jitterRange = sgGeneral.add(new DoubleSetting.Builder()
        .name("jitter-range")
        .description("Amount of random deviation in jitter mode (degrees)")
        .defaultValue(45.0)
        .min(0.0)
        .max(180.0)
        .sliderRange(0.0, 180.0)
        .build()
    );

    public final DoubleSetting yawOffset = sgGeneral.add(new DoubleSetting.Builder()
        .name("yaw-offset")
        .description("Fixed offset (degrees) added to all yaw modes.")
        .defaultValue(0.0)
        .min(-180.0)
        .max(180.0)
        .sliderRange(-180.0, 180.0)
        .build()
    );

    public final BoolSetting separateFake = sgGeneral.add(new BoolSetting.Builder()
        .name("separate-fake")
        .description("Let the body yaw (fake) and head yaw (real) be separate.")
        .defaultValue(false)
        .build()
    );

    public YawMod() {
        super(Categories.Player, "anti-aim", "CS:GO-style anti-aim settings.");
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (mc.player == null) return;

        float baseYaw = mc.player.getYaw();
        float basePitch = mc.player.getPitch();

        float targetYaw = baseYaw;
        float targetPitch = basePitch;

        switch (yawMode.get()) {
            case Spin:
                targetYaw = (mc.player.bodyYaw + spinSpeed.get().floatValue()) % 360.0f;
                break;
            case Backwards:
                targetYaw = baseYaw + 180.0f;
                break;
            case Jitter:
                targetYaw = baseYaw + (random.nextFloat() * 2 - 1) * jitterRange.get().floatValue();
                break;
            case Static:
                targetYaw = baseYaw;
                break;
            case OffsetStatic:
                targetYaw = baseYaw + yawOffset.get().floatValue();
                break;
        }

        // İsterseniz tüm modlara ofset eklemek için bu satırı aktif edin
        // targetYaw += yawOffset.get().floatValue();

        switch (pitchMode.get()) {
            case Up:
                targetPitch = -90.0f;
                break;
            case Down:
                targetPitch = 90.0f;
                break;
            case Zero:
                targetPitch = 0.0f;
                break;
            case Jitter:
                targetPitch = random.nextBoolean() ? -90.0f : 90.0f;
                break;
            case Static:
                targetPitch = basePitch;
                break;
        }

        // Fake/Real
        if (separateFake.get()) {
            mc.player.bodyYaw = targetYaw;
            mc.player.headYaw = baseYaw;
            Rotations.rotate(baseYaw, targetPitch, 10, null);
        } else {
            mc.player.bodyYaw = targetYaw;
            mc.player.headYaw = targetYaw;
            Rotations.rotate(targetYaw, targetPitch, 10, null);
        }
    }
}