package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber,  Double price, RoomType enumeration) {
        super(roomNumber, price, enumeration, true);
    }

    @Override
    public String toString(){
        return "Room "+this.roomNumber+" is a "+this.enumeration+" and costs "+this.price;
    }
}
