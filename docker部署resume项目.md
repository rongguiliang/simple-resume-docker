## docker部署

### 一、环境准备

准备一台服务器，2核心2线程，4G的服务器；

我们一般有了新的项目要部署就会申请服务器，一般我们拿到一台新的服务器都要对这个服务器进行一个初始化，比如IP的配置，防火墙的配置，还有账号密码的设置等，

##### 1.配置yum仓库/yum源

```bash
yum reinstall wget
cd /etc/yum.repos.d/
wget -O /etc/yum.repos.d/CentOS-Base.repo https://mirrors.aliyun.com/repo/Centos-7.repo
wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-7.repo


配置yum源的步骤
1.备份原有的yum源
mv CentOS-Base.repo CentOS-Base.repo.bak
2.配置yum源，找到阿里云的官方镜像源地址 https://developer.aliyun.com/mirror/
3.下载centos7的yum源
wget -O /etc/yum.repos.d/CentOS-Base.repo https://mirrors.aliyun.com/repo/Centos-7.repo
4.清空旧的yum缓存
yum clean all
5.生成新的yum仓库缓存
yum makecache
6.配置一个第三方的 额外仓库源 （epel源），这个源的作用是，如果阿里云源找不到这个软件，就在这里找
wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-7.repo
7.yum文件详情
[base]	源名称
name=CentOS-$releasever - Base	描述
mirrorlist=http://mirrorlist.centos.org/?release=$releasever&arch=$basearch&repo=os&infra=$infra
#baseurl=http://mirror.centos.org/centos/$releasever/os/$basearch/
gpgcheck=1	开关
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7
```



##### 2.我这里搞了一个简单的初始化脚本   

```bash
#!/bin/bash
 # 设置要分配的IP地址、子网掩码和默认网关
echo '正在关闭防火墙'
systemctl stop firewalld && systemctl disable firewalld
echo '正在关闭networkmanager'
systemctl stop NetworkManager && systemctl disable NetworkManager
echo '正在关闭selinux'
setenforce 0 && sed -ri 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/selinux/config
echo '正在配置网卡'
ip_address="192.168.88.130"
subnet_mask="255.255.255.0"
default_gateway="192.168.88.2"
networkfile=/etc/sysconfig/network-scripts/ifcfg-ens32
if [ -f "$networkfile" ]; then
    rm "$networkfile"
fi
# 编写ifcfg-ens32文件并将其复制到相应目录中
cat >> /etc/sysconfig/network-scripts/ifcfg-ens32 <<EOF
TYPE="Ethernet"
PROXY_METHOD="none"
BROWSER_ONLY="no"
BOOTPROTO="static"
DEFROUTE="yes"
IPV4_FAILURE_FATAL="yes"
IPV6INIT="yes"
IPV6_AUTOCONF="yes"
IPV6_DEFROUTE="yes"
IPV6_FAILURE_FATAL="no"
IPV6_ADDR_GEN_MODE="stable-privacy"
NAME="ens32"
UUID="21c14d56-bd0a-a661-e4ba-8ab10aefd4cf"
DEVICE="ens32"
ONBOOT="yes"
IPADDR=$ip_address
NETMASK=$subnet_mask
GATEWAY=$default_gateway
DNS1=114.114.114.114
DNS2=223.5.5.5
EOF

echo '正在安装基础软件包'
yum -y install wget lrzsz unzip gzip telnet
echo '配置基础环境变量'
cat >> /etc/profile <<EOF
if [ $(id -u) == 0 ]; then
export PS1='[\[\e[31;1m\]\u\[\e[m\]@\h \w]# '
else
export PS1='[\[\e[34;1m\]\u\[\e[m\]@\h \w]$ '
fi
alias ll='ls -lh --color=auto'
alias grep='grep --color'
alias free='free -m'
alias df='df -h'
alias du='du -h'
alias vi='vim'
EOF
cat >> ~/.vimrc <<EOF
"设置高亮
syntax on
"显示行号
set number
"自动缩进功能
set autoindent
"启用鼠标支持
"set mouse=a
"vim新打开.sh文件自动添加一下内容
autocmd BufNewFile *.sh exec ":call SetComment()"
func SetComment()
     if expand("%:e")=='sh'
         call setline(1,'#!/bin/bash'iii)
         call setline(2,'#Author:     xxx')
         call setline(3,'#Create time:'.strftime("%Y-%m-%d"))
     endif
endfunc
EOF
source /etc/profile
systemctl restart network
echo '脚本执行完毕'

```

没有执行权限，给他一个执行权限

```
chmod +x init.sh
```

### 





### 二.docker安装

环境：CentOS Linux release 7.7.1908 (Core)/版本：3.10.0-1062.el7.x86_64

##### 1.卸载旧的版本

```bash
yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```

##### 2.安装需要的包

