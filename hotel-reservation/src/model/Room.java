package model;

import java.util.Objects;

public class Room implements IRoom{

    String roomNumber;
    Double price;
    RoomType enumeration;
    Boolean isFree;

    public Room(String roomNumber, Double price, RoomType enumeration, Boolean isFree){
        this.enumeration = enumeration;
        this.isFree = isFree;
        this.price = price;
        this.roomNumber = roomNumber;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }

    @Override
    public boolean isFree() {
        return this.isFree;
    }

    public void setIsFree(Boolean state){
        this.isFree = state;
    }

    @Override
    public String toString() {
        return "Room number " + roomNumber +" " +this.enumeration+
                " Room Price: $" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber) && Objects.equals(price, room.price) && enumeration == room.enumeration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, enumeration);
    }
}
