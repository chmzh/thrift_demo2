服务器地址 192.168.22.189
用户名 www 密码 www

1、先更新项目
svn update
2、编译thrift文件
thrift -r --gen java service.thrift
3、提交文件
svn commit -m ""