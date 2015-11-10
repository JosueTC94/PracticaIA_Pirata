package Paquete;

public class coordenadas
{
	Object x;
	Object y;
	
	public coordenadas()
	{
		x = -1;
		y = -1;
	}
	public coordenadas(Object x_,Object y_)
	{
		x = x_;
		y = y_;
	}
	
	public Object get_x()
	{
		return x;
	}
	public Object get_y()
	{
		return y;
	}
	public void set_x(Object x_)
	{
		x = x_;
	}
	public void set_y(Object y_)
	{
		y = y_;
	}
}