S0C：年轻代中第一个survivor（幸存区）的容量 (kb)
S1C：年轻代中第二个survivor（幸存区）的容量 (kb)
S0U：年轻代中第一个survivor（幸存区）目前已使用空间 (kb)
S1U：年轻代中第二个survivor（幸存区）目前已使用空间 (kb)
EC：年轻代中Eden（伊甸园）的容量 (kb)
EU：年轻代中Eden（伊甸园）目前已使用空间 (kb)
OC：Old代的容量 (kb)
OU：Old代目前已使用空间 (kb)
PC：Perm(持久代)的容量 (kb)
PU：Perm(持久代)目前已使用空间 (kb)
YGC：从应用程序启动到采样时年轻代中gc次数
YGCT：从应用程序启动到采样时年轻代中gc所用时间(s)
FGC：从应用程序启动到采样时old代(全gc)gc次数
FGCT：从应用程序启动到采样时old代(全gc)gc所用时间(s)
GCT：从应用程序启动到采样时gc用的总时间(s)
NGCMN：年轻代(young)中初始化(最小)的大小 (kb)
NGCMX：年轻代(young)的最大容量 (kb)
NGC：年轻代(young)中当前的容量 (kb)
OGCMN：old代中初始化(最小)的大小 (kb)
OGCMX：old代的最大容量 (kb)
OGC：old代当前新生成的容量 (kb)
PGCMN：perm代中初始化(最小)的大小 (kb)
PGCMX：perm代的最大容量 (kb)
PGC：perm代当前新生成的容量 (kb)
S0：年轻代中第一个survivor（幸存区）已使用的占当前容量百分比
S1：年轻代中第二个survivor（幸存区）已使用的占当前容量百分比
E：年轻代中Eden（伊甸园）已使用的占当前容量百分比
O：old代已使用的占当前容量百分比
P：perm代已使用的占当前容量百分比
S0CMX：年轻代中第一个survivor（幸存区）的最大容量 (kb)
S1CMX ：年轻代中第二个survivor（幸存区）的最大容量 (kb)
ECMX：年轻代中Eden（伊甸园）的最大容量 (kb)
DSS：当前需要survivor（幸存区）的容量 (kb)（Eden区已满）
TT： 持有次数限制
MTT ： 最大持有次数限制





jstat是jvm最常用的命令之一，下面针对常用的几个进行整理。

1、 jstat -gcutil pid
统计gc信息统计。

[root@AY140330215454793e81Z ~]# jstat -gcutil 5801
  S0     S1     E      O      P     YGC     YGCT    FGC    FGCT     GCT
  0.00  97.37   5.54  53.37  69.83     21    0.366     1    0.480    0.846
2、 jstat -gc pid
可以显示gc的信息，查看gc的次数，及时间。其中最后五项，分别是young gc的次数，young gc的时间，full gc的次数，full gc的时间，gc的总时间。

[root@AY140330215454793e81Z ~]# jstat -gc 5801
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       PC     PU    YGC     YGCT    FGC    FGCT     GCT
15360.0 12288.0  0.0   11964.6 298496.0 16530.5   67072.0    35793.4   83968.0 58633.2     21    0.366   1      0.480    0.846
3、 jstat -gccapacity pid
可以显示，VM内存中三代（young,old,perm）对象的使用和占用大小，如：PGCMN显示的是最小perm的内存使用量，PGCMX显示的是perm的内存最大使用量，PGC是当前新生成的perm内存占用量，PC是但前perm内存占用量。其他的可以根据这个类推， OC是old内纯的占用量。

[root@AY140330215454793e81Z ~]# jstat -gccapacity 5801
 NGCMN    NGCMX     NGC     S0C   S1C       EC      OGCMN      OGCMX       OGC         OC      PGCMN    PGCMX     PGC       PC     YGC    FGC
 20480.0 327168.0 327168.0 15360.0 12288.0 298496.0    40448.0   653824.0    67072.0    67072.0  21504.0  83968.0  83968.0  83968.0     21     1
4、jstat -gcnew pid
年轻代对象的信息。

[root@AY140330215454793e81Z ~]# jstat -gcnew 5801
 S0C    S1C    S0U    S1U   TT MTT  DSS      EC       EU     YGC     YGCT
15360.0 12288.0    0.0 11964.6  3  15 15360.0 298496.0  16563.7     21    0.366
5、jstat -gcnewcapacity pid
年轻代对象的信息及其占用量。

[root@AY140330215454793e81Z ~]# jstat -gcnewcapacity 5801
  NGCMN      NGCMX       NGC      S0CMX     S0C     S1CMX     S1C       ECMX        EC      YGC   FGC
   20480.0   327168.0   327168.0 109056.0  15360.0 109056.0  12288.0   326144.0   298496.0    21     1
6、jstat -gcold pid
old代对象的信息。

[root@AY140330215454793e81Z ~]# jstat -gcold 5801
   PC       PU        OC          OU       YGC    FGC    FGCT     GCT
 83968.0  58639.1     67072.0     35793.4     21     1    0.480    0.846
7、jstat -gcoldcapacity pid
old代对象的信息及其占用量。

[root@AY140330215454793e81Z ~]# jstat -gcoldcapacity 5801
   OGCMN       OGCMX        OGC         OC       YGC   FGC    FGCT     GCT
    40448.0    653824.0     67072.0     67072.0    21     1    0.480    0.846
8、jstat -gcpermcapacity pid
perm对象的信息及其占用量。

[root@AY140330215454793e81Z ~]# jstat -gcpermcapacity 5801
  PGCMN      PGCMX       PGC         PC      YGC   FGC    FGCT     GCT
   21504.0    83968.0    83968.0    83968.0    21     1    0.480    0.846
9、jstat -class pid
显示加载class的数量，及所占空间等信息。

[root@AY140330215454793e81Z ~]# jstat -class 5801
Loaded  Bytes  Unloaded  Bytes     Time
 10924 20744.5        0     0.0      13.11
10、jstat -compiler pid
显示VM实时编译的数量等信息。

[root@AY140330215454793e81Z ~]# jstat -compiler 5801
Compiled Failed Invalid   Time   FailedType FailedMethod
    1452      2       0    54.32          1 java/net/URL openConnection
11、jstat -printcompilation pid
当前VM执行的信息。

[root@AY140330215454793e81Z ~]# jstat -printcompilation 5801
Compiled  Size  Type Method
    1453     13    1 java/util/concurrent/atomic/AtomicBoolean get