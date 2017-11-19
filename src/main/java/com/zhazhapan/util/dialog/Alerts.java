/**
 * 
 */
package com.zhazhapan.util.dialog;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * @author pantao 对JavaFX对话框进行封装
 */
@SuppressWarnings("restriction")
public class Alerts {

	public static Optional<ButtonType> showInformation(String title, String content) {
		return showInformation(title, null, content);
	}

	public static Optional<ButtonType> showInformation(String title, String header, String content) {
		return alert(title, header, content, AlertType.INFORMATION);
	}

	public static Optional<ButtonType> showWarning(String title, String content) {
		return showWarning(title, null, content);
	}

	public static Optional<ButtonType> showWarning(String title, String header, String content) {
		return alert(title, header, content, AlertType.WARNING);
	}

	public static Optional<ButtonType> showError(String title, String content) {
		return showError(title, null, content);
	}

	public static Optional<ButtonType> showError(String title, String header, String content) {
		return alert(title, header, content, AlertType.ERROR);
	}

	public static Optional<ButtonType> showConfirmation(String title, String content) {
		return showConfirmation(title, null, content);
	}

	public static Optional<ButtonType> showConfirmation(String title, String header, String content) {
		return alert(title, header, content, AlertType.CONFIRMATION);
	}

	public static Optional<ButtonType> showException(String title, Exception e) {
		return showException(title, null, e);
	}

	public static void showFatalError(String title, String header, Exception e) {
		showException(title, header, e);
		System.exit(0);
	}

	public static Optional<ButtonType> showException(String title, String header, Exception e) {
		Alert alert = getAlert(title, header, "错误信息追踪：", AlertType.ERROR);

		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		String exception = stringWriter.toString();

		TextArea textArea = new TextArea(exception);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane gridPane = new GridPane();
		gridPane.setMaxWidth(Double.MAX_VALUE);
		gridPane.add(textArea, 0, 0);

		alert.getDialogPane().setExpandableContent(gridPane);

		return alert.showAndWait();
	}

	public static Optional<ButtonType> alert(String title, String content) {
		return alert(title, null, content);
	}

	public static Optional<ButtonType> alert(String title, String content, AlertType alertType) {
		return alert(title, null, content, alertType);
	}

	public static Optional<ButtonType> alert(String title, String header, String content) {
		return alert(title, header, content, AlertType.INFORMATION);
	}

	public static Optional<ButtonType> alert(String title, String header, String content, AlertType alertType) {
		return alert(title, header, content, alertType, Modality.NONE, null, StageStyle.DECORATED);
	}

	public static Optional<ButtonType> alert(String title, String header, String content, AlertType alertType,
			Modality modality, Window window, StageStyle style) {
		return getAlert(title, header, content, alertType, modality, window, style).showAndWait();
	}

	public static Alert getAlert(String title, String header, String content, AlertType alertType) {
		return getAlert(title, header, content, alertType, Modality.APPLICATION_MODAL, null, StageStyle.DECORATED);
	}

	public static Alert getAlert(String title, String header, String content, AlertType alertType, Modality modality,
			Window window, StageStyle style) {
		Alert alert = new Alert(alertType);

		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.initModality(modality);
		alert.initOwner(window);
		alert.initStyle(style);

		return alert;
	}
}
