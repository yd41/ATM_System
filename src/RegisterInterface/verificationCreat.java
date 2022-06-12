package RegisterInterface;

public class verificationCreat {
  static   public String source = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
   static public String genCode(){
        String code = "";
        for(int i = 0;i <4;i++) {
            //0-61之间是的数值
            int index = (int)(Math.random()*source.length());
            char c = source.charAt(index);
            code += c;
        }
        return code;
        }
}
