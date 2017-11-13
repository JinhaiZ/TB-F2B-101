## RPC Remote Procedure Call

PRC是面向程序的(orientée application)，不像Chat while watching是面向协议的。

远程过程调用详细解释 https://waylau.com/remote-procedure-calls/

幂等操作 idempotent

> 在编程中一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同。幂等函数，或幂等方法，是指可以使用相同参数重复执行，并能获得相同结果的函数。这些函数不会影响系统状态，也不用担心重复执行会对系统造成改变。例如，“setTrue()”函数就是一个幂等函数,无论多次执行，其结果都是一样的.更复杂的操作幂等保证是利用唯一交易号(流水号)实现. Source: baike.baidu

## 分布式系统的拓扑结构

 - 杂乱型
 - 星形
 - 环形
 - 树形
 - 完整网格 maillage complet
 - 结合型

最好的拓扑结构？

超方形拓扑(hypercude topology)也许是最好的拓扑结构，其特性是当主机爆炸性增长时，其拓扑复杂度缓慢增长