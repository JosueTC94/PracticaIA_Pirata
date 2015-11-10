package Paquete;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu_principal extends JFrame implements ActionListener{

	private JPanel contentPane;
	JMenu menu1,menu2;
	JMenuItem sin_mouse,con_mouse,ayuda,salir;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_principal frame = new Menu_principal();
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
	public Menu_principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 350, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		ImageIcon fondo = new ImageIcon((getClass().getResource("/Images/fondo.jpg")));
		Image im_dimension= fondo.getImage();//Obtenemos el tama�o de la imagen
		fondo = new ImageIcon (im_dimension.getScaledInstance(350,350,Image.SCALE_SMOOTH));

		JLabel fondoo= new JLabel(fondo);
		contentPane.add(fondoo);
		JMenuBar mb;
		//JMenu menu1,menu2;
		
		//setLayout(null);
		mb=new JMenuBar();//Crea una barra de menu
		menu1=new JMenu("Inicio");//Crea boton opciones
		menu2=new JMenu("Ayuda");
		mb.add(menu1);//Lo a�ade a la barra 
		mb.add(menu2);
		contentPane.add(mb);//a�adimos barra de menu al panel
		/*
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
		menu2.add(ayuda);*/

		contentPane.setSize(350,350);
		contentPane.setVisible(true);
	}

}
