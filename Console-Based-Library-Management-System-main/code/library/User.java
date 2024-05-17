package library;
import java.util.List;
public abstract class User{
  private String name;
  private String phoneNum;
  private List<String> tags;
  public User(String name, String phoneNum){
    this.name = name;
    this.phoneNum = phoneNum;
  }
  public String getName(){
    return name;
  }
  public String getPhoneNum(){
    return phoneNum;
  }
  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }
  public void setTags(List<String> tags) {
    this.tags = tags;
  }
  public abstract void displayInfo();
  public Object getPassword() {
    return null;
  }
}
class Student extends User{
  public Student(String name, String phoneNum){
    super(name, phoneNum);
  }

  @Override
  public void displayInfo(){
    System.out.println("Student Name: " + getName());
    System.out.println("Phone Numeber: " + getPhoneNum());
  }
}

class Faculty extends User{
  public Faculty(String name, String phoneNum){
    super(name, phoneNum);
  }

  @Override
  public void displayInfo(){
    System.out.println("Faculty name: "+ getName());
    System.out.println("Phone Number: "+ getPhoneNum());
  }
}

// The UserFactory class implements the Factory Design Pattern.
// It provides a static method to create objects of User subclasses (Student or Faculty) based on the type parameter.
class UserFactory{
  // This static method creates and returns User objects.
  // The type of User object (Student or Faculty) depends on the type argument.
  public static User createUser(String type, String name, String phoneNum){
    switch(type.toLowerCase()){
      case "student":
        return new Student(name, phoneNum);

      case "faculty":
        return new Faculty(name, phoneNum);
        
      default:
        throw new IllegalArgumentException("Unknown user type.");
    }
  }
}
