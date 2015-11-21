package railwayreservationsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfo {

    private String Name;
    private String Sex;
    private int Age;
    private String BerthPreference;
    private boolean IsSeniorCiti = false;
    private boolean IsFemalTeen = false;
    private boolean IsmaleTeen = false;
    private boolean IsPregnant = false;
    private int SeatNumber;
    private int CoachNum;
    private Status status;
    private static int WaitingNumberCount = 0;
    private int WaitingNumber;
    private int PNR;
    private int SeatIndex;

    public int getSeatIndex() {
        return SeatIndex;
    }

    public void setSeatIndex(int SeatIndex) {
        this.SeatIndex = SeatIndex;
    }

    public int getPNR() {
        return PNR;
    }

    public void setPNR(int PNR) {
        this.PNR = PNR;
    }

    public void setWaitingNumber() {
        ++WaitingNumberCount;
        WaitingNumber = WaitingNumberCount;
    }

    public int getWaitingNumber() {
        return WaitingNumber;
    }

    public int getCoachNum() {
        return CoachNum;
    }

    public void setCoachNum(int CoachNum) {
        this.CoachNum = CoachNum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setBerthPreference(String BerthPreference) {
        this.BerthPreference = BerthPreference;
    }

    public int getSeatNumber() {
        return SeatNumber;
    }

    public void setSeatNumber(int SeatNumber) {
        this.SeatNumber = SeatNumber;
    }

    public String getName() {
        return Name;
    }

    public String getSex() {
        return Sex;
    }

    public int getAge() {
        return Age;
    }

    public String getBerthPreference() {
        return BerthPreference;
    }

    public boolean isIsSeniorCiti() {
        return IsSeniorCiti;
    }

    public boolean isIsFemalTeen() {
        return IsFemalTeen;
    }

    public boolean isIsmaleTeen() {
        return IsmaleTeen;
    }

    public boolean isIsPregnant() {
        return IsPregnant;
    }

    UserInfo getInfo(BufferedReader Input) throws IOException {
        Pattern SexPattern = Pattern.compile("[M,m,F,f]");
        Matcher Patternmatcher;

        System.out.println("Enter your Name");
        this.Name = Input.readLine();

        System.out.println("Enter your Sex: M or F");
        this.Sex = Input.readLine();
        Patternmatcher = SexPattern.matcher(Sex);
        while (!Patternmatcher.matches()) {
            System.out.println("your Input was Invalid, valid pattern is M or m or F or f");
            this.Sex = Input.readLine();
            Patternmatcher = SexPattern.matcher(Sex);
        }
        Sex=Sex.toUpperCase();

        if (this.Sex.equals("F")) {
            System.out.println("Are you Pregenant right now, Y or N");
            if (Input.readLine().equals("Y")) {
                IsPregnant = true;
            }
        }

        System.out.println("Enter your Age");
        this.Age = Integer.parseInt(Input.readLine());
        while (Age <= 0) {
            System.out.println("Age should be greater than 1");
            Age = Integer.parseInt(Input.readLine());
        }
        System.out.println("Enter your Berth Preference");
        this.BerthPreference = Input.readLine();
        while (true) {
            if (BerthPreference.equals("LB")) {
                break;
            } else if (BerthPreference.equals("MB")) {
                break;
            } else if (BerthPreference.equals("UB")) {
                break;
            } else if (BerthPreference.equals("SLB")) {
                break;
            } else if (BerthPreference.equals("SUB")) {
                break;
            } else if (BerthPreference.equals("")) {
                BerthPreference = "UB";
                break;
            } else {
                System.out.println("Enter valid Berth Preference, Preferences are LB, MB, UB, SLB, SUB or just press enter");
                this.BerthPreference = Input.readLine();
            }

        }
        if (this.Age >= 45) {
            IsSeniorCiti = true;
        } else if (this.Age >= 18 && this.Sex.equals(
                "F")) {
            IsFemalTeen = true;
        }
        return this;
    }

}
