package com.autoTest.javaFXEquivalenceClass.base;

public class ConsField {

    public static final String YES="是";
    public static final String NO="否";

    public static final String STRING_TYPE_NAME="字符串型";
    public static final String INTER_TYPE_NAME="整数型";
    public static final String DECIMAL_TYPE_NAME="小数型";
    public static final String DATE_TYPE_NAME="日期型";
    public static final String ENUMERATION_TYPE_NAME="枚举型";
    /**
     * 主界面fxMl
      */
    public static final String vieFieldBatchOverview="/view/FieldBatchOverview.fxml";
    public static final String viewRootLayout="/view/RootLayout.fxml";
    /**
     * 字符串FXML
     */
    public static final String viewStringFieldEditFxmlPath="/view/StringFieldEditDialog.fxml";
    /**
     * 整型FXML
     */
    public static final String viewInterFieldEditFxmlPath="/view/InterFieldEdit.fxml";
    /**
     * 小数FXML
     */
    public static final String viewDecimalTypeFieldEditFxmlPath="/view/DecimalTypeFieldEdit.fxml";
    /**
     * 日期型FXML
     */
    public static final String viewDateTypeFieldEditFxmlPath="/view/DateTypeFieldEdit.fxml";
    /**
     * 枚举型FXML
     */
    public static final String viewEnumerationTypeFieldEditFxmlPath="/view/EnumerationTypeFieldEdit.fxml";
    /**
     * xml保存路径
     */
    public static final String XML_FILE_PATH="src\\main\\resources\\temp\\dataXml.xml";

    /**
     * 加载xml配置
     */
    public static final String viewSystemDployFxmlPath="/view/SystemDeploy.fxml";

    /**
     * 设置excel保存路径
     */
    public static final String viewExcelDployFxmlPath="/view/ExcelFileDeploy.fxml";
    /**
     * 字符串类型
     * 1.“无效下边界的预期结果”的系统默认值为：操作失败。
     * 2.“无效上边界的预期结果”的系统默认值为：操作失败。
     * 3.当“是否允许为空”的选项为否时，为空的预期结果的系统默认值为：操作失败；否则，“为空的预期结果”的系统默认值为空。
     * 4.“包含不允许字符的预期结果”的系统默认值为：操作失败。
     * 整数类型
     * 1.“超出下边界的预期结果”的系统默认值为：操作失败。
     * 2.“超出上边界的预期结果”的系统默认值为：操作失败。
     * 3.当“是否允许为空”的选项为否时，为空的预期结果的系统默认值为：操作失败；否则，“为空的预期结果”的系统默认值为空。
     * 4.“输入其它非整数的字符串的预期结果”的系统默认值为：操作失败。
     * 小数类型
     * 1.“超出下边界的预期结果”的系统默认值为：操作失败。
     * 2.“超出上边界的预期结果”的系统默认值为：操作失败。
     * 3.当“是否允许为空”的选项为否时，为空的预期结果的系统默认值为：操作失败；否则，“为空的预期结果”的系统默认值为空。
     * 4.“非小数格式的预期结果”的系统默认值为：操作失败。
     * 日期类型
     * 1.“超出下边界的预期结果”的系统默认值为：操作失败。
     * 2.“超出上边界的预期结果”的系统默认值为：操作失败。
     * 3.当“是否允许为空”的选项为否时，日期为空的预期结果的系统默认值为：操作失败；否则，“日期为空的预期结果”的系统默认值为空。
     * 4.“非日期格式的预期结果”的系统默认值为：操作失败。
     * 枚举类型
     * 2.当“是否允许为空”的选项为否时，为空的预期结果的系统默认值为：操作失败；否则，“为空的预期结果”的系统默认值为空。
     */
     public static final String SYSTEM_DEFAULT_FAIL_VALUE="操作失败";
     public static final String SYSTEM_DEFAULT_FAIL_VALUE_withDisallowedStringExpectedResult="（当“含不允许的字符串”非空时，系统默认值为：操作失败）";

