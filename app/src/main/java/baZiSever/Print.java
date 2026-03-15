package baZiSever;


import com.example.mybazi.R;
import com.example.mybazi.databinding.ActivityMainBinding;
import com.nlf.calendar.EightChar;
import com.nlf.calendar.eightchar.DaYun;
import com.nlf.calendar.eightchar.Yun;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

public class Print {
    Tools tools ;
    private BaziResult baziResult;
    private Context context;
    ActivityMainBinding binding;

    public Print(BaziResult baziResult, Tools tools, ActivityMainBinding binding, Context context)
    {//这一段是把括号里面传进来的外部的baziresult数据保存在print里面
        this.baziResult = baziResult;
        this.tools = tools;
        this.binding = binding;
        this.context = context;
    }


    //下面这个是打印四柱和十神的
    public String printSiZhu() {
        EightChar eightChar = baziResult.getEightChar();
        //下面这是四柱
        StringBuilder sizhu = new StringBuilder();
        sizhu.append("年柱：").append(eightChar.getYear()).append("\n");
        sizhu.append("月柱：").append(eightChar.getMonth()).append("\n");
        sizhu.append("日柱：").append(eightChar.getDay()).append("\n");
        sizhu.append("时柱：").append(eightChar.getTime()).append("\n");
//接下来打印藏干
        sizhu.append("年柱藏干：").append(eightChar.getYearHideGan()).append("\n");
        sizhu.append("月柱藏干：").append(eightChar.getMonthHideGan()).append("\n");
        sizhu.append("日柱藏干：").append(eightChar.getDayHideGan()).append("\n");
        sizhu.append("时柱藏干：").append(eightChar.getTimeHideGan()).append("\n");
        return sizhu.toString();
    }





    public void printResult()
    {

        EightChar eightChar = baziResult.getEightChar();

      List<GanZhiText> list =  tools.getGanZhiText(eightChar);//这里返回的是一个list类型 装载的是四柱，Ac
        //接下来要将刚刚的list传入textview里面
        TextView[] starViews = {binding.tvYearStar,binding.tvMonthStar,binding.tvDayStar, binding.tvTimeStar};
        TextView[] ganViews = {binding.tvYearGan, binding.tvMonthGan, binding.tvDayGan, binding.tvTimeGan};//创建数组把他们存起来，等会方便循环遍历
        TextView[] zhiViews = {binding.tvYearZhi, binding.tvMonthZhi, binding.tvDayZhi, binding.tvTimeZhi};
        TextView[] hideGanViews = {binding.tvYearHideGan, binding.tvMonthHideGan, binding.tvDayHideGan, binding.tvTimeHideGan};
        TextView[] naYinView = {binding.tvYearNaYin,binding.tvMonthNaYin,binding.tvDayNaYin,binding.tvTimeNaYin  };
        TextView[] diShiView = {binding.tvYearDiShi,binding.tvMonthDiShi,binding.tvDayDiShi,binding.tvTimeDiShi};

            for(int i = 0;i<list.size();i++){
                GanZhiText ganZhiText =list.get(i) ;
                starViews[i].setText(ganZhiText.star);
                ganViews[i].setText(Tools.setColorOfGanZhi(ganZhiText.gan));
                zhiViews[i].setText(Tools.setColorOfGanZhi(ganZhiText.zhi));
                hideGanViews[i].setText(Tools.SetColorOfGanZhi(context,ganZhiText.hideGan, ganZhiText.hideGanShiShen));
                diShiView[i].setText(ganZhiText.diShi);
                naYinView[i].setText(ganZhiText.naYin);

                //用三个循环来把这个干支view初始化了
                //我好棒
            }
    }




