package com.autoTest.javaFXEquivalenceClass.controller;


import com.autoTest.javaFXEquivalenceClass.model.EnumerationTypeBean;
import com.autoTest.javaFXEquivalenceClass.model.Field;
import com.autoTest.javaFXEquivalenceClass.util.MinTool;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.autoTest.javaFXEquivalenceClass.base.ConsField.*;
import static com.autoTest.javaFXEquivalenceClass.util.PopupUtil.dataIsEmptyStage;
import static com.autoTest.javaFXEquivalenceClass.util.PopupUtil.dataIsInvalid;


public class EnumerationFieldEditDialogController {

    private static final Logger logger = LoggerFactory.getLogger(EnumerationFieldEditDialogController.class);
    @FXML
    private TextField uuid;
    @FXML
    private TextField fieldOtherType;
    @FXML
    private TextField fieldType;
    @FXML
    private TextField fieldLength;
    @FXML
    private TextField orderNumber;
    @FXML
    private ChoiceBox choiceFieldEmpty;
    @FXML
    private  TextField fieldID;
    @FXML
    private  TextField  fieldName;
    @FXML
    private TextField fieldNormalData;
    @FXML
    private TextArea enumerationValue;
    @FXML
    private TextArea successExpectedResults;
    @FXML
    private TextArea isNullExpectedResults;

    private Stage dialogStage;
    private Field field;
    private EnumerationTypeBean enumerationTypeBean = new EnumerationTypeBean();
    private List<EnumerationTypeBean> enumerationTypeBeans;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        choiceFieldEmpty.getItems().addAll("否", "是");

        fieldName.setPromptText(TEXT_INPUT_NOT_NULL);
        fieldID.setPromptText(TEXT_INPUT_NOT_REQUIRED);
        fieldNormalData.setPromptText(TEXT_INPUT_NOT_REQUIRED_SYSTEM_CREATE);

        isNullExpectedResults.setPromptText(SYSTEM_DEFAULT_CHOICEFIELDEMPTY_withNullExpectedResult);
        successExpectedResults.setPromptText(SYSTEM_DEFAULT_SUCCESS_VALUE);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setField(Field field) {
        this.field = field;
        uuid.setText(field.getUuid());
        choiceFieldEmpty.setValue(field.getChoiceFieldEmpty());
        fieldOtherType.setText(field.getFieldOtherType());
        fieldType.setText(field.getFieldType());
        fieldLength.setText(field.getFieldLength());
        orderNumber.setText(field.getOrderNumber());
    }
    public void setEnumerationTypeBeans(List<EnumerationTypeBean> enumerationTypeBeans) {
        this.enumerationTypeBeans = enumerationTypeBeans;
    }

    public void setEnumerationListTypeBeanEdit(int index) {
        String fieldId;
        EnumerationTypeBean enumerationTypeBean = enumerationTypeBeans.get(index);
        fieldId = enumerationTypeBean.getFieldID();
        try {
            setEnumerationBeanEdit(enumerationTypeBean);
        } catch (Exception e) {
            logger.error("ID为" + fieldId + "编辑错误！");
        }
    }
    public void setEnumerationBeanEdit(EnumerationTypeBean enumerationTypeBean) {
        try {
            uuid.setText(enumerationTypeBean.getUuid());
            orderNumber.setText(enumerationTypeBean.getOrderNumber());
            fieldName.setText(enumerationTypeBean.getFieldName());
            fieldID.setText(enumerationTypeBean.getFieldID());
            fieldType.setText(enumerationTypeBean.getfieldType());
            fieldNormalData.setText(enumerationTypeBean.getFieldNormalData());
            fieldOtherType.setText(enumerationTypeBean.getFieldOtherType());
            isNullExpectedResults.setText(enumerationTypeBean.getIsNullExpectedResults());
           /* fieldLength.setText(enumerationTypeBean.getFieldLength());*/
            fieldLength.setText("");
            choiceFieldEmpty.setValue(enumerationTypeBean.getChoiceFieldEmpty());
            enumerationValue.setText(enumerationTypeBean.getEnumerationValue());
            successExpectedResults.setText(enumerationTypeBean.getSuccessExpectedResults());
        } catch (Exception e) {
            logger.error("编辑框中的数据放入到StringTypeBean里面失败：" + e);
        }
    }

    public Boolean isOkClicked() {
        return okClicked;
    }

