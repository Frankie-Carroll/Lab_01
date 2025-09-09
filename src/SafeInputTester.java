public class SafeInputTester {
    public static void main(String[] args) {
        SafeInputObject in = new SafeInputObject();

        String name = in.getNonZeroLenString("Enter your name");
        int age = in.getInt("Enter your age");
        double gpa = in.getDouble("Enter your gpa");
        double YOB = in.getRangedDouble("Enter you year of birth", 1000, 9999);
        boolean cont = in.getYNConfirm("Do you wish to add more");

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("YOB: " + YOB);
        System.out.println("GPA: " + gpa);
        System.out.println("Continue? " + cont);
    }
}
