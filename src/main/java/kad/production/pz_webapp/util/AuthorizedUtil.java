package kad.production.pz_webapp.util;

import jakarta.servlet.http.HttpSession;
import kad.production.pz_webapp.model.Role;
import kad.production.pz_webapp.model.User;

public class AuthorizedUtil {
    public static boolean isAuthorized(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return role != null;
    }

    public static boolean isAuthorized(HttpSession session, Role role)  {
        String roleName = (String) session.getAttribute("role");
        return roleName != null && Role.getRoleByDisplayName(roleName).equals(role);
    }
}
