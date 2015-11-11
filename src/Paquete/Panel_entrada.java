package Paquete;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.crypto.spec.PSource;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Panel_entrada extends JFrame implements ActionListener{

	private JPanel contentPane;
	pila p = new pila();
	Container contenedor;
	JFrame panel_entrada;
	JPanel menu;
	JMenuBar mb,menu_empezar_juego;
	JMenu menu1,menu2;
	JMenuItem sin_mouse,con_mouse,salir,ayuda;
	JLabel fondoo;
	JTextField pirata_x,pirata_y,tesoro_x,tesoro_y,carga_obstaculos,numero_filas,numero_columnas;
	JButton mCasillas[][],aceptar_sin_mouse,opcion_empezar,opcion_ver_pila,nuevo_juego, ver_camino_minimo;
	Boolean mCasillas_visitados[][],mCasillas_Obstaculos[][], control_bucles;

	int num_filas,num_columnas,num_obstaculos,posicion_x_pirata,posicion_y_pirata,posicion_x_tesoro,posicion_y_tesoro;
	int posicion_x_pirata_aux;
	int posicion_y_pirata_aux;
	int ancho,alto;

	/**
	 * Create the frame.
	 */
	public Panel_entrada() {

	}

	public void Menu()
	{
		panel_entrada = new JFrame(" Buscar el tesoro ");
		panel_entrada.setBounds(10,10,250,200);
		panel_entrada.setLocationRelativeTo(null);//
		panel_entrada.setResizable(false);

		contenedor= panel_entrada.getContentPane();

		ImageIcon fondo = new ImageIcon((getClass().getResource("/Images/fondo.jpg")));
		Image im_dimension= fondo.getImage();//Obtenemos el tama�o de la imagen
		fondo = new ImageIcon (im_dimension.getScaledInstance(250,200,Image.SCALE_SMOOTH));

		fondoo= new JLabel(fondo);

		mb=new JMenuBar();//Crea una barra de menu
		panel_entrada.setJMenuBar(mb);
		menu1=new JMenu("Inicio");//Crea boton opciones
		menu2=new JMenu("Ayuda");
		mb.add(menu1);//Lo a�ade a la barra 
		mb.add(menu2);
		sin_mouse=new JMenuItem("1 -Panel de datos");//Opcion raton
		sin_mouse.addActionListener(this);
		menu1.add(sin_mouse);
		con_mouse=new JMenuItem("2 -Panel con raton");//Opcion mouse
		con_mouse.addActionListener(this);
		menu1.add(con_mouse);
		salir=new JMenuItem("3 -Salir");
		salir.addActionListener(this);
		menu1.add(salir);
		ayuda=new JMenuItem("Help");
		ayuda.addActionListener(this);
		menu2.add(ayuda);

		contenedor.add(fondoo);//A�adimos imagen al contenedor del panel
		//getContentPane().add(fondoo);
		panel_entrada.setSize(250,200);
		panel_entrada.setVisible(true);
		panel_entrada.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {//Resive las acciones de los botones
		if(e.getSource() == con_mouse)
		{
			Prueba_gridlayout juego_con_mouse = new Prueba_gridlayout();
			juego_con_mouse.setVisible(true);
		}
		if(e.getSource() == sin_mouse)
		{
			panel_entrada.remove(fondoo);
			panel_entrada.setSize(700,350);
			ImageIcon fondo = new ImageIcon((getClass().getResource("/Images/fondo.jpg")));
			Image im_dimension= fondo.getImage();//Obtenemos el tama�o de la imagen
			fondo = new ImageIcon (im_dimension.getScaledInstance(500,350,Image.SCALE_SMOOTH));

			JLabel fondoo= new JLabel(fondo);


			JPanel introduccion_datos = new JPanel(new GridLayout(9, 1));
			JLabel filas= new JLabel("Numero de filas de la matriz:") ;
			//coordx.setBounds(10,10,100,30);
			introduccion_datos.add(filas);
			numero_filas=new JTextField("7");//Creamos la caja(ventana) del texto
			//m_.setBounds(10,40,100,30);//Dimension de la caja a introducir el texto
			introduccion_datos.add(numero_filas);//a�adimos la caja al panel
			JLabel columnas= new JLabel("Numero de columnas de la matriz:") ;
			//coordy.setBounds(10,70,100,30);
			introduccion_datos.add(columnas);
			numero_columnas=new JTextField("7");
			//n_.setBounds(10,90,100,30);
			introduccion_datos.add(numero_columnas);
			JLabel pos_x_pirata=new JLabel("Coordenada x del pirata:");
			//cor_robotx.setBounds(10,110,100,30);
			introduccion_datos.add(pos_x_pirata);

			pirata_x=new JTextField("1");//Creamos la caja(ventana) del texto

			introduccion_datos.add(pirata_x);//a�adimos la caja al panel
			JLabel pos_y_pirata=new JLabel("Coordenada y del pirata:");
			//cor_robotx.setBounds(10,150,100,30);
			introduccion_datos.add(pos_y_pirata);
			pirata_y=new JTextField("1");//Creamos la caja(ventana) del texto
			//py_.setBounds(10,170,100,30);//Dimension de la caja a introducir el texto
			introduccion_datos.add(pirata_y);//anadimos la caja al panel
			JLabel pos_x_tesoro=new JLabel("Coordenada x del tesoro:");
			//cor_tesorx.setBounds(10,190,100,30);
			introduccion_datos.add(pos_x_tesoro);
			tesoro_x=new JTextField("6");//Creamos la caja(ventana) del texto
			//tx_.setBounds(10,210,100,30);//Dimension de la caja a introducir el texto
			introduccion_datos.add(tesoro_x);//anadimos la caja al panel
			JLabel pos_y_tesoro=new JLabel("Coordenada y del tesoro:");
			//cor_tesory.setBounds(10,230,100,30);
			introduccion_datos.add(pos_y_tesoro);
			tesoro_y=new JTextField("6");//Creamos la caja(ventana) del texto
			//ty_.setBounds(10,250,100,30);//Dimension de la caja a introducir el texto
			introduccion_datos.add(tesoro_y);//anadimos la caja al panel
			JLabel numero_obstaculos=new JLabel("Numero de objetos:");
			//carga.setBounds(10,270,100,30);
			introduccion_datos.add(numero_obstaculos);
			carga_obstaculos=new JTextField("12");//Creamos la caja(ventana) del texto
			//carga_.setBounds(10,290,100,30);//Dimension de la caja a introducir el texto
			introduccion_datos.add(carga_obstaculos);//a�adimos la caja al panel


			//Label vacio
			JLabel label_vacio = new JLabel("");
			//label_vacio.setBounds(10,300,100,30);
			introduccion_datos.add(label_vacio);

			//Boton aceptar
			aceptar_sin_mouse = new JButton("Aceptar");
			//aceptar_sin_mouse.setBounds(10,310,100,30);//Dimension de la caja a introducir el texto
			introduccion_datos.add(aceptar_sin_mouse);
			aceptar_sin_mouse.addActionListener(this);

			contenedor.add(fondoo);
			contenedor.add(introduccion_datos);
		}
		if(e.getSource() == aceptar_sin_mouse)
		{
			/*while(!p.empty())
			{
				p.pop();
			}*/
			System.out.print("Aceptar sin mouse");
			num_filas = Integer.parseInt(numero_filas.getText());
			num_columnas = Integer.parseInt(numero_columnas.getText());
			posicion_x_pirata = Integer.parseInt(pirata_x.getText());
			posicion_y_pirata = Integer.parseInt(pirata_y.getText());
			posicion_x_tesoro = Integer.parseInt(tesoro_x.getText());
			posicion_y_tesoro = Integer.parseInt(tesoro_y.getText());
			num_obstaculos = Integer.parseInt(carga_obstaculos.getText());

			System.out.print("Posicion pirata->"+posicion_x_pirata);
			System.out.print("Posicion pirata->"+posicion_y_pirata);

			Interfaz_sin_raton();
		}
		if(e.getSource() == opcion_ver_pila)
		{
			System.out.print("\n\n\nVer pilaaaaa\n");
			int i=0;
			while(!p.empty())
			{
				coordenadas aux = new coordenadas();
				aux = p.pop();
				System.out.print("\nCoordenada->"+aux.get_x()+","+aux.get_y());
				i++;
			}
		}
		if(e.getSource() == nuevo_juego)
		{
			System.out.print("Pulse nuevo juego");
			this.dispose();
			//System.exit(0);
			Panel_entrada nuevo_juego = new Panel_entrada();
			nuevo_juego.setVisible(true);
			nuevo_juego.Menu();
		}
		if(e.getSource() == ver_camino_minimo)
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
		if(e.getSource() == opcion_empezar)
		{
			System.out.print("\n\n\n");
			posicion_x_pirata_aux = posicion_x_pirata;
			posicion_y_pirata_aux = posicion_y_pirata;
			
			//Pongo como primera posición en la pila la posición inicial del pirata
			p.push(posicion_x_pirata_aux,posicion_y_pirata_aux);
			
			mCasillas_visitados = new Boolean[num_filas][num_columnas];
			for(int i=0;i<num_filas;i++)
			{
				for(int j=0;j<num_columnas;j++)
				{
					mCasillas_visitados[i][j] = false;
				}
			}
			mCasillas_visitados[posicion_x_pirata_aux][posicion_y_pirata_aux] = true;

			int contador=0;
			control_bucles = false;
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
										ImageIcon imagen_pirata3 = new ImageIcon(getClass().getResource("/Images/arriba.jpg"));
										ImageIcon imagen_escalada3_1= new ImageIcon(imagen_pirata3.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada3_1);
									}
									if((posicion_pila_x == posicion_x_pirata_aux+1)&&(posicion_pila_y == posicion_y_pirata_aux))
									{
										ImageIcon imagen_pirata3 = new ImageIcon(getClass().getResource("/Images/abajo.jpg"));
										ImageIcon imagen_escalada3_1= new ImageIcon(imagen_pirata3.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada3_1);
									}
									if((posicion_pila_x == posicion_x_pirata_aux)&&(posicion_pila_y==posicion_y_pirata_aux-1))
									{
										ImageIcon imagen_pirata3 = new ImageIcon(getClass().getResource("/Images/izquierda.jpg"));
										ImageIcon imagen_escalada3_1= new ImageIcon(imagen_pirata3.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
										mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada3_1);
									}
									if((posicion_pila_x == posicion_x_pirata_aux)&&(posicion_pila_y == posicion_y_pirata_aux+1))
									{
										ImageIcon imagen_pirata3 = new ImageIcon(getClass().getResource("/Images/derecha.jpg"));
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


								ImageIcon imagen_pirata = new ImageIcon(getClass().getResource("/Images/arriba.jpg"));
								ImageIcon imagen_escalada= new ImageIcon(imagen_pirata.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
								mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada);	
								mCasillas_visitados[posicion_x_pirata_aux][posicion_y_pirata_aux]=true;
								p.push(posicion_x_pirata_aux, posicion_y_pirata_aux);

								posicion_x_pirata_aux = posicion_x_pirata_aux-1;

								break;
							case 2:
								System.out.print("Entre en case 2");
								//ImageIcon imagen_arena1 = new ImageIcon(getClass().getResource("/Images/arena1.jpg"));
								//ImageIcon imagen_escalada1= new ImageIcon(imagen_arena1.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
								//mCasillas[pos_x_pirata_aux][pos_y_pirata_aux].setIcon(imagen_escalada1);
								//Actualizo matriz visitados
								mCasillas_visitados[posicion_x_pirata_aux][posicion_y_pirata_aux]=true;

								ImageIcon imagen_pirata1 = new ImageIcon(getClass().getResource("/Images/abajo.jpg"));
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

								ImageIcon imagen_pirata2 = new ImageIcon(getClass().getResource("/Images/izquierda.jpg"));
								ImageIcon imagen_escalada2_1= new ImageIcon(imagen_pirata2.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
								mCasillas[posicion_x_pirata_aux][posicion_y_pirata_aux].setIcon(imagen_escalada2_1);

								//Meto nueva posición en la pila
								p.push(posicion_x_pirata_aux, posicion_y_pirata_aux);

								posicion_y_pirata_aux = posicion_y_pirata_aux - 1;

								break;
							case 4:
								System.out.print("Entre en case 4");
								//ImageIcon imagen_arena3 = new ImageIcon(getClass().getResource("/Images/arena1.jpg"));
								//ImageIcon imagen_escalada3= new ImageIcon(imagen_arena3.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
								//mCasillas[pos_x_pirata_aux][pos_y_pirata_aux].setIcon(imagen_escalada3);
								//Actualizo matriz visitados
								mCasillas_visitados[posicion_x_pirata_aux][posicion_y_pirata_aux]=true;

								ImageIcon imagen_pirata3 = new ImageIcon(getClass().getResource("/Images/derecha.jpg"));
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
	}


	private int calcular_heuristica() {
		// TODO Auto-generated method stub
		//Encontrar el pirata Posicion_x_pirata y Posicion_y_pirata
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
	
		if(distancia > (Math.sqrt(Math.pow((posicion_x_pirata_aux+1)-posicion_x_tesoro,2)+Math.pow(posicion_y_pirata_aux-posicion_y_tesoro,2))) && (posicion_x_pirata_aux+1<=num_filas-1))
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
		if(distancia > (Math.sqrt(Math.pow(posicion_x_pirata_aux-posicion_x_tesoro,2)+Math.pow((posicion_y_pirata_aux+1)-posicion_y_tesoro,2))) && (posicion_y_pirata_aux+1<=num_columnas-1))
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

	private void Interfaz_sin_raton() {
		// TODO Auto-generated method stub
		JFrame interfaz = new JFrame(" Búsqueda del tesoro ");
		interfaz.setBounds(10,10,250,200);
		//interfaz.setLocationRelativeTo(null);//Para centrar en la pantalla
		interfaz.setResizable(false);
		interfaz.setVisible(true);
		interfaz.setSize(900,600);

		contenedor = interfaz.getContentPane();
		contenedor.setSize(900, 600);



		JPanel panel_matriz = new JPanel(new GridLayout(num_filas,num_columnas));
		panel_matriz.setBounds(300, 100, 200, 200);
		panel_matriz.setSize(900,600);

		menu_empezar_juego=new JMenuBar();//Crea una barra de menu
		interfaz.setJMenuBar(menu_empezar_juego);
		opcion_empezar=new JButton("Empezar");//Crea boton opciones
		opcion_empezar.addActionListener(this);
		menu_empezar_juego.add(opcion_empezar);//Lo a�ade a la barra 
		
		opcion_ver_pila = new JButton("Ver pila");
		opcion_ver_pila.addActionListener(this);
		//menu_empezar_juego.add(opcion_ver_pila);
		
		nuevo_juego = new JButton("Nuevo juego");
		nuevo_juego.addActionListener(this);
		//menu_empezar_juego.add(nuevo_juego);
		
		ver_camino_minimo = new JButton("Mejor Camino");
		ver_camino_minimo.addActionListener(this);
		menu_empezar_juego.add(ver_camino_minimo);
		
		//INICIALIZO MATRIZ DE BOTONES
		mCasillas = new JButton[num_filas][num_columnas];
	
		for(int i=0;i<num_filas;i++)
		{
			for(int j=0;j<num_columnas;j++)
			{
				mCasillas[i][j] = new JButton();

				ancho = 900/num_columnas;
				alto = 600/num_filas;


				// Obtiene un icono en escala con las dimensiones especificadas
				ImageIcon imagen_arena = new ImageIcon(getClass().getResource("/Images/arena.jpg"));
				ImageIcon imagen_escalada= new ImageIcon(imagen_arena.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
				System.out.print("Medida_imagen:"+ imagen_escalada.getIconHeight());
				mCasillas[i][j].setIcon(imagen_escalada);
				panel_matriz.add(mCasillas[i][j]);
			}
		}	

		System.out.print("\n");
		//Coloca los las imágenes
		ImageIcon imagen_pirata = new ImageIcon(getClass().getResource("/Images/pirata.jpg"));
		ImageIcon imagen_escalada_pirata = new ImageIcon(imagen_pirata.getImage().getScaledInstance(ancho,alto,java.awt.Image.SCALE_SMOOTH));
		mCasillas[posicion_x_pirata][posicion_y_pirata].setIcon(imagen_escalada_pirata);
		//panel_matriz.add(mCasillas[posicion_x_pirata][posicion_y_pirata]);
		panel_matriz.setVisible(true);
		System.out.print("Posicion_x_pirata->"+posicion_x_pirata);
		System.out.print("Posicion_y_pirata->"+posicion_y_pirata);
		System.out.print("\n");

		ImageIcon imagen_tesoro = new ImageIcon(getClass().getResource("/Images/tesoro.jpg"));
		ImageIcon imagen_escalada_tesoro = new ImageIcon(imagen_tesoro.getImage().getScaledInstance(ancho,alto,java.awt.Image.SCALE_SMOOTH));
		mCasillas[posicion_x_tesoro][posicion_y_tesoro].setIcon(imagen_escalada_tesoro);
		System.out.print("\n");
		System.out.print("Posicion_x_tesoro->"+posicion_x_tesoro);
		System.out.print("Posicion_y_tesoro->"+posicion_y_tesoro);
		System.out.print("\n");

		System.out.print("\n");
		System.out.print("Numero de obstaculos->"+num_obstaculos);
		int aux = 0;
		int pos_x_prev = -1;
		int pos_y_prev = -1;

		/*for(int i=0;i<num_filas;i++)
	    {
	    	for(int j=0;j<num_columnas;j++)
	    	{
	    		mCasillas_Obstaculos[i][j] = false;
	    	}
	    }*/
		
		mCasillas_Obstaculos = new Boolean[num_filas][num_columnas];
		for(int i=0;i<num_filas;i++)
		{
			for(int j=0;j<num_columnas;j++)
			{
				mCasillas_Obstaculos [i][j]= false;
			}
		}
		while(aux < num_obstaculos)
		{
			System.out.print("\n");
			System.out.print("iwecninioec");
			Random r = new Random();
			int valorDado = r.nextInt(3)+1; 
			Random r1 = new Random();
			int pos_x_aleatoria = r1.nextInt(num_filas);
			System.out.print("Pos_x_aleatoria->"+pos_x_aleatoria);
			Random r2 = new Random();
			int pos_y_aleatoria = r2.nextInt(num_columnas);
			System.out.print("Pos_y_aleatoria->"+pos_y_aleatoria);
			System.out.print("\n");
			ImageIcon imagen_palmera = new ImageIcon(getClass().getResource("/Images/obstaculo"+valorDado+".jpg"));
			ImageIcon imagen_escalada = new ImageIcon(imagen_palmera.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
			if((pos_x_aleatoria == posicion_x_pirata && pos_y_aleatoria == posicion_y_pirata) || (pos_x_aleatoria == posicion_x_tesoro && pos_y_aleatoria == posicion_y_tesoro))
			{
				System.out.print("no pongo obstaculo");
			}
			else
			{
				//Tenemos que arreglar esto. Tengo que comparar con todas las posiciones de los obstaculos aleatorios
				pos_x_prev = pos_x_aleatoria;
				pos_y_prev = pos_y_aleatoria;
				//	System.out.print("Si pongo obstaculo");
				mCasillas[pos_x_aleatoria][pos_y_aleatoria].setIcon(imagen_escalada);
			//	System.out.print("LLEGO 1");
				mCasillas_Obstaculos[pos_x_aleatoria][pos_y_aleatoria] = true;
			//	System.out.print("LLEGO 2");
				aux++;
			}
			
		}
		

			ImageIcon imagen_arena = new ImageIcon(getClass().getResource("/Images/obstaculo1.jpg"));
			ImageIcon imagen_escalada= new ImageIcon(imagen_arena.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_FAST));
			System.out.print("Medida_imagen:"+ imagen_escalada.getIconHeight());
		
				
		panel_matriz.setVisible(true);
		contenedor.add(panel_matriz);
	}
}
