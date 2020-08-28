/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author leandrocotarelo
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label cartelFinal;

    @FXML
    private TextField barraUrl;

    @FXML
    private AnchorPane anchorid;

    @FXML
    private void aExa(ActionEvent action) {
        final DirectoryChooser dirchooser = new DirectoryChooser();
        Stage stage = (Stage) anchorid.getScene().getWindow();
        File file = dirchooser.showDialog(stage);
        if (file != null) {
            barraUrl.setText(file.getAbsolutePath());
        }

    }

    @FXML
    private void borra(ActionEvent action) {
        String direccion = barraUrl.getText();
        borrar(direccion);

    }

    public void borrar(String direccion) {
        File ruta = new File(direccion); // objeto ruta raiz (donde arranca el programa)
        String[] carpetaDoc = ruta.list();
        if (ruta.isDirectory()) {
            for (int i = 0; i < carpetaDoc.length; i++) {
                Arrays.sort(carpetaDoc);
                System.out.println();
                File rutaDoc = new File(ruta.getAbsolutePath() + File.separator + carpetaDoc[i] + File.separator); // objeto
                // carpeta
                // de
                // documentos
                String[] carpetaImg = rutaDoc.list();
                if (rutaDoc.isDirectory() && rutaDoc.exists()) {
                    for (int j = 2; j < carpetaImg.length; j++) {
                        Arrays.sort(carpetaImg);

                        File archivos = new File(
                                rutaDoc.getAbsolutePath() + File.separator + carpetaImg[j] + File.separator);// objeto
                        // carpeta
                        // de
                        // imagenes

                        String[] finalPath = archivos.list();
                        if (archivos.isDirectory()) {
                            for (int k = 0; k < finalPath.length; k++) {
                                Arrays.sort(finalPath);
                                String fichero = (archivos.getAbsolutePath() + File.separator + finalPath[k]);
                                File borrameArch = new File(fichero);
                                borrameArch.delete();
                                archivos.delete();
                                System.out.println("Se han eliminado los archivos " + carpetaImg[j] + "de las carpetas "
                                        + carpetaDoc[i]);
                                if (!archivos.delete()) {
                                    barraUrl.setText("");
                                    cartelFinal.setText("Archivos eliminados exitosamente");
                                    
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
