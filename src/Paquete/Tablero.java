package Paquete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class Tablero extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Este es el panel general
	private JPanel contentPane;
	//JPanel PanelTablero = new JPanel(new GridLayout(8,8));
	//JPanel PanelTablero2 = new JPanel(new GridLayout(1,1));
    JPanel PanelTablero;
    JPanel PanelTablero2;
	JPanel Panel_menu;
    JButton mCasillas[][];
    JButton Crear_matriz1,boton_prueba;
    int numero_filas;
    int numero_columnas;
    //Variables para saber que boton pulso
    int pos_x;
    int pos_y;
    JLabel jl1, jl2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tablero frame = new Tablero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	   
	public Tablero() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		capturo_datos();
		construyo_menu();
		System.out.print("Numero de filas->"+numero_filas+"\n");
		System.out.print("Numero de columnas->"+numero_columnas+"\n");
		
		contentPane = new JPanel(new GridLayout(10,10));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		PanelTablero = new JPanel(new GridLayout(numero_filas,numero_columnas));
    	mCasillas = new JButton[8][8];
    	for(int i=0;i<numero_filas;i++)
    	{
    		for(int j=0;j<numero_columnas;j++)
    		{
    			System.out.print(i);
    			mCasillas[i][j] = new JButton(i+","+j);
    			PanelTablero.add(mCasillas[i][j]);
    		}
    	}
    	this.addListeners();
    	
    	//Creo panel para crear matriz
		PanelTablero2 = new JPanel(new GridLayout(3,3));
		JLabel label_filas = new JLabel("Numero de filas:");
		JLabel label_columnas = new JLabel("Numero de columnas:");
		
		label_filas.setVisible(true);
		label_columnas.setVisible(true);
		
		JTextField num_filas = new JTextField();
		num_filas.setVisible(true); 
		JTextField num_columnas = new JTextField();
		
		JLabel espacio = new JLabel("");
		JButton Crear_matriz1 = new JButton("Crear matriz");
		Crear_matriz1.addActionListener(this);
		
		PanelTablero2.add(label_filas);
		PanelTablero2.add(num_filas);
		PanelTablero2.add(label_columnas);
		PanelTablero2.add(num_columnas);
		PanelTablero2.add(espacio);
		PanelTablero2.add(Crear_matriz1);
		
		//Voy creando paneles y añado al contentPane que es mi panel general
    	PanelTablero.setVisible(true);
    	contentPane.add(PanelTablero);
    	contentPane.add(PanelTablero2);
    	//contentPane.add(Panel_menu);

    	contentPane.setVisible(true);
        contentPane.add(PanelTablero, BorderLayout.SOUTH);
        contentPane.add(PanelTablero2, BorderLayout.NORTH);
//    	contentPane.add(Panel_menu,BorderLayout.EAST);
        setContentPane(contentPane);
	}

	private void addListeners() {
		// TODO Auto-generated method stub
		pos_x = 0;
		pos_y = 0;
		for(pos_x=0;pos_x<numero_filas;pos_x++)
		{
			for(pos_y=0;pos_y<numero_columnas;pos_y++)
			{
				mCasillas[pos_x][pos_y].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						JButton button =(JButton)e.getSource();
						button.setBackground(Color.RED);
						System.out.print("Pulsado el boton->"+pos_x+","+pos_y);
					}			
				});
			}
		}
	}

	private void construyo_menu() {
		// TODO Auto-generated method stub
		Panel_menu = new JPanel(new GridLayout(5,1));
		JButton insertar_pirata,insertar_tesoro,insertar_obstaculos;
		insertar_pirata = new JButton("Insertar pirata");
		Panel_menu.add(insertar_pirata);
		insertar_tesoro = new JButton("Insertar tesoro");
		Panel_menu.add(insertar_tesoro);
		insertar_obstaculos = new JButton("Obstaculos");
		Panel_menu.add(insertar_obstaculos);
		Panel_menu.setVisible(true);
	}

	private void capturo_datos() {
		// TODO Auto-generated method stub
		
		numero_filas = 8;
		numero_columnas = 8;
	}

	//Gestion de eventos
	
}
