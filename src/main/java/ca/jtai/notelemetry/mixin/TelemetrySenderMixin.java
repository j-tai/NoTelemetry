package ca.jtai.notelemetry.mixin;

import com.mojang.authlib.minecraft.TelemetrySession;
import com.mojang.authlib.minecraft.UserApiService;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.telemetry.TelemetrySender;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.UUID;

@Mixin(value = TelemetrySender.class)
public class TelemetrySenderMixin {
    @Shadow
    private boolean sent;
    @Shadow
    @Final
    @Mutable
    private TelemetrySession session;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(MinecraftClient minecraftClient, UserApiService userApiService, Optional optional, Optional optional2, UUID uUID, CallbackInfo ci) {
        session = TelemetrySession.DISABLED;
        sent = true;
    }
}
