package net.st1n.schematicannon_substitutes.mixin;

import com.simibubi.create.content.schematics.cannon.SchematicannonBlockEntity;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import com.simibubi.create.foundation.item.ItemHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.EmptyItemHandler;
import net.st1n.schematicannon_substitutes.api.SubstituteHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.st1n.schematicannon_substitutes.api.SchematicannonSubstituteHooks;

import java.util.LinkedHashSet;

@Mixin(value = SchematicannonBlockEntity.class, remap = false)
public abstract class SchematicannonInventoryMixin {

    @Shadow
    private LinkedHashSet<IItemHandler> attachedInventories;

    @Unique
    @Inject(method = "grabItemsFromAttachedInventories", at = @At("RETURN"), cancellable = true)
    private void grabItemsFromAttachedInventories(ItemRequirement.StackRequirement required, boolean simulate, CallbackInfoReturnable<Boolean> cir) {

        Level level = ((BlockEntity) (Object) this).getLevel();

        if (cir.getReturnValue() || level == null) {
            return;
        }

        // find substitutes for the required item
        boolean success = false;
        ItemStack substituteRequired = SubstituteHelper.getSubstitute(level, required.stack);

        if (substituteRequired == null) {
            return;
        }

        int amountFound = 0;
        for (IItemHandler cap : attachedInventories) {
            if (cap == null)
                cap = EmptyItemHandler.INSTANCE;
            amountFound += ItemHelper
                    .extract(cap, (stack -> ItemStack.isSameItem(stack, substituteRequired)), ItemHelper.ExtractionCountMode.UPTO,
                            substituteRequired.getCount(), true)
                    .getCount();

            if (amountFound < substituteRequired.getCount())
                continue;

            success = true;
            break;
        }

        if (!simulate && success) {
            amountFound = 0;
            for (IItemHandler cap : attachedInventories) {
                if (cap == null)
                    cap = EmptyItemHandler.INSTANCE;
                amountFound += ItemHelper
                        .extract(cap, (stack -> ItemStack.isSameItem(stack, substituteRequired)), ItemHelper.ExtractionCountMode.UPTO,
                                substituteRequired.getCount(), false)
                        .getCount();
                if (amountFound < substituteRequired.getCount())
                    continue;
                break;
            }
        }

        if (success) {
            cir.setReturnValue(true);
        }
    }
}
