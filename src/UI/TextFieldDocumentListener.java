/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import game.Board;
import java.awt.Color;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 *
 * @author felipe
 */
public class TextFieldDocumentListener implements DocumentListener {
    
    JTextField textField;
    String textFromCell, textName;
    int insertedNumber, index, data, row, col;
    Document doc;
    Board board;
    
    public TextFieldDocumentListener(Board b, JTextField text) {
        board = b;
        textField = text;
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        doc = e.getDocument();
        try {
            data = Integer.valueOf(doc.getText(0, doc.getLength()));
            textName = textField.getName();
            index = textName.indexOf('.');
            row = Integer.valueOf(textName.substring(0, index));
            col = Integer.valueOf(textName.substring(index + 1, textName.length()));
            board.validateInput(row, col, data);
            TextFieldFocusListener.res = board.getValidationResult();
            board.resetValidationResult();
            if(board.getIsWon()) {
                System.out.println("Game won");
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(TextFieldDocumentListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        doc = e.getDocument();
        try {
            data = Integer.valueOf(doc.getText(0, doc.getLength()));
            textName = textField.getName();
            index = textName.indexOf('.');
            row = Integer.valueOf(textName.substring(0, index));
            col = Integer.valueOf(textName.substring(index + 1, textName.length()));
            board.validateInput(row, col, data);
            TextFieldFocusListener.res = board.getValidationResult();
            board.resetValidationResult();
            if(board.isWon()) {
                System.out.println("Game won");
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(TextFieldDocumentListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
