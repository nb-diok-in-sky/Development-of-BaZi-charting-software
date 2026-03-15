package baZiSever;

import java.util.List;

public class YunNianInfo {//这个类是用于转化大运流年的信息，存储天干  地支 和藏干以及各种十神 然后塞到lunar的map里面进行转化
    public int startYear;              // 大运用：开始年份  流年用：当前年份
    public int startAge;               // 大运专用，流年填0或不用

    public int year;//给流年用 存储流年的
    public String tiangan;             // 天干，如"辛"
    public String dizhi;               // 地支，如"丑"
    public String shiShenTianGan;      // 天干十神
    public List<String> cangGan;       // 藏干列表
    public List<String> shiShenCangGan;// 藏干十神列表


}
