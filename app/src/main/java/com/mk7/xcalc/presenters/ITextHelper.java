package com.mk7.xcalc.presenters;

/**
 * Created by MK7 on 5/15/2015.
 */
public interface ITextHelper {

    ITextHelper text(String text);
    ITextHelper append(String text);
    ITextHelper replace(String text, int start, int end);
    ITextHelper toEnd();
    ITextHelper toNextCursor();
    ITextHelper toPreviousCursor();
    ITextHelper toNewLine();
    ITextHelper toBackspace();
    ITextHelper toRemoveSelection();
    ITextHelper clear();
    ITextHelper cursorPosition(int index);

    String getText();
    String getSelectionToString();
    String getNextChar();
    String getPreviousChar();
    int getSelectionStart();
    int getSelectionEnd();
    int length();

}
