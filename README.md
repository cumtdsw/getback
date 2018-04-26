# getback
后端：spring + jpa
前端：bootstrap + ajax
开发语言：java、html、js
数据库：mysql
架构：ajax + rest
2017-12-10：create maven project
项目代码分包设计domain/dao/service/util/query/intercepot
项目集成spring + jpa
项目前端bootstrap + ajax
ajax实现用户登录验证
ajax实现用户状态监测
ajax实现table数据绑定
集成aspectj方法拦截，方法的执行开始和结束、方法执行时间（效率）
集成junit测试
集成quartz
test
/**************************规范******************/
DAO层接口命名方式
添加: persit
修改: update
删除： delete
查询: query



Service层接口命名方式
添加: add
修改: modify
删除： remove
查询: search
是否: is
开始: beigin
提交:commit

好处：规范，便于AOP

BaseDao除外

备忘：
项目中存在两个dataSource,一个是连接实体类和entityManger的dataSource(没有找到获取的配置，这个需要再研究）
还有一个是管理quartz集群需要的datasource(配置在spring-quartz.xml)中

集成redis(做缓存，参考com.dsw.getback.service.cache.imp.CacheServiceImp)

web.xml 配置注意项
<servlet-mapping>
	<servlet-name>dsw-dispatcher</servlet-name>
	<!-- 如果写成/*,当访问*.jsp时，会交给controller,找不到时会返回jsp的源码 ，当写成/时,不会匹配*.jsp-->
<!--如果写成/*,当访问*.jsp时，会交给controller,找不到时会返回jsp的源码 ，当写成/时,不会匹配*.jsp-->
	<url-pattern>/</url-pattern>
</servlet-mapping>
