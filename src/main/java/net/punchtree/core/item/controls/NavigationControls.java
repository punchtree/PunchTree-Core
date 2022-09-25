package net.punchtree.core.item.controls;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class NavigationControls implements Listener {


	// PROFILE_ITEM is dynamic based on player's head, constructProfileItem(Player);
	private static final ItemStack SHOP_ITEM = constructShopItem();
	// CURRENT_GADGET_ITEM is dynamic based on player

	private final GlobalNavigationControl navigator = new GlobalNavigationControl();

	public NavigationControls() {

	}



	// TODO gadgets
	// TODO lore for hotbar items

	private static ItemStack constructProfileItem(Player player) {
		ItemStack profileItem = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta sm = (SkullMeta) profileItem.getItemMeta();

		sm.setOwningPlayer(player);

		sm.displayName(Component.text("Profile")
				.color(TextColor.color(0, 245, 129))
				.decorate(TextDecoration.ITALIC));

		profileItem.setItemMeta(sm);
		return profileItem;
	}

	private static ItemStack constructShopItem() {
		ItemStack shopItem = new ItemStack(Material.ENDER_CHEST);
		ItemMeta im = shopItem.getItemMeta();

		im.displayName(Component.text("Shop")
				.color(TextColor.color(170, 97, 96))
				.decorate(TextDecoration.ITALIC));

		shopItem.setItemMeta(im);
		return shopItem;
	}

	public void setupInventoryForPlayer(Player player) {
		Inventory inventory = player.getInventory();

		inventory.clear();

		/*
		 * Hotbar looks like this:
		 * 1 - Compass/Global navigatiions
		 * 2 - Sword/Local join-games
		 * 3 - Head/Profile/Social
		 * 4 - Coin/Shop
		 * 5 - Any cosmetic/gadget
		 */
		inventory.setItem(0, navigator.getItem());
//		inventory.setItem(1, GAME_BROWSER_ITEM);
		inventory.setItem(2, constructProfileItem(player));

		inventory.setItem(4, SHOP_ITEM);
	}


	@EventHandler
	public void onMenuDrag(InventoryDragEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onSwapHandItems(PlayerSwapHandItemsEvent event) {
		event.setCancelled(true);
	}


	private void openProfileMenu(Player player) {
		// TODO Auto-generated method stub

	}

	private void openGameBrowserMenu(Player player) {
		// TODO Auto-generated method stub

	}






	private void openShopMenu(Player player) {
		// TODO Auto-generated method stub

	}



}
