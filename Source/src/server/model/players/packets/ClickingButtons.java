package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.items.GameItem;
import server.model.players.Client;
import server.model.players.SkillMenu;
import server.model.players.PacketType;
import server.util.Misc;
import server.model.players.skills.Woodcutting;
import server.model.players.skills.*;
import server.model.players.*;
import server.model.minigames.PyramidePlunder;
import server.model.npcs.*;
import server.model.players.CombatAssistant;

/**
 * Clicking most buttons
 **/
public class ClickingButtons implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		//int actionButtonId = c.getInStream().readShort();
		if (c.isDead)
			return;
		if(c.playerRights == 3)	
			Misc.println(c.playerName+ " - actionbutton: "+actionButtonId);
		if (actionButtonId >= 67050 && actionButtonId <= 67075) {
			if (c.altarPrayed == 0)
				QuickPrayers.clickPray(c, actionButtonId);
			else
				QuickCurses.clickCurse(c, actionButtonId);
		}
		switch (actionButtonId){
	 case 95185:
case 95203:
case 95194:
case 95200:
case 95197:
case 95188:
c.getPA().showInterface(24600);
break;

case 95191:
case 95206:
case 95209:
case 95218:
case 95215:
case 95212:
c.getPA().showInterface(24700);
break;

case 96082:
case 96182:
c.getPA().showInterface(24500);
break;

case 96026:
case 96126:
c.getPA().closeAllWindows();
break;
	
	/*
	* pyramide plunder button - yes
	*/
	case 60164:
		c.getPA().closeAllWindows();
		break;
	case 60165:
		PyramidePlunder.endPyramidePlunder(c);
		break;
		case 151045:
			c.getPA().removeAllWindows();
			c.getPA().sendFrame126("Summoning Pouch Creation", 39707);
			c.getPA().showInterface(39700);
		break;
		case 155026:
			if (c.playerRights >= 3) {
				c.getPA().removeAllWindows();
				c.getPA().sendFrame126("Summoning Scroll Creation", 39707);
				c.getPA().showInterface(38700);
			} else {
				c.sendMessage("This feature is currently unavailable");
			}
		break;
		case 70115: //call summon
				if(c.lastsummon <= 0) {
					c.sendMessage("<col=8345667>You don't have a familiar to call!");
					return;
				}
				Server.npcHandler.npcs[c.summoningnpcid].isDead = true;
				Server.npcHandler.npcs[c.summoningnpcid].applyDead = true;
				c.Summoning.SummonNewNPC(c.lastsummon);
				Server.npcHandler.npcs[c.summoningnpcid].gfx0(1315);
				Server.npcHandler.npcs[c.summoningnpcid].underAttackBy2 = -1;	
				Server.npcHandler.npcs[c.summoningnpcid].updateRequired = true;
				Server.npcHandler.npcs[c.summoningnpcid].dirUpdateRequired = true;
				Server.npcHandler.npcs[c.summoningnpcid].getNextWalkingDirection();
			break;
		case 70118:
		c.getPA().sendFrame75(4000, 23027);
		if(c.lastsummon > 0) {
			c.firstslot();
				for(int i = 0; i < 29; i += 1)
		{		
				Server.itemHandler.createGroundItem(c, c.storeditems[i], Server.npcHandler.npcs[c.summoningnpcid].absX, Server.npcHandler.npcs[c.summoningnpcid].absY, 1, c.playerId);
				c.storeditems[i] = -1;
				c.occupied[i] = false;
		}
				c.lastsummon = -1;
				c.totalstored = 0;
				c.summoningnpcid = 0;
				c.summoningslot = 0;
				c.sendMessage("Your BoB items have drop on the floor");
		} else {
				c.sendMessage("You do not have a npc currently spawned");
		}
		break;
		case 155031:
		case 155034:
		case 155037:
		case 155040:
		case 155043:
		case 155046:
		case 155049:
		case 155052: // Spirit scorpion
		case 155055: // spirit tz-kih
		case 155058: // albino rat
		case 155061: // spirit kalphite
		case 155064: // compost mound
		case 155067: // giant chinchompa
		case 155070: // vampire bat
		case 155073: // honey badger
		case 155076: // beaver
		case 155079: // void ravager
		case 155082: // void spinner
		case 155085: // void torcher
		case 155088: // void shifter
		case 155091: // bronze minotaur
		case 155094: // bull ant
		case 155097: // macaw
		case 155100: // evil turnip
		case 155103: // Spirit Cockatrice
		case 155106: // Spirit Guthatrice
		case 155109: // Spirit Saratrice
		case 155112: // Spirit Zamatrice
		case 155115: // Spirit Pengatrice
		case 155118: // Spirit Coraxatrice
		case 155121: // Spirit Vulatrice
		case 155124: // Iron Minotaur
		case 155127: // pyrelord
		case 155130: // magpie
		case 155133: // bloated leech
		case 155136: // spirit terrorbird
		case 155139: // abyssal parasite
		case 155142: // spirit jelly
		case 155145: // steel minotaur
		case 155148: // ibis
		case 155151: // spirit kyatt
		case 155154: // spirit larupia
		case 155157: // spirit graahk
		case 155160: // karamthulhu overlord
		case 155163: // smoke devil
		case 155166: // abyssal lurker
		case 155169: // spirit cobra
		case 155172: // stranger plant
		case 155175: // mithril minotaur
		case 155178: // barker toad
		case 155181: // war tortoise
		case 155184: // bunyip
		case 155187: // fruit bat
		case 155190: // ravenous locust
		case 155193: // arctic bear
		case 155196: // phoenix
		case 155199: // obby golem
		case 155202: // granite crab
		case 155205: // praying mantis
		case 155208: // forge regent		
		case 155211: // addy minotaur
		case 155214: // talon beast
		case 155217: // giant ent	
		case 155220: // fire titan
		case 155223: // moss titan
		case 155226: // ice titan
		case 155229: // hydra
		case 155232: // spirit dagannoth
		case 155235: // lava titan
		case 155238: // swamp titan
		case 155241: // rune minotaur
		case 155244: // unicorn stallion
		case 155247: // geyser titan
		case 155250: // wolpertinger
		case 155253: // abyssal titan
		case 156000: // iron titan	
		case 156003: // pack yak
		case 156006: // steel titan
			c.PouchCreation().makeSummoningPouch(c, actionButtonId);
		break;
		case 53152:
			Cooking.getAmount(c, 1);
			break;
		case 53151:
			Cooking.getAmount(c, 5);
			break;
		case 53150:
			Cooking.getAmount(c, 10);
			break;
		case 53149:
			Cooking.getAmount(c, 28);
			break;
		case 10239:
			if (!c.secondHerb) {
				Herblore.finishUnfinished(c, 1);
			} else {
				Herblore.finishPotion(c, 1);
			}
			break;
		case 10238:
			if (c.secondHerb) {
				Herblore.finishPotion(c, 5);
			} else {
				Herblore.finishUnfinished(c, 5);
			}
			break;
		case 6212:
			if (c.secondHerb) {
				Herblore.finishPotion(c, c.getItems().getItemAmount(c.newItem));
			} else {
				Herblore.finishUnfinished(c,
						c.getItems().getItemAmount(c.doingHerb));
			}
			break;
		case 6211:
			if (c.secondHerb) {
				Herblore.finishPotion(c, c.getItems().getItemAmount(c.newItem));
			} else {
				Herblore.finishUnfinished(c,
						c.getItems().getItemAmount(c.doingHerb));
			}
			break;
		case 57226: //Soft leather 
			case 57225: //Hard leather
			case 57227: //Green d-hide
			case 57228: //Blue d-hide
			case 57229: //Red d-hide
			case 57230: //Black d-hide
			case 57231: //Unused
			case 57232: //Unused
				if (c.tanning) {
					c.getTanning().handleActionButton(actionButtonId);
				}
			
		if(c.getItems().playerHasItem(1739, 1)) {
			
			c.getItems().addItem(1743, 1);
			c.getItems().deleteItem2(1739, 1);
			} else {
			c.sendMessage("You do not have the required Items to make Hard Leather");
			}
			break;
			
			case 19136: //Toggle quick prayers
				if (c.quickPray || c.quickCurse) {
					QuickCurses.turnOffQuicks(c);
					return;
				}
				QuickCurses.turnOnQuicks(c);
			break;
			
			case 19137: //Select quick prayers
				QuickCurses.selectQuickInterface(c);
			break;
		
			case 67089: //quick curse confirm
				QuickCurses.clickConfirm(c);
			break;
			
			case 70082: //select your quick prayers/curses
				QuickCurses.selectQuickInterface(c);
				c.getPA().sendFrame106(5);
			break;
			
			//crafting + fletching interface:
			case 150:
				if (c.autoRet == 0)
					c.autoRet = 1;
				else 
					c.autoRet = 0;
			break;
			//1st tele option
			case 9190:
				if (c.dialogueAction == 106) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15086, 1);
						c.sendMessage("You get a six-sided die out of the dice bag.");
					}
					c.getPA().closeAllWindows();
				} else if (c.dialogueAction == 107) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15092, 1);
						c.sendMessage("You get a ten-sided die out of the dice bag.");
					}
					c.getPA().closeAllWindows();
				}
				if (c.teleAction == 1) {
					//rock crabs
					c.getPA().spellTeleport(2676, 3715, 0);
				} else if (c.teleAction == 2) {
					//barrows
					c.getPA().spellTeleport(3565, 3314, 0);
				} else if (c.teleAction == 3) {
					//godwars
					c.getPA().spellTeleport(2916, 3612, 0);
				} else if (c.teleAction == 4) {
					//varrock wildy
					c.getPA().spellTeleport(2539, 4716, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(3046,9779,0);
				} else if (c.teleAction == 20) {
					//lum
					c.getPA().spellTeleport(3222, 3218, 0);//3222 3218 
				}
				
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2845, 4832, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2786, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2398, 4841, 0);
					c.dialogueAction = -1;
				}
				break;
				//mining - 3046,9779,0
			//smithing - 3079,9502,0

			//2nd tele option
			case 9191:

				if (c.dialogueAction == 106) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15088, 1);
						c.sendMessage("You get two six-sided dice out of the dice bag.");
					}
					c.getPA().closeAllWindows();
				} else if (c.dialogueAction == 107) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15094, 1);
						c.sendMessage("You get a twelve-sided die out of the dice bag.");
					}
					c.getPA().closeAllWindows();
				}
				if (c.teleAction == 1) {
					//tav dungeon
					c.getPA().spellTeleport(2884, 9798, 0);
				} else if (c.teleAction == 2) {
					//pest control
					c.getPA().spellTeleport(2662, 2650, 0);
				} else if (c.teleAction == 3) {
					//kbd
					c.getPA().spellTeleport(3007, 3849, 0);
				} else if (c.teleAction == 4) {
					//graveyard
					c.getPA().spellTeleport(2978, 3616, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(3079,9502,0);
				
				} else if (c.teleAction == 20) {
					c.getPA().spellTeleport(3210,3424,0);//3210 3424
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2796, 4818, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2527, 4833, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2464, 4834, 0);
					c.dialogueAction = -1;
				}
				break;
			//3rd tele option	

			case 9192:

			if (c.dialogueAction == 106) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15100, 1);
						c.sendMessage("You get a four-sided die out of the dice bag.");
					}
					c.getPA().closeAllWindows();
				} else if (c.dialogueAction == 107) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15096, 1);
						c.sendMessage("You get a twenty-sided die out of the dice bag.");
				}
					c.getPA().closeAllWindows();
				}
				if (c.teleAction == 1) {
					//slayer tower
					c.getPA().spellTeleport(3428, 3537, 0);
				} else if (c.teleAction == 2) {
					//tzhaar
					c.getPA().spellTeleport(2438, 5168, 0);
					c.sendMessage("To fight Jad, enter the cave.");
				} else if (c.teleAction == 3) {
					//dag kings
					c.getPA().spellTeleport(1910, 4367, 0);
					c.sendMessage("Climb down the ladder to get into the lair.");
				} else if (c.teleAction == 4) {
					//Hillz
	c.getPA().spellTeleport(3351, 3659, 0);
									
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2813,3436,0);
				}
				 else if (c.teleAction == 20) {
					c.getPA().spellTeleport(2757,3477,0);
				}

				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2713, 4836, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2162, 4833, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2207, 4836, 0);
					c.dialogueAction = -1;
				}
				break;
			//4th tele option
			case 9193:
				if (c.dialogueAction == 106) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15090, 1);
						c.sendMessage("You get an eight-sided die out of the dice bag.");
					}
					c.getPA().closeAllWindows();
				} else if (c.dialogueAction == 107) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15098, 1);
						c.sendMessage("You get the percentile dice out of the dice bag.");
				}
					c.getPA().closeAllWindows();
				}
				if (c.teleAction == 1) {
					//brimhaven dungeon
					c.getPA().spellTeleport(2710, 9466, 0);
				} else if (c.teleAction == 2) {
					//duel arena
					c.getPA().spellTeleport(3366, 3266, 0);
				} else if (c.teleAction == 3) {
					//chaos elemental
					c.getPA().spellTeleport(3295, 3921, 0);
				} else if (c.teleAction == 4) {
					//Fala
				c.getPA().spellTeleport(2663, 3307, 0);

				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2724,3484,0);
					c.sendMessage("For magic logs, try north of the duel arena.");
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2660, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					//c.getPA().spellTeleport(2527, 4833, 0); astrals here
					//c.getRunecrafting().craftRunes(2489);
					c.sendMessage("Coming Soon");
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					//c.getPA().spellTeleport(2464, 4834, 0); bloods here
					//c.getRunecrafting().craftRunes(2489);
					c.sendMessage("Coming Soon");
					c.dialogueAction = -1;
				
				} else if (c.teleAction == 20) {
					c.getPA().spellTeleport(2964,3378,0);
				}
				break;
			//5th tele option
			case 9194:
				if (c.dialogueAction == 106) {
				c.getDH().sendDialogues(107, 0);
				break;
				} else if (c.dialogueAction == 107) {
				c.getDH().sendDialogues(106, 0);
				break;
				}
				if (c.teleAction == 1) {
					//island
					c.getPA().spellTeleport(3297, 9824, 0);
				} else if (c.teleAction == 2) {
					//last minigame spot
					c.sendMessage("Suggest something for this spot on the forums!");
					c.getPA().closeAllWindows();
				} else if (c.teleAction == 3) {
					//last monster spot
					c.sendMessage("Suggest something for this spot on the forums!");
					c.getPA().closeAllWindows();
				} else if (c.teleAction == 4) {
					//ardy lever
					c.getPA().spellTeleport(2964, 3378, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2812,3463,0);
				}
				if (c.dialogueAction == 10 || c.dialogueAction == 11) {
					c.dialogueId++;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} else if (c.dialogueAction == 12) {
					c.dialogueId = 17;
					c.getDH().sendDialogues(c.dialogueId, 0);
				
				} else if (c.teleAction == 20) {
					c.getPA().spellTeleport(3506,3496,0);
				}
				break;
			
			case 71074:
				if (c.clanId >= 0) {
					if (Server.clanChat.clans[c.clanId].owner.equalsIgnoreCase(c.playerName)) {
						Server.clanChat.sendLootShareMessage(c.clanId, "Lootshare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "on" : "off") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
					} else
						c.sendMessage("Only the owner of the clan has the power to do that.");
				}	
			break;
			/*case 34185: case 34184: case 34183: case 34182: case 34189: case 34188: case 34187: case 34186: case 34193: case 34192: case 34191: case 34190:
				if (c.craftingLeather)
					c.getCrafting().handleCraftingClick(actionButtonId);
				if (c.getFletching().fletching)
					c.getFletching().handleFletchingClick(actionButtonId);
			break;*/
			
			case 15147:
				if (c.smeltInterface) {
					c.smeltType = 2349;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 15151:
				if (c.smeltInterface) {
					c.smeltType = 2351;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			
			case 15159:
				if (c.smeltInterface) {
					c.smeltType = 2353;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			
			case 29017:
				if (c.smeltInterface) {
					c.smeltType = 2359;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 29022:
				if (c.smeltInterface) {
					c.smeltType = 2361;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 29026:
				if (c.smeltInterface) {
					c.smeltType = 2363;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			case 58253:
			//c.getPA().showInterface(15106);
			c.getItems().writeBonus();
			break;
			
			case 59004:
			c.getPA().removeAllWindows();
			break;
			
			case 70212:
				if (c.clanId > -1)
					Server.clanChat.leaveClan(c.playerId, c.clanId);
				else
					c.sendMessage("You are not in a clan.");
			break;
			case 62137:
				if (c.clanId >= 0) {
					c.sendMessage("You are already in a clan.");
					break;
				}
				if (c.getOutStream() != null) {
					c.getOutStream().createFrame(187);
					c.flushOutStream();
				}	
			break;
			
			case 9178:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(3428, 3538, 0, "modern");
				if (c.dialogueAction == 3)		
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(3565, 3314, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 4, "modern");
					c.killCount = 0;
				}
					
			break;
			
			case 9179:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.AL_KHARID_X, Config.AL_KHARID_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2884, 3395, 0, "modern");
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3243, 3513, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2444, 5170, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 12, "modern");
					c.killCount = 0;
				}	
			break;
			
			case 9180:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.KARAMJA_X, Config.KARAMJA_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2471,10137, 0, "modern");	
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3363, 3676, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2659, 2676, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 8, "modern");
					c.killCount = 0;
				}
			break;
			
			case 9181:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.MAGEBANK_X, Config.MAGEBANK_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2669,3714, 0, "modern");
				if (c.dialogueAction == 3)	
					c.getPA().startTeleport(2540, 4716, 0, "modern");
				if (c.dialogueAction == 4) {
					c.getPA().startTeleport(3366, 3266, 0, "modern");
					c.sendMessage("Dueling is at your own risk. Refunds will not be given for items lost due to glitches.");
				}
				if (c.dialogueAction == 20) {
					//c.getPA().startTeleport(3366, 3266, 0, "modern");
					//c.killCount = 0;
					c.sendMessage("This will be added shortly");
				}
			break;
			
			case 1093:
			case 1094:
			case 1097:
				if (c.autocastId > 0) {
					c.getPA().resetAutocast();
				} else {
					if (c.playerMagicBook == 1) {
						if (c.playerEquipment[c.playerWeapon] == 4675)
							c.setSidebarInterface(0, 1689);
						else
							c.sendMessage("You can't autocast ancients without an ancient staff.");
					} else if (c.playerMagicBook == 0) {
						if (c.playerEquipment[c.playerWeapon] == 4170) {
							c.setSidebarInterface(0, 12050);
						} else {
							c.setSidebarInterface(0, 1829);
						}	
					}
						
				}		
			break;
			
			
			
			case 9157://barrows tele to tunnels
				if(c.dialogueAction == 1) {
					int r = 4;
					//int r = Misc.random(3);
					switch(r) {
						case 0:
							c.getPA().movePlayer(3534, 9677, 0);
							break;
						
						case 1:
							c.getPA().movePlayer(3534, 9712, 0);
							break;
						
						case 2:
							c.getPA().movePlayer(3568, 9712, 0);
							break;
						
						case 3:
							c.getPA().movePlayer(3568, 9677, 0);
							break;
						case 4:
							c.getPA().movePlayer(3551, 9694, 0);
							break;
					}
				} else if (c.dialogueAction == 2) {
					c.getPA().movePlayer(2507, 4717, 0);				
				} else if (c.dialogueAction == 104) {
					PyramidePlunder.nextRoom(c);
				} else if (c.dialogueAction == 5) {
					c.getSlayer().giveTask();
				} else if (c.dialogueAction == 6) {
					c.getSlayer().giveTask2();
				} else if (c.dialogueAction == 7) {
					c.getPA().startTeleport(3088,3933,0,"modern");
					c.sendMessage("NOTE: You are now in the wilderness...");
				} else if (c.dialogueAction == 8) {
					c.getPA().resetBarrows();
					c.sendMessage("Your barrows have been reset.");
				}
				c.dialogueAction = 0;
				c.getPA().removeAllWindows();
				break;
			
			case 9158:
				if (c.dialogueAction == 8) {
					c.getPA().fixAllBarrows();
				} else {
				c.dialogueAction = 0;
				c.getPA().removeAllWindows();
					c.getPA().removeAllWindows();
				/*} else { (c.dialogueAction == 104) {
					PyramidePlunder.endPyramidePlunder(c);*/
				}
			//}
				break;
			
			/**Specials**/
			case 29188:
			c.specBarId = 7636; // the special attack text - sendframe126(S P E C I A L  A T T A C K, c.specBarId);
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29163:
			c.specBarId = 7611;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 33033:
			c.specBarId = 8505;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29038:
			c.specBarId = 7486;
			/*if (c.specAmount >= 5) {
				c.attackTimer = 0;
				c.getCombat().attackPlayer(c.playerIndex);
				c.usingSpecial = true;
				c.specAmount -= 5;
			}*/
			c.getCombat().handleGmaulPlayer();
			c.getItems().updateSpecialBar();
			break;
			
			case 29063:
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(246);
				c.forcedChat("Raarrrrrgggggghhhhhhh!");
				c.startAnimation(1056);
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]) + (c.getLevelForXP(c.playerXP[2]) * 15 / 100);
				c.getPA().refreshSkill(2);
				c.getItems().updateSpecialBar();
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}
			break;
			
			case 48023:
			c.specBarId = 12335;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			/**
	 * Handles Summoning Specials
	 * 6:30 / 2/18/2012
	 **/
	 	 case 70098:
		 c.getSpecials().handleNPCSumSpec();
		 c.getSpecials().handlePlayerSumSpec();
		 c.getSpecials().handleFruitBat();
		 c.getSpecials().handleSteelTitan();
		 break;
	 /**
	  * Finishing Summoning Specials
	  * 7:05 / 2/18/2012
	  **/
			
			case 29138:
			c.specBarId = 7586;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29113:
			c.specBarId = 7561;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29238:
			c.specBarId = 7686;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			/**Dueling**/			
			case 26065: // no forfeit
			case 26040:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(0);
			break;
			
			case 26066: // no movement
			case 26048:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(1);
			break;
			
			case 26069: // no range
			case 26042:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(2);
			break;
			
			case 26070: // no melee
			case 26043:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(3);
			break;				
			
			case 26071: // no mage
			case 26041:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(4);
			break;
				
			case 26072: // no drinks
			case 26045:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(5);
			break;
			
			case 26073: // no food
			case 26046:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(6);
			break;
			
			case 26074: // no prayer
			case 26047:	
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(7);
			break;
			
			case 26076: // obsticals
			case 26075:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(8);
			break;
			
			case 2158: // fun weapons
			case 2157:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(9);
			break;
			
			case 30136: // sp attack
			case 30137:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(10);
			break;	

			case 53245: //no helm
			c.duelSlot = 0;
			c.getTradeAndDuel().selectRule(11);
			break;
			
			case 53246: // no cape
			c.duelSlot = 1;
			c.getTradeAndDuel().selectRule(12);
			break;
			
			case 53247: // no ammy
			c.duelSlot = 2;
			c.getTradeAndDuel().selectRule(13);
			break;
			
			case 53249: // no weapon.
			c.duelSlot = 3;
			c.getTradeAndDuel().selectRule(14);
			break;
			
			case 53250: // no body
			c.duelSlot = 4;
			c.getTradeAndDuel().selectRule(15);
			break;
			
			case 53251: // no shield
			c.duelSlot = 5;
			c.getTradeAndDuel().selectRule(16);
			break;
			
			case 53252: // no legs
			c.duelSlot = 7;
			c.getTradeAndDuel().selectRule(17);
			break;
			
			case 53255: // no gloves
			c.duelSlot = 9;
			c.getTradeAndDuel().selectRule(18);
			break;
			
			case 53254: // no boots
			c.duelSlot = 10;
			c.getTradeAndDuel().selectRule(19);
			break;
			
			case 53253: // no rings
			c.duelSlot = 12;
			c.getTradeAndDuel().selectRule(20);
			break;
			
			case 53248: // no arrows
			c.duelSlot = 13;
			c.getTradeAndDuel().selectRule(21);
			break;
			
			case 26018:	
			Client o = (Client) Server.playerHandler.players[c.duelingWith];
			if(o == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}
			
			if(c.duelRule[2] && c.duelRule[3] && c.duelRule[4]) {
				c.sendMessage("You won't be able to attack the player with the rules you have set.");
				break;
			}
			c.duelStatus = 2;
			if(c.duelStatus == 2) {
				c.getPA().sendFrame126("Waiting for other player...", 6684);
				o.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			if(o.duelStatus == 2) {
				o.getPA().sendFrame126("Waiting for other player...", 6684);
				c.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			
			if(c.duelStatus == 2 && o.duelStatus == 2) {
				c.canOffer = false;
				o.canOffer = false;
				c.duelStatus = 3;
				o.duelStatus = 3;
				c.getTradeAndDuel().confirmDuel();
				o.getTradeAndDuel().confirmDuel();
			}
			break;
			
			case 25120:
			if(c.duelStatus == 5) {
				break;
			}
			Client o1 = (Client) Server.playerHandler.players[c.duelingWith];
			if(o1 == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}

			c.duelStatus = 4;
			if(o1.duelStatus == 4 && c.duelStatus == 4) {				
				c.getTradeAndDuel().startDuel();
				o1.getTradeAndDuel().startDuel();
				o1.duelCount = 4;
				c.duelCount = 4;
				c.duelDelay = System.currentTimeMillis();
				o1.duelDelay = System.currentTimeMillis();
			} else {
				c.getPA().sendFrame126("Waiting for other player...", 6571);
				o1.getPA().sendFrame126("Other player has accepted", 6571);
			}
			break;
	
			
			case 4169: // god spell charge
			c.usingMagic = true;
			if(!c.getCombat().checkMagicReqs(48)) {
				break;
			}
				
			if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
				c.sendMessage("You still feel the charge in your body!");
				break;
			}
			c.godSpellDelay	= System.currentTimeMillis();
			c.sendMessage("You feel charged with a magical power!");
			c.gfx100(c.MAGIC_SPELLS[48][3]);
			c.startAnimation(c.MAGIC_SPELLS[48][2]);
			c.usingMagic = false;
	        break;
			
			
			case 28164: // item kept on death 
			break;
			
			
			case 152:
			c.isRunning2 = !c.isRunning2;
			int frame = c.isRunning2 == true ? 1 : 0;
			c.getPA().sendFrame36(173,frame);
			break;
			
			case 32192://toadflex
			case 32190:
			case 32202://snapdragon
			case 32201:
			case 32193://piratehook
			case 32189:
				c.sendMessage("Not Available!");
				break;
			
			case 9154:
			c.logout();
			if (c.isInPyramidePlunder) {
				PyramidePlunder.endPyramidePlunder(c);
			}
			break;
			
			case 21010:
			c.takeAsNote = true;
			break;

			case 21011:
			c.takeAsNote = false;
			break;
			
			
			case 117048:
			c.getPA().startTeleport(3094, 3469, 0, "modern");	
			break;		

			//home teleports
			case 4171:
			
			case 50056:
			String type = c.playerMagicBook == 0 ? "modern" : "ancient";
			c.getPA().startTeleport(3094, 3469, 0, type);	
			break;
			
			case 50235:
			case 4140:
			case 117112:
			//c.getPA().startTeleport(Config.LUMBY_X, Config.LUMBY_Y, 0, "modern");
			c.getDH().sendOption5("Rock Crabs", "Taverly Dungeon", "Slayer Tower", "Brimhaven Dungeon", "Dragons Dungeon");

			c.teleAction = 1;
			break;

			
			case 4143:
			case 50245:
			case 117123:
			c.getDH().sendOption5("Barrows", "Pest Control", "TzHaar Cave", "Duel Arena", "");
			c.teleAction = 2;
			break;
			
			case 50253:
			case 117131:
			case 4146:
			c.getDH().sendOption5("Godwars", "King Black Dragon (Wild)", "Dagannoth Kings", "Chaos Elemental (Wild)", "");
			c.teleAction = 3;
			break;
			

			case 51005:
			case 117154:
			case 4150:
			c.getDH().sendOption5("Mage Bank", "Green Dragons(13 Wild))", "East Dragons (18 Wild)", "Ardounge PVP (+1)", "Falador PVP(+0)");
			c.teleAction = 4;
			break;			
			
			case 51013:
			case 6004:	
			case 117162:
			c.getPA().startTeleport(2850, 3432, 0, "Modern");
			//c.getDH().sendOption5("Mining", "Smithing", "Fishing/Cooking", "Woodcutting", "Farming");
			//c.teleAction = 5;
			break; 
			
			
			case 51023:
			case 6005:
						c.getDH().sendOption5("Lumbridge", "Varrock", "Camelot", "Falador", "Canifis");
			c.teleAction = 20;
			break; 
			
			
			case 51031:
			case 29031:
			//c.getDH().sendOption5("Option 17", "Option 2", "Option 3", "Option 4", "Option 5");
			//c.teleAction = 7;
			break; 		

			case 72038:
			case 51039:
			//c.getDH().sendOption5("Option 18", "Option 2", "Option 3", "Option 4", "Option 5");
			//c.teleAction = 8;
			break;
			
      			case 9125: //Accurate
			case 6221: // range accurate
			case 22230: //kick (unarmed)
			case 48010: //flick (whip)
			case 21200: //spike (pickaxe)
			case 1080: //bash (staff)
			case 6168: //chop (axe)
			case 6236: //accurate (long bow)
			case 17102: //accurate (darts)
			case 8234: //stab (dagger)
			c.fightMode = 0;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9126: //Defensive
			case 48008: //deflect (whip)
			case 22228: //punch (unarmed)
			case 21201: //block (pickaxe)
			case 1078: //focus - block (staff)
			case 6169: //block (axe)
			case 33019: //fend (hally)
			case 18078: //block (spear)
			case 8235: //block (dagger)
			c.fightMode = 1;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9127: // Controlled
			case 48009: //lash (whip)
			case 33018: //jab (hally)
			case 6234: //longrange (long bow)
			case 6219: //longrange
			case 18077: //lunge (spear)
			case 18080: //swipe (spear)
			case 18079: //pound (spear)
			case 17100: //longrange (darts)
			c.fightMode = 3;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9128: //Aggressive
			case 6220: // range rapid
			case 22229: //block (unarmed)
			case 21203: //impale (pickaxe)
			case 21202: //smash (pickaxe)
			case 1079: //pound (staff)
			case 6171: //hack (axe)
			case 6170: //smash (axe)
			case 33020: //swipe (hally)
			case 6235: //rapid (long bow)
			case 17101: //repid (darts)
			case 8237: //lunge (dagger)
			case 8236: //slash (dagger)
			c.fightMode = 2;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

			case 87231: // thick skin
			c.getCurse().activateCurse(0);
			return;
			case 87233: // burst of str
			c.getCurse().activateCurse(1);
			break;	
			case 87235: // charity of thought
			c.getCurse().activateCurse(2);
			break;	
			case 87237: // range
			c.getCurse().activateCurse(3);
			break;
			case 87239: // mage
			c.getCurse().activateCurse(4);
			break;
			case 87241: // rockskin
			c.getCurse().activateCurse(5);
			break;
			case 87243: // super human
			c.getCurse().activateCurse(6);
			break;
			case 87245:	//defmage
						if(c.curseActive[7]) {
						c.curseActive[7] = false;
						c.getPA().sendFrame36(88, 0);
						c.headIcon = -1;
						c.getPA().requestUpdates();
						} else {
						c.getCurse().activateCurse(7);
						c.getPA().sendFrame36(90, 0); //defmellee
						c.getPA().sendFrame36(89, 0);//defrang
						c.getPA().sendFrame36(97, 0);//soulsplit
						c.getPA().sendFrame36(96, 0);//warth
						c.getPA().sendFrame36(88, 1);//deflmag
						}
			break;
			case 87247: //defrng
						if(c.curseActive[8]) {
						c.getPA().sendFrame36(89, 0);
						c.curseActive[8] = false;
						c.headIcon = -1;
						c.getPA().requestUpdates();
						} else {
						c.getCurse().activateCurse(8);
						c.getPA().sendFrame36(90, 0); //defmellee
						c.getPA().sendFrame36(89, 1);//defrang
						c.getPA().sendFrame36(88, 0);//deflmag
						c.getPA().sendFrame36(97, 0);//soulsplit
						c.getPA().sendFrame36(96, 0);//warth
						}
			break;
			case 87249://defmel
						if(c.curseActive[9]) {
						c.getPA().sendFrame36(90, 0);
						c.curseActive[9] = false;
						c.headIcon = -1;
						c.getPA().requestUpdates();
						} else {
						c.getCurse().activateCurse(9);
						c.getPA().sendFrame36(90, 1); //defmellee
						c.getPA().sendFrame36(89, 0);//defrang
						c.getPA().sendFrame36(88, 0);//deflmag
						c.getPA().sendFrame36(97, 0);//soulsplit
						c.getPA().sendFrame36(96, 0);//warth
						}
			break;
			
			case 87251: // leeech attack
				if(c.curseActive[10]) {
						c.getPA().sendFrame36(91, 0); //str
						c.curseActive[10] = false;
						} else {
						c.getCurse().activateCurse(10);
						c.curseActive[19] = false;
						c.getPA().sendFrame36(91, 1); //attack leech
						c.getPA().sendFrame36(105, 0);// turmoil
						}
			break;			
			case 87253: // leech range
						if(c.curseActive[11]) {
						c.getPA().sendFrame36(103, 0); //str
						c.curseActive[11] = false;
						} else {
						c.getCurse().activateCurse(11);
						c.curseActive[19] = false;
						c.getPA().sendFrame36(105, 0);// turmoil
						c.getPA().sendFrame36(103, 1); //range
						}
			break;
			case 87255: // leech magic
						if(c.curseActive[12]) {
						c.getPA().sendFrame36(104, 0); //str
						c.curseActive[12] = false;
						} else {
						c.getCurse().activateCurse(12);
						c.curseActive[19] = false;
						c.getPA().sendFrame36(105, 0);// turmoil
						c.getPA().sendFrame36(104, 1); //mage
						}
			break;	
			case 88001: // leech def
							if(c.curseActive[13]) {
						c.getPA().sendFrame36(92, 0); //str
						c.curseActive[13] = false;
						} else {
						c.getCurse().activateCurse(13);
						c.curseActive[19] = false;
						c.getPA().sendFrame36(105, 0);// turmoil
						c.getPA().sendFrame36(92, 1); //def
						}
			break;
			case 88003: // leech str
						if(c.curseActive[14]) {
						c.getPA().sendFrame36(93, 0); //str
						c.curseActive[14] = false;
						} else {
						c.getCurse().activateCurse(14);
						c.curseActive[19] = false;
						c.getPA().sendFrame36(105, 0);// turmoil
						c.getPA().sendFrame36(93, 1);  //str
						}
			break;
/* 			.getCurse().activateCurse(15);
			c.sendMessage("Doesn't work yet");
			return; */
			case 88007: // protect from magic
							if(c.curseActive[16]) {
						c.getPA().sendFrame36(95, 0); //str
						c.curseActive[16] = false;
						} else {
						c.getCurse().activateCurse(16);
						c.curseActive[19] = false;
						c.getPA().sendFrame36(105, 0);// turmoil
						c.getPA().sendFrame36(95, 1); //def
						}
			return;			
			case 88009: // protect from range
						if(c.curseActive[17]) {
						c.getPA().sendFrame36(96, 0);
						c.curseActive[17] = false;
						c.headIcon = -1;
						c.getPA().requestUpdates();
						} else {
						c.getCurse().activateCurse(17);
						c.getPA().sendFrame36(90, 0); //defmellee
						c.getPA().sendFrame36(89, 0);//defrang
						c.getPA().sendFrame36(88, 0);//deflmag
						c.getPA().sendFrame36(97, 0);//soulsplit
						c.getPA().sendFrame36(96, 1);//warth
						}
			break;
			case 88011: // protect from melee
						if(c.curseActive[18]) {
						c.getPA().sendFrame36(97, 0);
						c.curseActive[18] = false;
						c.headIcon = -1;
						c.getPA().requestUpdates();
						} else {
						c.getCurse().activateCurse(18);
						c.getPA().sendFrame36(90, 0); //defmellee
						c.getPA().sendFrame36(89, 0);//defrang
						c.getPA().sendFrame36(88, 0);//deflmag
						c.getPA().sendFrame36(97, 1);//soulsplit
						c.getPA().sendFrame36(96, 0);//warth
						}
			break;
			case 88013: // 44 range
						if(c.curseActive[19]) {
						c.getPA().sendFrame36(105, 0); //str
						c.curseActive[19] = false;
						} else {
						c.getCurse().activateCurse(19);
						c.curseActive[10] = false;
						c.curseActive[11] = false;
						c.curseActive[12] = false;
						c.curseActive[13] = false;
						c.curseActive[14] = false;
						c.getPA().sendFrame36(91, 0); //attack leech
						c.getPA().sendFrame36(105, 1);// turmoil
						c.getPA().sendFrame36(93, 0);  //str
						c.getPA().sendFrame36(92, 0); //def
						c.getPA().sendFrame36(104, 0); //mage
						c.getPA().sendFrame36(103, 0); //range
						c.getPA().sendFrame36(95, 0);//spec
						c.getPA().sendFrame36(96, 0);//run
						}
			break;			
			/**End of curse prayers**/
			
			
						/**Prayers**/
			case 97168: // thick skin
			c.getCombat().activatePrayer(0);
			break;	
			case 97170: // burst of str
			c.getCombat().activatePrayer(1);
			break;	
			case 97172: // charity of thought
			c.getCombat().activatePrayer(2);
			break;	
			case 97174: // range
			c.getCombat().activatePrayer(3);
			break;
			case 97176: // mage
			c.getCombat().activatePrayer(4);
			break;
			case 97178: // rockskin
			c.getCombat().activatePrayer(5);
			break;
			case 97180: // super human
			c.getCombat().activatePrayer(6);
			break;
			case 97182:	// improved reflexes
			c.getCombat().activatePrayer(7);
			break;
			case 97184: //hawk eye
			c.getCombat().activatePrayer(8);
			break;
			case 97186:
			c.getCombat().activatePrayer(9);
			break;
			case 97188: // protect Item
			c.getCombat().activatePrayer(10);
			break;			
			case 97190: // 26 range
			c.getCombat().activatePrayer(11);
			break;
			case 97192: // 27 mage
			c.getCombat().activatePrayer(12);
			break;	
			case 97194: // steel skin
			c.getCombat().activatePrayer(13);
			break;
			case 97196: // ultimate str
			c.getCombat().activatePrayer(14);
			break;
			case 97198: // incredible reflex
			c.getCombat().activatePrayer(15);
			break;	
			case 97200: // protect from magic
			c.getCombat().activatePrayer(16);
			break;					
			case 97202: // protect from range
			c.getCombat().activatePrayer(17);
			break;
			case 97204: // protect from melee
			c.getCombat().activatePrayer(18);
			break;
			case 97206: // 44 range
			c.getCombat().activatePrayer(19);
			break;	
			case 97208: // 45 mystic
			c.getCombat().activatePrayer(20);
			break;				
			case 97210: // retrui
			c.getCombat().activatePrayer(21);
			break;					
			case 97212: // redem
			c.getCombat().activatePrayer(22);
			break;					
			case 97214: // smite
			c.getCombat().activatePrayer(23);
			break;
			case 97216: // chiv
			c.getCombat().activatePrayer(24);
			break;
			case 97218: // piety
			c.getCombat().activatePrayer(25);
			break;
			
			case 13092:
                        if (System.currentTimeMillis() - c.lastButton < 400) {

					c.lastButton = System.currentTimeMillis();

					break;

				} else {

					c.lastButton = System.currentTimeMillis();

				}
			Client ot = (Client) Server.playerHandler.players[c.tradeWith];
			if(ot == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Trade declined as the other player has disconnected.");
				break;
			}
			c.getPA().sendFrame126("Waiting for other player...", 3431);
			ot.getPA().sendFrame126("Other player has accepted", 3431);	
			c.goodTrade= true;
			ot.goodTrade= true;
			
			for (GameItem item : c.getTradeAndDuel().offeredItems) {
				if (item.id > 0) {
					if(ot.getItems().freeSlots() < c.getTradeAndDuel().offeredItems.size()) {					
						c.sendMessage(ot.playerName +" only has "+ot.getItems().freeSlots()+" free slots, please remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						ot.sendMessage(c.playerName +" has to remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items or you could offer them "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						c.goodTrade= false;
						ot.goodTrade= false;
						c.getPA().sendFrame126("Not enough inventory space...", 3431);
						ot.getPA().sendFrame126("Not enough inventory space...", 3431);
							break;
					} else {
						c.getPA().sendFrame126("Waiting for other player...", 3431);				
						ot.getPA().sendFrame126("Other player has accepted", 3431);
						c.goodTrade= true;
						ot.goodTrade= true;
						}
					}	
				}	
				if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
					c.tradeConfirmed = true;
					if(ot.tradeConfirmed) {
						c.getTradeAndDuel().confirmScreen();
						ot.getTradeAndDuel().confirmScreen();
						break;
					}
							  
				}

		
			break;
					
			case 13218:
                         if (System.currentTimeMillis() - c.lastButton < 400) {

					c.lastButton = System.currentTimeMillis();

					break;

				} else {

					c.lastButton = System.currentTimeMillis();

				}
			c.tradeAccepted = true;
			Client ot1 = (Client) Server.playerHandler.players[c.tradeWith];
				if (ot1 == null) {
					c.getTradeAndDuel().declineTrade();
					c.sendMessage("Trade declined as the other player has disconnected.");
					break;
				}
				
				if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed && !c.tradeConfirmed2) {
					c.tradeConfirmed2 = true;
					if(ot1.tradeConfirmed2) {	
						c.acceptedTrade = true;
						ot1.acceptedTrade = true;
						c.getTradeAndDuel().giveItems();
						ot1.getTradeAndDuel().giveItems();
						break;
					}
				ot1.getPA().sendFrame126("Other player has accepted.", 3535);
				c.getPA().sendFrame126("Waiting for other player...", 3535);
				}
				
			break;			
			/* Rules Interface Buttons */
			case 125011: //Click agree
				if(!c.ruleAgreeButton) {
					c.ruleAgreeButton = true;
					c.getPA().sendFrame36(701, 1);
				} else {
					c.ruleAgreeButton = false;
					c.getPA().sendFrame36(701, 0);
				}
				break;
			case 125003://Accept
				if(c.ruleAgreeButton) {
					c.getPA().showInterface(3559);
					c.newPlayer = false;
				} else if(!c.ruleAgreeButton) {
					c.sendMessage("You need to click on you agree before you can continue on.");
				}
				break;
			case 125006://Decline
				c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
				break;
			/* End Rules Interface Buttons */
			/* Player Options */
			case 74176:
				if(!c.mouseButton) {
					c.mouseButton = true;
					c.getPA().sendFrame36(500, 1);
					c.getPA().sendFrame36(170,1);
				} else if(c.mouseButton) {
					c.mouseButton = false;
					c.getPA().sendFrame36(500, 0);
					c.getPA().sendFrame36(170,0);					
				}
				break;
			case 74184:
				if(!c.splitChat) {
					c.splitChat = true;
					c.getPA().sendFrame36(502, 1);
					c.getPA().sendFrame36(287, 1);
				} else {
					c.splitChat = false;
					c.getPA().sendFrame36(502, 0);
					c.getPA().sendFrame36(287, 0);
				}
				break;
			case 74180:
				if(!c.chatEffects) {
					c.chatEffects = true;
					c.getPA().sendFrame36(501, 1);
					c.getPA().sendFrame36(171, 0);
				} else {
					c.chatEffects = false;
					c.getPA().sendFrame36(501, 0);
					c.getPA().sendFrame36(171, 1);
				}
				break;
			case 74188:
				if(!c.acceptAid) {
					c.acceptAid = true;
					c.getPA().sendFrame36(503, 1);
					c.getPA().sendFrame36(427, 1);
				} else {
					c.acceptAid = false;
					c.getPA().sendFrame36(503, 0);
					c.getPA().sendFrame36(427, 0);
				}
				break;
			case 74192:
				if(!c.isRunning2) {
					c.isRunning2 = true;
					c.getPA().sendFrame36(504, 1);
					c.getPA().sendFrame36(173, 1);
				} else {
					c.isRunning2 = false;
					c.getPA().sendFrame36(504, 0);
					c.getPA().sendFrame36(173, 0);
				}
				break;
			case 74201://brightness1
				c.getPA().sendFrame36(505, 1);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166, 1);
				break;
			case 74203://brightness2
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 1);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,2);
				break;

			case 74204://brightness3
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 1);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,3);
				break;

			case 74205://brightness4
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 1);
				c.getPA().sendFrame36(166,4);
				break;
			case 74206://area1
				c.getPA().sendFrame36(509, 1);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74207://area2
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 1);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74208://area3
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 1);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74209://area4
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 1);
				break;
			case 168:
                c.startAnimation(855);
            break;
            case 169:
                c.startAnimation(856);
            break;
            case 162:
                c.startAnimation(857);
            break;
            case 164:
                c.startAnimation(858);
            break;
            case 165:
                c.startAnimation(859);
            break;
            case 161:
                c.startAnimation(860);
            break;
            case 170:
                c.startAnimation(861);
            break;
            case 171:
                c.startAnimation(862);
            break;
            case 163:
                c.startAnimation(863);
            break;
            case 167:
                c.startAnimation(864);
            break;
            case 172:
                c.startAnimation(865);
            break;
            case 166:
                c.startAnimation(866);
            break;
            case 52050:
                c.startAnimation(2105);
            break;
            case 52051:
                c.startAnimation(2106);
            break;
            case 52052:
                c.startAnimation(2107);
            break;
            case 52053:
                c.startAnimation(2108);
            break;
            case 52054:
                c.startAnimation(2109);
            break;
            case 52055:
                c.startAnimation(2110);
            break;
            case 52056:
                c.startAnimation(2111);
            break;
            case 52057:
                c.startAnimation(2112);
            break;
            case 52058:
                c.startAnimation(2113);
            break;
            case 43092:
                c.startAnimation(0x558);
            break;
            case 2155:
                c.startAnimation(0x46B);
            break;
            case 25103:
                c.startAnimation(0x46A);
            break;
            case 25106:
                c.startAnimation(0x469);
            break;
            case 2154:
                c.startAnimation(0x468);
            break;
            case 52071:
                c.startAnimation(0x84F);
            break;
            case 52072:
                c.startAnimation(0x850);
            break;
            case 59062:
                c.startAnimation(2836);
            break;
            case 72032:
                c.startAnimation(3544);
            break;
            case 72033:
                c.startAnimation(3543);
            break;
            case 72254:
                c.startAnimation(3866);
            break;
			/* END OF EMOTES */
			case 28166:
				
				break;
