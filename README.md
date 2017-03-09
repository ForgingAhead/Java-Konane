# Java-Konane

At the beginning, I considered only two features of the game as part of the heuristic evaluation function which are number of pieces on board and number of possible valid moves for each player. But from the rule of the game, we can easily say that the number of pieces for both player on the board is the same most of the time. So I don't take into account that when considering the evaluation function. Then after some tests and I played with the game for some times, I found another feature, number of movable pieces on board, which is also very useful. 
   
   All my minimax is in depth of 4. Firstly considering the number of possible moves, players always want to choose the move that can make them have more number of possible valid moves but also want their opponents have less moves in the next few ply. So I wrote my first minimax player 10086617A using the evaluation function, h = 2*myMoves - opMoves, where myMoves stands for the number of possible moves for my minimax player in the current board and similar for opMoves. Then the test result is as following ( test was run in a 100-game tournament): 

SimplePlayer Vs 10086617A
<li>Board Size	    SimplePlayer Wins	    10086617A Wins</li>
<li>4	    0	    100</li>
<li>5	0	100</li>
<li>6	0	100</li>
<li>7	0	100</li>
<li>8	0	100</li>

RandomPlayer Vs 10086617A
<li>Board Size	SimplePlayer Wins	10086617A Wins</li>
<li>4	0	100</li>
<li>5	13	87</li>
<li>6	0	100</li>
<li>7	1	99</li>
<li>8	0	100</li>

   This result seems really good just because the strategy of its opponent is very simple. Then I wrote another test player, 10086617B using another evaluation function h = myMoves/opMoves. It's easy to say that the 10086617B player will get good results to play against simple or random player so I test it only with player 10086617A in a 10-game tournament as 100-game need too long time to run. Here is the result:

10086617B Vs 10086617A 
<li>Board Size	10086617B Wins	10086617A Wins</li>
<li>4	5	5</li>
<li>5	5	5</li>
<li>6	10	0</li>
<li>7	0	10</li>
<li>8	5	5</li>

   When using minimax with heuristic, we assume that the opponent also uses the same heuristic as we use but actually most of the time, the opponent is not. So we can't avoid this kind of result but the heuristic function just uses the feature, the number of possible valid moves for a player on current board. When playing the game, I noticed that one piece can have several valid moves. Then I wrote the third minimax player 10086617C with evaluation function h = 2*myMovablePieces - opMovablePieces, where myMovablePieces stands for the movable pieces on current board for my player and similar for opMovablePieces. I use the same test strategy as above but test with both 10086617A and 10086617B and the result is :

10086617C Vs 10086617A
<li>Board Size	10086617C Wins	10086617A Wins</li>
<li>4	5	5</li>
<li>5	5	5</li>
<li>6	10	0</li>
<li>7	5	5</li>
<li>8	0	10</li>

10086617C Vs 10086617B
<li>Board Size	10086617C Wins	10086617B Wins</li>
<li>4	5	5</li>
<li>5	10	0</li>
<li>6	5	5</li>
<li>7	10	0</li>
<li>8	5	5</li>

As we can see from the results, generally 10086617C performs slightly better than the other two players. But when 10086617C vs 10086617A and the board size is 8, 10086617C lose all the 10 games. So I came up with another heuristic to fix this which is combine the two heuristic together, h = 2*myMovablePieces - opMovablePieces + 2*myMoves - opMoves. This is the fourth player 10086617D and then I did the test with the other three players. Results are as following:

10086617D Vs 10086617A
<li>Board Size	10086617D Wins	10086617A Wins</li>
<li>4	5	5</li>
<li>5	5	5</li>
<li>6	10	0</li>
<li>7	5	5</li>
<li>8	5	5</li>

10086617D Vs 10086617B
<li>Board Size	10086617D Wins	10086617B Wins</li>
<li>4	5	5</li>
<li>5	5	5</li>
<li>6	0	10</li>
<li>7	5	5</li>
<li>8	5	5</li>

10086617D Vs 10086617C
<li>Board Size	10086617D Wins	10086617C Wins</li>
<li>4	5	5</li>
<li>5	5	5</li>
<li>6	5	5</li>
<li>7	5	5</li>
<li>8	5	5</li>

  Simply combining the two heuristics by adding them together doesn't work very well according to the test results. And from all the test result, we can draw a conclusion that when board size is 7 or 8, there are most likely having some problems. So my fifth heuristic is combining the two heuristics by using if-else statement to separate different situations with different those board sizes. The peusdo code fragment is as following:
if boardSize = 7 or 8
 h = 2*myMoves - opMoves;
else  h = 2*myMovablePieces - opMovablePieces;

This time this player can win the player 10086617A when board size is 6, win 10086617B when board size is 5 and win 10086617C when board size is 8. The other situation most likely will be a draw.

   After those step by step tests and improvement, my final heuristic that used is the last one combining the two features, number of movable pieces and number of possible moves on board, by using if-else statement.
