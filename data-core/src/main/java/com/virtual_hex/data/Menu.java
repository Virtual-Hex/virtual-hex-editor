package com.virtual_hex.data;

public class Menu extends UIData{

    // This class probably should be a any menu builder, essentially a linked list od worldsHeader


    public static final class JaweMenuBuilder {


        private JaweMenuBuilder() {
        }

        public static Menu.JaweMenuBuilder aJaweWindow() {
            return new Menu.JaweMenuBuilder();
        }
    }

}
