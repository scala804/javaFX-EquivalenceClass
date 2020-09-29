package com.autoTest.javaFXEquivalenceClass.controller;


import com.autoTest.javaFXEquivalenceClass.model.DecimalTypeBean;
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
import static com.autoTest.javaFXEquivalenceClass.util.MinTool.getNumberDecimalDigits;
import static com.autoTest.javaFXEquivalenceClass.util.MinTool.isDecimal;
import static com.autoTest.javaFXEquivalenceClass.util.PopupUtil.dataIsEmptyStage;
import static com.autoTest.javaFXEquivalenceClass.util.PopupUtil.dataIsInvalid;


public class DecimalFieldEditDialogController {

    private static final Logger logger = LoggerFactory.getLogger(DecimalFieldEditDialogController.class);
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
    private ChoiceBox ChoiceFieldEmpty;
    @FXML
    private  TextField fieldID;
    @FXML
    private  TextField  fieldName;
    @FXML
    private TextField fieldNormalData;
    @FXML
    private TextField precision;
    @FXML
    private TextField decimalBeyondLowerBoundaryValue;
    @FXML
    private TextArea beyondLowerBoundaryExpectedResults;
    @FXML
    private TextArea isNullExpectedResults;
    @FXML
    private TextField decimalBeyondUpperBoundaryValue;
    @FXML
    private TextArea beyondUpperBoundaryExpectedResults;
    @FXML
    private TextArea decimalIsFormatExpectedResults;

    private Stage dialogStage;
    private Field field;
    private DecimalTypeBean decimalTypeBean = new DecimalTypeBean();
    private List<DecimalTypeBean> decimalTypeBeans;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        ChoiceFieldEmpty.getItems().addAll("否", "是");

        fieldName.setPromptText(TEXT_INPUT_NOT_NULL);
        fieldID.setPromptText(TEXT_INPUT_NOT_REQUIRED);
        fieldNormalData.setPromptText(TEXT_INPUT_NOT_REQUIRED_SYSTEM_CREATE);

