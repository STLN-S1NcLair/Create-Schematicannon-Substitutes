package net.st1n.schematicannon_substitutes.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.st1n.schematicannon_substitutes.CreateSchematicannonSubstitutes;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class RecipeInit {
    // Serializers
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, CreateSchematicannonSubstitutes.MODID);
    public static final Supplier<RecipeSerializer<SubstituteRecipe>> SUBSTITUTE_SERIALIZER = registerSerializer("substitute", SubstituteRecipe.CODEC, SubstituteRecipe.STREAM_CODEC);
    // Types
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, CreateSchematicannonSubstitutes.MODID);
    public static final Supplier<RecipeType<SubstituteRecipe>> SUBSTITUTE_TYPE = registerType("substitute");

    private static <T extends Recipe<?>> @NotNull Supplier<RecipeSerializer<T>> registerSerializer(@NotNull String name, @NotNull MapCodec<T> codec, @NotNull StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
        return SERIALIZERS.register(name, () -> new RecipeSerializer<>() {
            @Override
            public @NotNull MapCodec<T> codec() {
                return codec;
            }

            @Override
            public @NotNull StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
                return streamCodec;
            }
        });
    }

    private static <T extends Recipe<?>> @NotNull Supplier<RecipeType<T>> registerType(@NotNull String name) {
        return TYPES.register(name, RecipeType::simple);
    }

    public static void registerRecipes(IEventBus eventBus) {
        CreateSchematicannonSubstitutes.LOGGER.info("Registering Recipes for " + CreateSchematicannonSubstitutes.MODID);
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }


}
