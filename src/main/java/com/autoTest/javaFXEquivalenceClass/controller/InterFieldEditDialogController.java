package com.autoTest.javaFXEquivalenceClass.controller;


import com.autoTest.javaFXEquivalenceClass.model.Field;
import com.autoTest.javaFXEquivalenceClass.model.InterTypeBean;
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


public class InterFieldEditDialogController {
    private static final Logger logger = LoggerFactory.getLogger(InterFieldEditDialogController.class);
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
    private TextArea beyondLowerBoundaryExpectedResults;
    @FXML
    private TextField interBeyondLowerBoundaryValue;
    @FXML
    private TextArea beyondUpperBoundaryExpectedResults;
    @FXML
    private TextField interBeyondUpperBoundaryValue;
    @FXML
    private TextArea otherNotInterExpectedResults;
    @FXML
    private TextArea withNullExpectedResult;



    private Stage dialogStage;
    private Field field;
    private InterTypeBean interTypeBean = new InterTypeBean();
    private List<InterTypeBean> interTypeBeans;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
       choiceFieldEmpty.getItems().addAll("否", "是");

        fieldName.setPromptText(TEXT_INPUT_NOT_NULL);
        fieldID.setPromptText(TEXT_INPUT_NOT_REQUIRED);
        fieldNormalData.setPromptText(TEXT_INPUT_NOT_REQUIRED_SYSTEM_CREATE);

