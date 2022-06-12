package Deposit;

public class IsNum {
    //正则表达式判断是否是数字字符串（可判断正数，负数和小数）
 static    public boolean isNumberString(String str) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9]+(\\.[0-9]+)?");
        return pattern.matcher(str).matches();
    }
}
