package baZiSever;


import java.util.List;

public class GanZhiText {//这个类是用来存储每个柱的   里面是 天干 和地支

    //每一个字都是一个单独的对象 用来存储数据
    public String gan;      // 天干
    public String zhi;      // 地支
    public List<String> hideGan;  // 藏干
    public List<String> hideGanShiShen;

    public String star;//天干十神
    public String diShi;
    public String naYin;    //纳音



    // 构造方法：创建对象时必须给这三个属性赋值
    public GanZhiText(String star,String gan, String zhi, List<String> hideGan,List<String> hideGanShiShen,String diShi,String naYin){
        this.star = star;//十神
        this.gan = gan;
        this.zhi = zhi;
        this.hideGanShiShen = hideGanShiShen;
        this.hideGan = hideGan;
        this.diShi = diShi;
        this.naYin = naYin;
    }


}
