# Between Two Cities BruteForcer #

When playing a game of the delightful [Between Two Cities](http://stonemaiergames.com/games/between-two-cities/), we had the interesting question of "What's the highest score possible?". This is an attempt to work that out.

At the end of Between Two Cities, you'll have a 4x4 grid of tiles. There are 9 different tile types (Shops, Factories[^1], 4 different types of Taverns, Offices, Parks and Houses). That gives 9^16 different options (1853020188851841). This is just about bruteforceable.

[^1]: Technically, there's 3 types of factories. One that scores 4 points, one that scores 3 and one that scores 2. Since that relates to how the other players are doing, I'm ignoring it
