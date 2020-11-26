package com.autoTest.javaFXEquivalenceClass.controller;


import com.autoTest.javaFXEquivalenceClass.model.Field;
import com.autoTest.javaFXEquivalenceClass.model.StringTypeBean;
import com.autoTest.javaFXEquivalenceClass.util.MinTool;
import com.autoTest.javaFXEquivalenceClass.util.PopupUtil;
import com.autoTest.javaFXEquivalenceClass.util.RandomUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.autoTest.javaFXEquivalenceClass.base.ConsField.*;
import static com.autoTest.javaFXEquivalenceClass.util.PopupUtil.*;


/**
 * @author yangbihua
 */
public class FieldOverviewController {
    @FXML
    private TableView<Field> fieldTable;
    @FXML
    private TableColumn<Field, String> orderNumberColumn;
    @FXML
    private TableColumn<Field, String> fieldIDColumn;
    @FXML
    private TableColumn<Field, String> nameColumn;
    @FXML
    private TableColumn<Field, String> fieldTypeColumn;
    @FXML
    private TableColumn<Field, String> fielLengthColumn;
    @FXML
    private TableColumn<Field, String> fielEmptyColumn;
    @FXML
    private TableColumn<Field, String> fieldOtherTypeColumn;
    @FXML
    private TextField fieldAddNumber;
    @FXML
    private TextField fieldLength;
    @FXML
    private  ChoiceBox ChoiceBoxfieldType;
    @FXML
    private  ChoiceBox ChoiceFieldEmpty;
    @FXML
    private  ChoiceBox ChoiceOtherType;


    private  JavaFxMain javaFxMain;

