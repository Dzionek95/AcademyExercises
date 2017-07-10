# TicTacToeGame

**This is project was created to practise OOP, Server-Socket, TestNG, Git, Maven skills.**
 What is more some part of project is inherited after swap of code with Miłosz

## How to run ##
First of all you have to start Server 

**mvn exec:java -Dexec.mainClass="game.Server"**

After insterting all informations about game start Client

**mvn exec:java -Dexec.mainClass="game.Client"**

# Requirements

- [x] It is "best of three", though I can quit mid-way through.
- [x] Characters: O (naught) and X (cross)
- [x] Players have names and scores.
- [x] Winner has better score. Draw is possible.
- [x] Client Server 
## Interactive:
- [x] it should accept players instructions about each move
- [x] it should ask who begins
- [x] it informs about session result, who’s turn it is now and the like
- [x] Match gives points: win 3, draw 1, loss 0. 3 matches == game.
- [x] Game works with square or rectangular board.
- [x] Player wins, if he has unbroken line of his characters, in a row, in a column or diagonally.
- [x] Winning is announced in a message: Wygrywa O. O: 1 X: 0 (numbers are current points).
## Game is configurable:
- [x] Board dimensions: 3x3, 4x4, 99x101, etc. (user provides)
- [ ] Winning condition has variable number of characters: 3, 4, 5, etc. (user provides)
- [x] Game messages should have configurable target: console (System.out) or logs (for the sake of this exercise it’s OK to make it System.err), or external printer.
- [x] before game starts it asks who goes first, O or X
- [ ] We are bi-lingual: Polish and English are fine. In future we want to add more languages: messages are to be read from a file for chosen language. Choosing the language depends on configuration variable.

## My comment
- Some classes do not follow SRP 
- Some parts of my code do not follow DRY principle, because my Server is one - threaded. This basicly
means that I've hardcoded possible movements of players and server