        beyondLowerBoundaryExpectedResults.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE);
        beyondUpperBoundaryExpectedResults.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE);

        decimalIsFormatExpectedResults.setPromptText(SYSTEM_DEFAULT_FAIL_VALUE);
        isNullExpectedResults.setPromptText(SYSTEM_DEFAULT_CHOICEFIELDEMPTY_withNullExpectedResult);

        decimalBeyondLowerBoundaryValue.setPromptText(TEXT_INPUT_NOT_NULL_REQUIRED);
        decimalBeyondUpperBoundaryValue.setPromptText(TEXT_INPUT_NOT_NULL_REQUIRED);
        precision.setPromptText(TEXT_INPUT_NOT_NULL);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setField(Field field) {
        this.field = field;
        uuid.setText(field.getUuid());
        ChoiceFieldEmpty.setValue(field.getChoiceFieldEmpty());
        fieldOtherType.setText(field.getFieldOtherType());
        fieldType.setText(field.getFieldType());
        fieldLength.setText(field.getFieldLength());
        orderNumber.setText(field.getOrderNumber());
    }
    public void setDecimalTypeBeans(List<DecimalTypeBean> decimalTypeBeans) {
        this.decimalTypeBeans = decimalTypeBeans;
    }


    public void setDecimalListTypeBeanEdit(int index) {
        String fieldId;
        DecimalTypeBean decimalTypeBean = decimalTypeBeans.get(index);
        fieldId = decimalTypeBean.getFieldID();
        try {
            setDecimalBeanEdit(decimalTypeBean);
        } catch (Exception e) {
            logger.error("ID为" + fieldId + "编辑错误！");
        }
    }

    public void setDecimalBeanEdit(DecimalTypeBean decimalTypeBean) {
        try {
            uuid.setText(decimalTypeBean.getUuid());
            orderNumber.setText(decimalTypeBean.getOrderNumber());
            fieldName.setText(decimalTypeBean.getFieldName());
            fieldID.setText(decimalTypeBean.getFieldID());
            fieldType.setText(decimalTypeBean.getfieldType());
            fieldNormalData.setText(decimalTypeBean.getFieldNormalData());
            fieldOtherType.setText(decimalTypeBean.getFieldOtherType());
            precision.setText(decimalTypeBean.getPrecision());
            decimalBeyondLowerBoundaryValue.setText(decimalTypeBean.getDecimalBeyondLowerBoundaryValue());
            isNullExpectedResults.setText(decimalTypeBean.getIsNullExpectedResults());
            decimalBeyondUpperBoundaryValue.setText(decimalTypeBean.getDecimalBeyondUpperBoundaryValue());
            decimalIsFormatExpectedResults.setText(decimalTypeBean.getDecimalIsFormatExpectedResults());
            beyondLowerBoundaryExpectedResults.setText(decimalTypeBean.getBeyondLowerBoundaryExpectedResults());
            fieldLength.setText(decimalTypeBean.getFieldLength());
            ChoiceFieldEmpty.setValue(decimalTypeBean.getChoiceFieldEmpty());
            beyondUpperBoundaryExpectedResults.setText(decimalTypeBean.getBeyondUpperBoundaryExpectedResults());
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
            field.setChoiceFieldEmpty(ChoiceFieldEmpty.getValue().toString());
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
    public DecimalTypeBean setDecimalBean() {
        try {
            decimalTypeBean.setUuid(uuid.getText());
            decimalTypeBean.setOrderNumber(orderNumber.getText());
            decimalTypeBean.setFieldID(fieldID.getText());
            decimalTypeBean.setFieldName(fieldName.getText());
            decimalTypeBean.setfieldType(fieldType.getText());
            decimalTypeBean.setFieldNormalData(fieldNormalData.getText());
            decimalTypeBean.setFieldOtherType(fieldOtherType.getText());
            decimalTypeBean.setPrecision(precision.getText());
            decimalTypeBean.setDecimalBeyondLowerBoundaryValue(decimalBeyondLowerBoundaryValue.getText());
            decimalTypeBean.setIsNullExpectedResults(isNullExpectedResults.getText());
            decimalTypeBean.setDecimalBeyondUpperBoundaryValue(decimalBeyondUpperBoundaryValue.getText());
            decimalTypeBean.setDecimalIsFormatExpectedResults(decimalIsFormatExpectedResults.getText());
            decimalTypeBean.setBeyondLowerBoundaryExpectedResults(beyondLowerBoundaryExpectedResults.getText());
            decimalTypeBean.setBeyondUpperBoundaryExpectedResults(beyondUpperBoundaryExpectedResults.getText());
            decimalTypeBean.setFieldLength(fieldLength.getText());
            decimalTypeBean.setChoiceFieldEmpty(ChoiceFieldEmpty.getValue().toString());
        } catch (Exception e) {
            logger.error("编辑框中的数据放入到StringTypeBean里面失败：" + e);
        }
        return decimalTypeBean;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        DecimalTypeBean decimalTypeBean;
        /*stringTypeBean.setFieldName(fieldName.getText());*/
        if (!isInputDataFormatValid()) {
            okClicked=false;
        } else {
            String uuidText = uuid.getText();
            int index = MinTool.getDecimalIndex(decimalTypeBeans, uuidText);
            decimalTypeBean = setDecimalBean();
            if (index >= 0) {
                decimalTypeBeans.remove(index);
                decimalTypeBeans.add(index, decimalTypeBean);
            } else {
                decimalTypeBeans.add(decimalTypeBean);
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
         * 精度检验，精度不能为空
         */
        if (StringUtils.isEmpty(precision.getText())&& returnBoolean == true) {
            String strMessage = POPUP_PRECISION_IS_EMPTY;
            dataIsEmptyStage(dialogStage, "精度", strMessage);
            return false;
        }else {
           String precisionStr= precision.getText();
           boolean result= precisionStr.matches("^[0-9]*[1-9][0-9]*$");
           if(result){
               int precisionInt=Integer.valueOf(precisionStr);
               if(precisionInt<=0){
                   String strMessage = POPUP_PRECISION_LESS_THAN_ZERO;
                   dataIsEmptyStage(dialogStage, "精度", strMessage);
                   return false;
               }else {
                   if(precisionInt>20){
                       String strMessage = POPUP_PRECISION_GREATER_THAN_TWENTY;
                       dataIsEmptyStage(dialogStage, "精度", strMessage);
                       return false;
                   }
               }
           }else {
               String strMessage = POPUP_PRECISION_IS_NOT_INTER;
               dataIsEmptyStage(dialogStage, "精度", strMessage);
               return false;
           }
        }
        /**
         *小数下边界值
         */
            if(!StringUtils.isEmpty(decimalBeyondLowerBoundaryValue.getText())){
              String str=decimalBeyondLowerBoundaryValue.getText();
               /*Boolean result=str.matches("[+-]?[0-9]+(\\\\.[0-9]+)?");*/
               Boolean result= isDecimal(str);
               if(result){
                   int number=getNumberDecimalDigits(str);
                   int length=str.length();
                   int precisionInt=Integer.valueOf(precision.getText());
                   if(length>100){
                       String strMessage = POPUP_DECIMAL_LOW_BOUNDARY_OVER_LENGTH_ONE_HUNDRED;
                       dataIsEmptyStage(dialogStage, "小数下边界值", strMessage);
                       return false;
                   }else {
                       if(number!=precisionInt){
                           String strMessage = POPUP_DECIMAL_LOW_BOUNDARY_PRECISION_IS_NOT_MATCH;
                           dataIsEmptyStage(dialogStage, "小数下边界值", strMessage);
                           return false;
                       }
                   }
               }else {
                   String strMessage = POPUP_DECIMAL_LOW_BOUNDARY_IS_NOT_PRECISION;
                   dataIsEmptyStage(dialogStage, "小数下边界值", strMessage);
                   return false;
               }
            }else {
                String strMessage = POPUP_DECIMAL_IS_EMPTY;
                dataIsEmptyStage(dialogStage, "小数下边界值", strMessage);
                return false;
            }


        /**
         * 小数上边界值
         */
            if(!StringUtils.isEmpty(decimalBeyondUpperBoundaryValue.getText())){
                String str=decimalBeyondUpperBoundaryValue.getText();
                Boolean result= isDecimal(str);
                if(result){
                    int number=getNumberDecimalDigits(str);
                    int precisionInt=Integer.valueOf(precision.getText());
                    if(number>100){
                        String strMessage = POPUP_DECIMAL_UPPER_BOUNDARY_OVER_LENGTH_ONE_HUNDRED;
                        dataIsEmptyStage(dialogStage, "小数上边界值", strMessage);
                        return false;
                    }else {
                        if(number!=precisionInt){
                            String strMessage = POPUP_DECIMAL_UPPER_BOUNDARY_PRECISION_IS_NOT_MATCH;
                            dataIsEmptyStage(dialogStage, "小数上边界值", strMessage);
                            return false;
                        }else {
                            String strLower=decimalBeyondLowerBoundaryValue.getText();
                            Double strLowerDouble=Double.valueOf(strLower);
                            Double strUpperDouble=Double.valueOf(str);
                            if(strUpperDouble<strLowerDouble){
                                String strMessage = POPUP_DECIMAL_LOW_BOUNDARY_GREATER_UPPER_BOUNDARY;
                                dataIsEmptyStage(dialogStage, "小数上边界值", strMessage);
                                return false;
                            }
                        }
                    }
                }else {
                    String strMessage = POPUP_DECIMAL_UPPER_BOUNDARY_VALUE_IS_NOT_DECIMAL;
                    dataIsEmptyStage(dialogStage, "小数上边界值", strMessage);
                    return false;
                }
            }else {
                String strMessage = POPUP_DECIMAL_UPPER_BOUNDARY_VALUE_IS_NOT_DECIMAL;
                dataIsEmptyStage(dialogStage, "小数上边界值", strMessage);
                return false;
            }
      /*  }*/

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
         * 非小数格式的预期结果
         */
        if (!StringUtils.isEmpty(decimalIsFormatExpectedResults.getText()) && returnBoolean == true) {
            String str=decimalIsFormatExpectedResults.getText();
            if(str.length()>100){
                String strMessage = POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED;
                dataIsInvalid(dialogStage, "非小数格式的预期结果", strMessage);
                return false;
            }
        }


        return returnBoolean;
    }
}
