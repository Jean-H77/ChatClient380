package com.client.controllers;

import com.client.Client;
import com.client.managers.ImageManager;
import com.client.model.RegistrationDetails;
import com.sanctionco.jmail.JMail;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import okhttp3.Response;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    private String imageLink = "https://i.imgur.com/61psZF5.jpg";

    private final ImageManager imageManager = Client.getInstance().getImageManager();

    private final Validator<String> nameLengthValidator = Validator.createPredicateValidator(s -> s.length() >= 5 && s.length() <= 12, "Name should be between 4 and 13 characters long.", Severity.ERROR);

    private final Validator<String> emailValidator = Validator.createPredicateValidator(JMail::isValid, "Please enter a valid email.");

    //private final Validator<String> passwordValidator = Validator.createRegexValidator("Password must include 1 number", Pattern.compile("[0-9 ]"), Severity.ERROR);

    private final Validator<LocalDate> dateValidator = Validator.createPredicateValidator(dp ->
                    dp != null && dp.isBefore(ChronoLocalDate.from(ZonedDateTime.now().minusYears(12))),"You must be at least 12 years of age to sign up.");

    private ValidationSupport validationSupport;

    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ImageView imageView;

    @FXML
    public void SignUpAction() {
        if(!validationSupport.isInvalid()) {
            String dateValue = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            RegistrationDetails registrationDetails = new RegistrationDetails(emailField.getText(), nameField.getText(), passwordField.getText(), dateValue, imageLink);
            Client.getInstance().sendRegistrationDetails(registrationDetails);
        } else {
            validationSupport.setErrorDecorationEnabled(true);
        }
    }

    public void addValidators() {
        validationSupport = new ValidationSupport();
        validationSupport.registerValidator(nameField, nameLengthValidator);
        validationSupport.registerValidator(emailField, emailValidator);
        //validationSupport.registerValidator(passwordField, passwordValidator);
        validationSupport.registerValidator(datePicker, dateValidator);
        validationSupport.setErrorDecorationEnabled(false);
    }

    @FXML
    public void ChooseImageAction() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Choose Image...");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                new FileChooser.ExtensionFilter("JPG Files", "*.jpg")
        );

        File file = fileChooser.showOpenDialog(Client.getInstance().getPrimaryStage());

        if (file != null) {
            Path path = file.toPath();
            Image image = new Image(path.toUri().toString(), 128, 128, true, true);
            imageView.setImage(image);

            try (Response response = imageManager.uploadImage(Base64.getEncoder().encodeToString(Files.readAllBytes(path)))) {
                assert response.body() != null;
                imageLink = imageManager.getImageLink(response.body().string());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(this::addValidators); //must call run later or reflection error occurs
    }
}
