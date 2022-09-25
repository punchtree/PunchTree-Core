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

public class GameBrowserControl {

	private static final Component NAME = Component.text("Browse Games");

	private final ItemStack item = constructItem();
	private final Inventory gameBrowserMenu = Bukkit.createInventory(null, 9 * 3, NAME);

	public GameBrowserControl() {
		setupMenuContents();
	}

	private void setupMenuContents() {

	}

	public Inventory getGlobalNavMenu() {
		return gameBrowserMenu;
	}


	private ItemStack constructItem() {
		ItemStack gameBrowserItem = new ItemStack(Material.WOODEN_SWORD);
		ItemMeta im = gameBrowserItem.getItemMeta();

		im.displayName(NAME
				.color(TextColor.color(255, 88, 11))
				.decorate(TextDecoration.ITALIC));

		gameBrowserItem.setItemMeta(im);
		UsableItemHandler.getInstance().setupAsUsable(gameBrowserItem, "PvpMinigameBrowser", this::openGameBrowserMenu);
		return gameBrowserItem;
	}

	private void openGameBrowserMenu(Player player) {
		player.openInventory(getGlobalNavMenu());
	}

	public ItemStack getItem() {
		return item;
	}

}
