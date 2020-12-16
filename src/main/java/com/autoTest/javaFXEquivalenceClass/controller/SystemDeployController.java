package com.autoTest.javaFXEquivalenceClass.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.autoTest.javaFXEquivalenceClass.base.ConsField.*;
import static com.autoTest.javaFXEquivalenceClass.util.PopupUtil.dataIsEmptyStage;

/**
 * @author yangbihua
 */
public class SystemDeployController {
    private static final Logger logger = LoggerFactory.getLogger(SystemDeployController.class);
    @FXML
    private TextField deployXmlFilePath;
    /*@FXML
    private TextField deployExcelFilePath;*/
   /* @FXML
    private Button excel_changeDirButton;*/

    @FXML
    private Button xml_changeDirButton;

    private Stage dialogStage;

    private boolean okClicked=false;

    private  JavaFxMain javaFxMain;

    public String xmlfile;


    final FileChooser xmlFileChooser = new FileChooser();

    @FXML
    private void initialize() {
        deployXmlFilePath.setPromptText(XML_PROMPT_FILE);
        xml_changeDirButton.setOnAction(
                (final ActionEvent e) -> {
                    Scene scene = xml_changeDirButton.getScene();
                    Window Stage = (null == scene) ? null : scene.getWindow();
                    xmlFileChooser.setTitle("选择xml文件路径");
                    xmlFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml Files", "*.xml"));
                    File file = xmlFileChooser.showOpenDialog(Stage);
                    if (file != null) {
                        System.out.println(file.getAbsolutePath());
                        deployXmlFilePath.setText(file.getAbsolutePath().trim());
                    }
                });
    }


    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    @FXML
    public void handleOk() {
        xmlfile=deployXmlFilePath.getText();
           if(!"".equals(xmlfile)){
               okClicked = true;
               dialogStage.close();
           }
        else {
            dataIsEmptyStage(dialogStage, "Xml保存路径", "没有选择Xml保存路径。");
        }
    }


    public void setJavaFxMain(JavaFxMain javaFxMain) {
        this.javaFxMain = javaFxMain;
        javaFxMain.xmlFilePathJavaFxMain=xmlfile;
     /*   javaFxMain.excelFilePathJavaFxMain=excelFile;*/
        javaFxMain.getFieldsData().clear();
    }

  public Boolean systemDeployIsOkClicked() {
        return okClicked;
    }

    @FXML
    public void handleCancel(){
        dialogStage.close();
    }
}
