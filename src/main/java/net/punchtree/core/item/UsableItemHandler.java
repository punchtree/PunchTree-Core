package net.punchtree.core.item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.punchtree.core.PunchTreeCorePlugin;

public class UsableItemHandler implements Listener {

	private final NamespacedKey key;

	private UsableItemHandler() {
		this.key = new NamespacedKey(PunchTreeCorePlugin.getInstance(), "item");
	}

	private static UsableItemHandler instance;
	public static UsableItemHandler getInstance() {
		if (instance == null) {
			instance = new UsableItemHandler();
		}
		return instance;
	}

	/**
	 * Maps item names (as stored in persistent data containers on the itemstacks) to
	 * callbacks
	 */
	private Map<String, Consumer<Player>> itemNameToOnUseConsumer = new HashMap<>();

	public void setupAsUsable(ItemStack item, String name, Consumer<Player> onUse) {
		PersistentDataContainer dataContainer = item.getItemMeta().getPersistentDataContainer();
		dataContainer.set(key, PersistentDataType.STRING, name);

		itemNameToOnUseConsumer.put(name, onUse);
	}

	@EventHandler
	public void onRightClickWithItem(PlayerInteractEvent event) {
		if (!isRightClick(event)) return;
		// TODO test if leaving mainhand enabled causes any problems versus disabling it
//		if (!isMainHand(event)) return;

		Player player = event.getPlayer();
		ItemStack item = event.getItem();

		if (!item.hasItemMeta()) return;
		ItemMeta im = item.getItemMeta();

		PersistentDataContainer dataContainer = im.getPersistentDataContainer();
		String name = im.getPersistentDataContainer().get(key, PersistentDataType.STRING);
		if (name == null) return;

		itemNameToOnUseConsumer.get(name).accept(player);
	}

	private boolean isRightClick(PlayerInteractEvent event) {
		return event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK;
	}

	private boolean isMainHand(PlayerInteractEvent event) {
		return event.getHand() == EquipmentSlot.HAND;
	}

}
