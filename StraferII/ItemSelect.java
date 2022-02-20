
import greenfoot.*;
import java.util.HashMap;

public class ItemSelect extends Menu {

    GreenfootImage overlay = new GreenfootImage("UI/hud/selectOverlay.png");

    private Player player;

    public ItemSelect(Player player) {
        this.player = player;
        setImage(overlay);
    }

    public String getItemSelected() {
        switch (Inventory.nrItem) {
            case 1: {
                return "sword";
            }
            case 2: {
                return "icelock";
            }
            case 3: {
                return "blackhole";
            }
            case 4: {
                return "lantern";
            }
            case 5: {
                return "laser";
            }
            case 6: {
                return "portalgun";
            }
        }
        return "";
    }

    private void select() {
        if (Greenfoot.mouseClicked(this)) {
            if (Greenfoot.getMouseInfo().getButton() == 1) {

                //System.out.println(super.getItemSelected());
                switch (getItemSelected()) {
                    case "sword": {
                        if (WorldData.hasSword) {
                            player.setEquipSword(!player.isEquipSword());
                        }
                        break;
                    }
                    case "icelock": {
                        if (WorldData.hasIceLock) {
                            player.setEquipIceLock(!player.isEquipIceLock());
                        }
                        break;
                    }
                    case "blackhole": {
                        if (WorldData.hasBlackHole) {
                            player.setEquipBlackHole(!player.isEquipBlackHole());
                        }
                        break;
                    }
                    case "lantern": {
                        if (WorldData.hasLantern) {
                            player.setEquipLantern(!player.isEquipLantern());
                        }
                        break;
                    }
                    case "laser": {
                        if (WorldData.hasLaser) {
                            player.setEquipLaser(!player.isEquipLaser());
                        }
                        break;
                    }
                    case "portalgun": {
                        if (WorldData.hasPortalGun) {
                            player.setEquipPortalGun(!player.isEquipPortalGun());
                        }
                        break;
                    }
                    default: {
                    }
                }
                player.setToggledInventory(false);

            }
        }
    }

    public void act() {
        select();
        setLocation(875, 430);
        if (!player.isToggledInventory()) {
            getWorld().removeObject(this);
        }

    }
}
