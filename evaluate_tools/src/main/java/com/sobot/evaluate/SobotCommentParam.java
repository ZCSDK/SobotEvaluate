package com.sobot.evaluate;

/**
 * Created by jinxl on 2017/6/21.
 */

public class SobotCommentParam {
    /*
    * @param type评价机器人还是人工  机器人是0 人工是1
     * @param score  人工打几分
     * @param problem   存在什么问题，标签
     * @param suggest   有什么建议
     * @param isresolve     是否解决问题
     * @param commentType       主动评价还是被动评价
     * @param robotFlag                 评价的是哪个机器人
     * */
    String type;//评价机器人还是人工  机器人是0 人工是1
    String score;//  人工打几分
    String problem;//存在什么问题，标签
    String suggest;//有什么建议
    int isresolve=0;//是否解决问题 -1 没选上或者没有配置   0 是已解决  1 未解决
    int commentType;//主动评价还是被动评价 1是主动评价  0是邀请评价
    String robotFlag;//评价的是哪个机器人

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public int getIsresolve() {
        return isresolve;
    }

    public void setIsresolve(int isresolve) {
        this.isresolve = isresolve;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public String getRobotFlag() {
        return robotFlag;
    }

    public void setRobotFlag(String robotFlag) {
        this.robotFlag = robotFlag;
    }

    @Override
    public String toString() {
        return "SobotCommentParam{" +
                "type='" + type + '\'' +
                ", score='" + score + '\'' +
                ", problem='" + problem + '\'' +
                ", suggest='" + suggest + '\'' +
                ", isresolve=" + isresolve +
                ", commentType=" + commentType +
                ", robotFlag='" + robotFlag + '\'' +
                '}';
    }
}
