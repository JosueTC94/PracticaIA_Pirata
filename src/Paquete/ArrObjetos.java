package Paquete;
 
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class ArrObjetos implements ActionListener{
 
    //JButton jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8, jb9;
    JButton mCasillas[][];
    int numero_filas = 10;
    int numero_columnas = 10;
	JPanel jp1, jp2, jp3;
    JLabel jl1, jl2;
 
    private ArrObjetos (){//constructor de la clase
 
    	//Nombre del JFrame que es la ventana
        JFrame frMain = new JFrame("Tablero Matriz");
        frMain.setLayout(new BorderLayout(60, 20));
 
        jl1 = new JLabel(); //creacion jlabel que muestra el numero pulsado
        jl1.setText("Aquí ira el numero que se pulse");
 
        mostrarBot();
 
        frMain.add(jl1, BorderLayout.SOUTH);
        frMain.add(jp2, BorderLayout.NORTH);
 
        frMain.setSize(400, 300);
        frMain.setLocation(300, 200);
        frMain.setVisible(true);
        frMain.setResizable(false);
        frMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
 
    public void mostrarBot(){//metodo donde se encontrara el jpanel que contiene los botones
        //Creamos un nuevo panel
    	jp2 = new JPanel();
 
    	mCasillas = new JButton[numero_filas][numero_columnas];
    	for(int i=0;i<numero_filas;i++)
    	{
    		for(int j=0;j<numero_columnas;j++)
    		{
    			System.out.print(i);
    			mCasillas[i][j] = new JButton("("+i+","+j+")");
    			jp2.add(mCasillas[i][j]);
    			mCasillas[i][j].addActionListener(this);
    		}
    	}
    	/*
        //creacion de los botones
        jb1 = new JButton("1"); jb2 = new JButton("2"); jb3 = new JButton("3");
        jb4 = new JButton("4"); jb5 = new JButton("5"); jb6 = new JButton("6");
        jb7 = new JButton("7"); jb8 = new JButton("8"); jb9 = new JButton("9");
 
        //añadiendo los botones al jpanel
        jp2.add(jb9); jp2.add(jb8); jp2.add(jb7); 
        jp2.add(jb6); jp2.add(jb5); jp2.add(jb4); 
        jp2.add(jb3); jp2.add(jb2); jp2.add(jb1); 
 
        //añadiendo el listener para el evento del click
        jb1.addActionListener(this); jb2.addActionListener(this); jb3.addActionListener(this);
        jb4.addActionListener(this); jb5.addActionListener(this); jb6.addActionListener(this);
        jb7.addActionListener(this); jb8.addActionListener(this); jb9.addActionListener(this);*/
    }
 
    public static void main(String[] args) {        
        ArrObjetos trin = new ArrObjetos(); //se invoca el constructor        
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {        
        jl1.setText(e.getActionCommand());//cuando se presione un boton se mostrara el numero en el jlabel
    }
}