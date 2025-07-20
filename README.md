# Library-System
Java实训课程成果——图书馆管理系统  
[![Java Version](https://img.shields.io/badge/Java-8%2B-orange?logo=java)](https://java.com) 
[![License](https://img.shields.io/badge/License-MIT-blue)](LICENSE)

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
```bash
# 1. 克隆仓库
git clone https://github.com/Yuilns/Library-System.git

# 2. 导入IntelliJ IDEA
File > Open > 选择项目目录

# 3. Maven依赖安装（自动）
IDEA会自动加载pom.xml中的依赖

# 4. 数据库配置
修改 src/main/resources/db.properties 中的数据库连接信息

# 5. 运行主类
Library/src/main/java/com/library/Login.java

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
欢迎提交Pull Request！请遵循以下流程：

1. **Fork本项目**  
   [点击右上角Fork按钮](https://github.com/Yuilns/Library-System/fork)

2. **创建新分支**  
   ```bash
   git checkout -b feature/your-feature

3. **提交更改**  
   ```bash
   git commit -m 'Add some feature'

4. **推送分支**  
   ```bash
   git push origin feature/your-feature

5. **创建Pull Request**  
   在GitHub仓库页面点击"New Pull Request"

## 📜 许可证
MIT License © 2023 Yuilns
