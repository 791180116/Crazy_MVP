#自用mvp 在下面练个开源库基础上修改的 如有版权问题请联系修改
*借鉴的AndroidProject（https://github.com/getActivity/AndroidProject）和 XDroidMvp（https://github.com/limedroid/XDroidMvp） 
# 安卓技术中台
#XDroidMvp 轻量级的Android MVP快速开发框架

#### 常用界面

![](picture/activity/1.jpg) ![](picture/activity/2.jpg) ![](picture/activity/3.jpg)

![](picture/activity/4.jpg) ![](picture/activity/5.jpg) ![](picture/activity/6.jpg)

![](picture/activity/7.jpg) ![](picture/activity/8.jpg) ![](picture/activity/9.jpg)

![](picture/activity/10.jpg) ![](picture/activity/11.jpg) ![](picture/activity/12.jpg)

![](picture/activity/13.jpg) ![](picture/activity/14.jpg) ![](picture/activity/15.jpg)

![](picture/activity/16.jpg) ![](picture/activity/17.jpg) ![](picture/activity/18.jpg)

![](picture/activity/19.jpg) ![](picture/activity/20.jpg) ![](picture/activity/21.jpg)

![](picture/activity/22.jpg) ![](picture/activity/23.jpg) ![](picture/activity/24.jpg)

![](picture/activity/25.jpg) ![](picture/activity/26.jpg) ![](picture/activity/27.jpg)

![](picture/activity/28.jpg) ![](picture/activity/29.jpg)

------

![](picture/activity/30.jpg)

![](picture/activity/31.jpg)

![](picture/activity/32.jpg)

![](picture/activity/33.jpg)

![](picture/activity/34.jpg)

![](picture/activity/35.jpg)

![](picture/activity/36.jpg)

![](picture/activity/37.jpg)

#### 常用对话框

![](picture/dialog/1.jpg) ![](picture/dialog/2.jpg) ![](picture/dialog/3.jpg)

![](picture/dialog/4.jpg) ![](picture/dialog/5.jpg) ![](picture/dialog/6.jpg)

![](picture/dialog/7.jpg) ![](picture/dialog/8.jpg) ![](picture/dialog/9.jpg)

![](picture/dialog/10.jpg) ![](picture/dialog/11.jpg) ![](picture/dialog/12.jpg)

![](picture/dialog/13.jpg) ![](picture/dialog/14.jpg) ![](picture/dialog/15.jpg)

![](picture/dialog/16.jpg) ![](picture/dialog/17.jpg) ![](picture/dialog/18.jpg)

#### 动图欣赏

![](picture/gif/1.gif) ![](picture/gif/2.gif) ![](picture/gif/3.gif)

![](picture/gif/4.gif) ![](picture/gif/5.gif) ![](picture/gif/6.gif)

#### 项目亮点

* App 优化：已经进行了全面的内存优化、布局优化、代码优化、瘦身优化，并且对结果进行了严格的长久测试。

* 代码规范：参照 Android SDK 、Support 源码和参考阿里巴巴的代码规范文档对代码进行命名，并对难点代码进行了注释，对重点代码进行了说明。

* 代码统一：对项目中常见的代码进行了封装，或是封装到基类中、或是封装到工具类中、或者封装到框架中，不追求过度封装，根据实际场景和代码维护性考虑，尽量保证同一个功能的代码在项目中不重复。

* 敏捷开发：一个 App 大概率会出现的功能已经写好，对项目的敏捷开发起到了至关重要的作用，可用于新项目开发或者旧项目重构，可将开发周期缩短近一半的时间，并且后续不会因为前期的快速开发而留下成堆的技术遗留问题，万丈高楼平地起，AndroidProject 属于基建工程，而在软件行业我们称之为技术中台。

* 无任何瑕疵：对小屏手机、全面屏手机、带虚拟按键手机进行了适配和优化，确保每一个界面细节都能处理到位、每一个功能细节都能符合大众的需求、乃至每一行代码都能贴合 Android 程序员的审美观。

* 兼容性优良：在此感谢开源道路上给予我支持和帮助的小伙伴，一个人一台机在兼容性面前无能为力，而在几百人几百台机面前却不是问题。如果没有这些的测试，有些问题我一个人可能这辈子都发现不了，纵使代码写得再好，逻辑再严谨，没有经过大众的验证，无异于纸上谈兵。

#### [代码规范文档请点击这里查看](https://github.com/getActivity/AndroidCodeStandard)

#### [常见问题解答请点击这里查看](HelpDoc.md)

#### 作者的其他开源项目

