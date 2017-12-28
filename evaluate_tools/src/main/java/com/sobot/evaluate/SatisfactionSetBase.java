package com.sobot.evaluate;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/12.
 */

public class SatisfactionSetBase implements Serializable {

    /**
     * configId : null
     * companyId : null
     * groupId : null
     * groupName : null
     * labelId : null
     * labelName : 服务态度差,回答不及时,没解决问题,不礼貌
     * isQuestionFlag : null
     * score : 1
     * scoreExplain : 非常不满意，特别失望
     * isTagMust : null
     * isInputMust : null
     * inputLanguage : null
     * createTime : null
     * settingMethod : null
     * updateTime : null
     * operateType : null
     */

    private String configId;//满意度配置id
    private String companyId;//企业id
    private String groupId;//技能组id
    private String groupName;//技能组名称
    private String labelId;//标签id，多个id之间逗号分隔
    private String labelName;//标签名称，多个名称之间逗号分隔
    private boolean isQuestionFlag;//人工客服是否解决问题  1解决 0未解决
    private String score;//星级 1,2,3,4,5星
    private String scoreExplain;//星级说明
    private boolean isTagMust;//标签是否必填 1是 0否
    private boolean isInputMust;//评价框是否必填 1是 0否
    private String inputLanguage;//评价框引导语
    private String createTime;//创建时间
    private String settingMethod;//设置方式：1整体设置 2区分客服技能组设置
    private String updateTime;//更新时间
    private String operateType;//操作类型 0开关设置 1满意度策略

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public boolean getIsQuestionFlag() {
        return isQuestionFlag;
    }

    public void setIsQuestionFlag(boolean isQuestionFlag) {
        this.isQuestionFlag = isQuestionFlag;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScoreExplain() {
        return scoreExplain;
    }

    public void setScoreExplain(String scoreExplain) {
        this.scoreExplain = scoreExplain;
    }

    public boolean getIsTagMust() {
        return isTagMust;
    }

    public void setIsTagMust(boolean isTagMust) {
        this.isTagMust = isTagMust;
    }

    public boolean getIsInputMust() {
        return isInputMust;
    }

    public void setIsInputMust(boolean isInputMust) {
        this.isInputMust = isInputMust;
    }

    public String getInputLanguage() {
        return inputLanguage;
    }

    public void setInputLanguage(String inputLanguage) {
        this.inputLanguage = inputLanguage;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSettingMethod() {
        return settingMethod;
    }

    public void setSettingMethod(String settingMethod) {
        this.settingMethod = settingMethod;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    @Override
    public String toString() {
        return "SatisfactionSetBase{" +
                "configId='" + configId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", labelId='" + labelId + '\'' +
                ", labelName='" + labelName + '\'' +
                ", isQuestionFlag='" + isQuestionFlag + '\'' +
                ", score='" + score + '\'' +
                ", scoreExplain='" + scoreExplain + '\'' +
                ", isTagMust='" + isTagMust + '\'' +
                ", isInputMust='" + isInputMust + '\'' +
                ", inputLanguage='" + inputLanguage + '\'' +
                ", createTime='" + createTime + '\'' +
                ", settingMethod='" + settingMethod + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", operateType='" + operateType + '\'' +
                '}';
    }
}