/**
 *
 */
package com.zhazhapan.util.dialog;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;

import java.util.Optional;

/**
 * @author pantao
 */
public class Dialogs {

    private static final String CANCEL_BUTTON_TEXT = "取消";

    public static String showInputDialog(String title, String header, String content, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    public GridPane getGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        return grid;
    }

    public Dialog<String[]> getDialog(String title, ButtonType ok) {
        Dialog<String[]> dialog = new Dialog<String[]>();
        dialog.setTitle(title);
        dialog.setHeaderText(null);

        dialog.initModality(Modality.APPLICATION_MODAL);

        // 自定义确认和取消按钮
        ButtonType cancel = new ButtonType(CANCEL_BUTTON_TEXT, ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(ok, cancel);
        return dialog;
    }
}
