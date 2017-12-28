package com.sobot.evaluate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 评价界面的显示
 * Created by jinxl on 2017/6/12.
 */
public class SobotEvaluateDialog extends SobotActionSheet {

    private SubmitEvaluateListener submitEvaluateListener;

    public void setSubmitEvaluateListener(SubmitEvaluateListener submitEvaluateListener) {
        this.submitEvaluateListener = submitEvaluateListener;
    }

    private String sobot_close_now_action = "sobot_close_now_action";/*立即结束*/
    private String robotCommentTitle;/* 机器人的评价语 */
    private String currentRobotFlag;//当前的机器人编号
    private String robotName;//机器人名称
    private boolean isExitTalk;

    private Activity context;
    private int score;
    private boolean isFinish;
    private int current_model;
    private int commentType;/*commentType 评价类型 主动评价1 邀请评价0*/
    private String customName;
    private List<SatisfactionSetBase> satisFactionList;
    private SatisfactionSetBase satisfactionSetBase;
    private LinearLayout coustom_pop_layout;
    private LinearLayout sobot_robot_relative;//评价 机器人布局
    private LinearLayout sobot_custom_relative;//评价人工布局
    private LinearLayout sobot_hide_layout;//评价机器人和人工未解决时显示出来的布局
    private RadioGroup sobot_readiogroup;//
    private RadioButton sobot_btn_ok_robot;//评价  已解决
    private Button sobot_close_now;//提交评价按钮

    private EditText sobot_add_content;//评价  添加建议
    private TextView sobot_tv_evaluate_title;//评价   当前是评价机器人还是评价人工客服
    private TextView sobot_robot_center_title;//评价  机器人或人工是否解决了问题的标题
    private TextView sobot_text_other_problem;//评价  机器人或人工客服存在哪些问题的标题
    private TextView sobot_custom_center_title;//评价  对 哪个人工客服 打分  的标题
    private TextView sobot_ratingBar_title;//评价  对人工客服打分不同显示不同的内容
    private TextView sobot_evaluate_cancel;//评价  暂不评价
    private TextView sobot_tv_evaluate_title_hint;//评价  提交后结束评价
    private RatingBar sobot_ratingBar;//评价  打分

    private LinearLayout sobot_evaluate_ll_lable1;//评价  用来放前两个标签，标签最多可以有六个
    private LinearLayout sobot_evaluate_ll_lable2;//评价  用来放中间两个标签
    private LinearLayout sobot_evaluate_ll_lable3;//评价  用来放最后两个标签
    private CheckBox sobot_evaluate_cb_lable1;//六个评价标签
    private CheckBox sobot_evaluate_cb_lable2;
    private CheckBox sobot_evaluate_cb_lable3;
    private CheckBox sobot_evaluate_cb_lable4;
    private CheckBox sobot_evaluate_cb_lable5;
    private CheckBox sobot_evaluate_cb_lable6;
    private SobotEditTextLayout setl_submit_content;//评价框

    private List<CheckBox> checkBoxList = new ArrayList<>();

    //人与机械 模式进行聊天 模式
    private int client_model_robot = 301;
    //人与客服之间聊天
    private int client_model_customService = 302;

    /**
     * 创建评价的Dialog
     * @param context   上下文对象
     * @param isFinish     是否显示暂不评价按钮
     * @param robotCommentTitle     机器人评价语，用“,”隔开的String字符串
     * @param currentRobotFlag          当前评价的机器人编号
     * @param robotName         机器人名称
     * @param current_model     当前评价的是机器人还是人工客服。301是机器人  302  是人工客服
     * @param commentType       这次评价是客服邀请评价还是用户主动评价。1是主动评价  0是邀请评价
     * @param customName    人工客服名称
     * @param score     给人人工客服评价几分
     * @param isExitTalk        评价完成是否结束会话
     * @param satisFactionList      从后台获取到的对人工客服的评价标准
     */
    public SobotEvaluateDialog(Activity context, boolean isFinish, String robotCommentTitle, String currentRobotFlag, String robotName, int current_model, int commentType, String customName, int score, boolean isExitTalk, List<SatisfactionSetBase> satisFactionList) {
        super(context);
        this.context = context;
        this.score = score;
        this.isFinish = isFinish;
        this.isExitTalk = isExitTalk;
        this.robotCommentTitle = robotCommentTitle;
        this.currentRobotFlag = currentRobotFlag;
        this.robotName = robotName;
        this.current_model = current_model;
        this.commentType = commentType;
        this.customName = customName;
        this.satisFactionList = satisFactionList;
    }

