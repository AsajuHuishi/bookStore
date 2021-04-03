# 简介
这是一个JavaWeb书城项目。
[参考连接](https://www.bilibili.com/video/BV1Y7411K7zz?p=6&spm_id_from=pageDriver)

# 环境
<ul>
<li>JDK 1.8</li>
<li>Eclipse 2020</li>
<li>Tomcat 8.0</li>
<li>MySQL 5.5.15
</ul>

# 功能
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210403161457400.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2OTM3Njg0,size_16,color_FFFFFF,t_70)
# 目录结构
```bash
├── Readme.md                   // help
├── jdbc.properties             // 数据库配置信息
├── src        
│   ├── indi.huishi.dao         // 持久层
│   ├── indi.huishi.filter      // 过滤器
│   ├── indi.huishi.pojo      	// 实体类
│   ├── indi.huishi.service     // service层
│   ├── indi.huishi.test     	// 测试类
│   ├── indi.huishi.utils      	// 工具类
│   └── indi.huishi.web      	// web层
├── web
│   ├── pages                   // 页面 
│   ├── static                  // css/img/script
│   └── WEB-INF 			
│   	└── lib			// jar包
│       └── web.xml 	        // web工程配置
```