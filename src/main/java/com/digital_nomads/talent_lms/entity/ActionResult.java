package com.digital_nomads.talent_lms.entity;

public class ActionResult {

    private String selectedGroup;
    private String selectedBranch;

    // Конструктор
    public ActionResult(String selectedGroup, String selectedBranch) {
        this.selectedGroup = selectedGroup;
        this.selectedBranch = selectedBranch;
    }

    public String getSelectedGroup() {
        return selectedGroup;
    }

    public String getSelectedBranch() {
        return selectedBranch;
    }

}
