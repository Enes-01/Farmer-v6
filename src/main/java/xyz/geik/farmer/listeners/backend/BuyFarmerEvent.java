package xyz.geik.farmer.listeners.backend;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import xyz.geik.farmer.api.handlers.FarmerBoughtEvent;
import xyz.geik.farmer.api.managers.FarmerManager;
import xyz.geik.farmer.model.Farmer;
import xyz.geik.farmer.model.user.FarmerPerm;

/**
 * When someone buys farmer this event triggers to add user to farmer and adds farmer to cache
 */
public class BuyFarmerEvent implements Listener {

    /**
     * Constructor of class
     */
    public BuyFarmerEvent() {}

    /**
     * Farmer bought event contains;
     * <ul>
     *     <li>
     *         adds user of farmer
     *     </li>
     *     <li>
     *         adds farmer to cache value
     *     </li>
     * </ul>
     *
     * @param e FarmerBoughtEvent listener
     */
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void farmerBoughtEvent(@NotNull FarmerBoughtEvent e) {
        Farmer farmer = e.getFarmer();
        // Adds owner uuid to farmer
        farmer.addUser(e.getOwnerUUID(), Bukkit.getOfflinePlayer(e.getOwnerUUID()).getName(), FarmerPerm.OWNER);
        // Adds farmer to cache
        FarmerManager.getFarmers().put(e.getFarmer().getRegionID(), e.getFarmer());
    }
}
