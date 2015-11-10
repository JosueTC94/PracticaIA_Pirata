package Paquete;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Prueba_array extends JFrame {

private JButton[][] boton;
private int n;

/** Creates a new instance of Main */
public Prueba_array(int n) {
this.n = n;
boton = new JButton[n][n];

this.setLayout(new GridLayout(n,n));
for (int i = 0; i < n; i++) {
for (int j = 0; j < n; j++) {
boton[i][j] = new JButton();
String nombre = new Integer(i).toString();
nombre += new Integer(j).toString();
boton[i][j].setActionCommand(nombre);
this.add(boton[i][j]);
}
}
this.addListeners();
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
this.setSize(new Dimension(100,100));
}

public void addListeners() {
for (int i = 0; i < n; i++) {
for (int j = 0; j < n; j++) {
boton[i][j].addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent evt) {
JButton evento = (JButton)evt.getSource();
System.out.println("apretadooton "+evento.getActionCommand());
}
});
}
}
}

public static void main(String[] args) {
// TODO code application logic here
Prueba_array m = new Prueba_array(3);
m.setVisible(true);
}

} 