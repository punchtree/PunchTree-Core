package net.punchtree.core.item.controls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.punchtree.core.item.UsableItemHandler;

public class GlobalNavigationControl {

	private static final Component NAME = Component.text("Navigator");


	private final ItemStack item = constructItem();
	private final Inventory globalNavigationMenu = Bukkit.createInventory(null, 9 * 3, NAME);

	public GlobalNavigationControl() {
		setupMenuContents();
	}

	private void setupMenuContents() {

	}

	public Inventory getGlobalNavMenu() {
		return globalNavigationMenu;
	}


	private ItemStack constructItem() {
		ItemStack globalNavItem = new ItemStack(Material.COMPASS);
		ItemMeta im = globalNavItem.getItemMeta();

		im.displayName(NAME
				.color(TextColor.color(241, 13, 61))
				.decorate(TextDecoration.ITALIC));

		globalNavItem.setItemMeta(im);
		UsableItemHandler.getInstance().setupAsUsable(globalNavItem, "GlobalNavigation", this::openGlobalNavigationMenu);
		return globalNavItem;
	}

	private void openGlobalNavigationMenu(Player player) {
		player.openInventory(getGlobalNavMenu());
	}

	public ItemStack getItem() {
		return item;
	}

}
