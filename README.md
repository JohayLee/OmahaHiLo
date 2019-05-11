**The design and algorithm of Poker Ohama Hi/Lo evaluation**

The general modeling follows the real-world activities:

Each line of input file is considered as a round of game with two players and cards on board. Reading a line from the input file is like dispatching cards. The application then tries to EvaluatePlayers, which tells each player to combine 2 of his/her received cards with the 3 picked from the board. All the 60 combination possibilities including both high and low rank, are filtered with ranking rules, i.e., high rank rules like straight, flush, 4-of-a-kind etc., and the low8 rank rule for each player. Then each the player finds the max-valued combinations for both high and low8. The comparison text result between the max-valued combinations of the two players are generated to write out to the output file. For every and each ranking rule, it has two important methods:

1. ApplyToHandOfCards - to check if a hand of 5 cards is qualified for the rule.

2. CompareCards - to decide which one of the two hand of cards is more valuable.

The algorithms for the above methods are described below for the ranking rules:

**Straight:**

ApplyToHandOfCards - sort rank descending and then check if the neighbor difference is 1.

CompareCards -compare rank one by one.

**Flush:**

ApplyToHandOfCards - Check if suits identical.

CompareCards -same as above.

**StraightFlush:**

ApplyToHandOfCards - Check both Straight and Flush.

CompareCards -same as above.

**4-of-a-kind:**

ApplyToHandOfCards - Construct rank to same-rank-count map, check if the map contains same-rank-count 4.

CompareCards -sort cards as most-repeated rank first, then compare by rank one by one.

**FullHouse:**

ApplyToHandOfCards - Construct rank to same-rank-count map, check if the map contains both same-rank-count 3 and same-rank-count 2.

CompareCards -sort cards as most-repeated rank first, then compare by rank one by one.

**3-of-a-kind:**

ApplyToHandOfCards - Construct rank to same-rank-count map, check if the map contains same-rank-count 3.CompareCards -sort cards as most-repeated rank first, then rank descending. Compare by rank one by one.

**One pair:**

ApplyToHandOfCards - Construct rank to same-rank-count map, check if the same-ranks occur once only.

CompareCards -sort cards as most-repeated rank first, then rank descending. Compare by rank one by one.

**Two pairs:**

ApplyToHandOfCards - Construct rank to same-rank-count map, check if the same-ranks occur twice.

CompareCards -sort cards as most-repeated rank first, as there are two pairs, which pair goes first based on its rank descending. Compare by rank one by one.

**High card:**

ApplyToHandOfCards - Always returns true.

CompareCards -sort cards descending. Compare by rank one by one.

**Low 8:**

ApplyToHandOfCards - Check to make sure none of the card is higher than 8. Also collect a distinct ranked hash-set, return false at the element adding if anyrank already exists. If none identical rank, return true.

CompareCards -sort cards descending. Compare by rank one by one.

To simplify, the program is not segregated to different layers, e.g., The ranking rule name etc. is just put into a rule object although it is supposed to be in the presentation layer.
