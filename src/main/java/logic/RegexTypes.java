package logic;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public enum RegexTypes {


        SELECTED_CARD("select\\s+card\\s+number\\s+(?<number>\\d+) player (?<usernumber>\\d+)\\s*"),
        PLACING_CARD("placing\\s+card\\s+number\\s+(?<number>\\d+) in block (?<blocknum>\\d+)\\s*"),

        DIGIT("\\d+");
        final String regex;

        RegexTypes(String regex) {
            this.regex = regex;
        }

        public Matcher getMatcher(String input) {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(input);
        }

        public boolean matches(String input) {
            return getMatcher(input).matches();
        }

        public String getGroup(String input, String group) {
            Matcher matcher = getMatcher(input);
            if (matcher.matches() && matcher.group(group) != null) {
                return matcher.group(group);
            }
            return "";
        }
    }

