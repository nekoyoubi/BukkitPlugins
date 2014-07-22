Configuration
==========
Gods are now completely configurable. Also, this allows for the advent of your own custom gods. New gods can be defined in the config.yml that's created upon running the plugin for the first time. Below is a detailed breakdown of a Blessings config.yml file.

gods
-------
Nothing currently lives under `gods` alone.

gods.list
------------
This is the list of gods that will be loaded and used. Having this list allows you to easily turn on or off the presence of a god without deleting all of its data.

gods.`<godname>`
-----------------------
This is the main container for your god.

gods.`<godname>`.name
-------------------------------
The name of the god typically seen in practice.

god.`<godname>`.color
----------------------------
The color associated with the god throughout the plugin. A color chart can be found [on this post on the forums](http://forums.bukkit.org/threads/what-are-the-color-codes.7323/).

gods.`<godname>`.description
-------------------------------------
This is the description displayed in the `/blessings gods` list and in the `/blessings <godname> ` information.

gods.`<godname>`.bases
--------------------------------
This is a list of block ids that when under a gold block will act as a shrine for this god.

gods.`<godname>`.ignoresBelow
--------------------------------
Attempting to offer to this god below this number will not warrant the god's time and (s)he will ignore you outright, resulting in no loss of experience and no blessings or curses.

gods.`<godname>`.angersBelow
--------------------------------
Offer to this god below this number will cause curse rolls to take place, but blessings will still be rolled on.

gods.`<godname>`.messages.please
---------------------------------------------
This message was displayed when the god performed a blessing, but is currently *"legacy"*. It will most likely be used in the future, so it would be best to include it, though.

gods.`<godname>`.messages.anger
---------------------------------------------
This message is displayed when you offer to a god at a level below `gods.<godname>.angersBelow` and curses are rolled for.

gods.`<godname>`.messages.ignore
---------------------------------------------
This message is displayed when you attempt to offer to a god at a level below `gods.<godname>.ignoresBelow`.

gods.`<godname>`.blessings
--------------------------------
This is the list of possible blessings that the god will choose to bestow on you and others. Blessings are rolled at complete random until one is "won". The format for a blessings entry is as follows.

*EXAMPLE:*

    - 10@500 give player;random 351:1r1-10;351:14x1-20

In the above example, the blessing can be parsed as such:

    - level@chance action target;target item:data random min-max;item:data scaled min-max

Here is a brief explanation on what these terms means and how they are used:

* __level__ - The intended level of the blessing. The farther away (up or down) the players level at offering from this number, the less likely this item will be given.  

* __chance__ - The chance in 1000 that the blessing will be given if selected. The roll (1-1000) times a 5% __level__ deviation modifier must be less than this number for this blessing to process.  

* __action__ - This is what the blessing actually *does*, which in this case we are only discussing the **give** action, so we will just assume this to be static for the moment.  

* __target__ - The target of the action. Targets can be stacked together with a semi-colon (";"), and possible options are `player`, `random`, and `world`.  

* __item__ - This is the item id of what the target will be given. Item sets can be created by joining them with a semi-colon (";").  

* __data__ - This is the data value of the item the target will be given.  

    __NOTE:__ A list of item ids and data values can be found [on the Minecraft wiki](http://www.minecraftwiki.net/images/8/8c/DataValuesBeta.png).

* __random__ - This indicates that the amount of items to be given will vary randomly and is designated by prefixing the *min-max* with an "r" (e.g. `r1-10`).

* __scaled__ - This indicates that the amount of items to be given will scale depending on the player's level (between 1 and 20) and is designated by prefixing the *min-max* with an "x" (e.g. `x1-10`). By this method, a player at level 7 on an amount of "x16-64" would be awarded 33 of the item.

gods.`<godname>`.curses
--------------------------------
This is the list of possible curses that the god will roll on when offered to by a player at a level lower than `angersBelow`. Curses, unlike blessings, are not re-rolled if unsatisfied. Therefore, it is completely likely that a player will be able to anger the god and not see any punishment for it if the numbers are in their favor.

Curses, like all other non-`give` actions need further elaboration, but until they are fleshed out more and the documentation can be written up, feel free to review the code for yourself up on [github](https://github.com/Nekoyoubi/Blessings/).