package creatingclass;

public class Dog {
    private String dogType;
    private String dogName;
    private String dogColor;
    private int dogAge;

    public Dog(String dogType, String dogName, String dogColor, int dogAge) {
        this.dogType = dogType;
        this.dogName = dogName;
        this.dogColor = dogColor;
        this.dogAge = dogAge;
    }

    public void setDogType(String dogType) {
        this.dogType = dogType;
    }

    public String getDogType() {
        return dogType;
    }

    public String getDogName() {
        return dogName;
    }

    public String getDogColor() {
        return dogColor;
    }

    public int getDogAge() {
        return dogAge;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public void setDogColor(String dogColor) {
        this.dogColor = dogColor;
    }

    public void setDogAge(int dogAge) {
        this.dogAge = dogAge;
    }

    @Override
    public String toString(){
        return "Dog Type" + dogType + " Dog name "+dogName + " Dog Color " + dogColor+ " Dog age "+dogAge;
    }
}
