package com.zhazhapan.util.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

/**
 * @author pantao 对JavaFX对话框进行封装
 */
public class Alerts {

    private Alerts() {}

    /**
     * 弹出信息框
     *
     * @param title 标题
     * @param content 内容
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> showInformation(String title, String content) {
        return showInformation(title, null, content);
    }

    /**
     * 弹出信息框
     *
     * @param title 标题
     * @param header 信息头
     * @param content 内容
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> showInformation(String title, String header, String content) {
        return alert(title, header, content, AlertType.INFORMATION);
    }

    /**
     * 弹出警告框
     *
     * @param title 标题
     * @param content 内容
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> showWarning(String title, String content) {
        return showWarning(title, null, content);
    }

    /**
     * 弹出警告框
     *
     * @param title 标题
     * @param header 信息头
     * @param content 内容
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> showWarning(String title, String header, String content) {
        return alert(title, header, content, AlertType.WARNING);
    }

    /**
     * 弹出错误框
     *
     * @param title 标题
     * @param content 内容
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> showError(String title, String content) {
        return showError(title, null, content);
    }

    /**
     * 弹出错误框
     *
     * @param title 标题
     * @param header 信息头
     * @param content 内容
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> showError(String title, String header, String content) {
        return alert(title, header, content, AlertType.ERROR);
    }

    /**
     * 弹出确认框
     *
     * @param title 标题
     * @param content 内容
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> showConfirmation(String title, String content) {
        return showConfirmation(title, null, content);
    }

    /**
     * 弹出确认框
     *
     * @param title 标题
     * @param header 信息头
     * @param content 内容
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> showConfirmation(String title, String header, String content) {
        return alert(title, header, content, AlertType.CONFIRMATION);
    }

    /**
     * 弹出异常框
     *
     * @param title 标题
     * @param e 异常
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> showException(String title, Exception e) {
        return showException(title, null, e);
    }

    /**
     * 弹出异常框，并退出程序
     *
     * @param title 标题
     * @param header 信息头
     * @param e 异常
     */
    public static void showFatalError(String title, String header, Exception e) {
        showException(title, header, e);
        System.exit(0);
    }

    /**
     * 弹出异常框
     *
     * @param title 标题
     * @param header 信息头
     * @param e 异常
     *
     * @return {@link ButtonType}
     */
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

    /**
     * 弹窗，header默认为null，alertType默认为{@link AlertType#INFORMATION}，modality默认为{@link
     * Modality#NONE}，window默认为null，style默认为{@link StageStyle#DECORATED}
     *
     * @param title 标题
     * @param content 内容
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> alert(String title, String content) {
        return alert(title, null, content);
    }

    /**
     * 弹窗，header默认为null，modality默认为{@link Modality#NONE}，window默认为null，style默认为{@link StageStyle#DECORATED}
     *
     * @param title 标题
     * @param content 内容
     * @param alertType {@link AlertType}
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> alert(String title, String content, AlertType alertType) {
        return alert(title, null, content, alertType);
    }

    /**
     * 弹窗，alertType默认为{@link AlertType#INFORMATION}，modality默认为{@link Modality#NONE}，window默认为null，style默认为{@link
     * StageStyle#DECORATED}
     *
     * @param title 标题
     * @param header 信息头
     * @param content 内容
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> alert(String title, String header, String content) {
        return alert(title, header, content, AlertType.INFORMATION);
    }

    /**
     * 弹窗，modality默认为{@link Modality#NONE}，window默认为null，style默认为{@link StageStyle#DECORATED}
     *
     * @param title 标题
     * @param header 信息头
     * @param content 内容
     * @param alertType {@link AlertType}
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> alert(String title, String header, String content, AlertType alertType) {
        return alert(title, header, content, alertType, Modality.NONE, null, StageStyle.DECORATED);
    }

    /**
     * 弹窗
     *
     * @param title 标题
     * @param header 信息头
     * @param content 内容
     * @param alertType {@link AlertType}
     * @param modality {@link Modality}
     * @param window {@link Window}
     * @param style {@link StageStyle}
     *
     * @return {@link ButtonType}
     */
    public static Optional<ButtonType> alert(String title, String header, String content, AlertType alertType,
                                             Modality modality, Window window, StageStyle style) {
        return getAlert(title, header, content, alertType, modality, window, style).showAndWait();
    }

    /**
     * 获取{@link Alert}对象，modality默认为{@link Modality#APPLICATION_MODAL}，window默认为null，style默认为{@link
     * StageStyle#DECORATED}
     *
     * @param title 标题
     * @param header 信息头
     * @param content 内容
     * @param alertType {@link AlertType}
     *
     * @return {@link Alert}
     */
    public static Alert getAlert(String title, String header, String content, AlertType alertType) {
        return getAlert(title, header, content, alertType, Modality.APPLICATION_MODAL, null, StageStyle.DECORATED);
    }

    /**
     * 获取{@link Alert}对象
     *
     * @param title 标题
     * @param header 信息头
     * @param content 内容
     * @param alertType {@link AlertType}
     * @param modality {@link Modality}
     * @param window {@link Window}
     * @param style {@link StageStyle}
     *
     * @return {@link Alert}
     */
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
