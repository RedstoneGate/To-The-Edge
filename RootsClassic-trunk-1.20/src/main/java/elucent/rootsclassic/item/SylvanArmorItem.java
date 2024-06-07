package elucent.rootsclassic.item;

import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import com.lothrazar.library.util.ItemStackUtil;
import elucent.rootsclassic.Const;
import elucent.rootsclassic.capability.RootsCapabilityManager;
import elucent.rootsclassic.client.ClientHandler;
import elucent.rootsclassic.client.model.SylvanArmorModel;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.fml.DistExecutor;

@SuppressWarnings("deprecation")
public class SylvanArmorItem extends ArmorItem {

  private final LazyLoadedValue<HumanoidModel<?>> model;

  public SylvanArmorItem(ArmorMaterial materialIn, ArmorItem.Type type, Item.Properties builderIn) {
    super(materialIn, type, builderIn);
    this.model = DistExecutor.unsafeRunForDist(() -> () -> new LazyLoadedValue<>(() -> this.provideArmorModelForSlot(type)),
        () -> () -> null);
  }

  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
    return Const.MODID + ":textures/models/armor/sylvan.png";
  }

  @OnlyIn(Dist.CLIENT)
  public HumanoidModel<?> provideArmorModelForSlot(ArmorItem.Type type) {
    return new SylvanArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ClientHandler.SYLVAN_ARMOR), type);
  }

  @Override
  public void onArmorTick(ItemStack stack, Level levelAccessor, Player player) {
    ItemStackUtil.randomlyRepair(levelAccessor.random, stack, 80);
    if (levelAccessor.random.nextInt(40) == 0) {
      player.getCapability(RootsCapabilityManager.MANA_CAPABILITY).ifPresent(cap -> {
        cap.setMana(cap.getMana() + 1.0f);
      });
    }
  }

  @Override
  public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
    return false;
  }

  @Override
  public void appendHoverText(ItemStack stack, @Nullable Level levelAccessor, List<Component> tooltip, TooltipFlag flagIn) {
    super.appendHoverText(stack, levelAccessor, tooltip, flagIn);
    tooltip.add(Component.empty());
    tooltip.add(Component.translatable("rootsclassic.attribute.equipped").withStyle(ChatFormatting.GRAY));
    tooltip.add(Component.literal(" ").append(Component.translatable("rootsclassic.attribute.increasedmanaregen")).withStyle(ChatFormatting.BLUE));
    tooltip.add(Component.empty());
    tooltip.add(Component.translatable("rootsclassic.attribute.fullset").withStyle(ChatFormatting.GRAY));
    tooltip.add(Component.literal(" +1 ").append(Component.translatable("rootsclassic.attribute.potency")).withStyle(ChatFormatting.BLUE));
  }

  @Override
  public void initializeClient(Consumer<IClientItemExtensions> consumer) {
    consumer.accept(new IClientItemExtensions() {

      @Override
      public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        return model.get();
      }
    });
  }
}
