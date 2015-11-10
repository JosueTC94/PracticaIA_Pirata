package Paquete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.ScrollPane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Prueba_gridlayout extends JFrame implements ActionListener{

	private JPanel contentPane;
	private int fila;
	private int columna;
	pila p = new pila();
	JPanel panel;
	JPanel labels;
	Container contenedor;
	JPanel ejemplo_grid;
	JMenuBar menu_empezar_juego;
	JButton mCasillas[][],btnInsertarPirata,btnInsertarObstaculos,btnInsertarTesoro,btnCrearMatriz,btnCrearMapa,empezar_juego,btnNuevoJuego;
	JButton ver_camino_minimo;
	Boolean mCasillas_Obstaculos[][];
	Boolean mCasillas_visitados[][];

	//int tic=0;
	
	JLabel lblHagaClickEn,lblNumeroDeFilas,lblNumeroDeColumnas,label_obstaculos,label_tesoro;
	int numero_filas;
	int numero_columnas;
	int numero_obstaculos;
	int aux;
	int ancho;
	int alto;
	int pos_x;
	int pos_y;
	int posicion_x_tesoro = -1;
	int posicion_y_tesoro = -1;
	int posicion_x_pirata = -1;
	int posicion_y_pirata = -1;
	int posicion_x_pirata_aux;
	int posicion_y_pirata_aux;

	//Boleanos de control
	Boolean poner_pirata = false;
	Boolean poner_tesoro = false;
	Boolean poner_obstaculos = false;

	boolean contador;
	boolean contador1;
	private JButton btnEmpezarJuego;


	//Runnable
	Runnable r1;
	//Booleanos de control
	boolean acceso_insertar_tesoro = false;
	boolean acceso_insertar_pirata = false;
	boolean acceso_insertar_obstaculos = false;
	int numero_accesos;
	//temporizador
	//Timer timer;


	/**
	 * 
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba_gridlayout frame = new Prueba_gridlayout();
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
	public Prueba_gridlayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setBounds(100, 100, 450, 300);
		//setBounds(10,10,250,200);
		this.setSize(905,659);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setSize(200,600);
		// this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		capturo_datos();
		this.setVisible(true);

		panel = new JPanel(new GridLayout(numero_filas,numero_columnas));
		labels = new JPanel(new GridLayout(1,1));
		contentPane.add(labels);
		//panel.setBounds(300, 100, 200, 200);
		//panel.setBounds(300, 100, 200, 200);
		//panel.setSize(900,600);
		panel.setBounds(3, 3, 900, 600);
		mCasillas = new JButton[numero_filas][numero_columnas];
		for(int i=0;i<numero_filas;i++)
		{
			for(int j=0;j<numero_columnas;j++)
			{
				mCasillas[i][j] = new JButton();

				ancho = 900/numero_columnas;
				alto = 600/numero_filas;


				// Obtiene un icono en escala con las dimensiones especificadas
				ImageIcon imagen_arena = new ImageIcon(getClass().getResource("/Images/arena.jpg"));
				ImageIcon imagen_escalada= new ImageIcon(imagen_arena.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
				System.out.print("Medida_imagen:"+ imagen_escalada.getIconHeight());
				mCasillas[i][j].setIcon(imagen_escalada);
				String posicion = new Integer(i).toString();
				posicion += ","+new Integer(j).toString();

				mCasillas[i][j].setActionCommand(posicion);

				panel.add(mCasillas[i][j]);
			}
		}
		//	this.addListeners();	
		if(!p.empty())
		{
			while(!p.empty())
			{
				p.pop();
			}
		}
		panel.setVisible(true);
		contentPane.add(panel);

		inicializo_matrices();
		/*JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);*/
		menu_empezar_juego=new JMenuBar();//Crea una barra de menu
		this.setJMenuBar(menu_empezar_juego);
		btnInsertarTesoro=new JButton("Insertar Tesoro");//Crea boton opciones
		btnInsertarPirata=new JButton("Insertar Pirata");
		btnInsertarObstaculos=new JButton("Insertar obstaculos");
		btnEmpezarJuego=new JButton("Empezar Juego");
		btnNuevoJuego=new JButton("Nuevo Juego");
		ver_camino_minimo = new JButton("Mejor camino");
		btnNuevoJuego.setVisible(false);
		//opcion_empezar.addActionListener(this);
		menu_empezar_juego.add(btnInsertarTesoro);//Lo a�ade a la barra 
		menu_empezar_juego.add(btnInsertarPirata);//Lo a�ade a la barra 
		menu_empezar_juego.add(btnInsertarObstaculos);//Lo a�ade a la barra 
		menu_empezar_juego.add(btnEmpezarJuego);
		menu_empezar_juego.add(btnNuevoJuego);
		menu_empezar_juego.add(ver_camino_minimo);

		ver_camino_minimo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				System.out.print("Camino minimo");
				while(!p.empty())
				{
					System.out.print("\nHOLA");
					coordenadas aux = p.pop();
					int pos_x_min = (int)aux.get_x();
					int pos_y_min = (int)aux.get_y();
					System.out.print("\nPosicionx_pintar->"+pos_x_min);
					System.out.print("\nPosiciony_pintar->"+pos_y_min);
					mCasillas[pos_x_min][pos_y_min].setIcon(null);
					mCasillas[pos_x_min][pos_y_min].setBackground(Color.BLUE);
				}
			}
		});
		contador1 = false;
		//btnInsertarTesoro = new JButton("Insertar tesoro");
		btnInsertarTesoro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("\nPoner_tesoro->"+poner_tesoro+"\n");
				lblHagaClickEn.setVisible(false);
				lblHagaClickEn.setText("Haga click en la celda/s:");
				lblHagaClickEn.setVisible(true);
				JButton button =(JButton)e1.getSource();
				button.setBackground(Color.lightGray);
				System.out.print("\nHe pulsado Insertar tesoro\n");

				contador = false;
				for(int i=0;i<numero_filas;i++)
				{
					for(int j=0;j<numero_columnas;j++)
					{
						mCasillas[i][j].addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e1)
							{
								if((contador == false) && (posicion_x_tesoro == -1 && posicion_y_tesoro == -1))
								{
									System.out.print("\nEntre en Insertar Tesoro\n");
									JButton button =(JButton)e1.getSource();

									String[] posicion_pirata = (button.getActionCommand()).split(",");
									posicion_x_tesoro = Integer.parseInt(posicion_pirata[0]);
									posicion_y_tesoro = Integer.parseInt(posicion_pirata[1]);
									System.out.print("\nPosicion x tesoro->"+posicion_x_tesoro);
									System.out.print("\nPosicion y tesoro->"+posicion_y_tesoro);
									ImageIcon imagen_tesoro = new ImageIcon(getClass().getResource("/Images/tesoro.jpg"));
									ImageIcon imagen_escalada = new ImageIcon(imagen_tesoro.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
									button.setIcon(imagen_escalada);
									//button.setBackground(Color.blue);
									acceso_insertar_tesoro = true;
									contador = true;
									numero_accesos++;
								}
								else
								{
									System.out.print("\nTesoro colocado else\n");
									/*ImageIcon imagen_arena = new ImageIcon(getClass().getResource("/Images/arena.jpg"));
						    			ImageIcon imagen_escalada = new ImageIcon(imagen_arena.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										mCasillas[posicion_x_tesoro][posicion_y_tesoro].setIcon(imagen_escalada);			

										JButton boton = (JButton)e1.getSource();
										String[] posicion_pirata = (boton.getActionCommand()).split(",");
										posicion_x_tesoro = Integer.parseInt(posicion_pirata[0]);
										posicion_y_tesoro = Integer.parseInt(posicion_pirata[1]);
										System.out.print("\nPosicion x tesoro->"+posicion_x_tesoro);
										System.out.print("\nPosicion y tesoro->"+posicion_y_tesoro);
						    			ImageIcon imagen_tesoro1 = new ImageIcon(getClass().getResource("/Images/tesoro.jpg"));
						    			ImageIcon imagen_escalada1 = new ImageIcon(imagen_tesoro1.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										boton.setIcon(imagen_escalada1);*/
								}
							}			
						});
					}
				}
			}
		});
		//btnInsertarTesoro.setBounds(118, 126, 147, 25);
		//contentPane.add(btnInsertarTesoro);
		btnNuevoJuego.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.out.print("Nuevo Juego");
				dispose();
				//p.vaciar();
				Prueba_gridlayout nuevo_juego = new Prueba_gridlayout();
			}
		});
		//btnInsertarPirata = new JButton("Insertar pirata");
		btnInsertarPirata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				lblHagaClickEn.setVisible(false);
				lblHagaClickEn.setText("Haga click en la celda/s:");
				lblHagaClickEn.setVisible(true);
				JButton button =(JButton)e1.getSource();
				button.setBackground(Color.lightGray);
				System.out.print("\nHe pulsado Insertar pirata\n");

				contador = false;
				for(int i=0;i<numero_filas;i++)
				{
					for(int j=0;j<numero_columnas;j++)
					{
						mCasillas[i][j].addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e1)
							{
								if(contador == false)
								{
									System.out.print("\nEntre en insertar pirata\n");
									lblHagaClickEn.setVisible(false);
									JButton button =(JButton)e1.getSource();

									String[] posicion_pirata = (button.getActionCommand()).split(",");
									posicion_x_pirata = Integer.parseInt(posicion_pirata[0]);
									posicion_y_pirata = Integer.parseInt(posicion_pirata[1]);
									System.out.print("\nPosicion x pirata->"+posicion_x_pirata);
									System.out.print("\nPosicion y pirata->"+posicion_y_pirata);
									ImageIcon imagen_pirata = new ImageIcon(getClass().getResource("/Images/pirata.jpg"));
									ImageIcon imagen_escalada = new ImageIcon(imagen_pirata.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
									button.setIcon(imagen_escalada);
									//button.setBackground(Color.blue);
									mCasillas_visitados[posicion_x_pirata][posicion_y_pirata] = true;
									acceso_insertar_pirata = true;
									contador = true;
									numero_accesos++;
								}
								if(contador == true)
								{
									lblHagaClickEn.setVisible(false);
								}
							}			
						});
					}
				}	
			}
		});
		//btnInsertarPirata.setBounds(118, 187, 147, 25);
		//contentPane.add(btnInsertarPirata);

		//btnInsertarObstaculos = new JButton("Obstaculos");
		btnInsertarObstaculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				//lblHagaClickEn.setText("Haga click en la casilla/s:");
				//lblHagaClickEn.setVisible(true);
				JButton button = (JButton)e2.getSource();
				button.setBackground(Color.lightGray);
				pos_x = 0;
				pos_y = 0;
				aux = 0;
				numero_obstaculos = (numero_filas*numero_columnas)-2;
				for(pos_x=0;pos_x<numero_filas;pos_x++)
				{
					for(pos_y=0;pos_y<numero_columnas;pos_y++)
					{
						mCasillas[pos_x][pos_y].addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e2)
							{
								if(aux < numero_obstaculos)
								{
									//lblHagaClickEn.setVisible(true);
									Random r = new Random();
									int valorDado = r.nextInt(3)+1; 
									JButton evento2 =(JButton)e2.getSource();
									//button.setIcon(new ImageIcon(getClass().getResource("/Images/pirata.jpg")));
									String[] posicion_obstaculo = (evento2.getActionCommand()).split(",");
									int posicion_x_obstaculo= Integer.parseInt(posicion_obstaculo[0]);
									int posicion_y_obstaculo = Integer.parseInt(posicion_obstaculo[1]);
									//Compruebo posiciones a ver si estan en la matriz obstaculos

									if((mCasillas_Obstaculos[posicion_x_obstaculo][posicion_y_obstaculo]!=true)&&((posicion_x_obstaculo!=posicion_x_pirata)||(posicion_y_obstaculo!=posicion_y_pirata))&&((posicion_x_obstaculo!=posicion_x_tesoro)||(posicion_y_obstaculo!=posicion_y_tesoro)))
									{
										System.out.print("\nPosicion x obstaculo->"+posicion_x_obstaculo);
										System.out.print("\nPosicion y obstaculo->"+posicion_y_obstaculo);
										System.out.print("Valor aleatorio->"+valorDado+"\n");

										ImageIcon imagen_palmera = new ImageIcon(getClass().getResource("/Images/obstaculo"+valorDado+".jpg"));
										ImageIcon imagen_escalada = new ImageIcon(imagen_palmera.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										evento2.setIcon(imagen_escalada);
										//button.setBackground(Color.green);

										//Pongo la posición actual en mCasillas_Obstaculos como true
										mCasillas_Obstaculos[posicion_x_obstaculo][posicion_y_obstaculo] = true;

										aux++;
									}
									else
									{
										System.out.print("Posicion ocupada");
										JLabel posicion_ocupada = new JLabel("Posición ocupada");
										posicion_ocupada.setBounds(1, 1, 1, 1);
										contentPane.add(posicion_ocupada);
									}
								}
								if(aux >= numero_obstaculos)
								{
									lblHagaClickEn.setVisible(false);
									label_obstaculos = new JLabel("Obstaculos Completados");
									label_obstaculos.setBounds(300, 73, 256, 15);
									label_obstaculos.setVisible(true);
									contentPane.add(label_obstaculos);

									acceso_insertar_obstaculos = true;
									numero_accesos++;
								}							
							}			
						});
					}
				}	
			}
		});
		//btnInsertarObstaculos.setBounds(118, 243, 147, 25);
		//contentPane.add(btnInsertarObstaculos);

		lblHagaClickEn = new JLabel();
		lblHagaClickEn.setBounds(300, 73, 256, 15);
		lblHagaClickEn.setVisible(false);
		contentPane.add(lblHagaClickEn);

		


		btnEmpezarJuego.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JButton button =(JButton)arg0.getSource();
				button.setBackground(Color.lightGray);
				if((posicion_x_tesoro != -1)&&(posicion_y_pirata !=-1)&&(posicion_x_tesoro != -1)&&(posicion_y_tesoro != -1))
				{
					btnNuevoJuego.setVisible(true);
					//btnEmpezarJuego.setVisible(false);
					System.out.print("\nMatriz visitados\n");
					for(int i=0;i<numero_filas;i++)
					{
						for(int j=0;j<numero_columnas;j++)
						{
							System.out.print("i->"+i+",j->"+j+mCasillas_visitados[i][j]+"\n");
						}
					}
					System.out.print("\nMatriz obstaculos\n");
					for(int i=0;i<numero_filas;i++)
					{
						for(int j=0;j<numero_columnas;j++)
						{
							System.out.print("i->"+i+",j->"+j+mCasillas_Obstaculos[i][j]+"\n");
						}
					}
					//Comenzamos implementación de la heurística
					System.out.print("Yiesaaaaa");
					System.out.print("\n\n\n");
					posicion_x_pirata_aux = posicion_x_pirata;
					posicion_y_pirata_aux = posicion_y_pirata;

					//Pongo como primera posición en la pila la posición inicial del pirata
					p.push(posicion_x_pirata_aux,posicion_y_pirata_aux);

					boolean control_bucles = false;
					Runnable r1 = new Runnable()
					{
						public void run()
						{
							int i=0;
							boolean control_bucles = false;
							try{
								while (((posicion_x_pirata_aux != posicion_x_tesoro)||(posicion_y_pirata_aux != posicion_y_tesoro))&&(control_bucles!=true))
								{
									Thread.sleep(250L);//TIEMPO EJECUCION
									System.out.print("\nPosicion pirata->"+posicion_x_pirata_aux);
									System.out.print("\nPosicion pirata->"+posicion_y_pirata_aux);
									System.out.print("\nPosicion tesoro->"+posicion_x_tesoro);
									System.out.print("\nPosicion tesoro->"+posicion_y_tesoro);
									int mov = calcular_heuristica();
									//movimientos[i] = mov;

									switch (mov){
									case 0:
										System.out.print("Entre en case 0");
										//Esto lo hago mientras la pila no esté vacía
										System.out.print("\nEstado de la pila:"+p.empty());

										if(p.empty()!=true)
										{		
											//LLamo a la pila y devuelvo la posicion de la que vengo
											coordenadas aux1 = new coordenadas();
											//coordenadas aux2 = new coordenadas();
											aux1 = p.pop();
											//aux2 = p.pop();
											int posicion_pila_x = (int)aux1.get_x();
											int posicion_pila_y = (int)aux1.get_y();
											if((posicion_pila_x == posicion_x_pirata_aux-1)&&(posicion_pila_y == posicion_y_pirata_aux))
											{
												ImageIcon imagen_pirata3 = new ImageIcon(getClass().getResource("/Images/flecha_arriba.png"));
												ImageIcon imagen_escalada3_1= new ImageIcon(imagen_pirata3.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
												mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada3_1);
											}
											if((posicion_pila_x == posicion_x_pirata_aux+1)&&(posicion_pila_y == posicion_y_pirata_aux))
											{
												ImageIcon imagen_pirata3 = new ImageIcon(getClass().getResource("/Images/flecha_abajo.png"));
												ImageIcon imagen_escalada3_1= new ImageIcon(imagen_pirata3.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
												mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada3_1);
											}
											if((posicion_pila_x == posicion_x_pirata_aux)&&(posicion_pila_y==posicion_y_pirata_aux-1))
											{
												ImageIcon imagen_pirata3 = new ImageIcon(getClass().getResource("/Images/flecha_izquierda.png"));
												ImageIcon imagen_escalada3_1= new ImageIcon(imagen_pirata3.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
												mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada3_1);
											}
											if((posicion_pila_x == posicion_x_pirata_aux)&&(posicion_pila_y == posicion_y_pirata_aux+1))
											{
												ImageIcon imagen_pirata3 = new ImageIcon(getClass().getResource("/Images/flecha_derecha.png"));
												ImageIcon imagen_escalada3_1= new ImageIcon(imagen_pirata3.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
												mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada3_1);
											}
											System.out.print("\nPosicion desde la pila_x:"+posicion_pila_x);
											System.out.print("\nPosicion desde la pila_y:"+posicion_pila_y);
											//Pongo a true la posición en la que estoy
											mCasillas_visitados[posicion_x_pirata_aux][posicion_y_pirata_aux] = true;
											//Actualizo la posición del pirata al top de la pila
											posicion_x_pirata_aux = posicion_pila_x;
											posicion_y_pirata_aux = posicion_pila_y;
										}else
										{
											control_bucles = true;
										}
										break;
									case 1:
										System.out.print("Entre en case 1");


										ImageIcon imagen_pirata = new ImageIcon(getClass().getResource("/Images/flecha_arriba.png"));
										ImageIcon imagen_escalada= new ImageIcon(imagen_pirata.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada);	
										mCasillas_visitados[posicion_x_pirata_aux][posicion_y_pirata_aux]=true;
										p.push(posicion_x_pirata_aux, posicion_y_pirata_aux);

										posicion_x_pirata_aux = posicion_x_pirata_aux-1;
										//Actualizo matriz visitados


										//Meto nueva posición en la pila

										break;
									case 2:
										System.out.print("Entre en case 2");
										//ImageIcon imagen_arena1 = new ImageIcon(getClass().getResource("/Images/arena1.jpg"));
										//ImageIcon imagen_escalada1= new ImageIcon(imagen_arena1.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										//mCasillas[pos_x_pirata_aux][pos_y_pirata_aux].setIcon(imagen_escalada1);
										//Actualizo matriz visitados
										mCasillas_visitados[posicion_x_pirata_aux][posicion_y_pirata_aux]=true;

										ImageIcon imagen_pirata1 = new ImageIcon(getClass().getResource("/Images/flecha_abajo.png"));
										ImageIcon imagen_escalada1_1= new ImageIcon(imagen_pirata1.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada1_1);
										p.push(posicion_x_pirata_aux, posicion_y_pirata_aux);

										posicion_x_pirata_aux = posicion_x_pirata_aux+1;

										break;
									case 3:
										System.out.print("Entre en case 3");
										//ImageIcon imagen_arena2 = new ImageIcon(getClass().getResource("/Images/arena1.jpg"));
										//ImageIcon imagen_escalada2= new ImageIcon(imagen_arena2.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										//mCasillas[pos_x_pirata_aux][pos_y_pirata_aux].setIcon(imagen_escalada2);
										//Actualizo matriz visitados
										mCasillas_visitados[posicion_x_pirata_aux][posicion_y_pirata_aux]=true;

										ImageIcon imagen_pirata2 = new ImageIcon(getClass().getResource("/Images/flecha_izquierda.png"));
										ImageIcon imagen_escalada2_1= new ImageIcon(imagen_pirata2.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada2_1);

										//Meto nueva posición en la pila
										p.push(posicion_x_pirata_aux, posicion_y_pirata_aux);

										posicion_y_pirata_aux = posicion_y_pirata_aux - 1;

										break;
									case 4:
										System.out.print("Entre en case 4");

										//Actualizo matriz visitados
										mCasillas_visitados[posicion_x_pirata_aux][posicion_y_pirata_aux]=true;

										ImageIcon imagen_pirata3 = new ImageIcon(getClass().getResource("/Images/flecha_derecha.png"));
										ImageIcon imagen_escalada3_1= new ImageIcon(imagen_pirata3.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada3_1);

										//Meto nueva posición en la pila
										p.push(posicion_x_pirata_aux, posicion_y_pirata_aux);

										posicion_y_pirata_aux = posicion_y_pirata_aux + 1;

										break;
									}
									//i++;
									//Deberia incluir una función que me determine exactamente que no hay movimiento posible
									if((control_bucles!=false)&&((posicion_x_pirata_aux != posicion_x_tesoro) ||(posicion_y_pirata_aux != posicion_y_tesoro)))
									{
										if(calcular_heuristica() == 0)
										{
											System.out.print("No hay camino posible");
											JOptionPane.showMessageDialog(null, "No hay camino posible", "Fin del juego", JOptionPane.WARNING_MESSAGE);							
											break;
										}
									}
									if((posicion_x_pirata_aux==posicion_x_tesoro)&&(posicion_y_pirata_aux==posicion_y_tesoro))
									{
										ImageIcon imagen_pirata_tesoro = new ImageIcon(getClass().getResource("/Images/pirata_tesoro.jpg"));
										ImageIcon imagen_escalada= new ImageIcon(imagen_pirata_tesoro.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada);
										System.out.print("Camino encontrado");
										JOptionPane.showMessageDialog(null, "Tesoro encontrado", "Fin del juego", JOptionPane.WARNING_MESSAGE);							
										ver_camino_minimo.setVisible(true);
										break;
									}
									System.out.print("Posicion del pirata nueva->"+posicion_x_pirata_aux+"\n");
									System.out.print("Posicion y del pirata nueva->"+posicion_y_pirata_aux+"\n");
									System.out.print("Posicion del tesoro->"+posicion_x_tesoro+"\n");
									System.out.print("Posicion y del tesoro->"+posicion_y_tesoro+"\n");
								}
							}
							catch(Exception e) {
								//e.printStackTrace();
							}
						}
					};
					Thread thr1 = new Thread(r1);
					thr1.start();				
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Introduzca los datos", "Imposible", JOptionPane.WARNING_MESSAGE);							
				}
			}

			private int calcular_heuristica() {
				// TODO Auto-generated method stub
				double distancia = 10000;
				int movimiento = 0;
				boolean control = false;
				if(distancia > (Math.sqrt(Math.pow((posicion_x_pirata_aux-1)-posicion_x_tesoro,2)+Math.pow(posicion_y_pirata_aux-posicion_y_tesoro,2))) && (posicion_x_pirata_aux-1>=0))
				{
					if((mCasillas_visitados[posicion_x_pirata_aux-1][posicion_y_pirata_aux]==false) && (mCasillas_Obstaculos[posicion_x_pirata_aux-1][posicion_y_pirata_aux]==false))
					{
						distancia = Math.sqrt(Math.pow((posicion_x_pirata_aux-1)-posicion_x_tesoro,2)+Math.pow(posicion_y_pirata_aux-posicion_y_tesoro,2));
						movimiento = 1;		//movimiento arriba
						control = true;
					}
				}

				if(distancia > (Math.sqrt(Math.pow((posicion_x_pirata_aux+1)-posicion_x_tesoro,2)+Math.pow(posicion_y_pirata_aux-posicion_y_tesoro,2))) && (posicion_x_pirata_aux+1<=numero_filas-1))
				{
					if((mCasillas_visitados[posicion_x_pirata_aux+1][posicion_y_pirata_aux]==false) && (mCasillas_Obstaculos[posicion_x_pirata_aux+1][posicion_y_pirata_aux]==false))
					{
						distancia = Math.sqrt(Math.pow((posicion_x_pirata_aux+1)-posicion_x_tesoro,2)+ Math.pow(posicion_y_pirata_aux-posicion_y_tesoro,2));
						movimiento = 2;		//movimiento abajo
						control = true;
					}
				}
				if(distancia > (Math.sqrt(Math.pow(posicion_x_pirata_aux-posicion_x_tesoro,2)+Math.pow((posicion_y_pirata_aux-1)-posicion_y_tesoro,2))) && (posicion_y_pirata_aux-1>=0))
				{
					if((mCasillas_visitados[posicion_x_pirata_aux][posicion_y_pirata_aux-1]==false) && (mCasillas_Obstaculos[posicion_x_pirata_aux][posicion_y_pirata_aux-1]==false))
					{
						distancia = Math.sqrt(Math.pow(posicion_x_pirata_aux-posicion_x_tesoro,2)+Math.pow((posicion_y_pirata_aux-1)-posicion_y_tesoro,2));
						movimiento = 3;		//movimiento izquierda
						control = true;
					}
				}
				if(distancia > (Math.sqrt(Math.pow(posicion_x_pirata_aux-posicion_x_tesoro,2)+Math.pow((posicion_y_pirata_aux+1)-posicion_y_tesoro,2))) && (posicion_y_pirata_aux+1<=numero_columnas-1))
				{
					if((mCasillas_visitados[posicion_x_pirata_aux][posicion_y_pirata_aux+1]==false) && (mCasillas_Obstaculos[posicion_x_pirata_aux][posicion_y_pirata_aux+1]==false))
					{
						distancia = Math.sqrt(Math.pow(posicion_x_pirata_aux-posicion_x_tesoro,2)+Math.pow((posicion_y_pirata_aux+1)-posicion_y_tesoro,2));
						movimiento = 4;		//movimiento derecha
						control = true;
					}
				}
				if (control == false)
				{
					System.out.print("\nNo obtuve movimiento\n");
					movimiento = 0;
				}
				//distancia = 0;
				System.out.print("\nDistancia -> "+distancia);
				System.out.print("\nMovimiento ->"+movimiento);
				System.out.print("\n");
				return movimiento;
			}
		});
	}

	private void inicializo_matrices() {
		// TODO Auto-generated method stub
		mCasillas_visitados = null;
		mCasillas_Obstaculos = null;
		mCasillas_visitados = new Boolean[numero_filas][numero_columnas];
		mCasillas_Obstaculos = new Boolean[numero_filas][numero_columnas];
		for(int i=0;i<numero_filas;i++)
		{
			for(int j=0;j<numero_columnas;j++)
			{
				mCasillas_visitados[i][j]=false;
				mCasillas_Obstaculos[i][j]=false;
			}
		}
	}

	private void capturo_datos() {
		// TODO Auto-generated method stub
		numero_filas = 0;
		numero_columnas = 0;
		String respuesta1 = null;
		String respuesta2 = null;
	
		String filtro = "[0..9]";
		
		try{

			respuesta1 = JOptionPane.showInputDialog("Numero de Filas:");
			System.out.print("Respuesta->"+respuesta1.matches(filtro));
			numero_filas = Integer.parseInt(respuesta1);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null,"ingrese numero entero","ERROR!!" ,JOptionPane.ERROR_MESSAGE);
			pedir_fila();
			//capturo_datos();
			
		}
		try{

			respuesta2 = JOptionPane.showInputDialog("Numero de Columnas:");
			System.out.print("Respuesta->"+respuesta2.matches(filtro));
			numero_columnas= Integer.parseInt(respuesta2);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null,"ingrese numero entero","ERROR!!" ,JOptionPane.ERROR_MESSAGE);
			pedir_colum();
			//capturo_datos();
			
		}

	}
	

	public void pedir_fila(){
		String respuesta1 = null;
		numero_filas = 0;
		respuesta1 = JOptionPane.showInputDialog("Numero de Filas:");
		numero_filas = Integer.parseInt(respuesta1);
	}
	
	public void pedir_colum(){
		String respuesta2 = null;
		numero_columnas = 0;
		respuesta2 = JOptionPane.showInputDialog("Numero de Columnas:");
		numero_columnas = Integer.parseInt(respuesta2);
	}
	/*public void addListeners() {
		for (int i = 0; i < numero_filas; i++) {
		for (int j = 0; j < numero_columnas; j++) {
		mCasillas[i][j].addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent evt) {
		JButton evento = (JButton)evt.getSource();
		System.out.println("apretadooton "+evento.getActionCommand());
				}
			});
		}
		}
		}*/
}
