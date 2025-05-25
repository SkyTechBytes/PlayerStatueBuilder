# PlayerStatueBuilder / SkinStatueBuilder
SkyTechBytes' Player Statue Builder. Supports basically all skin formats!
# PlayerStatueBuilderX
## Description
Need something to decorate your server? Tired of building player statues by hand? A tad bit conceited? If you answered yes to any of these questions, this player statue builder is perfect for you!

## Installation

 1. Download the .jar below
 2. Move the .jar to your "plugins" folder in your spigot/bukkit/craftbukkit etc. server folder
 3. Restart your server
 4. Type `/statue USERNAME` to generate a statue for the specified USERNAME

## Basics

- Usage: `/statue USERNAME [glass|concrete|terracotta|planks|gray|wool] [default|slim|legacy] [xy|xz|yz] [iron_armor|diamond_armor|chainmail_armor|golden_armor] [left_leg|right_leg|body|head|left_arm|right_arm] [hue:0-1|saturation:0-1|brightness:0-1|contrast:0-1|posterize:LEVEL]`
- Console/Command Block Usage: `/statue USERNAME x:[x coord] y:[y coord] z:[z coord] direction:[North|South|East|West] world:[world] [glass|concrete|terracotta|planks|gray|wool] [default|slim|legacy] [xy|xz|yz] [iron_armor|diamond_armor|chainmail_armor|golden_armor] [left_leg|right_leg|body|head|left_arm|right_arm] [hue:0-1|saturation:0-1|brightness:0-1|contrast:0-1|posterize:LEVEL]`
- Usage: `/undostatue` (Admin) You can now UNDO more than just your last statue!

## Examples
| Example            | Command                                                                                     | Description                                                                                                                                                         |
|--------------------|---------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| EXAMPLE 1          | `/statue [USERNAME] glass`                                                                  | creates a statue made of glass only                                                                                                                                 |
| EXAMPLE 2          | `/statue [USERNAME] concrete`                                                               | creates a statue made of concrete only                                                                                                                              |
| EXAMPLE 3          | `/statue [USERNAME] terracotta planks`                                                      | creates a statue made of only terracotta and wood planks                                                                                                            |
| EXAMPLE 4          | `/statue [USERNAME] gray`                                                                   | creates a grayscale statue                                                                                                                                          |
| EXAMPLE 5          | `/statue [USERNAME] slim gray`                                                              | creates a grayscale statue that has the `slim` skin format (3px arms)                                                                                               |
| EXAMPLE 6          | `/statue [USERNAME] wool xz`                                                                | creates a statue made of only wool. The statue is lying down because of the `xz` parameter                                                                          |
| EXAMPLE 7          | `/statue [USERNAME] diamond_armor concrete`                                                 | creates a statue with diamond armor made only out of concrete                                                                                                       |
| EXAMPLE 8          | `/statue [USERNAME] wool glass chainmail_helmet iron_chestplate golden_boots`               | creates a statue with a chainmail helmet, iron chestplate, and golden boots, made only out of wool and glass                                                        |
| EXAMPLE 9          | `/statue [USERNAME] head right_arm slim`                                                    | creates the head and right arm components of the statue only with slim arms (Use this to build a statue in parts)                                                   |
| EXAMPLE 10         | `/statue [USERNAME] hue:.2 contrast:.3 posterize:3 saturation:.4 brightness:.5`             | shifts the hue of the image by .2, sets contrast at .3 (decreases contrast), posterizes the image with 3 levels, saturation reduced to .4, brightness reduced to .5 |
| CONSOLE EXAMPLE 11 | `/statue [USERNAME] x:-593 y:32 z:269 direction:North world:world`                          | console/command block only; builds a statue with the specified coordinates and world                                                                                |
| CONSOLE EXAMPLE 12 | `/statue [USERNAME] x:-593 y:32 z:269 direction:North world:world hue:.34 posterize:3 wool` | console/command block only; builds a statue with the specified coordinates and world with parameters                                                                |
| EXAMPLE 13         | `/statue [USERNAME] converter:lab`                                                          | creates a statue with color to block conversion based on the LAB color space which may result in more accurate colors                                               |
| EXAMPLE 14         | `/statue [USERNAME] converter:rgb`                                                          | creates a statue with color to block conversion based on the RGB color space which is the original color diff algorithm                                             |


