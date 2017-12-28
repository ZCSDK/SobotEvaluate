package com.sobot.evaluate.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sobot.evaluate.SobotCommentParam;
import com.sobot.evaluate.SobotEvaluateDialog;
import com.sobot.evaluate.SubmitEvaluateListener;

public class MainActivity extends AppCompatActivity {

    private SobotEvaluateDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                 * 创建评价的Dialog
                 * @param context       Context     上下文对象
                 * @param isFinish      boolean     是否显示暂不评价按钮
                 * @param robotCommentTitle     String      机器人评价语，用“,”隔开的String字符串
                 * @param currentRobotFlag      String      当前评价的机器人编号
                 * @param robotName     String      机器人名称
                 * @param current_model     int     当前评价的是机器人还是人工客服。301是机器人  302  是人工客服
                 * @param commentType       int     这次评价是客服邀请评价还是用户主动评价。1是主动评价  0是邀请评价
                 * @param customName        String      人工客服名称
                 * @param score     int     给人人工客服评价几分
                 * @param isExitTalk    boolean     评价完成是否结束会话
                 * @param satisFactionList      List<SatisfactionSetBase>       从后台获取到的对人工客服的评价标准。如：1分对应的人工评价语是什么。2分对应的人工评价语是什么...
                 * SobotEvaluateDialog(context, isFinish, robotCommentTitle, currentRobotFlag, robotName, current_model, commentType, customName, score, isExitTalk, satisFactionList)
                 */
                dialog = new SobotEvaluateDialog(MainActivity.this, true, "你好", "1", "机器人", 301, 1, "王帅", 5, true, null);
                dialog.show();
                dialog.setSubmitEvaluateListener(new SubmitEvaluateListener() {
                    @Override
                    public void onSubmitEvaluate(SobotCommentParam commentParam) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}