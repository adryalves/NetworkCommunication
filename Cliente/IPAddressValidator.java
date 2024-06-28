package Cliente;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class IPAddressValidator {
    private static final String IPV4_PATTERN = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private final Pattern pattern;

    public IPAddressValidator() {
        pattern = Pattern.compile(IPV4_PATTERN);
    }

    public boolean validate(final String ip) {
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

}
