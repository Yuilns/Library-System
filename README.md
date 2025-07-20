# Library-System
Java实训课程成果——图书馆管理系统  
[![Java Version](https://img.shields.io/badge/Java-8%2B-orange?logo=java)](https://java.com) 
[![Maven Version](https://img.shields.io/badge/Maven-3.6%2B-blue?logo=apachemaven)](https://maven.apache.org)
[![MySQL Version](https://img.shields.io/badge/MySQL-5.7%2B-green?logo=mysql)](https://www.mysql.com)
[![License](https://img.shields.io/badge/License-MIT-red)](LICENSE)

> 为图书管理员设计的高效管理系统，支持多级权限控制和实时通讯功能

## 🌟 核心特性
- **权限分级管理**  
  区分「高级管理员」与「普通员工」，高级管理员可管理员工信息及权限升降
- **实时通讯系统**  
  内置Socket聊天功能，方便管理员之间即时沟通
- **完整CRUD操作**  
  支持读者/管理员信息的增删改查（Select/Insert/Update/Delete）
- **智能身份标识**  
  登录后界面左上角动态显示用户ID及权限状态（高级管理员显示`【管理员】`前缀）

## 🚀 快速开始
### 前置条件
- Java JDK 1.8+
- MySQL 5.7+（或兼容数据库）
- Maven 3.6+

### 安装与运行
# 1. 克隆仓库
```bash
git clone https://github.com/Yuilns/Library-System.git
```
# 2. 导入IntelliJ IDEA
File > Open > 选择项目目录

# 3. Maven依赖安装（自动）
IDEA会自动加载pom.xml中的依赖

# 4. 数据库配置
修改 src/main/resources/db.properties 中的数据库连接信息

# 5. 运行主类
Library/src/main/java/com/library/Login.java

## 📂 项目结构
```plaintext
Library-System/
├── .idea            # IDEA 项目配置文件夹
├── Library
│   └── src          # 源文件
│       └── main
│           ├── java
│           │   ├── com.yuilns
│           │   │   ├── mapper            # MyBatis 的 Mapper 接口文件夹
│           │   │   └── pojo              # 实体类文件夹
│           │   └── org.example           # 工具、功能类文件夹
│           └── resources
│               ├── com.yuilns.mapper     # MyBatis 的 Mapper XML 文件文件夹
│               ├── logback.xml           # Logback 日志配置文件
│               └── mybatis-config.xml    # MyBatis 配置文件
├── out             # 输出文件夹
├── .gitignore      # Git 忽略文件
├── pom.xml         # Maven 配置文件
└── README.md       # 项目说明文件
```

## 🧪 测试账号
| 权限级别       | 用户名   | 密码        |
|---------------|----------|------------|
| 高级管理员     | 11520    | 123456     |
| 普通员工       | 199806   | 123213     |

## ❓ 常见问题
### Q：如何创建新员工账户？
A：在登录Login页面点击 “注册” 按钮即可创建新员工账号

### Q：为什么看不到聊天功能？
A：确保所有用户在同一局域网，且防火墙允许8080端口通信

### Q：数据库连接失败怎么办？
A：检查`db.properties`中的配置，确保MySQL服务已启动

## 🤝 参与贡献
欢迎提交Pull Request改进系统！贡献前请遵循以下流程规范：

- **分支命名**：`feature/功能名`（如`feature/books-search`）或`fix/问题描述`（如`fix/login-error`）

- **代码风格**：
  - 类名使用帕斯卡命名法（如`BookManager`）
  - 方法名使用驼峰命名法（如`getBookById`）
  - 提交前执行`mvn checkstyle:check`检查格式

- **提交信息格式**：
  ```plaintext
  [模块名] 描述内容  
  例：[登录模块] 修复验证码过期不刷新问题
  ```
### PR 流程：
1. Fork 本仓库到个人账号  
2. 创建分支并完成开发（分支命名需遵循规范）  
3. 提交 PR 到 `main` 分支，描述修改内容（建议说明实现逻辑、测试情况等）
   
<details>
<summary>点击展开查看查看 PR 流程（展开/折叠）</summary>

1. **Fork 本项目**  
   [点击右上角 Fork 按钮](https://github.com/Yuilns/Library-System/fork)，将仓库复制到个人账号下

2. **创建新分支**  
   克隆仓库到本地后，通过命令创建符合规范的分支：  
   ```bash
   # 克隆 Fork 后的个人仓库
   git clone https://github.com/你的用户名/Library-System.git
   cd Library-System
   
   # 创建并切换到新分支（功能开发用feature/前缀，修复问题用fix/前缀）
   git checkout -b feature/books-search  # 示例：图书搜索功能
   # 或
   git checkout -b fix/login-error       # 示例：修复登录错误
   ```

3. **提交更改**  
   完成开发后，按规范提交代码：  
   ```bash
   # 添加所有修改文件
   git add .
   
   # 提交信息格式：[模块名] 描述内容
   git commit -m '[图书模块] 新增ISBNISBN搜索功能'
   ```

4. **推送分支到远程**  
   ```bash
   git push origin feature/books-search  # 推送分支到个人Fork仓库
   ```

5. **创建 Pull Request**  
   1. 进入个人个人Fork的仓库页面  
   2. 点击"Compare & pull request"按钮  
   3. 填写PR描述（建议说明实现逻辑、测试情况、关联的Issue等）  
   4. 选择目标分支为原仓库的`main`分支，点击"Create pull request"
</details>

## 📜 许可证
MIT License © 2025 Yuilns
