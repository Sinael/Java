/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kółko_krzyżyk;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Kółko_Krzyżyk extends JFrame implements ActionListener {

 private JButton [][]buttons = new JButton [3][3];
 private JButton playButton = new JButton("Graj");
 // tworzenie warstwy statusu
 private JLabel statusLabel = new JLabel("");
 private Kółko_Krzyżyk_Ai game = null;
 private int gracz = 0;
 private int komputer = 0;
 // semafor konczący gre po przegranej / wygranej
 private boolean isPlay = false;
 private String []chars=new String[]{"","X","O"};

 private void setStatus(String s) {
  statusLabel.setText(s);
 }
// wyświetlanie przycisków aktywnych, domyślnie false
 private void setButtonsEnabled(boolean enabled) {
  for(int i=0;i<3;i++)
   for(int j=0;j<3;j++) {
       //implementacja siatki
   buttons[i][j].setEnabled(enabled);
    if(enabled) buttons[i][j].setText(" ");
   }
  
 }

 public Kółko_Krzyżyk() {

  setTitle("kółko i krzyżyk");
  // ustawienie wyłaczenia okna jako zakończenie aplikacji
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  JPanel centerPanel = new JPanel(new GridLayout(3, 3));
  Font font = new Font("Georgia",Font.BOLD, 32);
  // tworzenie nieaktynych przycisków
  for(int i=0;i<3;i++)
   for(int j=0;j<3;j++) {
    buttons[i][j] = new JButton(" ");
    buttons[i][j].setFont(font);
    // nasluch playbutton pojawienie się po zmianie wartosci z false na 
    buttons[i][j].addActionListener(this);
    // wylaczenie zaznaczenia podczas klinaknia
    buttons[i][j].setFocusable(false);
    // dodawanie przycisków
    centerPanel.add(buttons[i][j]);
   }


 JPanel northPanel = new JPanel();
 northPanel.add(statusLabel);

  JPanel southPanel = new JPanel();
  southPanel.add(playButton);

  setStatus("Wciśnij 'graj' by zagrać");
  //domyślna wartosc false
    setButtonsEnabled(false);

  // implementacj warst w odpowidnich miejscach
  add(northPanel,"North");
  add(centerPanel,"Center");
  add(southPanel,"South");

  // wielkość okna
  setSize(400,400);

  // ustawienie okna na pozycji przeciwna do startowej
  setLocation(900, 370);
  playButton.addActionListener(this);
 }

 public static void main(String []args) {
     // otworzenie okna o wczęsniej nadanych parametrach
 new Kółko_Krzyżyk().setVisible(true);
 }

 private void computerTurn() {
     // jezeli zmienna jest false pzerwij
  if(isPlay == false) return;
  // 
  int []pos = game.nextMove(komputer);
  if(pos!=null) {
   int i = pos[0];
   int j = pos[1];
   // przypisanie przyciskowi wartości tekstowej która ma wyświetlic w przypadku
   // ruchu komutera O
   buttons[i][j].setText(chars[komputer]);
   // odwołaie do setBoardValue w klasie komputera
   game.setBoardValue(i,j, komputer);
  }
  // sprawdzenie zapelnienia albo wygranej
  checkState();
 }

 private void gameOver(String s) {
  setStatus(s);
  setButtonsEnabled(false);
  isPlay = false;
 }

 private void checkState() {
     // wyslanie 
  if(game.isWin(gracz)) {
   gameOver("Gratulacje wygrałeś!");
  }
  if(game.isWin(komputer)) {
   gameOver("Jesteś do bani!");
  }
  if(game.nextMove(gracz)==null && game.nextMove(komputer)==null) {
   gameOver("Remis.");
  }
 }
// zaczyna gracz
 private void click(int i,int j) {
     // jezeli wyslane wspolzedne i oraz j sa w granicach (getBoardValue dla 0,1,2 =0, EMPTY ==0)
  if(game.getBoardValue(i,j)==Kółko_Krzyżyk_Ai.EMPTY) {
      //ustawenie i zmiana wartosci "" na X
   buttons[i][j].setText(chars[gracz]);
   //wyslanie wspolzednych klikniętego buttona do komputera
   game.setBoardValue(i,j,gracz);
   //sprawdzenie stanu
   checkState();
   //kolej przeciwnika
   computerTurn();
  }
 }

 public void actionPerformed(ActionEvent event) {
    
  if(event.getSource()==playButton) {
   play();
  }else {
   for(int i=0;i<3;i++)
    for(int j=0;j<3;j++)
     if(event.getSource()==buttons[i][j])
      click(i,j);
  }
 }

 private void play() {
  game = new Kółko_Krzyżyk_Ai();
  gracz = Kółko_Krzyżyk_Ai.ONE;
  komputer = Kółko_Krzyżyk_Ai.TWO;
  setStatus("Twoja kolej");
  setButtonsEnabled(true);
  isPlay = true;
 }
}