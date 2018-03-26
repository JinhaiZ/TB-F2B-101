# Distributed Algorithmes

book Michel Raynal <Algorithmes distribués et protocoles>

## First point 

- Exclusion mutuelle: partage de ressource
- Election, besoin un coordonateure
- termination, programme classique, se termine quand il y un return, programme distibue, ensemble programme
- deadlock/live lock, mal synchronization

## E.M

Section critique -> utiliser la ressource

TB洗澡例子：１层楼１０个学生只有一个浴室，大家在洗澡的时候都希望

proprites:

1. unicite de l'utilisateur
2. pas de "famine" (starvation) (equitable) le ressource est libre, il y des demandeur -> elle attribue
3. pas d'interblocage
4. resolution des conflits en temps fini

L'E.M est de base dans les O.S / Programmation concurrent

S.C section critique

utilisation des E.M (prelude -> S.C -> postlude)

Solution
 
1. bloquer/debloquer les interruption
2. verrouillage/deverrouillage
3. Semaphore　用浴室的人洗完澡给等待的人发送ＳＭＳ通知等待人可以去浴室了
4. Synchronize

上述方法应用与memoire partagee (mono processor)系统
上述解决方案的关系是confort de programmation递增，程序员使用同步或者信号量方法，其实都在使用锁，锁又会产生中断

## Nouvelle classe d'applis pour l'E.M en distibue

- algo "centralise":
- approche distibue:
    - solution a jeton
    - solution sans jeton

### alog centralise

```
                                  --------
-----------    --------------    /|user 1|
|ressource| <- |gestionnaire|　<- --------
-----------    |de ressource|    \|user 2|
               --------------     --------
```

3 type de message

1. user send "demande" to resource manager
2. manger send "OK" to user
3. user send "lib" to resource manager

```
send "demande" \
                 --> prelude
wait for "ok"  /

SC

send "lib" --> postlude

```

浴室管理员：想洗澡的学生问管理员可以洗澡不，如果没人用浴室，管理员让学生进入浴室，学生用完浴室告诉管理员自己不再使用。如果有人用浴室，管理员登记该学生的联系方式（管理员管理file d'attent），等浴室再次无人使用时再通知学生，学生在等待期间可以去干其他事情。

该方法的问题：如果管理员en panne，整个系统就出问题了，因为管理员在整个系统中扮演特殊角色joue un role special

complexite: 3 longeur de message

### solution distribue

#### solution a jeton

jeton = privilege 谁有jeton谁有权使用资源

```
demande jeton \
                --> prelude
attent jeton  /

SC

liberer (jeton) et attente de demandeur  --> postlude

```

该方法的问题: 没有人在系统里扮演特殊角色，也就是说没有人知道有谁想要得到资源的所有人的名单（拓扑结构）

解决方法:　使用特殊拓扑结构，比如环形

```

           ## jeton 
           %%%    %%%
      %%%   0      1   %%%

  %%%                    2  %%%

 %%%                      3   %%%

 %%%                       4  %%%

 %%%                     5   %%%

    %%%                  %%%

          %%%     %%%
```

```
demande jeton \
                --> prelude
attente jeton /

SC

envoi (jeton) au sucesseur  --> postlude

```

使用jeton方法要注意的问题:

- 底层网络要保证没有duplication，不然网络上会出现两个jeton
    - 为什么两个jeton的情况会发生?
    - 发送jeton时假丢包，发送者重新发送jeton
- 环形拓扑若有节点断开怎么办？
    - 等待时加timeout
    - timeout时间如何确定 (n-1) x utilisation
    - 如果timeout发生，资源申请者可以推测出以下情况并作出相应反应
        - jeton丢了perdu -> 申明jeton丢失，并重新产生jeton
        - jeton在发送中transmission ->　检查tuyau中没有jeton
    - jeton丢了后如何产生jeton?
    - 大家选举出一个节点，并由这个节点（特殊节点）重新生产jeton，需要注意，如果没有选举过程，大家都可以重新生产jeton的话，会遇到duplication情况

