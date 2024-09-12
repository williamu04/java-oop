import javax.swing.*;

public class Main{
    private static String judul;
    public Main() {
        judul = "Test";
    }

    public static void main(String[] args) {
        JFrame myWindow;
        myWindow = new JFrame();
        myWindow.setSize(500, 300);
        myWindow.setTitle(judul);
        myWindow.setVisible(true);
    }
}