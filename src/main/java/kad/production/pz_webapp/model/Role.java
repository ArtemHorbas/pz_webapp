package kad.production.pz_webapp.model;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("Administrator"),
    TEACHER("Prowadzacy"),
    STUDENT("Student");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public static Role getRoleByDisplayName(String displayName) {
        int index = -1;
        Role[] rValues = values();

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
