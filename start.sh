cd /var/local/jdhelper/con
 #删除容器
  docker rm -f $(docker ps -a | grep jdhelperconsumer | awk '{print $1}')
  #删除镜像
  docker rmi jdhelperconsumer

  #打包镜像
  docker build -t jdhelperconsumer .

  #启动容器
  docker run -d --net=host --name=jdhelperconsumer jdhelperconsumer

  echo "$var启动完成"
  
  cd /var/local/jdhelper/pro
 #删除容器
  docker rm -f $(docker ps -a | grep jdhelperprovider | awk '{print $1}')
  #删除镜像
  docker rmi jdhelperprovider

  #打包镜像
  docker build -t jdhelperprovider .

  #启动容器
  docker run -d --net=host --name=jdhelperprovider jdhelperprovider

  echo "$var启动完成"