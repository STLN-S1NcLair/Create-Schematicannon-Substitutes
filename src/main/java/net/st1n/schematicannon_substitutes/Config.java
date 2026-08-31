package net.st1n.schematicannon_substitutes;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

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
            .define("fallbackToConstructionWhenUntagged", false);

    static final ModConfigSpec SPEC = BUILDER.build();
}
