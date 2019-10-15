package app;

public class Mark {//分数
    int x;              //分子
    int y;              //分母
    private Mark temp;

    public Mark(int a, int b) {
        x = a;
        y = b;
    }

    Mark add(Mark r) {
        temp = new Mark(0, 0);
        temp.x = x * r.y + y * r.x;
        temp.y = y * r.y;
        return temp;
    }

    Mark sub(Mark r) {
        temp = new Mark(0, 0);
        temp.x = x * r.y - y * r.x;
        temp.y = y * r.y;
        return temp;
    }

    Mark mul(Mark r) {
        temp = new Mark(0, 0);
        temp.x = x * r.x;
        temp.y = y * r.y;
        return temp;
    }

    Mark division(Mark r) {
        temp = new Mark(0, 0);
        temp.x = x * r.y;
        temp.y = y * r.x;
        return temp;
    }

    public String print() {		//分数的打印
        if (x == 0) {
            return "0";
        } else {
            int n;
            if (x > y)
                n = x;
            else
                n = y;
            int c = 0;
            for (int i = 1; i <= n; ++i) {              //约分
                if (x % i == 0 && y % i == 0)
                    c = i;
            }
            int a = x / c;
            int b = y / c;
            if (a == b)
                return "1";
            else if(b==1)
                return a+"";
            else
                return a + "/" + b;
        }
    }
    
    
    public void Analyse(String str) {		//分数计算处理选择
        String[] Mark = null;
        if (str.contains("+")) {
            Mark = str.split("+");
            MarkCalculate(Mark, 0);
        } else if (str.contains("-")) {
            Mark = str.split("-");
            MarkCalculate(Mark, 1);
        } else if (str.contains("x")) {
            Mark = str.split("x");
            MarkCalculate(Mark, 2);
        } else if (str.contains("÷")) {
            Mark = str.split("÷");
            MarkCalculate(Mark, 3);
        }
    }

    public void MarkCalculate(String[] str, int temp) {		//分数计算选择
        String[] Mark1 = new String[2];
        String[] Mark2 = new String[2];
        
        if (str[0].contains("/"))
            Mark1 = str[0].split("/");
        else {
            Mark1[0] = str[0];
            Mark1[1] = "1";
        }
        if (str[1].contains("/"))
            Mark2 = str[1].split("/");
        else {
            Mark2[0] = str[1];
            Mark2[1] = "1";
        }

        Mark m1 = new Mark(Integer.parseInt(Mark1[0]), Integer.parseInt(Mark1[1]));
        Mark m2 = new Mark(Integer.parseInt(Mark2[0]), Integer.parseInt(Mark2[1]));
        m1.print();
        
        switch (temp) {
            case 0:
            	m1.add(m2).print();
                break;
            case 1:
            	m1.sub(m2).print();
                break;
            case 2:
            	m1.mul(m2).print();
                break;
            case 3:
            	m1.division(m2).print();
                break;
        }
    }
}

