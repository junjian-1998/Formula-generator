package app;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class MyApp {
    private static Scanner in;

	public static void main(String[] args) {
        in = new Scanner(System.in);
        System.out.print("请输入生成的题目数量 -n：");
        int probleNum = in.nextInt();
        System.out.print("请输入题目的数值范围(自然数) -r：");
        int rangNum = in.nextInt();

        LinkedHashMap<Integer, String> rightAnswerMap = new LinkedHashMap<Integer, String>();
        LinkedHashMap<Integer,String> exerciseMap = new LinkedHashMap<Integer, String>();
        ArrayList<Integer> rightRecord = new ArrayList<Integer>();
        ArrayList<Integer> wrongRecord = new ArrayList<Integer>();
        Generate generate1 = new Generate();
        IO save = new IO();
        generate1.setNum(rangNum);
        
        for (int i = 1; i <= probleNum; i++) {
            String problem = generate1.generation();
            exerciseMap.put(i,problem);
            String ns = problem;
            int rightbrackets;
            int leftbrackets;
            
            if (problem.contains(")")) {
                rightbrackets = problem.indexOf(")");
                leftbrackets = problem.indexOf("(");
                if (rightbrackets != problem.length() - 1 && problem.charAt(rightbrackets + 1) == '/') {
                    StringBuilder sb = new StringBuilder(problem);
                    if (leftbrackets - 1 > 0 && problem.charAt(leftbrackets - 1) == '÷')
                        sb.replace(rightbrackets + 1, rightbrackets + 2, "x");
                    else
                        sb.replace(rightbrackets + 1, rightbrackets + 2, "÷");
                    ns = sb.toString();
                }
            }


            Calculator cal = new Calculator();
            String result = cal.calculate(ns);
            
            if (result != null) {
                result = cal.getResult(result);
                System.out.println(i + ":  " + problem + "=" );
                rightAnswerMap.put(i, result);
                if (!save.WriteToFile(i + ":  " + problem + "=", 0)) {
                    System.out.println("生成Exercise文件失败！");
                    System.exit(0);
                }
                if (!save.WriteToFile( i + ":  " + problem + "=" + result, 1)) {
                    System.out.println("生成Answer文件失败！");
                    System.exit(0);
                }
               
            } else {
                i--;
            }

            

        }

            System.out.println("请输入答案(带分数用“ ’ ”分隔，如1’1/2)：");
            
            for (int i = 1; i <= probleNum; i++) {
                System.out.print( + i + ":  " + exerciseMap.get(i) + "=");
                String input = in.next();
                if (rightAnswerMap.get(i).equals(input))
                    rightRecord.add(i);
                else
                    wrongRecord.add(i);
            }
            System.out.println("结果为：");

            //打印正确题目数目和题号
            if (rightRecord.size()!=0){
                System.out.print("正确题目:"+rightRecord.size()+" (");
                for (int i=0;i<rightRecord.size()-1;i++)
                    System.out.print(rightRecord.get(i)+",");
                System.out.println(rightRecord.get(rightRecord.size()-1)+")");
            }
            else
                System.out.println("正确题目:"+rightRecord.size());

            //打印错误题目数目和题号
            if (wrongRecord.size()!=0){
                System.out.print("错误题目:"+wrongRecord.size()+" (");
                for (int i=0;i<wrongRecord.size()-1;i++)
                    System.out.print(wrongRecord.get(i)+",");
                System.out.println(wrongRecord.get(wrongRecord.size()-1)+")");
            }
            else
                System.out.println("错误题目:"+wrongRecord.size());
            
            //打印正确题目数目和题号到Grade.txt文件
            if (rightRecord.size()!=0){
                save.WriteToFile("正确题目:"+rightRecord.size()+" (",2);
                for (int i=0;i<rightRecord.size()-1;i++)
                save.WriteToFile(rightRecord.get(i)+",",2);
                save.WriteToFile(rightRecord.get(rightRecord.size()-1)+")"+"\n",2);
            }
            else
            	 save.WriteToFile("正确题目:"+rightRecord.size()+"\n",2);
            
            //打印错误题目数目和题号到Grade.txt文件
            if (wrongRecord.size()!=0){
            	 save.WriteToFile("错误题目:"+wrongRecord.size()+" (",2);
                for (int i=0;i<wrongRecord.size()-1;i++)
                	 save.WriteToFile(wrongRecord.get(i)+",",2);
                save.WriteToFile(wrongRecord.get(wrongRecord.size()-1)+")",2);
            }
            else
            	 save.WriteToFile("错误题目:"+wrongRecord.size(),2);
            
            
            
            
        if (save.CloseOutBufferedWriter())
            System.out.println("文本创建成功");
        else
            System.out.println("文本创建失败");
        
    }
	
}