    @Override
    protected String getLayoutStrName() {
        return "sobot_layout_evaluate";
    }

    @Override
    protected View getDialogContainer() {
        if (coustom_pop_layout == null) {
            coustom_pop_layout = findViewById(getResId("sobot_evaluate_container"));
        }
        return coustom_pop_layout;
    }

    @Override
    protected void initView() {
        sobot_close_now = findViewById(getResId("sobot_close_now"));
        sobot_readiogroup = findViewById(getResId("sobot_readiogroup"));
        sobot_tv_evaluate_title = findViewById(getResId("sobot_tv_evaluate_title"));
        sobot_robot_center_title = findViewById(getResId("sobot_robot_center_title"));
        sobot_text_other_problem = findViewById(getResId("sobot_text_other_problem"));
        sobot_custom_center_title = findViewById(getResId("sobot_custom_center_title"));
        sobot_ratingBar_title = findViewById(getResId("sobot_ratingBar_title"));
        sobot_tv_evaluate_title_hint = findViewById(getResId("sobot_tv_evaluate_title_hint"));
        sobot_evaluate_cancel = findViewById(getResId("sobot_evaluate_cancel"));
        if (isFinish) {
            sobot_evaluate_cancel.setVisibility(View.VISIBLE);
        } else {
            sobot_evaluate_cancel.setVisibility(View.GONE);
        }
        sobot_ratingBar = findViewById(getResId("sobot_ratingBar"));
        sobot_evaluate_ll_lable1 = findViewById(getResId("sobot_evaluate_ll_lable1"));
        sobot_evaluate_ll_lable2 = findViewById(getResId("sobot_evaluate_ll_lable2"));
        sobot_evaluate_ll_lable3 = findViewById(getResId("sobot_evaluate_ll_lable3"));
        sobot_evaluate_cb_lable1 = findViewById(getResId("sobot_evaluate_cb_lable1"));
        sobot_evaluate_cb_lable2 = findViewById(getResId("sobot_evaluate_cb_lable2"));
        sobot_evaluate_cb_lable3 = findViewById(getResId("sobot_evaluate_cb_lable3"));
        sobot_evaluate_cb_lable4 = findViewById(getResId("sobot_evaluate_cb_lable4"));
        sobot_evaluate_cb_lable5 = findViewById(getResId("sobot_evaluate_cb_lable5"));
        sobot_evaluate_cb_lable6 = findViewById(getResId("sobot_evaluate_cb_lable6"));
        checkBoxList.add(sobot_evaluate_cb_lable1);
        checkBoxList.add(sobot_evaluate_cb_lable2);
        checkBoxList.add(sobot_evaluate_cb_lable3);
        checkBoxList.add(sobot_evaluate_cb_lable4);
        checkBoxList.add(sobot_evaluate_cb_lable5);
        checkBoxList.add(sobot_evaluate_cb_lable6);
        sobot_add_content = findViewById(getResId("sobot_add_content"));
        sobot_btn_ok_robot = findViewById(getResId("sobot_btn_ok_robot"));
        sobot_btn_ok_robot.setChecked(true);
        sobot_robot_relative = findViewById(getResId("sobot_robot_relative"));
        sobot_custom_relative = findViewById(getResId("sobot_custom_relative"));
        sobot_hide_layout = findViewById(getResId("sobot_hide_layout"));
        setl_submit_content = findViewById(getResId("setl_submit_content"));
        LinearLayout sobot_negativeButton = findViewById(getResId("sobot_negativeButton"));
        sobot_negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SobotEvaluateDialog.this.dismiss();
            }
        });
        setViewGone();
        setViewListener();
    }

    @Override
    protected void initData() {
        if (current_model == client_model_customService && satisFactionList != null && satisFactionList.size() > 0) {
            sobot_ratingBar.setRating(score);

            setCustomLayoutViewVisible(score, satisFactionList);

            try {
                sobot_ratingBar_title.setText(satisfactionSetBase.getScoreExplain());
                sobot_ratingBar_title.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (satisFactionList.get(0).getIsQuestionFlag()) {
                sobot_robot_relative.setVisibility(View.VISIBLE);
            } else {
                sobot_robot_relative.setVisibility(View.GONE);
            }
        }
    }

    private void setViewListener() {
        sobot_ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int score = (int) Math.ceil(sobot_ratingBar.getRating());
                if (score > 0 && score <= 5) {
                    sobot_close_now.setSelected(true);
                    setCustomLayoutViewVisible(score, satisFactionList);
                }
            }
        });

        sobot_readiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (current_model == client_model_robot && !TextUtils.isEmpty(robotCommentTitle)) {
                    if (checkedId == getResId("sobot_btn_ok_robot")) {
                        sobot_hide_layout.setVisibility(View.GONE);
                        setl_submit_content.setVisibility(View.GONE);
                    } else if (checkedId == getResId("sobot_btn_no_robot")) {
                        sobot_hide_layout.setVisibility(View.VISIBLE);
                        setl_submit_content.setVisibility(View.VISIBLE);
                        String tmpData[] = convertStrToArray(robotCommentTitle);
                        if (tmpData != null && tmpData.length > 0) {
                            setLableViewVisible(tmpData);
                        } else {
                            sobot_hide_layout.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });

        sobot_close_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subMitEvaluate();
            }
        });

        sobot_evaluate_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent intent = new Intent();
                intent.setAction(sobot_close_now_action);/*立即结束*/
                sendLocalBroadcast(context.getApplicationContext(), intent);
            }
        });
    }

    private void setViewGone() {
        sobot_hide_layout.setVisibility(View.GONE);
        setl_submit_content.setVisibility(View.GONE);
        sobot_evaluate_ll_lable1.setVisibility(View.GONE);
        sobot_evaluate_ll_lable2.setVisibility(View.GONE);
        sobot_evaluate_ll_lable3.setVisibility(View.GONE);
        sobot_evaluate_cb_lable1.setVisibility(View.GONE);
        sobot_evaluate_cb_lable2.setVisibility(View.GONE);
        sobot_evaluate_cb_lable3.setVisibility(View.GONE);
        sobot_evaluate_cb_lable4.setVisibility(View.GONE);
        sobot_evaluate_cb_lable5.setVisibility(View.GONE);
        sobot_evaluate_cb_lable6.setVisibility(View.GONE);
        sobot_ratingBar_title.setVisibility(View.GONE);

        if (current_model == client_model_robot && !TextUtils.isEmpty(robotName)) {
            sobot_tv_evaluate_title.setText(getResString("sobot_robot_customer_service_evaluation"));
            sobot_robot_center_title.setText(String.format(getResString(context, "sobot_question"), robotName));
            sobot_text_other_problem.setText(getResString("sobot_what_are_the_problems"));
            sobot_robot_relative.setVisibility(View.VISIBLE);
            sobot_custom_relative.setVisibility(View.GONE);
        } else {
            if (isExitTalk) {
                sobot_tv_evaluate_title_hint.setText(getResString("sobot_evaluation_completed_exit"));
                sobot_tv_evaluate_title_hint.setVisibility(View.VISIBLE);
            } else {
                sobot_tv_evaluate_title_hint.setVisibility(View.GONE);
            }
            sobot_tv_evaluate_title.setText(getResString("sobot_please_evaluate_this_service"));
            sobot_robot_center_title.setText(String.format(getResString(context, "sobot_question"), customName));
            sobot_custom_center_title.setText(String.format(getResString(context, "sobot_please_evaluate"), customName));
            sobot_robot_relative.setVisibility(View.GONE);
            sobot_custom_relative.setVisibility(View.VISIBLE);
        }
    }

    //设置人工客服评价的布局显示逻辑
    private void setCustomLayoutViewVisible(int score, List<SatisfactionSetBase> satisFactionList) {
        satisfactionSetBase = getSatisFaction(score, satisFactionList);
        for (int i = 0; i < checkBoxList.size(); i++) {
            checkBoxList.get(i).setChecked(false);
        }
        if (satisfactionSetBase != null) {
            sobot_ratingBar_title.setText(satisfactionSetBase.getScoreExplain());
            sobot_ratingBar_title.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(satisfactionSetBase.getInputLanguage())) {
                if (satisfactionSetBase.getIsInputMust()) {
                    sobot_add_content.setHint(getResString("sobot_required") + satisfactionSetBase.getInputLanguage().replace("<br/>", "\n"));
                } else {
                    sobot_add_content.setHint(satisfactionSetBase.getInputLanguage().replace("<br/>", "\n"));
                }
            } else {
                sobot_add_content.setHint(getResString(context, "sobot_edittext_hint"));
            }

            if (!TextUtils.isEmpty(satisfactionSetBase.getLabelName())) {
                String tmpData[] = convertStrToArray(satisfactionSetBase.getLabelName());
                setLableViewVisible(tmpData);
            } else {
                setLableViewVisible(null);
            }

            if (score == 5) {
                sobot_hide_layout.setVisibility(View.GONE);
                setl_submit_content.setVisibility(View.GONE);
                sobot_ratingBar_title.setText(satisfactionSetBase.getScoreExplain());
                sobot_ratingBar_title.setVisibility(View.VISIBLE);
            } else {
                setl_submit_content.setVisibility(View.VISIBLE);
            }
        } else {
            sobot_ratingBar_title.setVisibility(View.GONE);
        }
    }

    private SatisfactionSetBase getSatisFaction(int score, List<SatisfactionSetBase> satisFactionList) {
        if (satisFactionList == null) {
            return null;
        }
        for (int i = 0; i < satisFactionList.size(); i++) {
            if (satisFactionList.get(i).getScore().equals(score + "")) {
                return satisFactionList.get(i);
            }
        }
        return null;
    }

    //设置评价标签的显示逻辑
    private void setLableViewVisible(String tmpData[]) {
        if (tmpData == null) {
            sobot_hide_layout.setVisibility(View.GONE);
            return;
        } else {
            sobot_hide_layout.setVisibility(View.VISIBLE);
            if (current_model == client_model_customService) {
                if (satisfactionSetBase != null) {
                    if (satisfactionSetBase.getIsTagMust()) {
                        sobot_text_other_problem.setText(getResString("sobot_what_are_the_problems") + getResString("sobot_required"));
                    } else {
                        sobot_text_other_problem.setText(getResString("sobot_what_are_the_problems"));
                    }
                } else {
                    sobot_text_other_problem.setText(getResString("sobot_what_are_the_problems"));
                }
            } else {
                sobot_text_other_problem.setText(getResString("sobot_what_are_the_problems"));
            }
        }

        switch (tmpData.length) {
            case 1:
                sobot_evaluate_cb_lable1.setText(tmpData[0]);
                sobot_evaluate_cb_lable1.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable2.setVisibility(View.INVISIBLE);
                sobot_evaluate_ll_lable1.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable2.setVisibility(View.GONE);
                sobot_evaluate_ll_lable3.setVisibility(View.GONE);
                break;
            case 2:
                sobot_evaluate_cb_lable1.setText(tmpData[0]);
                sobot_evaluate_cb_lable1.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable2.setText(tmpData[1]);
                sobot_evaluate_cb_lable2.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable1.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable2.setVisibility(View.GONE);
                sobot_evaluate_ll_lable3.setVisibility(View.GONE);
                break;
            case 3:
                sobot_evaluate_cb_lable1.setText(tmpData[0]);
                sobot_evaluate_cb_lable1.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable2.setText(tmpData[1]);
                sobot_evaluate_cb_lable2.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable1.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable3.setText(tmpData[2]);
                sobot_evaluate_cb_lable3.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable4.setVisibility(View.INVISIBLE);
                sobot_evaluate_ll_lable2.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable3.setVisibility(View.GONE);
                break;
            case 4:
                sobot_evaluate_cb_lable1.setText(tmpData[0]);
                sobot_evaluate_cb_lable1.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable2.setText(tmpData[1]);
                sobot_evaluate_cb_lable2.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable1.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable3.setText(tmpData[2]);
                sobot_evaluate_cb_lable3.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable4.setText(tmpData[3]);
                sobot_evaluate_cb_lable4.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable2.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable3.setVisibility(View.GONE);
                break;
            case 5:
                sobot_evaluate_cb_lable1.setText(tmpData[0]);
                sobot_evaluate_cb_lable1.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable2.setText(tmpData[1]);
                sobot_evaluate_cb_lable2.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable1.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable3.setText(tmpData[2]);
                sobot_evaluate_cb_lable3.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable4.setText(tmpData[3]);
                sobot_evaluate_cb_lable4.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable2.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable5.setText(tmpData[4]);
                sobot_evaluate_cb_lable5.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable6.setVisibility(View.INVISIBLE);
                sobot_evaluate_ll_lable3.setVisibility(View.VISIBLE);
                break;
            case 6:
                sobot_evaluate_cb_lable1.setText(tmpData[0]);
                sobot_evaluate_cb_lable1.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable2.setText(tmpData[1]);
                sobot_evaluate_cb_lable2.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable1.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable3.setText(tmpData[2]);
                sobot_evaluate_cb_lable3.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable4.setText(tmpData[3]);
                sobot_evaluate_cb_lable4.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable2.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable5.setText(tmpData[4]);
                sobot_evaluate_cb_lable5.setVisibility(View.VISIBLE);
                sobot_evaluate_cb_lable6.setText(tmpData[5]);
                sobot_evaluate_cb_lable6.setVisibility(View.VISIBLE);
                sobot_evaluate_ll_lable3.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private int getResovled() {
        if (current_model == client_model_robot) {
            if (sobot_btn_ok_robot.isChecked()) {
                return 0;
            } else {
                return 1;
            }
        } else if (current_model == client_model_customService) {
            if (satisfactionSetBase != null && satisfactionSetBase.getIsQuestionFlag()) {
                if (sobot_btn_ok_robot.isChecked()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
        return -1;
    }

    private SobotCommentParam getCommentParam() {
        SobotCommentParam param = new SobotCommentParam();
        String type = current_model == client_model_robot ? "0" : "1";
        int score = (int) Math.ceil(sobot_ratingBar.getRating());
        String problem = checkBoxIsChecked();
        String suggest = sobot_add_content.getText().toString();
        param.setType(type);
        param.setProblem(problem);
        param.setSuggest(suggest);
        param.setIsresolve(getResovled());
        param.setCommentType(commentType);
        if (current_model == client_model_robot && !TextUtils.isEmpty(currentRobotFlag)) {
            param.setRobotFlag(currentRobotFlag);
        } else {
            param.setScore(score + "");
        }
        return param;
    }

    //提交评价
    private void subMitEvaluate() {
        if (!checkInput()) {
            return;
        }

        SobotCommentParam commentParam = getCommentParam();
        if (submitEvaluateListener != null) {
            submitEvaluateListener.onSubmitEvaluate(commentParam);
        }
    }

    /**
     * 检查是否能提交评价
     * @return 返回值 boolean
     */
    private boolean checkInput() {
        if (current_model == client_model_customService) {
            if (satisfactionSetBase != null) {
                SobotCommentParam commentParam = getCommentParam();
                if (!"5".equals(commentParam.getScore())) {
                    if (!TextUtils.isEmpty(satisfactionSetBase.getLabelName()) && satisfactionSetBase.getIsTagMust()) {
                        if (TextUtils.isEmpty(commentParam.getProblem())) {
                            ToastUtil.showToast(context, getResString("sobot_the_label_is_required"));//标签必选
                            return false;
                        }
                    }

                    if (satisfactionSetBase.getIsInputMust()) {
                        if (TextUtils.isEmpty(commentParam.getSuggest())) {
                            ToastUtil.showToast(context, getResString("sobot_suggestions_are_required"));//建议必填
                            return false;
                        }
                    }
                }
            }
        } else if (current_model == client_model_robot) {
            return true;
        }

        return true;
    }

    // 使用String的split 方法把字符串截取为字符串数组
    private static String[] convertStrToArray(String str) {
        String[] strArray = null;
        if (!TextUtils.isEmpty(str)) {
            strArray = str.split(","); // 拆分字符为"," ,然后把结果交给数组strArray
        }
        return strArray;
    }

    //检测呗选中的标签
    private String checkBoxIsChecked() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < checkBoxList.size(); i++) {
            if (checkBoxList.get(i).isChecked()) {
                str.append(checkBoxList.get(i).getText()).append(",");
            }
        }
        return str + "";
    }

    private static String getResString(Context context, String name) {
        return context.getResources().getString(getResStringId(context, name));
    }

    private static int getResStringId(Context context, String name) {
        return ResourceUtils.getIdByName(context, "string", name);
    }

    /**
     * 发送应用内广播
     *
     * @param context   上下文对象
     * @param intent  参数
     */
    private static void sendLocalBroadcast(Context context, Intent intent) {
        String packageName = getPackageName(context);
        if (!TextUtils.isEmpty(packageName)) {
            intent.setPackage(packageName);
        }
        context.sendBroadcast(intent);
    }

    /**
     * @param context 上下文对象
     * @return 返回值类型  String
     */
    private static String getPackageName(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            // e.printStackTrace();
        }
        return null;
    }
}