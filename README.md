# 八字排盘软件 BaZi Charting App

> 这是我学习 Android 开发出来的第一个完整作品。从控制台打印迭代到完整 Android 应用，历时约一周完成。

---

## 功能介绍

### 八字排盘
- 通过 NumberPicker 输入出生年、月、日、时
- 支持选择性别（男/女）与流派（流派1/流派2）
- 自动计算并展示四柱天干地支、藏干、纳音
- 支持重新排盘，点击返回按钮可回到输入界面重新选择

### 五行颜色系统
- 每个天干、地支、藏干根据五行属性自动上色
- 木→绿、火→红、土→棕、金→黄、水→蓝
- 藏干与对应十神同行显示，颜色独立区分

### 大运 & 流年
- 横向 RecyclerView 展示十个大运柱
- 点击大运动态刷新对应流年列表
- 大运自动计算藏干与十神（基于 lunar 库扩展实现）

### 干支详情弹窗
- 点击任意天干或地支弹出 BottomSheet
- 展示该字的五行、阴阳、十二长生、神煞等详细信息
- 标题文字使用对应五行颜色高亮
---

## 技术栈

| 模块 | 技术 |
|---|---|
| 开发语言 | Java |
| 平台 | Android |
| 八字计算 | [6tail/lunar-java](https://github.com/6tail/lunar-java) |
| 布局 | ConstraintLayout + LinearLayout |
| 列表 | RecyclerView（横向滑动） |
| 弹窗 | BottomSheetDialogFragment |
| 文本富样式 | SpannableStringBuilder + ForegroundColorSpan |

---

## 项目结构

```
app/src/main/
├── java/
│   ├── baZiSever/
│   │   ├── BaZiInfo.java             # 干支详情文本
│   │   ├── BaziResult.java           # 八字数据获取
│   │   ├── DaYunAdapter.java         # 大运列表适配器
│   │   ├── GanZhiBottomSheet.java    # 干支详情弹窗
│   │   ├── GanZhiText.java           # 单柱干支数据模型
│   │   ├── LiuNianAdapter.java       # 流年列表适配器
│   │   ├── Print.java                # 数据输出
│   │   ├── Tools.java                # 工具类
│   │   ├── WuXingColorUtil.java      # 五行颜色工具类
│   │   ├── YunNianFactory.java       # 大运/流年数据构建
│   │   └── YunNianInfo.java          # 大运/流年数据模型
│   └── com.example.mybazi/
│       └── MainActivity.java         # 主界面逻辑
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── bottom_sheet_ganzhi.xml
    │   └── item_dayun.xml
    └── values/
        ├── colors.xml                # 五行颜色定义
        └── strings.xml               # 干支详情文本
```

---

## 运行方式

1. 克隆项目
```bash
git clone https://github.com/nb-diok-in-sky/Development-of-BaZi-charting-software.git
```

2. 用 Android Studio 打开项目

3. 确保已安装 Android SDK，最低支持 API 26

4. 连接设备或启动模拟器，点击 Run 即可

---

## 依赖

```gradle
implementation 'cn.6tail:lunar:1.7.7'
implementation 'com.google.android.material:material:1.x.x'
```

---

## 开发背景

开发时作者为大二在校生，方向为技术美术（TA）。本项目为自学 Android 开发的练习作品，核心目的是熟悉面向对象编程、UI 组件、数据流设计等基础概念。

项目从最初将所有八字信息打印到单个 TextView，逐步迭代为：
- 多 TextView 精确控制每个字段
- SpannableString 实现多色富文本
- RecyclerView 动态列表
- BottomSheet 交互弹窗

---

## License

MIT License
