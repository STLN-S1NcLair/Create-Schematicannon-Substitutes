package net.st1n.schematicannon_substitutes.api;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.st1n.schematicannon_substitutes.item.ItemInit;
import net.st1n.schematicannon_substitutes.recipe.RecipeInit;
import net.st1n.schematicannon_substitutes.recipe.SubstituteRecipe;

import java.util.Optional;

public class SubstituteHelper {

    private SubstituteHelper() {}

    public static ItemStack getSubstitute(Level level, ItemStack stack) {
        Optional<RecipeHolder<SubstituteRecipe>> recipe = level.getRecipeManager().getRecipeFor(RecipeInit.SUBSTITUTE_TYPE.get(), new SingleRecipeInput(stack), level);
        if  (recipe.isPresent()) {
            ItemStack result = recipe.get().value().result();
            // constructionを強制的に使わせる
            if (SchematicannonSubstituteHooks.shouldUseOnlyConstructionMaterials()) {
                return new ItemStack(ItemInit.CONSTRUCTION_MATERIALS.asItem(), result.getCount());
            } else {
                return result;
            }
        // 代替品が見つからなかった場合、コンストラクションマテリアルにフォールバックするかどうかを確認
        } else if (SchematicannonSubstituteHooks.shouldFallbackToConstructionWhenUntagged()) {
            return new ItemStack(ItemInit.CONSTRUCTION_MATERIALS.asItem(), stack.getCount());
        } else {
            return null;
        }
    }
}
