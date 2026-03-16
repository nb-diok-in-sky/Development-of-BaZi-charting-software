package baZiSever;

import static com.nlf.calendar.EightChar.CHANG_SHENG;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import androidx.core.content.ContextCompat;

import com.example.mybazi.R;
import com.nlf.calendar.EightChar;
import com.nlf.calendar.util.LunarUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tools {



    private static final Map<String, Integer> CHANG_SHENG_OFFSET = new HashMap<String, Integer>() {{
        put("甲", 1);  put("丙", 10); put("戊", 10);
        put("庚", 7);  put("壬", 4);
        put("乙", 6);  put("丁", 9);  put("己", 9);
        put("辛", 0);  put("癸", 3);
    }};

    public static String getDiShi(String dayGan, int zhiIndex) {
        int dayGanIndex = Arrays.asList(LunarUtil.GAN).indexOf(dayGan);
        int index = CHANG_SHENG_OFFSET.get(dayGan) + (dayGanIndex % 2 == 0 ? zhiIndex : -zhiIndex);
        if (index >= 12) index -= 12;
        if (index < 0)   index += 12;
        return CHANG_SHENG[index];
    }




    private static Map<String, Integer> COLOR_MAP;

    //新写一个工具用来区别开不同字的颜色
    public static void ControlColor(Context context){
            COLOR_MAP = new HashMap<>();
        //将那些颜色数据初始化进来 转化成int类方便处理
        int mu = ContextCompat.getColor(context, R.color.wuxing_mu);
        int huo = ContextCompat.getColor(context,R.color.wuxing_huo);
        int tu   = ContextCompat.getColor(context, R.color.wuxing_tu);
        int jin  = ContextCompat.getColor(context, R.color.wuxing_jin);
        int shui = ContextCompat.getColor(context, R.color.wuxing_shui);


        for(String s :new String[]{"甲","乙","寅","卯"})            COLOR_MAP.put(s, mu);
        for(String s :new String[]{"丙","丁","巳","午"})            COLOR_MAP.put(s, huo);
        for (String s : new String[]{"戊","己","丑","辰","未","戌"}) COLOR_MAP.put(s, tu);
        for (String s : new String[]{"庚","辛","申","酉"})          COLOR_MAP.put(s, jin);
        for (String s : new String[]{"壬","癸","亥","子"})          COLOR_MAP.put(s, shui);
        //这里是绑定每个颜色和对应的字，接下来只要在输入的时候把颜色和字匹配就可以了

    }
    public static int getColor(String ganOrZhi) {
        Integer color = COLOR_MAP.get(ganOrZhi);
        return color != null ? color : Color.BLACK;
    }




    //这就需要一个给天干地支调整颜色的方法了，
    public static SpannableStringBuilder setColorOfGanZhi(String text){
            SpannableStringBuilder sb = new SpannableStringBuilder(text);
            sb.setSpan(new ForegroundColorSpan(getColor(text)), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
    }


    //新增一个拼接十神和藏干的tools

    public static SpannableStringBuilder setColorOfHideGanShiShen(Context context,List<String> hideGan, List<String> shiShen){
        //需要拼接 所以要先声明用于拼接的字符串
        SpannableStringBuilder formatHideGanWithShiShen = new SpannableStringBuilder();

        for (int i = 0; i < hideGan.size(); i++) {
            String gan  = hideGan.get(i);
            String shen = shiShen.get(i);

            int start = formatHideGanWithShiShen.length();
            formatHideGanWithShiShen.append(gan);
            formatHideGanWithShiShen.setSpan(new ForegroundColorSpan(getColor(gan)), start, formatHideGanWithShiShen.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


            formatHideGanWithShiShen.append(" ");

            start = formatHideGanWithShiShen.length();
            formatHideGanWithShiShen.append(shen);
            formatHideGanWithShiShen.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.shiShen_color)), start, formatHideGanWithShiShen.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            if (i < hideGan.size() - 1) formatHideGanWithShiShen.append("\n");
        }
        return formatHideGanWithShiShen;
    }

    public static List<GanZhiText> getGanZhiText(EightChar eightChar){
        //这里是生成遍历 存储每个柱子的天干地支和藏干
        //生成四个柱子 每个柱都算一个天干地支还有藏干

        List<GanZhiText> list = new ArrayList<>();
        list.add(new GanZhiText(eightChar.getYearShiShenGan(),eightChar.getYearGan(), eightChar.getYearZhi(), eightChar.getYearHideGan(),eightChar.getYearShiShenZhi(),eightChar.getYearDiShi(),eightChar.getYearNaYin()));
        list.add(new GanZhiText(eightChar.getMonthShiShenGan(),eightChar.getMonthGan(), eightChar.getMonthZhi(), eightChar.getMonthHideGan(),eightChar.getMonthShiShenZhi(),eightChar.getMonthDiShi(),eightChar.getMonthNaYin()));
        list.add(new GanZhiText(eightChar.getDayShiShenGan(),eightChar.getDayGan(), eightChar.getDayZhi(), eightChar.getDayHideGan(),eightChar.getDayShiShenZhi(),eightChar.getDayDiShi(),eightChar.getDayNaYin()));
        list.add(new GanZhiText(eightChar.getTimeShiShenGan(),eightChar.getTimeGan(), eightChar.getTimeZhi(),eightChar.getTimeHideGan(),eightChar.getTimeShiShenZhi(),eightChar.getTimeDiShi(),eightChar.getTimeNaYin()));
        return list;

    }





}


