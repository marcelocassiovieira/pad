package com.pad.bet.pad.login;

import com.pad.bet.pad.PadApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class LoginController {

    private boolean authenticated = false;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        // Crear el layout para la pantalla de inicio de sesión
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label userNameLabel = new Label("Usuario:");
        grid.add(userNameLabel, 0, 0);

        TextField userNameField = new TextField();
        grid.add(userNameField, 1, 0);

        Label passwordLabel = new Label("Contraseña:");
        grid.add(passwordLabel, 0, 1);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 2);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);

        loginButton.setOnAction(e -> {
            String username = userNameField.getText();
            String password = passwordField.getText();

            // Verificar las credenciales
            if (authenticate(username, password)) {
                authenticated = true;
                primaryStage.close();

                // Abrir la aplicación principal
                Stage mainStage = new Stage();
                new PadApplication().start(mainStage);
            } else {
                // Mostrar un mensaje de error
                Label errorLabel = new Label("Usuario o contraseña incorrectos.");
                grid.add(errorLabel, 1, 3);
            }
        });

        primaryStage.show();
    }

    private boolean authenticate(String username, String password) {

        // Aquí debes implementar la lógica de autenticación
        // Por ejemplo, comparar con valores hardcoded o consultar una base de datos
        final String VALID_USERNAME = "suertecaba";
        final String VALID_PASSWORD = "suertecaba123";
        final LocalDate EXPIRATION_DATE = LocalDate.of(2024, 6, 30);
        // Obtener la fecha actual
        LocalDate currentDate = LocalDate.now();
        // Verificar si la fecha actual es anterior a la fecha de vencimiento
        if (currentDate.isAfter(EXPIRATION_DATE)) {
            System.out.println("La contraseña ha expirado. Por favor, contacte al administrador.");
            return false;
        }
        // Verificar las credenciales
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
