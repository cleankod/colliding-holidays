public class Application {
    public String getGreeting() {
        return "Hello BS!";
    }

    public static void main(String[] args) {
        System.out.println(new Application().getGreeting());
    }
}
