package app;

import java.util.Stack;

public class Calculator {
	//用栈来存放数字和符号
    private Stack<String> numStack = null;
    private Stack<Character> charStack = null;

    public String calculate(String Str) {
        Str = removeSpace(Str);
        
/*    	if(Str != null) {
    		Str.replaceAll(" ", "");
    		return Str;
    	}else {
    		return Str = "";
    	}*/
       
        if (Str.length() > 1 && !"=".equals(Str.charAt(Str.length() - 1) + "")) {
            Str += "=";
        }
        if (!isStandard(Str)) {
            return "0";
        }

        numStack = new Stack<String>();
        charStack = new Stack<Character>();
        StringBuffer temp = new StringBuffer();
        
        for (int i = 0; i < Str.length(); i++) {
            char ch = Str.charAt(i);
            if ((ch >= '0' && ch <= '9') || ch == '/') { 
                temp.append(ch);
            } else {
                String tempStr = temp.toString();
                if (!tempStr.isEmpty()) {
                    numStack.push(tempStr);
                    temp = new StringBuffer();
                }
                while (!compare(ch) && !charStack.empty()) {
                    String a = numStack.pop(); 
                    String b = numStack.pop();
                    Mark m1 = null;
                    Mark m2 = null;
                    if (a.contains("/")) {
                        String[] alist = a.split("/");
                        m1 = new Mark(Integer.parseInt(alist[0]), Integer.parseInt(alist[1]));
                    } else {
                        m1 = new Mark(Integer.parseInt(a), 1);
                    }
                    if (b.contains("/")) {
                        String[] blist = b.split("/");
                        m2 = new Mark(Integer.parseInt(blist[0]), Integer.parseInt(blist[1]));
                    } else {
                        m2 = new Mark(Integer.parseInt(b), 1);
                    }
                    switch (charStack.pop()) {
                        case '+':
                            numStack.push(m2.add(m1).print());
                            break;
                        case '-':
                            if ((m1.x/m1.y) >= (m2.x/m2.y)) {
                                return null;
                            
                        }
                            numStack.push(m2.sub(m1).print());
                            break;
                        case 'x':
                            numStack.push(m2.mul(m1).print());
                            break;
                        case '÷':
                            if (m1.x==0){
                                return null;
                            }
                            numStack.push(m2.division(m1).print());
                            break;
                        default:
                            break;
                    }
                } 
                if (ch != '=') {
                    charStack.push(new Character(ch)); 
                    if (ch == ')') { 
                        charStack.pop();
                        charStack.pop();
                    }
                }
            }
        } 

        return numStack.pop(); 
    }


    private String removeSpace(String str) {
        return str != null ? str.replaceAll(" ", "") : "";
    }

 
    private boolean isStandard(String numStr) {
        if (numStr == null || numStr.isEmpty()) 
            return false;
        Stack<Character> stack = new Stack<Character>(); 
        boolean b = false; 
        for (int i = 0; i < numStr.length(); i++) {
            char n = numStr.charAt(i);
            if (!((n >= '0' && n <= '9') || "(".equals(n + "") || ")".equals(n + "")
                    || "+".equals(n + "") || "-".equals(n + "")
                    || "x".equals(n + "") || "÷".equals(n + "") || "/".equals(n + "")
                    || "=".equals(n + ""))) {
                return false;
            }
            
            if ("(".equals(n + "")) {
                stack.push(n);
            }
            if (")".equals(n + "")) { 
                if (stack.isEmpty() || !"(".equals((char) stack.pop() + "")) 
                    return false;
            }
            
            if ("=".equals(n + "")) {
                if (b)
                    return false;
                b = true;
            }
        }
        if (!stack.isEmpty())
            return false;
        if (!("=".equals(numStr.charAt(numStr.length() - 1) + "")))
            return false;
        return true;
    }

    
    private boolean compare(char symbol) {	 //比较优先级：如果当前运算符比栈顶元素运算符优先级高则返回true，否则返回false
        if (charStack.empty()) { 
            return true;
        }

        char top = charStack.peek();
        if (top == '(') {
            return true;
        }
        switch (symbol) {
            case '(': 
                return true;
            case 'x': {
                if (top == '+' || top == '-') 
                    return true;
                else
                    return false;
            }
            case '÷': {
                if (top == '+' || top == '-') 
                    return true;
                else
                    return false;
            }
            case '+':
                return false;
            case '-':
                return false;
            case ')': 
                return false;
            case '=':
                return false;
            default:
                break;
        }
        return true;
    }

    String getResult(String str) {
        if (!str.contains("/"))
            return str;
        String[] part = str.split("/");
        int a = Integer.parseInt(part[0]);
        int b = Integer.parseInt(part[1]);
        
        if (a == b)
            return "1";
        else if (a > b && a % b != 0) {
            return a / b + "’" + a % b + "/" + b;
        } else if (a < b && -a > b && (-a) % b != 0) {
            return "-" + (-a) / b + "’" + (-a) % b + "/" + b;
        } else if (b == 1)
            return a + "";
        else
            return a + "/" + b;
    }

}