     public static final String SYSTEM_DEFAULT_CHOICEFIELDEMPTY_withNullExpectedResult="（当“是否为空”的选项为否时，系统默认值为：操作失败；否则，系统默认值为空）";
    /**
     * 枚举类型
     * 1.“成功的预期结果”的系统默认值为： 操作成功
     */
    public static final String SYSTEM_DEFAULT_SUCCESS_VALUE="操作成功";

    /**整数类型
     * 4a.6	超出上边界的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 4a.7	超出下边界的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 4a.8 如果“是否允许为空”的选项为否，为空的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 4a.9	输入其它非整数的字符串的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     *小数类型
     * 4a.11 超出下边界的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 4a.12 超出上边界的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 4a.13 如果“是否允许为空”的选项为否，为空的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 4a.14 非小数格式的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 日期类型
     * 4a.5	超出下边界的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 4a.6	超出上边界的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 4a.7	如果“是否允许为空”的选项为否，日期为空的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 4a.8	非日期格式的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 枚举类型
     * 4a.1	枚举类型的值输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 4a.2	成功的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     * 4a.3	如果“是否允许为空”的选项为否，为空的预期结果输入超过100个字符，系统提示：“输入值不能超过100个字符”。
     */
    public static final String POPUP_VALUE_OVER_LENGTH_ONE_HUNDRED="输入值不能超过100个字符";

    /**
     * 弹出框提示信息
     */
    public static final String POPUP_DATA_INVALID_TITLE="数据验证";
    public static final String POPUP_DATA_INVALID_CONTENTTEXT="请重新选择输入满足要求的数据。";

    public static final String POPUP_DATA_EMPTY_TITLE="非空验证";
    public static final String POPUP_DATA_EMPTY_CONTENTTEXT="请选择输入合法的非空数据。";

    /**
     * 字符串类型，弹出框提示信息
     */
    /**
     * 4a.1	最小字符串长度输入非整数，系统提示：“最小字符串长度必须输入整数。”
     */
    public static final String POPUP_MINIMUM_STRING_SIZE_IS_NOT_INTER="最小字符串长度必须输入整数。";

    public static final String POPUP_BIG_STRING_SIZE_IS_NOT_INTER="最大字符串长度必须输入整数。";
    /**
     *4a.2 最小字符串长度输入为空，系统提示：“最小字符串长度不能为空。”。
     */
    public static final String POPUP_MINIMUM_STRING_SIZE_IS_EMPTY="最小字符串长度不能为空。";
    /**
     * 加的
     */
    public static final String POPUP_BIG_STRING_SIZE_IS_EMPTY="最大字符串长度不能为空。";
    /**
     * 4a.3	最小字符串长度大于最大字符串长度，系统提示：“最小字符串长度不能大于最大字符串长度。”
     */
    public static final String POPUP_MINIMUM_STRING_SIZE_GREATER_MAX_STRING_SIZE="最小字符串长度不能大于最大字符串长度。";
    /**
     * 4a.4	如果“是否允许为空”的选项为否，最小字符串长度输入为0，系统提示：“不允许为空的字段，最小字符串长度输入最少为1。”
     */
    public static final String POPUP_STRING_IS_EMPT="不允许为空的字段，最小字符串长度输入最少为1";
    /**
     * 4a.5	“无效下边界的预期结果”输入超过9999个字符，系统提示：“无效下边界的预期结果输入超长。”
     */
    public static final String POPUP_STRING_LOW_BOUNDARY_EXPECTED_RESULTS_OVER_LENGTH="无效下边界的预期结果输入超长。";

    /**扩展场景
    /**
     * 2a.1	字段名称为空，系统提示：“字段名称不能为空。”。
     */
    public static final String FILED_NAME_NOT_EMPTY="字段名称不能为空。";

