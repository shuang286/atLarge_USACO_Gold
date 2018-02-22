# atLarge_USACO_Gold
### atLarge problem in USACO Gold in Jan 2018

In this tree problem, we go from the leaves (dead ends) of the entire barn tree inwards, mapping every barn
with a number using recursion. I was able to assign the least number to every internal node by starting from
every leaf, and branching inwards from every leaf. That number represented the least number of barns it would
take to reach the leaves (distance from the nearest leaf) from each internal node.

Then we give every barn/node another number, the number of barns away from where the cow Bessie initially starts
(distance from Bessie's original position). To count how many farmers are needed to effectively catch Bessie,
I compare the two numbers (the distance from the nearest leaf and the distance from Bessie's initial position).
If a barn's distance from the nearest leaf is either the same or one less than the distance from
Bessie's original position, then I know we need a farmer, adding one to the final count.
