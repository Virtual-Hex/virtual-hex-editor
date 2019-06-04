package com.mr00anderson.data;

public class Menu {

    // This class probably should be a any menu builder, essentially a linked list od worldsHeader


    public static final class JaweMenuBuilder {


        private JaweMenuBuilder() {
        }

        public static Menu.JaweMenuBuilder aJaweWindow() {
            return new Menu.JaweMenuBuilder();
        }
    }

}
