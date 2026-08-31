package net.st1n.schematicannon_substitutes.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.st1n.schematicannon_substitutes.CreateSchematicannonSubstitutes;

public class ItemInit {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateSchematicannonSubstitutes.MODID);

    public static final DeferredItem<Item> TERRAIN_MATERIALS = ITEMS.register("terrain_materials",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CONSTRUCTION_MATERIALS = ITEMS.register("construction_materials",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WOOL_MATERIALS = ITEMS.register("wool_materials",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CRYSTAL_MATERIALS = ITEMS.register("crystal_materials",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> METAL_MATERIALS = ITEMS.register("metal_materials",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INDUSTRIAL_MATERIALS = ITEMS.register("industrial_materials",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHER_MATERIALS = ITEMS.register("nether_materials",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> END_MATERIALS = ITEMS.register("end_materials",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ORGANIC_MATERIALS = ITEMS.register("organic_materials",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PRECIOUS_MATERIALS = ITEMS.register("precious_materials",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        CreateSchematicannonSubstitutes.LOGGER.info("Registering Items for" + CreateSchematicannonSubstitutes.MODID);
        ITEMS.register(eventBus);
    }
}
