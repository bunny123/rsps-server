package server.model.players.skills;

import server.model.players.Client;
import server.model.players.*;
import server.Config;
import server.util.Misc;
import server.model.players.ActionHandler;

	public class FlaxStringer {
	
	private Client c;
		
	public FlaxStringer(Client c) {
		this.c = c;
	}

	private final int FLAX[][] = {{1779, 1777, 1777, 40, 120}};
	private final int FLAXID[] = {1779};
	
	public void itemOnObject(int itemId) {
		for (int j = 0; j < FLAX.length; j++) {
			if (FLAX[j][0] == itemId)
				handleStringing(FLAX[j][0], j);
		}
	}
	
	public void handleStringing(int id, int slot) {
		if(c.playerLevel[12] < 40) {
		c.sendMessage("You need a Crafting level of " + FLAX[slot][3] + " to Craft this.");
			return;
		}
			if (c.getItems().playerHasItem(id,1)) {
				if (c.playerLevel[c.playerCrafting] >= FLAX[slot][3]) {
					c.startAnimation(896);
						c.getItems().deleteItem(id, c.getItems().getItemSlot(id), 1);
						c.getItems().addItem(FLAX[slot][1], 1);
						c.getPA().addSkillXP(FLAX[slot][4] * Config.CRAFTING_EXPERIENCE, c.playerCrafting);
						}
					}
				}
			}