Complexite: O(n/2) 得到jeton的期望是n/2

Equitable ? Oui

#### jeton valué

Jeton + valeur entiere

每传送一次jeton，valuer加1，这样jeton丢失时就知道jeton在哪里丢失的

### Solution sans jeton

L.Lamport 78

Comment determiner la situation E.M ?

Codition boolean, predicat

qui peut indiquer si j'ai ou non le droit d'acceder a l'E.M ?

Algo **horloge logique de Lamport**

Sur 1 ordinateur: 2 evenements (2 executions d'instructions) soient compa

- i1 est executee a t1
- i2 est executee a t2

i1 a eu lieu avant le i2 ssi t1 < t2

En System Distribue

on veut comparer les evenements, Lamport propose d'utiliser l'Horloge logique (un conter). Horloge est pour separer les evenements

on utilise MSC Msg Chart

```

      H0++
H0=0  Env              H0=100  Env
M0-----+------------------------+-------------> Reception
                                 \
                                  \ msg 100
H1=0                               \ H1=101   H1=max(H1,H2)+1
M1----------------------------------+---------+--------->
                                             /
                                            / msg 1
H2=0                                       / H2=1
M2----------------------------------------+----->
```

Sur 2 ordinateur: 2 evenements diff

- Ei qui a lieu sur le Si a Hi
- Ej qui a lieu sur le Sj a Hj

重点Regle de Lamport : Ei preces Ej ssi Hi < Hj ou (Hi=Hj et i < j) 定义 i < j 是arbitaire 

System transactionelle

```
  / --->----- \
 /             \
T1(H1)          T2(H2) 
 \             /
  \ ----<-----/
```

Quand Dead lock
tuer le jeune, tuer le transaction plus recent, ce qui a fait le moindre chose

Horloge vectorielle (ou matrix)

```
    Hi          Hj
H0|----|    H0|----|
  |----|      |----|
  |----|      |----|
  |----|      |----|
  |----|      |----|
Hn|----|    Hn|----|
```

Algo de Lamport

- mission: ...
- donner privilege (access au E.M) au site plus ancienne

Fil d'attent centralise FIFO

demander acceder au section critique

diffuser msg au tous les sites dans le reseaux

chaque site mise a jour leur fil d'attent et envoie un msg ack au demandeur

```
prelude
-------
Hi++
envoi(Req, Hi, id=i)
attente aue ma req soit la plus ancienne

postlude
-------
Hi++
envoi(Lib, Hi, id=i)
diff
```

Complexite: 3(n-1)　复杂度也就是发送信息个数，计算过程如下

Req (n-1)
Ack (n-1)
Lib (n-1)

有没有可能减小信息发送的个数呢?
可以减小Ack信息的发送次数，当发送Ack的machine比要接收Ack的machine更老时，老machine没必要向年轻的machine发送Ack，反正老machine有优先权

Lamport老爷子的逻辑时间来源于下面这个paper　

[*Time, Clocks, and the Ordering of Events in a Distributed System*](https://www.microsoft.com/en-us/research/publication/time-clocks-ordering-events-distributed-system/?from=http%3A%2F%2Fresearch.microsoft.com%2Fen-us%2Fum%2Fpeople%2Flamport%2Fpubs%2Ftime-clocks.pdf)

下面几个博客文章也很有意思

- [事件和时间：Time, Clocks, and the Ordering of Events in a Distributed System 读后感](http://www.d-kai.me/%E4%BA%8B%E4%BB%B6%E5%92%8C%E6%97%B6%E9%97%B4%EF%BC%9Atime-clocks-and-the-ordering-of-events-in-a-distributed-system-%E8%AF%BB%E5%90%8E%E6%84%9F/)
- [记2013年图灵奖得主、微软科学家莱斯利·兰伯特](http://www.52cs.org/?p=136)