        beyondLowerBoundaryExpectedResults.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE);
        beyondUpperBoundaryExpectedResults.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE);

        otherNotInterExpectedResults.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE);
        withNullExpectedResult.setPromptText(SYSTEM_DEFAULT_CHOICEFIELDEMPTY_withNullExpectedResult);

        interBeyondLowerBoundaryValue.setPromptText(TEXT_INPUT_NOT_NULL_REQUIRED);
        interBeyondUpperBoundaryValue.setPromptText(TEXT_INPUT_NOT_NULL_REQUIRED);


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

    public void setInterTypeBeans(List<InterTypeBean> interTypeBeans) {
        this.interTypeBeans = interTypeBeans;
    }

    public void setInterListTypeBeanEdit(int index) {
        String fieldId;
        InterTypeBean interTypeBean = interTypeBeans.get(index);
        fieldId = interTypeBean.getFieldID();
        try {
            setInterBeanEdit(interTypeBean);
        } catch (Exception e) {
            logger.error("ID为" + fieldId + "编辑错误！");
        }
    }

    /**
     * 把StringTypeBean里面的数据放入到编辑框中,用于再页面上显示
     *
     * @param interTypeBean
     * @return
     */
    public void setInterBeanEdit(InterTypeBean interTypeBean) {
        try {
            uuid.setText(interTypeBean.getUuid());
            orderNumber.setText(interTypeBean.getOrderNumber());
            fieldName.setText(interTypeBean.getFieldName());
            fieldID.setText(interTypeBean.getFieldID());
            fieldType.setText(interTypeBean.getfieldType());
            fieldNormalData.setText(interTypeBean.getFieldNormalData());
            fieldOtherType.setText(interTypeBean.getFieldOtherType());
            interBeyondLowerBoundaryValue.setText(interTypeBean.getInterBeyondLowerBoundaryValue());
            beyondLowerBoundaryExpectedResults.setText(interTypeBean.getBeyondLowerBoundaryExpectedResults());
            fieldLength.setText(interTypeBean.getFieldLength());
            choiceFieldEmpty.setValue(interTypeBean.getChoiceFieldEmpty());
            interBeyondUpperBoundaryValue.setText(interTypeBean.getInterBeyondUpperBoundaryValue());
            beyondUpperBoundaryExpectedResults.setText(interTypeBean.getBeyondUpperBoundaryExpectedResults());
            otherNotInterExpectedResults.setText(interTypeBean.getOtherNotInterExpectedResults());
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
     * 把编辑框中的数据放入到StringTypeBean里面，获取页面上的数据
     *
     * @param
     * @return
     */
    public InterTypeBean setInterBean() {
        /*StringTypeBean stringTypeBean = null;*/
        try {
            interTypeBean.setUuid(uuid.getText());
            interTypeBean.setOrderNumber(orderNumber.getText());
            interTypeBean.setFieldID(fieldID.getText());
            interTypeBean.setFieldName(fieldName.getText());
            interTypeBean.setfieldType(fieldType.getText());
            interTypeBean.setFieldNormalData(fieldNormalData.getText());
            interTypeBean.setFieldOtherType(fieldOtherType.getText());
            interTypeBean.setInterBeyondUpperBoundaryValue(interBeyondUpperBoundaryValue.getText());
            interTypeBean.setInterBeyondLowerBoundaryValue(interBeyondLowerBoundaryValue.getText());
            interTypeBean.setOtherNotInterExpectedResults(otherNotInterExpectedResults.getText());
            interTypeBean.setBeyondLowerBoundaryExpectedResults(beyondLowerBoundaryExpectedResults.getText());
            interTypeBean.setBeyondUpperBoundaryExpectedResults(beyondUpperBoundaryExpectedResults.getText());
            interTypeBean.setWithNullExpectedResult(withNullExpectedResult.getText());
            interTypeBean.setFieldLength(fieldLength.getText());
            interTypeBean.setChoiceFieldEmpty(choiceFieldEmpty.getValue().toString());
        } catch (Exception e) {
            logger.error("编辑框中的数据放入到StringTypeBean里面失败：" + e);
        }
        return interTypeBean;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        InterTypeBean interTypeBean;
        /*stringTypeBean.setFieldName(fieldName.getText());*/
        if (!isInputDataFormatValid()) {
            okClicked=false;
        } else {
            String uuidText = uuid.getText();
            int index = MinTool.getInterIndex(interTypeBeans, uuidText);
            interTypeBean = setInterBean();
            if (index >= 0) {
                interTypeBeans.remove(index);
                interTypeBeans.add(index, interTypeBean);
            } else {
                interTypeBeans.add(interTypeBean);
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

        /**
         * 整数下边界值校验
         */
            if (!StringUtils.isEmpty(interBeyondLowerBoundaryValue.getText())) {
                String strMessage = "";
                String interBeyondLowerBoundaryValueStr=interBeyondLowerBoundaryValue.getText();
                boolean result=interBeyondLowerBoundaryValueStr.matches("^[0-9]*[1-9][0-9]*$");
                if(!result){
                    strMessage=POPUP_INTER_LOW_BOUNDARY_VALUE_IS_NOT_INTER;
                    dataIsInvalid(dialogStage, "整数下边界值", strMessage);
                    return  false;
                } else {
                        if (interBeyondLowerBoundaryValueStr.length()>100){
                            strMessage=POPUP_INTER_LOW_BOUNDARY_VALUE_OVER_LENGTH;
                            dataIsInvalid(dialogStage, "整数下边界值", strMessage);
                            return  false;
                        }
                }
            }else {
                String strMessage = "";
               strMessage=POPUP_INTER_IS_EMPTY;
                dataIsInvalid(dialogStage, "整数下边界值", strMessage);
                return  false;
            }
        /*}*/


        /**
         *
         */
        /**
         * 整数下边界值校验
         */
            if (!StringUtils.isEmpty(interBeyondUpperBoundaryValue.getText())) {
                String strMessage = "";
                String interBeyondLowerBoundaryValueStr=interBeyondUpperBoundaryValue.getText();
                boolean result=interBeyondLowerBoundaryValueStr.matches("^[0-9]*[1-9][0-9]*$");
                if(!result){
                    strMessage=POPUP_INTER_UPPER_BOUNDARY_IS_NOT_INTER;
                    dataIsInvalid(dialogStage, "整数上边界值", strMessage);
                    return  false;
                } else {
                    if (interBeyondLowerBoundaryValueStr.length()>100){
                        strMessage=POPUP_INTER_UPPER_BOUNDARY_VALUE_OVER_LENGTH;
                        dataIsInvalid(dialogStage, "整数上边界值", strMessage);
                        return  false;
                    }else {
                        int minLength=Integer.valueOf(interBeyondLowerBoundaryValue.getText());
                        int bigLength=Integer.valueOf(interBeyondUpperBoundaryValue.getText());
                        if(minLength>bigLength){
                            strMessage=POPUP_INTER_LOW_BOUNDARY_GREATER_UPPER_BOUNDARY_VALUE;
                            dataIsInvalid(dialogStage, "整数上边界值", strMessage);
                            return  false;
                        }
                    }
                }
            }else {
                String strMessage = "";
                if(!StringUtils.isEmpty(interBeyondLowerBoundaryValue.getText())){
                     strMessage=POPUP_INTER_LOW_BOUNDARY_GREATER_UPPER_BOUNDARY_VALUE;
                    dataIsInvalid(dialogStage, "整数上边界值", strMessage);
                    return  false;
                }
            }
       /* }*/

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
         * 超出上边界的预期结果
         */
        if (!StringUtils.isEmpty(beyondUpperBoundaryExpectedResults.getText()) && returnBoolean == true) {
            String str=beyondUpperBoundaryExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "超出上边界的预期结果", strMessage);
                return false;
            }
        }


        /**
         * 输入其它非整数的字符串的预期结果
         */
        if (!StringUtils.isEmpty(otherNotInterExpectedResults.getText()) && returnBoolean == true) {
            String str=otherNotInterExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "输入其它非整数的字符串的预期结果", strMessage);
                return false;
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


        return returnBoolean;
    }
}
