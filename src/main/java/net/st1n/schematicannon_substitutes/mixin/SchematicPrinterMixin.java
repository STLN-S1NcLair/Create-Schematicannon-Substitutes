package net.st1n.schematicannon_substitutes.mixin;

import com.simibubi.create.content.schematics.SchematicPrinter;
import com.simibubi.create.content.schematics.cannon.MaterialChecklist;
import net.minecraft.world.level.Level;
import net.st1n.schematicannon_substitutes.api.MaterialChecklistAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SchematicPrinter.class, remap = false)
public abstract class SchematicPrinterMixin {

    @Unique
    @Inject(method = "markAllBlockRequirements", at = @At("HEAD"), cancellable = false)
    private void passLevelToChecklist(MaterialChecklist checklist, Level world, SchematicPrinter.PlacementPredicate predicate, CallbackInfoReturnable<Integer> cir) {
        ((MaterialChecklistAccessor) checklist).schematicannonSubstitutes$setLevel(world);
    }
}
