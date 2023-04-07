package com.client.model;

public enum SceneType {
    LOGIN_SCENE("views/login-view.fxml", 1000, 560),
    MAIN_SCENE("views/main-view.fxml", 1000, 560),
    SIGNUP_SCENE("views/signup-view.fxml", 1000, 560),
    COMPLETED_REGISTRATION_SCENE("views/completed-registration-view.fxml",411, 370),
    OPTION_SCENE("views/option-view.fxml", 600, 800);

    private final String path;
    private final double width;
    private final double height;

    SceneType(String path, double width, double height) {
        this.path = path;
        this.width = width;
        this.height = height;
    }

    public String getPath() {
        return path;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