## Features
- The plugin attempts to connect to a fallback API to grab skins if the Mojang API is down
- Now automatically creates a slim statue if the arms are less than 4 pixels wide (instead of having to type `slim`)
- Supports the <strong>new skin type (sleeves, pantlegs, jacket, etc.)</strong>
- Supports the <strong>`slim`</strong> skin format with 3 pixel arms
- Supports the older skin type without sleeves, pantlegs, or a jacket
- <strong>So it basically supports all skins!</strong>
- Supports the following block types: glass, concrete, terracotta, planks, gray-colored blocks, wool (you can combine them too!)
- The default setting is `default`
- Also supports different skin orientations (lying down, lying on side, standing up)
- So you would type `/statue USERNAME slim xz` if you would like to generate a skin with the user USERNAME, with a skin format of `slim`, and having the statue laying down.
- WorldGuard Support (also works without WorldGuard)!!!
- Vault Support (also works without Vault)!!!
- EssentialsX Economy Support (also works without EssentialsX)!!!
- Informs the player of what blocks/items are needed to build the statue.
- Allows building of a statue ONLY if the uncolored/undyed blocks are in inventory (Admins bypass this) w/ Terracotta, Wood, Wool, Concrete, and Diamonds (Diamond Cost = number of blocks divided by 16) - for survival/normal players. Configurable!
- Multithreaded so it shouldn't lag your server
- Rotates the statue so that it faces you (Supports 4 directions + the aforementioned xy,xz,yz, so 12 orientations in total!)
- If used by a survival/normal player, the plugin won't delete blocks- only modifies air. This prevents accidental modification of the terrain, and eliminates the possibility that the statue will destroy bedrock or other stuff it shouldn't destroy.
- Spawns the statue two blocks above the player to prevent suffocation.
- Undo-ing a statue affects only the last statue created by ANY player (Admin only)
- You can add armor to your player statues now (chainmail, golden, diamond, iron)! You can select a piece of armor by stating what type of armor and then adding `_helmet` , `_chestplate` , or `_boots` (ex: diamond_boots).
- The plugin uses bStats for plugin metrics; no personal information is stored and if you use worldedit/essentialsX you already use bStats. You may opt out by going to the configuration file under the `bStats` folder in your plugins folder.
- You can now create a player statue DIRECTLY from your computer (if you're an admin).
- Config file supports cooldowns (min), extra items required, and # of extra items required
## How to create a statue with a skin image directly from your computer
  1. Obtain your skin's .png file and copy it
  2. Go to your `plugins` folder in your spigot server folder
  3. Go to the `PlayerStatueBuilderX` folder
  4. Paste your skin's .png file here. Remember the name of the file!!! (Like `bestskin.png` or `bestskin`)
  5. Start your server
  6. Type `/statue .FILENAME` Make sure you ADD the period BEFORE you write the name of the file. REMOVE `.png` at the end of your filename when you type it into the command if you have it. Ex: Your file is named `bestSkinEver.png` so you would type `/statue .bestSkinEver`
  7. You can add `glass` `diamond_armor` etc. after it just like a normal skin statue to customize it!
## Permissions
```
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
playerstatuebuilderx.custom:
  description: The player can put a '.' in front to create a custom statue from the plugin's data folder
  default: true   
  </pre>
```
## Configuration File Explanation

```
# PlayerStatueBuilderX Configuration File
# For the number of blocks below inside the statue total, add one of the "charge" item to the total
# So if rate is 16 and the total blocks in the statue is 512, the player would need 512/16 = 32 of the "charge" item (in this case, Diamond)
  rate: 16
# Cooldown time for using the command in MINUTES (No decimals, set to zero for no cooldown). So 1 means 1 minute between SUCCESSFUL statue CREATIONS (or statue parts).
  cooldown: 1
# The "rate" determines how many extra of this "charge" item a player will need. Ex: EMERALD, REDSTONE, DIAMOND, etc.
  charge: DIAMOND
# Whether the plugin requires exact materials (ex: 10 blue_concrete and 7 red_concrete INSTEAD of 17 white_concrete)
  exact: false
# Cost (in $ - EssentialsX/Vault support) per block that will be placed (ex: 1000 block statue adds $2500 to the cost if priceRate is 2.5)
  priceRate: 0.0
```
## Gallery
![2019-12-24_14 56 38](https://user-images.githubusercontent.com/36645753/71426391-a5946000-265d-11ea-8f40-76a392d1133c.png)
    
![2019-12-24_14 56 21](https://user-images.githubusercontent.com/36645753/71426392-a5946000-265d-11ea-9dc7-929790f2dfa2.png)
<br>
<a href="https://imgur.com/a/m3HXHHj">Or Use Imgur to see them (click here)</a>
    <br>
    <a href="https://imgur.com/a/34qkFPs">1.7 Example (click)</a>
<br>
1.10 Example: 

![image](https://user-images.githubusercontent.com/36645753/118715520-8c52fd00-b7d8-11eb-857b-5ee5bfbdfd59.png)