```bash
yum install -y yum-utils
```

##### 3.设置镜像仓库

```bash
yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

##### 4.更新yum软件包索引

```bash
yum makecache fast
```

##### 5.安装docker

```bash
yum -y install docker-ce docker-ce-cli containerd.io
yum -y install docker-ce-20.10.* docker-ce-cli-20.10.* containerd.io
docker-ce	#社区版
#安装完成后验证
docker version
```



阿里云镜像加速

```
mkdir -p /etc/docker

tee /etc/docker/daemon.json <<-'EOF'
{
  	"registry-mirrors": [ 
	"http://hub-mirror.c.163.com",
    "https://docker.m.daocloud.io",
    "https://dockerproxy.com",
    "https://docker.mirrors.ustc.edu.cn",
    "https://docker.nju.edu.cn"]
}
EOF

systemctl daemon-reload
systemctl restart docker
```

##### 6.启动docker

```bash
systemctl start docker
```

##### 7.运行 hello-world进行验证

```bash
docker run hello-world
```

##### 8.查看运行的hello-world

```bash
docker images
```

##### 9.卸载docker

```bash
1.删除安装包
yum remove docker-ce docker-ce-cli containerd.io
2.删除镜像、容器、配置文件等内容
rm -rf /var/lib/docker
```

##### 



### 三、数据库准备

![image-20260624103000392](C:\Users\31548\AppData\Roaming\Typora\typora-user-images\image-20260624103000392.png)

##### 1.mysql基础配置

```
mkdir /opt/mysql -p

mkdir /opt/mysql/data -p
cd /opt/mysql/data
mkdir mysql

mkdir /opt/mysql/etc -p
cd /opt/mysql/etc 
mkdir my.cnf

#客户端设置
[client]

#mysql服务配置
[mysqld]

skip-grant-tables

#服务器监听端口，默认为3306
port=3306
#数据存储位置
datadir=/var/lib/mysql
#Unix套接字文件路径
socket=/var/lib/mysql/mysql.sock
#MySQL服务唯一标识,开启二进制日志，做主从时需要
server-id=1
log-bin=mysql-bin
#不会跟随符号链接
symbolic-links=0
#日志路径
log-error=/var/log/mysqld.log
#服务进程pid文件路径
pid-file=/var/run/mysqld/mysqld.pid
#mysql字符集设置
character_set_server=utf8mb4
#mysql比较规则设置
collation_server=utf8mb4_general_ci
```



##### 2.compose文件配置

```
cd /opt/mysql
vim docker-compose.yml

#编写docker-compose.yml
version: '2.1'
services:
  mysql:
    image: mysql:5.7
    container_name: mysql
    restart: always
    expose:
      - 3306
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: "Admin123." #mysql密码
      TZ: Asia/Shanghai
    volumes:
      - /opt/mysql/data/mysql:/var/lib/mysql
      - /opt/mysql/etc/my.cnf:/etc/my.cnf
    command: --default-authentication-plugin=mysql_native_password  #解决外部无法访问
networks:
  default:
    external:
      name: common-net

```



配置完成后执行

```
docker pull mysql:5.7
docker-compose up -d 

```



##### 3.连接navicat

![image-20260622213827541](C:\Users\31548\AppData\Roaming\Typora\typora-user-images\image-20260622213827541.png)

```
mkdir /opt/web/resume -p &&  cd  /opt/web/resume

```





### 四、jdk

##### 1.编写dockerfile制作jdk镜像

```
FROM openjdk:8-jdk-alpine 
MAINTAINER heber<heber@123.com> 
WORKDIR /app 
COPY ./*.jar /app/ 
COPY ./*.yml /app/ 
CMD java -jar /app/resume-0.0.1-SNAPSHOT.jar
```



##### 2.修改application-dev.yml文件中的地址

​       同 Docker 网络的容器之间，**容器名就是自带的内网域名**，直接写容器名是内网直连；写宿主机 IP 是跨层绕路访问，很容易被拦截拒绝连接。

​       同**自定义网桥**：写容器名`mysql`，完美互通；

​       分属**两个默认网桥**：写`mysql`域名无效，只能用宿主机 IP 绕路访问，稳定性差。

![image-20260624101512714](C:\Users\31548\AppData\Roaming\Typora\typora-user-images\image-20260624101512714.png)

之后执行

![image-20260623195507161](C:\Users\31548\AppData\Roaming\Typora\typora-user-images\image-20260623195507161.png)



##### 3.vim docker-compose.yml

![image-20260624101951883](C:\Users\31548\AppData\Roaming\Typora\typora-user-images\image-20260624101951883.png)

docker-compose up -d



可以成功运行

![image-20260624101407144](C:\Users\31548\AppData\Roaming\Typora\typora-user-images\image-20260624101407144.png)



问题：

可以通过docker logs -f 来排查错误