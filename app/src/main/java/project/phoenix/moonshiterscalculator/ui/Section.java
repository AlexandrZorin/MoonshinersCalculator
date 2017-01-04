package project.phoenix.moonshiterscalculator.ui;

public class Section {
    private String sectionName;
    private String sectionDescription;
    private int image;

    public Section(String sectionName, String sectionDescription, int image) {
        this.sectionName = sectionName;
        this.sectionDescription = sectionDescription;
        this.image = image;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionDescription() {
        return sectionDescription;
    }

    public void setSectionDescription(String sectionDescription) {
        this.sectionDescription = sectionDescription;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
