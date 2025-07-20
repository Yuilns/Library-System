# Library-System
java实训课程成果——图书馆管理系统

## 🌟 特性
- 核心功能 1
    为图书管理员提供方便的管理系统，包括读者信息的查询（Select）、录入（Insert）、更新（Update）、删除（Delete）；管理员信息的录入（Insert）、更新（Update）、删除（Delete）；管理员信息注册（Register）；以及高级管理员与普通员工的聊天系统（Socket）
- 创新点 2
    进入图书管理系统后，左上角会呈现您的ID，如果你是高级管理员则会增加“【管理员】”前缀
- 技术亮点 3
    管理员分为“高级管理员”和“普通员工”。当登录的账号为高级管理员时，Management界面右下角会出现“内部人员管理”，在此界面你可以删改查所有员工的信息，你甚至可以在里面为员工“升职”或为管理员“降职”！

## 🚀 快速开始
### 前置条件
- Java JDK 1.8+
- IntelliJ IDEA Ultimate Edition 2023.1+ / IntelliJ IDEA Community Edition 2023.1+

### 安装
```bash
# 克隆仓库
git clone https://github.com/Yuilns/Library-System.git

# 安装依赖
在IntelliJ IDEA中添加Maven依赖

##📂 项目结构
Library-System/
├── .idea/          # 配置文件目录
├── Library/        # 源代码目录
├── out/            # 编译输出目录
├── pom.xml         # Maven项目配置文件
└── README.md       # 项目说明
