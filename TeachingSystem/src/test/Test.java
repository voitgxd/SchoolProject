package test;

public class Test {
	public static void main(String[] args) {
		final StringBuffer b = new StringBuffer().append("abc");
		b.append("def");
		System.out.println(b);
	}
}
