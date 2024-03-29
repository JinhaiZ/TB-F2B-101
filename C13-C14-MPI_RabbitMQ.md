# MPI

Question: parallelism on shared memory 
- the more the worse

solution 1 parallelism on DSM (Distributed shared memory)
- easy to use
- virtual memory
    - access to local mem
    - access to distance mem

soulution 2 Message passing
- difficult than DSM

The need for parallel machines:
- we cannot build fast enough computers
- data is big while memory is limited
- we can build parallel machines but there are numerous challenges:
    - how to organise data, and how to divide data to different machines, how to get result
- Flynn's taxonomy
    - SISD
    - SIMD: single Intruction - Multiple data: 
    - MIMD:
    - SPMD: Signle Program - Multiple Data: the same version of the prog is replicated and run on different data <- model choosen by MPI
- P.V.M

## MPI Message Passing Interface

MPI is an interface
A message passing library specification
The MPI Forum organized in 1992 with board participation by:
vendors :IBM, Intel, TMC, SGI and Convex

How big is the MPI library
- Huge 125 functions
- small 6 functions

Environment for paralle programming
- Upshot, Jumpshot, and MPE tools
- Pallas VAMPIR
- Paragraph

## a simple MPI program

```c
#include "mpi.h"
#include <stdio.h>

int main(int argc, char *argv[]) {
    int rank, size;
    MPI_init(&argc, &argv);
    MPI_Comm_size(MPI_Comm_World, &size);
    MPI_Comm_rank(MPI_Comm_World, &rank);
    printf("I am %d of %d\n", rank, size);
    MPI_finalize();
    return 0;
}
```

```bash
gcc main.c
mpirun main -destionation m1 m2 m3 m4
```

Finding out about the environment
2 questions

MPI_Comm_size reports the number of processes
MPI_Comm_rank reports the rannk, number between 0 and size -1

Some basic concepts

MPI blocking send

```c
MPI_SEND(void *start, ini count, MPI_DATATYPE datatype, int dest, int tag, MPI_COMM comm)
```
- the message buffer is described by (start, count, datatype)
- the dest is the rank
- tag is message identifier

MPI blocking receive

```c
MPI_RECV(start, count, datatype, source, tag, comm, satus)
```
- waits untile a matching message is received from the system, and the buffer can be used
- source is rank in commnunicator specified by *comm*
- receiving fewer than count occurrence of datatype is ok, but receiving more is error

The role of worker:
- wait data of master

    ```c   
    s:= s + data(i)
    ```

- send data to master

the role of master/coordonator:

```c
cluster_size = MPI_Comm_size();
//divide the data
for i in 1...cluster_size:
    send(data for i)
for i in cluster_size:
    waite result of i
    s:= s + result
```

write them in a single program SPMD

```c
r := MPI_Comm_rank();
if r == 0 exec master
else exec worker
```

MSC: Message sequence chart

MPI datatypes
- MPI_ANY_SOURCES
MPI data Tags
- MPI_ANY_TAGS

## Retriving further informaiton from status

```c
int recvd_tag, recvd_from, recvd_count;
MPI_Status status;
MPI_Recv(..., MPI_ANY_SOURCE, MPI_ANY_TAGS, ..., &status);
recvd_tag = status.MPI_TAG;
recvd_from = status.MPI_SOURCE;
```

In MPV, we can choose the less loaded machine for load balancing

## Why MPI is simple ?

MPI_Init
MPI_Comm_size
MPI_Comm_rank
MPI_Recv
MPI_Send
MPI_Finalize

## Tolerance of failure

MPI is not so tolerance of failure
a missing message -> a missing part of data

## Basic Function

- Broadcast

```c
MPI_Bcast()
```

- Gather

```c
MPI_Gather
```

- Scatter
- Alligather
- Reduce


## Synchronization

```c
MPI_Barrier
```

# RabbitMQ: a MOM (Message Oriented Middleware)

《RabbitMQ in action》

## AMQP

Need
 - BUssiness, app, services and devices need to interact and communicate information
- IBM monopole who controled over 80% of the market with WebShpere and MQ

AMQP
- Advanced Message Queuing Protocol is an open standard application layer protocol for message oriented middleware

AMQP user group
- Set up by JPMorgan in 2006
- The working group grew to 23 companies, mainly banks
- MOM needs to be everywhere to be useful
- Middleware Hell
- Needs to be standard

Agreed User Requirements
- Ubiquitous and pervasive
    - Scalable, fault-tolerant
    - Compatible with IBM MQ
- Safe
- Fidelity
- Unified
    - a MQ client can communicate with another MQ client
- Interoperability
- Manageable

Various impletaions
- RabbitMQ
- ZeroMQ
- ActiveMQ

## Message Passing withRabbitMQ

RabbitMQ in context
Model Producer and Consumer

Producer(Data -> create msg -> publish msg) -> RabbitMQ -> Consumer

Messages in -> Exchanges(router of message, 3 types, Topic(send msg to a certain machine), Fanout(send msg to certain machines) and Direct) -> Binding(bind message to a queue) -> Queues(Q1,Q2,Q3,Q4,Q5)

Queues
- queues are like named mailboxes
- msg arrived with no consumers
- msg arrived with a sonsumer
- msg arrived with multiple consumers: msg received by the queue are served in a round-robin fashion to the consumers

Exchanges and bindings
- dliver a msg to a queue
    - bindings: direct use queue name as routing
    - bindings: fanout or multicast 
    - bindings with topic: msg be routed based on the key
- Message durability and persistence
    - nomrally message not survive after system roboot
    - but if we set msg deliver mode as persisting, they are now persiste

Summary of messaging

## Example of RabbitMQ

producer side

```python

import pika
connection = pika.BlockingConnect(..)
channel = connection.channel()
channel.queue_decalre(..)

```

consumer side

```python

import pika
connection = pika.BlockingConnect(..)
channel = connection.channel()
channel.queue_decalre(queue="hello")

def callback(ch, method, properties, body):
    print "[x] recevide %r" %(body,)
channel.basic)consume()
channel.start_consuming(callback)

```

A second example

load balancing

worker side

```python

import pika
connection = pika.BlockingConnect(..)
channel = connection.channel()
channel.queue_decalre(queue="hello") # by default durable=False

def callback(ch, method, properties, body):
    print "[x] recevide %r" %(body,)
channel.basic_qos(prefetch=1)  # load balancing
channel.basic_consume()
```

## Adminstration of the broker

## Synthesis

- AMQP is a powerful protocol for connecting devices and applications through various networks
- A very large set of services with enhanced 
- RabbitMQ is an effcient implementation that is used in IoT, services within the cloud (eg: OpenStack), and operataionnal applications