    /**
     * 整数类型，弹出框提示信息
     */
    /**
     *4a.1	整数下边界值输入非整数，系统提示：“整数下边界值必须输入整数”。
     */
     public static final String POPUP_INTER_LOW_BOUNDARY_VALUE_IS_NOT_INTER="整数下边界值必须输入整数";
    /**
     * 4a.2 整数下边界值输入超过100个整数字符，系统提示：“整数下边界值不能超过100个数字字符”。
     */
    public static final String POPUP_INTER_LOW_BOUNDARY_VALUE_OVER_LENGTH="整数下边界值不能超过100个数字字符";
    /**
     * 4a.3	整数上边界值输入非整数，系统提示：“整数上边界值必须输入整数”。
     */
    public static final String POPUP_INTER_UPPER_BOUNDARY_IS_NOT_INTER="整数上边界值必须输入整数";
    /**
     * 4a.4	整数上边界值输入超过100个整数字符，系统提示：“整数上边界值不能超过100个数字字符”。
     */
    public static final String POPUP_INTER_UPPER_BOUNDARY_VALUE_OVER_LENGTH="整数上边界值不能超过100个数字字符";
    /**
     * 4a.5	整数下边界值输入大于整数上边界值，系统提示：“整数下边界值不能大于整数上边界值”。
     */
    public static final String POPUP_INTER_LOW_BOUNDARY_GREATER_UPPER_BOUNDARY_VALUE="整数下边界值不能大于整数上边界值";

    /**
     * 4a.10	如果“是否允许为空”的选项为否，整数下边界长度输入为0，系统提示：“不允许为空的字段，整数下边界长度输入最少为1。”
     */
    public static final String POPUP_INTER_IS_EMPTY="不允许为空的字段，整数下边界长度输入最少为1。";

    /**
     * 小数类型，弹出框提示信息
     */

    /**
     * 4a.1	精度输入为空，系统提示：“请输入精度”。
     */
    public static final String POPUP_PRECISION_IS_EMPTY="请输入精度";
    /**
     * 4a.2	精度输入为小于等于0的整数，系统提示：“精度值必须大于0”。
     */
    public static final String POPUP_PRECISION_LESS_THAN_ZERO="精度值必须大于0";

    /**
     * 精度必须为整数
     */
    public static final String POPUP_PRECISION_IS_NOT_INTER="精度值必须为整数";
    /**
     * 4a.3	精度输入大于20，系统提示：“精度值不能超过20”。
     */
    public static final String POPUP_PRECISION_GREATER_THAN_TWENTY="精度值不能超过20";
    /**
     * 4a.4	“小数下边界值”输入的值的精度与“精度”输入值不符，系统提示：““小数下边界值”的精度应与输入的精度一致”。
     */
    public static final String POPUP_DECIMAL_LOW_BOUNDARY_PRECISION_IS_NOT_MATCH="“小数下边界值”的精度应与输入的精度一致";
    /**
     * 4a.5	“小数上边界值”输入的值的精度与“精度”输入值不符，系统提示：““小数上边界值”的精度应与输入的精度一致”。
     */
    public static final String POPUP_DECIMAL_UPPER_BOUNDARY_PRECISION_IS_NOT_MATCH="“小数上边界值”的精度应与输入的精度一致";
    /**
     * 4a.6	小数下边界值输入非小数格式字符，系统提示：“小数下边界值必须输入小数。”
     */
    public static final String POPUP_DECIMAL_LOW_BOUNDARY_IS_NOT_PRECISION="“小数下边界值必须输入小数。";
    /**
     * 4a.7	小数下边界值输入超过长度100位（含精度）的小数数字，系统提示：“小数下边界值长度不能超过100位”。
     */
    public static final String POPUP_DECIMAL_LOW_BOUNDARY_OVER_LENGTH_ONE_HUNDRED="小数下边界值长度不能超过100位";
    /**
     * 4a.8	小数上边界值输入非小数格式字符，系统提示：“小数上边界值必须输入小数。”
     */
    public static final String POPUP_DECIMAL_UPPER_BOUNDARY_VALUE_IS_NOT_DECIMAL="小数上边界值必须输入小数。";
    /**
     * 4a.9	小数上边界值输入超过长度100位（含精度）的小数数字，系统提示：“小数上边界值长度不能超过100位”。
     */
    public static final String POPUP_DECIMAL_UPPER_BOUNDARY_OVER_LENGTH_ONE_HUNDRED="小数上边界值长度不能超过100位";
    /**
     * 4a.10	小数下边界值输入大于小数上边界值，系统提示：“小数下边界值不能大于小数上边界值”。
     */
    public static final String POPUP_DECIMAL_LOW_BOUNDARY_GREATER_UPPER_BOUNDARY="小数下边界值不能大于小数上边界值";
    /**
     *4a.15	如果“是否允许为空”的选项为否，小数下边界长度输入为0，系统提示：“不允许为空的字段，小数下边界长度输入最少为1。”
     */
    public static final String POPUP_DECIMAL_IS_EMPTY="不允许为空的字段，小数下边界长度输入最少为1。";

