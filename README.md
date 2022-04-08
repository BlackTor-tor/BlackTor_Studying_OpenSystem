# 一、项目名称

# 黑洋葱学习开放平台系统（BlackTor Studying OpenSystem，后面简称BSOS）

# 二、项目背景

学习是一件终生的事。先提出三个问题：一、为什么要学习？二、为什么而学习？三、学习的意义所在？我思考了良久，这个项目包含了我所有想法的精华所在。所以我想把这个平台打造成一个对所有热爱学习的人都有所帮助的平台，它不仅仅是一个软件系统，更是一个注入了灵魂的学习世界。每个人的答案是不一样的，无论你是想提升自己还是想多汲取一些知识，答案你可以在项目中寻找。

这个系统能做什么？

可以帮你找到志同道合的人。

可以开拓你的思维，专注于学习这件事。

可以帮助你变得更加优秀。

# 三、项目情况

**项目负责人**：tor

**项目成员**：待定

**项目协议**：开源协议Apache

**项目技术架构**：

**PC端**：web、MacOS、Windows

  - **开发语言**：Java
  - **前端框架**：vue
  - **后端框架**：
  - **服务器**：Linux8.5、Nginx
  - **数据库**：MySQL



**移动端**：H5、小程序、iOS、Android



## 1、业务功能需求：

1. 目前项目一共有10个模块：①用户注册和登录、②打卡签到、③数据统计、④学伴、⑤学习社区、⑥图书馆、⑦设置、⑧个人中心、⑨成就、⑩学习工具箱
2. 第一版暂时只做①②③⑦⑧
3. 网站主页显示一些文艺的图片和文字，提供【注册】和【登录】、【忘记密码】的按钮



## 2、开发模块详情：

### ①用户注册和登录

需求描述：

1. 1. 用户注册的基本信息：*用户昵称、年龄、*账号、*密码、手机号和邮箱等字段。（*号为必填项）
   2. 支持微信、QQ授权登录
   3. 根据国家政策要求，互联网平台用户注册必须进行实名认证，注册完给予提示和7天的宽限期，如果不实名就回收账号
   4. 采用邀请制注册，必须有邀请码

- 详见http://www.cac.gov.cn/2021-10/26/c_1636843202454310.htm



### ②打卡签到

示例：

| 用户昵称 | 描述     | 打卡时间         | 创建时间         | 结束时间         | 打卡封面 | 删除时间(前端不可见) |
| -------- | -------- | ---------------- | ---------------- | ---------------- | -------- | -------------------- |
| 张三     | 学习打卡 | 2000-01-03 15:00 | 2000-01-01 12:00 | 2001-01-01 12:00 | image    | null                 |

需求描述：

- - 用户创建打卡，生成一张表。

- - 可以生成打卡链接，分享给别人，不需要注册也可以打开链接，但是要打卡必须注册。



### ③数据统计

1. 1. 统计每个用户的打卡次数、未打卡次数。可以按时间筛选。
   2. 排行榜功能。

- - 建立日排行榜、周排行榜、月排行榜、年排行榜等四个纬度，统计用户、打卡次数、连续打卡时间(天)



### ④学伴

1. 1. 社交功能。

- - 可以添加、删除、拉黑好友
  - 可以私信好友、建立对话窗口，发送文字、图片和视频



### ⑤学习社区

1. 1. 设置不同的领域版块。

比如：人文、地理、历史、科学、社会、财经等等。



### ⑥图书馆

1. 1. 内置一个浏览器的功能，通过访问网址跳转到对应的在线图书平台
   2. 实现电子图书阅读器的功能，可以导入PDF、TXT等格式的文件。

**PS：该功能必须声明提示用户购买正版图书，如果使用盗版图书需自行承担相应法律责任**



### ⑦设置

1. 1. 设置个人头像、个人介绍、姓名、年龄、手机号、邮箱。**【均可以修改】**
   2. 退出登录



### ⑧个人中心

1. 1. 记事本功能

- - 可以做文字笔记、上传图片、音频、不超过1分钟的视频
  - 设置打卡提醒通知

- - - 设置提醒时间
    - 通知方式：APP弹窗、后续对接小程序和微信API通知接口

1. 1. 显示用户已读x本书、最近在看、打卡次数等数据



### ⑨成就

1. 1. 通过打卡次数、连续打卡次数、打卡的具体时间，来获得各项系统成就。

示例：

连续打卡次数超过30天，获得【学习路上的独行者】成就

连续打卡时间为21:00-24:00超过7天，获得【最勤奋的学习者】成就



### ⑩学习工具箱

1. 1. 学习辅助的功能，可以是对接第三方平台的工具



## 3、首页

- 默认文本文案：开始学习吧

- 注册完以后弹窗，让用户输入一句最想对自己开始学习说的话。**并且数据保存到到首页展示，类似一个3D模型，可以让每句话不停的滚动。点击文字可以停留查看，文字末尾显示❤的图标可以点击。**
- slogan：优秀，从来不晚。
