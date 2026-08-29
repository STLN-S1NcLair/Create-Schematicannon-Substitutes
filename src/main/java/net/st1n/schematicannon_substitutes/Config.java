package net.st1n.schematicannon_substitutes;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue TERRAIN_MATERIALS_PER_BLOCK = BUILDER
            .translation("config.schematicannon_substitutes.terrain_materials_per_block")
            .comment("Terrain Materials consumed per 1 schematic block.")
            .defineInRange("terrainMaterialsPerBlock", 1, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue CONSTRUCTION_MATERIALS_PER_BLOCK = BUILDER
            .translation("config.schematicannon_substitutes.construction_materials_per_block")
            .comment("Construction Materials consumed per 1 schematic block.")
            .defineInRange("constructionMaterialsPerBlock", 1, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue CRYSTAL_MATERIALS_PER_BLOCK = BUILDER
            .translation("config.schematicannon_substitutes.cystal_materials_per_block")
            .comment("Crystal Materials consumed per 1 schematic block.")
            .defineInRange("crystalMaterialsPerBlock", 1, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue METAL_MATERIALS_PER_BLOCK = BUILDER
            .translation("config.schematicannon_substitutes.metal_materials_per_block")
            .comment("Metal Materials consumed per 1 schematic block.")
            .defineInRange("metalMaterialsPerBlock", 1, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue INDUSTRIAL_MATERIALS_PER_BLOCK = BUILDER
            .translation("config.schematicannon_substitutes.industrial_materials_per_block")
            .comment("Industrial Materials consumed per 1 schematic block.")
            .defineInRange("industrialMaterialsPerBlock", 1, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue PRECIOUS_MATERIALS_PER_BLOCK = BUILDER
            .translation("config.schematicannon_substitutes.precious_materials_per_block")
            .comment("Precious Materials consumed per 1 schematic block.")
            .defineInRange("preciousMaterialsPerBlock", 1, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec.BooleanValue USE_ONLY_CONSTRUCTION_MATERIALS = BUILDER
            .translation("config.schematicannon_substitutes.use_only_construction_materials")
            .comment("If true, all schematic blocks consume Construction Materials regardless of tags, with required quantities preserved.")
            .define("useOnlyConstructionMaterials", false);

    public static final ModConfigSpec.BooleanValue SHOW_SUBSTITUTES_IN_CLIPBOARD = BUILDER
            .translation("config.schematicannon_substitutes.show_substitutes_in_clipboard")
            .comment("If true, clipboard requirement display is replaced with substitute materials.")
            .define("showSubstitutesInClipboard", true);

    public static final ModConfigSpec.BooleanValue FALLBACK_TO_CONSTRUCTION_WHEN_UNTAGGED = BUILDER
            .translation("config.schematicannon_substitutes.fallback_to_construction_when_untagged")
            .comment("If true, untagged blocks consume Construction Materials. If false, edit the tags to specify which Materials they consume.")
            .define("fallbackToConstructionWhenUntagged", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}
