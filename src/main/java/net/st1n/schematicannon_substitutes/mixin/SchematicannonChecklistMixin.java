package net.st1n.schematicannon_substitutes.mixin;

import com.simibubi.create.content.schematics.cannon.MaterialChecklist;
import com.simibubi.create.content.schematics.cannon.SchematicannonBlockEntity;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import com.simibubi.create.foundation.item.ItemHelper;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.EmptyItemHandler;
import net.st1n.schematicannon_substitutes.api.SchematicannonSubstituteHooks;
import net.st1n.schematicannon_substitutes.api.SubstituteRequirement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.LinkedHashSet;

@Mixin(value = MaterialChecklist.class, remap = false)
public abstract class SchematicannonChecklistMixin {

    @Shadow
    public Object2IntMap<Item> required = new Object2IntArrayMap<>();
    @Shadow
    public Object2IntMap<Item> damageRequired = new Object2IntArrayMap<>();

    @Shadow
    protected abstract void putOrIncrement(Object2IntMap<Item> map, ItemStack stack);

    @Unique
    @Inject(method = "require", at = @At("HEAD"), cancellable = true)
    private void schematicannonSubstitutes$grabItemsFromAttachedInventories(ItemRequirement requirement, CallbackInfo cir) {
        if (!SchematicannonSubstituteHooks.shouldShowSubstitutesInClipboard()) {
            return;
        }

        for (ItemRequirement.StackRequirement stack : requirement.getRequiredItems()) {
            SubstituteRequirement substituteRequired = SchematicannonSubstituteHooks.getSubstituteRequirement(stack.stack);
            if (substituteRequired.type() != null) {
                putOrIncrement(required, new ItemStack(SchematicannonSubstituteHooks.getMaterialItemFromType(substituteRequired.type()), substituteRequired.amount()));
            } else {
                if (stack.usage == ItemRequirement.ItemUseType.DAMAGE)
                    putOrIncrement(damageRequired, stack.stack);
                if (stack.usage == ItemRequirement.ItemUseType.CONSUME)
                    putOrIncrement(required, stack.stack);
            }
        }

        cir.cancel();
    }
}