        //接下来打印十神
public String printShiShen(){
    EightChar eightChar = baziResult.getEightChar();
    StringBuilder shishen = new StringBuilder();
        //年柱十神
    shishen.append("年干十神：").append(eightChar.getYearShiShenGan()).append("\n");
    shishen.append("年支十神：").append(eightChar.getYearShiShenZhi()).append("\n");

    shishen.append("月干十神：").append(eightChar.getMonthShiShenGan()).append("\n");
    shishen.append("月支十神：").append(eightChar.getMonthShiShenZhi()).append("\n");

    shishen.append("日干十神：").append(eightChar.getDayShiShenGan()).append("\n");
    shishen.append("日支十神：").append(eightChar.getDayShiShenZhi()).append("\n");

    shishen.append("时干十神：").append(eightChar.getTimeShiShenGan()).append("\n");
    shishen.append("时支十神：").append(eightChar.getTimeShiShenZhi()).append("\n");
  return shishen.toString();
    }



    //接下来打印地支十二张生
    public String printDiZhangSheng(){
        EightChar eightChar = baziResult.getEightChar();
        StringBuilder shierzhangsheng = new StringBuilder();
        shierzhangsheng.append("年自坐").append(eightChar.getYearDiShi()).append("\n");
        shierzhangsheng.append("月自坐").append(eightChar.getMonthDiShi()).append("\n");
        shierzhangsheng.append("日自坐").append(eightChar.getDayDiShi()).append("\n");
        shierzhangsheng.append("时自坐").append(eightChar.getTimeDiShi()).append("\n");
        return shierzhangsheng.toString();

    }




    //接下来打印纳音
    public String printNaYin() {
        EightChar eightChar = baziResult.getEightChar();
        StringBuilder nayin = new StringBuilder();
       nayin.append("年纳音").append(eightChar.getYearNaYin()).append("\n");

        nayin.append("月纳音").append(eightChar.getMonthNaYin()).append("\n");

        nayin.append("日纳音").append(eightChar.getDayNaYin()).append("\n");

         nayin.append("时纳音").append(eightChar.getTimeNaYin()).append("\n");
        return nayin.toString();
    }

    //接下来打印空亡
    public String printKongWang(){
        EightChar eightChar = baziResult.getEightChar();
        StringBuilder kongwang = new StringBuilder();
        kongwang.append("年空亡").append(eightChar.getYearXunKong()).append("\n");
        kongwang.append("月空亡").append(eightChar.getMonthXunKong()).append("\n");
        kongwang.append("日空亡").append(eightChar.getDayXunKong()).append("\n");
        kongwang.append("时空亡").append(eightChar.getTimeXunKong()).append("\n");
       return kongwang.toString();


    }

    public String printDaYun() {
        EightChar eightChar = baziResult.getEightChar();
        int liuPai = baziResult.liuPai;
        int gender = baziResult.gender;
        StringBuilder dayun = new StringBuilder();

        Yun yun = eightChar.getYun(gender, liuPai);
        DaYun[] daYunArr = yun.getDaYun();

        for (int i = 0, j = daYunArr.length; i < j; i++) {
            DaYun daYun = daYunArr[i];
            dayun.append("大运["+i+"] = ").append(daYun.getStartYear()).append("年 ").append(daYun.getStartAge()).append("岁 ").append(daYun.getGanZhi()).append("\n");
        }
        return dayun.toString();

    }

    //接下来是汇总
    public String  printAll(){
        StringBuilder all = new StringBuilder();
        all.append(printSiZhu()).append("\n");
        all.append(printShiShen()).append("\n");
        all.append(printDiZhangSheng()).append("\n");
        all.append(printNaYin()).append("\n");
        all.append(printKongWang()).append("\n");
        all.append(printDaYun()).append("\n");
        return all.toString();

    }


//接下来打印旺相休囚死
    //如果月令是木
    //木	旺
    //火	相
    //水	休
    //金	囚
    //土	死


//五行	状态
//火	旺
//土	相
//木	休
//水	囚
//金	死


//金当令。
//
//五行	状态
//金	旺
//水	相
//土	休
//火	囚
//木	死


    //水当令。
    //
    //五行	状态
    //水	旺
    //木	相
    //金	休
    //土	囚
    //火	死




}
