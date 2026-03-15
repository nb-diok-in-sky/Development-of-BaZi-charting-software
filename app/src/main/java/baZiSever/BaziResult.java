package baZiSever;

import com.nlf.calendar.Solar;
import com.nlf.calendar.EightChar;

public class BaziResult {
//首先是从键盘处获取userinput的数字 然后形成阳历


    private Solar solar = new Solar();
    private EightChar eightChar ;
    //通过这个方法，来获取edittext里面的内容
    int liuPai;//1流派1  2流派2
   int gender;//1男  0女
    int year, month, day, time;


    public void getUserInput(int year,int month,int day,int time,int gender,int liuPai) {

        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.gender = gender;
        this.liuPai = liuPai;

        this.solar = Solar.fromYmdHms(year, month, day, time, 0, 0);

        this.eightChar = solar.getLunar().getEightChar();

        //这里已经获取完毕生辰信息了，顺便转化成了阴历和八字


    }






    public Solar getSolar() {//表示阳历信息
        return this.solar;
    }
    //这个是单独获取阳历

    public EightChar getEightChar() {//获取八字信息
        return this.eightChar;
    }
    //这个是单独获取八字



}
