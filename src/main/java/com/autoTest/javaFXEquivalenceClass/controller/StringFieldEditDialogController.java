package com.autoTest.javaFXEquivalenceClass.controller;


import com.autoTest.javaFXEquivalenceClass.model.Field;
import com.autoTest.javaFXEquivalenceClass.model.StringTypeBean;
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


/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class StringFieldEditDialogController {

    private static final Logger logger = LoggerFactory.getLogger(StringFieldEditDialogController.class);
    @FXML
    private TextField orderNumber;
    @FXML
    private TextField uuid;
    @FXML
    private ChoiceBox choiceFieldEmpty;
    @FXML
    private ChoiceBox isGroupBytes;
    @FXML
    private TextField fieldOtherType;
    @FXML
    private TextField fieldType;
    @FXML
    private TextField fieldLength;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldID;
    @FXML
    private TextField fieldNormalData;
    @FXML
    private TextField minStringLenth;
    @FXML
    private TextArea beyondLowerBoundaryExpectedResults;
    @FXML
    private TextField bigStringLenth;
    @FXML
    private TextArea beyondUpperBoundaryExpectedResults;
    @FXML
    private TextArea withNullExpectedResult;
    @FXML
    private TextField notAllowedString;
    @FXML
    private TextArea withDisallowedStringExpectedResult;


    private StringTypeBean stringTypeBean = new StringTypeBean();
    private List<StringTypeBean> stringTypeBeans;
    private Stage dialogStage;
    private Field field;

    private boolean okClicked = false;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    private StringFieldEditDialogController stringFieldEditDialogController;

    public StringFieldEditDialogController() {
    }

    @FXML
    private void initialize() {
        choiceFieldEmpty.getItems().addAll("否", "是");
        isGroupBytes.getItems().addAll("否", "是");
        fieldName.setPromptText(TEXT_INPUT_NOT_NULL);
        fieldID.setPromptText(TEXT_INPUT_NOT_REQUIRED);

        fieldNormalData.setPromptText(TEXT_INPUT_NOT_REQUIRED_SYSTEM_CREATE);
        minStringLenth.setPromptText(TEXT_INPUT_NOT_NULL_REQUIRED);
        bigStringLenth.setPromptText(TEXT_INPUT_NOT_NULL_REQUIRED);

        beyondLowerBoundaryExpectedResults.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE);
        beyondUpperBoundaryExpectedResults.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE);

        withNullExpectedResult.setPromptText(SYSTEM_DEFAULT_CHOICEFIELDEMPTY_withNullExpectedResult);
        withDisallowedStringExpectedResult.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE);
        notAllowedString.setPromptText(TEXT_INPUT_NOT_REQUIRED);
        withDisallowedStringExpectedResult.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE_withDisallowedStringExpectedResult);
    }

    /**
     * 通过List<StringTypeListBean> 显示编辑数据
     *
     * @param index
     * @return
     */
    public void setStringListTypeBeanEdit(int index) {
        String fieldId;
        StringTypeBean stringTypeBean = stringTypeBeans.get(index);
        fieldId = stringTypeBean.getFieldID();
        try {
            setStringBeanEdit(stringTypeBean);
        } catch (Exception e) {
            logger.error("ID为" + fieldId + "编辑错误！");
        }
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    /**
     * Sets the person to be edited in the dialog.
     *
     * @param field
     */
    public void setField(Field field) {
        this.field = field;
        uuid.setText(field.getUuid());
        choiceFieldEmpty.setValue(field.getChoiceFieldEmpty());
        fieldOtherType.setText(field.getFieldOtherType());
        fieldType.setText(field.getFieldType());
        fieldLength.setText(field.getFieldLength());
        orderNumber.setText(field.getOrderNumber());
        isGroupBytes.setValue(field.getIsGroupBytes());
    }

    public void setStringTypeBeans(List<StringTypeBean> stringTypeBeans) {
        this.stringTypeBeans = stringTypeBeans;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
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
            field.setIsGroupBytes(isGroupBytes.getValue().toString());
        }
        return field;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    public void handleOk() {
        StringTypeBean stringTypeBean;
        if (!isInputDataFormatValid()) {
            okClicked=false;
        } else {
            String uuidText = uuid.getText();
            int index = MinTool.getStringIndex(stringTypeBeans, uuidText);
            stringTypeBean = setStringBean();
            if (index >= 0) {
                stringTypeBeans.remove(index);
                stringTypeBeans.add(index, stringTypeBean);
            } else {
                stringTypeBeans.add(stringTypeBean);
            }
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputDataFormatValid() {
        Boolean returnBoolean = true;
        /**字段名称验证**/
        if (StringUtils.isEmpty(fieldName.getText())) {
            String strMessage =FILED_NAME_NOT_EMPTY;
            dataIsEmptyStage(dialogStage, "字段名称", strMessage);
           return  false;
        }
        /**数据项要求填写验证**/
        if (!StringUtils.isEmpty(fieldID.getText())&&returnBoolean==true) {
            String str=fieldID.getText();
            boolean result = str.matches("[a-zA-Z]+");
            if(!result){
                String strMessage ="数据项如果填写，必须为英文";
                dataIsInvalid(dialogStage, "数据项", strMessage);
                return  false;
            }
        }
        /**根据字段是否为空判断，最小字符串长度的验证**/
        if(choiceFieldEmpty.getValue().equals(CHOICE_FIELD_NO)&&returnBoolean==true){
            if (!StringUtils.isEmpty(minStringLenth.getText())) {
                String minStringLenthStr=minStringLenth.getText();
                String strMessage = "";
                boolean result=minStringLenthStr.matches("^[0-9]*[1-9][0-9]*$");
                if(!result){
                    strMessage=POPUP_MINIMUM_STRING_SIZE_IS_NOT_INTER;
                    dataIsInvalid(dialogStage, "最小字符串长度", strMessage);
                    return  false;
                }else {
                    if(minStringLenthStr.length()<1){
                        strMessage=POPUP_STRING_IS_EMPT;
                        dataIsInvalid(dialogStage, "最小字符串长度", strMessage);
                        return  false;
                    }else {
                        if (minStringLenthStr.length()>100){
                            strMessage=POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                            dataIsInvalid(dialogStage, "最小字符串长度", strMessage);
                            return  false;
                        }
                    }
                }
            }else {
                String strMessage = POPUP_MINIMUM_STRING_SIZE_IS_EMPTY;
                dataIsEmptyStage(dialogStage, "最小字符串长度", strMessage);
                return  false;
            }
        }

        /**根据字段是否为空判断，最长字符串长度的验证**/
        if(choiceFieldEmpty.getValue().equals(CHOICE_FIELD_NO)&&returnBoolean==true){
            if(!StringUtils.isEmpty(bigStringLenth.getText())){
                String bigStringLenthStr=bigStringLenth.getText();
                String strMessage = "";
                boolean result=bigStringLenthStr.matches("^[0-9]*[1-9][0-9]*$");
               if(!result){
                    strMessage=POPUP_BIG_STRING_SIZE_IS_NOT_INTER;
                    dataIsInvalid(dialogStage, "最大字符串长度", strMessage);
                    return  false;
                }else {
                        if(bigStringLenthStr.length()>100){
                            strMessage=POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                            dataIsInvalid(dialogStage, "最大字符串长度", strMessage);
                            return  false;
                        }else {
                            int minLength=Integer.valueOf(minStringLenth.getText());
                            int bigLength=Integer.valueOf(bigStringLenth.getText());
                            if(minLength>bigLength){
                                strMessage=POPUP_MINIMUM_STRING_SIZE_GREATER_MAX_STRING_SIZE;
                                dataIsInvalid(dialogStage, "最大字符串长度", strMessage);
                                return  false;
                            }
                        }
                }
            }else {
                if(!StringUtils.isEmpty(minStringLenth.getText())){
                       String strMessage=POPUP_MINIMUM_STRING_SIZE_GREATER_MAX_STRING_SIZE;
                        dataIsInvalid(dialogStage, "最大字符串长度", strMessage);
                        return  false;
                }
            }
        }
        /**
         * 超出下边界的预期结果
         */
        if(!StringUtils.isEmpty(beyondLowerBoundaryExpectedResults.getText())&&returnBoolean==true){
            if(beyondLowerBoundaryExpectedResults.getText().length()>100){
                String strMessage=POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "超出下边界的预期结果", strMessage);
                return  false;
            }
        }
        /**
         * 超出上界预期结果
         */
        if(!StringUtils.isEmpty(beyondUpperBoundaryExpectedResults.getText())&&returnBoolean==true){
            if(beyondUpperBoundaryExpectedResults.getText().length()>100){
                String strMessage=POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "超出上界预期结果", strMessage);
                return  false;
            }
        }
        /**
         * 含不允许字符串的预期结果
         */
        if(!StringUtils.isEmpty(notAllowedString.getText())&&returnBoolean==true){
            if(!StringUtils.isEmpty(withDisallowedStringExpectedResult.getText())){
                if(withDisallowedStringExpectedResult.getText().length()>9999){
                    String strMessage=POPUP_STRING_LOW_BOUNDARY_EXPECTED_RESULTS_OVER_LENGTH;
                    dataIsInvalid(dialogStage, "含不允许字符串的预期结果", strMessage);
                    return  false;
                }
            }
        }

        /**
         * 为空的预期结果
         */
        if (!StringUtils.isEmpty(withNullExpectedResult.getText()) && returnBoolean == true) {
            String str=withNullExpectedResult.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "为空的预期结果", strMessage);
                return false;
            }
        }

        /**
         * 含不允许字符串的预期结果
         */
        if (!StringUtils.isEmpty(withDisallowedStringExpectedResult.getText()) && returnBoolean == true) {
            String str=withDisallowedStringExpectedResult.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "含不允许字符串的预期结果", strMessage);
                return false;
            }
        }

        /**
         * 超出下边界的预期结果
         */
        if (!StringUtils.isEmpty(beyondLowerBoundaryExpectedResults.getText()) && returnBoolean == true) {
            String str=beyondLowerBoundaryExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "超出下边界的预期结果", strMessage);
                return false;
            }
        }

        /**
         * 超出上界预期结果
         */
        if (!StringUtils.isEmpty(beyondUpperBoundaryExpectedResults.getText()) && returnBoolean == true) {
            String str=beyondUpperBoundaryExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "超出上界预期结果", strMessage);
                return false;
            }
        }

        return returnBoolean;
    }


    /**
     * 把编辑框中的数据放入到StringTypeBean里面，获取页面上的数据
     *
     * @param
     * @return
     */
    public StringTypeBean setStringBean() {
        try {
            stringTypeBean.setUuid(uuid.getText());
            stringTypeBean.setOrderNumber(orderNumber.getText());
            stringTypeBean.setFieldID(fieldID.getText());
            stringTypeBean.setFieldName(fieldName.getText());
            stringTypeBean.setfieldType(fieldType.getText());
            stringTypeBean.setFieldNormalData(fieldNormalData.getText());
            stringTypeBean.setFieldOtherType(fieldOtherType.getText());
            stringTypeBean.setMinStringLenth(minStringLenth.getText());
            stringTypeBean.setBeyondLowerBoundaryExpectedResults(beyondLowerBoundaryExpectedResults.getText());
            stringTypeBean.setBigStringLenth(bigStringLenth.getText());
            stringTypeBean.setBeyondUpperBoundaryExpectedResults(beyondUpperBoundaryExpectedResults.getText());
            stringTypeBean.setFieldLength("["+minStringLenth.getText()+","+bigStringLenth.getText()+"]");
            /*stringTypeBean.setFieldLength(fieldLength.getText());*/
            stringTypeBean.setWithNullExpectedResult(withNullExpectedResult.getText());
            stringTypeBean.setNotAllowedString(notAllowedString.getText());
            stringTypeBean.setWithDisallowedStringExpectedResult(withDisallowedStringExpectedResult.getText());
            stringTypeBean.setChoiceFieldEmpty(choiceFieldEmpty.getValue().toString());
            stringTypeBean.setIsGroupBytes(isGroupBytes.getValue().toString());

        } catch (Exception e) {
            logger.error("编辑框中的数据放入到StringTypeBean里面失败：" + e);
        }
        return stringTypeBean;
    }

    /**
     * 把StringTypeBean里面的数据放入到编辑框中,用于再页面上显示
     *
     * @param stringTypeBean
     * @return
     */
    public void setStringBeanEdit(StringTypeBean stringTypeBean) {
        try {
            uuid.setText(stringTypeBean.getUuid());
            orderNumber.setText(stringTypeBean.getOrderNumber());
            fieldName.setText(stringTypeBean.getFieldName());
            fieldID.setText(stringTypeBean.getFieldID());
            fieldType.setText(stringTypeBean.getfieldType());
            fieldNormalData.setText(stringTypeBean.getFieldNormalData());
            fieldOtherType.setText(stringTypeBean.getFieldOtherType());
            minStringLenth.setText(stringTypeBean.getMinStringLenth());
            beyondLowerBoundaryExpectedResults.setText(stringTypeBean.getBeyondLowerBoundaryExpectedResults());
            bigStringLenth.setText(stringTypeBean.getBigStringLenth());
            beyondUpperBoundaryExpectedResults.setText(stringTypeBean.getBeyondUpperBoundaryExpectedResults());
            /*fieldLength.setText(stringTypeBean.getFieldLength());*/
            fieldLength.setText("["+stringTypeBean.getMinStringLenth()+","+stringTypeBean.getBigStringLenth()+"]");
            withNullExpectedResult.setText(stringTypeBean.getWithNullExpectedResult());
            notAllowedString.setText(stringTypeBean.getNotAllowedString());
            withDisallowedStringExpectedResult.setText(stringTypeBean.getWithDisallowedStringExpectedResult());
            choiceFieldEmpty.setValue(stringTypeBean.getChoiceFieldEmpty());
            isGroupBytes.setValue(stringTypeBean.getIsGroupBytes());
        } catch (Exception e) {
            logger.error("编辑框中的数据放入到StringTypeBean里面失败：" + e);
        }
    }

}