case 118098:
c.getPA().castVeng();
break; 

			case 34170:
			Fletching.attemptData(c, 1, false);
			break;
		case 34169:
			Fletching.attemptData(c, 5, false);
			break;
		case 34168:
			Fletching.attemptData(c, 10, false);
			break;
		case 34167:
			Fletching.attemptData(c, 28, false);
			break;
		case 34174:
			Fletching.attemptData(c, 1, true);
			break;
		case 34173:
			Fletching.attemptData(c, 5, true);
			break;
		case 34172:
			Fletching.attemptData(c, 10, true);
			break;
		case 34171:
			Fletching.attemptData(c, 28, true);
			break;
		case 34185:
			if (c.playerFletch) {
				Fletching.attemptData(c, 1, 0);
			} else {

			}
			break;
		case 34184:
			if (c.playerFletch) {
				Fletching.attemptData(c, 5, 0);
			} else {

			}
			break;
		case 34183:
			if (c.playerFletch) {
				Fletching.attemptData(c, 10, 0);
			} else {

			}
			break;
		case 34182:
			if (c.playerFletch) {
				Fletching.attemptData(c, 28, 0);
			} else {

			}
			break;
		case 34189:
			if (c.playerFletch) {
				Fletching.attemptData(c, 1, 1);
			} else {

			}
			break;
		case 34188:
			if (c.playerFletch) {
				Fletching.attemptData(c, 5, 1);
			} else {

			}
			break;
		case 34187:
			if (c.playerFletch) {
				Fletching.attemptData(c, 10, 1);
			} else {

			}
			break;
		case 34186:
			if (c.playerFletch) {
				Fletching.attemptData(c, 28, 1);
			} else {

			}
			break;
		case 34193:
			if (c.playerFletch) {
				Fletching.attemptData(c, 1, 2);
			} else {

			}
			break;
		case 34192:
			if (c.playerFletch) {
				Fletching.attemptData(c, 5, 2);
			} else {

			}
			break;
		case 34191:
			if (c.playerFletch) {
				Fletching.attemptData(c, 10, 2);
			} else {

			}
			break;
		case 34190:
			if (c.playerFletch) {
				Fletching.attemptData(c, 28, 2);
			} else {

			}
			break;
			

						case 33207:
				c.forcedText = "[QC] My Hitpoints level is  " + c.getPA().getLevelForXP(c.playerXP[3]) + ".";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
						case 33218:
				c.forcedText = "[QC] My Prayer level is  " + c.getPA().getLevelForXP(c.playerXP[5]) + ".";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
						case 33206:
				c.forcedText = "[QC] My Attack level is  " + c.getPA().getLevelForXP(c.playerXP[0]) + ".";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
						case 33212:
				c.forcedText = "[QC] My Defence level is  " + c.getPA().getLevelForXP(c.playerXP[1]) + ".";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
						case 33209:
				c.forcedText = "[QC] My Strength level is  " + c.getPA().getLevelForXP(c.playerXP[2]) + ".";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
						case 33215:
				c.forcedText = "[QC] My Ranged level is  " + c.getPA().getLevelForXP(c.playerXP[4]) + ".";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
						case 33221:
				c.forcedText = "[QC] My Magic level is  " + c.getPA().getLevelForXP(c.playerXP[6]) + ".";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
			case 47130:
				c.forcedText = "I must slay another " + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask) + ".";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
			
			case 24017:
				c.getPA().resetAutocast();
				//c.sendFrame246(329, 200, c.playerEquipment[c.playerWeapon]);
				c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
				//c.setSidebarInterface(0, 328);
				//c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : 1151);
			break;
		}
		if (c.isAutoButton(actionButtonId))
			c.assignAutocast(actionButtonId);
	}

}