    /**
     * 日期格式，弹出框信息
     */
    /**
     * 4a.1	日期下边界值晚于日期上边界值，系统提示：“日期下边界值不能晚于日期上边界值”。
     */
     public static final String POPUP_DATE_LOW_BOUNDARY_AFTER_UPPER_BOUNDARY="日期下边界值不能晚于日期上边界值";
    /**
     *4a.2	时间下边界值晚于时间上边界值，系统提示：“时间下边界值不能晚于时间上边界值”。
     */
    public static final String POPUP_TIME_LOW_BOUNDARY_AFTER_UPPER_BOUNDARY="时间下边界值不能晚于时间上边界值";
    /**
     * 4a.3	日期输入的是非法格式，系统提示：“请按照正确格式输入日期，日期格式为YYYY-MM-DD”。
     */
    public static final String POPUP_DATE_IS_INVALID="请按照正确格式输入日期，日期格式为YYYY-MM-DD";
    /**
     * 4a.4	时间输入的是非法格式，系统提示：“请按照正确格式输入时间，时间格式为HH-MM-SS”。
     */
    public static final String POPUP_TIME_IS_INVALID="请按照正确格式输入时间，时间格式为HH-MM-SS";
    /**
     * 4a.9	日期输入为空，系统提示：“日期不能为空”。
     */
    public static final String POPUP_DATE_IS_EMPTY="日期不能为空";

    public static final String POPUP_TIME_IS_EMPTY="时间不能为空";

    /**
     * 输入提示
     */
    public static final String TEXT_INPUT_NOT_NULL="（必填）";
    public static final String TEXT_INPUT_NOT_REQUIRED="（非必填）";

    public static final String TEXT_INPUT_NOT_REQUIRED_SYSTEM_CREATE="（如果不填，工具生成）";

    public static final String TEXT_INPUT_NOT_NULL_REQUIRED="（当“是否为空”的为否时，必填）";
    public static final String TEXT_NOT_INPUT_DEFAULT_NULL="（如果不填，默认为空）";

    /**
     * 字段类型
     */
    public static final String FIELD_TYPE_STRING="字符串型";
    public static final String FIELD_TYPE_INTER="整数型";
    public static final String FIELD_TYPE_DECIMAL="小数型";
    public static final String FIELD_TYPE_DATE="日期型";
    public static final String FIELD_TYPE_ENUMERATION="枚举型";

    public static final String[] FIELD_TYPE={FIELD_TYPE_STRING,FIELD_TYPE_INTER,FIELD_TYPE_DECIMAL,FIELD_TYPE_DATE,FIELD_TYPE_ENUMERATION};

    public static final String  UUID_DEFAULT_VALUE="uuid001";

    /**用于选择框里面的是或者否**/
    public static final String CHOICE_FIELD_YES="是";
    public static final String CHOICE_FIELD_NO="否";

    public static final String STANDARD_CHARACTERS="'%_,.*~`!@#$^&()-+=[]{}:;\"?/<>";

    public static final String EXPECT_RESULT="期望结果";


    public static final String XML_PROMPT_FILE="如果使用之前的编辑文件，请选择对应名称的xml";
    public static final String EXCEL_PROMPT_FILE_PATH="默认Excel存放路径为：D:\\EquivalenceClass\\dataXml.xlsx";

    public static final String FILED_NAME="字符串名称";
    public static final String FILED_PARAMS="参数值";
}
