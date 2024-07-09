package logic;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.proj.Login;
import org.example.proj.Menu;
import org.example.proj.Start;

import static javafx.application.Application.launch;

public class Control {
    static String uv,pv;
    @FXML
    public static TextField unf;
    @FXML
    public static TextField psf;
    @FXML
    static Button loginbtn;
    @FXML
    static Button subtn;
    @FXML
    static Label errorlbl;
    @FXML
    public static TextField username,pass,passc,nickname,email,sqt,capchat,nt;
//    @FXML
//    public void initialize() {
//        unf.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                uv = newValue;
//            }
//        });
//    }
//    @FXML
//    public void initialize1() {
//        psf.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                pv = newValue;
//            }
//        });
//    }
    public static void main(String[] args) throws Exception {
        loginbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    User.login(unf.getText(),psf.getText());
                    Menu m= new Menu();
                    m.start(Login.stage);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        subtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {

                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

}
