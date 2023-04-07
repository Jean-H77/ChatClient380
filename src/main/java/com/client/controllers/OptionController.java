package com.client.controllers;

import com.client.Client;
import com.client.managers.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionController implements Controller{

    @Override
    public void clear() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private final SceneManager sceneManager = Client.getInstance().getSceneManager();
    @FXML
    Button closeButton = new Button();
    @FXML
    Button minimizeButton = new Button();
    @FXML
    public void closeAction() throws IOException
    {
        sceneManager.closeWindow(closeButton);
    }
    @FXML
    public void minimizeAction() throws IOException
    {
        sceneManager.minimizeWindow(minimizeButton);
    }

    private double x = 0;
    private double y = 0;

    @FXML
    public void menuPane_dragged(MouseEvent event)
    {
        Stage stage = Client.getInstance().getPrimaryStage();
        stage.setX(event.getScreenX() + x);
        stage.setY(event.getScreenY() + y);
    }

    @FXML
    public void menuPane_pressed(MouseEvent event)
    {
        Stage stage = Client.getInstance().getPrimaryStage();
        x = stage.getX() - event.getScreenX();
        y = stage.getY() - event.getScreenY();
    }
}
