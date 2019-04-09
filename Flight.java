public  class Flight implements FlightInterface {
    private String flightName;
    private int row;
    private int rowLength;
    private int[] fail = {-1};
    private Passenger[] passengerList;

    /**
    * 航班类构造方法
    * @param flightName 航班名
    * @param row 排数
    * @param rowLength 每排座位数
    * @throws Exception 抛出异常类
    */
     public Flight(String flightName, int row, int rowLength) throws Exception {
        if (flightName == null || flightName.trim().length() == 0 || row <= 0 || rowLength <= 0) {
            throw new Exception("Error");
            } else {
            this.flightName = flightName;
            this.row = row;
            this.rowLength = rowLength;
            this.passengerList = new Passenger[row * rowLength];
            for (Passenger passenger : passengerList) {
                passenger = null;
                }
            }
        }
     public int[] reserve(String[] names) {
        if (names.length > rowLength) {
            return fail;
            }
        for (int i = 0; i < row; i++) {
            // count为连续空位
            int count = 0;
             for (int j = 0; j < rowLength; j++) {
                if (passengerList[i * rowLength + j] == null) {
                    // 如果找到符合预定条件
                    if (++count >= names.length) {
                        int[] bn = new int[names.length];
                        for (int k = 0; k < names.length; k++) {
                            int num = i * rowLength + j - count + 1 + k;
                            bn[k] = num;
                            passengerList[num] = new Passenger(names[k], num + 1, i, j - count + 1 + k);
                            }
                        return bn;
                        }
                    } else {
                    count = 0;
                     }
                }
            }
         return fail;
        }

    @Override
    public int[] reserve(String names) {
        return new int[0];
    }

    public boolean cancel(int bookingNumber) {
        for (int i = 0; i < passengerList.length; i++) {
            if (passengerList[i] != null && bookingNumber == passengerList[i].getBookingNumber()) {
                passengerList[i] = null;
                return true;
                }
            }
        return false;
        }


     public Passenger[] getPassengerList() {
        return this.passengerList;
        }


}
