package app;

import java.util.Random;

public class Generate {
    String[] sign = {"+", "-", "x", "÷", "/"};
    int rangeNum;
    Random random = new Random();

    
    public void setNum(int rangeNum) {
        this.rangeNum =rangeNum;
    }

    
    public String generation() {
        String str = "";
        int num = random.nextInt(3);
        
        for (int i = 0; i < 3; i++) {
            if (num == 0 && i == 0) {
                str += "(";
            } else if (num == 2 && i == 1) {
                str += "(";
            }
            
            str += random.nextInt(rangeNum) % rangeNum + 1;   //产生指定范围随机数
            if (num == 0 && i == 1) {
                str += ")";
            }
            if (num == 2 && i == 2) {
                str += ")";
            }
            
            String symbol = sign[random.nextInt(5)];//产生随机运算符号
            str += symbol;
            if (symbol == "/") {
                str += random.nextInt(rangeNum) % rangeNum + 1;
                symbol = sign[random.nextInt(5)];
                while (true) {
                    if (symbol != "/") {
                        str += symbol;
                        break;
                    }
                    symbol = sign[random.nextInt(5)];
                }
            }
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }
}