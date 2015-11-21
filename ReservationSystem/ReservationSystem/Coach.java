package railwayreservationsystem;

public class Coach {

    private int AvailNumberofUB = 2;
    private int AvailNumberofLB = 2;
    private int AvailNumberofMB = 2;
    private int AvailNumberofSLSeat = 2;
    private int AvailNumberofSUB = 1;
    private int UB[] = new int[AvailNumberofUB];
    private int MB[] = new int[AvailNumberofLB];
    private int LB[] = new int[AvailNumberofMB];
    private int SLSeat[] = new int[AvailNumberofSLSeat];
    private int SUBNumber;
    private int SeatIndex = -1;

    public int getSeatIndex() {
        return SeatIndex;
    }

    public void setSeatIndex() {
        this.SeatIndex = -1;
    }

    public int getAvailNumberofUB() {
        return AvailNumberofUB;
    }

    public void setAvailNumberofUB() {
        --this.AvailNumberofUB;
    }

    public int getAvailNumberofLB() {
        return AvailNumberofLB;
    }

    public void setAvailNumberofLB() {
        --this.AvailNumberofLB;
    }

    public int getAvailNumberofMB() {
        return AvailNumberofMB;
    }

    public void setAvailNumberofMB() {
        --this.AvailNumberofMB;
    }

    public int getAvailNumberofSLSeat() {
        return AvailNumberofSLSeat;
    }

    public void setAvailNumberofSLSeat() {
        --this.AvailNumberofSLSeat;
    }

    public int getAvailNumberofSUB() {
        return AvailNumberofSUB;
    }

    public void setAvailNumberofSUB() {
        --this.AvailNumberofSUB;
    }

    public void setUBNumber(int Berth1, int Berth2) {
        UB[0] = Berth1;
        UB[1] = Berth2;
    }

    public void setMBNumber(int Berth1, int Berth2) {
        MB[0] = Berth1;
        MB[1] = Berth2;
    }

    public void setLBNumber(int Berth1, int Berth2) {
        LB[0] = Berth1;
        LB[1] = Berth2;
    }

    public void setSLSeatNumber(int Seat1, int Seat2) {
        SLSeat[0] = Seat1;
        SLSeat[1] = Seat2;
    }

    public int getSUBNumber() {
        --AvailNumberofSUB;
        return SUBNumber;
    }

    public void setSUBNumber(int SUBNumber) {
        this.SUBNumber = SUBNumber;
    }

    public int getUB() {
        int tmp;
        if (UB[0] > 0) {
            --AvailNumberofUB;
            tmp = UB[0];
            UB[0] = -1;
            SeatIndex = 0;
            return tmp;
        } else {
            --AvailNumberofUB;
            tmp = UB[1];
            UB[1] = -1;
            SeatIndex = 1;
            return tmp;
        }
    }

    public int getMB() {
        int tmp;
        if (MB[0] > 0) {
            --AvailNumberofMB;
            tmp = MB[0];
            MB[0] = -1;
            SeatIndex = 0;
            return tmp;
        } else {
            --AvailNumberofMB;
            tmp = MB[1];
            MB[1] = -1;
            SeatIndex = 1;
            return tmp;
        }
    }

    public int getLB() {
        int tmp;
        if (LB[0] > 0) {
            --AvailNumberofLB;
            tmp = LB[0];
            LB[0] = -1;
            SeatIndex = 0;
            return tmp;
        } else {
            --AvailNumberofLB;
            tmp = LB[1];
            LB[1] = -1;
            SeatIndex = 1;
            return tmp;
        }
    }

    public int getSLSeat() {
        int tmp;
        if (SLSeat[0] > 0) {
            --AvailNumberofSLSeat;
            tmp = SLSeat[0];
            SLSeat[0] = -1;
            SeatIndex = 0;
            return tmp;
        } else {
            --AvailNumberofSLSeat;
            tmp = SLSeat[1];
            SLSeat[1] = -1;
            SeatIndex = 1;
            return tmp;
        }
    }

    void freeLBBerth(int seatIndex, int seatnumber) {
        ++AvailNumberofLB;
        LB[seatIndex] = seatnumber;
    }

    void freeMBBerth(int seatIndex, int seatnumber) {
        ++AvailNumberofMB;
        MB[seatIndex] = seatnumber;
    }

    void freeUBBerth(int seatIndex, int seatnumber) {
        ++AvailNumberofUB;
        UB[seatIndex] = seatnumber;
    }

    void freeSLBBerth(int seatIndex, int seatnumber) {
        ++AvailNumberofSLSeat;
        SLSeat[seatIndex] = seatnumber;
    }

    void freeSUBBerth(int seatnumber) {
        ++AvailNumberofSUB;
        SUBNumber = seatnumber;
    }

}
