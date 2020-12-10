package com.autoTest.javaFXEquivalenceClass.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.autoTest.javaFXEquivalenceClass.base.ConsField.EXCEL_PROMPT_FILE_PATH;
import static com.autoTest.javaFXEquivalenceClass.base.ConsField.XML_PROMPT_FILE;
import static com.autoTest.javaFXEquivalenceClass.util.PopupUtil.dataIsEmptyStage;

/**
 * @author yangbihua
 */
public class ExcelFileDeployController {
    private static final Logger logger = LoggerFactory.getLogger(ExcelFileDeployController.class);
    @FXML
    private TextField deployXmlFilePath;
    @FXML
    private TextField deployExcelFilePath;
    @FXML
    private Button excel_changeDirButton;


    private Stage dialogStage;

    private boolean okClicked=false;

    private  JavaFxMain javaFxMain;

    public String xmlfile;
    public String excelFile;

    final FileChooser excelFileChooser = new FileChooser();

    @FXML
    private void initialize() {
        deployXmlFilePath.setPromptText(XML_PROMPT_FILE);
        deployExcelFilePath.setPromptText(EXCEL_PROMPT_FILE_PATH);
        Stage Stage=new Stage();
        excel_changeDirButton.setOnAction(
                (final ActionEvent e) -> {
                    excelFileChooser.setTitle("选择Excel文件路径");
                    excelFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xlsx Files", "*.xlsx"));
                    File file = excelFileChooser.showOpenDialog(Stage);
                    if (file != null) {
                        System.out.println(file.getAbsolutePath());
                        deployExcelFilePath.setText(file.getAbsolutePath().trim());
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
        String excelFileName="";
        String filePath="";
        xmlfile=deployXmlFilePath.getText();
        excelFile=deployExcelFilePath.getText();
        if(!"".equals(excelFile)){
           int indexFileName=excelFile.lastIndexOf("\\");
           int indexSullfix=excelFile.lastIndexOf(".");
           if(indexSullfix>indexFileName){
                excelFileName=excelFile.substring(indexFileName+1,indexSullfix);
               filePath=excelFile.substring(0,indexFileName+1);
               System.out.println(filePath);
           }
           if("".equals(xmlfile)){
               xmlfile=filePath+excelFileName+".xml";
           }
        }else {
            dataIsEmptyStage(dialogStage, "Excel保存路径", "没有选择Excel保存路径。");
        }
        okClicked = true;
        dialogStage.close();
    }


    public void setJavaFxMain(JavaFxMain javaFxMain) {
        this.javaFxMain = javaFxMain;
        javaFxMain.xmlFilePathJavaFxMain=xmlfile;
        javaFxMain.excelFilePathJavaFxMain=excelFile;
        /*javaFxMain.getFieldsData().clear();*/
    }

  public Boolean systemDeployIsOkClicked() {
        return okClicked;
    }

    @FXML
    public void handleCancel(){
        dialogStage.close();
    }
}
