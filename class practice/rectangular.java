package work;

public class rectangular implements shape{

	@Override
	public double vol(double a, double b,double c) {
		// TODO Auto-generated method stub
		double lon = a;
		double side = b;
		double h = c;
		double vol = lon*side*h;
		return vol;
	}
	@Override
	public double area(double a, double b, double c) {
		// TODO Auto-generated method stub
		double lon = a;
		double side =b;
		double high =c;
		double area = (lon*side*2)+(lon*high*2)+(side*high*2);
		return area;
	}
	@Override
	public double vol(double a, double b) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double area(double a, double b) {
		// TODO Auto-generated method stub
		return 0;
	}
}