* 网络框架：[EasyHttp](https://github.com/getActivity/EasyHttp)  （已集成）

* 权限框架：[XXPermissions](https://github.com/getActivity/XXPermissions)  （已集成）

* 吐司框架：[ToastUtils](https://github.com/getActivity/ToastUtils)  （已集成）

* 标题栏框架：[TitleBar](https://github.com/getActivity/TitleBar)  （已集成）

* Gson 解析容错：[GsonFactory](https://github.com/getActivity/GsonFactory)  （已集成）

* 悬浮窗框架：[XToast](https://github.com/getActivity/XToast)  （未集成）

* 国际化框架：[MultiLanguages](https://github.com/getActivity/MultiLanguages)  （未集成）

* 日志查看框架：[Logcat](https://github.com/getActivity/Logcat)  （未集成）

![](https://raw.githubusercontent.com/getActivity/Donate/master/picture/official_ccount.png)

#### [点击查看捐赠列表](https://github.com/getActivity/Donate)

#XDroidMvp 轻量级的Android MVP快速开发框架

## 概述

<p align="center">
	<img src="xdroid_logo_128.png"/>
</p>
**XDroidMvp全新文档**：[https://github.com/limedroid/XDroidMvp/wiki](https://github.com/limedroid/XDroidMvp/wiki)

[![](https://jitpack.io/v/limedroid/XDroidMvp.svg)]

<p align="center">
	<img src="art/XdroidMvp_total.png"/>
</p>

`XDroidMvp`主要会有这些特性：

**无需写`Contract`！ 无需写`Present`接口！  无需写`View`接口!**

新增：

* Mvp实现
* `RxJava` & `RxAndroid`
* 权限适配 `RxPermission`
* 事件订阅默认采用 `RxBus`
* 网络交互：
    * `Retrofit` + `rx`
    * `Https`
    * **统一异常处理**
    * 缓存
    * **支持多个baseUrl**
    * 。。。。
* 无需担心rx内存泄漏
* 适配AndroidX，请前往`android-x`分支

保留：

* 提供`XActivity`、`XFragment`、`SimpleRecAdapter`、`SimpleListAdapter`等基类，可快速进行开发
* 完整封装`XRecyclerView`，可实现绝大部分需求
* `XStateController`、`XRecyclerContentLayout`实现loading、error、empty、content四种状态的自由切换
* 实现了`Memory`、`Disk`、`SharedPreferences`三种方式的缓存，可自由扩展
* 内置了`RxBus`，可自由切换到其他事件订阅库
* 内置`Glide`，可自由切换其他图片加载库
* 可输出漂亮的`Log`，支持`Json`、`Xml`、`Throwable`等，蝇量级实现
* 内置链式路由
* 内置常用工具类：`package`、`random`、`file`...,提供的都是非常常用的方法
* 内置加密工具类 `XCodec`，你想要的加密姿势都有


<p align="center">
	<img src="mvp.png"/>
</p>


**先睹为快**

你可以这么使用:

BasePagerFragment

```java
public abstract class BasePagerFragment extends XFragment<PBasePager>{

   @Override
    public void initData(Bundle savedInstanceState) {
        getP().loadData(getType(), 1);	//调用P方法
    }

   public void showData(int page, GankResults model) {
        if (page > 1) {
            getAdapter().addData(model.getResults());
        } else {
            getAdapter().setData(model.getResults());
        }

        contentLayout.getRecyclerView().setPage(page, MAX_PAGE);

        if (getAdapter().getItemCount() < 1) {
            contentLayout.showEmpty();
            return;
        }
    }
    
	 @Override
    public PBasePager newP() {
        return new PBasePager();
    }}
    
    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_pager;
    }
```

PBasePager

```java
public class PBasePager extends XPresent<BasePagerFragment> {
    protected static final int PAGE_SIZE = 10;


    public void loadData(String type, final int page) {
        Api.getGankService().getGankData(type, PAGE_SIZE, page)
                .compose(XApi.<GankResults>getApiTransformer())
                .compose(XApi.<GankResults>getScheduler())
                .subscribe(new ApiSubcriber<GankResults>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error); //调用V方法
                    }

                    @Override
                    public void onNext(GankResults gankResults) {
                        getV().showData(page, gankResults);
                    }
                });
    }
}
```

## Get Started

使用，仅需四步：

### step1

clone 'XDroid'库到本地:
```groovy
git clone https://github.com/limedroid/XDroidMvp.git
```

### step2

将`mvp`作为依赖库，在您的app module 中 添加如下依赖:
```groovy
compile project(':mvp')
```

### step3

拷贝`conf.gradle`到您的项目根目录，并修改项目gradle文件下引入：
```groovy
apply from: "conf.gradle"
```

并添加:

```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

### step4

修改`XDroidConf`配置类，主要针对log、cache、router、imageloader。若采用默认配置，此步骤可略过.

## 第二种方式，通过JitPack引入

### step1 在根目录的gradle文件中配置:
```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

### step2 添加依赖:
```groovy
dependencies {
	   implementation 'com.github.limedroid:XDroidMvp:v2.0.1'
}
```


## Demo效果

<p align="center">
	<img src="art/snapshot_2.png"/>
</p>

<p align="center">
	<img src="art/snapshot_1.png"/>
</p>


## 重要说明

* [ButterKnife](https://github.com/JakeWharton/butterknife)使用的是8.4.0版本，重点是 `@BindView`，可以去项目官网查看。
* [Rxlifecycle](https://github.com/trello/RxLifecycle)使用的是1.0版本，具体如何使用可以查看官网。
* [RxPermissions](https://github.com/tbruyelle/RxPermissions)使用的是0.9.1版本，具体如何使用可以查看官网。
* [retrofit](https://github.com/square/retrofit)，具体如何使用可以查看官网。

## 更新日志

* 2017-04-23，新增proguard rules,upgrade to rx2
* 2016-12-23，新增mvp、base、cache、event、imageloader、log、router
* 2016-12-25，新增rxJava、rxAndroid、rxlifecycle、rxpermission、rxbus、net(retrofit)
* 2016-12-26，新增网络异常统一处理
* 2016-12-28，重构MVP
* 2016-12-30，重构网络层
* 2016-12-31，新增[Demo](https://github.com/limedroid/XDroidMvp/tree/master/app)


## TODO

* [x] rx
* [x] retrofit
* [x] rxpermission
* [x] rxbus
* [x] cache
* [x] wiki
* [x] demo

## About Me

**Email** : droidlover@126.com

**XDroid交流群**：153569290

**XDroid MVC版本**：[XDroid](https://github.com/limedroid/XDroid)

若您在使用过程中遇到任何问题，欢迎加入 **153569290** 群或者是邮件反馈，谢谢您的关注。**XDroidMvp**会持续维护，如果喜欢，记得star fork。


