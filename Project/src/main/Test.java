package main;

public class Test {
    public static void main(String[] args) {
        String s = "EE332";

        String a[] = s.split("1");
        String b[] = s.split("2");
        String c[] = s.split("3");
        String d[] = s.split("4");
        System.out.println(a[0]);
        System.out.println(b[0]);
        System.out.println(c[0]);
        System.out.println(d[0]);

        String f[] = s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        System.out.println(f[1]);
    }
}
