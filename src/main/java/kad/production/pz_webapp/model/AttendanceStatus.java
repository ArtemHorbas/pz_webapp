package kad.production.pz_webapp.model;

import lombok.Getter;

@Getter
public enum AttendanceStatus {
    PRESENT("Obecny"),
    ABSENT("Nieobecny"),
    DELAYED("Spozniony");

    private final String displayName;

    AttendanceStatus(String displayName) {
        this.displayName = displayName;
    }

    public static AttendanceStatus getStatusByDisplayName(String displayName) {
        int index = -1;
        AttendanceStatus[] rValues = values();

        boolean roleFound = false;
        while (!roleFound && index < rValues.length - 1) {
            index++;
            if (rValues[index].getDisplayName().equals(displayName)) {
                roleFound = true;
            }
        }

        return roleFound
                ? rValues[index]
                : rValues[0];
    }
}
