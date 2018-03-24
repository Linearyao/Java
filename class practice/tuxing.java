package work;

public class tuxing implements shape{

	@Override
	public double vol(double a,double b) {
		// TODO Auto-generated method stub
		double r = a;
		double h = b;
		double vol = 3.14*r*h;
		return vol;
	}

	@Override
	public double area(double a,double b) {
		// TODO Auto-generated method stub
		double r = a;
		double h = b;
		double area = (3.14*r*r*2)+(3.14*2*r*h);
		return area;
	}

	@Override
	public double vol(double a, double b, double c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double area(double a, double b, double c) {
		// TODO Auto-generated method stub
		return 0;
	}

}
