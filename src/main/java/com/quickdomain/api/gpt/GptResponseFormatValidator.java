package com.quickdomain.api.gpt;

/**
 * Provides methods to validate the format of the gpt response.
 */
public class GptResponseFormatValidator {

    /**
     * Checks whether the response content is in the valid format: "str1,str2,str3"
     * @param content is the string to validate its format.
     * @return <code>true</code> if the string is on valid format, <code>false</code> otherwise.
     */
    public static boolean isValidContent(String content) {
        String FORMAT_REGEX = "^[^,]+(,[^,]+)*$";
        return content.matches(FORMAT_REGEX);
    }
}
