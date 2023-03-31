package com.client.controllers;

import com.client.Client;
import com.client.managers.SceneManager;
import com.client.model.SceneType;
import javafx.fxml.FXML;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Controller {

    private final SceneManager sceneManager = Client.getInstance().getSceneManager();
    private boolean isLogin = false;
    @FXML
    public void LoginAction() throws IOException
    {
        isLogin = true;
        if(isLogin == true)
        {
            sceneManager.switchScene(SceneType.MAIN_SCENE);
        }
    }
    @FXML
    public void switchToMainScene() throws IOException {
        sceneManager.switchScene(SceneType.MAIN_SCENE);
    }

    @FXML
    public void switchToSignUpScene() throws IOException {
        sceneManager.switchScene(SceneType.SIGNUP_SCENE);
    }

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
        sceneManager.minimizeWindow(closeButton);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void moreHelp(MouseEvent mouseEvent) {
        System.out.println("Clicked");
    }



    @Override
    public void clear()
    {

    }
}