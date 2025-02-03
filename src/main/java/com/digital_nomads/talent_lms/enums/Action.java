package com.digital_nomads.talent_lms.enums;

public enum Action {

    ACTIVATE("Activate"),
    DEACTIVATE("Deactivate"),
    DELETE("Delete"),
    ADD_TO_BRANCH("Add to branch"),
    REMOVE_FROM_BRANCH("Remove from branch"),
    ADD_TO_GROUP("Add to group"),
    REMOVE_FROM_GROUP("Remove from group"),
    SEND_MESSAGE("Send message");

    private final String actionName;

    Action (String actionName){
        this.actionName = actionName;
    }

    public String getActionName(){
        return actionName;
    }
}
