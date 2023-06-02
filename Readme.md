Copyright 2023 Ángel Martínez Espina
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

# Heart Disease Predictor #


## Propósito de este Software ##
El propósito de este sistema basado en el conocimiento (SBC) es la identificación de pacientes con mayor disposición a padecer eventos cardiovasculares (infarto de miocardio, ictus, insuficiencia cardiaca, muerte súbita etc.) en función de los síntomas que presente y sus características personales. 

El SBC se ha encapsulado en una API REST para gestionar las consultas HTTP lanzadas por los clientes. Estas consultas incluirán la siguiente información:
1. Edad [años]
2. Sexo [M o F]
4. Presión arterial en reposo [mm Hg]
5. Colesterol [mm/dl]
6. Azúcar en ayunas
7. MaxHR: frecuencia cardíaca máxima alcanzada [60-202]


De este modo, el servvicio responderá en formato JSON los datos aportados por el cliente y un nuevo atributo llamado "enfermedad cardíaca". Si su valor es igual a "1" signfiica que el paciente tiene mayor disposicición a padecer eventos cardíacos adversos. De lo contrario, el resultado será un "0". 

## Índice de la Guía ##
Esta guía cuenta con tres secciones: en primer lugar, se adjunta que software se debe tener instalado para ejecutar el microservicio y unos enlaces que explican cómo realizar la instalación, posteriormente se explica qué acciones hay que llevar a cabo para montar el microsrevicio en un host, y por último se indicará la sintaxis correcta de las consultas. 

## Software Requerido ##
Para montar el microservicio en un host propio es necesario que este tenga instalado *Java Development Kit* v.17 o superior y *Apache Maven*. La instalación de *Docker* es opcional, ya que el microservicio se puede ejecutar a partir del fichero *.jar*. 

En un **sistema Linux**  se recomienda llevar a cabo la instalación del JDK v.17 y Maven desde el terminal.  En el caso de Ubuntu, es necesario ejecutar los siguientes comandos: 

```console
sudo apt update
```

sudo apt install openjdk-17-jdk openjdk-17-jre

sudo apt install maven
```

La instalación de Docker es más compleja ya que es probable que el paquete de instalación contenido en el repositorio oficial de Ubuntu no contenga la versión más reciente. Por ello se recomienda llevar a cabo los pasos de la siguiente guía:

[Guía Instalación Docker en Linux](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04-es)

Para su instalación en **Windows**, se recomienda utilizar la interfaz gráfica. A continuación, se adjuntan los tres vínculos a las páginas oficiales para descargar los instaladores: 

**Maven:**
[Centro de descargas Maven](https://maven.apache.org/download.cgi)

**JDK v.17:**
[Centro de descargas JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)


**Docker Engine:**
[Centro de descargas Docker Engine](https://docs.docker.com/engine/install/)

En **ambos sistemas operativos** se puede comprobar su instalación. Para ello se ejecuta desde el terminal o desde el cmd los siguientes comandos:

```console
java -version

mvn -version

docker --version

```


## Montaje del microservicio (sin Docker) ##
En este caso, las acciones a llevar a cabo son: abrir un terminal y clonar el repositorio que lo contiene. De esta forma, los comandos a ejecutar serían:  

```console
git clone https://github.com/amartespina/heartfailurepredictor.git
```

Si se está utilizando un **sistema Linux**, se accede al directorio creado y se ejecuta el script linuxAutorun.sh: 

`cd heartfailurepredictor`

`sh linuxAutorun.sh`

Si el sistema operativo es **Windows**, se accede al directorio creado a través de la interfaz gráfica y se ejecuta el script windowsAutorun. Para ello, se hace clic derecho sobre él y se presiona la opción “Ejecutar con PowerShell”.


Tras ejecutar este comando, se verá desde la consola que la aplicación de Spring Boot comienza a iniciarse e indicará el puerto y el protocolo en el que se ha montado el microservicio. Actualmente está configurado para que se lance en el puerto 80.


## Montaje del microservicio (con Docker) ##

En primer lugar, es necesario iniciar *Docker Daemon*. En un **sistema Windows** es posible hacerlo desde la interfaz gráfica abriendo el programa Docker Desktop. En un **sistema Linux** se ejecuta desde el terminal: 

`sudo systemctl start docker`

Cuando el repositorio termine de clonarse, se accede al directorio que se haya creado.  Posteriormente se construye la imagen Docker, indicando con el punto final que el Dockerfile se encuentra en el directorio desde el que se ejecuta el comando. Por último, se ejecuta la imagen creada previamente enlazando el puerto 80 de la máquina host con el puerto 80 del contenedor. Los comandos para ejecutar son: 

`git clone https://github.com/amartespina/heartfailurepredictor.git`

`cd heartfailurepredictor`

`mvn package`

`docker build -t “imagenmyheartfailurepredictor” .`

`docker run -p 80:80 -d “imagenmyheartfailurepredictor”`

## Lanzamientos de consultas al microservicio (montado sobre el host propio) ##

Es posible lanzar consultas al microservicio desde el navegador o desde el terminal. En el primer caso, la consulta se introduce en la barra de búsqueda sustituyendo los valores dados por aquellos propios del usuario: 

`localhost:80/?edad=40&sexo=M&presArtReposo=140&colesterol=289&glucemiaAyunas=0&frecuenciaCardiacaMax=172`

Si se opta por utilizar el terminal, es necesario utilizar el comando curl e introducir la consulta entre comillas. Por ejemplo:

`curl “localhost:80/?edad=40&sexo=M&presArtReposo=140&colesterol=289&glucemiaAyunas=0&frecuenciaCardiacaMax=172”`

## Lanzamiento de consultas al microservicio (montado en un servidor ajeno)  ##
El microservicio admite consultas realizadas desde el terminal o desde el navegador. A continuación, se muestra una consulta lanzada desde el navegador: 

`http://myheartfailurepredictor.es/?edad=40&sexo=M&presArtReposo=140&colesterol=289&glucemiaAyunas=0&frecuenciaCardiacaMax=172`

De lo contrario, para lanzar la petición desde el terminal la consulta debe de ir entre comillas y precedida por el comando curl. Por ejemplo: 

`curl “http://myheartfailurepredictor.es/?edad=40&sexo=M&presArtReposo=140&colesterol=289&glucemiaAyunas=0&frecuenciaCardiacaMax=172”`

