/**
 *
 */
public class Passenger implements Passengerface{
    private String name;
    private  int bookingNumber;
    private int row;
    private int seatPosition;
    /**
     * 旅客类构造方法
     *  旅客姓名
     * 预定号
     *  座位排数
     * seatPosition 座位号
     */
    public Passenger(String name, int bookingNumber, int row, int seatPosition) {
        this.name = name;
        this.bookingNumber = bookingNumber;
        this.row = row;
        this.seatPosition = seatPosition;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBookingNumber() {
        return bookingNumber;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getSeatPosition() {
        return seatPosition;
    }
}

