package com.pad.bet.pad;

import com.pad.bet.pad.domain.Cliente;
import com.pad.bet.pad.service.MessageRepositoryService;
import com.pad.bet.pad.service.MessageService;
import com.pad.bet.pad.utils.CreateText;
import com.pad.bet.pad.utils.MessageType;
import com.pad.bet.pad.utils.Text;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Objects;

public class PadApplication extends Application {
    GridPane grid = new GridPane();
    Cliente cliente;
    String usuario;

    MessageService messageService = new MessageService();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pad Bet Control");
        this.createButtons();
        Scene scene = new Scene(grid, 800, 600);  // Ajusta el tamaño de la ventana para acomodar todos los botones
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String label, String textToCopy) {
        Button button = new Button(label);
        button.setOnAction(e -> copyToClipboard(textToCopy));
        return button;
    }

    private void setButtonSize(Button button, double width, double height) {
        button.setPrefSize(width, height);
    }

    private void copyToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Información");
//        alert.setHeaderText(null);
//        alert.setContentText("Texto copiado: " + text);
//        alert.showAndWait();
    }

    private void cargarDatos() {
        // Crear la ventana secundaria
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Cargar info");

        // Crear los labels y los TextField
        Label label1 = new Label("Titular:");
        Label label2 = new Label("CBU:");
        Label label3 = new Label("Alias:");

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();

        // Establecer el ancho preferido de los TextField
        textField1.setPrefWidth(350);
        textField2.setPrefWidth(350);
        textField3.setPrefWidth(350);

        Button cargar = new Button("GUARDAR");
        Button cancelar = new Button("CANCELAR");

        // Crear el layout para la ventana secundaria
        HBox hbox1 = new HBox(10); // Espacio entre nodos
        hbox1.getChildren().addAll(label1, textField1);
        hbox1.setPadding(new Insets(10, 0, 0, 50)); // Padding izquierdo de 50

        HBox hbox2 = new HBox(10); // Espacio entre nodos
        hbox2.getChildren().addAll(label2, textField2);
        hbox2.setPadding(new Insets(10, 0, 0, 50)); // Padding izquierdo de 50

        HBox hbox3 = new HBox(10); // Espacio entre nodos
        hbox3.getChildren().addAll(label3, textField3);
        hbox3.setPadding(new Insets(10, 0, 0, 50)); // Padding izquierdo de 50

        HBox hbox4 = new HBox(10); // Espacio entre nodos
        hbox4.getChildren().addAll(cancelar, cargar);
        hbox4.setPadding(new Insets(0, 50, 0, 0)); // Padding derecho de 50
        hbox4.setAlignment(Pos.CENTER_RIGHT); // Alineación a la derecha

        VBox layout = new VBox(10); // Espacio entre nodos
        layout.getChildren().addAll(hbox1, hbox2, hbox3, hbox4);

        // Crear la escena y establecerla en la ventana secundaria
        Scene scene = new Scene(layout, 500, 200);
        secondaryStage.setScene(scene);

        // Hacer que la ventana no sea redimensionable
        secondaryStage.setResizable(false);

        // Mostrar la ventana secundaria
        secondaryStage.show();

        cargar.setOnAction(event -> {
            if (Objects.isNull(cliente)) {
                cliente = new Cliente();
            }
            cliente.setTitular(textField1.getText());
            cliente.setAlias(textField3.getText());
            cliente.setCbu(textField2.getText());
            secondaryStage.close();
            updateButtons();
        });

        cancelar.setOnAction(event -> {
            // Cerrar la ventana secundaria
            secondaryStage.close();

            // Limpiar los campos de texto
            textField1.clear();
            textField2.clear();
            textField3.clear();
        });
    }

    private void registrarUsuario() {
        // Crear la ventana secundaria
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Registrar");
        Label label1 = new Label("Nombre del usuario:");
        TextField textField1 = new TextField();
        textField1.setPrefWidth(350);

        Button cargar = new Button("REGISTRAR");
        Button cancelar = new Button("CANCELAR");

        HBox hbox1 = new HBox(10); // Espacio entre nodos
        hbox1.getChildren().addAll(label1, textField1);
        hbox1.setPadding(new Insets(10, 0, 0, 50));

        HBox hbox4 = new HBox(10); // Espacio entre nodos
        hbox4.getChildren().addAll(cancelar, cargar);
        hbox4.setPadding(new Insets(0, 80, 10, 0));
        hbox4.setAlignment(Pos.CENTER_RIGHT);

        VBox layout = new VBox(10); // Espacio entre nodos
        layout.getChildren().addAll(hbox1, hbox4);

        // Crear la escena y establecerla en la ventana secundaria
        Scene scene = new Scene(layout, 600, 200);
        secondaryStage.setScene(scene);

        // Hacer que la ventana no sea redimensionable
        secondaryStage.setResizable(false);

        // Mostrar la ventana secundaria
        secondaryStage.show();

        cargar.setOnAction(event -> {
            if (Objects.isNull(cliente)) {
                cliente = new Cliente();
            }
            cliente.setUsuario(textField1.getText());
            if (!cliente.getUsuario().isEmpty()) {
                secondaryStage.close();
                updateButtons();
                copyToClipboard(CreateText.getRegistroUsuario(cliente));
            }
        });

        cancelar.setOnAction(event -> {
            // Cerrar la ventana secundaria
            secondaryStage.close();

            // Limpiar los campos de texto
            textField1.clear();
        });
    }

    private void updateButtons() {
        grid.getChildren().clear();
        this.createButtons();
    }

    private void createButtons() {
        grid.setPadding(new Insets(75, 75, 75, 75));
        grid.setVgap(10);
        grid.setHgap(10);

        Button buttonCargarDatos = new Button("CARGAS DATOS");
        buttonCargarDatos.setOnAction(e -> cargarDatos());
        buttonCargarDatos.setPrefSize(150, 100);

        // Ajustar la posición del nuevo botón

        // Crear y añadir los botones al panel con tamaños personalizados
        Button button1 = createButton("CBU COMPLETO", CreateText.getCbuCompleto(cliente));
        button1.setDisable(isDisabled(cliente));

        Button button2 = createButton("CBU", CreateText.getCbu(cliente));
        button2.setDisable(isDisabled(cliente));

        Button button3 = createButton("REGISTRAR USUARIO", CreateText.getRegistroUsuario(cliente));
        button3.setOnAction(e -> registrarUsuario());
        Button button4 = createButton("BIENVENIDA", messageService.getMessage(MessageType.BIENVENIDO));
        Button button5 = createButton("INFO", Text.INFO);
        Button button6 = createButton("RETIRO", Text.RETIRO);
        Button button7 = createButton("RETIRO EXITOSO", Text.RETIRO_EXITOSO);
        Button button8 = createButton("PREMIO ENVIADO", Text.PREMIO_ENVIADO);
        Button button9 = createButton("CONTRASEÑA", Text.CONTRASENA);
        Button button10 = createButton("NO INGRESO", Text.NO_INGRESO);
        Button button11 = createButton("SOPORTE", Text.SOPORTE);
        Button button12 = createButton("DEMORAS PAGOS", Text.DEMORAS_PAGOS);
        Button button13 = createButton("SOLICITO USUARIO", Text.SOLICITO_USUARIO);
        Button button14 = createButton("CBU EQUIVOCADO", Text.CBU_EQUIVOCADO);
        Button button15 = createButton("DEMORAS MP", Text.DEMORAS_MP);
        Button button16 = createButton("DEMORAS CARGAS", Text.DEMORAS_CARGAS);

        // Ajustar las posiciones de los botones para que comiencen después de los campos de texto
        GridPane.setConstraints(button1, 0, 1);
        GridPane.setConstraints(button2, 1, 1);
        GridPane.setConstraints(button3, 2, 1);
        GridPane.setConstraints(button4, 3, 1);
        GridPane.setConstraints(button5, 0, 2);
        GridPane.setConstraints(button6, 1, 2);
        GridPane.setConstraints(button7, 2, 2);
        GridPane.setConstraints(button8, 3, 2);
        GridPane.setConstraints(button9, 0, 3);
        GridPane.setConstraints(button10, 1, 3);
        GridPane.setConstraints(button11, 2, 3);
        GridPane.setConstraints(button12, 3, 3);
        GridPane.setConstraints(button13, 0, 4);
        GridPane.setConstraints(button14, 1, 4);
        GridPane.setConstraints(button15, 2, 4);
        GridPane.setConstraints(button16, 3, 4);

        // Ajustar el tamaño de los botones
        setButtonSize(button1, 200, 100);
        setButtonSize(button2, 200, 100);
        setButtonSize(button3, 200, 100);
        setButtonSize(button4, 200, 100);
        setButtonSize(button5, 200, 100);
        setButtonSize(button6, 200, 100);
        setButtonSize(button7, 200, 100);
        setButtonSize(button8, 200, 100);
        setButtonSize(button9, 200, 100);
        setButtonSize(button10, 200, 100);
        setButtonSize(button11, 200, 100);
        setButtonSize(button12, 200, 100);
        setButtonSize(button13, 200, 100);
        setButtonSize(button14, 200, 100);
        setButtonSize(button15, 200, 100);
        setButtonSize(button16, 200, 100);

        grid.getChildren()
                .addAll(buttonCargarDatos, button1, button2, button3, button4, button5, button6, button7, button8,
                        button9,
                        button10, button11, button12, button13, button14, button15, button16);
    }

    private boolean isDisabled(Cliente cliente) {
        if (Objects.isNull(cliente)) {
            return true;
        } else {
            return Objects.isNull(cliente.getTitular()) ||
                    cliente.getTitular().isEmpty() ||
                    Objects.isNull(cliente.getCbu()) ||
                    cliente.getCbu().isEmpty() ||
                    Objects.isNull(cliente.getAlias()) ||
                    cliente.getAlias().isEmpty();
        }
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}