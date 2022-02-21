package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    public Validate() {
    }

    public boolean validateTitleOrCategory(String keyword, String regex) {
        keyword = keyword.toLowerCase();
        String TITLE_CATEGORY_REGEX = ".*" + keyword + ".*";
        Pattern pattern = Pattern.compile(TITLE_CATEGORY_REGEX);
        Matcher matcher = pattern.matcher(regex.toLowerCase());
        return matcher.matches();
    }
}
