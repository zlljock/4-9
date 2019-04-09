import java.util.Arrays;
import java.util.Scanner;

public class Client {
    private String flightName = null;                   // 航班名
    private int row = 0;                                // 座位排数
    private int rowLength = 0;                          // 每排座位数
    private Flight flight = null;                       // 航班对象
    private String cmdString = null;                    // 命令串
    private Scanner scanner = new Scanner(System.in);   // 读取控制台命名


    public void commandShell() {
        System.out.println("\n\n========================================================");
        System.out.println("Command Shell V2.01");
        System.out.println("type 'exit' command to exit.");
        while (true) {
            readCommand();
            processCommand();
            }
        }

      /**
      * 读取命令
      */
        private void readCommand() {
        // 若没有创建航班，提示先创建航班
        if (flightName == null) {
            System.out.println("********************************************************");
            System.out.println("Please Create The Flight Data First!");
            System.out.println("User command: create flight_name rows rowLength<CR>");
            System.out.println("********************************************************\n\n");
            }
        System.out.print("\nCOMMAND> ");
        cmdString = scanner.nextLine();
        }



      /**
      * 分析命令
      */
    private void processCommand() {
        if (cmdString != null) {
            String[] cmds = cmdString.split(" ");
            if (cmds != null) {
                String cmd = cmds[0].toLowerCase();
                if (cmd.equals("create")) {
                    if (flightName == null) {
                        createCommand(cmds);
                        } else {
                        System.out.println("Create Error: can't handle more flights");
                        }
                    } else if (flightName != null) {
                    if (cmd.equals("reserve")) {
                        reserveCommand(cmds);
                        } else if (cmd.equals("cancel")) {
                        cancelCommand(cmds);
                        } else if (cmd.equals("list")) {
                        listCommand(cmds);
                        } else if (cmd.equals("exit")) {
                        System.out.println("Thanks. See you later!");
                        System.exit(0);
                        } else {
                        System.out.println("Bad command!");
                        cmdString = null;
                        }
                    }
                }
            }
        }


     /**
     * 查看预定列表
     * @param cmds
     */
    private void listCommand(String[] cmds) {
        if (cmds.length != 1) {
            System.out.println("\nlist command format error!");
            return;
            }
        Passenger[] passengerList = flight.getPassengerList();
        int flag = 0;
        System.out.println("Name Booking Number Row Seat Position ");
        System.out.println("----------------------------------------------------");
        if (passengerList == null || passengerList.length <= 0) {
            System.out.println("Now no seat is occupied!");
            } else {
            for (Passenger p : passengerList) {
                if (p != null) {
                    flag = 1;
                    System.out.format("%-16s%-16d%-16d%-16d\n", p.getName(), p.getBookingNumber(), p.getRow(),
                            p.getSeatPosition());
                    }
                }
            if (flag == 0) {
                System.out.println("Now no seat is occupied!");
                }
            }
        }


     /**
     * 取消航班
     * @param cmds
     */
    private void cancelCommand(String[] cmds) {
        if (cmds.length != 2) {
            System.out.println("\ncancel command format error!");
            return;
            }
        int bookingNumber = -1;
        try {
            bookingNumber = Integer.parseInt(cmds[1]);
            } catch (NumberFormatException e) {
            System.out.println();
            }
        if (bookingNumber <= 0) {
            System.out.println("\ncancel command format error!");
            return;
            }
        boolean state = flight.cancel(bookingNumber);
        if (state) {
            System.out.println("Your seat has been cancelled!");
            } else {
            System.out.println("The seat has not been reserved!");
            }
        }


    /**
     * 预定座位
     * @param cmds
     */
    private void reserveCommand(String[] cmds) {
        if (cmds.length <= 1) {
            System.out.println("Reserve command error!");
            return;
            }
        int[] bn = flight.reserve(Arrays.copyOfRange(cmds, 1, cmds.length));
        if (bn[0] != -1) {
            for (int i = 0; i < bn.length; i++) {
                System.out.println(cmds[i + 1] + "'s Booking Number is: " + bn[i]);
                }
            } else {
            System.out.println("No Such Sequential Seats Now!");
            }
        }

     /**
     * 错误状态恢复
     */
        private void errorStatus() {
        this.flight = null;
        this.flightName = null;
        this.row = 0;
        this.rowLength = 0;
        }


      /**
      * 创建航班
      * @param cmds
      */
    private void createCommand(String[] cmds) {
        if (cmds.length != 4) {
            System.out.println("Create command error!");
            } else {
            try {
                flightName = cmds[1];
                row = Integer.parseInt(cmds[2]);
                rowLength = Integer.parseInt(cmds[3]);
                if (row <= 0 || rowLength <= 0) {
                    System.out.println("Create command parameters error!");
                    errorStatus();
                    } else {
                    flight = new Flight(flightName, row, rowLength);
                    System.out.println("create Flight OK!");
                }
                } catch (Exception e) {
                e.printStackTrace();
                errorStatus();
                }
            }
        }

    public static void main(String[] args) {
        new Client().commandShell();
         }

}
