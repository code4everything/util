/**
 * 
 */
package com.zhazhapan.util.fx;

import java.net.URL;

import org.apache.log4j.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * @author pantao
 *
 */
@SuppressWarnings("restriction")
public class FxmlLoader {

	private static final Logger logger = Logger.getLogger(FxmlLoader.class);

	public static Scene loadFxml(URL url) {
		logger.info("load fxml from url: " + url);
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(url);
			return new Scene(root);
		} catch (Exception e) {
			logger.error("load fxml error: " + e.getMessage());
		}
		return null;
	}
}
