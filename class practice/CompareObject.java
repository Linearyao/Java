package work;

public abstract class CompareObject {
	public abstract double CompareTo(Position a);
}
class Position extends CompareObject {
	private double x,y,z;
	public Position(){
		this.x=10.0;
		this.y=5.0;
		this.z=6.0;
	}
	public Position(double a,double b,double c){
		this.x=a;
		this.y=b;
		this.z=c;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}
	public double CompareTo(Position a) {
		double d1=Math.pow(Math.pow(a.getX(), 2)+Math.pow(a.getY(), 2)+Math.pow(a.getZ(), 2), 0.5);
		double d2=Math.pow(Math.pow(this.getX(), 2)+Math.pow(this.getY(), 2)+Math.pow(this.getZ(), 2), 0.5);
		return d1-d2;
	}
}