package net.st1n.schematicannon_substitutes.api;

import net.minecraft.world.item.ItemStack;
import net.st1n.schematicannon_substitutes.tag.SubstituteMaterialType;

public record SubstituteRequirement(SubstituteMaterialType type, int amount) {

    public boolean matches(ItemStack stack) {
        if (type == null) return false;
        return SchematicannonSubstituteHooks.resolveItemType(stack) == type;
    }
}
