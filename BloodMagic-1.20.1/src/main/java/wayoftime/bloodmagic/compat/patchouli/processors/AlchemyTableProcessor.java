package wayoftime.bloodmagic.compat.patchouli.processors;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;
import wayoftime.bloodmagic.common.item.BloodMagicItems;
import wayoftime.bloodmagic.common.recipe.BloodMagicRecipeType;
import wayoftime.bloodmagic.recipe.RecipeAlchemyTable;

/*
 * Example Page:
 * 
 * {
 *   "type": "bloodmagic:crafting_alchemy_table",    // Corresponding Template.
 *   "heading": "Title",    // (Optional) Title.
 *   "recipe": "recipe_id",    // Recipe ID.
 *   "text": "Extra text."    // (Optional) Extra text to go under the entry.
 * },
 */

public class AlchemyTableProcessor implements IComponentProcessor
{
	private RecipeAlchemyTable recipe;

	@Override
	public void setup(Level level, IVariableProvider variables)
	{
		ResourceLocation id = new ResourceLocation(variables.get("recipe").asString());
		Optional<? extends Recipe<?>> recipeHandler = Minecraft.getInstance().level.getRecipeManager().byKey(id);
		if (recipeHandler.isPresent())
		{
			Recipe<?> recipe = recipeHandler.get();
			if (recipe.getType().equals(BloodMagicRecipeType.ALCHEMYTABLE.get()))
			{
				this.recipe = (RecipeAlchemyTable) recipe;
			}
		}
		if (this.recipe == null)
		{
			LogManager.getLogger().warn("Guidebook missing Alchemy Table recipe {}", id);
		}
	}

	@Override
	public IVariable process(Level level,String key)
	{
		if (recipe == null)
		{
			return null;
		} else if (key.startsWith("input"))
		{
			int index = Integer.parseInt(key.substring(5)) - 1;
			if (recipe.getInput().size() > index)
			{
				return IVariable.wrapList(Arrays.stream(recipe.getInput().get(index).getItems()).map(IVariable::from).collect(Collectors.toList()));
			} else
			{
				return null;
			}
		}
		switch (key)
		{
		case "output":
			return IVariable.from(recipe.getOutput());
		case "syphon":
			return IVariable.wrap(recipe.getSyphon());
		case "time":
			return IVariable.wrap(recipe.getTicks());
		case "tier":
			return IVariable.wrap(recipe.getMinimumTier());
		case "orb":
			switch (recipe.getMinimumTier())
			{
			case 0: // same as Case 1
			case 1:
				return IVariable.from(new ItemStack(BloodMagicItems.WEAK_BLOOD_ORB.get()));
			case 2:
				return IVariable.from(new ItemStack(BloodMagicItems.APPRENTICE_BLOOD_ORB.get()));
			case 3:
				return IVariable.from(new ItemStack(BloodMagicItems.MAGICIAN_BLOOD_ORB.get()));
			case 4:
				return IVariable.from(new ItemStack(BloodMagicItems.MASTER_BLOOD_ORB.get()));
			// case 5: return IVariable.from(new
			// ItemStack(BloodMagicItems.ARCHMAGES_BLOOD_ORB.get()));
			// case 6: return IVariable.from(new
			// ItemStack(BloodMagicItems.TRANSCENDENT_BLOOD_ORB.get()));
			default:
				LogManager.getLogger().warn("Guidebook unable to find large enough Blood Orb for {}", recipe.getId());
				return IVariable.from(new ItemStack(Items.BARRIER));
			}
		default:
			return null;
		}
	}

}
