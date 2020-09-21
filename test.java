import press.*;

public class test {

	public static void main(String[] args) throws InvalidExpressException {
		Pressure p = new Pressure("-9.9909293");
		System.out.println(p.add("-1111.11"));
	}
}