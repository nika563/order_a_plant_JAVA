package Human;

public abstract class Human {
    protected String nameHuman;
    protected int phoneHumber;

    protected Human(String nameHuman, int phoneNumber) {
        this.nameHuman = nameHuman;
        this.phoneHumber = phoneNumber;
    }
    //get
    public String getNameHuman(){ return nameHuman; }
    public int getPhoneHumber(){ return phoneHumber; }
    //set
    public void setNameHuman(String nameHuman){ this.nameHuman = nameHuman; }
    public void  setPhoneHumber(int phoneHumber){ this.phoneHumber = phoneHumber; }
}
