package ua.serg.objects;

import org.controlsfx.control.textfield.CustomTextField;

/**
 * Created by shpak on 26.08.2016.
 */

//Позволяет вводить в текстовое поле только Цифры
public class NumberTextField extends CustomTextField {
    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[0-9]") || text.matches("[.]")/* || text.matches(",")*/ || text.isEmpty()) {
            super.replaceText(start, end, text);

        }

    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }
}

