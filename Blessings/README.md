Overview
========
Blessings allows players the ability to place shrines of numerous deities around the world and pay homage to their gods for various blessings (or curses if they're not careful).

The idea for this plugin, other than the fact that it's just fun, is that it allows players a reason to kill mobs in a survival world instead of simply enjoying complacency. Also, it gives server admins a way to limit the possible damage of the 1.8 XP orbs (lag on death disbursement) and give a purpose to having them before 1.9 gets here... or maybe even after, who knows? ;)

Blessings is currently being worked on every day, so development is "active" to say the least. As of yet, the plugin is being solely developed by [Nekoyoubi](/profiles/Nekoyoubi) with design considerations coming from our [Minecraft Server's](http://minecraft.nekoyoubi.com) patrons, but will hopefully gain some community support in the near future.

If you would like to help with its development, feel free to check it out at <https://github.com/Nekoyoubi/Blessings/>

More information may be available at <http://minecraft.nekoyoubi.com/wiki/Plugins/Blessings>... not sure. ;)

Usage
=====
Below is some basic usage information. This information is in a constant state of flux due to the infancy of this project, and as such, should not be relied on 100%. Check back often for updates both to the plugin, and the documentation.

Shrines
-------
Shrines are created by simply placing a golden block. What material your golden block is placed on determines the god the shine belongs to. Upon placing a shrine, you will be notified as to what god owns the shrine.

To find out what blocks to place the shrine on for a particular god, type `/blessings [godname]`

__Note:__ Replacing the block under the golden block *does* change its god *without* notification.

To use a shrine, simply right-click on the golden block. You will expend a level of faith (your tracked, but as of yet unused levels that were introduced in 1.8), and pay homage to the shrine's deity. This will hopefully gain you the favor of the god and award you with some beneficial effect or item.

There are varying amounts and grades of items given to higher or lower faiths. Finding the balance is up to you.

Levels
------
Since gods are completely configurable now, this information may not be completely accurate, but typically, gods do not want your zeroth (0) level and will simply turn you away. You should reach at least level one (1) to use a shrine, but some gods may require more.

The higher your level of faith, the better your odds of gaining the many blessings of the gods.

__Note:__ You can check your level by typing `/faith` or `/xp`.

Blessings
---------
Blessings given for your faith are randomly chosen and may effect you, someone else at random, or everyone else in your world depending on the blessing.

Examples of blessings could include...
* Giving you a diamond sword.
* Filling your food bar.
* Stopping a thunderstorm.
* Healing everyone in your world.

Curses
------
Using a shrine with less faith (at a lower level) than its god prefers runs the risk of insulting and angering said god. This is not advisable and may harm you, others in your world, or the entire world!

__Note:__ Curses are *not* guaranteed and *do not* negate blessings.

Examples of curses could include:
* Set the player on fire.
* Strike a random player with lightning.
* Draining the food level of everyone in the world.

Gods
====
Below is a list of the gods, their purpose, and a brief list of some of the possible blessings and curses they **may** or **may not** choose to bestow upon the player or world.

__Note:__ Not all blessings and curses are implemented yet, but they are well on their way. Again, the list below is meant to give you an *idea* of the god, not a flat loot table.

Ares
----
God of war and violence.

__Base:__ Sand, Sandstone  
__Blessings:__ Give weapons, crude armor, dogs of war, damage boosts  
__Curses:__ Setting players on fire, increasing damage taken  

Athena
----
Goddess of wisdom, civilization, and military strategy.

__Base:__ Brick, Stone Brick  
__Blessings:__ Armor, building materials, repair armor  
__Curses:__ Damage items  

Gaia
----
Goddess of the earth and all life.

__Base:__ Dirt, Grass  
__Blessings:__ Spawn animals, heal players, feed players, give food (e.g. apples) and other "natural" items  
__Curses:__ Drain health, fatigue players  

Hades
-----
God of the underworld.

__Base:__ Adminium (Bedrock), Obsidian  
__Blessing:__ Give items from the nether, Halloween-esque items (e.g. bones, web, jack-o-lanterns), various spelunking equipment and spoils  
__Curses:__ Drag players down towards the underworld  

Poseidon
-----
God of the seas.

__Base:__ Water, Lapis, Ice  
__Blessing:__ Give items from or for the seas  
__Curses:__ Cast the player out to sea  

Zeus
----
God of sky and thunder.

__Base:__ Air  
__Blessings:__ Give 'powered' items (e.g. pistons, powered rails, switches) and devices (e.g. watch, compass), stop thunderstorms  
__Curses:__ Strike players with lightning, start thunderstorms  