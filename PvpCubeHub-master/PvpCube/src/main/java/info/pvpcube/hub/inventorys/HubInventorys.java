package info.pvpcube.hub.inventorys;


public class HubInventorys {

    private static MinigameInventory minigamesInventory = new MinigameInventory();
    private static TrailsInventory trailsInventory = new TrailsInventory();
    private static KevinOrderInventory kevinInventory = new KevinOrderInventory();

    public static MinigameInventory getMinigamesInventory() {
        return minigamesInventory;
    }

    public static KevinOrderInventory getKevinInventory() {
        return kevinInventory;
    }

    public static TrailsInventory getTrailsInventory() {
        return trailsInventory;
    }

}
