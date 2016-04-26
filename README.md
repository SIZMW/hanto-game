Hanto Game
=========================

## Description
This project is used to demonstrate a game implementation of Hanto built from specified user stories that describe how different variations of the game are played.

## Variations
#### Alpha Hanto
This variation is a basic game, where each player can place one (1) ```BUTTERFLY``` piece on the board. The first move must be at the origin (0, 0) and the sunsequent move must be next to a piece on the game board. The game ends after two (2) moves.

#### Beta Hanto
This variation is a continuation of Alpha Hanto, and adds the ability for each player to place one (1) ```BUTTERFLY``` and five (5) ```SPARROW``` pieces on the board. In addition, the pieces must always be placed next to another piece on the game board. The game ends after a maximum of twelve (12) moves.

#### Gamma Hanto
This variation adds more rules to the game. Each player can place one (1) ```BUTTERFLY``` and five (5) ```SPARROW``` pieces on the board. These pieces can then move by walking one space. In addition, the pieces must always be placed next to another piece of the same color, and not next to the opposite when introduced on the game board. The pieces may walk one hex only if they have sufficient room to "slide" out of their current position. The game ends after a maxmimum of forty (40) moves.

#### Delta Hanto
This variation focuses on larger restructuring and refactoring of the game, and also adds new rules. Players can now resign from the game when they do not have any valid moves remaining. Each player can place one (1) ```BUTTERFLY```, four (4) ```SPARROW```, and four (4) ```CRAB``` pieces on the board. ```BUTTERFLY``` pieces can walk one (1) space, ```CRAB``` pieces can walk up to three (3) spaces, and ```SPARROW``` pieces can fly an unlimited amount of spaces.

#### Epsilon Hanto
This variation adds rules to resignation. If a player resigns and there is actually a valid move for them to make, a ```HantoPrematureResignationException``` is thrown. Otherwise, the opponent is declared the winner. Each player can place one (1) ```BUTTERFLY```, two (2) ```SPARROW```, six (6) ```CRAB```, and four (4) ```HORSE``` pieces on the board. ```BUTTERFLY``` pieces can walk one (1) space, ```CRAB``` pieces can walk one (1) space, ```SPARROW``` pieces can fly five (5) spaces, and ```HORSE``` pieces can jump an unlimited number of spaces.

#### Hanto Player
// TODO

## Building
This project can be imported into [Eclipse](https://eclipse.org/downloads/) as a project, which can then be compiled.

## Execution
The project can be executed by running the [JUnit](http://junit.org/) test cases under the ```test``` directory.

#### Statistics
Coverage statistics can be generated using the [ECLEmma](http://eclemma.org/) plugin for Eclipse.

Auditing can be done with the [CodePro AnalytiX](https://marketplace.eclipse.org/content/codepro-analytix) plugin for Eclipse. Audits were run on all classes, removing any of the high or medium issues using a custom rule set.
