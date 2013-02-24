/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kółko_krzyżyk;


public class Kółko_Krzyżyk_Ai {

final private int board[][];
final public static int EMPTY = 0;
final public static int ONE = 1;
 
    public static int TWO = 2;

 public Kółko_Krzyżyk_Ai() {
     // tablica 3 X 3 ze względu na dwóch graczy i wartość EMPTY =0
  board = new int[3][3];
 }

 // tworzenie wartosci granicznych
 public int getBoardValue(int i,int j) {
     // jezeli warunek jest spelniony zwroc 0
  if(i < 0 || i >= 3) return EMPTY;
  if(j < 0 || j >= 3) return EMPTY;
  //  zwrócenie pozycji i x j , 0,1,2 x 0,1,2
  return board[i][j];
  
    }
 // nadanie współżędnych ganicznych
 public void setBoardValue(int i,int j,int token) {
     //jezeli i jest po za siatka przerwij
  if(i < 0 || i >= 3) return;
    //jezeli j jest po za siatka przerwij
  if(j < 0 || j >= 3) return;
  // przypisanie zmiennej token o wartosci 1 lub 2 wartosci pola
// wartosc pola (board) 1,2,3 x 1,2,3  = (token) gracz 1,2
  board[i][j] = token;
    }

 public int []nextWinningMove(int token) {

  for(int i=0;i<3;i++)
   for(int j=0;j<3;j++)
       //jezeli wartosci pola przypisanego sa w granicach
    if(getBoardValue(i, j)==EMPTY) {
     board[i][j] = token;
     boolean win = isWin(token);
     // przypisanie zmiennej board wartosci EMPTY
    board[i][j] = EMPTY;
     //zapobieganie nakladaniu sie wartosci
     if(win) return new int[]{i,j};
    }

  return null;
    }

    public int inverse(int token) {
        // zmiana wartosci token
  return token==ONE ? TWO : ONE;
 }

    // wyliczanie ruchu
    public int []nextMove(int token) {

        // jezeli gracz nie zajal miejsca na sroku 1 x 1 zwroc komputerowi zmiene 1, 1 do zmiennych i, j
        if(getBoardValue(1, 1)==EMPTY) return new int[]{1,1};

        // jezeli mozna zrobic kolejny ruch
        int winMove[] = nextWinningMove(token);
        if(winMove!=null) return winMove;

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(getBoardValue(i, j)==EMPTY)
                {
                    board[i][j] = token;
                    //pobranie wartosci z isWin przez WinningMove z zmienioną wartoscią 
                    boolean ok = nextWinningMove(inverse(token)) == null;
                    board[i][j] = EMPTY;
                    if(ok) return new int[]{i,j};
                }

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(getBoardValue(i, j)==EMPTY)
                    return new int[]{i,j};

        return null;
    }

// klasa sprawdzajaca czy ktos wygral
 public boolean isWin(int token) {
 final int DI[]={-1,0,1,1};
  final int DJ[]={1,1,1,0};
   // wspólzędne planszy
  for(int i=0;i<3;i++)
   for(int j=0;j<3;j++) {
       // jezeli ktoras z czesc współrzędnych (0,1,2) X (0,1,2) jest różna od tokena (1 lub 2)nie przerywaj
    if(getBoardValue(i, j)!=token) continue;

   
    for(int k=0;k<4;k++) {
     int ctr = 0;
                    //sprwdzanie mozliwych kombinacji nie zawierajacych w sobie wartosci tokena
                 while(getBoardValue(i+DI[k]*ctr, j+DJ[k]*ctr)==token) ctr++;
     if(ctr==3) return true;
    }
  }
  return false;
    }
} 