### How to host your own bot.

## TOC
* [Discord Setup](#Discord-Setup)
* [Code Setup](#Code-Setup)

# Discord Setup
First things first; go to [https://discord.com/developers](https://discord.com/developers) and sign in if you are not already.

You should see something like the following:

![Discord Developer Applications](https://imgur.com/OlNchaN.jpeg)

In the top right click on __New Application__.

Type in the name of your bot; I'll use "_Jermey_" for this demostration.

Next click __Create__.

![Application View](https://imgur.com/g9wmueq.jpeg)

On the left, go to __Bot__, and click __Add Bot__, then confirm by clicking __Yes, Do It!__.

Go to the __OAuth2__ tab; click bot, the scroll down and select premissions that the bot should have, choosing __Administrator__ will give it all permissions, so you don't have to worry about it.

Copy the link and paste it in a new tab; then invite it to your server.

Keep the Discord Developer tab open, we will use it later.

## Code Setup

_You dont need a code editor, but its strongly reccomended when following the next steps. I'll be using VSCode for this; you might want another tutorial on setting that up._

Ok first we need your bot toke, go to the __Bot__ tab, click __Copy__ under the bot token.

Now go to Secret.java