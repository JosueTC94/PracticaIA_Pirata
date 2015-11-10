package Paquete;

import java.util.ArrayList;


public class pila{

	private static ArrayList<coordenadas> p = new ArrayList<coordenadas>();
	
	/*public void vaciar()
	{
		while(!(p.isEmpty()))
		{
			coordenadas o = p.pop();
		}
	}*/
	public void push(Object o, Object o1)
	{
		//Agregar valores a la pila
		coordenadas nuevo_nodo = new coordenadas(o, o1);
		p.add(nuevo_nodo);
		
	}
	public static coordenadas pop()
	{
		//Muestro ultimo valor y lo elimino
		if(!(p.isEmpty()))
		{
			coordenadas o = p.get(p.size()-1);
			p.remove(p.size()-1);
			return o;
		}
		else
		{
			return null;
		}
	}

	public Boolean empty()
	{
		return p.isEmpty();
	}
}
