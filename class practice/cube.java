package work;

public class cube extends rectangular{
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
		double area = lon*side*6;
		return area;
	}
}
