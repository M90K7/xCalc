package com.mk7.xcalc.views;

import android.text.Html;
import android.text.Spanned;
import android.widget.EditText;

import com.mk7.xcalc.presenters.ITextHelper;

/**
 * Created by MK7 on 4/24/2015.
 */
public class EditTextHelper implements ITextHelper {

    EditText et;

    public EditTextHelper(EditText editText) {
        this.et = editText;
    }

    public void setEditText(EditText editText) {
        this.et = editText;
    }

   /* Spanned toSpanned(String string){
        return Html.fromHtml(string);
    }*/

    @Override
    public EditTextHelper text(String text) {
        //et.setText(toSpanned(text));
        et.setText(text);
        return this;
    }

    @Override
    public EditTextHelper append(String text) {
        //et.getEditableText().insert(et.getSelectionStart(), toSpanned(text));
        et.getEditableText().insert(et.getSelectionStart(), text);
        return this;
    }

    @Override
    public EditTextHelper replace(String text, int start, int end) {
        //et.getEditableText().replace(start, end, toSpanned(text));
        et.getEditableText().replace(start, end, text);
        return this;
    }

    @Override
    public EditTextHelper toEnd() {
        cursorPosition(et.length());
        return this;
    }

    @Override
    public EditTextHelper toNextCursor() {

        int position = et.getSelectionStart() + 1;
        if (position > et.length())
            position = et.length();
        return cursorPosition(position);
    }

    @Override
    public EditTextHelper toPreviousCursor() {
        int position = et.getSelectionStart() - 1;
        if (position < 0)
            position = 0;
        return cursorPosition(position);
    }

    @Override
    public EditTextHelper toNewLine() {
        toEnd();
        et.append("\n");
        return this;
    }

    @Override
    public EditTextHelper toBackspace() {

        int sStart = et.getSelectionStart();
        int sEnd = et.getSelectionEnd();
        int selectLen = sEnd - sStart;

        if (et.length() == 0 || (selectLen == 0 && sStart == 0))
            return this;


        if (selectLen > 0) {
            et.getEditableText().delete(sStart, sEnd);
        } else {
            et.getEditableText().delete(sStart - 1, sStart);
        }
        return this;

    }

    @Override
    public ITextHelper toRemoveSelection() {
        int sStart = getSelectionStart();
        int sEnd = getSelectionEnd();
        int selectLen = sEnd - sStart;

        if (selectLen > 0)
            replace("", sStart, sEnd);

        return this;
    }

    @Override
    public EditTextHelper clear() {
        et.getEditableText().clear();
        return this;
    }

    @Override
    public EditTextHelper cursorPosition(int index) {
        et.setSelection(index);
        return this;
    }

    @Override
    public String getText() {
        return et.getText().toString();
    }

    @Override
    public String getSelectionToString() {
        int sStart = et.getSelectionStart();
        int sEnd = et.getSelectionEnd();

        return et.getEditableText().subSequence(sStart, sEnd).toString();
    }

    @Override
    public String getNextChar() {
        int next = getSelectionStart();
        if (length() == 0 || next >= length() )
            return "";
        return String.valueOf(et.getEditableText().charAt(next));
    }

    @Override
    public String getPreviousChar() {
        int prev = getSelectionStart() - 1;
        if (length() == 0 || prev < 0)
            return "";
        return String.valueOf(et.getEditableText().charAt(prev));
    }

    @Override
    public int getSelectionStart() {
        return et.getSelectionStart();
    }

    @Override
    public int getSelectionEnd() {
        return et.getSelectionEnd();
    }

    @Override
    public int length() {
        return et.length();
    }
}
