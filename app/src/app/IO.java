package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IO {   //输出文件
    File ExerciseFile = null;
    File AnswerFile = null;
    File GradeFile = null;
    String filename = "";
    BufferedWriter ExerciseOut = null;
    BufferedWriter AnswerOut = null;
    BufferedWriter GradeOut = null;

    public IO() {
        if (this.CreateFile()) {
            this.setOutBufferedWriter();
        } else
            System.out.println("创建文件失败！");
    }

    public void setOutBufferedWriter() {
        try {
            this.ExerciseOut = new BufferedWriter(new FileWriter(ExerciseFile));
            this.AnswerOut=new BufferedWriter(new FileWriter(AnswerFile));
            this.GradeOut=new BufferedWriter(new FileWriter(GradeFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean CreateFile() {
        String relativelyPath=System.getProperty("user.dir");
        ExerciseFile = new File(relativelyPath+"\\Exercise" + ".txt");
        AnswerFile = new File(relativelyPath+"\\Answer" + ".txt");
        GradeFile = new File(relativelyPath+"\\Grade" + ".txt");
        if (ExerciseFile.exists()) {
            ExerciseFile.delete();
        }
        if (AnswerFile.exists()) {
            AnswerFile.delete();
        }
        if (GradeFile.exists()) {
            GradeFile.delete();
        }
        try {
            ExerciseFile.createNewFile();
            AnswerFile.createNewFile();
            GradeFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    //写入文件的方法
    public boolean WriteToFile(String content, int flag) {
        try {
            switch (flag) {
                case 0:
                    ExerciseOut.write(content);
                    ExerciseOut.write("\r\n");
                    ExerciseOut.flush();
                    return true;
                case 1:
                    AnswerOut.write(content);
                    AnswerOut.write("\r\n");
                    AnswerOut.flush();
                    return true;
                case 2:
                    GradeOut.write(content);
                    GradeOut.flush();
                    return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean CloseOutBufferedWriter() {
        try {
            ExerciseOut.close(); 
            AnswerOut.close();
            GradeOut.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}