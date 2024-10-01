public class Util {
    public static void printTitle() {
        String title = "" +
                "███████╗ █████╗ ██╗     ██████╗ ███████╗██╗  ██╗\n" +
                "██╔════╝██╔══██╗██║     ██╔══██╗██╔════╝╚██╗██╔╝\n" +
                "███████╗███████║██║     ██║  ██║█████╗   ╚███╔╝ \n" +
                "╚════██║██╔══██║██║     ██║  ██║██╔══╝   ██╔██╗ \n" +
                "███████║██║  ██║███████╗██████╔╝███████╗██╔╝ ██╗\n" +
                "╚══════╝╚═╝  ╚═╝╚══════╝╚═════╝ ╚══════╝╚═╝  ╚═╝";
        System.out.println(title);
        System.out.println("An alternative Pokedex by 201 students!\n");
    }

    public static void showLoadingMessage(String message) throws InterruptedException {
        System.out.print(message);
        for (int i = 0; i < 3; i++) {
            Thread.sleep(800);
            System.out.print(".");
        }
        System.out.print("DONE\n");
    }
}
