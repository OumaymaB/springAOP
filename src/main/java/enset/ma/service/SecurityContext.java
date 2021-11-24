package enset.ma.service;

public class SecurityContext {
    private static String username="";
    private static String password="";
    private static String[] roles={};

    public static void authenticate(String username, String password, String[] r){
        if(username.equals("root") && password.equals("1234")){
            username = username;
            password = password;
            roles = r;
        }else {
            throw new RuntimeException("Access denied ...");
        }
    }

    public static boolean hasRole(String r){
        for (String role : roles){
            if (role.equals(r))
                return true;
        }
        return false;
    }
}