    public Field returnModifyField(){
        Field field=new Field();
        if(isOkClicked()){
            field.setName(fieldName.getText());
            field.setFieldType(fieldType.getText());
            field.setChoiceFieldEmpty(choiceFieldEmpty.getValue().toString());
            field.setUuid(uuid.getText());
            field.setFieldOtherType(fieldOtherType.getText());
            field.setFieldLength(fieldLength.getText());
            field.setFieldType(fieldType.getText());
            field.setFieldID(fieldID.getText());
            field.setOrderNumber(orderNumber.getText());
            field.setIsGroupBytes("");
        }
        return field;
    }

    /**
     * 把编辑框中的数据放入到dateTypeBean里面，获取页面上的数据
     *
     * @param
     * @return
     */
    public EnumerationTypeBean setEnumerationBean() {
        try {
            enumerationTypeBean.setUuid(uuid.getText());
            enumerationTypeBean.setOrderNumber(orderNumber.getText());
            enumerationTypeBean.setFieldID(fieldID.getText());
            enumerationTypeBean.setFieldName(fieldName.getText());
            enumerationTypeBean.setfieldType(fieldType.getText());
            enumerationTypeBean.setFieldNormalData(fieldNormalData.getText());
            enumerationTypeBean.setFieldOtherType(fieldOtherType.getText());
            enumerationTypeBean.setIsNullExpectedResults(isNullExpectedResults.getText());
            /*enumerationTypeBean.setFieldLength(fieldLength.getText());*/
            enumerationTypeBean.setFieldLength("");
            enumerationTypeBean.setChoiceFieldEmpty(choiceFieldEmpty.getValue().toString());
            enumerationTypeBean.setEnumerationValue(enumerationValue.getText());
            enumerationTypeBean.setSuccessExpectedResults(successExpectedResults.getText());
        } catch (Exception e) {
            logger.error("编辑框中的数据放入到StringTypeBean里面失败：" + e);
        }
        return enumerationTypeBean;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        EnumerationTypeBean enumerationTypeBean;
        /*stringTypeBean.setFieldName(fieldName.getText());*/
        if (!isInputDataFormatValid()) {
            return;
        } else {
            String uuidText = uuid.getText();
            int index = MinTool.getEnumerationIndex(enumerationTypeBeans, uuidText);
            enumerationTypeBean = setEnumerationBean();
            if (index >= 0) {
                enumerationTypeBeans.remove(index);
                enumerationTypeBeans.add(index, enumerationTypeBean);
            } else {
                enumerationTypeBeans.add(enumerationTypeBean);
            }
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }



    private boolean isInputDataFormatValid() {
        Boolean returnBoolean = true;
        if (StringUtils.isEmpty(fieldName.getText())) {
            String strMessage = FILED_NAME_NOT_EMPTY;
            dataIsEmptyStage(dialogStage, "字段名称", strMessage);
            return false;
        }
        /**数据项要求填写验证**/
        if (!StringUtils.isEmpty(fieldID.getText()) && returnBoolean == true) {
            String str = fieldID.getText();
            boolean result = str.matches("[a-zA-Z]+");
            if (!result) {
                String strMessage = "数据项如果填写，必须为英文";
                dataIsInvalid(dialogStage, "数据项", strMessage);
                return false;
            }
        }
        /**
         * 枚举类型的值
         */
        if (!StringUtils.isEmpty(enumerationValue.getText()) && returnBoolean == true) {
            String str=enumerationValue.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "枚举类型的值", strMessage);
                return false;
            }
        }
        /**
         * 成功的预期结果
         */
        if (!StringUtils.isEmpty(successExpectedResults.getText()) && returnBoolean == true) {
            String str=successExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "成功的预期结果", strMessage);
                return false;
            }
        }
        /**
         * 为空的预期结果
         */
        if (!StringUtils.isEmpty(isNullExpectedResults.getText()) && returnBoolean == true) {
            String str=isNullExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "为空的预期结果", strMessage);
                return false;
            }
        }

        /**
         * 成功的预期结果
         */
        if (!StringUtils.isEmpty(successExpectedResults.getText()) && returnBoolean == true) {
            String str=successExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "成功的预期结果", strMessage);
                return false;
            }
        }

        /**
         * 为空的预期结果
         */
        if (!StringUtils.isEmpty(isNullExpectedResults.getText()) && returnBoolean == true) {
            String str=isNullExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "为空的预期结果", strMessage);
                return false;
            }
        }

        return returnBoolean;
    }

}
