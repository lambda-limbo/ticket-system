package org.ticket.view;


import java.awt.*;

public class Screen {

    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Dimension dimension;


    private Screen() {}

    public static Dimension getScreenSize() {
        dimension = toolkit.getScreenSize();
        return dimension;
    }

    public static int height() {
        return dimension.height;
    }

    public static int width() {
        return dimension.width;
    }

}