    public List<StringTypeBean> stringTypeBeans=new ArrayList<StringTypeBean>();

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public FieldOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<Field, String>("orderNumber"));
        fieldIDColumn.setCellValueFactory(new PropertyValueFactory<Field, String>("fieldID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Field, String>("name"));
        fieldTypeColumn.setCellValueFactory(new PropertyValueFactory<Field, String>("fieldType"));
        fielLengthColumn.setCellValueFactory(new PropertyValueFactory<Field, String>("fieldLength"));
        fielEmptyColumn.setCellValueFactory(new PropertyValueFactory<Field, String>("choiceFieldEmpty"));
        fieldOtherTypeColumn.setCellValueFactory(new PropertyValueFactory<Field, String>("fieldOtherType"));

        ChoiceBoxfieldType.getItems().addAll("字符串型", "整数型", "小数型","日期型", "枚举型");
        ChoiceBoxfieldType.getSelectionModel().selectFirst();

        ChoiceFieldEmpty.getItems().addAll("否", "是");
        ChoiceFieldEmpty.getSelectionModel().selectFirst();

        ChoiceOtherType.getItems().addAll("否", "是");
        ChoiceOtherType.getSelectionModel().selectFirst();
        /*fieldNumbers=0;*/
        fieldAddNumber.setPromptText(TEXT_INPUT_NOT_NULL);
        fieldTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! fieldTable.getSelectionModel().isEmpty()) ) {
                /*Field emailInfo = fieldTable.getSelectionModel().getSelectedItem();*/
                handleEditField();
            }
        });
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param javaFxMain
     */
    public void setJavaFxMain(JavaFxMain javaFxMain) {
        this.javaFxMain = javaFxMain;
        //表格设置为可编辑
        /*fieldTable.setEditable(true);*/
        //给需要编辑的列设置属性
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fieldTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fielLengthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fielEmptyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fieldOtherTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        // Add observable list data to the table
        fieldTable.setItems(javaFxMain.getFieldsData());
        fieldTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteField() {
        int selectedIndex = fieldTable.getSelectionModel().getSelectedIndex();
        String uuid;
        if (selectedIndex >= 0) {
            uuid=fieldTable.getItems().get(selectedIndex).getUuid();
           Boolean booleanResult=alertConfirDialog(javaFxMain,"请确认是否删除","");
            boolean deletBoolean=false;
           if(booleanResult){
               try {
                   String fieldType=fieldTable.getSelectionModel().getSelectedItem().getFieldType();
                   deletBoolean=javaFxMain.deleteFieldListBeans(fieldType,uuid);
                   fieldTable.getItems().remove(selectedIndex);
               }catch (Exception e){
                   String title="删除失败";
                   String headerText="删除失败！";
                   String contentText="删除发生异常."+e;
                   PopupUtil.WarningHints(javaFxMain,title,headerText,contentText);
                   return;
               }
           }else {
               booleanResult=false;
           }
            if(booleanResult&&deletBoolean){
                String title="删除成功";
                String headerText="删除成功！";
                String contentText="删除成功.";
                PopupUtil.WarningHints(javaFxMain,title,headerText,contentText);
            }else {
                if(booleanResult&&!deletBoolean){
                    String title="删除失败";
                    String headerText="删除失败！";
                    String contentText="删除失败.";
                    PopupUtil.WarningHints(javaFxMain,title,headerText,contentText);
                }
            }
        } else {
            String title="没有选择删除内容";
            String headerText="没有选择删除内容";
            String contentText="没有选择删除内容.";
            PopupUtil.WarningHints(javaFxMain,title,headerText,contentText);
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewField() {
        String strTitle;
        /**
         * 处理长度或者边界输入是否合法，如果合法返回结果fileldLength
         */
        String StrFieldLength=fieldLength.getText();
        int fileldLength=0;
        strTitle="长度或者边界";
        fileldLength=handleIntText(javaFxMain, StrFieldLength,strTitle,fileldLength);
        if(fileldLength==0){
            return;
        }
        /**
         * 处理个数是否合法，如果合法返回结果fieldAddNumberInt
         */
        String strfieldAddNumber=fieldAddNumber.getText();
        int fieldAddNumberInt=0;
        strTitle="个数";
        fieldAddNumberInt=handleIntText(javaFxMain, strfieldAddNumber,strTitle,fieldAddNumberInt);
        if(fieldAddNumberInt==0){
            return;
        }else {
            try {
                String choiceBoxfieldType=ChoiceBoxfieldType.getValue().toString();
                String choiceOtherType=ChoiceOtherType.getValue().toString();
                for(int i=0;i<fieldAddNumberInt;i++){
                   /* fieldNumbers++;*/
                    Field tempField = new Field();
                    int[] in = { 1, 2, 3, 4, 5, 6, 7 ,8,9};
                  int randomIntOrderNumber= RandomUtil.getNotSimple(in,5);
                    UUID uuid = UUID.randomUUID();
                    tempField.setUuid(uuid.toString());
                    tempField.setOrderNumber(String.valueOf(randomIntOrderNumber));
                    /*int randomIntFieldID=RandomUtil.getNotSimple(in,3);*/
                    tempField.setFieldID("testID");
                    tempField.setFieldLength(Integer.toString(fileldLength));
                    tempField.setFieldOtherType(choiceOtherType);
                    tempField.setFieldType(choiceBoxfieldType);
                    javaFxMain.getFieldsData().add(tempField);
                }
            }catch (Exception e){
                String title="增加字段失败！";
                errorPopup(javaFxMain,title,e);
            }
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditField() {
        Boolean okClicked=false;
        try{
            Field selectedField = fieldTable.getSelectionModel().getSelectedItem();
            int selectedIndex = fieldTable.getSelectionModel().getSelectedIndex();
            if (selectedField != null&&selectedIndex>=0) {
                Field field = javaFxMain.fieldEditDialog(selectedField);
                if(!field.getUuid().equals(UUID_DEFAULT_VALUE)){
                    javaFxMain.getFieldsData().set(selectedIndex,field);
                    okClicked=true;
                }
            }
        }
        catch (Exception e){
            String title="编辑字段内容";
            String headerText=SYSTEM_DEFAULT_FAIL_VALUE;
            String contentText="编辑字段内容失败";
            PopupUtil.WarningHints(javaFxMain,title,headerText,contentText);
        }
        if (okClicked) {
                String title="编辑字段内容";
                String headerText=SYSTEM_DEFAULT_SUCCESS_VALUE;
                String contentText="编辑字段内容成功";
                PopupUtil.WarningHints(javaFxMain,title,headerText,contentText);
            }
    }


    /**
     * 生成样本数据
     */
    @FXML
    private void handleGeneratedSamples(){
        boolean generatedSamples=javaFxMain.generatedSamples();
        if(generatedSamples){
            String title="生成数据样本";
            String headerText="生成数据提示";
            String contentText="生成数据样本成功.";
            PopupUtil.WarningHints(javaFxMain,title,headerText,contentText);
        }else {
            String title="生成数据失败！";
            String headerText="成数据提示";
            String contentText="生成数据样本失败.";
            PopupUtil.WarningHints(javaFxMain,title,headerText,contentText);
        }
    }

    public Integer handleIntText(JavaFxMain javaFxMain, String StrText,String strTitle,int fileldLength){
        if(!StringUtils.isEmpty(StrText)){
            if(MinTool.canParseInt(StrText)){
                fileldLength=Integer.valueOf(StrText);
            }else {
                dataIsInvalid(javaFxMain,strTitle);
            }
        }else {
            dataIsEmpty(javaFxMain,strTitle);
        }
        return fileldLength;
    }



}
