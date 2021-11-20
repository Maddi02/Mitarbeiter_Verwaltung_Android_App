package Entitätsklassen;

public class SacharbeiterEK {

    private static String username;
    private static String passwort;
    private static String role;


    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public static String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
