package com.digital_nomads.talent_lms.page.course;

public class CourseActionResult {
    private final String selectedCategory;
    private final String selectedBranches;

    public CourseActionResult(String selectedCategory, String selectedBranches) {
        this.selectedCategory = selectedCategory;
        this.selectedBranches = selectedBranches;
    }
    public String getSelectedCategory() {
        return selectedCategory;
    }

    public String getSelectedBranches() {
        return selectedBranches;
    }
    @Override
    public String toString() {
        return "CourseActionResult{" +
                "selectedCategory='" + selectedCategory + '\'' +
                ", selectedBranches='" + selectedBranches + '\'' +
                '}';
    }
}
