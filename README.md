# PlayerStatueBuilder
SkyTechBytes' Player Statue Builder. Supports basically all skin formats!
<h1>PlayerStatueBuilderX</h1>
<h2>Description</h2>
Need something to decorate your server? Tired of building player statues by hand? A tad bit conceited? If you answered yes to any of these questions, this player statue builder is perfect for you!
<h2>Installation</h2>
<li>1. Download the .jar below
<li>2. Move the .jar to your "plugins" folder in your spigot/bukkit/craftbukkit etc. server folder
<li>3. Restart your server
<li>4. Type "/statue USERNAME" to generate a statue for the specified USERNAME
<h2>Features</h2>
<li>Usage: "/statue USERNAME [default|slim|legacy] [xy|xz|yz]"
<li>Usage: "/undostatue" (Admin)
<li>Supports the <strong>new skin type (sleeves, pantlegs, jacket, etc.)</strong>
<li>Supports the <strong>"slim"</strong> skin format with 3 pixel arms
<li>Supports the older skin type without sleeves, pantlegs, or a jacket
<li><strong>So it basically supports all skins!</strong>
<li>The default setting is "default" 
<li>Also supports different skin orientations (lying down, lying on side, standing up)
<li> So you would type "/statue USERNAME slim xz" if you would like to generate a skin with the user USERNAME, with a skin format of "slim", and having the statue laying down.
<li>WorldGuard Support (also works without WorldGuard)!!!
<li>Informs the player of what blocks/items are needed to build the statue.
<li>Allows building of a statue ONLY if the uncolored/undyed blocks are in inventory (Admins bypass this) w/ Terracotta, Wood, Wool, and Diamonds (Diamond Cost = number of blocks divided by 16) - for survival/normal players. Configurable!
<li>Multithreaded so it shouldn't lag your server 
<li>Rotates the statue so that it faces you (Supports 4 directions + the aforementioned xy,xz,yz, so 12 orientations in total!)
<li>If used by a survival/normal player, the plugin won't delete blocks- only modifies air. This prevents accidental modification of the terrain, and eliminates the possibility that the statue will destroy bedrock or other stuff it shouldn't destroy.
<li>Spawns the statue two blocks above the player to prevent suffocation.
<li>Undo-ing a statue affects only the last statue created by ANY player (Admin only)
<li>Config file supports cooldowns (min), extra items required, and # of extra items required
  <h2>Permissions</h2>
   <pre>
   playerstatuebuilderx.createStatue:
      description: Allow creation of a statue
      default: true
   playerstatuebuilderx.bypass:
      description: Create a statue, bypassing material requirements
      default: op
   playerstatuebuilderx.specialOrientation:
      description: Allows creation of statues in the xz and yz plane. Orientation unpredictable so Admin only!
      default: op
   playerstatuebuilderx.override:
      description: Player statue is created and overwrites blocks (usually just skips if not air)
      default: op
   playerstatuebuilderx.noWait:
      description: The player does not need to wait out the cooldown to make the statue - 5 minutes is default
      default: op
   playerstatuebuilderx.undo:
      description: The player is allowed to undo the last statue created by ANYONE on the server (no material refunds). You must have "override" permissions for this to work
      default: op
      </pre>
  <h2>Configuration File Explanation</h2>
    <pre>
    # PlayerStatueBuilderX Configuration File
    # For the number of blocks below inside the statue total, add one of the "charge" item to the total
    # So if rate is 16 and the total blocks in the statue is 512, the player would need 512/16 = 32 of the "charge" item (in this case, Diamond)
      rate: 16
    # Cooldown time for using the command in MINUTES (No decimals, set to zero for no cooldown). So 5 means 5 minutes between SUCCESSFUL statue CREATIONS.
      cooldown: 5
    # The "rate" determines how many extra of this "charge" item a player will need. Ex: EMERALD, REDSTONE, DIAMOND, etc.
      charge: DIAMOND
    </pre>
<h2>Gallery</h2>
![2019-12-24_14 56 38](https://user-images.githubusercontent.com/36645753/71426391-a5946000-265d-11ea-8f40-76a392d1133c.png)
![2019-12-24_14 56 21](https://user-images.githubusercontent.com/36645753/71426392-a5946000-265d-11ea-9dc7-929790f2dfa2.png)
<br>
<a href="https://imgur.com/a/m3HXHHj">Or Use Imgur to see them (click here)</a>
