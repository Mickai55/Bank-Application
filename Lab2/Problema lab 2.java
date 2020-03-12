import java.util.*; 
import java.util.Scanner;  

class p2 { 
      
    public static void main(String[] args)  
    { 
        Person p = new Person();
        p.setName("ionel");
        p.setAge(100);
        p.setSurName("georgel");
        p.setType("Restant");
        p.setid("1");

        Person k = new Person("mikai", "voika", 2, "da", "nu");

        System.out.println(p);

        Room r1 = new Room(1, "nice", 3);
        System.out.println(r1);

        Subject da = new Subject(r1, k, 3);
        System.out.println(da);


    } 
} 
      
class Person
{
    private String name;
    private String surname;
    private int age;
    private String idNo;
    private String type;

    public Person(){}

    public Person(String name, String surname, int age, String idNo, String type)
    {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.idNo = idNo;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public String getSurName()
    {
        return surname;
    }

    public int getAge()
    {
        return age;
    }

    public String idNo()
    {
        return idNo;
    }

    public String getType()
    {
        return type;
    }

    public void setName(String a)
    {
        name = a;
    }

    public void setSurName(String a)
    {
        surname = a;
    }

    public void setAge(int a)
    {
        age = a;
    }

    public void setid(String a)
    {
        idNo = a;
    }

    public void setType(String a)
    {
        type = a;
    }

    @Override
    public String toString() 
    {
        return "Person(" + name + " " + surname + " " + age + " " + idNo + " " + type + ");";
    }
}

class Room
{
    private int number;
    private String type;
    private int floor;

    public Room(){}

    public Room(int number, String type, int floor)
    {
        this.number = number;
        this.type = type;
        this.floor = floor;
    }

    public int getNumber()
    {
        return number;
    }

    public String getType()
    {
        return type;
    }

    public int getFloor()
    {
        return floor;
    }

    public void setNumber(int a)
    {
        number = a;
    }

    public void setType(String a)
    {
        type = a;
    }

    public void setFloor(int a)
    {
        floor = a;
    }

    @Override
    public String toString() 
    {
        return "Room(" + number + " " + type + " " + floor + ");";
    }
}

class Subject {

    private Room room;
    private Person teacher;
    private int noOfStudents;

    public Subject(Room room, Person teacher, int noOfStudents) {
        this.room = room;
        this.teacher = teacher;
        this.noOfStudents = noOfStudents;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    @Override
    public String toString()
    {
        return "Subject(" + room +" " + teacher + " " + noOfStudents + ") \n";
    }
}
