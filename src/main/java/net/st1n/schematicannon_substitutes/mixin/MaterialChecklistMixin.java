package net.st1n.schematicannon_substitutes.mixin;

import com.simibubi.create.content.schematics.cannon.MaterialChecklist;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.st1n.schematicannon_substitutes.api.MaterialChecklistAccessor;
import net.st1n.schematicannon_substitutes.api.SchematicannonSubstituteHooks;
import net.st1n.schematicannon_substitutes.api.SubstituteHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = MaterialChecklist.class, remap = false)
public abstract class MaterialChecklistMixin implements MaterialChecklistAccessor {

    @Shadow
    public Object2IntMap<Item> required = new Object2IntArrayMap<>();
    @Shadow
    public Object2IntMap<Item> damageRequired = new Object2IntArrayMap<>();

    @Shadow
    protected abstract void putOrIncrement(Object2IntMap<Item> map, ItemStack stack);

    @Unique
    private Level schematicannonSubstitutes$level;

    @Unique
    @Inject(method = "require", at = @At("HEAD"), cancellable = true)
    private void schematicannonSubstitutes$grabItemsFromAttachedInventories(ItemRequirement requirement, CallbackInfo cir) {
        if (!SchematicannonSubstituteHooks.shouldShowSubstitutesInClipboard() || schematicannonSubstitutes$level == null) {
            return;
        }

        for (ItemRequirement.StackRequirement stack : requirement.getRequiredItems()) {
            if (stack.stack == null || stack.stack.isEmpty()) {
                continue;
            }
            ItemStack substituteRequired = SubstituteHelper.getSubstitute(schematicannonSubstitutes$level, stack.stack);
            // 代替できない場合
            if (substituteRequired == null) {
                if (stack.usage == ItemRequirement.ItemUseType.DAMAGE)
                    putOrIncrement(damageRequired, stack.stack);
                if (stack.usage == ItemRequirement.ItemUseType.CONSUME)
                    putOrIncrement(required, stack.stack);
            } else {
                putOrIncrement(required, substituteRequired);
            }
        }

        cir.cancel();
    }

    @Unique
    @Override
    public void schematicannonSubstitutes$setLevel(Level level) {
        this.schematicannonSubstitutes$level = level;
    }
}
