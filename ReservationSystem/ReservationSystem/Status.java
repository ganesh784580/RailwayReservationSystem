package railwayreservationsystem;

public enum Status {

    CNF("Confirm"), RAC("RAC"), WATNG("Waiting");

    String value;

    private Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
