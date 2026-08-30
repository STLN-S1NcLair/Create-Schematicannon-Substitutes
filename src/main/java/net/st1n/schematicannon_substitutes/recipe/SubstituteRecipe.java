package net.st1n.schematicannon_substitutes.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record SubstituteRecipe(String group, Ingredient input, ItemStack result) implements Recipe<SingleRecipeInput> {
    public static final MapCodec<SubstituteRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(SubstituteRecipe::group),
            Ingredient.CODEC.fieldOf("ingredient").forGetter(SubstituteRecipe::input),
            ItemStack.STRICT_CODEC.fieldOf("result").forGetter(SubstituteRecipe::result)
    ).apply(instance, SubstituteRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SubstituteRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SubstituteRecipe::group,
            Ingredient.CONTENTS_STREAM_CODEC,
            SubstituteRecipe::input,
            ItemStack.STREAM_CODEC,
            SubstituteRecipe::result,
            SubstituteRecipe::new
    );

    @Override
    public boolean matches(@NotNull SingleRecipeInput in, @NotNull Level level) {
        return input.test(in.item());
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SingleRecipeInput input, HolderLookup.@NotNull Provider registries) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider registries) {
        return result().copy();
    }

    @Override
    public @NotNull String getGroup() {
        return group;
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return result.copy();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return RecipeInit.SUBSTITUTE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return RecipeInit.SUBSTITUTE_TYPE.get();
    }
}
