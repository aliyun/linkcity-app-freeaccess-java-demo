示例说明
==
本工程是一个标准的spring boot工程,页面使用thymeleaf模板技术,配置信息在src/main/resources/application.yml文件中；
作为示例，代码中使用Map暂存数据，没有使用数据库

运行说明
==
* 本工程可以直接导入到IDE中,也可以使用maven打成jar包直接运行
* 运行前需要修改src/main/resources/application.yml文件,将其中涉及到iot SDK 配置相关的部分改成自己的配置
（app.id,app.appkey,app.appsecret,app.host）,同时，app.redirectUrlTemplate、app.logoutRedirectUrl
域名部分需修改成对应城市平台的访问域名，示例(假设当前对接的城市平台访问域名是http://citylink.aliplus.com，对应的open API
访问host是api.citylink.aliplus.com)：
```
app:
  id: xxxxxxx
  appkey: xxxxxxx
  appsecret: xxxxxxx
  redirectUrlTemplate: http://citylink.aliplus.com/api/tac/authorize?responseType=code&clientId={0}&state={1}&redirectUrl={2}
  logoutRedirectUrl: http://citylink.aliplus.com/login/login.htm
  grantType: authorization_code
  host: api.citylink.aliplus.com
  role:
    admin: ADMIN
    normal: NORMAL
  apiVersion:
    device: 0.2.0
```
* 示例导入到IDE中后可以打开com.aliyun.iotx.city.demos.App类,这是一个启动类,里面有个标准的main方法,可以启动运行,或者命令行使用maven运行,命令如下:mvn clean spring-boot:run;如果需要打包,使用
如下maven命令: mvn clean package spring-boot:repackage
* 启动后访问端口默认为8080，如果直接在浏览器输入http://localhost:8080/index访问，会跳转到http://localhost:8080/login，需要登录；
如果是从城市平台点击应用，会免登并跳转到index页面