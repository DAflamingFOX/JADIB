// after putting your token in below; move it to "JADIB/src/main/java", the same folder with Main.java
public class Secret {
    private static String token = "put-bot-token-here";
    private static String dblToken = "put-dbl-token-here-if-applicable";


    public static String getToken() {
        return token;
    }

    public static String getDblToken() {
        return dblToken;
    }
}
