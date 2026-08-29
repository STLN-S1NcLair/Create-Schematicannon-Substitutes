package net.st1n.schematicannon_substitutes.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.st1n.schematicannon_substitutes.CreateSchematicannonSubstitutes;

import java.util.List;

public class CreativeTabInit {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateSchematicannonSubstitutes.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("tab.schematicannon_substitutes.tab"))
            .icon(() -> ItemInit.CONSTRUCTION_MATERIALS.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ItemInit.TERRAIN_MATERIALS.get());
                output.accept(ItemInit.CONSTRUCTION_MATERIALS.get());
                output.accept(ItemInit.CRYSTAL_MATERIALS.get());
                output.accept(ItemInit.METAL_MATERIALS.get());
                output.accept(ItemInit.INDUSTRIAL_MATERIALS.get());
                output.accept(ItemInit.PRECIOUS_MATERIALS.get());
            }).build());

    public static void registerCreativeTabs(IEventBus eventBus) {
        CreateSchematicannonSubstitutes.LOGGER.info("Registering Creative Tabs for" + CreateSchematicannonSubstitutes.MODID);
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
