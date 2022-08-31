# Name-Spoofer

For Forge 1.8.9

Change **ANY** text displayed on your screen visually. 

*It does not actually change any strings and is client side (If you spoof a 1 character string to 5 character it will actually be 1 character, only it will appears as 5)



Does not only work on names. Works on: nametags, chest, furnace, inventory, any Minecraft item, other mods, gui, tablist, chat, nicknider name, any text etc.


A revamp of fakenames by [ayleafs](https://github.com/ayleafs/fake-names) (Heavily Modified)

### - [Download](https://github.com/TheExploration/Name-Spoofer/releases/download/1.4/name-spoofer-1.4.jar) -


## Commands

- `/spoof` - Resets everything back to normal
- `/spoof <name>` - Changes any instance of your username to <name>. Basically Nickhider but you decide your name.
- `/spoof <target> <name>` - Changes any instance of `<target>` to `<name>`. (<name> can have spaces)
- `/remove <target>` - Hides the string `<target>`.
- `/spooffirst <string>` - Used with `/spoofsecond`. (`<string>` can have spaces. Can spoof text with spaces as long as it's the same color.)
- `/spoofsecond <string2>` - Changes whatever string that is set as spooffirst to `<string2>`. (`<string2>` can have spaces)

NOTE: THESE APPLY TO ANY (LITERALLY ANY) TEXT THAT APPEARS ON YOUR SCREEN (IT MAY MAKE YOUR CHAT HISTORY CONFUSING TO READ)

## Examples
 Paired with boomboompower's skinchanger you can fake just about anyone.
  
**Example: Change your minecraft ign to Dream** 

`/spoof <name> &c[&fYOUTUBE&c] Dream`

**Example: Give Youtube rank to everyone on hypixel**
1. `/spoof [ &c[`
2. `/spoof ] &c]`
3. `/remove +`
4. `/spoof MVP &fYOUTUBE`
5. `/spoof VIP &fYOUTUBE`
6. `/spoof <playername> &c<Youtuber>`

 NOTE: Remove the + separately from MVP and VIP because the color is different from the rank.


<details>
  <summary>Color Codes</summary>
 
  ![color codes](https://github.com/TheExploration/Name-Spoofer/blob/main/demo/colorcodes.PNG)
 
  ![color codes](https://github.com/TheExploration/Name-Spoofer/blob/main/demo/othercolor%20codes.PNG)
</details>


## Images
![bedwars](https://github.com/TheExploration/Name-Spoofer/blob/main/demo/bedwar.png)

![owner](https://github.com/TheExploration/Name-Spoofer/blob/main/demo/bridgeowner.png)
![tablist](https://github.com/TheExploration/Name-Spoofer/blob/main/demo/tablistspoof.png)

## Features
- Saves the names you spoof!!
- Works with replay mod and NickHider mod
- Saves color of text and works with color codes (ex: &c[OWNER])
-Added the ability to spoof text with spaces so you can spoof entire name with a rank.
- Changes text visually; It can change the text that is displayed on the screen, such as item names as well and text in vanilla Minecraft

## Notes
- May affect fps if there is a lot of text displayed (such as when you press tab in bedwars lobby one)
- Cannot remove color codes as they are invisible and not shown on the screen (if it could it would be disallowed as it would be able to remove duels nametag obfuscation since im pretty sure it uses &k as the color code)
- The release jar is very large because the entirety of Jackson is in there
