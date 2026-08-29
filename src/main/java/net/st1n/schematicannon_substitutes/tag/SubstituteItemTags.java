package net.st1n.schematicannon_substitutes.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.st1n.schematicannon_substitutes.CreateSchematicannonSubstitutes;

public final class SubstituteItemTags {
    public static final TagKey<Item> TERRAIN_MATERIALS = tag("terrain_materials");
    public static final TagKey<Item> CONSTRUCTION_MATERIALS = tag("construction_materials");
    public static final TagKey<Item> CRYSTAL_MATERIALS = tag("crystal_materials");
    public static final TagKey<Item> METAL_MATERIALS = tag("metal_materials");
    public static final TagKey<Item> INDUSTRIAL_MATERIALS = tag("industrial_materials");
    public static final TagKey<Item> PRECIOUS_MATERIALS = tag("precious_materials");

    private SubstituteItemTags() {
    }

    private static TagKey<Item> tag(String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(CreateSchematicannonSubstitutes.MODID, path));
    }
}
